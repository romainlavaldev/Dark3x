//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue.dialogues;

import hophophop.etudiant.modele.H3Etudiant;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RendererError extends JLabel implements TableCellRenderer {
    public RendererError() {
        this.setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            this.setBackground(H3Etudiant.COULEUR_ERREUR);
        } else {
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }

        if (column == 1) {
            this.setHorizontalAlignment(0);
        } else {
            this.setHorizontalAlignment(2);
        }

        this.setText((String)value);
        return this;
    }
}
