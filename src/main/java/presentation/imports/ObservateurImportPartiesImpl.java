package presentation.imports;

import domain.core.valeurs.IdentifiantPartie;
import domain.core.valeurs.MainPoker;
import domain.core.valeurs.Partie;
import domain.imports.enregistrement.ObservateurImportParties;

import java.util.List;

public class ObservateurImportPartiesImpl implements ObservateurImportParties {
    @Override
    public void partieAjoutee(Partie partie, List<MainPoker> mainPokers) {

    }

    @Override
    public void ErreurImport(IdentifiantPartie identifiantPartie) {

    }
}
