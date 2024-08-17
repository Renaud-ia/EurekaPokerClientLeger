import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuJavaFx extends Application {

    private VBox viewContainer;

    @Override
    public void start(Stage primaryStage) {
        // Créer le BorderPane principal
        BorderPane root = new BorderPane();

        // Créer le conteneur pour les vues
        viewContainer = new VBox();
        root.setCenter(viewContainer);

        // Créer une barre de menu horizontale
        HBox menuBar = new HBox(10); // Espacement entre les boutons
        menuBar.setStyle("-fx-padding: 10px; -fx-background-color: #333;");

        // Créer les boutons pour le menu
        Button view1Button = new Button("View 1");
        view1Button.setStyle("-fx-text-fill: white;");
        view1Button.setOnAction(event -> loadView1());

        Button view2Button = new Button("View 2");
        view2Button.setStyle("-fx-text-fill: white;");
        view2Button.setOnAction(event -> loadView2());

        // Ajouter les boutons au menu
        menuBar.getChildren().addAll(view1Button, view2Button);

        // Ajouter la barre de menu en haut du BorderPane
        root.setTop(menuBar);

        // Configurer la scène et le stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("JavaFX Custom Menu Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Méthode pour charger View 1
    private void loadView1() {
        viewContainer.getChildren().clear();
        VBox view1 = new VBox();
        view1.getChildren().add(new javafx.scene.control.Label("This is View 1"));
        viewContainer.getChildren().add(view1);
    }

    // Méthode pour charger View 2
    private void loadView2() {
        viewContainer.getChildren().clear();
        VBox view2 = new VBox();
        view2.getChildren().add(new javafx.scene.control.Label("This is View 2"));
        viewContainer.getChildren().add(view2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}