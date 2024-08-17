package ui.imports;

import application.controleurs.ControleurImportGlobal;
import application.controleurs.ControleurImportRoom;
import application.notifications.NotificationGestionDossiersRoom;
import application.notifications.NotificationImportParties;
import domain.core.valeurs.Room;
import javafx.geometry.Insets;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import ui.GestionnaireContenu;

public class VueImport extends StackPane {
    private final GestionnaireContenu gestionnaireContenu;
    private final TabPane selecteurRooms;
    private final BandeauImport bandeauImport;
    public VueImport(GestionnaireContenu gestionnaireContenu) {
        this.gestionnaireContenu = gestionnaireContenu;

        selecteurRooms = new TabPane();

        bandeauImport = new BandeauImport();

        VBox centerLayout = new VBox(10, selecteurRooms, bandeauImport);
        centerLayout.setPadding(new Insets(10));

        this.getChildren().add(centerLayout);
    }

    public void fixControleurImportGlobal(ControleurImportGlobal controleurImportGlobal) {

    }

    public void ajouterControleur(Room room, ControleurImportRoom controleurImportRoom) {
    }

    public NotificationGestionDossiersRoom creerRoom(Room room) {
        EcranImportRoom ecranImportRoom = new EcranImportRoom(room);
        selecteurRooms.getTabs().add(ecranImportRoom);

        return ecranImportRoom;
    }

    public NotificationImportParties obtNotificationImportGlobal() {
        return null;
    }

}
