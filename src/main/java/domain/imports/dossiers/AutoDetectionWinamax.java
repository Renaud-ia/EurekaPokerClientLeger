package domain.imports.dossiers;

import domain.imports.services.AutoDetectionService;

import java.util.List;

public class AutoDetectionWinamax extends AutoDetectionService {
    public AutoDetectionWinamax(GestionnaireDossiers gestionnaireDossiers) {
        super(gestionnaireDossiers);
    }

    @Override
    protected List<String> dossiersPotentiels() {
        // todo
        return null;
    }
}
