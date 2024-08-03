package domain.imports.dossiers;

import domain.imports.services.AutoDetectionService;

import java.util.ArrayList;
import java.util.List;

public class AutoDetectionWinamax extends AutoDetectionService {
    public AutoDetectionWinamax(GestionnaireDossiers gestionnaireDossiers) {
        super(gestionnaireDossiers);
    }

    @Override
    protected List<String> dossiersPotentiels() {
        List<String> dossiersDetection = new ArrayList<>();

        dossiersDetection.add("C:\\Program Files (x86)\\Xeester\\processed\\Winamax");

        String userHome = System.getProperty("user.home");

        String dossierBaseMesDocuments = userHome + "\\Mes Documents\\Winamax Poker\\accounts";
        dossiersDetection.addAll(trouverDossiersHistoriquesParUser(dossierBaseMesDocuments, "history"));

        String dossierBaseAppDataLocal = userHome + "\\AppData\\Local\\Winamax Poker\\accounts";
        dossiersDetection.addAll(trouverDossiersHistoriquesParUser(dossierBaseAppDataLocal, "history"));

        String dossierBaseAppDataRoaming = userHome + "\\AppData\\Roaming\\winamax\\documents\\accounts";
        dossiersDetection.addAll(trouverDossiersHistoriquesParUser(dossierBaseAppDataRoaming, "history"));

        String dossierPT4 = userHome + "\\AppData\\Local\\PokerTracker 4\\Processed\\Winamax";
        dossiersDetection.add(dossierPT4);

        return dossiersDetection;
    }
}
