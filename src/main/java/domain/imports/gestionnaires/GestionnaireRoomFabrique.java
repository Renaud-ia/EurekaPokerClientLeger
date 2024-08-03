package domain.imports.gestionnaires;

import domain.core.valeurs.Room;
import domain.imports.gestionnaires.winamax.GestionnaireWinamax;
import domain.exceptions.ErreurGestionRoom;

public class GestionnaireRoomFabrique {
    public static GestionnaireRoom creerGestionnaireRoom(Room room) throws ErreurGestionRoom {
        return switch (room) {
            case Room.WINAMAX -> new GestionnaireWinamax();
            default -> throw new ErreurGestionRoom("La room n'a pas de gestionnaire : " + room);
        };
    }
}
