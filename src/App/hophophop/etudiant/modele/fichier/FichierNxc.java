//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.fichier;

import hophophop.etudiant.modele.projet.Projet;
import java.io.File;
import java.util.Date;
import javax.swing.text.AttributeSet;

public class FichierNxc extends Fichier {
    public FichierNxc(Projet projet, File fichier) {
        super(projet, fichier);
        this.setSyntaxStyle("text/nxc");
        this.setCommentaire("//");
    }

    public FichierNxc(Projet projet, String nom, boolean vide) {
        super(projet, nom);
        this.setSyntaxStyle("text/nxc");
        this.setCommentaire("//");
        if (!vide) {
            Date laDate = new Date();
            String texte = "/*\n * auteur : LeNomDeLEtudiant\n * version 0.1 : Date : " + laDate + "\n *\n*/\n";

            try {
                this.getSource().insertString(0, texte, (AttributeSet)null);
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        }

    }

    public FichierNxc(Projet projet, String nom, String contenu) {
        super(projet, nom, contenu);
        this.setSyntaxStyle("text/nxc");
        this.setCommentaire("//");
    }

    public String getType() {
        return "Nxc";
    }
}
