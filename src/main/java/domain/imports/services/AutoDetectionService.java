package domain.imports.services;

import domain.core.valeurs.Room;
import domain.imports.dossiers.GestionnaireDossiers;

import java.io.File;
import java.util.ArrayList;
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

    protected List<String> trouverDossiersHistoriquesParUser(String nomDossier, String nomSousDossier) {
        File dossier = new File(nomDossier);
        List<String> dossiersAvecHistorique = new ArrayList<>();

        // Vérifier que le dossier existe et est un répertoire
        if (dossier.exists() && dossier.isDirectory()) {
            File[] sousDossiers = dossier.listFiles();

            if (sousDossiers == null) return dossiersAvecHistorique;

            for (File sousDossier : sousDossiers) {
                if (sousDossier.isDirectory()) {
                    // Vérifier l'existence du sous-dossier spécifié
                    File dossierPotentielHistorique = new File(sousDossier, nomSousDossier);
                    if (dossierPotentielHistorique.exists() && dossierPotentielHistorique.isDirectory()) {
                        dossiersAvecHistorique.add(sousDossier.getAbsolutePath());
                    }
                }
            }
        }

        return dossiersAvecHistorique;
    }
}
