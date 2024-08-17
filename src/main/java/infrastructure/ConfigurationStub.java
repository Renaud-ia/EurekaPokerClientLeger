package infrastructure;

import application.config.ConfigRooms;
import domain.core.valeurs.Room;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationStub implements ConfigRooms {
    @Override
    public List<Room> obtRoomsDisponibles() {
        List<Room> listeRooms = new ArrayList<>();
        listeRooms.add(Room.WINAMAX);
        return listeRooms;
    }
}
