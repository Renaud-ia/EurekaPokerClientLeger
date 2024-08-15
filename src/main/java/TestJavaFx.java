import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TestJavaFx extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Créer le panneau principal
        BorderPane root = new BorderPane();

        // Panneau des onglets pour chaque room
        TabPane tabPane = new TabPane();

        // Ajout d'onglets pour chaque room
        for (int i = 1; i <= 3; i++) {
            Tab tab = new Tab("Room " + i);
            VBox roomPane = new VBox(new Text("Contrôles pour Room " + i));
            tab.setContent(roomPane);
            tabPane.getTabs().add(tab);
        }

        // Ajouter le TabPane au centre
        root.setCenter(tabPane);

        // Panneau commun en bas
        HBox bottomPane = new HBox(new Text("Panneau Commun pour les contrôles globaux"));
        root.setBottom(bottomPane);

        // Créer la scène et l'afficher
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Gestion des Rooms");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
