package domain.imports.gestionnaires.winamax;

import domain.imports.gestionnaires.GestionnaireRoom;
import domain.imports.services.ServicesGestionnaireFabrique;
import domain.imports.services.AutoDetectionService;
import domain.imports.dossiers.AutoDetectionWinamax;

public class GestionnaireWinamax extends GestionnaireRoom {
    public GestionnaireWinamax(ServicesGestionnaireFabrique servicesGestionnaireFabrique) {
        super(servicesGestionnaireFabrique);
    }
    @Override
    public AutoDetectionService autoDetection() {
        return new AutoDetectionWinamax(gestionnaireDossiers);
    }
}
