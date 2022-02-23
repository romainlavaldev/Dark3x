//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue;

import hophophop.commun.modele.H3Commun;
import hophophop.commun.vue.Icones;
import hophophop.etudiant.modele.H3Etudiant;
import hophophop.etudiant.modele.observateurs.ObservateurH3Etudiant;
import hophophop.etudiant.modele.projet.Projet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectInputStream;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.Border;

public class Fenetre extends JFrame implements ObservateurH3Etudiant {
    public static final Color COULEURQUESTIONS = new Color(255, 255, 225);
    public static final Color FOND_GRIS = new Color(238, 238, 238);
    public static final int TAILLEFONT = 12;
    private BarreMenu menu;
    private BarreOutils outils;
    private Editeur editeur;
    private Console console;
    private Explorateur explorateur;
    private Thread thread;
    private boolean fin;
    private ObjectInputStream in;
    private JPanel panelExplorateur;
    private boolean interfaceActive = true;
    private Dimension dimension;
    private PanelConsigneQuestions panelConsigneQuestions = null;
    private JPanel panelEditeurQuestion;
    private JSplitPane spExploEditQuest;
    private JSplitPane spIdeConsole;

    public BarreMenu getMenu() {
        return this.menu;
    }

    public Dimension getDimension() {
        return this.dimension;
    }

    public Fenetre(boolean simulation) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Insets insets = kit.getScreenInsets(this.getGraphicsConfiguration());
        Dimension screen = kit.getScreenSize();
        int largeurEcran = (int)(screen.getWidth() - (double)insets.left - (double)insets.right);
        int hauteurEcran = (int)(screen.getHeight() - (double)insets.top - (double)insets.bottom);
        this.dimension = new Dimension(1200, 800);
        H3Etudiant.getLogger().info("Dimension de l'écran (LxH) : " + largeurEcran + " x " + hauteurEcran);
        this.setIconImage(((ImageIcon)Icones.ETUDIANT_128).getImage());
        H3Etudiant.ajouterObservateur(this);
        this.setSize(this.dimension);
        this.getContentPane().setLayout(new BorderLayout(0, 0));
        this.spIdeConsole = new JSplitPane(0, true);
        this.add(this.spIdeConsole, "Center");
        JPanel panelConsole = new JPanel();
        panelConsole.setBorder(BorderFactory.createTitledBorder((Border)null, "Console", 2, 0, H3Commun.LaFonteExplorateur));
        panelConsole.setLayout(new BorderLayout(0, 0));
        this.console = new Console(this.editeur);
        panelConsole.add(new JScrollPane(this.console), "Center");
        this.console.ecrireGras("Système : " + System.getProperty("os.name") + " : " + System.getProperty("os.arch") + " : " + System.getProperty("os.version"));
        this.console.ecrireGras("\nVersion de Java : " + System.getProperty("java.version"));
        this.console.ecrireGras("\n\tHome de Java : " + System.getProperty("java.home"));
        this.spExploEditQuest = new JSplitPane(1, true);
        this.panelExplorateur = new JPanel(new BorderLayout());
        Border leBorder = BorderFactory.createTitledBorder((Border)null, "Explorateur", 2, 0, H3Commun.LaFonteExplorateur);
        this.panelExplorateur.setBorder(leBorder);
        this.explorateur = new Explorateur(this);
        this.explorateur.setRootVisible(false);
        JScrollPane spExplorateur = new JScrollPane();
        spExplorateur.setViewportView(this.explorateur);
        this.panelExplorateur.add(spExplorateur, "Center");
        this.panelExplorateur.setMinimumSize(new Dimension(160, 0));
        this.spExploEditQuest.setLeftComponent(this.panelExplorateur);
        this.spExploEditQuest.setDividerLocation(0.25D);
        this.panelEditeurQuestion = new JPanel();
        this.editeur = new Editeur(this);
        this.spExploEditQuest.setRightComponent(this.panelEditeurQuestion);
        this.panelEditeurQuestion.setLayout(new BorderLayout(0, 0));
        this.panelEditeurQuestion.add(this.editeur, "Center");
        this.spIdeConsole.setTopComponent(this.spExploEditQuest);
        this.spIdeConsole.setBottomComponent(panelConsole);
        this.spIdeConsole.setOneTouchExpandable(true);
        this.spIdeConsole.setResizeWeight(0.6D);
        this.outils = new BarreOutils();
        this.add(this.outils, "North");
        this.menu = new BarreMenu();
        this.setJMenuBar(this.menu);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                Actions.QUITTER.actionPerformed((ActionEvent)null);
            }
        });
        this.setSize(this.dimension);
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(0);
        this.repaint();
    }

    public JSplitPane getSpExploEditQuest() {
        return this.spExploEditQuest;
    }

    public JPanel getPanelEditeurQuestion() {
        return this.panelEditeurQuestion;
    }

    public Thread getThread() {
        return this.thread;
    }

    public void activerInterface() {
        this.interfaceActive = true;
        this.outils.setActive();
        this.menu.setActif();
        this.explorateur.setMenuActif();
        this.editeur.setActif();
    }

    public void desactiverInterface() {
        this.interfaceActive = false;
        this.outils.setInactive("      Barre d'outils Inactive : Vous devez sélectionner une question à traiter");
        this.menu.setInactif("      Menu Inactif : Vous devez sélectionner une question à traiter");
        this.explorateur.setMenuInactif("Menu contextuel inactif : Vous devez sélectionner une question à traiter");
        this.editeur.setInactif();
    }

    public Editeur getEditeur() {
        return this.editeur;
    }

    public Explorateur getExplorateur() {
        return this.explorateur;
    }

    public void setExplorateur(Explorateur explorateur) {
        this.explorateur = explorateur;
    }

    public Console getConsole() {
        return this.console;
    }

    public void quitter(boolean confirmation) {
        if (confirmation) {
            if (JOptionPane.showConfirmDialog(this, "Etes vous sur de vouloir quitter ?", "Attention !!", 0, 2, Icones.INFO_64) == 0) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }

    }

    public void ajoutProjet(Projet projet) {
        Actions.IMPORTERREPERTOIRE.setEnabled(!H3Etudiant.isSessionLectureSeule());
    }

    public void connexion() {
    }

    public void deconnexion() {
    }

    public void suppressionProjet(Projet projet) {
        Actions.IMPORTERREPERTOIRE.setEnabled(false);
    }

    public boolean isInterfaceActive() {
        return this.interfaceActive;
    }

    public PanelConsigneQuestions getPanelConsigneQuestion() {
        return this.panelConsigneQuestions;
    }

    public void setPanelQuestion(PanelConsigneQuestions unPanelQuestion) {
        this.panelConsigneQuestions = unPanelQuestion;
    }
}
