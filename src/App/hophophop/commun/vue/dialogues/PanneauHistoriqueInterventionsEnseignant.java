//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.dialogues;

import hophophop.commun.modele.Evenement;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class PanneauHistoriqueInterventionsEnseignant extends JScrollPane {
    public PanneauHistoriqueInterventionsEnseignant(List<Evenement> vector, String border, boolean enseignant) {
        int cpt = 0;
        String ligne = "==================================================================\n";
        String ligne1 = "  -------------------------------------------------------------------------------\n";
        StringBuffer aAfficher = new StringBuffer();
        Iterator var8 = vector.iterator();

        while(var8.hasNext()) {
            Evenement s = (Evenement)var8.next();
            String date = DateFormat.getInstance().format(new Date(s.getTemps()));
            new String();
            String intervention;
            if (s.getType().equals("HELP")) {
                intervention = ligne + " " + date + "\tAPPEL A L'AIDE de " + s.get("ETUDIANT") + "\n\t\tMotif : " + s.get("MOTIF") + "\n";
            } else {
                String message = s.get("MESSAGE");
                ++cpt;
                intervention = ligne + " " + date + "\tMessage N°" + cpt + " de " + s.get("ENSEIGNANT") + "\n";
                if (enseignant) {
                    intervention = intervention + "\t\t\tpour " + s.get("ETUDIANT");
                    if (s.get("INDICATEURS") != null) {
                        String indicateurs = s.get("INDICATEURS").replaceAll("\\[[\\/]*INTERVENTION TEXTE\\]", "").replaceAll("\\[[\\/]INTERVENTION AUDIO\\]", "\n").replaceAll("[\\[\\/]", "").replace(']', '\n');
                        intervention = intervention + "\n" + ligne1 + indicateurs;
                    } else {
                        intervention = intervention + "\n" + ligne1 + "\t\tPAS D'INDICATEUR SELECTIONNE\n";
                    }
                }

                intervention = intervention + ligne1;
                if (message != null) {
                    intervention = intervention + "  " + message;
                } else {
                    intervention = intervention + "\tPAS DE MESSAGE";
                }

                intervention = intervention + "\n";
            }

            intervention = intervention + ligne + "\n";
            aAfficher.insert(0, intervention);
        }

        JTextArea jtHistorique = new JTextArea(aAfficher.toString());
        jtHistorique.setRows(20);
        jtHistorique.setColumns(30);
        jtHistorique.setEditable(false);
        jtHistorique.setLineWrap(true);
        jtHistorique.setCaretPosition(0);
        this.setViewportView(jtHistorique);
        this.setPreferredSize(new Dimension(700, 250));
        this.setBorder(BorderFactory.createTitledBorder((Border)null, border, 2, 0, new Font("Comic Sans MS", 0, 12)));
    }
}
