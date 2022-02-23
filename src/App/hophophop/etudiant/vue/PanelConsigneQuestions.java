//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue;

import hophophop.commun.modele.Evenement;
import hophophop.commun.modele.Message;
import hophophop.commun.vue.Icones;
import hophophop.etudiant.Main;
import hophophop.etudiant.modele.H3Etudiant;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class PanelConsigneQuestions extends JPanel {
    private JPanel panelQuestions;
    private JPanel panelResultatTestUnitaire;
    private JButton btnSuivant;
    private JButton btnPrecedent;
    private JEditorPane txtQuestion;
    private JScrollPane jScrollPaneTxtQuestions;
    private JButton btnRevoirConsigne;
    private JButton btnRevoirHistorique;
    private JPanel panelBouton;
    private JLabel[] tableauDesResultat;

    public JPanel getPanelQuestions() {
        return this.panelQuestions;
    }

    public JButton getBtnSuivant() {
        return this.btnSuivant;
    }

    public JButton getBtnPrecedent() {
        return this.btnPrecedent;
    }

    public JEditorPane getTxtQuestion() {
        return this.txtQuestion;
    }

    public PanelConsigneQuestions(final Fenetre laFenetre, int questionCourante) {
        this.setLayout(new BorderLayout());
        if (!H3Etudiant.getLstQuestions().isEmpty()) {
            if (questionCourante == 0) {
                H3Etudiant.setQuestionCourante(1);
            }

            this.tableauDesResultat = new JLabel[H3Etudiant.getLstQuestions().size()];
            this.panelResultatTestUnitaire = new JPanel(new GridLayout(1, H3Etudiant.getLstQuestions().size()));

            for(int i = 0; i < H3Etudiant.getLstQuestions().size(); ++i) {
                this.tableauDesResultat[i] = new JLabel(Icones.CARRE_BLANC_16);
                this.tableauDesResultat[i].setToolTipText("Question " + String.valueOf(i + 1));
                final int qc = i + 1;
                this.tableauDesResultat[i].addMouseListener(new MouseAdapter() {
                    public void mouseReleased(MouseEvent e) {
                        H3Etudiant.setQuestionCourante(qc);

                        try {
                            H3Etudiant.getOut().writeObject(Message.getEnonceQuestion(H3Etudiant.getNomDeLaSession(), qc));
                            PanelConsigneQuestions.this.panelQuestions.setBorder(BorderFactory.createTitledBorder((Border)null, "Question " + qc + " / " + H3Etudiant.getNombreDeQuestions(), 2, 0, new Font("Comic Sans MS", 0, 12)));
                            PanelConsigneQuestions.this.panelQuestions.setPreferredSize(new Dimension(395, PanelConsigneQuestions.this.panelQuestions.getHeight()));
                            if (qc == H3Etudiant.getNombreDeQuestions()) {
                                PanelConsigneQuestions.this.btnSuivant.setEnabled(false);
                                PanelConsigneQuestions.this.btnPrecedent.setEnabled(true);
                            } else if (qc == 1) {
                                PanelConsigneQuestions.this.btnPrecedent.setEnabled(false);
                                PanelConsigneQuestions.this.btnSuivant.setEnabled(true);
                            } else {
                                PanelConsigneQuestions.this.btnSuivant.setEnabled(true);
                                PanelConsigneQuestions.this.btnPrecedent.setEnabled(true);
                            }
                        } catch (IOException var3) {
                            JOptionPane.showMessageDialog(laFenetre, "Erreur lors de la demande d'énoncé de la question", "Attention !!!!!", 0);
                            var3.printStackTrace();
                        }

                    }
                });
                this.panelResultatTestUnitaire.add(this.tableauDesResultat[i]);
            }

            this.panelQuestions = new JPanel(new BorderLayout(10, 10));
            this.panelQuestions.setBorder(BorderFactory.createTitledBorder((Border)null, "Question " + H3Etudiant.getQuestionCourante() + " / " + H3Etudiant.getNombreDeQuestions(), 2, 0, new Font("Comic Sans MS", 0, 12)));
            this.txtQuestion = new JEditorPane();
            this.jScrollPaneTxtQuestions = new JScrollPane(this.txtQuestion);
            this.jScrollPaneTxtQuestions.setOpaque(true);
            this.jScrollPaneTxtQuestions.setBorder((Border)null);
            this.txtQuestion.setContentType("text/html");
            this.txtQuestion.setEditable(false);
            this.txtQuestion.setText((String)H3Etudiant.getLstQuestions().get(H3Etudiant.getQuestionCourante() - 1));
            if (H3Etudiant.isTestunitaire()) {
                try {
                    H3Etudiant.getOut().writeObject(Message.getEnonceQuestion(H3Etudiant.getNomDeLaSession(), H3Etudiant.getQuestionCourante()));
                } catch (IOException var6) {
                    var6.printStackTrace();
                }
            }

            this.txtQuestion.setBackground(Fenetre.FOND_GRIS);
            this.txtQuestion.setCaretPosition(0);
            this.jScrollPaneTxtQuestions.setViewportView(this.txtQuestion);
            this.jScrollPaneTxtQuestions.setHorizontalScrollBarPolicy(31);
            this.jScrollPaneTxtQuestions.setVerticalScrollBarPolicy(20);
            this.btnSuivant = new JButton(Icones.FLECHESDROITES_24);
            this.btnSuivant.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PanelConsigneQuestions.this.btnPrecedent.setEnabled(true);
                    int qc = H3Etudiant.getQuestionCourante();
                    if (qc < H3Etudiant.getNombreDeQuestions()) {
                        ++qc;
                        H3Etudiant.setQuestionCourante(qc);

                        try {
                            H3Etudiant.getOut().writeObject(Message.getEnonceQuestion(H3Etudiant.getNomDeLaSession(), qc));
                            H3Etudiant.getTraceur().envoyer(Evenement.getEvenementNavigationQuestion(qc - 1, qc));
                            PanelConsigneQuestions.this.panelQuestions.setBorder(BorderFactory.createTitledBorder((Border)null, "Question " + H3Etudiant.getQuestionCourante() + " / " + H3Etudiant.getNombreDeQuestions(), 2, 0, new Font("Comic Sans MS", 0, 12)));
                            PanelConsigneQuestions.this.panelQuestions.setPreferredSize(new Dimension(395, PanelConsigneQuestions.this.panelQuestions.getHeight()));
                            if (qc == H3Etudiant.getNombreDeQuestions()) {
                                PanelConsigneQuestions.this.btnSuivant.setEnabled(false);
                            }
                        } catch (IOException var4) {
                            JOptionPane.showMessageDialog(laFenetre, "Erreur lors de la demande d'énoncé de la question", "Attention !!!!!", 0);
                            var4.printStackTrace();
                        }
                    }

                }
            });
            this.btnPrecedent = new JButton(Icones.FLECHESGAUCHES_24);
            this.btnPrecedent.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PanelConsigneQuestions.this.btnSuivant.setEnabled(true);
                    int qc = H3Etudiant.getQuestionCourante();
                    if (qc > 1) {
                        --qc;
                        H3Etudiant.setQuestionCourante(qc);

                        try {
                            H3Etudiant.getOut().writeObject(Message.getEnonceQuestion(H3Etudiant.getNomDeLaSession(), qc));
                            H3Etudiant.getTraceur().envoyer(Evenement.getEvenementNavigationQuestion(qc + 1, qc));
                            PanelConsigneQuestions.this.panelQuestions.setBorder(BorderFactory.createTitledBorder((Border)null, "Question " + H3Etudiant.getQuestionCourante() + " / " + H3Etudiant.getNombreDeQuestions(), 2, 0, new Font("Comic Sans MS", 0, 12)));
                            PanelConsigneQuestions.this.panelQuestions.setPreferredSize(new Dimension(395, PanelConsigneQuestions.this.panelQuestions.getHeight()));
                            if (qc == 1) {
                                PanelConsigneQuestions.this.btnPrecedent.setEnabled(false);
                            }
                        } catch (IOException var4) {
                            JOptionPane.showMessageDialog(laFenetre, "Erreur lors de la demande d'énoncé de la question", "Attention !!!!!", 0);
                            var4.printStackTrace();
                        }
                    }

                }
            });
            this.btnSuivant.setEnabled(true);
            this.btnPrecedent.setEnabled(H3Etudiant.getQuestionCourante() > 1);
            this.panelQuestions.add(this.jScrollPaneTxtQuestions, "Center");
            this.panelBouton = new JPanel(new BorderLayout());
            JPanel panelBouton1 = new JPanel(new GridLayout(1, 4));
            this.panelBouton.add(panelBouton1, "Center");
            this.panelBouton.add(new JLabel(" "), "South");
            panelBouton1.add(new JLabel());
            panelBouton1.add(this.btnPrecedent);
            panelBouton1.add(this.btnSuivant);
            panelBouton1.add(new JLabel());
            this.add(this.panelQuestions, "Center");
            this.add(this.panelBouton, "North");
        }

        if (H3Etudiant.getConsigne() != null) {
            try {
                this.btnRevoirConsigne = new JButton("Revoir la consigne");
                final Consigne cons = new Consigne(H3Etudiant.getConsigne(), laFenetre, !H3Etudiant.getLstQuestions().isEmpty(), this.btnRevoirConsigne, this.btnRevoirHistorique);
                this.btnRevoirConsigne.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        PanelConsigneQuestions.this.btnRevoirConsigne.setEnabled(false);
                        cons.afficher();
                    }
                });
                this.panelBouton.add(this.btnRevoirConsigne, "North");
                if (questionCourante == 0 && !Main.isSimulation()) {
                    this.btnRevoirConsigne.doClick();
                }
            } catch (IOException var5) {
            }
        } else {
            this.panelBouton.add(new JLabel(" "), "North");
        }

        this.panelBouton.add(this.panelResultatTestUnitaire, "South");
        this.setPreferredSize(new Dimension(395, 300));
    }

    public JPanel getPanelResultatTestUnitaire() {
        return this.panelResultatTestUnitaire;
    }

    public void mettreAJourPanelResultat() {
        String etat = H3Etudiant.getTracageTU();

        for(int i = 0; i < etat.length(); ++i) {
            if (etat.charAt(i) == 'S') {
                this.tableauDesResultat[i].setIcon(Icones.CARRE_VERT_16);
            } else if (etat.charAt(i) == 'N') {
                this.tableauDesResultat[i].setIcon(Icones.CARRE_BLANC_16);
            } else {
                this.tableauDesResultat[i].setIcon(Icones.CARRE_ROUGE_16);
            }
        }

    }

    public void mettreAJourPanelResultat(Boolean resultat, Integer question) {
        if (resultat) {
            this.tableauDesResultat[question - 1].setIcon(Icones.CARRE_VERT_16);
        } else {
            this.tableauDesResultat[question - 1].setIcon(Icones.CARRE_ROUGE_16);
        }

        this.tableauDesResultat[question - 1].repaint();
        this.repaint();
    }
}
