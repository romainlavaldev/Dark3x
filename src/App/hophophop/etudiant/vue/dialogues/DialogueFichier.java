//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue.dialogues;

import hophophop.commun.vue.Icones;
import hophophop.commun.vue.dialogues.Dialogue;
import hophophop.etudiant.modele.projet.Projet;
import hophophop.etudiant.modele.projet.ProjetC;
import hophophop.etudiant.modele.projet.ProjetEv3;
import hophophop.etudiant.modele.projet.ProjetJava;
import hophophop.etudiant.modele.projet.ProjetNxc;
import hophophop.etudiant.modele.projet.ProjetPython;
import hophophop.etudiant.modele.projet.ProjetRuby;
import hophophop.etudiant.modele.projet.ProjetScala;
import hophophop.etudiant.modele.projet.ProjetSpiC;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DialogueFichier extends Dialogue {
    private String regex = "[A-Z_][a-zA-Z0-9_]*";
    private JTextField champNom;
    private JButton boutonOk;
    private JRadioButton radioInterface;
    private JRadioButton radioClasse;
    private JRadioButton radioC;
    private JRadioButton radioH;
    private JRadioButton radioRuby;
    private JRadioButton radioPython;
    private JRadioButton radioNxc;
    private JRadioButton radioEv3;
    private JRadioButton radioMakeFile;
    private JRadioButton radioScala;
    private JRadioButton radioAutre;
    private JTextField txtExtensionAutreFichier;
    private JPanel panelExtension;
    private JLabel lblExtension;
    protected String regexExtension = "[a-zA-Z0-9_]*";

    public DialogueFichier(JFrame fenetre, Projet projet) {
        super(fenetre, "Nouveau Fichier", Icones.FICHIER_32);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        this.setPanel(panel);
        JLabel lblNomFichier = new JLabel("Nom du fichier :");
        panel.add(lblNomFichier);
        panel.add(Box.createVerticalStrut(5));
        if (!(projet instanceof ProjetC) && !(projet instanceof ProjetNxc) && !(projet instanceof ProjetSpiC)) {
            this.regex = "[A-Z][a-zA-Z0-9_]*";
        } else {
            this.regex = "[a-zA-Z][a-zA-Z0-9_]*";
        }

        this.champNom = new JTextField();
        this.champNom.setColumns(15);
        this.champNom.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent evt) {
                DialogueFichier.this.boutonOk.setEnabled(Pattern.matches(DialogueFichier.this.regexExtension, DialogueFichier.this.txtExtensionAutreFichier.getText()) && Pattern.matches(DialogueFichier.this.regex, DialogueFichier.this.champNom.getText()));
            }
        });
        this.champNom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueFichier.this.boutonOk.doClick();
            }
        });
        panel.add(this.champNom);
        this.champNom.requestFocus();
        this.champNom.setToolTipText(this.regex);
        this.champNom.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Pattern.matches(DialogueFichier.this.regex, DialogueFichier.this.champNom.getText() + Character.valueOf(c).toString())) {
                    evt.consume();
                }

            }
        });
        panel.add(Box.createVerticalStrut(10));
        JLabel labelType = new JLabel("Type de fichier :");
        panel.add(labelType);
        panel.add(Box.createVerticalStrut(5));
        this.radioClasse = new JRadioButton("Java");
        this.radioInterface = new JRadioButton("Interface Java");
        this.radioC = new JRadioButton("Source C");
        this.radioH = new JRadioButton("Header (.h)");
        this.radioRuby = new JRadioButton("Ruby");
        this.radioPython = new JRadioButton("Python");
        this.radioNxc = new JRadioButton("Nxc");
        this.radioEv3 = new JRadioButton("Ev3");
        this.radioMakeFile = new JRadioButton("Makefile");
        this.radioScala = new JRadioButton("Scala");
        this.radioAutre = new JRadioButton("Autre");
        this.txtExtensionAutreFichier = new JTextField(3);
        this.txtExtensionAutreFichier.setToolTipText("3 caractères " + this.regexExtension);
        this.txtExtensionAutreFichier.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent evt) {
                DialogueFichier.this.boutonOk.setEnabled(Pattern.matches(DialogueFichier.this.regexExtension, DialogueFichier.this.txtExtensionAutreFichier.getText()) && Pattern.matches(DialogueFichier.this.regex, DialogueFichier.this.champNom.getText()));
            }
        });
        this.txtExtensionAutreFichier.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Pattern.matches(DialogueFichier.this.regexExtension, DialogueFichier.this.txtExtensionAutreFichier.getText() + Character.valueOf(c).toString()) || DialogueFichier.this.txtExtensionAutreFichier.getText().length() > 2) {
                    evt.consume();
                }

            }
        });
        this.radioMakeFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                DialogueFichier.this.champNom.setText("Makefile");
            }
        });
        this.radioAutre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DialogueFichier.this.txtExtensionAutreFichier.requestFocus();
            }
        });
        this.radioAutre.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                DialogueFichier.this.txtExtensionAutreFichier.setEnabled(DialogueFichier.this.radioAutre.isSelected());
                DialogueFichier.this.lblExtension.setEnabled(DialogueFichier.this.radioAutre.isSelected());
            }
        });
        ButtonGroup boutons = new ButtonGroup();
        boutons.add(this.radioInterface);
        boutons.add(this.radioClasse);
        boutons.add(this.radioH);
        boutons.add(this.radioC);
        boutons.add(this.radioRuby);
        boutons.add(this.radioPython);
        boutons.add(this.radioNxc);
        boutons.add(this.radioEv3);
        boutons.add(this.radioMakeFile);
        boutons.add(this.radioScala);
        boutons.add(this.radioAutre);
        JPanel panelAutre = new JPanel();
        panelAutre.setLayout(new BoxLayout(panelAutre, 1));
        panelAutre.add(this.radioAutre);
        panelAutre.setBorder(new TitledBorder((Border)null, "Autres Fichiers", 2, 2, (Font)null, (Color)null));
        this.panelExtension = new JPanel();
        this.panelExtension.setLayout(new BoxLayout(this.panelExtension, 0));
        this.lblExtension = new JLabel("Extension :");
        this.lblExtension.setEnabled(false);
        this.panelExtension.add(this.lblExtension);
        this.panelExtension.add(this.txtExtensionAutreFichier);
        this.txtExtensionAutreFichier.setEnabled(false);
        panelAutre.add(this.panelExtension);
        if (projet instanceof ProjetJava) {
            this.radioClasse.setSelected(true);
            JPanel pjava = new JPanel(new FlowLayout());
            pjava.add(this.radioClasse);
            pjava.add(this.radioInterface);
            panel.add(pjava);
            panel.add(Box.createVerticalStrut(10));
            panel.add(panelAutre);
        } else if (projet instanceof ProjetRuby) {
            this.radioRuby.setSelected(true);
            panel.add(this.radioRuby);
            panel.add(Box.createVerticalStrut(5));
        } else if (projet instanceof ProjetPython) {
            this.radioPython.setSelected(true);
            panel.add(this.radioPython);
            panel.add(Box.createVerticalStrut(5));
        } else if (projet instanceof ProjetC) {
            this.radioC.setSelected(true);
            panel.add(this.radioC);
            panel.add(Box.createVerticalStrut(5));
            panel.add(this.radioH);
            panel.add(Box.createVerticalStrut(5));
            if (projet.isCompilationProjet()) {
                panel.add(this.radioMakeFile);
            }
        } else if (projet instanceof ProjetNxc) {
            this.radioNxc.setSelected(true);
            panel.add(this.radioNxc);
            panel.add(Box.createVerticalStrut(5));
        } else if (projet instanceof ProjetEv3) {
            this.radioEv3.setSelected(true);
            panel.add(this.radioEv3);
            panel.add(Box.createVerticalStrut(5));
        } else if (projet instanceof ProjetScala) {
            this.radioScala.setSelected(true);
            panel.add(this.radioScala);
            panel.add(Box.createVerticalStrut(5));
        }

        lblNomFichier.setAlignmentX(0.5F);
        labelType.setAlignmentX(0.5F);
        JButton boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.setIcon(Icones.ANNULER_16);
        boutonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueFichier.this.dialogue.setVisible(false);
            }
        });
        this.ajouterBouton(boutonAnnuler);
        this.boutonOk = new JButton("Valider");
        this.boutonOk.setIcon(Icones.OK_16);
        this.boutonOk.setEnabled(false);
        this.boutonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueFichier.this.resultat = 2;
                DialogueFichier.this.dialogue.setVisible(false);
            }
        });
        this.ajouterBouton(this.boutonOk);
    }

    public String getFichier() {
        if (this.isC()) {
            return this.champNom.getText() + ".c";
        } else if (this.isH()) {
            return this.champNom.getText() + ".h";
        } else if (this.isMakeFile()) {
            return this.champNom.getText();
        } else if (this.isRuby()) {
            return this.champNom.getText() + ".rb";
        } else if (this.isPython()) {
            return this.champNom.getText() + ".py";
        } else if (this.isJava()) {
            return this.champNom.getText() + ".java";
        } else if (this.isNxc()) {
            return this.champNom.getText() + ".nxc";
        } else if (this.isEvo3()) {
            return this.champNom.getText() + ".c";
        } else if (this.isScala()) {
            return this.champNom.getText() + ".scala";
        } else {
            String nf = this.champNom.getText();
            if (!this.txtExtensionAutreFichier.getText().equals("")) {
                nf = nf + "." + this.txtExtensionAutreFichier.getText().substring(0, Integer.min(3, this.txtExtensionAutreFichier.getText().length()));
            }

            return nf;
        }
    }

    private boolean isEvo3() {
        return this.radioEv3.isSelected();
    }

    public boolean isInterface() {
        return this.radioInterface.isSelected();
    }

    public boolean isClasse() {
        return this.radioClasse.isSelected();
    }

    public boolean isH() {
        return this.radioH.isSelected();
    }

    public boolean isMakeFile() {
        return this.radioMakeFile.isSelected();
    }

    public boolean isScala() {
        return this.radioScala.isSelected();
    }

    public boolean isRuby() {
        return this.radioRuby.isSelected();
    }

    public boolean isPython() {
        return this.radioPython.isSelected();
    }

    public boolean isNxc() {
        return this.radioNxc.isSelected();
    }

    public boolean isJava() {
        return this.isClasse() || this.isInterface();
    }

    public boolean isC() {
        return this.radioC.isSelected();
    }

    public boolean isFichierAutre() {
        return this.radioAutre.isSelected();
    }
}
