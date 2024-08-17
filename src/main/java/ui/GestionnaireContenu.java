package ui;

import javafx.scene.layout.BorderPane;
import ui.imports.VueImport;

public class GestionnaireContenu {
    private final BorderPane borderPane;
    private VueImport vueImport;
    GestionnaireContenu(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    void fixVueImport(VueImport vueImport) {
        this.vueImport = vueImport;
    }

    public void afficherPageImport() {
        this.borderPane.setCenter(vueImport);
    }
}
