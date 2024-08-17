package domain.imports.dossiers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class GestionnaireDossiers {
    int N_FICHIERS_TESTES_PAR_DOSSIER = 5;
    private final PersistenceDossierRoom persistenceDossierRoom;
    private final List<String> dossiersAjoutes;
    private final NomFichierValide nomFichierValide;
    public GestionnaireDossiers(
            PersistenceDossierRoom persistenceDossierRoom,
            NomFichierValide nomFichierValide) {
        this.persistenceDossierRoom = persistenceDossierRoom;
        this.dossiersAjoutes = persistenceDossierRoom.obtListDossiers();
        this.nomFichierValide = nomFichierValide;
    }
    /**
     * @param cheminDossier chemin du dossier à ajouter
     * @return null si dossier bien ajouté, le message d'erreur sinon
     */
    public String ajouterDossier(String cheminDossier) {
        if (dossierDejaAjoute(cheminDossier)) {
            return "Ce dossier a déjà été ajouté";
        }

        if (dossierParentDejaAjoute(cheminDossier)) {
            return "Un dossier parent à déjà été ajouté";
        }

        if (!estUnDossierValide(cheminDossier)) {
            return "Ce dossier n'est pas valide";
        }

        remplacerDossierEnfants(cheminDossier);

        procedureAjoutDossier(cheminDossier);
        return null;
    }

    public void supprimerDossier(String cheminDossier) {
        procedureRetraitDossier(cheminDossier, true);
    }



    private void remplacerDossierEnfants(String cheminDossier) {
        Path cheminDossierPath = Paths.get(cheminDossier);

        Iterator<String> iterator = dossiersAjoutes.iterator();
        while (iterator.hasNext()) {
            Path cheminExistantPath = Paths.get(iterator.next());

            if (cheminExistantPath.startsWith(cheminDossierPath) && !cheminExistantPath.equals(cheminDossierPath)) {
                procedureRetraitDossier(cheminExistantPath.toString(), false);
                iterator.remove();
            }
        }
    }

    /**
     * vérifie si c'est un dossier valide
     * doit contenir des fichiers valides
     * (ou si ne contient que des dossiers) tous ses sous-dossiers doivent contenir des fichiers valides
     */
    private boolean estUnDossierValide(String cheminDossier) {
        // on vérifie si tous les dossiers de manière récursive sont valides
        Path start = Paths.get(cheminDossier);
        try (Stream<Path> stream = Files.walk(start)) {
            for (Path path : (Iterable<Path>) stream::iterator) {
                if (!Files.isDirectory(path)) continue;

                if (!contientPartiesValides(path.toString())) return false;
            }

        } catch (IOException e) {
            // todo logger erreurs
            e.printStackTrace();
        }

        return true;
    }

    private boolean contientPartiesValides(String cheminDossier) {
        int nFichiersTestes = 0;

        Path chemin = Paths.get(cheminDossier);
        try (Stream<Path> stream = Files.list(chemin)) {
            for (Path path : (Iterable<Path>) stream::iterator) {
                if (Files.isDirectory(path)) continue;
                if (nFichiersTestes++ >= this.N_FICHIERS_TESTES_PAR_DOSSIER) break;

                if (nomFichierValide.fichierValide(path.toString())) return true;
            }
        } catch (IOException e) {
            // todo logger les erreurs
            e.printStackTrace();
        }

        return nFichiersTestes == 0;
    }

    private boolean dossierDejaAjoute(String cheminDossier) {
        return dossiersAjoutes.contains(cheminDossier);
    }

    private boolean dossierParentDejaAjoute(String cheminDossier) {
        Path chemin = Paths.get(cheminDossier);

        for (String dossier : dossiersAjoutes) {
            Path cheminExistant = Paths.get(dossier);
            if (chemin.startsWith(cheminExistant)) {
                return true;
            }
        }
        return false;
    }

    public List<String> obtListeDossiers() {
        return dossiersAjoutes;
    }

    private void procedureAjoutDossier(String nomDossier) {
        persistenceDossierRoom.ajouterDossier(nomDossier);
        dossiersAjoutes.add(nomDossier);
    }

    private void procedureRetraitDossier(String nomDossier, boolean supprimerDeLaListe) {
        persistenceDossierRoom.supprimerDossier(nomDossier);
        if (supprimerDeLaListe) dossiersAjoutes.remove(nomDossier);
    }
}
