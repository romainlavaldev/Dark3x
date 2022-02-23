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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.text.BadLocationException;

public class ProjetScala extends Projet {
    private String commandeCompilation;

    public String getCommandeCompilation() {
        return this.commandeCompilation;
    }

    public void setCommandeCompilation(String commandeCompilation) {
        this.commandeCompilation = commandeCompilation;
    }

    public ProjetScala(String nom, boolean compilationProjet) {
        super(nom, compilationProjet);
        this.exprReguliereTypeFichier = "([^.]*[.][scala]$)";
        if (System.getProperty("os.name").indexOf("Windows") != -1) {
            this.commandeCompilation = "scalac ";
        } else if (System.getProperty("os.name").indexOf("Mac") != -1) {
            this.commandeCompilation = "/usr/local/bin/scalac ";
        } else if (System.getProperty("os.name").indexOf("Linux") != -1) {
            this.commandeCompilation = "scalac ";
        }

    }

    public int compilerUnFichier(boolean manuelle, Fichier fichierVisible) {
        this.enregistrer();
        String rep = H3Etudiant.getFenetre().getExplorateur().getProjet().getRepertoire();
        H3Etudiant.getLogger().info("Compilation Fichier = " + rep);
        String commande = this.commandeCompilation + "-d " + rep + " " + fichierVisible.getFichierSource();
        return this.compileCommande(manuelle, commande);
    }

    public int compiler(boolean manuelle) {
        this.enregistrer();
        String commande = null;
        String rep = H3Etudiant.getFenetre().getExplorateur().getProjet().getRepertoire();
        H3Etudiant.getLogger().info("Compilation Projet = " + rep);
        int result = 0;

        for(Iterator var5 = this.getFichiers().iterator(); var5.hasNext(); result += this.compileCommande(manuelle, commande)) {
            Fichier f = (Fichier)var5.next();
            commande = this.commandeCompilation + "-d " + rep + " " + f.getFichierSource();
        }

        return result;
    }

    public int compileCommande(boolean manuelle, String commande) {
        int valeurDeSortie = 0;
        String sortie = "";
        H3Etudiant.getLogger().info("Compilation SCALA: " + commande);

        try {
            Process processus = Runtime.getRuntime().exec(commande);
            BufferedReader bufferSortie = new BufferedReader(new InputStreamReader(processus.getInputStream()));
            BufferedReader bufferErreur = new BufferedReader(new InputStreamReader(processus.getErrorStream()));

            try {
                valeurDeSortie = processus.waitFor();
            } catch (InterruptedException var9) {
                var9.printStackTrace();
            }

            String ligne;
            while(bufferSortie.ready() && (ligne = bufferSortie.readLine()) != null) {
                sortie = sortie + ligne + "\n";
            }

            while(bufferErreur.ready() && (ligne = bufferErreur.readLine()) != null) {
                sortie = sortie + ligne + "\n";
            }
        } catch (IOException var10) {
            var10.printStackTrace();
        }

        Evenement evenement;
        if (manuelle) {
            evenement = Evenement.getEvenementCompilationManuelle(this.getNom(), sortie);
        } else {
            evenement = Evenement.getEvenementCompilationAutomatique(this.getNom(), sortie);
        }

        H3Etudiant.getTraceur().envoyer(evenement);
        Iterator var12 = this.getObservateurs().iterator();

        while(var12.hasNext()) {
            ObservateurProjet observateur = (ObservateurProjet)var12.next();
            observateur.compilationProjet(this, sortie, this.getListeErreur(), this.getEnsembleFichierAvecErreur(), true);
        }

        return valeurDeSortie;
    }

    public void executer(String main) {
        String commande = "/usr/local/bin/scala -classpath " + this.getRepertoire() + " " + main.replace(".scala", "");
        super.executer(commande);
    }

    public List<String> getFichiersMain() {
        Pattern pattern = Pattern.compile(".*^(.*\\s)?main\\s?\\(.*\\).*\\{.*\\}.*|^.*?extends App.*$", 40);
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
        return "Scala";
    }
}
