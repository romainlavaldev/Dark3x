//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class Consigne extends JFrame {
    private JEditorPane consigne;
    private Fenetre fenetre;

    public Consigne(String texte, final Fenetre fenetre, boolean question, final JButton btnRevoirConsigne, JButton btnRevoirHistorique) throws IOException {
        this.fenetre = fenetre;
        this.setLayout(new BorderLayout(10, 10));
        if (texte.indexOf(".pdf") == -1) {
            this.consigne = new JEditorPane();
            JScrollPane jspConsigne = new JScrollPane(this.consigne);
            this.consigne.setBackground(Fenetre.FOND_GRIS);
            this.consigne.setContentType("text/html");
            this.consigne.setText(texte);
            jspConsigne.setBorder((Border)null);
            this.add(jspConsigne, "Center");
            this.setPreferredSize(new Dimension(700, 500));
            this.consigne.setCaretPosition(0);
            this.setResizable(false);
        }

        JButton bouton = new JButton("Fermer la fenêtre de consigne");
        this.add(bouton, "South");
        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fenetre.isInterfaceActive()) {
                    fenetre.activerInterface();
                } else {
                    fenetre.desactiverInterface();
                }

                btnRevoirConsigne.setEnabled(true);
                Consigne.this.setVisible(false);
            }
        });
    }

    void afficher() {
        this.pack();
        this.setLocationRelativeTo(this.fenetre);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }
}
