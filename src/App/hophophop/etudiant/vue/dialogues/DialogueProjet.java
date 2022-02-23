//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue.dialogues;

import hophophop.commun.vue.Icones;
import hophophop.commun.vue.dialogues.Dialogue;
import hophophop.etudiant.modele.H3Etudiant;
import hophophop.etudiant.vue.Fenetre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class DialogueProjet extends Dialogue {
    private JButton boutonOk;
    private JTextField champNom;
    private JRadioButton radioJava;
    private JRadioButton radioCProjet;
    private JRadioButton radioCFichier;
    private JRadioButton radioPython;
    private JRadioButton radioRuby;
    private JRadioButton radioNxc;
    private JRadioButton radioScala;
    private JRadioButton radioEv3;

    public DialogueProjet(final JFrame fenetre, String langage) {
        super(fenetre, "Nouveau projet", Icones.PROJET_32);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        this.setPanel(panel);
        panel.add(new JLabel("Nom du projet :"));
        panel.add(Box.createVerticalStrut(5));
        this.champNom = new JTextField();
        this.champNom.setColumns(15);
        this.champNom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                DialogueProjet.this.boutonOk.doClick();
            }
        });
        this.champNom.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent evt) {
                DialogueProjet.this.boutonOk.setEnabled(DialogueProjet.this.champNom.getText().trim().length() > 0);
            }
        });
        panel.add(this.champNom);
        panel.add(Box.createVerticalStrut(5));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Type du projet :"));
        panel.add(Box.createVerticalStrut(5));
        final ButtonGroup boutons = new ButtonGroup();
        this.radioJava = new JRadioButton("Java");
        this.radioJava.setSelected(false);
        if (langage.equals("") || langage.indexOf("Java") != -1) {
            panel.add(this.radioJava);
            boutons.add(this.radioJava);
            panel.add(Box.createVerticalStrut(5));
        }

        this.radioRuby = new JRadioButton("Ruby");
        this.radioRuby.setSelected(false);
        if (langage.equals("") || langage.indexOf("Ruby") != -1) {
            panel.add(this.radioRuby);
            boutons.add(this.radioRuby);
            panel.add(Box.createVerticalStrut(5));
        }

        this.radioCFichier = new JRadioButton("C en Mode Fichier");
        this.radioCFichier.setSelected(false);
        this.radioCProjet = new JRadioButton("C en Mode Projet (Makefile)");
        this.radioCProjet.setSelected(false);
        if (langage.equals("") || langage.indexOf("C") != -1) {
            boutons.add(this.radioCFichier);
            panel.add(this.radioCFichier);
            if (H3Etudiant.isCompilationProjet()) {
                boutons.add(this.radioCProjet);
                panel.add(this.radioCProjet);
            }

            panel.add(Box.createVerticalStrut(5));
        }

        this.radioNxc = new JRadioButton("Nxc");
        this.radioNxc.setSelected(false);
        if (langage.equals("") || langage.indexOf("Nxc") != -1) {
            panel.add(this.radioNxc);
            boutons.add(this.radioNxc);
        }

        this.radioEv3 = new JRadioButton("Ev3");
        this.radioEv3.setSelected(false);
        if (langage.equals("") || langage.indexOf("Ev3") != -1) {
            panel.add(this.radioEv3);
            boutons.add(this.radioEv3);
        }

        this.radioPython = new JRadioButton("Python");
        this.radioPython.setSelected(false);
        if (langage.equals("") || langage.indexOf("Python") != -1) {
            panel.add(this.radioPython);
            boutons.add(this.radioPython);
            panel.add(Box.createVerticalStrut(5));
        }

        this.radioScala = new JRadioButton("Scala");
        this.radioScala.setSelected(false);
        if (langage.equals("") || langage.indexOf("Scala") != -1) {
            panel.add(this.radioScala);
            boutons.add(this.radioScala);
            panel.add(Box.createVerticalStrut(5));
        }

        if (boutons.getButtonCount() == 1) {
            ((AbstractButton)boutons.getElements().nextElement()).setSelected(true);
        }

        JButton boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.setIcon(Icones.ANNULER_16);
        boutonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueProjet.this.dialogue.setVisible(false);
            }
        });
        this.ajouterBouton(boutonAnnuler);
        this.boutonOk = new JButton("Valider");
        this.boutonOk.setIcon(Icones.OK_16);
        this.boutonOk.setEnabled(false);
        this.boutonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (boutons.getSelection() == null) {
                    JOptionPane.showMessageDialog(fenetre, "Vous devez sélectionner un langage", "Erreur !!!", 0);
                } else {
                    DialogueProjet.this.resultat = 2;
                    DialogueProjet.this.champNom.setText(DialogueProjet.this.champNom.getText().replaceAll(" ", ""));
                    DialogueProjet.this.dialogue.setVisible(false);
                }

            }
        });
        this.ajouterBouton(this.boutonOk);
    }

    public DialogueProjet(Fenetre fenetre, String langage, String nom) {
        this(fenetre, langage);
        this.champNom.setText(nom);
        this.boutonOk.setEnabled(true);
    }

    public String getNom() {
        return this.champNom.getText();
    }

    public boolean isJava() {
        return this.radioJava.isSelected();
    }

    public boolean isNxc() {
        return this.radioNxc.isSelected();
    }

    public boolean isEv3() {
        return this.radioEv3.isSelected();
    }

    public boolean isPython() {
        return this.radioPython.isSelected();
    }

    public boolean isScala() {
        return this.radioScala.isSelected();
    }

    public boolean isC() {
        return this.radioCFichier.isSelected() || this.radioCProjet.isSelected();
    }

    public boolean isRuby() {
        return this.radioRuby.isSelected();
    }

    public boolean isCompilationProjet() {
        return this.radioJava.isSelected() || this.radioCProjet.isSelected() || this.radioScala.isSelected();
    }
}
