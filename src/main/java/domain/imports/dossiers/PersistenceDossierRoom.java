package domain.imports.dossiers;

import domain.core.valeurs.Room;

import java.util.List;

public interface PersistenceDossierRoom {
    List<String> obtListDossiers();
    void ajouterDossier(String nomDossier);
    void supprimerDossier(String nomDossier);
}
