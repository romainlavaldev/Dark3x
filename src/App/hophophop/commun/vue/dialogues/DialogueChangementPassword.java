//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.dialogues;

import hophophop.commun.vue.Icones;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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

public class DialogueChangementPassword extends Dialogue {
    private JButton boutonOk;
    private JLabel lblOldPassword;
    private JPasswordField newPassword;
    private JPasswordField confNewPassword;
    private JPasswordField oldPassword;

    public String getNewPassword() {
        return new String(this.newPassword.getPassword());
    }

    public String getOldPassword() {
        return new String(this.oldPassword.getPassword());
    }

    public DialogueChangementPassword(JFrame fenetre, String title, final String username) {
        super(fenetre, title, Icones.UTILISATEUR_48);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        this.setPanel(panel);
        this.lblOldPassword = new JLabel("Ancien Mot de Passe :");
        panel.add(this.lblOldPassword);
        this.oldPassword = new JPasswordField();
        this.oldPassword.setText("");
        this.oldPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueChangementPassword.this.boutonOk.doClick();
            }
        });
        panel.add(this.oldPassword);
        panel.add(new JLabel("Nouveau Mot de Passe"));
        final JLabel consigne = new JLabel("Au moins 6 caractères avec au minimum un Chiffre");
        consigne.setFont(new Font("Comic Sans MS", 0, 10));
        panel.add(consigne);
        this.newPassword = new JPasswordField();
        this.newPassword.setText("");
        this.newPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            }
        });
        panel.add(this.newPassword);
        panel.add(new JLabel("Confirmation Mot de Passe"));
        this.confNewPassword = new JPasswordField();
        this.confNewPassword.setText("");
        this.confNewPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueChangementPassword.this.boutonOk.doClick();
            }
        });
        panel.add(this.confNewPassword);
        final JButton boutonAnnuler = new JButton("Annuler", Icones.ANNULER_16);
        boutonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueChangementPassword.this.oldPassword.setText("");
                DialogueChangementPassword.this.newPassword.setText("");
                DialogueChangementPassword.this.dialogue.setVisible(false);
            }
        });
        this.ajouterBouton(boutonAnnuler);
        this.boutonOk = new JButton("Valider", Icones.OK_16);
        this.boutonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String nouveau = new String(DialogueChangementPassword.this.newPassword.getPassword());
                String ancien = new String(DialogueChangementPassword.this.oldPassword.getPassword());
                if (nouveau.equals(ancien)) {
                    JOptionPane.showMessageDialog((Component)null, "Vous ne pouvez pas remettre le même mot de passe", "Erreur", 0);
                } else if (nouveau.equals(username)) {
                    JOptionPane.showMessageDialog((Component)null, "Vous ne pouvez pas utiliser votre username comme mot de passe", "Erreur", 0);
                } else if (!nouveau.equals(new String(DialogueChangementPassword.this.confNewPassword.getPassword()))) {
                    JOptionPane.showMessageDialog((Component)null, "Le mot de passe ne correspond pas à la confirmation", "Erreur", 0);
                } else {
                    int longueur = nouveau.length();
                    int digit = 0;
                    int majuscule = 0;

                    for(int i = 0; i < longueur; ++i) {
                        if (Character.isDigit(nouveau.charAt(i))) {
                            ++digit;
                        }

                        if (Character.isUpperCase(nouveau.charAt(i))) {
                            ++majuscule;
                        }
                    }

                    if (longueur >= 6 && digit >= 1) {
                        if (JOptionPane.showConfirmDialog((Component)null, "Etes vous sûr de vouloir changer de mot de passe ?", "Confirmamtion", 2) == 0) {
                            DialogueChangementPassword.this.resultat = 2;
                            DialogueChangementPassword.this.dialogue.setVisible(false);
                        } else {
                            boutonAnnuler.doClick();
                        }
                    } else {
                        consigne.setForeground(Color.RED);
                    }
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
