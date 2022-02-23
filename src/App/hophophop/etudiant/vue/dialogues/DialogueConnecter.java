//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue.dialogues;

import hophophop.commun.modele.Etudiant;
import hophophop.commun.modele.Session;
import hophophop.commun.vue.Icones;
import hophophop.commun.vue.dialogues.Dialogue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;

public class DialogueConnecter extends Dialogue {
    private List<Session> sessionsDemarrees;
    private List<Session> sessionDejaConnectees = new ArrayList();
    private JButton boutonOk;
    private Etudiant utilisateur;
    private String nomSessionSelectionnee;
    private String langageSession;
    private boolean compilationProjet;

    public boolean isCompilationProjet() {
        return this.compilationProjet;
    }

    public void setCompilationProjet(boolean comilationProjet) {
        this.compilationProjet = comilationProjet;
    }

    public DialogueConnecter(JFrame fenetre, List<String> sessions) {
        super(fenetre, "Connecter", Icones.CONNECTER_32);
    }

    public String getNomSessionSelectionnee() {
        return this.nomSessionSelectionnee;
    }

    public String getUtilisateur() {
        String nomUtil = this.utilisateur.getPrenom() + " " + this.utilisateur.getNom();
        return nomUtil;
    }

    public String getLangageSession() {
        return this.langageSession;
    }
}
