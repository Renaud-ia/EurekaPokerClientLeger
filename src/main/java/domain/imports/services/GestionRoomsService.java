package domain.imports.services;

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
    private HashMap<String, GestionnaireRoom> gestionnaires;

    public GestionRoomsService() {
        gestionnaires = new HashMap<>();
    }
    public void initialiserRoom(String nomRoom) throws ErreurGestionRoom {
        if (gestionnaires.containsKey(nomRoom)) {
            throw new ErreurGestionRoom("La room a déjà été initialisée");
        }

        GestionnaireRoom nouveauGestionnaire = GestionnaireRoomFabrique.creerGestionnaireRoom(nomRoom);

        gestionnaires.put(nomRoom, nouveauGestionnaire);
    }

    public String ajouterDossier(String nomRoom, String cheminDossier) throws ErreurGestionRoom {
        GestionnaireRoom gestionnaireRoom = obtGestionnaire(nomRoom);

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

    private GestionnaireRoom obtGestionnaire(String nomRoom) throws ErreurGestionRoom {
        if (!gestionnaires.containsKey(nomRoom)) {
            throw new ErreurGestionRoom("La room n'a pas été initialisée");
        }

        return gestionnaires.get(nomRoom);
    }
}
