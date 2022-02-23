//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue.dialogues;

import hophophop.commun.vue.Icones;
import hophophop.commun.vue.dialogues.Dialogue;
import hophophop.etudiant.modele.projet.Projet;
import hophophop.etudiant.modele.projet.ProjetC;
import hophophop.etudiant.modele.projet.ProjetNxc;
import hophophop.etudiant.modele.projet.ProjetSpiC;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class DialogueRenommerFichier extends Dialogue {
    private JButton boutonOk;
    private JButton boutonAnnuler;
    private JLabel lblAncienNom;
    private JTextField nouveauNom;
    private String regex;

    public DialogueRenommerFichier(JFrame fenetre, String titre, String ancienNom, Projet projet) {
        super(fenetre, titre);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        if (!(projet instanceof ProjetC) && !(projet instanceof ProjetNxc) && !(projet instanceof ProjetSpiC)) {
            this.regex = "[A-Z][a-zA-Z0-9_]*";
        } else {
            this.regex = "[a-zA-Z][a-zA-Z0-9_]*";
        }

        this.lblAncienNom = new JLabel(ancienNom);
        this.nouveauNom = new JTextField();
        this.nouveauNom.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Pattern.matches(DialogueRenommerFichier.this.regex, DialogueRenommerFichier.this.nouveauNom.getText() + Character.valueOf(c).toString())) {
                    evt.consume();
                }

            }
        });
        this.nouveauNom.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent evt) {
                DialogueRenommerFichier.this.boutonOk.setEnabled(Pattern.matches(DialogueRenommerFichier.this.regex, DialogueRenommerFichier.this.nouveauNom.getText()));
            }
        });
        this.nouveauNom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueRenommerFichier.this.boutonOk.doClick();
            }
        });
        this.lblAncienNom.setBorder(new TitledBorder((Border)null, "Ancien Nom", 2, 2, (Font)null, (Color)null));
        this.lblAncienNom.setAlignmentX(0.5F);
        this.nouveauNom.setBorder(new TitledBorder((Border)null, "Nouveau Nom", 2, 2, (Font)null, (Color)null));
        this.nouveauNom.setAlignmentX(0.5F);
        this.nouveauNom.setToolTipText(this.regex);
        panel.add(this.lblAncienNom);
        panel.add(this.nouveauNom);
        this.boutonAnnuler = new JButton("Annuler", Icones.ANNULER_16);
        this.boutonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueRenommerFichier.this.resultat = 1;
                DialogueRenommerFichier.this.dialogue.setVisible(false);
            }
        });
        this.ajouterBouton(this.boutonAnnuler);
        this.boutonOk = new JButton("Valider", Icones.OK_16);
        this.boutonOk.setEnabled(false);
        this.boutonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (DialogueRenommerFichier.this.nouveauNom.getText().length() >= 1) {
                    DialogueRenommerFichier.this.resultat = 2;
                    DialogueRenommerFichier.this.dialogue.setVisible(false);
                }

            }
        });
        this.ajouterBouton(this.boutonOk);
        this.setPanel(panel);
        this.dialogue.setDefaultCloseOperation(0);
        this.dialogue.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                DialogueRenommerFichier.this.boutonAnnuler.doClick();
            }
        });
    }

    public String getNouveauNom() {
        return this.nouveauNom.getText();
    }
}
