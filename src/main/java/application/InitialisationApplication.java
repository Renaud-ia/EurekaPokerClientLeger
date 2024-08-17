package application;

import application.config.ConfigRooms;
import application.controleurs.ControleurImportGlobal;
import application.controleurs.ControleurImportGlobalImpl;
import application.controleurs.ControleurImportRoom;
import application.controleurs.ControleurImportRoomImpl;
import application.persistence.ServiceGestionnaireFabriqueImpl;
import domain.core.valeurs.Room;
import application.notifications.NotificationDemarrage;
import application.notifications.NotificationGestionDossiersRoom;
import application.notifications.NotificationImportParties;
import domain.exceptions.ErreurGestionRoom;
import domain.imports.gestionnaires.GestionnaireRoom;
import domain.imports.gestionnaires.GestionnaireRoomFabrique;
import infrastructure.ConfigurationStub;

import java.util.List;

public class InitialisationApplication {
    private final NotificationDemarrage notificationDemarrage;
    private final ConfigRooms configRooms = new ConfigurationStub();
    private ControleurImportGlobal controleurImportGlobal;
    public InitialisationApplication(NotificationDemarrage notificationDemarrage) {
        this.notificationDemarrage = notificationDemarrage;
        notificationDemarrage.changerMessageDemarrage("Initialisation de l'application");
    }

    public ControleurImportGlobal initialiserImportGlobal(NotificationImportParties notificationImportParties) {
        this.controleurImportGlobal = new ControleurImportGlobalImpl(notificationImportParties);
        return controleurImportGlobal;
    }

    public List<Room> roomsDisponibles() {
        return configRooms.obtRoomsDisponibles();
    }
    public ControleurImportRoom initialiserImportRoom(Room room,
                                                      NotificationGestionDossiersRoom notificationGestionDossiersRoom)
            throws InterruptedException, ErreurGestionRoom {
        notificationDemarrage.changerMessageDemarrage("Initialisation de Winamax");

        ControleurImportRoom controleurImportRoom = null;

        ServiceGestionnaireFabriqueImpl serviceGestionnaireFabrique
                    = new ServiceGestionnaireFabriqueImpl(room);
        GestionnaireRoom nouveauGestionnaire =
                    GestionnaireRoomFabrique.creerGestionnaireRoom(serviceGestionnaireFabrique, room);
        controleurImportRoom = new ControleurImportRoomImpl(nouveauGestionnaire, notificationGestionDossiersRoom);

        this.controleurImportGlobal.ajouterControleurRoom(controleurImportRoom);
        Thread.sleep(2000);

        notificationDemarrage.changerMessageDemarrage("On va faire une pause");
        Thread.sleep(5000);
        notificationDemarrage.changerMessageDemarrage("On a fini la pause");

        return controleurImportRoom;
    }
}
