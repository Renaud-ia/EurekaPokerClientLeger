package domain.imports.gestionnaires;

import domain.core.valeurs.Room;
import domain.imports.dossiers.GestionnaireDossiers;
import domain.imports.enregistrement.GestionnaireParties;
import domain.imports.services.AutoDetectionService;
import domain.imports.services.ImportFichierService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * gestionnaire de room
 * gère l'orchestration des services
 * gère les logs des erreurs
 */
public abstract class GestionnaireRoom {
    private final FabriqueServicesRoom fabriqueServicesRoom;
    protected final GestionnaireDossiers gestionnaireDossiers;
    protected final GestionnaireParties gestionnaireParties;
    protected GestionnaireRoom(Room room) {
        this.fabriqueServicesRoom = new FabriqueServicesRoom(room);

        NomFichierValide nomFichierValide = fabriqueServicesRoom.obtNomFichierValide();

        this.gestionnaireDossiers = new GestionnaireDossiers(nomFichierValide);
        this.gestionnaireParties = new GestionnaireParties();
    }

    /**
     * @param cheminDossier chemin du dossier à ajouter
     * @return null si dossier bien ajouté, le message d'erreur sinon
     */
    public String ajouterDossier(String cheminDossier) {
        return gestionnaireDossiers.ajouterDossier(cheminDossier);
    }

    public List<ImportFichierService> fichiersImportables() {
        List<String> listeDossiers = gestionnaireDossiers.obtListeDossiers();

        List<ImportFichierService> fichiersImportables = new ArrayList<>();
        for (String dossier : listeDossiers) {
            ajouterTousLesImports(dossier, fichiersImportables);
        }

        return fichiersImportables;
    }

    private void ajouterTousLesImports(String dossier, List<ImportFichierService> fichiersImportables) {
        Path chemin = Paths.get(dossier);
        try (Stream<Path> stream = Files.walk(chemin)) {
            for (Path path : (Iterable<Path>) stream::iterator) {
                if (!fichierEstValide(path)) continue;
                ImportFichierService importFichierService = fabriqueServicesRoom.obtImportFichierService();
                importFichierService.ajouterChemin(path);
                importFichierService.ajouterGestionnaireParties(this.gestionnaireParties);
                fichiersImportables.add(importFichierService);
            }
        }

        catch (IOException e) {
            // todo logger les erreurs
            e.printStackTrace();
        }
    }

    private boolean fichierEstValide(Path chemin) {
        NomFichierValide nomFichierValide = fabriqueServicesRoom.obtNomFichierValide();

        return Files.isRegularFile(chemin) && nomFichierValide.fichierValide(chemin.toString());
    }

    public abstract AutoDetectionService autoDetection();
}
