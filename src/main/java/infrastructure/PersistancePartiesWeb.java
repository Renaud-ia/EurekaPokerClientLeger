package infrastructure;

import domain.core.valeurs.IdentifiantPartie;
import domain.core.valeurs.MainPoker;
import domain.core.valeurs.Partie;
import domain.imports.enregistrement.PersistenceImportParties;

import java.util.List;

public class PersistancePartiesWeb implements PersistenceImportParties {
    @Override
    public List<MainPoker> initialiserPartie(IdentifiantPartie identifiantPartie) {
        return null;
    }

    @Override
    public void sauvegarderPartie(Partie partie, List<MainPoker> mainsPartie) {

    }
}
