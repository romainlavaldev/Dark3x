//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue.dialogues;

import hophophop.etudiant.vue.Actions;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InvitationVideo extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();

    public InvitationVideo(String message, final String adresse) {
        this.setTitle("Invitation vidéo");
        this.setBounds(100, 100, 450, 130);
        this.setLocationRelativeTo((Component)null);
        this.getContentPane().setLayout(new BorderLayout());
        this.contentPanel.setLayout(new BorderLayout());
        this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.getContentPane().add(this.contentPanel, "Center");
        JEditorPane txtMessage = new JEditorPane();
        txtMessage.setContentType("text/html");
        txtMessage.setText(message);
        txtMessage.setEditable(false);
        this.contentPanel.add(txtMessage, "Center");
        JPanel buttonPane = new JPanel();
        this.getContentPane().add(buttonPane, "South");
        buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
        JButton cancelButton = new JButton("Lancer la Vidéo");
        buttonPane.add(cancelButton);
        this.getRootPane().setDefaultButton(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Actions.demarrerVideo(adresse);
                InvitationVideo.this.setVisible(false);
            }
        });
        cancelButton = new JButton("Fermer");
        buttonPane.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InvitationVideo.this.setVisible(false);
            }
        });
        this.setDefaultCloseOperation(2);
        this.setVisible(true);
    }
}
