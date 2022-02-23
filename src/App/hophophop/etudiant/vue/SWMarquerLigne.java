//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue;

import hophophop.etudiant.modele.projet.Erreur;
import java.awt.Color;
import javax.swing.SwingWorker;

public class SWMarquerLigne extends SwingWorker<Integer, String> {
    private Editeur editeur;
    private Erreur erreur;
    private Color color;

    public SWMarquerLigne(Editeur ed, Erreur er) {
        this.color = ed.getPageActuelle().getrSyntaxCodeSource().getCurrentLineHighlightColor();
        this.erreur = er;
        this.editeur = ed;
    }

    public Integer doInBackground() {
        this.editeur.marquerLaLigne(this.erreur);
        return 0;
    }

    protected void done() {
        this.editeur.getPageActuelle().getrSyntaxCodeSource().setCurrentLineHighlightColor(this.color);
    }
}
