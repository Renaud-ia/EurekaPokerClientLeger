package domain.imports.services;

import domain.core.valeurs.Room;
import domain.imports.gestionnaires.GestionnaireRoom;
import domain.exceptions.ErreurGestionRoom;
import domain.imports.gestionnaires.GestionnaireRoomFabrique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * crée les gestionnaires de room et les gardes en mémoire
 * interface pour lancer les services des rooms
 */
public class GestionRoomsService {
    private HashMap<Room, GestionnaireRoom> gestionnaires;

    public GestionRoomsService() {
        gestionnaires = new HashMap<>();
    }
    public void initialiserRoom(String nomRoom) throws ErreurGestionRoom {
        Room room = Room.depuis_nom(nomRoom);

        if (gestionnaires.containsKey(room)) {
            throw new ErreurGestionRoom("La room a déjà été initialisée");
        }

        GestionnaireRoom nouveauGestionnaire = GestionnaireRoomFabrique.creerGestionnaireRoom(room);

        gestionnaires.put(room, nouveauGestionnaire);
    }

    public String ajouterDossier(Room room, String cheminDossier) throws ErreurGestionRoom {
        GestionnaireRoom gestionnaireRoom = obtGestionnaire(room);

        return gestionnaireRoom.ajouterDossier(cheminDossier);
    }

    public List<domain.imports.services.ImportFichierService> importFichierServices() {
        List<domain.imports.services.ImportFichierService> fichiersImportables = new ArrayList<>();

        for (GestionnaireRoom gestionnaireRoom: gestionnaires.values()) {
            fichiersImportables.addAll(gestionnaireRoom.fichiersImportables());
        }

        return fichiersImportables;
    }

    public List<domain.imports.services.AutoDetectionService> autoDetectionServices() {
        List<domain.imports.services.AutoDetectionService> autoDetections = new ArrayList<>();

        for (GestionnaireRoom gestionnaireRoom: gestionnaires.values()) {
            autoDetections.add(gestionnaireRoom.autoDetection());
        }

        return autoDetections;
    }

    private GestionnaireRoom obtGestionnaire(Room room) throws ErreurGestionRoom {
        if (!gestionnaires.containsKey(room)) {
            throw new ErreurGestionRoom("La room n'a pas été initialisée");
        }

        return gestionnaires.get(room);
    }
}
