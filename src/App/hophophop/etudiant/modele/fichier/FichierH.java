//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.fichier;

import hophophop.etudiant.modele.projet.Projet;
import java.io.File;
import javax.swing.text.AttributeSet;

public class FichierH extends Fichier {
    public FichierH(Projet projet, File fichier) {
        super(projet, fichier);
        this.setSyntaxStyle("text/c");
        this.setCommentaire("//");
    }

    public FichierH(Projet projet, String nom, boolean vide) {
        super(projet, nom);
        this.setSyntaxStyle("text/c");
        this.setCommentaire("//");
        if (!vide) {
            String temp = nom.replace(".h", "").toUpperCase();
            String texte = "";
            texte = texte + "#ifndef " + temp + "\n";
            texte = texte + "#define " + temp + "\n\n";
            texte = texte + "#endif\n";

            try {
                this.getSource().insertString(0, texte, (AttributeSet)null);
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        }

    }

    public FichierH(Projet projet, String nom, String contenu) {
        super(projet, nom, contenu);
        this.setSyntaxStyle("text/c");
        this.setCommentaire("//");
    }

    public String getType() {
        return "H";
    }
}
