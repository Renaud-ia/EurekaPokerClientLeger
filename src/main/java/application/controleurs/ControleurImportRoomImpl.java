package application.controleurs;

import application.notifications.NotificationGestionDossiersRoom;
import domain.imports.gestionnaires.GestionnaireRoom;
import domain.imports.services.ImportFichierService;

import java.util.List;

public class ControleurImportRoomImpl implements ControleurImportRoom {
    private final GestionnaireRoom gestionnaireRoom;
    private final NotificationGestionDossiersRoom notificationGestionDossiersRoom;
    public ControleurImportRoomImpl(GestionnaireRoom gestionnaireRoom,
                                    NotificationGestionDossiersRoom notificationGestionDossiersRoom) {
        this.gestionnaireRoom = gestionnaireRoom;
        this.notificationGestionDossiersRoom = notificationGestionDossiersRoom;
    }

    @Override
    public void ajouterDossier(String cheminDossier) {
        String messageImport = gestionnaireRoom.ajouterDossier(cheminDossier);
    }

    @Override
    public void autoDetection() {

    }

    public List<ImportFichierService> obtFichiersImportables() {
        return gestionnaireRoom.fichiersImportables();
    }
}
