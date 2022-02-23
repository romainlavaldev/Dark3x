//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue;

import hophophop.commun.modele.Evenement;
import hophophop.etudiant.modele.H3Etudiant;
import hophophop.etudiant.modele.fichier.Fichier;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class MonRSyntaxTextArea extends RSyntaxTextArea {
    private int positionDebut;
    private int positionFin;

    public int getPositionDebut() {
        return this.positionDebut;
    }

    public int getPositionFin() {
        return this.positionFin;
    }

    public void setPositionDebut(int positionDebutLigne) {
        this.positionDebut = positionDebutLigne;
    }

    public void setPositionFin(int i) {
        this.positionFin = i;
    }

    public MonRSyntaxTextArea(RSyntaxDocument rSyntaxDocument) {
        super(rSyntaxDocument);
        AutoCompletion ac = new AutoCompletion(H3Etudiant.getProvider());
        ac.install(this);
        this.setEditable(true);
        this.setDocument(rSyntaxDocument);
    }

    public MonRSyntaxTextArea(Fichier fichier) {
        this(fichier.getSource());
        this.setSyntaxEditingStyle(fichier.getSyntaxStyle());
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                MonRSyntaxTextArea.this.positionDebut = MonRSyntaxTextArea.this.getCaretPosition();
            }

            public void mouseReleased(MouseEvent e) {
                MonRSyntaxTextArea.this.positionFin = MonRSyntaxTextArea.this.getCaretPosition();
            }
        });
        if (H3Etudiant.isSessionLectureSeule()) {
            this.setEditable(false);
        }

    }

    public void copy() {
        super.copy();
        if (this.getSelectedText() != null) {
            Clipboard clipboard = this.getToolkit().getSystemClipboard();
            String selection = this.getSelectedText();
            Evenement evenement = Evenement.getEvenementCopierTexte(selection);
            H3Etudiant.getTraceur().envoyer(evenement);
            clipboard.setContents(new StringSelection(selection), (ClipboardOwner)null);
        }

    }

    public void cut() {
        if (this.isEditable()) {
            Evenement evenement = Evenement.getEvenementCouperTexte();
            H3Etudiant.getTraceur().envoyer(evenement);
            super.cut();
        }

    }

    public void paste() {
        if (this.isEditable()) {
            Clipboard clipboard = this.getToolkit().getSystemClipboard();

            try {
                String selection = ((String)clipboard.getContents(this).getTransferData(DataFlavor.stringFlavor)).replace('\r', '\n');
                Evenement evenement = Evenement.getEvenementCollerTexte(selection);
                H3Etudiant.getTraceur().envoyer(evenement);
            } catch (UnsupportedFlavorException var4) {
                var4.printStackTrace();
            } catch (IOException var5) {
                var5.printStackTrace();
            }

            super.paste();
        }

    }
}
