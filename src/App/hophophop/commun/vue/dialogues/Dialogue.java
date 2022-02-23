//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.dialogues;

import hophophop.commun.modele.H3Commun;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public abstract class Dialogue {
    public static final int OPTION_ANNULER = 1;
    public static final int OPTION_OK = 2;
    protected int resultat;
    protected JDialog dialogue;
    private JPanel panelBoutons;
    private JPanel panel;

    protected Dialogue(JFrame fenetre, String titre, boolean modal) {
        this(fenetre, titre);
        this.dialogue.setModal(modal);
    }

    protected Dialogue(JFrame fenetre, String titre) {
        this.dialogue = new JDialog(fenetre, titre, true);
        this.dialogue.getContentPane().setLayout(new BoxLayout(this.dialogue.getContentPane(), 1));
        this.panel = new JPanel(new BorderLayout());
        this.panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.dialogue.getContentPane().add(this.panel);
        this.dialogue.getContentPane().add(new JSeparator());
        this.panelBoutons = new JPanel();
        this.panelBoutons.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.dialogue.getContentPane().add(this.panelBoutons);
        this.dialogue.setResizable(false);
    }

    protected Dialogue(JFrame fenetre, String titre, Icon icone) {
        if (titre.equals("Etudiant")) {
            this.dialogue = new JDialog(fenetre, H3Commun.getVersionEtudiant(), true);
        } else if (titre.equals("Tuteur")) {
            this.dialogue = new JDialog(fenetre, H3Commun.getVersionTuteur(), true);
        } else if (titre.equals("Admin")) {
            this.dialogue = new JDialog(fenetre, H3Commun.getVersionAdmin(), true);
        } else {
            this.dialogue = new JDialog(fenetre, (String)null, true);
        }

        this.dialogue.getContentPane().setLayout(new BoxLayout(this.dialogue.getContentPane(), 1));
        JPanel panelTitre = new JPanel(new BorderLayout());
        panelTitre.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.dialogue.getContentPane().add(panelTitre);
        JLabel labelIcone = new JLabel();
        labelIcone.setIcon(icone);
        panelTitre.add(labelIcone, "West");
        JLabel labelTitre = new JLabel(titre);
        labelTitre.setHorizontalAlignment(0);
        labelTitre.setFont(labelTitre.getFont().deriveFont(1, 14.0F));
        panelTitre.add(labelTitre, "Center");
        this.panel = new JPanel(new BorderLayout());
        this.panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.dialogue.getContentPane().add(this.panel);
        this.panelBoutons = new JPanel();
        this.panelBoutons.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.dialogue.getContentPane().add(this.panelBoutons);
        this.dialogue.setResizable(false);
    }

    protected void ajouterBouton(JButton bouton) {
        this.panelBoutons.add(bouton);
    }

    protected void setPanel(JPanel panel) {
        this.panel.add(panel, "Center");
    }

    public int afficher() {
        this.resultat = 1;
        this.dialogue.pack();
        this.dialogue.setLocationRelativeTo(this.dialogue.getOwner());
        this.dialogue.setVisible(true);

        while(this.dialogue.isVisible()) {
        }

        this.dialogue.dispose();
        return this.resultat;
    }

    public int afficherALaPosition(int x, int y) {
        this.resultat = 1;
        this.dialogue.pack();
        this.dialogue.setLocation(x, y);
        this.dialogue.setVisible(true);

        while(this.dialogue.isVisible()) {
        }

        this.dialogue.dispose();
        return this.resultat;
    }
}
