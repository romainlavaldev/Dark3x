//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.projet;

import hophophop.commun.modele.Evenement;
import hophophop.commun.vue.Icones;
import hophophop.etudiant.modele.H3Etudiant;
import hophophop.etudiant.modele.fichier.Fichier;
import hophophop.etudiant.modele.observateurs.ObservateurProjet;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

public class ProjetC extends Projet {
    private String commandeCompilation;
    private String commandeMake;
    private String cibleMake;

    public void setCibleMake(String cibleMake) {
        this.cibleMake = cibleMake;
    }

    public String getCibleMake() {
        return this.cibleMake;
    }

    public String getCommandeCompilation() {
        return this.commandeCompilation;
    }

    public void setCommandeCompilation(String commandeCompilation) {
        this.commandeCompilation = commandeCompilation;
    }

    public ProjetC(String nom, boolean compilationProjet) {
        super(nom, compilationProjet);
        this.exprReguliereTypeFichier = "([^.]*[.][cChH]$)|(^[Mm]akefile$)";
        if (System.getProperty("os.name").indexOf("Windows") != -1) {
            this.commandeCompilation = "gcc ";
            this.commandeMake = H3Etudiant.DOSSIER_OUTILS + "make -C " + this.getRepertoire() + " -f Makefile ";
            this.cibleMake = "";
        } else if (System.getProperty("os.name").indexOf("Mac") != -1) {
            this.commandeCompilation = "gcc ";
            this.commandeMake = "make -C " + this.getRepertoire() + " -f Makefile ";
            this.cibleMake = "";
        } else if (System.getProperty("os.name").indexOf("Linux") != -1) {
            this.commandeCompilation = "gcc -lm";
            this.commandeMake = "make  -C " + this.getRepertoire() + " -f Makefile ";
            this.cibleMake = "";
        }

    }

    public int compilerUnFichier(boolean manuelle, Fichier fichierVisible) {
        this.enregistrer();
        String executable = fichierVisible.getFichierSource().replace(".c", "");
        File MyFile = new File(executable);
        MyFile.delete();
        String commande = this.commandeCompilation + " -o " + executable + " " + fichierVisible.getFichierSource();
        if (System.getProperty("os.name").indexOf("Linux") != -1) {
            commande = commande + " -lm";
        }

        return this.compileCommande(manuelle, commande);
    }

    public int compiler(boolean manuelle) {
        this.enregistrer();
        String commande = null;
        String executable = this.getRepertoire() + this.getNom();
        File myFile = new File(executable);
        myFile.delete();
        File makefile = new File(this.getRepertoire() + "Makefile");
        if (!makefile.exists()) {
            JOptionPane.showMessageDialog((Component)null, "Vous devez créer un fichier Makefile", "Attention !!!", 2, Icones.INFO_64);
            return -1;
        } else {
            H3Etudiant.getLogger().info("Commande Make : " + this.commandeMake);
            H3Etudiant.getLogger().info("Cible    Make : " + this.cibleMake);
            commande = this.commandeMake + " " + this.cibleMake;
            H3Etudiant.getLogger().info("Commande Make  : " + commande);
            return this.compileCommande(manuelle, commande);
        }
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
        Pattern pattern = Pattern.compile(".*^(.*\\s)*main\\s*\\(.*\\).*\\{.*\\}.*", 40);
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
        return "C";
    }
}
