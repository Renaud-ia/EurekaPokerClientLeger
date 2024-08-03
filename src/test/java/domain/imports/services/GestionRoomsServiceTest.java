package domain.imports.services;

import domain.exceptions.ErreurGestionRoom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestionRoomsServiceTest {
    @Test
    public void refuseDeuxCreationsMemeRoom() {
        GestionRoomsService gestionRoomsService = new GestionRoomsService();

        String nomRoom = "Winamax";

        assertDoesNotThrow(() -> gestionRoomsService.initialiserRoom(nomRoom));
        assertThrows(ErreurGestionRoom.class, () -> gestionRoomsService.initialiserRoom(nomRoom));
    }
}
