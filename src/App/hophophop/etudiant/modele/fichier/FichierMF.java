//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.fichier;

import hophophop.etudiant.modele.projet.Projet;
import java.io.File;
import javax.swing.text.AttributeSet;

public class FichierMF extends Fichier {
    public FichierMF(Projet projet, File fichier) {
        super(projet, fichier);
        this.setSyntaxStyle("text/makefile");
        this.setCommentaire("#");
    }

    public FichierMF(Projet projet, String nom, boolean vide) {
        super(projet, nom);
        this.setSyntaxStyle("text/makefile");
        this.setCommentaire("#");
        if (!vide) {
            String texte = projet.getNom() + ":\t";

            try {
                this.getSource().insertString(0, texte, (AttributeSet)null);
            } catch (Exception var6) {
                var6.printStackTrace();
            }
        }

    }

    public FichierMF(Projet projet, String nom, String contenu) {
        super(projet, nom, contenu);
        this.setSyntaxStyle("text/makefile");
        this.setCommentaire("#");
    }

    public String getType() {
        return "Makefile";
    }
}
