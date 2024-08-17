package presentation.imports;

import domain.core.valeurs.IdentifiantPartie;
import domain.core.valeurs.MainPoker;
import domain.core.valeurs.Partie;
import application.notifications.NotificationImportParties;

import java.util.List;

public class NotificationImportPartiesImpl implements NotificationImportParties {
    @Override
    public void partieAjoutee(Partie partie, List<MainPoker> mainPokers) {

    }

    @Override
    public void ErreurImport(IdentifiantPartie identifiantPartie) {

    }
}
