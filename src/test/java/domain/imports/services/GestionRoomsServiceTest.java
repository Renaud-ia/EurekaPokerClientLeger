package domain.imports.services;

import domain.core.valeurs.Room;
import domain.imports.dossiers.PersistenceDossierRoom;
import domain.exceptions.ErreurGestionRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GestionRoomsServiceTest {
    private PersistenceDossierRoom persistenceDossierRoom;
    private ServicesGestionnaireFabrique servicesGestionnaireFabrique;
    @BeforeEach
    public void setUp() {
        // on mock la persistence
        persistenceDossierRoom = Mockito.mock(PersistenceDossierRoom.class);
        when(persistenceDossierRoom.obtListDossiers()).thenReturn(new ArrayList<String>());

        // Pour les tests, les fichiers valides sont selon leur nom
        servicesGestionnaireFabrique = Mockito.mock(ServicesGestionnaireFabrique.class);
        when(servicesGestionnaireFabrique.obtPersistenceDossiers()).thenReturn(persistenceDossierRoom);
    }

    @Test
    public void refuseDeuxCreationsMemeRoom() {
        GestionRoomsService gestionRoomsService = new GestionRoomsService();

        Room room = Room.WINAMAX;

        assertDoesNotThrow(() -> gestionRoomsService.initialiserRoom(room, servicesGestionnaireFabrique));
        assertThrows(ErreurGestionRoom.class, () -> gestionRoomsService.initialiserRoom(room, servicesGestionnaireFabrique));
    }
}
