package ui;

import application.InitialisationApplication;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ui.demarrage.DemarrageImportService;
import ui.demarrage.FenetreDemarrage;
import ui.imports.VueImport;
import ui.modeles.BandeauBas;
import ui.modeles.BandeauHaut;

import java.util.Objects;

public class FenetrePrincipale extends Application {
    private FenetreDemarrage fenetreDemarrage;
    private GestionnaireContenu gestionnaireContenu;
    private VueImport vueImport;
    private Stage contenu;

    public FenetrePrincipale() {
    }

    @Override
    public void start(Stage stage) {
        this.contenu = stage;

        fenetreDemarrage = new FenetreDemarrage();
        fenetreDemarrage.afficher();

        fenetreDemarrage.changerMessageDemarrage("Chargement des composants graphiques");
        creerComposantsGraphiques();

        initialiserApplication();
    }

    private void creerComposantsGraphiques() {
        initialiserFenetrePrincipale();
        this.vueImport = new VueImport(gestionnaireContenu);
        gestionnaireContenu.fixVueImport(vueImport);

        gestionnaireContenu.afficherPageImport();
    }

    private void initialiserFenetrePrincipale() {
        BorderPane borderPane = new BorderPane();

        BandeauHaut panneauHaut = new BandeauHaut();
        borderPane.setTop(panneauHaut);

        BandeauBas bandeauBas = new BandeauBas();
        borderPane.setBottom(bandeauBas);

        gestionnaireContenu = new GestionnaireContenu(borderPane);

        Scene scene = new Scene(borderPane, 600, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style_test.css")).toExternalForm());
        contenu.setTitle("Eurêka Poker v0.1");
        contenu.setScene(scene);
    }

    private void initialiserApplication() {
        InitialisationApplication initialisationApplication = new InitialisationApplication(fenetreDemarrage);
        DemarrageImportService demarrageImportService =
                new DemarrageImportService(initialisationApplication, vueImport);

        demarrageImportService.setOnSucceeded(
                event -> {
                    fenetreDemarrage.changerMessageDemarrage("Initialisation des autres composants");
                    // todo lancer les autres tâches d'initialisation
                    fenetreDemarrage.fermer();
                    contenu.show();
                });

        demarrageImportService.setOnFailed(event -> {
            fenetreDemarrage.changerMessageDemarrage("Erreur lors de l'initialisation des Rooms");
            // todo logger l'erreur
        });


        demarrageImportService.start();
    }
}