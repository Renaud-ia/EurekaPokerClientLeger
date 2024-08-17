package ui.demarrage;

import application.InitialisationApplication;
import application.controleurs.ControleurImportGlobal;
import application.controleurs.ControleurImportRoom;
import application.notifications.NotificationGestionDossiersRoom;
import application.notifications.NotificationImportParties;
import domain.core.valeurs.Room;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import ui.imports.VueImport;

public class DemarrageImportService extends Service<Void> {
    private final InitialisationApplication initialisationApplication;
    private final VueImport vueImport;
    public DemarrageImportService(InitialisationApplication initialisationApplication, VueImport vueImport) {
        this.initialisationApplication = initialisationApplication;
        this.vueImport = vueImport;
    }
    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                NotificationImportParties notificationImportParties = vueImport.obtNotificationImportGlobal();
                ControleurImportGlobal controleurImportGlobal =
                        initialisationApplication.initialiserImportGlobal(notificationImportParties);
                vueImport.fixControleurImportGlobal(controleurImportGlobal);

                for (Room room : initialisationApplication.roomsDisponibles()) {
                    NotificationGestionDossiersRoom notificationGestionDossiersRoom = vueImport.creerRoom(room);
                    ControleurImportRoom controleurImportRoom =
                            initialisationApplication.initialiserImportRoom(room, notificationGestionDossiersRoom);
                    vueImport.ajouterControleur(room, controleurImportRoom);
                }
                return null;
            }
        };
    }
}
