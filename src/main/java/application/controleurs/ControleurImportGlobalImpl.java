package application.controleurs;

import application.notifications.NotificationImportParties;
import domain.imports.services.ImportFichierService;

import java.util.ArrayList;
import java.util.List;

public class ControleurImportGlobalImpl implements ControleurImportGlobal {
    private final List<ControleurImportRoom> controleursRoom;
    private final NotificationImportParties notificationImportParties;
    public ControleurImportGlobalImpl(NotificationImportParties notificationImportParties) {
        this.notificationImportParties = notificationImportParties;
        this.controleursRoom = new ArrayList<>();
    }
    @Override
    public void ajouterControleurRoom(ControleurImportRoom controleurImportRoom) {
        controleursRoom.add(controleurImportRoom);
    }

    @Override
    public void toutImporter() {
        List<ImportFichierService> fichiersImportables = new ArrayList<>();

        for (ControleurImportRoom controleurImportRoom : controleursRoom) {
            fichiersImportables.addAll(controleurImportRoom.obtFichiersImportables());
        }
    }
}
