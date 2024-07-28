package domain.imports.fabriques;

import domain.imports.agregats.GestionnaireRoom;
import domain.imports.agregats.GestionnaireWinamax;
import domain.imports.exceptions.ErreurGestionRoom;

public class GestionnaireRoomFabrique {
    public static GestionnaireRoom creerGestionnaireRoom(String nomRoom) throws ErreurGestionRoom {
        switch (nomRoom.toLowerCase()) {
            case "winamax":
                return new GestionnaireWinamax();
            default:
                throw new ErreurGestionRoom("La room n'a pas de gestionnaire : " + nomRoom);
        }
    }
}
