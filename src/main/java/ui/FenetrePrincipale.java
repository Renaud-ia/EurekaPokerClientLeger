package ui;

import application.InitialisationApplication;
import javafx.application.Application;
import javafx.stage.Stage;
import ui.demarrage.DemarrageImportService;
import ui.demarrage.FenetreDemarrage;
import ui.imports.VueImport;

public class FenetrePrincipale extends Application {
    private FenetreDemarrage fenetreDemarrage;
    private GestionnaireContenu gestionnaireContenu;
    private VueImport vueImport;

    public FenetrePrincipale() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        fenetreDemarrage = new FenetreDemarrage();
        fenetreDemarrage.afficher();

        fenetreDemarrage.changerMessageDemarrage("Chargement des composants graphiques");
        creerComposantsGraphiques();

        initialiserApplication();
    }

    private void creerComposantsGraphiques() {
        gestionnaireContenu = new GestionnaireContenu();
        this.vueImport = new VueImport(gestionnaireContenu);

    }

    private void initialiserApplication() {
        InitialisationApplication initialisationApplication = new InitialisationApplication(fenetreDemarrage);
        DemarrageImportService demarrageImportService =
                new DemarrageImportService(initialisationApplication, vueImport);

        demarrageImportService.setOnSucceeded(
                event -> {
                    fenetreDemarrage.changerMessageDemarrage("Initialisation des autres composants");
                    // todo lancer les autres tâches d'initialisation
                });

        demarrageImportService.setOnFailed(event -> {
            fenetreDemarrage.changerMessageDemarrage("Erreur lors de l'initialisation des Rooms");
            // todo logger l'erreur
        });


        demarrageImportService.start();
    }
}