package domain.imports.gestionnaires.winamax;

import domain.imports.dossiers.GestionnaireDossiers;
import domain.imports.gestionnaires.NomFichierValide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class NomFichierValideWinamaxTest {
    private NomFichierValideWinamax nomFichierValideWinamax;
    @BeforeEach
    public void setUp() {
        nomFichierValideWinamax = new NomFichierValideWinamax();
    }

    @Test
    public void nomFichierValideMiniRoll() {
        String nomFichier = "20190507_MiniRoll(277342139)_real_holdem_no-limit.txt";

        assertTrue(nomFichierValideWinamax.fichierValide(nomFichier));
    }

    @Test
    public void nomFichierDeepStack() {
        String nomFichier = "20190512_Deepstack Hold'em(277897332)_real_holdem_no-limit.txt";

        assertTrue(nomFichierValideWinamax.fichierValide(nomFichier));
    }

    @Test
    public void nomFichierValideExpresso() {
        String nomFichier = "20190807_Expresso(290770141)_real_holdem_no-limit.txt";

        assertTrue(nomFichierValideWinamax.fichierValide(nomFichier));
    }

    @Test
    public void nomFichierInvalideSummary() {
        String nomFichier = "20190807_Expresso(290770141)_real_holdem_no-limit_summary.txt";

        assertFalse(nomFichierValideWinamax.fichierValide(nomFichier));
    }

    @Test
    public void nomFichierValideXimport() {
        String nomFichier = ".ximport";

        assertFalse(nomFichierValideWinamax.fichierValide(nomFichier));
    }

    @Test
    public void nomFichierValideBetclic() {
        String nomFichier = "5420425863.xml";

        assertFalse(nomFichierValideWinamax.fichierValide(nomFichier));
    }

    @Test
    public void nomFichierValidePartyPoker() {
        String nomFichier = "2€ SPINS (355137448) Table #1.txt";

        assertFalse(nomFichierValideWinamax.fichierValide(nomFichier));
    }
}
