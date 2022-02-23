//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.projet;

import hophophop.commun.modele.Evenement;
import hophophop.etudiant.modele.H3Etudiant;
import hophophop.etudiant.modele.fichier.Fichier;
import hophophop.etudiant.modele.observateurs.ObservateurProjet;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.text.BadLocationException;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.DefaultCompletionProvider;

public class ProjetSpiC extends Projet {
    private String commandeCompilation;

    public String getCommandeCompilation() {
        return this.commandeCompilation;
    }

    public void setCommandeCompilation(String commandeCompilation) {
        this.commandeCompilation = commandeCompilation;
    }

    public ProjetSpiC(String nom, boolean compilationProjet) {
        super(nom, compilationProjet);
        this.exprReguliereTypeFichier = "([^.]*[.]c$)";
        this.commandeCompilation = "gcc ";
    }

    public int compilerUnFichier(boolean manuelle, Fichier fichierVisible) {
        this.enregistrer();
        String executable = fichierVisible.getFichierSource().replace(".c", "");
        File MyFile = new File(executable);
        MyFile.delete();
        String commande = this.commandeCompilation + " -o " + executable + " " + fichierVisible.getFichierSource();
        return this.compileCommande(manuelle, commande);
    }

    public int compiler(boolean manuelle) {
        return 0;
    }

    public int compileCommande(boolean manuelle, String commande) {
        H3Etudiant.getLogger().info("Compilation : " + commande);
        String sortieErreur = "";
        String sortieStandard = "";

        try {
            Process processus = Runtime.getRuntime().exec(commande);
            BufferedReader bufferSortie = new BufferedReader(new InputStreamReader(processus.getInputStream()));
            BufferedReader bufferErreur = new BufferedReader(new InputStreamReader(processus.getErrorStream()));
            boolean stop = false;

            while(!stop) {
                try {
                    processus.exitValue();
                    stop = true;
                } catch (Exception var10) {
                }
            }

            String ligne;
            while(bufferSortie.ready() && (ligne = bufferSortie.readLine()) != null) {
                sortieStandard = sortieStandard + ligne + "\n";
            }

            while(bufferErreur.ready() && (ligne = bufferErreur.readLine()) != null) {
                sortieErreur = sortieErreur + ligne + "\n";
            }
        } catch (IOException var11) {
            var11.printStackTrace();
        }

        Evenement evenement;
        if (manuelle) {
            evenement = Evenement.getEvenementCompilationManuelle(this.getNom(), sortieStandard + sortieErreur);
        } else {
            evenement = Evenement.getEvenementCompilationAutomatique(this.getNom(), sortieStandard + sortieErreur);
        }

        H3Etudiant.getTraceur().envoyer(evenement);
        Iterator var13 = this.getObservateurs().iterator();

        while(var13.hasNext()) {
            ObservateurProjet observateur = (ObservateurProjet)var13.next();
            observateur.compilationProjet(this, sortieStandard + sortieErreur, this.getListeErreur(), this.getEnsembleFichierAvecErreur(), true);
        }

        return sortieErreur.length();
    }

    public void executer(String main) {
        String commande = this.getRepertoire() + main.replace(".c", "");
        super.executer(commande);
    }

    public List<String> getFichiersMain() {
        Pattern pattern = Pattern.compile(".*^(.*\\s)?main\\s?\\(.*\\).*\\{.*\\}.*", 40);
        List<String> mains = new ArrayList();
        Iterator var3 = this.getFichiers().iterator();

        while(var3.hasNext()) {
            Fichier fichier = (Fichier)var3.next();

            try {
                String code = fichier.getSource().getText(0, fichier.getSource().getLength());
                if (pattern.matcher(code).matches()) {
                    mains.add(fichier.getNom());
                }
            } catch (BadLocationException var6) {
                var6.printStackTrace();
            }
        }

        return mains;
    }

    public String getType() {
        return "SpiC";
    }

    public void initialiseCompletionProvider() {
        DefaultCompletionProvider provider = H3Etudiant.getProvider();
        provider.clear();
        provider.addCompletion(new BasicCompletion(provider, "AfficherFloat("));
        provider.addCompletion(new BasicCompletion(provider, "AfficherInt("));
        provider.addCompletion(new BasicCompletion(provider, "AfficherTexte("));
        provider.addCompletion(new BasicCompletion(provider, "SaisirInt();"));
        provider.addCompletion(new BasicCompletion(provider, "SaisirFloat();"));
        provider.addCompletion(new BasicCompletion(provider, "ALaLigne();"));
    }
}
