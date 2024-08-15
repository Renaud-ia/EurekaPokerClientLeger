package application.controleurs;

import domain.imports.services.GestionRoomsService;

public class ControleurImport {
    private final GestionRoomsService gestionRoomsService;
    public ControleurImport(GestionRoomsService gestionRoomsService) {
        this.gestionRoomsService = gestionRoomsService;
    }
}
