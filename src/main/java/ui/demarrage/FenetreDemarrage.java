package ui.demarrage;

import application.notifications.NotificationDemarrage;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.modeles.FenetreSecondaire;

public class FenetreDemarrage implements FenetreSecondaire, NotificationDemarrage {

    private final Stage stage;
    private final Label messageLabel;

    public FenetreDemarrage() {
        stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);

        messageLabel = new Label("Chargement en cours...");

        StackPane layout = new StackPane(messageLabel);
        Scene scene = new Scene(layout, 300, 200);

        stage.setScene(scene);
    }

    @Override
    public void afficher() {
        stage.show();
    }

    @Override
    public void fermer() {
        stage.close();
    }

    @Override
    public void changerMessageDemarrage(String message) {
        Platform.runLater(() -> messageLabel.setText(message));
    }

    @Override
    public void notifierErreur(String message) {

    }
}