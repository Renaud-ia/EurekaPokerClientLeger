package application.controleurs;

import domain.imports.services.ImportFichierService;

import java.util.List;

public interface ControleurImportRoom {
    void ajouterDossier(String cheminDossier);
    void autoDetection();
    List<ImportFichierService> obtFichiersImportables();
}
