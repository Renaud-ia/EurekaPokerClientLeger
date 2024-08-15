package application.vues;

import application.controleurs.ControleurImport;
import domain.core.valeurs.Room;
import domain.imports.dossiers.ObservateurDossierImport;
import domain.imports.enregistrement.ObservateurImportParties;

public abstract class VueImport {
    private final ControleurImport controleurImport;
    public VueImport(ControleurImport controleurImport) {
        this.controleurImport = controleurImport;
    }

    public abstract ObservateurDossierImport obtObservateurDossiers(Room room);

    public abstract ObservateurImportParties observateurImportParties(Room room);
}
