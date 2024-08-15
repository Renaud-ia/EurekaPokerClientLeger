package domain.imports.services;

import domain.imports.enregistrement.GestionnaireParties;
import domain.imports.parsing.LecteurFichier;

import java.nio.file.Path;

public abstract class ImportFichierService {
    private LecteurFichier lecteurFichier;
    protected Path cheminFichier;
    protected GestionnaireParties gestionnaireParties;

    public ImportFichierService(LecteurFichier lecteurFichier) {
        this.lecteurFichier = lecteurFichier;
    }

    public void importer() {
        // todo supprimer
        System.out.println("Fichier en cours d'import: " + cheminFichier);
        lireFichier();
        sauvegarderPartie();
    }

    protected abstract void lireFichier();

    protected void sauvegarderPartie() {
        // todo
    }

    public void ajouterChemin(Path path) {
        this.cheminFichier = path;
    }

    public void ajouterGestionnaireParties(GestionnaireParties gestionnaireStats) {
        this.gestionnaireParties = gestionnaireStats;
    }

    protected boolean estInitialise() {
        return (cheminFichier != null && gestionnaireParties != null);
    }
}
