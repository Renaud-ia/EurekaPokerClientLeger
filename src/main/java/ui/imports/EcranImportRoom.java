package ui.imports;

import application.notifications.NotificationGestionDossiersRoom;
import domain.core.valeurs.Room;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

class EcranImportRoom extends Tab implements NotificationGestionDossiersRoom {
    private ListView<String> dossiersImportes;
    EcranImportRoom(Room room) {
        super(room.toString());
        this.setClosable(false);

        VBox contenu = new VBox();

        Label label = new Label("Nombre de mains importées : 25");
        contenu.getChildren().add(label);

        dossiersImportes = new ListView<>();
        dossiersImportes.getItems().add("Dossier juste pour tester");
        dossiersImportes.getItems().add("Dossier juste pour tester");
        contenu.getChildren().add(dossiersImportes);

        this.setContent(contenu);
    }

    @Override
    public void dossierAjoute(String nomDossier) {

    }

    @Override
    public void dossierSupprime(String nomDossier) {

    }
}
