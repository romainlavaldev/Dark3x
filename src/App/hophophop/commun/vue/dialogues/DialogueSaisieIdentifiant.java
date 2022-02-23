//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.dialogues;

import hophophop.commun.vue.Icones;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DialogueSaisieIdentifiant extends Dialogue {
    private JButton boutonOk;
    private JLabel lblUserName;
    private JPasswordField txtPassword;
    private JTextField txtUserName;

    public String getTxtPassword() {
        return new String(this.txtPassword.getPassword());
    }

    public String getTxtUserName() {
        return this.txtUserName.getText();
    }

    public DialogueSaisieIdentifiant(final JFrame fenetre, String type) {
        super(fenetre, type, Icones.UTILISATEUR_48);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        this.setPanel(panel);
        this.lblUserName = new JLabel("Nom d'Utilisateur");
        panel.add(this.lblUserName);
        this.txtUserName = new JTextField();
        this.txtUserName.setText("");
        this.txtUserName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueSaisieIdentifiant.this.boutonOk.doClick();
            }
        });
        panel.add(this.txtUserName);
        panel.add(new JLabel("Mot de Passe"));
        this.txtPassword = new JPasswordField();
        this.txtPassword.setText("");
        this.txtPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueSaisieIdentifiant.this.boutonOk.doClick();
            }
        });
        panel.add(this.txtPassword);
        final JButton boutonAnnuler = new JButton("Annuler", Icones.ANNULER_16);
        boutonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (JOptionPane.showConfirmDialog(fenetre, "Etes vous sur de vouloir quitter ?", "Attention !!", 2, 2, Icones.INFO_64) == 0) {
                    System.exit(0);
                }

            }
        });
        this.ajouterBouton(boutonAnnuler);
        this.boutonOk = new JButton("Valider", Icones.OK_16);
        this.boutonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (!DialogueSaisieIdentifiant.this.txtPassword.getText().equals("") && !DialogueSaisieIdentifiant.this.txtUserName.getText().equals("")) {
                    DialogueSaisieIdentifiant.this.resultat = 2;
                    DialogueSaisieIdentifiant.this.dialogue.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog((Component)null, "Vous devez Donner un Nom d'Utilisateur et un Mot de passe", " Erreur !!!", 0);
                }

            }
        });
        this.ajouterBouton(this.boutonOk);
        this.dialogue.setDefaultCloseOperation(0);
        this.dialogue.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                boutonAnnuler.doClick();
            }
        });
    }
}
