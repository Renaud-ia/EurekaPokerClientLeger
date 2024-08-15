package application;

import application.config.ConfigRooms;
import application.controleurs.ControleurImport;
import application.dependances.ServiceGestionnaireFabriqueImpl;
import application.vues.VueImport;
import domain.core.valeurs.Room;
import domain.imports.services.GestionRoomsService;
import infrastructure.ConfigurationStub;
import ui.VueImportJavaFx;

public class Demarrage {
    public static void main(String[] args) {
        initialiserGestionRooms();
    }

    public static void initialiserGestionRooms() {
        try {
            ConfigRooms configRooms = new ConfigurationStub();
            GestionRoomsService gestionRoomsService = new GestionRoomsService();
            ControleurImport controleurImport = new ControleurImport(gestionRoomsService);
            VueImport vueImport = new VueImportJavaFx(controleurImport);

            for (String nomRoom : configRooms.obtRoomsDisponibles()) {
                Room room = Room.depuis_nom(nomRoom);
                ServiceGestionnaireFabriqueImpl serviceGestionnaireFabrique
                        = new ServiceGestionnaireFabriqueImpl(vueImport, room);
                gestionRoomsService.initialiserRoom(room, serviceGestionnaireFabrique);
            }
        }

        catch (Exception e) {
                System.out.println("Le service d'import n'a pas pu être initialisé : " + e);
            }
        }
}
