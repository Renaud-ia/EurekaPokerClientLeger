package domain.imports.services;

import domain.core.valeurs.Room;
import domain.imports.dossiers.GestionnaireDossiers;

import java.util.List;

public abstract class AutoDetectionService {
    private final GestionnaireDossiers gestionnaireDossiers;
    public AutoDetectionService(GestionnaireDossiers gestionnaireDossiers) {
        this.gestionnaireDossiers = gestionnaireDossiers;
    }

    public int lancer() {
        int dossiersAjoutes = 0;

        for (String nomDossier: this.dossiersPotentiels()) {
            if (gestionnaireDossiers.ajouterDossier(nomDossier) == null) {
                dossiersAjoutes++;
            }
        }

        return dossiersAjoutes;
    }

    /**
     * chaque implémentation concrète renvoie la liste des dossiers potentiels
     */
    protected abstract List<String> dossiersPotentiels();
}
