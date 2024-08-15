package domain.imports.parsing;

import domain.imports.services.ImportFichierService;

public class ImportFichierWinamax extends ImportFichierService {
    public ImportFichierWinamax() {
        super(new LecteurFichierWinamax());
    }

    @Override
    protected void lireFichier() {
        // todo
    }
}
