package domain.imports.enregistrement;

import domain.core.valeurs.IdentifiantPartie;
import domain.core.valeurs.MainPoker;
import domain.core.valeurs.Partie;

import java.util.List;

public interface ObservateurImportParties {
    void partieAjoutee(Partie partie, List<MainPoker> mainPokers);
    void ErreurImport(IdentifiantPartie identifiantPartie);
}
