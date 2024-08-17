package ui.imports;

import application.controleurs.ControleurImportGlobal;
import application.controleurs.ControleurImportRoom;
import application.notifications.NotificationGestionDossiersRoom;
import application.notifications.NotificationImportParties;
import domain.core.valeurs.Room;
import ui.GestionnaireContenu;
import ui.modeles.ModelePage;

public class VueImport extends ModelePage {
    public VueImport(GestionnaireContenu gestionnaireContenu) {

    }

    public void fixControleurImportGlobal(ControleurImportGlobal controleurImportGlobal) {

    }

    public NotificationImportParties obtNotificationImportGlobal() {
        return null;
    }

    public NotificationGestionDossiersRoom creerRoom(Room room) {
        return null;
    }

    public void ajouterControleur(Room room, ControleurImportRoom controleurImportRoom) {
    }
}
