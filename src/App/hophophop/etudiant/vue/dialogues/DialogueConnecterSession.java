//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue.dialogues;

import hophophop.commun.vue.Icones;
import hophophop.commun.vue.dialogues.Dialogue;
import hophophop.etudiant.modele.H3Etudiant;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class DialogueConnecterSession extends Dialogue {
    private String nomSessionSelectionnee;
    private JComboBox comboSessions;
    private JButton boutonOk;
    private JButton boutonAnnuler;
    private JButton boutonEffacerSession;

    public DialogueConnecterSession(String sessionActuelle) {
        super(H3Etudiant.getFenetre(), "Choix de la Session", Icones.CHOIXSESSION_48);
        final List<String> toutesLesSessionsPossibles = new ArrayList();
        toutesLesSessionsPossibles.addAll(H3Etudiant.getSessionsDemarrees());
        toutesLesSessionsPossibles.removeAll(H3Etudiant.getSessionsConnectées());
        toutesLesSessionsPossibles.remove(sessionActuelle);
        H3Etudiant.getLogger().info("Les SessionPossibles : " + toutesLesSessionsPossibles);
        H3Etudiant.getLogger().info("Les SessionDémarées : " + H3Etudiant.getSessionsConnectées());
        this.comboSessions = new JComboBox();
        Collections.sort(toutesLesSessionsPossibles, Collections.reverseOrder());
        Iterator var3 = toutesLesSessionsPossibles.iterator();

        while(var3.hasNext()) {
            String session = (String)var3.next();
            this.comboSessions.addItem(session);
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.setPanel(panel);
        if (!toutesLesSessionsPossibles.isEmpty()) {
            this.comboSessions.setSelectedIndex(0);
        }

        panel.add(this.comboSessions, "South");
        this.comboSessions.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    DialogueConnecterSession.this.boutonOk.doClick();
                }

            }
        });
        if (!H3Etudiant.getSessionsConnectées().isEmpty()) {
            this.boutonEffacerSession = new JButton("Il manque des Sessions", Icones.SESSION_16);
            this.boutonEffacerSession.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    Iterator var2 = H3Etudiant.getSessionsConnectées().iterator();

                    String session;
                    while(var2.hasNext()) {
                        session = (String)var2.next();

                        try {
                            H3Etudiant.forcerDeconnectionEtudiant(session, false);
                            toutesLesSessionsPossibles.add(session);
                        } catch (Exception var5) {
                            var5.printStackTrace();
                        }
                    }

                    H3Etudiant.getSessionsConnectées().clear();
                    DialogueConnecterSession.this.comboSessions.removeAllItems();
                    Collections.sort(toutesLesSessionsPossibles, Collections.reverseOrder());
                    var2 = toutesLesSessionsPossibles.iterator();

                    while(var2.hasNext()) {
                        session = (String)var2.next();
                        DialogueConnecterSession.this.comboSessions.addItem(session);
                    }

                    DialogueConnecterSession.this.boutonEffacerSession.setVisible(false);
                    DialogueConnecterSession.this.dialogue.repaint();
                }
            });
            this.ajouterBouton(this.boutonEffacerSession);
        }

        this.boutonAnnuler = new JButton("Annuler", Icones.ANNULER_16);
        this.boutonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueConnecterSession.this.dialogue.setVisible(false);
            }
        });
        this.ajouterBouton(this.boutonAnnuler);
        this.boutonOk = new JButton("Valider", Icones.OK_16);
        this.boutonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DialogueConnecterSession.this.resultat = 2;
                DialogueConnecterSession.this.nomSessionSelectionnee = DialogueConnecterSession.this.comboSessions.getSelectedItem().toString();
                DialogueConnecterSession.this.dialogue.setVisible(false);
            }
        });
        this.ajouterBouton(this.boutonOk);
    }

    public String getNomSessionSelectionnee() {
        return this.nomSessionSelectionnee;
    }
}
