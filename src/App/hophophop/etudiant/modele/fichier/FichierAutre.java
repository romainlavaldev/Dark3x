//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.fichier;

import hophophop.etudiant.modele.projet.Projet;
import java.io.File;

public class FichierAutre extends Fichier {
    public FichierAutre(Projet projet, File fichier) {
        super(projet, fichier);
        this.setSyntaxStyle("text/plain");
        this.setCommentaire("");
    }

    public FichierAutre(Projet projet, String nom) {
        super(projet, nom);
        this.setSyntaxStyle("text/plain");
        this.setCommentaire("");
    }

    public FichierAutre(Projet p, String nom, String contenu) {
        super(p, nom, contenu);
        this.setSyntaxStyle("text/plain");
        this.setCommentaire("");
    }

    public String getType() {
        return "Autre";
    }
}
