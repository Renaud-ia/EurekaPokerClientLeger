package domain.imports.gestionnaires;

import domain.core.valeurs.Room;
import domain.imports.gestionnaires.winamax.NomFichierValideWinamax;
import domain.imports.parsing.ImportFichierWinamax;
import domain.imports.services.ImportFichierService;

/**
 * fabrique des services internes qui seront utilisés par les gestionnaires
 */
class FabriqueServicesRoom {
    private final Room room;
    public FabriqueServicesRoom(Room room) {
        this.room = room;
    }

    public NomFichierValide obtNomFichierValide() {
        if (room == Room.WINAMAX) {
            return new NomFichierValideWinamax();
        }

        throw new RuntimeException("Impossible de fabriquer le service pour: " + room);
    }

    public ImportFichierService obtImportFichierService() {
        if (room == Room.WINAMAX) {
            return new ImportFichierWinamax();
        }

        throw new RuntimeException("Impossible de fabriquer le service pour: " + room);
    }
}
