//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue;

import hophophop.commun.modele.H3Commun;
import hophophop.commun.modele.Message;
import hophophop.commun.vue.Icones;
import hophophop.etudiant.modele.Dossier;
import hophophop.etudiant.modele.H3Etudiant;
import hophophop.etudiant.modele.fichier.Fichier;
import hophophop.etudiant.modele.fichier.FichierAutre;
import hophophop.etudiant.modele.observateurs.ObservateurDossier;
import hophophop.etudiant.modele.observateurs.ObservateurFichier;
import hophophop.etudiant.modele.observateurs.ObservateurH3Etudiant;
import hophophop.etudiant.modele.observateurs.ObservateurProjet;
import hophophop.etudiant.modele.projet.Erreur;
import hophophop.etudiant.modele.projet.Projet;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

public class Explorateur extends JTree implements ObservateurH3Etudiant {
    private DefaultMutableTreeNode racine;
    private JPopupMenu menuProjet;
    private JPopupMenu menuClasse;
    private JPopupMenu menuExplorateur;
    private boolean menuActif;
    protected String projetCourant;

    public Explorateur(final Fenetre fenetre) {
        this.setDragEnabled(true);
        this.setTransferHandler(new MyTransferHandler());
        H3Etudiant.ajouterObservateur(this);
        this.menuExplorateur = new JPopupMenu();
        this.menuExplorateur.add(Actions.NOUVEAU_PROJET);
        this.menuProjet = new JPopupMenu();
        this.menuProjet.add(Actions.NOUVEAU_FICHIER);
        this.menuProjet.add(Actions.SUPPRIMER_PROJET);
        this.menuClasse = new JPopupMenu();
        this.menuClasse.add(Actions.SUPPRIMER_FICHIER);
        this.menuClasse.add(Actions.RENOMMER_FICHIER);
        this.menuActif = true;
        this.racine = new DefaultMutableTreeNode();
        ((DefaultTreeModel)this.getModel()).setRoot(this.racine);
        this.setCellRenderer(new Explorateur.ExplorateurCellRenderer());
        this.setRootVisible(false);
        this.getSelectionModel().setSelectionMode(1);
        this.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent arg0) {
                Projet projet = Explorateur.this.getProjet();
                if (projet != null) {
                    if ((TreeNode)Explorateur.this.getLastSelectedPathComponent() instanceof Explorateur.NoeudFichier) {
                        projet.initialiseCompletionProvider();
                    }

                    Actions.IMPORTERREPERTOIRE.setEnabled(!H3Etudiant.isSessionLectureSeule());
                    Actions.SUPPRIMER_PROJET.setEnabled(!H3Etudiant.isSessionLectureSeule());
                    Actions.SUPPRIMER_FICHIER.setEnabled(!H3Etudiant.isSessionLectureSeule());
                    Actions.NOUVEAU_PROJET.setEnabled(!H3Etudiant.isSessionLectureSeule());
                    fenetre.getMenu().remove(fenetre.getMenu().getMenu_C());
                    fenetre.getMenu().remove(fenetre.getMenu().getMenu_Ruby());
                    fenetre.getMenu().remove(fenetre.getMenu().getMenu_NxC());
                    fenetre.setJMenuBar(fenetre.getMenu());
                    Actions.NOUVEAU_FICHIER.setEnabled(!H3Etudiant.isSessionLectureSeule());
                    if (projet.getFichiers().size() == 0) {
                        Actions.COMPILER.setEnabled(false);
                        Actions.EXECUTER.setEnabled(false);
                    } else {
                        Actions.COMPILER.setEnabled(!H3Etudiant.isSessionLectureSeule());
                        Actions.EXECUTER.setEnabled(!H3Etudiant.isSessionLectureSeule());
                        Actions.LIGNEDECOMMANDE.setEnabled(!H3Etudiant.isSessionLectureSeule());
                    }

                    if ((TreeNode)Explorateur.this.getLastSelectedPathComponent() instanceof Explorateur.NoeudProjet && !projet.isCompilationProjet()) {
                        Actions.EXECUTER.setEnabled(false);
                        Actions.COMPILER.setEnabled(false);
                    }

                    String tf = Explorateur.this.getProjet().getType();
                    if (tf.equals("Nxc")) {
                        fenetre.getMenu().add(fenetre.getMenu().getMenu_NxC());
                        fenetre.setJMenuBar(fenetre.getMenu());
                    } else {
                        TreeNode noeud;
                        if (tf.equals("C")) {
                            fenetre.getMenu().add(fenetre.getMenu().getMenu_C());
                            fenetre.setJMenuBar(fenetre.getMenu());
                            noeud = (TreeNode)Explorateur.this.getLastSelectedPathComponent();
                            if (noeud instanceof Explorateur.NoeudFichier && ((Explorateur.NoeudFichier)noeud).getFichier().getNom().endsWith(".h")) {
                                Actions.EXECUTER.setEnabled(false);
                                Actions.COMPILER.setEnabled(false);
                            }
                        } else if (tf.equals("Ruby")) {
                            Actions.COMPILER.setEnabled(false);
                            noeud = (TreeNode)Explorateur.this.getLastSelectedPathComponent();
                            if (noeud instanceof Explorateur.NoeudFichier) {
                                if (((Explorateur.NoeudFichier)noeud).getFichier().getNom().endsWith(".glade")) {
                                    Explorateur.this.menuClasse.removeAll();
                                    Explorateur.this.menuClasse.add(Actions.RENOMMER_FICHIER);
                                    Explorateur.this.menuClasse.add(Actions.SUPPRIMER_FICHIER);
                                    Explorateur.this.menuClasse.add(Actions.LANCERGLADE);
                                    Actions.EXECUTER.setEnabled(false);
                                    Actions.LANCERGLADE.setEnabled(!H3Etudiant.isSessionLectureSeule());
                                    Actions.NOUVEAUGLADE.setEnabled(false);
                                } else {
                                    Actions.LANCERGLADE.setEnabled(false);
                                    Actions.EXECUTER.setEnabled(!H3Etudiant.isSessionLectureSeule());
                                    Explorateur.this.menuClasse.removeAll();
                                    Explorateur.this.menuClasse.add(Actions.RENOMMER_FICHIER);
                                    Explorateur.this.menuClasse.add(Actions.SUPPRIMER_FICHIER);
                                }
                            }

                            Actions.NOUVEAUGLADE.setEnabled(!H3Etudiant.isSessionLectureSeule());
                            fenetre.getMenu().add(fenetre.getMenu().getMenu_Ruby());
                            fenetre.setJMenuBar(fenetre.getMenu());
                        }
                    }
                } else {
                    Actions.NOUVEAU_FICHIER.setEnabled(false);
                    Actions.COMPILER.setEnabled(false);
                    Actions.EXECUTER.setEnabled(false);
                }

            }
        });
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1 && evt.getClickCount() == 1) {
                    TreeNode noeud = (TreeNode)Explorateur.this.getLastSelectedPathComponent();
                    String nomProjet;
                    Pattern p;
                    Matcher mx;
                    boolean b;
                    boolean qcx;
                    Message m;
                    int qc;
                    if (noeud instanceof Explorateur.NoeudFichier) {
                        Fichier fichier = ((Explorateur.NoeudFichier)noeud).getFichier();
                        fenetre.getEditeur().ouvrirFichier(fichier);
                        nomProjet = fichier.getProjet().getNom();
                        if (H3Etudiant.isTestunitaire() && !nomProjet.equals(Explorateur.this.projetCourant)) {
                            m = Message.getEnonceQuestionProjet(nomProjet, H3Etudiant.getScenario());

                            try {
                                H3Etudiant.getOut().writeObject(m);
                            } catch (IOException var13) {
                                var13.printStackTrace();
                            }
                        } else if (H3Etudiant.isScenario()) {
                            p = Pattern.compile("Question([0-9]*)");
                            mx = p.matcher(nomProjet);
                            b = mx.matches();
                            qcx = false;
                            if (b) {
                                qc = Integer.parseInt(mx.group(1));
                                if (H3Etudiant.getQuestionCourante() != qc) {
                                    try {
                                        H3Etudiant.getOut().writeObject(Message.getEnonceQuestion(H3Etudiant.getNomDeLaSession(), qc));
                                    } catch (IOException var12) {
                                        var12.printStackTrace();
                                    }
                                }
                            }
                        }

                        Explorateur.this.projetCourant = nomProjet;
                    } else if (noeud instanceof Explorateur.NoeudProjet) {
                        Projet projet = ((Explorateur.NoeudProjet)noeud).getProjet();
                        nomProjet = projet.getNom();
                        Explorateur.this.projetCourant = nomProjet;
                        if (H3Etudiant.isTestunitaire()) {
                            m = Message.getEnonceQuestionProjet(nomProjet, H3Etudiant.getScenario());

                            try {
                                H3Etudiant.getOut().writeObject(m);
                            } catch (IOException var11) {
                                var11.printStackTrace();
                            }
                        } else if (H3Etudiant.isScenario()) {
                            p = Pattern.compile("Question([0-9]*)");
                            mx = p.matcher(Explorateur.this.projetCourant);
                            b = mx.matches();
                            qcx = false;
                            if (b) {
                                qc = Integer.parseInt(mx.group(1));
                                H3Etudiant.setQuestionCourante(qc);
                                if (qc <= H3Etudiant.getNombreDeQuestions()) {
                                    try {
                                        H3Etudiant.getOut().writeObject(Message.getEnonceQuestion(H3Etudiant.getNomDeLaSession(), qc));
                                        JPanel panelQuestions = H3Etudiant.getFenetre().getPanelConsigneQuestion().getPanelQuestions();
                                        panelQuestions.setBorder(BorderFactory.createTitledBorder((Border)null, "Question " + qc + " / " + H3Etudiant.getNombreDeQuestions(), 2, 0, new Font("Comic Sans MS", 0, 12)));
                                        panelQuestions.setPreferredSize(new Dimension(395, panelQuestions.getHeight()));
                                        if (qc == H3Etudiant.getNombreDeQuestions()) {
                                            H3Etudiant.getFenetre().getPanelConsigneQuestion().getBtnSuivant().setEnabled(false);
                                            H3Etudiant.getFenetre().getPanelConsigneQuestion().getBtnPrecedent().setEnabled(true);
                                        } else if (qc == 1) {
                                            H3Etudiant.getFenetre().getPanelConsigneQuestion().getBtnPrecedent().setEnabled(false);
                                            H3Etudiant.getFenetre().getPanelConsigneQuestion().getBtnSuivant().setEnabled(true);
                                        } else {
                                            H3Etudiant.getFenetre().getPanelConsigneQuestion().getBtnSuivant().setEnabled(true);
                                            H3Etudiant.getFenetre().getPanelConsigneQuestion().getBtnPrecedent().setEnabled(true);
                                        }
                                    } catch (IOException var10) {
                                        JOptionPane.showMessageDialog(H3Etudiant.getFenetre(), "Erreur lors de la demande d'énoncé de la question", "Attention !!!!!", 0);
                                        var10.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                } else if (evt.getButton() == 3 && Explorateur.this.menuActif) {
                    if (Explorateur.this.getLastSelectedPathComponent() instanceof Explorateur.NoeudProjet) {
                        Explorateur.this.menuProjet.show(evt.getComponent(), evt.getX(), evt.getY());
                    } else if (Explorateur.this.getLastSelectedPathComponent() instanceof Explorateur.NoeudFichier) {
                        Explorateur.this.menuClasse.show(evt.getComponent(), evt.getX(), evt.getY());
                    } else {
                        Explorateur.this.menuExplorateur.show(evt.getComponent(), evt.getX(), evt.getY());
                    }
                }

            }
        });
    }

    public void ajoutProjet(Projet projet) {
        Explorateur.NoeudProjet noeud = new Explorateur.NoeudProjet(projet, this);
        int pos = 0;
        boolean trouve = false;
        if (this.racine.getChildCount() == 0) {
            this.racine.insert(noeud, 0);
        } else {
            Enumeration np = this.racine.children();

            while(np.hasMoreElements()) {
                Explorateur.NoeudProjet f = (Explorateur.NoeudProjet)np.nextElement();
                if (f.getProjet().getNom().compareTo(projet.getNom()) < 0) {
                    ++pos;
                } else {
                    trouve = true;
                    this.racine.insert(noeud, pos);
                }
            }
        }

        if (!trouve) {
            this.racine.insert(noeud, pos);
        }

        projet.ajouterObservateur(noeud);
        Iterator var7 = projet.getFichiers().iterator();

        while(var7.hasNext()) {
            Fichier fichier = (Fichier)var7.next();
            noeud.ajoutFichier(fichier);
        }

        ((DefaultTreeModel)this.getModel()).reload();
        this.scrollPathToVisible(new TreePath(noeud.getPath()));
        this.setSelectionPath(new TreePath(noeud.getPath()));
    }

    public void connexion() {
    }

    public void deconnexion() {
    }

    public void suppressionProjet(Projet projet) {
        Explorateur.NoeudProjet noeud;
        for(noeud = (Explorateur.NoeudProjet)this.racine.getFirstChild(); noeud.getProjet() != projet; noeud = (Explorateur.NoeudProjet)this.racine.getChildAfter(noeud)) {
        }

        Enumeration enf = noeud.children();

        while(enf.hasMoreElements()) {
            H3Etudiant.getFenetre().getEditeur().fermer(((Explorateur.NoeudFichier)enf.nextElement()).getFichier());
        }

        this.racine.remove(noeud);
        projet.supprimerObservateur(noeud);
        ((DefaultTreeModel)this.getModel()).reload();
        if (this.racine.children().hasMoreElements()) {
            Explorateur.NoeudProjet np = (Explorateur.NoeudProjet)this.racine.getFirstChild();
            this.scrollPathToVisible(new TreePath(np.getPath()));
            this.setSelectionPath(new TreePath(np.getPath()));
        } else {
            Actions.SUPPRIMER_PROJET.setEnabled(false);
            Actions.SUPPRIMER_FICHIER.setEnabled(false);
        }

    }

    public Projet getProjet() {
        TreeNode noeud;
        for(noeud = (TreeNode)this.getLastSelectedPathComponent(); noeud != null && !(noeud instanceof Explorateur.NoeudProjet); noeud = noeud.getParent()) {
        }

        return noeud == null ? null : ((Explorateur.NoeudProjet)noeud).getProjet();
    }

    public Fichier getFichier() {
        TreeNode noeud = (TreeNode)this.getLastSelectedPathComponent();
        return noeud instanceof Explorateur.NoeudFichier ? ((Explorateur.NoeudFichier)noeud).getFichier() : null;
    }

    public void fermerToutLesNoeuds() {
        Explorateur ex = H3Etudiant.getFenetre().getExplorateur();
        DefaultMutableTreeNode racine = ex.getRacine();

        for(int i = 0; i < racine.getChildCount(); ++i) {
            ex.collapseRow(i);
        }

    }

    public DefaultMutableTreeNode getRacine() {
        return this.racine;
    }

    public void setRacine(DefaultMutableTreeNode racine) {
        this.racine = racine;
    }

    public JPopupMenu getMenuExplorateur() {
        return this.menuExplorateur;
    }

    public JPopupMenu getMenuProjet() {
        return this.menuProjet;
    }

    public JPopupMenu getMenuClasse() {
        return this.menuClasse;
    }

    public void setMenuInactif(String string) {
        this.menuActif = false;
        this.setToolTipText(string);
    }

    public void setMenuActif() {
        this.menuActif = true;
        this.setToolTipText("");
    }

    public class ExplorateurCellRenderer extends JLabel implements TreeCellRenderer {
        public ExplorateurCellRenderer() {
            this.setBackground(new Color(70, 70, 70));
            this.setFont(H3Commun.LaFonteExplorateur);
        }

        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean focus) {
            if (value instanceof Explorateur.NoeudProjet) {
                Projet projet = ((Explorateur.NoeudProjet)value).getProjet();
                this.setFont(this.getFont().deriveFont(1));
                this.setText(projet.getNom());
                this.setIcon(Icones.DOSSIER_16);
            } else if (value instanceof Explorateur.NoeudFichier) {
                Fichier fichier = ((Explorateur.NoeudFichier)value).getFichier();
                this.setFont(this.getFont().deriveFont(0));
                this.setText(fichier.getNom());
                this.setIcon(Icones.CLASSE_16);
            } else if (value instanceof Explorateur.NoeudDossier) {
                Dossier d = ((Explorateur.NoeudDossier)value).getDossier();
                this.setFont(this.getFont().deriveFont(0));
                this.setText(d.getNom());
                this.setIcon(Icones.DOSSIER_16);
            }

            return this;
        }
    }

    public class NoeudProjet extends DefaultMutableTreeNode implements ObservateurProjet {
        private Projet projet;
        private JTree explorateur;

        public NoeudProjet(Projet projet, JTree explorateur) {
            this.projet = projet;
            this.explorateur = explorateur;
        }

        public Projet getProjet() {
            return this.projet;
        }

        public void ajoutFichier(Fichier fichier) {
            Explorateur.NoeudFichier noeud = Explorateur.this.new NoeudFichier(fichier);
            this.insert(noeud, this.getChildCount());
            fichier.ajouterObservateur(noeud);
            ((DefaultTreeModel)this.explorateur.getModel()).reload();
            this.explorateur.scrollPathToVisible(new TreePath(noeud.getPath()));
            this.explorateur.setSelectionPath(new TreePath(noeud.getPath()));
        }

        public void suppressionFichier(Fichier fichier) {
            Explorateur.NoeudFichier noeud;
            for(noeud = (Explorateur.NoeudFichier)this.getFirstChild(); noeud.getFichier() != fichier; noeud = (Explorateur.NoeudFichier)this.getChildAfter(noeud)) {
            }

            this.remove(noeud);
            boolean dernier = this.getChildCount() == 0;
            if (!dernier) {
                noeud = (Explorateur.NoeudFichier)this.getFirstChild();
                ((DefaultTreeModel)this.explorateur.getModel()).reload();
                Explorateur.this.scrollPathToVisible(new TreePath(noeud.getPath()));
            } else {
                ((DefaultTreeModel)this.explorateur.getModel()).reload();
            }

        }

        public void executionProjet(Projet projet, String sortie) {
            Actions.EXECUTER.setEnabled(!H3Etudiant.isSessionLectureSeule());
            if (!projet.getType().equals("Ruby")) {
                Actions.COMPILER.setEnabled(!H3Etudiant.isSessionLectureSeule());
            }

        }

        public void compilationProjet(Projet projet, String sortie, List<Erreur> listeErreur, Set<Fichier> ensembleDesFichiersAvecErreur, boolean dialogue) {
        }
    }

    public class NoeudFichier extends DefaultMutableTreeNode implements ObservateurFichier, Transferable {
        private Fichier fichier;

        public NoeudFichier(Fichier fichier) {
            this.fichier = fichier;
        }

        public Fichier getFichier() {
            return this.fichier;
        }

        public void enregistrementFichier() {
        }

        public void modificationFichier() {
        }

        public Object getTransferData(DataFlavor arg0) throws UnsupportedFlavorException, IOException {
            return this;
        }

        public DataFlavor[] getTransferDataFlavors() {
            return null;
        }

        public boolean isDataFlavorSupported(DataFlavor arg0) {
            return true;
        }
    }

    public class NoeudDossier extends DefaultMutableTreeNode implements ObservateurDossier, Transferable {
        private Dossier dossier;
        private JTree explorateur;

        public NoeudDossier(Dossier dossier, Explorateur explorateur) {
            this.dossier = dossier;
            this.explorateur = explorateur;
        }

        public Dossier getDossier() {
            return this.dossier;
        }

        public Object getTransferData(DataFlavor arg0) throws UnsupportedFlavorException, IOException {
            return this;
        }

        public DataFlavor[] getTransferDataFlavors() {
            return null;
        }

        public boolean isDataFlavorSupported(DataFlavor arg0) {
            return true;
        }

        public void ajoutFichier(FichierAutre fichier) {
            Explorateur.NoeudFichier noeud = Explorateur.this.new NoeudFichier(fichier);
            this.insert(noeud, this.getChildCount());
            fichier.ajouterObservateur(noeud);
            ((DefaultTreeModel)this.explorateur.getModel()).reload();
            this.explorateur.scrollPathToVisible(new TreePath(noeud.getPath()));
            this.explorateur.setSelectionPath(new TreePath(noeud.getPath()));
        }

        public void suppressionFichier(FichierAutre fichier) {
            Explorateur.NoeudFichier noeud;
            for(noeud = (Explorateur.NoeudFichier)this.getFirstChild(); noeud.getFichier() != fichier; noeud = (Explorateur.NoeudFichier)this.getChildAfter(noeud)) {
            }

            this.remove(noeud);
            boolean dernier = this.getChildCount() == 0;
            if (!dernier) {
                noeud = (Explorateur.NoeudFichier)this.getFirstChild();
                ((DefaultTreeModel)this.explorateur.getModel()).reload();
                Explorateur.this.scrollPathToVisible(new TreePath(noeud.getPath()));
            } else {
                ((DefaultTreeModel)this.explorateur.getModel()).reload();
            }

        }
    }
}
