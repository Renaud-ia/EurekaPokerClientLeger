package domain.imports.services;

import domain.core.valeurs.Room;
import domain.imports.dossiers.NomFichierValide;
import domain.imports.dossiers.PersistenceDossierRoom;
import domain.imports.enregistrement.PersistenceImportParties;

public abstract class ServicesGestionnaireFabrique {
    protected final Room room;
    private final DomainServicesGestionnaireFabrique domainServicesGestionnaireFabrique;
    public ServicesGestionnaireFabrique(Room room) {
        this.room = room;
        this.domainServicesGestionnaireFabrique = new DomainServicesGestionnaireFabrique(room);
    }

    public NomFichierValide obtNomFichierValide() {
        return domainServicesGestionnaireFabrique.obtNomFichierValide();
    }

    public ImportFichierService obtImportFichierService() {
        return domainServicesGestionnaireFabrique.obtImportFichierService();
    }

    public abstract PersistenceImportParties obtPersistenceImportParties();

    public abstract PersistenceDossierRoom obtPersistenceDossiers();

}
