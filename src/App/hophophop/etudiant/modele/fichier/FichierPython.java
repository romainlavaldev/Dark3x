//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.fichier;

import hophophop.etudiant.modele.projet.Projet;
import java.io.File;
import java.util.Date;
import javax.swing.text.AttributeSet;

public class FichierPython extends Fichier {
    public FichierPython(Projet projet, File fichier) {
        super(projet, fichier);
        this.setSyntaxStyle("text/python");
        this.setCommentaire("#");
    }

    public FichierPython(Projet projet, String nom, boolean vide) {
        super(projet, nom);
        this.setSyntaxStyle("text/python");
        this.setCommentaire("#");
        if (!vide) {
            Date laDate = new Date();
            String texte = "#\n# Auteur LeNomDeLEtudinat\n# Version 0.1 : Date : " + laDate + "\n#\n\n";

            try {
                this.getSource().insertString(0, texte, (AttributeSet)null);
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        }

    }

    public FichierPython(Projet projet, String nom, String contenu) {
        super(projet, nom, contenu);
        this.setSyntaxStyle("text/python");
        this.setCommentaire("#");
    }

    public String getNomClasse() {
        return this.getNom().replace(".py", "");
    }

    public String getType() {
        return "Python";
    }
}
