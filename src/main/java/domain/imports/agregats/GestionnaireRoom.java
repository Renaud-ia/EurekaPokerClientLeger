package domain.imports.agregats;

import domain.imports.agregats.entites.StatsErreurImport;
import domain.imports.agregats.entites.StatsImport;
import domain.imports.services.AutoDetectionService;
import domain.imports.services.ImportFichierService;
import domain.imports.services.NomFichierValide;

import java.util.ArrayList;
import java.util.List;

public abstract class GestionnaireRoom {
    private final NomFichierValide nomFichierValide;
    private final StatsImport statsImport;
    private final StatsErreurImport statsErreurImport;
    private final List<String> dossiersAjoutes;
    protected GestionnaireRoom(NomFichierValide nomFichierValide) {
        statsImport = new StatsImport();
        statsErreurImport = new StatsErreurImport();
        this.nomFichierValide = nomFichierValide;
        this.dossiersAjoutes = new ArrayList<>();
    }

    /**
     * @param cheminDossier chemin du dossier à ajouter
     * @return null si dossier bien ajouté, le message d'erreur sinon
     */
    public String ajouterDossier(String cheminDossier) {
        if (dossierDejaAjoute(cheminDossier)) {
            return "Ce dossier a déjà été ajouté";
        }

        if (dossierParentDejaAjoute(cheminDossier)) {
            return "Un dossier parent à déjà été ajouté";
        }





        // todo tester récursivement les dossiers parents et les ajouter s'ils sont valides
        return null;
    }

    public abstract List<ImportFichierService> fichiersImportables();

    public abstract AutoDetectionService autoDetection();

    private boolean dossierDejaAjoute(String cheminDossier) {
        return dossiersAjoutes.contains(cheminDossier);
    }

    private boolean dossierContientPartiesValides(String cheminDossier) {
        // todo
        // si le dossier contient des fichiers, on vérifie qu'ils sont valides

        // sinon on regarde si tous les dossiers enfants sont tous valides
        return true;
    }

    private boolean dossierParentDejaAjoute(String cheminDossier) {
        // todo checker si sous-dossier d'un dossier déjà existant
        return false;
    }

}
