//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue;

import hophophop.etudiant.modele.fichier.Fichier;
import hophophop.etudiant.modele.observateurs.ObservateurProjet;
import hophophop.etudiant.modele.projet.Erreur;
import hophophop.etudiant.modele.projet.Projet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants.CharacterConstants;

public class Console extends JTextPane implements ObservateurProjet {
    static SimpleAttributeSet attrGras = new SimpleAttributeSet();
    private Editeur lediteur;

    public Console(Editeur editeur) {
        this.lediteur = editeur;
        this.setEditable(false);
        this.efface();
    }

    public void efface() {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:MM");
        this.setText(format.format(new Date()) + "\n");
    }

    public void ecrire(String texte) {
        try {
            if (texte.equals("")) {
                texte = texte + "\n";
            }

            int debut = this.getDocument().getEndPosition().getOffset() - 1;
            this.getDocument().insertString(debut, texte, (AttributeSet)null);
            this.setCaretPosition(debut);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void ecrireGras(String texte) {
        try {
            int debut = this.getDocument().getEndPosition().getOffset() - 1;
            int fin = debut + texte.length();
            this.getDocument().insertString(debut, texte, (AttributeSet)null);
            this.getStyledDocument().setCharacterAttributes(debut, fin, attrGras, true);
            this.setCaretPosition(debut);
        } catch (BadLocationException var4) {
            var4.printStackTrace();
        }

    }

    public void ajoutFichier(Fichier fichier) {
    }

    public void compilationProjet(Projet projet, String sortie, List<Erreur> listeErreur, Set<Fichier> ensembleDesFichiersAvecErreur, boolean dialogue) {
        this.ecrireGras("\nCompilation : " + projet.getNom() + " (" + this.getTemps() + ")\n");
        if (sortie.length() == 0) {
            this.ecrire("OK\n");
        } else {
            this.ecrire(sortie + "\n");
        }

    }

    public void suppressionFichier(Fichier fichier) {
    }

    private String getTemps() {
        SimpleDateFormat format = new SimpleDateFormat("EEEEEEEE d MMMMMMMMM - HH:mm:ss", Locale.FRANCE);
        return format.format(new Date());
    }

    public void executionProjet(Projet projet, String sortie) {
        this.ecrireGras("Exécution : " + projet.getNom() + " (" + this.getTemps() + ")\n");
        this.ecrire(sortie);
    }

    public Editeur getLediteur() {
        return this.lediteur;
    }

    static {
        attrGras.addAttribute(CharacterConstants.Bold, true);
    }
}
