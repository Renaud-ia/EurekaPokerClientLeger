package domain.imports.services;

import domain.imports.dossiers.ObservateurDossierImport;
import domain.imports.dossiers.PersistenceDossierRoom;
import domain.core.valeurs.Room;
import domain.imports.dossiers.NomFichierValide;
import domain.imports.enregistrement.ObservateurImportParties;
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

    public abstract PersistenceDossierRoom obtPersistenceDossiers();

    public abstract ObservateurDossierImport obtObservateurDossiers();

    public abstract PersistenceImportParties obtPersistenceImportParties();

    public abstract ObservateurImportParties obtOservateurImportParties();
}
