package ui;

import application.controleurs.ControleurImport;
import application.vues.VueImport;
import domain.core.valeurs.Room;
import domain.imports.dossiers.ObservateurDossierImport;
import domain.imports.enregistrement.ObservateurImportParties;

public class VueImportJavaFx extends VueImport {
    // todo : construire la vue
    // construire un écran par room et le binder sur un nouveau modèleVue
    // puis récupérer les observeurs depuis le Modèle Vue
    public VueImportJavaFx(ControleurImport controleurImport) {
        super(controleurImport);
    }

    @Override
    public ObservateurDossierImport obtObservateurDossiers(Room room) {
        return null;
    }

    @Override
    public ObservateurImportParties observateurImportParties(Room room) {
        return null;
    }
}
