package domain.imports.enregistrement;

import domain.core.valeurs.IdentifiantPartie;
import domain.core.valeurs.MainPoker;
import domain.core.valeurs.Partie;

import java.util.List;

public interface PersistenceImportParties {
    List<MainPoker> initialiserPartie(IdentifiantPartie identifiantPartie);
    void sauvegarderPartie(Partie partie, List<MainPoker> mainsPartie);
}
