package domain.imports.gestionnaires;

import domain.imports.gestionnaires.winamax.GestionnaireWinamax;
import domain.exceptions.ErreurGestionRoom;

public class GestionnaireRoomFabrique {
    public static GestionnaireRoom creerGestionnaireRoom(String nomRoom) throws ErreurGestionRoom {
        return switch (nomRoom.toLowerCase()) {
            case "winamax" -> new GestionnaireWinamax();
            default -> throw new ErreurGestionRoom("La room n'a pas de gestionnaire : " + nomRoom);
        };
    }
}
