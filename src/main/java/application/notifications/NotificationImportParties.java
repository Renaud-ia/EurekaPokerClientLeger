package application.notifications;

import domain.core.valeurs.IdentifiantPartie;
import domain.core.valeurs.MainPoker;
import domain.core.valeurs.Partie;

import java.util.List;

public interface NotificationImportParties {
    void partieAjoutee(Partie partie, List<MainPoker> mainPokers);
    void ErreurImport(IdentifiantPartie identifiantPartie);
}
