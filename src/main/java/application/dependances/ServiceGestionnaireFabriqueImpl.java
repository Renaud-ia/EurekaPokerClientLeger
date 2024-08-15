package application.dependances;

import application.vues.VueImport;
import domain.core.valeurs.Room;
import domain.imports.dossiers.ObservateurDossierImport;
import domain.imports.dossiers.PersistenceDossierRoom;
import domain.imports.enregistrement.ObservateurImportParties;
import domain.imports.enregistrement.PersistenceImportParties;
import domain.imports.services.ServicesGestionnaireFabrique;
import infrastructure.PersistanceDossierRoomFichierJson;
import infrastructure.PersistancePartiesWeb;

/**
 * injection de dépendances pour les gestionnaires de Room
  */
public class ServiceGestionnaireFabriqueImpl extends ServicesGestionnaireFabrique {
    private final VueImport vueImport;
    public ServiceGestionnaireFabriqueImpl(VueImport vueImport, Room room) {
        super(room);
        this.vueImport = vueImport;
    }

    @Override
    public PersistenceDossierRoom obtPersistenceDossiers() {
        return new PersistanceDossierRoomFichierJson();
    }

    @Override
    public ObservateurDossierImport obtObservateurDossiers() {
        return vueImport.obtObservateurDossiers(room);
    }

    @Override
    public PersistenceImportParties obtPersistenceImportParties() {
        return new PersistancePartiesWeb();
    }

    @Override
    public ObservateurImportParties obtOservateurImportParties() {
        return vueImport.observateurImportParties(room);
    }
}
