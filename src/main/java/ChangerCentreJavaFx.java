import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChangerCentreJavaFx extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Créer le BorderPane
        BorderPane borderPane = new BorderPane();

        // Créer la partie fixe en haut (Top)
        Label topLabel = new Label("Contenu fixe en haut");
        borderPane.setTop(topLabel);

        // Créer la partie fixe en bas (Bottom)
        Label bottomLabel = new Label("Contenu fixe en bas");
        borderPane.setBottom(bottomLabel);

        // Créer le contenu initial pour la région centrale (Center)
        Label centerLabel = new Label("Contenu initial au centre");
        borderPane.setCenter(centerLabel);

        // Créer le premier Pane pour le centre (ex. VBox)
        VBox vbox1 = new VBox(10);
        vbox1.setAlignment(Pos.CENTER);
        vbox1.getChildren().addAll(
                new Label("Vue 1 - Element 1"),
                new Label("Vue 1 - Element 2"),
                new Button("Bouton Vue 1")
        );


        // Créer un autre Pane pour le centre (ex. VBox)
        BorderPane vbox2 = new BorderPane();
        vbox2.setTop(new Label("Vue 2 - Element A"));
        vbox2.setRight(new Label("Vue 2 - Element B"));
        Button bouton2 = new Button("Bouton Vue 2");
        vbox2.setCenter(bouton2);
        bouton2.setOnAction(e -> vbox2.setTop(new Label("Clic!!")));

        // Boutons pour changer le contenu au centre
        Button changeCenterButton1 = new Button("Changer Centre - Vue 1");
        changeCenterButton1.setOnAction(e -> {
            borderPane.setCenter(vbox1);
        });

        Button changeCenterButton2 = new Button("Changer Centre - Vue 2");
        changeCenterButton2.setOnAction(e -> {
            borderPane.setCenter(vbox2);
        });

        // Ajouter des boutons dans la partie droite pour changer le centre
        VBox rightMenu = new VBox(10, changeCenterButton1, changeCenterButton2);
        borderPane.setRight(rightMenu);

        // Créer la scène et l'afficher
        Scene scene = new Scene(borderPane, 600, 400);
        primaryStage.setTitle("JavaFX BorderPane Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}