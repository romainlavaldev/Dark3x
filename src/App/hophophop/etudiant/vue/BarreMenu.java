//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue;

import hophophop.commun.modele.H3Commun;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class BarreMenu extends JMenuBar {
    private JMenuBar menuBase = new JMenuBar();
    private JMenu menuFichier = new JMenu("Fichier");
    private JMenu menuEdition;
    private JMenu menuOutils;
    private JMenu menuAssistance;
    private JMenu menu_C;
    private JMenu menu_NXC;
    private JMenu menu_Ruby;

    public JMenuBar getMenuBase() {
        return this.menuBase;
    }

    public BarreMenu() {
        this.menuFichier.setMnemonic('F');
        this.menuBase.add(this.menuFichier);
        this.add(this.menuFichier);
        this.menuFichier.add(Actions.NOUVEAU_PROJET);
        this.menuFichier.add(Actions.NOUVEAU_FICHIER);
        this.menuFichier.addSeparator();
        this.menuFichier.add(Actions.SUPPRIMER_PROJET);
        this.menuFichier.add(Actions.SUPPRIMER_FICHIER);
        this.menuFichier.addSeparator();
        this.menuFichier.add(Actions.IMPORTERREPERTOIRE);
        this.menuFichier.addSeparator();
        this.menuFichier.add(Actions.CHANGERDESESSION);
        this.menuFichier.addSeparator();
        this.menuFichier.add(Actions.CHANGERPASSWORD);
        this.menuFichier.addSeparator();
        this.menuFichier.add(Actions.QUITTER);
        this.menuEdition = new JMenu("Edition");
        this.menuEdition.setMnemonic('E');
        this.menuBase.add(this.menuEdition);
        this.add(this.menuEdition);
        this.menuEdition.add(Actions.COUPER);
        this.menuEdition.add(Actions.COPIER);
        this.menuEdition.add(Actions.COLLER);
        this.menuEdition.addSeparator();
        this.menuEdition.add(Actions.SELECTIONNER_TOUT);
        this.menuEdition.addSeparator();
        this.menuEdition.add(Actions.DECALERADROITE);
        this.menuEdition.add(Actions.DECALERAGAUCHE);
        this.menuEdition.addSeparator();
        this.menuEdition.add(Actions.COMMENTERCODE);
        this.menuEdition.add(Actions.DECOMMENTERCODE);
        this.menuEdition.addSeparator();
        this.menuEdition.add(Actions.FERMER);
        this.menuEdition.add(Actions.FERMER_TOUT);
        this.menuEdition.addSeparator();
        this.menuEdition.add(Actions.UNDO);
        this.menuEdition.add(Actions.REDO);
        this.menuEdition.addSeparator();
        this.menuEdition.add(Actions.EFFACER_CONSOLE);
        this.menuOutils = new JMenu("Outils");
        this.menuOutils.setMnemonic('O');
        this.menuBase.add(this.menuOutils);
        this.add(this.menuOutils);
        this.menuOutils.add(Actions.COMPILER);
        this.menuOutils.addSeparator();
        this.menuOutils.add(Actions.EXECUTER);
        this.menuOutils.add(Actions.LIGNEDECOMMANDE);
        this.menuOutils.addSeparator();
        this.menuOutils.add(Actions.OUVRIR_TERMINAL);
        this.menuOutils.addSeparator();
        this.menuOutils.add(Actions.TEST_UNITAIRE);
        this.menuOutils.addSeparator();
        this.menuOutils.addSeparator();
        this.menuOutils.add(Actions.RESTAURER);
        this.menuAssistance = new JMenu("Assistance");
        this.menuAssistance.setMnemonic('A');
        this.menuBase.add(this.menuAssistance);
        this.add(this.menuAssistance);
        this.menuAssistance.add(Actions.ACCEDERVIDEOINDIVIDUELLE);
        this.menuAssistance.add(Actions.ACCEDERVIDEOSESSION);
        this.menuAssistance.addSeparator();
        this.menuAssistance.add(Actions.MONTRERHISTORIQUEMESSAGE);
        this.menuAssistance.add(Actions.AUSECOURS);
        this.menu_C = new JMenu("Langage C");
        this.menu_C.add(Actions.COMPILATIONCOMMANDE_C);
        this.menu_C.add(Actions.EXECUTIONCOMMANDE_C);
        this.menu_C.add(Actions.MAKECIBLE_C);
        this.menu_NXC = new JMenu("Langage NxC");
        this.menu_NXC.add(Actions.MODESIMULATEUR);
        this.menu_NXC.add(Actions.OUVRIRLESIMULATEUR);
        this.menu_NXC.add(Actions.FERMERLESIMULATEUR);
        this.menu_NXC.add(Actions.ARRETERSIMULATEUR);
        this.menu_NXC.add(Actions.DEMARRERSIMULATEUR);
        this.menu_Ruby = new JMenu("Langage Ruby");
        this.menu_Ruby.add(Actions.NOUVEAUGLADE);
        this.menu_Ruby.addSeparator();
        this.menu_Ruby.add(Actions.LANCERGLADE);
        this.menu_Ruby.add(Actions.PRECISERCOMMANDERGLADE);
        this.menu_Ruby.addSeparator();
        this.menu_Ruby.add(Actions.PRECISERCOMMANDERUBY);
        this.setFont(H3Commun.LaFonteMenu);
        this.menu_Ruby.setFont(H3Commun.LaFonteMenu);
        Component[] var1 = this.menu_Ruby.getMenuComponents();
        int var2 = var1.length;

        int var3;
        Component mc;
        for(var3 = 0; var3 < var2; ++var3) {
            mc = var1[var3];
            mc.setFont(H3Commun.LaFonteMenu);
        }

        this.menu_C.setFont(H3Commun.LaFonteMenu);
        var1 = this.menu_C.getMenuComponents();
        var2 = var1.length;

        for(var3 = 0; var3 < var2; ++var3) {
            mc = var1[var3];
            mc.setFont(H3Commun.LaFonteMenu);
        }

        this.menuAssistance.setFont(H3Commun.LaFonteMenu);
        var1 = this.menuAssistance.getMenuComponents();
        var2 = var1.length;

        for(var3 = 0; var3 < var2; ++var3) {
            mc = var1[var3];
            mc.setFont(H3Commun.LaFonteMenu);
        }

        this.menuOutils.setFont(H3Commun.LaFonteMenu);
        var1 = this.menuOutils.getMenuComponents();
        var2 = var1.length;

        for(var3 = 0; var3 < var2; ++var3) {
            mc = var1[var3];
            mc.setFont(H3Commun.LaFonteMenu);
        }

        this.menuEdition.setFont(H3Commun.LaFonteMenu);
        var1 = this.menuEdition.getMenuComponents();
        var2 = var1.length;

        for(var3 = 0; var3 < var2; ++var3) {
            mc = var1[var3];
            mc.setFont(H3Commun.LaFonteMenu);
        }

        this.menuFichier.setFont(H3Commun.LaFonteMenu);
        var1 = this.menuFichier.getMenuComponents();
        var2 = var1.length;

        for(var3 = 0; var3 < var2; ++var3) {
            mc = var1[var3];
            mc.setFont(H3Commun.LaFonteMenu);
        }

    }

    public JMenu getMenu_Ruby() {
        return this.menu_Ruby;
    }

    public JMenu getMenu_C() {
        return this.menu_C;
    }

    public void setActif() {
        Component[] var1 = this.getComponents();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Component c = var1[var3];
            c.addNotify();
        }

        this.setToolTipText("");
        this.setCursor(new Cursor(0));
    }

    public void setInactif(String message) {
        Component[] var2 = this.getComponents();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Component c = var2[var4];
            c.removeNotify();
        }

        this.setToolTipText(message);
        this.setCursor(new Cursor(3));
    }

    public JMenu getMenu_NxC() {
        return this.menu_NXC;
    }
}
