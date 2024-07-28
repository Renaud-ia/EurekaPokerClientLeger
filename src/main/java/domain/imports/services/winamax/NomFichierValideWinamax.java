package domain.imports.services.winamax;

import domain.imports.services.NomFichierValide;

public class NomFichierValideWinamax extends NomFichierValide {
    @Override
    public boolean fichierValide(String nomFichier) {
        return false;
    }
}
