//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.dialogues;

import hophophop.commun.vue.Icones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DialogueExecuter extends Dialogue {
    private JButton boutonOk;
    private JComboBox combo;

    public DialogueExecuter(JFrame fenetre, List<String> classes) {
        super(fenetre, "Executer", Icones.DEMARRER_32);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        this.setPanel(panel);
        panel.add(new JLabel("Choisissez :"));
        panel.add(Box.createVerticalStrut(5));
        this.combo = new JComboBox();
        Iterator var4 = classes.iterator();

        while(var4.hasNext()) {
            String classe = (String)var4.next();
            this.combo.addItem(classe);
        }

        this.combo.setSelectedIndex(0);
        panel.add(this.combo);

        for(int i = 0; i < panel.getComponentCount(); ++i) {
            ((JComponent)panel.getComponent(i)).setAlignmentX(0.0F);
        }

        JButton boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.setIcon(Icones.ANNULER_16);
        boutonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueExecuter.this.dialogue.setVisible(false);
            }
        });
        this.ajouterBouton(boutonAnnuler);
        this.boutonOk = new JButton("Valider");
        this.boutonOk.setIcon(Icones.OK_16);
        this.boutonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueExecuter.this.resultat = 2;
                DialogueExecuter.this.dialogue.setVisible(false);
            }
        });
        this.ajouterBouton(this.boutonOk);
    }

    public String getClasse() {
        return (String)this.combo.getSelectedItem();
    }
}
