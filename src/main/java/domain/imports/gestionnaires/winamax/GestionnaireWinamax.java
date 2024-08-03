package domain.imports.gestionnaires.winamax;

import domain.core.valeurs.Room;
import domain.imports.gestionnaires.GestionnaireRoom;
import domain.imports.services.AutoDetectionService;
import domain.imports.services.ImportFichierService;
import domain.imports.dossiers.AutoDetectionWinamax;

import java.util.List;

public class GestionnaireWinamax extends GestionnaireRoom {
    public GestionnaireWinamax() {
        super(Room.WINAMAX);
    }
    @Override
    public AutoDetectionService autoDetection() {
        return new AutoDetectionWinamax(gestionnaireDossiers);
    }
}
