//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue.dialogues;

import hophophop.commun.modele.H3Commun;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class DialogueReceptionMessage extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();

    public DialogueReceptionMessage(String message, String expediteur) {
        this.setTitle("Nouveau message");
        this.setBounds(100, 100, 450, 450);
        this.setLocationRelativeTo((Component)null);
        this.getContentPane().setLayout(new BorderLayout());
        this.contentPanel.setLayout(new BorderLayout());
        this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.getContentPane().add(this.contentPanel, "Center");
        JTextPane txtMessage = new JTextPane();
        txtMessage.setFont(H3Commun.LaFonteMessage);
        txtMessage.setText(message);
        txtMessage.setEditable(false);
        JScrollPane jsp = new JScrollPane(txtMessage);
        this.contentPanel.add(jsp, "Center");
        JPanel buttonPane = new JPanel();
        this.getContentPane().add(buttonPane, "South");
        buttonPane.setLayout(new GridLayout(0, 3, 0, 0));
        JLabel lblNewLabel = new JLabel("De : " + expediteur);
        lblNewLabel.setHorizontalAlignment(0);
        buttonPane.add(lblNewLabel);
        JButton repondreButton = new JButton("Répondre");
        buttonPane.add(repondreButton);
        JButton okButton = new JButton("Fermer");
        buttonPane.add(okButton);
        this.getRootPane().setDefaultButton(okButton);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DialogueReceptionMessage.this.setVisible(false);
            }
        });
        repondreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DialogueReceptionMessage.this.setVisible(false);
            }
        });
        this.setDefaultCloseOperation(2);
        this.setVisible(true);
    }
}
