//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.projet;

import hophophop.etudiant.modele.fichier.Fichier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjetRuby extends Projet {
    private String commandeDoc;
    private String commandeVisuDoc;
    private static String commandeRuby;
    private static String commandeGlade;

    public static String getCommandeGlade() {
        return commandeGlade;
    }

    public static void setCommandeGlade(String commandeGlade) {
        ProjetRuby.commandeGlade = commandeGlade;
    }

    public String getCommandeVisuDoc() {
        return this.commandeVisuDoc;
    }

    public void setCommandeVisuDoc(String cVisuDoc) {
        this.commandeVisuDoc = cVisuDoc;
    }

    public String getCommandeDoc() {
        return this.commandeDoc;
    }

    public void setCommandeDoc(String cDoc) {
        this.commandeDoc = cDoc;
    }

    public ProjetRuby() {
    }

    public ProjetRuby(String nom, boolean compilationProjet) {
        super(nom, compilationProjet);
        this.exprReguliereTypeFichier = "([^.]*[.](rb|glade)$)";
        this.commandeDoc = "rdoc -e \"UTF-8\" " + this.getRepertoire() + " --op " + this.getRepertoire() + "doc";
        this.commandeVisuDoc = "open " + this.getRepertoire() + "doc/index.html";
        commandeGlade = "/usr/local/bin/glade ";
        commandeRuby = "ruby -KU -C";
        if (System.getProperty("os.name").indexOf("Windows") != -1) {
            this.commandeDoc = "cmd.exe /c " + this.commandeDoc.replace("\\", "/");
            this.commandeVisuDoc = "cmd.exe /c start firefox " + this.getRepertoire() + "doc/index.html";
            commandeGlade = "cmd.exe /c glade ";
        } else if (System.getProperty("os.name").indexOf("Linux") != -1) {
            this.commandeDoc = "rdoc " + this.getRepertoire() + " --op " + this.getRepertoire() + "doc";
            this.commandeVisuDoc = "firefox " + this.getRepertoire() + "doc/index.html";
            commandeGlade = "/usr/bin/glade ";
        }

    }

    public int compiler(boolean dialogue) {
        this.enregistrer();
        return 0;
    }

    public void executer(String main) {
        String commande = commandeRuby + this.getRepertoire() + " " + main;
        super.executer(commande);
    }

    public List<String> getFichiersMain() {
        List<String> mains = new ArrayList();
        Iterator var2 = this.getFichiers().iterator();

        while(var2.hasNext()) {
            Fichier fichier = (Fichier)var2.next();
            mains.add(fichier.getNom());
        }

        return mains;
    }

    public String getType() {
        return "Ruby";
    }

    public int compilerUnFichier(boolean dialogue, Fichier fichier) {
        return 0;
    }

    public static String getCommandeRuby() {
        return commandeRuby;
    }

    public static void setCommandeRuby(String resultat) {
        commandeRuby = resultat;
    }
}
