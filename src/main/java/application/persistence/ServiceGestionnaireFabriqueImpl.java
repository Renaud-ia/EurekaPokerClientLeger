package application.persistence;

import domain.core.valeurs.Room;
import domain.imports.dossiers.PersistenceDossierRoom;
import domain.imports.enregistrement.PersistenceImportParties;
import domain.imports.services.ServicesGestionnaireFabrique;
import infrastructure.PersistanceDossierRoomFichierJson;
import infrastructure.PersistancePartiesWeb;

/**
 * injection de dépendances pour les gestionnaires de Room
  */
public class ServiceGestionnaireFabriqueImpl extends ServicesGestionnaireFabrique {
    public ServiceGestionnaireFabriqueImpl(Room room) {
        super(room);
    }

    @Override
    public PersistenceDossierRoom obtPersistenceDossiers() {
        return new PersistanceDossierRoomFichierJson();
    }

    @Override
    public PersistenceImportParties obtPersistenceImportParties() {
        return new PersistancePartiesWeb();
    }
}
