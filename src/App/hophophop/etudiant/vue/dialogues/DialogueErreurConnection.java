//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue.dialogues;

import hophophop.commun.vue.Icones;
import hophophop.commun.vue.dialogues.Dialogue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DialogueErreurConnection extends Dialogue {
    private JButton boutonQuitter;
    private JButton boutonServeur;

    public DialogueErreurConnection(JFrame fenetre, String texte) {
        super(fenetre, "Erreur de Connexion", Icones.ERREUR_32);
        JPanel panel = new JPanel();
        this.setPanel(panel);
        panel.add(new JLabel(texte));
        this.boutonQuitter = new JButton("Fermer", Icones.FERMER_16);
        this.boutonQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueErreurConnection.this.resultat = 1;
                DialogueErreurConnection.this.dialogue.setVisible(false);
            }
        });
        this.ajouterBouton(this.boutonQuitter);
        this.boutonServeur = new JButton("Serveur", Icones.CONNECTER_16);
        this.boutonServeur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueErreurConnection.this.resultat = 2;
                DialogueErreurConnection.this.dialogue.setVisible(false);
            }
        });
        this.ajouterBouton(this.boutonServeur);
    }
}
