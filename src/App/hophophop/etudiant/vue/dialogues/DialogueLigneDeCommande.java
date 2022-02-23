//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue.dialogues;

import hophophop.commun.vue.Icones;
import hophophop.commun.vue.dialogues.Dialogue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogueLigneDeCommande extends Dialogue {
    private JButton boutonOk;
    private JTextField champParametres;

    public DialogueLigneDeCommande(JFrame fenetre, String valeur) {
        super(fenetre, "Paramètres de la ligne de commande");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        this.setPanel(panel);
        panel.add(new JLabel("Paramètres :"));
        panel.add(Box.createVerticalStrut(5));
        this.champParametres = new JTextField();
        this.champParametres.setColumns(20);
        this.champParametres.setText(valeur);
        this.champParametres.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                DialogueLigneDeCommande.this.boutonOk.doClick();
            }
        });
        panel.add(this.champParametres);
        panel.add(Box.createVerticalStrut(10));
        JButton boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.setIcon(Icones.ANNULER_16);
        boutonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueLigneDeCommande.this.dialogue.setVisible(false);
            }
        });
        this.ajouterBouton(boutonAnnuler);
        this.boutonOk = new JButton("Valider");
        this.boutonOk.setIcon(Icones.OK_16);
        this.boutonOk.setEnabled(true);
        this.boutonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueLigneDeCommande.this.resultat = 2;
                DialogueLigneDeCommande.this.dialogue.setVisible(false);
            }
        });
        this.ajouterBouton(this.boutonOk);
    }

    public String getChampParametres() {
        String retour = "";
        String depart = this.champParametres.getText();
        return depart == null ? "" : depart;
    }
}
