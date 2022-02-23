//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.fichier;

import hophophop.etudiant.modele.projet.Projet;
import hophophop.etudiant.modele.projet.ProjetScala;
import java.io.File;
import java.util.Date;
import javax.swing.text.AttributeSet;

public class FichierScala extends Fichier {
    public FichierScala(Projet projet, File fichier) {
        super(projet, fichier);
        this.setSyntaxStyle("text/scala");
        this.setCommentaire("//");
    }

    public FichierScala(ProjetScala projet, String nom) {
        this(projet, nom, false);
    }

    public FichierScala(Projet projet, String nom, boolean vide) {
        super(projet, nom);
        this.setSyntaxStyle("text/scala");
        this.setCommentaire("//");
        if (!vide) {
            Date laDate = new Date();
            String texte = "/**\n* @author LeNomDeLEtudiant\n* @version 0.1 : Date : " + laDate + "\n*\n*/\n";
            texte = texte + "object " + this.getNomClasse() + " extends App {\n\tprintln(\"Super Hop3x!\")\n}";

            try {
                this.getSource().insertString(0, texte, (AttributeSet)null);
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        }

    }

    public FichierScala(Projet projet, String nom, String contenu) {
        super(projet, nom, contenu);
        this.setSyntaxStyle("text/scala");
        this.setCommentaire("//");
    }

    public String getNomClasse() {
        return this.getNom().replace(".scala", "");
    }

    public String getType() {
        return "Scala";
    }
}
