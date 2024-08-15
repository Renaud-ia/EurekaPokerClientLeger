package infrastructure;

import domain.imports.dossiers.PersistenceDossierRoom;

import java.util.ArrayList;
import java.util.List;

public class PersistanceDossierRoomFichierJson implements PersistenceDossierRoom {
    @Override
    public List<String> obtListDossiers() {
        return new ArrayList<>();
    }

    @Override
    public void ajouterDossier(String nomDossier) {

    }

    @Override
    public void supprimerDossier(String nomDossier) {

    }
}
