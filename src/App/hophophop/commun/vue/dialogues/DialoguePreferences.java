//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.dialogues;

import hophophop.commun.vue.Icones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class DialoguePreferences extends Dialogue {
    private JTextField champAdresse;
    private String regex = "(([0-1]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])[.]){3}([0-1]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";

    public DialoguePreferences(JFrame fenetre, String adresse) {
        super(fenetre, "Preferences", Icones.PREFERENCES_32);
        JButton boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.setIcon(Icones.ANNULER_16);
        boutonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialoguePreferences.this.dialogue.setVisible(false);
            }
        });
        this.ajouterBouton(boutonAnnuler);
        final JButton boutonOk = new JButton("Valider");
        boutonOk.setIcon(Icones.OK_16);
        boutonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialoguePreferences.this.resultat = 2;
                DialoguePreferences.this.dialogue.setVisible(false);
            }
        });
        this.ajouterBouton(boutonOk);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        this.setPanel(panel);
        panel.add(new JLabel("Adresse du serveur :"));
        panel.add(Box.createVerticalStrut(5));
        this.champAdresse = new JTextField(adresse);
        this.champAdresse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boutonOk.doClick();
            }
        });
        this.champAdresse.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent arg0) {
                boutonOk.setEnabled(Pattern.matches(DialoguePreferences.this.regex, DialoguePreferences.this.champAdresse.getText()));
            }
        });
        panel.add(this.champAdresse);
    }

    public String getAdresseServeur() {
        return this.champAdresse.getText();
    }
}
