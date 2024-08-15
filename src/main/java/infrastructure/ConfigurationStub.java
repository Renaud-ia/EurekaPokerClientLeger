package infrastructure;

import application.config.ConfigRooms;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationStub implements ConfigRooms {
    @Override
    public List<String> obtRoomsDisponibles() {
        List<String> listeRooms = new ArrayList<>();
        listeRooms.add("Winamax");
        return listeRooms;
    }
}
