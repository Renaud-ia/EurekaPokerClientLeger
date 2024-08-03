package domain.core.valeurs;

import domain.exceptions.ErreurGestionRoom;
import domain.imports.gestionnaires.winamax.GestionnaireWinamax;

public enum Room {
    WINAMAX;

    public static Room depuis_nom(String nomRoom) throws ErreurGestionRoom {
        nomRoom = nomRoom.toLowerCase();

        return switch (nomRoom) {
            case "winamax" -> Room.WINAMAX;
            default -> throw new ErreurGestionRoom("La room n'est pas référencée : " + nomRoom);
        };
    }
}
