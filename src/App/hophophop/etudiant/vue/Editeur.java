//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue;

import hophophop.commun.modele.H3Commun;
import hophophop.commun.vue.Icones;
import hophophop.commun.vue.rsyntax.rSyntaxTextAreaTools;
import hophophop.etudiant.modele.H3Etudiant;
import hophophop.etudiant.modele.fichier.Fichier;
import hophophop.etudiant.modele.observateurs.ObservateurFichier;
import hophophop.etudiant.modele.observateurs.ObservateurProjet;
import hophophop.etudiant.modele.projet.Erreur;
import hophophop.etudiant.modele.projet.Projet;
import hophophop.etudiant.vue.Explorateur.NoeudFichier;
import hophophop.etudiant.vue.Explorateur.NoeudProjet;
import hophophop.etudiant.vue.dialogues.DialogueErreurCompilation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.ToolTipManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.tree.TreePath;
import org.fife.rsta.ac.LanguageSupport;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.rsta.ac.java.JavaLanguageSupport;
import org.fife.ui.rtextarea.RTextScrollPane;

public class Editeur extends JTabbedPane implements ObservateurProjet {
    private JPopupMenu menuEditeur;
    private Fenetre fenetre;
    private boolean actif = true;

    public Editeur(final Fenetre fen) {
        this.fenetre = fen;
        this.setFont(H3Commun.LaFonteEditeur);
        this.menuEditeur = new JPopupMenu();
        this.menuEditeur.add(Actions.FERMER);
        this.menuEditeur.add(Actions.FERMER_TOUT);
        this.setOpaque(false);
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int index = Editeur.this.getUI().tabForCoordinate(Editeur.this, evt.getX(), evt.getY());
                if (evt.getButton() == 1) {
                    if (index != -1) {
                        Rectangle r = ((Editeur.IconeFermeture)Editeur.this.getIconAt(index)).getBounds();
                        if (r.contains(evt.getX(), evt.getY())) {
                            Actions.FERMER.actionPerformed((ActionEvent)null);
                            if (Editeur.this.getComponentCount() == 0) {
                                Actions.COMPILER.setEnabled(false);
                                Actions.EXECUTER.setEnabled(false);
                            }
                        }
                    }
                } else if (evt.getButton() == 3 && Editeur.this.getComponentCount() > 0 && index != -1) {
                    Editeur.this.menuEditeur.show(evt.getComponent(), evt.getX(), evt.getY());
                }

            }
        });
        this.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                Editeur.Page page = (Editeur.Page)Editeur.this.getSelectedComponent();
                if (page == null) {
                    Actions.COUPER.setEnabled(false);
                    Actions.COPIER.setEnabled(false);
                    Actions.COLLER.setEnabled(false);
                    Actions.SELECTIONNER_TOUT.setEnabled(false);
                    Actions.FERMER.setEnabled(false);
                    Actions.FERMER_TOUT.setEnabled(false);
                    Actions.UNDO.setEnabled(false);
                    Actions.REDO.setEnabled(false);
                    Actions.COMMENTERCODE.setEnabled(false);
                    Actions.DECOMMENTERCODE.setEnabled(false);
                } else {
                    if (page.getrSyntaxCodeSource().getSelectedText() == null) {
                        Actions.COUPER.setEnabled(false);
                        Actions.COPIER.setEnabled(false);
                        Actions.DECALERADROITE.setEnabled(false);
                        Actions.DECALERAGAUCHE.setEnabled(false);
                    } else {
                        Actions.COUPER.setEnabled(!H3Etudiant.isSessionLectureSeule());
                        Actions.COPIER.setEnabled(!H3Etudiant.isSessionLectureSeule());
                        Actions.DECALERADROITE.setEnabled(!H3Etudiant.isSessionLectureSeule());
                        Actions.DECALERAGAUCHE.setEnabled(!H3Etudiant.isSessionLectureSeule());
                    }

                    Actions.COLLER.setEnabled(!H3Etudiant.isSessionLectureSeule());
                    Actions.SELECTIONNER_TOUT.setEnabled(!H3Etudiant.isSessionLectureSeule());
                    Actions.FERMER.setEnabled(!H3Etudiant.isSessionLectureSeule());
                    Actions.FERMER_TOUT.setEnabled(!H3Etudiant.isSessionLectureSeule());
                    Actions.UNDO.setEnabled(!H3Etudiant.isSessionLectureSeule());
                    Actions.REDO.setEnabled(!H3Etudiant.isSessionLectureSeule());
                    Actions.COMMENTERCODE.setEnabled(!H3Etudiant.isSessionLectureSeule());
                    Actions.DECOMMENTERCODE.setEnabled(!H3Etudiant.isSessionLectureSeule());
                    Actions.EFFACER_CONSOLE.setEnabled(!H3Etudiant.isSessionLectureSeule());
                    Fichier fic = page.getFichier();
                    Projet proj = fic.getProjet();
                    Explorateur ex = fen.getExplorateur();
                    Enumeration ch = ex.getRacine().getRoot().children();

                    label159:
                    while(true) {
                        NoeudProjet np;
                        do {
                            if (!ch.hasMoreElements()) {
                                break label159;
                            }

                            np = (NoeudProjet)ch.nextElement();
                        } while(np.getProjet() != proj);

                        Enumeration chi = np.children();

                        while(chi.hasMoreElements()) {
                            NoeudFichier nf = (NoeudFichier)chi.nextElement();
                            if (nf.getFichier() == fic) {
                                ex.scrollPathToVisible(new TreePath(nf.getPath()));
                                ex.setSelectionRow(ex.getRowForPath(new TreePath(nf.getPath())));
                            }
                        }
                    }
                }

                Actions.COUPER.setEnabled(!H3Etudiant.isSessionLectureSeule());
                Actions.COPIER.setEnabled(!H3Etudiant.isSessionLectureSeule());
                Actions.COLLER.setEnabled(!H3Etudiant.isSessionLectureSeule());
                Actions.SELECTIONNER_TOUT.setEnabled(!H3Etudiant.isSessionLectureSeule());
                Actions.FERMER.setEnabled(!H3Etudiant.isSessionLectureSeule());
                Actions.FERMER_TOUT.setEnabled(!H3Etudiant.isSessionLectureSeule());
                Actions.UNDO.setEnabled(!H3Etudiant.isSessionLectureSeule());
                Actions.REDO.setEnabled(!H3Etudiant.isSessionLectureSeule());
                Actions.DECALERADROITE.setEnabled(!H3Etudiant.isSessionLectureSeule());
                Actions.DECALERAGAUCHE.setEnabled(!H3Etudiant.isSessionLectureSeule());
                Actions.COMMENTERCODE.setEnabled(!H3Etudiant.isSessionLectureSeule());
                Actions.DECOMMENTERCODE.setEnabled(!H3Etudiant.isSessionLectureSeule());
            }
        });
    }

    public void ouvrirFichier(Fichier fichier) {
        Editeur.Page page;
        for(int i = 0; i < this.getComponentCount(); ++i) {
            page = (Editeur.Page)this.getComponentAt(i);
            if (page.getFichier() == fichier) {
                this.setSelectedComponent(page);
                fichier.setPage(page);
                Actions.FERMER.setEnabled(!H3Etudiant.isSessionLectureSeule());
                Actions.FERMER_TOUT.setEnabled(!H3Etudiant.isSessionLectureSeule());
                return;
            }
        }

        String titre = fichier.getNom();
        page = new Editeur.Page(fichier, this);
        fichier.setPage(page);
        Editeur.IconeFermeture icone = new Editeur.IconeFermeture(page);
        this.addTab(titre, icone, page);
        this.setSelectedComponent(page);
        fichier.ajouterObservateur(page);
    }

    public void ouvrirProjet(String projet) {
        Optional<Projet> proj = H3Etudiant.getProjets().stream().filter((px) -> {
            return px.getNom().equals(projet);
        }).findAny();
        if (proj.isPresent()) {
            Projet p = (Projet)proj.get();
            this.ouvrirProjet(p);
        }

    }

    public void ouvrirProjet(Projet projet) {
        this.fermerTout();
        Iterator var2 = projet.getFichiers().iterator();

        while(var2.hasNext()) {
            Fichier f = (Fichier)var2.next();
            this.ouvrirFichier(f);
        }

    }

    public void fermer(Fichier fichier) {
        for(int i = 0; i < this.getComponentCount(); ++i) {
            Editeur.Page page = (Editeur.Page)this.getComponentAt(i);
            if (page.getFichier() == fichier) {
                page.getFichier().enregistrer();
                fichier.supprimerObservateur(page);
                fichier.recharger();
                fichier.setPage((Editeur.Page)null);
                this.remove(page);
                break;
            }
        }

    }

    public void fermer(List<Fichier> fichiers) {
        Iterator var2 = fichiers.iterator();

        while(var2.hasNext()) {
            Fichier fichier = (Fichier)var2.next();
            this.fermer(fichier);
        }

    }

    public void fermer() {
        Editeur.Page page = (Editeur.Page)this.getSelectedComponent();
        page.getFichier().enregistrer();
        page.getFichier().supprimerObservateur(page);
        page.getFichier().recharger();
        page.getFichier().setPage((Editeur.Page)null);
        this.remove(page);
    }

    public void fermerTout() {
        int i;
        Editeur.Page page;
        for(i = 0; i < this.getComponentCount(); ++i) {
            page = (Editeur.Page)this.getComponentAt(i);
            this.enregistrerTout();
        }

        for(i = this.getComponentCount() - 1; i >= 0; --i) {
            page = (Editeur.Page)this.getComponentAt(i);
            page.getFichier().supprimerObservateur(page);
            page.getFichier().recharger();
            page.getFichier().setPage((Editeur.Page)null);
            this.remove(page);
        }

        H3Etudiant.getFenetre().getExplorateur().fermerToutLesNoeuds();
        Actions.NOUVEAU_PROJET.setEnabled(true);
    }

    public void enregistrerTout() {
        Editeur.Page page = (Editeur.Page)this.getSelectedComponent();
        if (page != null) {
            Fichier f = page.getFichier();
            Projet p = f.getProjet();
            Iterator var4 = p.getFichiers().iterator();

            while(var4.hasNext()) {
                Fichier fic = (Fichier)var4.next();
                fic.enregistrer();
            }
        }

    }

    public Editeur.Page getPageActuelle() {
        return (Editeur.Page)this.getSelectedComponent();
    }

    public int getNombrePages() {
        return this.getComponentCount();
    }

    public void marquerLaLigne(Erreur er) {
        this.ouvrirFichier(er.getLeFichier());
        this.getPageActuelle().marquerLaLigne(er.getNumeroDeLigne());
    }

    public void ajoutFichier(Fichier fichier) {
    }

    public void compilationProjet(Projet projet, String sortie, List<Erreur> listeErreur, Set<Fichier> ensembleDesFichiersAvecErreur, boolean dialogue) {
        Fichier actuel = null;
        if (this.getComponentCount() != 0) {
            actuel = this.getPageActuelle().getFichier();
        }

        if (actuel != null) {
            this.ouvrirFichier(actuel);
        }

        if (listeErreur.size() != 0) {
            DialogueErreurCompilation d = new DialogueErreurCompilation(Actions.getFenetre(), listeErreur, this, sortie);
            d.setAlwaysOnTop(true);
            Point topLeft = this.getLocationOnScreen();
            Dimension parentSize = this.getSize();
            Dimension mySize = d.getSize();
            int x;
            if (parentSize.width > mySize.width) {
                x = parentSize.width - mySize.width + topLeft.x;
            } else {
                x = topLeft.x;
            }

            int y;
            if (parentSize.height > mySize.height) {
                y = parentSize.height - mySize.height + topLeft.y;
            } else {
                y = topLeft.y;
            }

            d.setLocation(x, y);
            if (dialogue) {
                d.setVisible(true);
            }
        }

    }

    public void executionProjet(Projet projet, String sortie) {
    }

    public void suppressionFichier(Fichier fichier) {
        this.fermer(fichier);
    }

    public boolean isActif() {
        return this.actif;
    }

    public void setInactif() {
        this.actif = false;

        for(int compteur = 0; compteur < this.getTabCount(); ++compteur) {
            Editeur.Page p = (Editeur.Page)this.getComponentAt(compteur);
            p.getrSyntaxCodeSource().setEditable(false);
            p.getrSyntaxCodeSource().setSyntaxEditingStyle("text/plain");
            p.getrSyntaxCodeSource().setBackground(new Color(237, 237, 237));
            p.getrSyntaxCodeSource().repaint();
        }

    }

    public void setActif() {
        this.actif = true;

        for(int compteur = 0; compteur < this.getTabCount(); ++compteur) {
            Editeur.Page p = (Editeur.Page)this.getComponentAt(compteur);
            p.getrSyntaxCodeSource().setEditable(true);
            p.getrSyntaxCodeSource().setSyntaxEditingStyle("text/java");
            p.getrSyntaxCodeSource().setBackground(Color.white);
            p.getrSyntaxCodeSource().repaint();
        }

    }

    public Fenetre getFenetre() {
        return this.fenetre;
    }

    public class Page extends JPanel implements ObservateurFichier {
        private Fichier fichier;
        private MonRSyntaxTextArea rSyntaxCodeSource;
        private RTextScrollPane sp;

        public MonRSyntaxTextArea getrSyntaxCodeSource() {
            return this.rSyntaxCodeSource;
        }

        public Page(Fichier fichier, Editeur editeur) {
            this.fichier = fichier;
            this.setLayout(new BorderLayout());
            JPanel panel = new JPanel(new BorderLayout());
            this.rSyntaxCodeSource = new MonRSyntaxTextArea(fichier);
            float version = H3Commun.getJavaVersion();
            if ((double)version <= 1.8D) {
                H3Etudiant.getLogger().info("JRE (" + version + ") Complétion automatique possible en java");
                LanguageSupportFactory lsf = LanguageSupportFactory.get();
                if (fichier.getSyntaxStyle().equals("text/java")) {
                    LanguageSupport support = lsf.getSupportFor("text/java");
                    JavaLanguageSupport jls = (JavaLanguageSupport)support;

                    try {
                        jls.getJarManager().addCurrentJreClassFileSource();
                    } catch (IOException var10) {
                        var10.printStackTrace();
                    }
                } else {
                    lsf.getSupportFor(fichier.getSyntaxStyle());
                }

                if (H3Etudiant.isAutoCompletion()) {
                    LanguageSupportFactory.get().register(this.rSyntaxCodeSource);
                }
            }

            this.rSyntaxCodeSource.requestFocusInWindow();
            this.rSyntaxCodeSource.setMarkOccurrences(true);
            this.rSyntaxCodeSource.setCodeFoldingEnabled(true);
            this.rSyntaxCodeSource.setTabSize(4);
            ToolTipManager.sharedInstance().registerComponent(this.rSyntaxCodeSource);
            this.sp = new RTextScrollPane(this.rSyntaxCodeSource, true);
            this.sp.setIconRowHeaderEnabled(true);
            rSyntaxTextAreaTools.setColorRSyntaxTextArea(this.sp, this.rSyntaxCodeSource);
            panel.add(this.sp, "Center");
            this.add(panel, "Center");
        }

        public void marquerLaLigne(int n) {
            try {
                int position = this.rSyntaxCodeSource.getLineStartOffset(n - 1);
                if (position == 0) {
                    ++position;
                }

                this.rSyntaxCodeSource.setCaretPosition(position);
                this.rSyntaxCodeSource.setCurrentLineHighlightColor(H3Etudiant.COULEUR_ERREUR);
            } catch (BadLocationException var3) {
                var3.printStackTrace();
            }

        }

        public Fichier getFichier() {
            return this.fichier;
        }

        public void enregistrementFichier() {
            JTabbedPane editeur = (JTabbedPane)this.getParent();
            int index = 0;

            for(int i = 0; i < editeur.getComponentCount(); ++i) {
                if ((Editeur.Page)editeur.getComponentAt(i) == this) {
                    index = i;
                }
            }

            String titre = this.fichier.getNom();
            editeur.setTitleAt(index, titre);
        }

        public void modificationFichier() {
        }

        public void couper() {
            this.rSyntaxCodeSource.cut();
        }

        public void coller() {
            this.rSyntaxCodeSource.paste();
        }

        public void copier() {
            this.rSyntaxCodeSource.copy();
        }

        public void selectionnerTout() {
            this.rSyntaxCodeSource.selectAll();
        }

        public String getSelection() {
            return this.rSyntaxCodeSource.getSelectedText();
        }
    }

    private class IconeFermeture extends ImageIcon {
        private Rectangle bounds;

        public IconeFermeture(Editeur.Page page) {
            super(((ImageIcon)Icones.FERMER_16).getImage());
        }

        public Rectangle getBounds() {
            return this.bounds;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            super.paintIcon(c, g, x, y);
            this.bounds = new Rectangle(x, y, this.getIconWidth(), this.getIconHeight());
        }
    }
}
