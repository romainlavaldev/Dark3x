//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.dialogues;

import hophophop.commun.modele.Evenement;
import hophophop.commun.vue.Icones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DialogueHistoriqueInterventionsEnseignant extends Dialogue {
    public DialogueHistoriqueInterventionsEnseignant(JFrame fenetre, String titre, List<Evenement> vector, boolean enseignant) {
        super(fenetre, "Historique des Messages Enseignants");
        JPanel panel = new JPanel();
        String titreBorder = " " + vector.size() + " " + titre;
        if (vector.size() > 1) {
            titreBorder = titreBorder + "s ";
        }

        panel.add(new PanneauHistoriqueInterventionsEnseignant(vector, titreBorder, enseignant));
        this.setPanel(panel);
        JButton boutonOk = new JButton("Fermer");
        boutonOk.setIcon(Icones.FERMER_16);
        boutonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueHistoriqueInterventionsEnseignant.this.dialogue.setVisible(false);
            }
        });
        this.ajouterBouton(boutonOk);
    }
}
