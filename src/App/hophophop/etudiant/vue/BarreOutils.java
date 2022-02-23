//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue;

import hophophop.commun.vue.Icones;
import hophophop.etudiant.modele.H3Etudiant;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.SearchContext;
import org.fife.ui.rtextarea.SearchEngine;
import org.fife.ui.rtextarea.SearchResult;

public class BarreOutils extends JToolBar implements ActionListener {
    private JComboBox comboSessions;
    private JTextField searchField;
    private JCheckBox regexCB;
    private JCheckBox matchCaseCB;
    private JTextField replaceField;
    private JButton replaceButton;
    private JCheckBox autoCompletion;

    public BarreOutils() {
        this.add(Actions.NOUVEAU_PROJET);
        this.add(Actions.NOUVEAU_FICHIER);
        this.addSeparator();
        this.add(Actions.IMPORTERREPERTOIRE);
        Actions.IMPORTERREPERTOIRE.setEnabled(false);
        this.addSeparator();
        this.addSeparator();
        this.add(Actions.CHANGERDESESSION);
        this.addSeparator();
        this.addSeparator();
        this.add(Actions.UNDO);
        this.add(Actions.REDO);
        this.addSeparator();
        this.add(Actions.DECALERAGAUCHE);
        this.add(Actions.DECALERADROITE);
        this.addSeparator();
        this.add(Actions.COMMENTERCODE);
        this.add(Actions.DECOMMENTERCODE);
        this.addSeparator();
        this.add(Actions.EFFACER_CONSOLE);
        this.addSeparator();
        this.addSeparator();
        this.add(Actions.COMPILER);
        this.add(Actions.EXECUTER);
        this.add(Actions.TEST_UNITAIRE);
        this.addSeparator();
        this.addSeparator();
        this.searchField = new JTextField(20);
        this.searchField.setMaximumSize(new Dimension(200, 30));
        this.searchField.setToolTipText("Ce qu'on cherche");
        this.add(this.searchField);
        this.addSeparator();
        final JButton nextButton = new JButton();
        nextButton.setIcon(Icones.RECHERCHE_AVANT_32);
        nextButton.setActionCommand("FindNext");
        nextButton.addActionListener(this);
        nextButton.setToolTipText("Chercher en avant");
        this.add(nextButton);
        this.searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextButton.doClick(0);
            }
        });
        JButton prevButton = new JButton();
        prevButton.setIcon(Icones.RECHERCHE_ARRIERE_32);
        prevButton.setToolTipText("Chercher en Arrière");
        prevButton.setActionCommand("FindPrev");
        prevButton.addActionListener(this);
        this.add(prevButton);
        this.addSeparator();
        this.addSeparator();
        this.replaceField = new JTextField(20);
        this.replaceField.setMaximumSize(new Dimension(200, 30));
        this.replaceField.setToolTipText("Le texte de remplacement");
        this.add(this.replaceField);
        this.replaceButton = new JButton();
        this.replaceButton.setIcon(Icones.REMPLACE_32);
        this.replaceButton.setActionCommand("Replace");
        this.replaceButton.setToolTipText("Remplacer toutes les occurences");
        this.replaceButton.addActionListener(this);
        this.addSeparator();
        this.add(this.replaceButton);
        this.addSeparator();
        this.addSeparator();
        this.regexCB = new JCheckBox("Regex");
        this.regexCB.setToolTipText("Utiliser les Expressions Régulières dans la recherche");
        this.add(this.regexCB);
        this.matchCaseCB = new JCheckBox("Casse");
        this.matchCaseCB.setToolTipText("Respect de la casse");
        this.add(this.matchCaseCB);
        this.addSeparator();
        this.addSeparator();
        this.autoCompletion = new JCheckBox("Completion automatique");
        this.autoCompletion.setToolTipText("Activer la completion automatique (Java/C)");
        this.autoCompletion.setSelected(H3Etudiant.isAutoCompletion());
        this.autoCompletion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                H3Etudiant.getFenetre().getEditeur().fermerTout();
                H3Etudiant.setAutoCompletion(BarreOutils.this.autoCompletion.isSelected());
                H3Etudiant.getLogger().info("Autocompletion : " + BarreOutils.this.autoCompletion.isSelected());
            }
        });
        this.add(this.autoCompletion);
    }

    public void setActive() {
        Component[] var1 = this.getComponents();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Component c = var1[var3];
            c.addNotify();
        }

        this.setToolTipText("");
        this.setCursor(new Cursor(0));
    }

    public void setInactive(String message) {
        Component[] var2 = this.getComponents();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Component c = var2[var4];
            c.removeNotify();
        }

        this.setToolTipText(message);
        this.setCursor(new Cursor(3));
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        SearchContext context = new SearchContext();
        String text = this.searchField.getText();
        if (text.length() != 0) {
            context.setSearchFor(text);
            context.setMatchCase(this.matchCaseCB.isSelected());
            context.setRegularExpression(this.regexCB.isSelected());
            context.setWholeWord(false);
            RSyntaxTextArea textArea = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource();
            boolean found = true;
            SearchResult sr;
            if (!"FindNext".equals(command) && !"FindPrev".equals(command)) {
                String text2 = this.replaceField.getText();
                context.setReplaceWith(text2);
                sr = SearchEngine.replaceAll(textArea, context);
                int nombre = sr.getCount();
                H3Etudiant.getLogger().info("Nombre de remplacement effectué " + nombre);
            } else {
                boolean forward = "FindNext".equals(command);
                context.setSearchForward(forward);
                sr = SearchEngine.find(textArea, context);
                found = sr.wasFound();
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "Texte non trouvé");
            }

        }
    }
}
