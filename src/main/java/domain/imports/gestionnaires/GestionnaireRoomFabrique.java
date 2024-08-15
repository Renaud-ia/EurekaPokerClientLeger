package domain.imports.gestionnaires;

import domain.core.valeurs.Room;
import domain.imports.gestionnaires.winamax.GestionnaireWinamax;
import domain.exceptions.ErreurGestionRoom;
import domain.imports.services.ServicesGestionnaireFabrique;

public class GestionnaireRoomFabrique {
    public static GestionnaireRoom creerGestionnaireRoom(ServicesGestionnaireFabrique servicesGestionnaireFabrique,
                                                         Room room) throws ErreurGestionRoom {
        return switch (room) {
            case Room.WINAMAX -> new GestionnaireWinamax(servicesGestionnaireFabrique);
            default -> throw new ErreurGestionRoom("La room n'a pas de gestionnaire : " + room);
        };
    }
}
