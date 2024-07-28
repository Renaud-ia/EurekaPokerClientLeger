package domain.imports.agregats;

import domain.imports.services.AutoDetectionService;
import domain.imports.services.ImportFichierService;
import domain.imports.services.NomFichierValide;
import domain.imports.services.winamax.AutoDetectionWinamax;
import domain.imports.services.winamax.NomFichierValideWinamax;

import java.util.List;

public class GestionnaireWinamax extends GestionnaireRoom {
    public GestionnaireWinamax() {
        super(new NomFichierValideWinamax());
    }
    @Override
    public List<ImportFichierService> fichiersImportables() {
        //todo
        return null;
    }

    @Override
    public AutoDetectionService autoDetection() {
        return new AutoDetectionWinamax();
    }
}
