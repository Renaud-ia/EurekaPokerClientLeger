package domain.imports.dossiers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GestionnaireDossiersTests {
    private PersistenceDossierRoom persistenceDossierRoom;
    private GestionnaireDossiers gestionnaireDossiers;
    private ObservateurDossierImport observateurDossierImport;

    @BeforeEach
    public void setUp() {
        // Pour les tests, les fichiers valides sont selon leur nom
        NomFichierValide nomFichierValide = Mockito.mock(NomFichierValide.class);
        when(nomFichierValide.fichierValide(anyString())).thenAnswer((Answer<Boolean>) invocation -> {
            String arg = invocation.getArgument(0);
            Path chemin = Paths.get(arg);
            String nomFichier = chemin.getFileName().toString();
            return nomFichier.startsWith("fichier_valide");
        });

        // on mock la persistence
        persistenceDossierRoom = Mockito.mock(PersistenceDossierRoom.class);
        when(persistenceDossierRoom.obtListDossiers()).thenReturn(new ArrayList<String>());

        // on mock l'observateur
        observateurDossierImport = Mockito.mock(ObservateurDossierImport.class);

        gestionnaireDossiers = new GestionnaireDossiers(observateurDossierImport, persistenceDossierRoom, nomFichierValide);
    }

    @Test
    public void testAjouterDossier() {
        String cheminRelatif = "dossier_valide";

        String cheminAbsolu = obtenirCheminFichier(cheminRelatif);

        String resultat = gestionnaireDossiers.ajouterDossier(cheminAbsolu);

        assertNull(resultat);
        assertTrue(gestionnaireDossiers.obtListeDossiers().contains(cheminAbsolu));

        verify(persistenceDossierRoom).ajouterDossier(cheminAbsolu);
    }

    @Test
    public void supprimerDossier() {
        String cheminAbsolu = obtenirCheminFichier("dossier_valide");

        gestionnaireDossiers.ajouterDossier(cheminAbsolu);
        gestionnaireDossiers.supprimerDossier(cheminAbsolu);

        assertFalse(gestionnaireDossiers.obtListeDossiers().contains(cheminAbsolu));
        verify(persistenceDossierRoom).supprimerDossier(cheminAbsolu);
    }

    @Test
    public void refuseDoublons() {
        String cheminRelatif = "dossier_valide";

        String cheminAbsolu = obtenirCheminFichier(cheminRelatif);

        gestionnaireDossiers.ajouterDossier(cheminAbsolu);
        String resultat = gestionnaireDossiers.ajouterDossier(cheminAbsolu);

        assertNotNull(resultat);
        assertTrue(gestionnaireDossiers.obtListeDossiers().contains(cheminAbsolu));
    }

    @Test
    public void refuseDossierInvalide() {
        String cheminRelatif = "dossier_invalide";

        String cheminAbsolu = obtenirCheminFichier(cheminRelatif);

        gestionnaireDossiers.ajouterDossier(cheminAbsolu);
        String resultat = gestionnaireDossiers.ajouterDossier(cheminAbsolu);

        assertNotNull(resultat);
        assertFalse(gestionnaireDossiers.obtListeDossiers().contains(cheminAbsolu));
    }

    @Test
    public void refuseDossierEnfant() {
        String cheminParent = obtenirCheminFichier("dossier_valide");

        gestionnaireDossiers.ajouterDossier(cheminParent);

        String cheminEnfant = obtenirCheminFichier("dossier_valide/sous_dossier_valide");
        String resultat = gestionnaireDossiers.ajouterDossier(cheminEnfant);

        assertNotNull(resultat);
        assertFalse(gestionnaireDossiers.obtListeDossiers().contains(cheminEnfant));
    }

    @Test
    public void remplaceDossierEnfant() {
        String cheminEnfant = obtenirCheminFichier("dossier_valide/sous_dossier_valide");
        gestionnaireDossiers.ajouterDossier(cheminEnfant);

        String cheminParent = obtenirCheminFichier("dossier_valide");
        String resultat = gestionnaireDossiers.ajouterDossier(cheminParent);

        assertNull(resultat);

        assertTrue(gestionnaireDossiers.obtListeDossiers().contains(cheminParent));
        assertFalse(gestionnaireDossiers.obtListeDossiers().contains(cheminEnfant));

        verify(persistenceDossierRoom).ajouterDossier(cheminParent);
        verify(persistenceDossierRoom).supprimerDossier(cheminEnfant);
    }

    @Test
    public void refuseDossierInvalideMemeSiSousDossiersValides() {
        String chemin = obtenirCheminFichier("dossier_invalide_avec_sous_dossier_valide");
        String resultat = gestionnaireDossiers.ajouterDossier(chemin);

        assertNotNull(resultat);
        assertFalse(gestionnaireDossiers.obtListeDossiers().contains(chemin));
    }

    @Test
    public void accepteDossierSiAucunFichierEtSousDossiersValides() {
        String cheminAbsolu = obtenirCheminFichier("dossier_vide_avec_sous_dossiers_valides");

        String resultat = gestionnaireDossiers.ajouterDossier(cheminAbsolu);

        assertNull(resultat);
        assertTrue(gestionnaireDossiers.obtListeDossiers().contains(cheminAbsolu));
    }

    @Test
    public void refuseDossierSiAucunFichierEtUnSousDossierInvalide() {
        String cheminAbsolu = obtenirCheminFichier("dossier_vide_avec_sous_dossier_invalide");

        String resultat = gestionnaireDossiers.ajouterDossier(cheminAbsolu);

        assertNotNull(resultat);
        assertFalse(gestionnaireDossiers.obtListeDossiers().contains(cheminAbsolu));
    }

    private String obtenirCheminFichier(String nomFichier) {
        String repertoireTest = "domain/imports/dossiers/";
        Path cheminFichier = Paths.get("src", "test", "resources", repertoireTest, nomFichier);

        return cheminFichier.toFile().getAbsolutePath();
    }
}
