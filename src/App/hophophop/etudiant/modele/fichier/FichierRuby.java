//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.fichier;

import hophophop.etudiant.modele.projet.Projet;
import hophophop.etudiant.modele.projet.ProjetRuby;
import java.io.File;
import java.util.Date;
import javax.swing.text.AttributeSet;

public class FichierRuby extends Fichier {
    public FichierRuby(Projet projet, File fichier) {
        super(projet, fichier);
        this.setSyntaxStyle("text/ruby");
        this.setCommentaire("#");
    }

    public FichierRuby(ProjetRuby projet, String nom) {
        this(projet, nom, false);
    }

    public FichierRuby(Projet projet, String nom, boolean vide) {
        super(projet, nom);
        this.setSyntaxStyle("text/ruby");
        this.setCommentaire("#");
        if (!vide) {
            Date laDate = new Date();
            String texte = "##\n# Auteur LeNomDeLEtudiant\n# Version 0.1 : Date : " + laDate + "\n#\n\n";
            texte = texte + "class " + this.getNomClasse() + "\n\n";
            texte = texte + "\t# Création des objets de la classe " + this.getNomClasse() + "\n\t#\n";
            texte = texte + "\t#\t A) S'il y a des paramètres à fournir à la création\n";
            texte = texte + "\t#\t\t - rendre new privée\n";
            texte = texte + "\t#\t\t - Ecrire une Méthode de classe de création d'instance avec un nom significatif\n";
            texte = texte + "\t#\t\t - Ecrire la Méthode d'instance d'initialisation\n\t#\n";
            texte = texte + "\t#\t B) S'il n'y a pas de paramètre à fournir à la création\n";
            texte = texte + "\t#\t\t - Ecrire SI BESOIN la Méthode d'instance d'initialisation\n\n";
            texte = texte + "end # Marqueur de fin de classe\n";

            try {
                this.getSource().insertString(0, texte, (AttributeSet)null);
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        }

    }

    public FichierRuby(Projet projet, String nom, String contenu) {
        super(projet, nom, contenu);
        this.setSyntaxStyle("text/ruby");
        this.setCommentaire("#");
    }

    public String getNomClasse() {
        return this.getNom().replace(".rb", "");
    }

    public String getType() {
        return "Ruby";
    }
}
