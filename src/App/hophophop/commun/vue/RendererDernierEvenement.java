//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue;

import hophophop.commun.modele.H3Commun;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RendererDernierEvenement extends DefaultTableCellRenderer {
    public RendererDernierEvenement() {
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        long dernier = 0L;
        if (value != null) {
            dernier = (Long)value;
        }

        long maintenant = System.currentTimeMillis();
        Date d = new Date(dernier);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        formatter.format(d);
        this.setForeground(Color.BLACK);
        long millis = maintenant - dernier;
        String heureEvent = String.format("%dm:%ds", TimeUnit.MILLISECONDS.toMinutes(millis), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        super.getTableCellRendererComponent(table, heureEvent, isSelected, hasFocus, row, column);
        if (maintenant - dernier > H3Commun.getInactivite()) {
            super.setForeground(Color.RED);
        }

        return this;
    }
}
