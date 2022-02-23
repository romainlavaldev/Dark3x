//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.fichier;

import hophophop.etudiant.modele.projet.Projet;
import hophophop.etudiant.modele.projet.ProjetJava;
import java.io.File;
import java.util.Date;
import javax.swing.text.AttributeSet;

public class FichierJava extends Fichier {
    public FichierJava(Projet projet, File fichier) {
        super(projet, fichier);
        this.setSyntaxStyle("text/java");
        this.setCommentaire("//");
    }

    public FichierJava(ProjetJava projet, String nom) {
        this(projet, nom, false, false);
    }

    public FichierJava(Projet projet, String nom, boolean vide, boolean interfac) {
        super(projet, nom);
        this.setSyntaxStyle("text/java");
        this.setCommentaire("//");
        if (!vide) {
            Date laDate = new Date();
            String texte = "/**\n* @author LeNomDeLEtudiant\n* @version 0.1 : Date : " + laDate + "\n*\n*/\n";
            if (interfac) {
                texte = texte + "public interface " + this.getNomClasse() + " {\n\t\n";
            } else {
                texte = texte + "public class " + this.getNomClasse() + " {\n\t\n";
            }

            texte = texte + "}";

            try {
                this.getSource().insertString(0, texte, (AttributeSet)null);
            } catch (Exception var8) {
                var8.printStackTrace();
            }
        }

    }

    public FichierJava(Projet projet, String nom, String contenu) {
        super(projet, nom, contenu);
        this.setSyntaxStyle("text/java");
        this.setCommentaire("//");
    }

    public String getNomClasse() {
        return this.getNom().replace(".java", "");
    }

    public String getType() {
        return "Java";
    }
}
