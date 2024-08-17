import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashScreen extends Application {

    private Stage splashStage;

    @Override
    public void start(Stage primaryStage) {
        showSplashScreen(primaryStage);

        // Simuler un chargement de l'application (par exemple, chargement de données)
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(3000); // Simuler un délai de 3 secondes
                return null;
            }
        };

        task.setOnSucceeded(event -> {
            splashStage.close(); // Fermer le splash screen
            showMainStage(primaryStage); // Montrer la fenêtre principale
        });

        new Thread(task).start();
    }

    private void showSplashScreen(Stage primaryStage) {
        // Créer un nouveau stage pour le splash screen
        splashStage = new Stage();
        splashStage.initStyle(StageStyle.UNDECORATED); // Enlever les bordures de fenêtre

        // Créer le contenu du splash screen
        StackPane splashLayout = new StackPane();
        splashLayout.getChildren().add(new Label("Loading, please wait..."));

        Scene splashScene = new Scene(splashLayout, 300, 200);
        splashStage.setScene(splashScene);
        splashStage.show();
    }

    private void showMainStage(Stage primaryStage) {
        // Créer le contenu de la fenêtre principale
        StackPane mainLayout = new StackPane();
        mainLayout.getChildren().add(new Label("This is the main application"));

        Scene mainScene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Main Application");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}