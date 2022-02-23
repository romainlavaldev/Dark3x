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

public class ProjetEv3 extends Projet {
    private String commandeCompilation;
    private static String repertoireDistant = "testEvo3/";

    public String getCommandeCompilation() {
        return this.commandeCompilation;
    }

    public void setCommandeCompilation(String commandeCompilation) {
        this.commandeCompilation = commandeCompilation;
    }

    public ProjetEv3(String nom, boolean compilationProjet) {
        super(nom, compilationProjet);
        this.exprReguliereTypeFichier = "([^.]*[.][cChH]$)|(^[Mm]akefile$)";
        this.commandeCompilation = "gcc ";
    }

    public int compilerUnFichier(boolean manuelle, Fichier fichierVisible) {
        this.enregistrer();
        return this.compileCommande(manuelle, fichierVisible);
    }

    public int compileCommande(boolean manuelle, Fichier fichierVisible) {
        H3Etudiant.getLogger().info("Compilation : " + fichierVisible.getNom());
        this.creerRepertoire(manuelle, repertoireDistant);
        this.effacer(manuelle, "");
        boolean resultat = this.compiler(manuelle, fichierVisible);
        if (resultat) {
            this.envoyerExecutable(false, fichierVisible);
            return 0;
        } else {
            return 1;
        }
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
        return "Ev3";
    }

    public int compiler(boolean dialogue) {
        return 0;
    }

    public void executer(String main) {
        String executable = main.replace(".c", "");
        super.executer("ssh " + H3Etudiant.getOu() + " ''" + repertoireDistant + executable + "''");
    }

    public boolean creerRepertoire(boolean manuelle, String repertoire) {
        return this.commande(manuelle, "ssh " + H3Etudiant.getOu() + " ''mkdir " + repertoire + "''", false);
    }

    public boolean effacer(boolean manuelle, String nomFichier) {
        return this.commande(manuelle, "ssh " + H3Etudiant.getOu() + " ''rm " + repertoireDistant + nomFichier + "*''", false);
    }

    public boolean envoyer(boolean manuelle, Fichier fichierVisible) {
        return this.commande(manuelle, "scp " + fichierVisible.getFichierSource() + " " + H3Etudiant.getOu() + ":" + repertoireDistant + fichierVisible.getNom(), false);
    }

    public boolean envoyerExecutable(boolean manuelle, Fichier fichierVisible) {
        String chemin = (new File(fichierVisible.getProjet().getRepertoire())).getAbsolutePath() + "/";
        String scp = "scp " + chemin + fichierVisible.getNom().replaceAll(".c", "") + " " + H3Etudiant.getOu() + ":" + repertoireDistant + fichierVisible.getNom().replaceAll(".c", "");
        return this.commande(manuelle, scp, false);
    }

    public boolean compiler(boolean manuelle, Fichier fichierVisible) {
        String chemin = (new File(fichierVisible.getProjet().getRepertoire())).getAbsolutePath();
        String comm = "/usr/local/bin/docker run --rm  -i -v " + chemin + ":/src -w /src evo3cc_hop3x gcc -lev3dev-c -o " + fichierVisible.getNom().replace(".c", "") + " " + fichierVisible.getNom();
        H3Etudiant.getLogger().info("Compilation : " + comm);
        return this.commande(manuelle, comm, true);
    }

    public boolean commande(boolean manuelle, String commande, boolean trace) {
        String sortieErreur = "";
        String sortieStandard = "";
        long temps = System.currentTimeMillis();

        try {
            Process processus = Runtime.getRuntime().exec(commande);
            BufferedReader bufferSortie = new BufferedReader(new InputStreamReader(processus.getInputStream()));
            BufferedReader bufferErreur = new BufferedReader(new InputStreamReader(processus.getErrorStream()));
            processus.waitFor();
            temps = System.currentTimeMillis() - temps;

            String ligne;
            for(sortieStandard = "Temps d'éxécution de la commande :" + temps + "\n"; bufferSortie.ready() && (ligne = bufferSortie.readLine()) != null; sortieStandard = sortieStandard + ligne + "\n") {
            }

            while(bufferErreur.ready() && (ligne = bufferErreur.readLine()) != null) {
                sortieErreur = sortieErreur + ligne + "\n";
            }
        } catch (IOException var12) {
            var12.printStackTrace();
        } catch (InterruptedException var13) {
            var13.printStackTrace();
        }

        boolean resultat = sortieErreur.length() == 0;
        if (trace) {
            Evenement evenement;
            if (manuelle) {
                evenement = Evenement.getEvenementCompilationManuelle(this.getNom(), sortieStandard + sortieErreur);
            } else {
                evenement = Evenement.getEvenementCompilationAutomatique(this.getNom(), sortieStandard + sortieErreur);
            }

            H3Etudiant.getTraceur().envoyer(evenement);
            Iterator var16 = this.getObservateurs().iterator();

            while(var16.hasNext()) {
                ObservateurProjet observateur = (ObservateurProjet)var16.next();
                observateur.compilationProjet(this, sortieStandard + sortieErreur, this.getListeErreur(), this.getEnsembleFichierAvecErreur(), true);
            }
        }

        return resultat;
    }
}
