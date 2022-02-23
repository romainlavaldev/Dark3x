//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.vue.dialogues;

import hophophop.commun.vue.Icones;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class DialogueAfficherEnonce extends Dialogue {
    public DialogueAfficherEnonce(JFrame fenetre, String titre, List<String> enonce) {
        super(fenetre, titre);
        String enonceHtml = new String();
        enonceHtml = enonceHtml + (String)enonce.get(0);
        enonceHtml = enonceHtml + "<ol>";

        String q;
        for(Iterator var5 = enonce.subList(1, enonce.size()).iterator(); var5.hasNext(); enonceHtml = enonceHtml + "<li>" + q + "</li>") {
            q = (String)var5.next();
        }

        enonceHtml = enonceHtml + "<ol>";
        JPanel panel = new JPanel();
        JEditorPane txtQuestion = new JEditorPane();
        JScrollPane jScrollPaneTxtQuestions = new JScrollPane();
        jScrollPaneTxtQuestions.setBackground((Color)null);
        jScrollPaneTxtQuestions.setOpaque(true);
        jScrollPaneTxtQuestions.setBorder((Border)null);
        txtQuestion.setContentType("text/html");
        txtQuestion.setEditable(false);
        txtQuestion.setText(enonceHtml);
        txtQuestion.setCaretPosition(0);
        jScrollPaneTxtQuestions.setPreferredSize(new Dimension(800, 500));
        jScrollPaneTxtQuestions.setViewportView(txtQuestion);
        panel.add(jScrollPaneTxtQuestions);
        this.setPanel(panel);
        JButton boutonOk = new JButton("Fermer");
        boutonOk.setIcon(Icones.FERMER_16);
        boutonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueAfficherEnonce.this.dialogue.setVisible(false);
            }
        });
        this.ajouterBouton(boutonOk);
    }
}
