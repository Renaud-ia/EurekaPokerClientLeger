package infrastructure;

import domain.core.valeurs.IdentifiantPartie;
import domain.core.valeurs.MainPoker;
import domain.core.valeurs.Partie;
import domain.imports.enregistrement.PersistenceImportParties;

import java.util.ArrayList;
import java.util.List;

public class PersistancePartiesStub implements PersistenceImportParties {
    @Override
    public List<MainPoker> initialiserPartie(IdentifiantPartie identifiantPartie) {
        return new ArrayList<>();
    }

    @Override
    public void sauvegarderPartie(Partie partie, List<MainPoker> mainsPartie) {
        System.out.println("Partie sauvegardée: " + partie);
    }
}
