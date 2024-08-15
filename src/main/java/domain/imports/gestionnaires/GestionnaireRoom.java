package domain.imports.gestionnaires;

import domain.imports.dossiers.NomFichierValide;
import domain.imports.dossiers.ObservateurDossierImport;
import domain.imports.dossiers.PersistenceDossierRoom;
import domain.imports.dossiers.GestionnaireDossiers;
import domain.imports.enregistrement.GestionnaireParties;
import domain.imports.enregistrement.ObservateurImportParties;
import domain.imports.enregistrement.PersistenceImportParties;
import domain.imports.services.AutoDetectionService;
import domain.imports.services.ImportFichierService;
import domain.imports.services.ServicesGestionnaireFabrique;

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
    private final ServicesGestionnaireFabrique servicesGestionnaireFabrique;
    protected final GestionnaireDossiers gestionnaireDossiers;
    protected final GestionnaireParties gestionnaireParties;
    protected GestionnaireRoom(ServicesGestionnaireFabrique servicesGestionnaireFabrique) {
        this.servicesGestionnaireFabrique = servicesGestionnaireFabrique;

        NomFichierValide nomFichierValide = servicesGestionnaireFabrique.obtNomFichierValide();
        PersistenceDossierRoom persistenceDossierRoom = servicesGestionnaireFabrique.obtPersistenceDossiers();
        ObservateurDossierImport observateurDossierImport = servicesGestionnaireFabrique.obtObservateurDossiers();
        this.gestionnaireDossiers = new GestionnaireDossiers(observateurDossierImport, persistenceDossierRoom, nomFichierValide);

        PersistenceImportParties persistenceImportParties = servicesGestionnaireFabrique.obtPersistenceImportParties();
        ObservateurImportParties observateurImportParties = servicesGestionnaireFabrique.obtOservateurImportParties();
        this.gestionnaireParties = new GestionnaireParties(persistenceImportParties, observateurImportParties);
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
                ImportFichierService importFichierService = servicesGestionnaireFabrique.obtImportFichierService();
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
        NomFichierValide nomFichierValide = servicesGestionnaireFabrique.obtNomFichierValide();

        return Files.isRegularFile(chemin) && nomFichierValide.fichierValide(chemin.toString());
    }

    public abstract AutoDetectionService autoDetection();
}
