package domain.imports.gestionnaires.winamax;

import domain.imports.gestionnaires.NomFichierValide;

public class NomFichierValideWinamax implements NomFichierValide {
    @Override
    public boolean fichierValide(String nomFichier) {
        // on prend les summary comme non valides → sinon ils seront comptés par le gestionnaire
        return nomFichier.matches("^[0-9]{8}_.+real_holdem_no-limit\\.txt$");
    }
}
