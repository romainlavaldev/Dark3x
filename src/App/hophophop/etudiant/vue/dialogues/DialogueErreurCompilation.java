//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue.dialogues;

import hophophop.commun.modele.Evenement;
import hophophop.commun.vue.Icones;
import hophophop.etudiant.modele.H3Etudiant;
import hophophop.etudiant.modele.projet.Erreur;
import hophophop.etudiant.vue.Console;
import hophophop.etudiant.vue.Editeur;
import hophophop.etudiant.vue.SWMarquerLigne;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class DialogueErreurCompilation extends JDialog {
    private JButton boutonCorriger;
    private Console console;
    private static final int TailleFont = 10;

    public DialogueErreurCompilation(JFrame fenetre, final List<Erreur> listeErreur, final Editeur editeur, String sortieDuCompilateur) {
        super(fenetre, "Liste des erreurs de Compilation", true);
        this.setDefaultCloseOperation(2);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
            }
        });

        class TableauErreurModel extends AbstractTableModel {
            public String[] nomColonnes = new String[]{"Fichier", "Ligne", "Message"};
            public List<List<String>> donnees = new ArrayList();

            public TableauErreurModel(List<Erreur> listeErreur) {
                this.initialiser(listeErreur);
            }

            private void initialiser(List<Erreur> listeErreur) {
                Iterator var3 = listeErreur.iterator();

                while(var3.hasNext()) {
                    Erreur er = (Erreur)var3.next();
                    List<String> ligne = new ArrayList();
                    ligne.add(er.getLeFichier().getNom());
                    ligne.add(Integer.toString(er.getNumeroDeLigne()));
                    ligne.add(er.getMessageErreur() + "\n" + er.getMessageErreurComplement());
                    this.donnees.add(ligne);
                }

            }

            public int getColumnCount() {
                return this.nomColonnes.length;
            }

            public int getRowCount() {
                return this.donnees.size();
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                return ((List)this.donnees.get(rowIndex)).get(columnIndex);
            }

            public String getColumnName(int col) {
                return this.nomColonnes[col];
            }

            public Class getColumnClass(int col) {
                return this.getValueAt(0, col).getClass();
            }
        }

        TableauErreurModel modele = new TableauErreurModel(listeErreur);
        final JTable tableau = new JTable(modele);
        JPanel panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(new BorderLayout());
        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(tableau);
        jsp.setPreferredSize(new Dimension(700, 150));
        JPanel panelBouton = new JPanel(new BorderLayout());
        this.boutonCorriger = new JButton("Fermer");
        this.boutonCorriger.setIcon(Icones.ERREUR_32);
        this.boutonCorriger.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (tableau.getSelectedRow() != -1) {
                    (new SWMarquerLigne(editeur, (Erreur)listeErreur.get(tableau.getSelectedRow()))).execute();
                    Erreur errSel = (Erreur)listeErreur.get(tableau.getSelectedRow());
                    Evenement evenement = Evenement.getEvenementSelectionErreur(editeur.getPageActuelle().getFichier().getProjet().getNom(), errSel.getLeFichier().getNom(), errSel.getNumeroDeLigne(), errSel.getMessageErreur() + errSel.getMessageErreurComplement());
                    H3Etudiant.getTraceur().envoyer(evenement);
                }

                DialogueErreurCompilation.this.setVisible(false);
                DialogueErreurCompilation.this.dispose();
            }
        });
        panelBouton.add(this.boutonCorriger, "Center");
        panelBouton.setPreferredSize(new Dimension(700, 50));
        tableau.setShowGrid(true);
        tableau.setRowHeight(20);
        tableau.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                (new SWMarquerLigne(editeur, (Erreur)listeErreur.get(tableau.getSelectedRow()))).execute();
            }
        });
        TableColumn col0 = tableau.getColumnModel().getColumn(0);
        TableColumn col1 = tableau.getColumnModel().getColumn(1);
        TableColumn col2 = tableau.getColumnModel().getColumn(2);
        col0.setPreferredWidth(100);
        col1.setPreferredWidth(25);
        col2.setPreferredWidth(600);
        tableau.setGridColor(Color.LIGHT_GRAY);
        RendererError rendererCol0 = new RendererError();
        tableau.setDefaultRenderer(String.class, rendererCol0);
        jsp.setPreferredSize(new Dimension(650, 100));
        panel.add(jsp, "Center");
        tableau.setSelectionMode(0);
        panel.add(panelBouton, "South");
        JPanel panelConsole = new JPanel(new BorderLayout());
        panelConsole.setBorder(BorderFactory.createTitledBorder((Border)null, "Sortie du compilateur", 2, 0, new Font("Comic Sans MS", 0, 10)));
        JScrollPane jsp1 = new JScrollPane();
        this.console = new Console((Editeur)null);
        jsp1.setViewportView(this.console);
        panelConsole.add(jsp1);
        this.console.ecrire(sortieDuCompilateur);
        panelConsole.setPreferredSize(new Dimension(650, 100));
        this.setSize(1200, 200);
        this.setResizable(true);
    }
}
