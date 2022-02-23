//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele;

import hophophop.commun.modele.Connection;
import hophophop.commun.modele.Etudiant;
import hophophop.commun.modele.Evenement;
import hophophop.commun.modele.H3Commun;
import hophophop.commun.modele.Hop3xLogFormater;
import hophophop.commun.modele.Message;
import hophophop.commun.modele.Requete;
import hophophop.etudiant.Main;
import hophophop.etudiant.modele.projet.Projet;
import hophophop.etudiant.vue.Actions;
import hophophop.etudiant.vue.MonRSyntaxTextArea;
import hophophop.etudiant.vue.PanelConsigneQuestions;
import hophophop.etudiant.vue.dialogues.DialogueReceptionMessage;
import hophophop.etudiant.vue.dialogues.InvitationVideo;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.logging.Handler;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import org.jsresources.apps.chat.PlayWave;

public class EcouteServeur implements Runnable {
    private boolean fin = false;
    private ObjectInputStream in = H3Etudiant.getIn();

    EcouteServeur() {
    }

    public void run() {
        H3Etudiant.getLogger().info("Démarage du thread EcouteServeur sur l'étudiant");

        while(!this.fin) {
            try {
                synchronized(this.in) {
                    Object o = this.in.readObject();
                    if (o != null) {
                        H3Etudiant.getLogger().warning("Objet Recu de classe : " + o.getClass().getName());
                        if (o instanceof Evenement) {
                            Evenement e = (Evenement)o;
                            this.traiter(e);
                        } else if (o instanceof Message) {
                            Message message = (Message)o;
                            this.traiter(message);
                        } else if (o instanceof Requete) {
                            Requete requete = (Requete)o;
                            this.traiter(requete);
                        } else {
                            H3Etudiant.getLogger().warning("Objet Recu de classe : " + o.getClass().getName());
                        }
                    }
                }
            } catch (InvalidClassException var6) {
                H3Etudiant.getLogger().severe("Le client etudiant n'est pas a jour " + var6.getMessage());
                if (H3Etudiant.getEtudiant() == null) {
                    JOptionPane.showMessageDialog((Component)null, "Votre Client Etudiant va être mis a jour automatiquement,\nEn cas de problême relancez à partir d'Hop3xE.jar\n http://hop3x.univ-lemans.fr/Hop3xE.jar\nDans un terminal : java -jar Hop3xE.jar");
                    H3Commun.installe(H3Etudiant.getLogger(), "hop3xEtudiant");
                }

                this.fin = true;
            } catch (IOException var7) {
                H3Etudiant.getLogger().severe("IOException : Probleme de lecture dans le flux ");
                JOptionPane.showMessageDialog((Component)null, "La Communication est coupée avec le serveur\nL'application va se terminer\nVous devez vous reconnecter");
                System.exit(0);
            } catch (ClassNotFoundException var8) {
                H3Etudiant.getLogger().severe("ClassNotFoundException : Probleme de lecture dans le flux " + var8.getMessage());
                this.fin = true;
            }
        }

    }

    private void traiter(Message message) {
        if (message != null) {
            if (message.getType().equals("m-129")) {
                Map reponse;
                try {
                    reponse = H3Etudiant.zipLesLogs();
                } catch (IOException var14) {
                    reponse = null;
                }

                Message m = Message.getMessageReponseAdminRecupereLogEtudiant((String)message.getArgument("m-130"), reponse, (String)message.getArgument("m-049"));

                try {
                    H3Etudiant.getOut().writeObject(m);
                } catch (IOException var13) {
                    var13.printStackTrace();
                }
            } else if (message.getType().equals("m-121")) {
                JOptionPane.showMessageDialog((Component)null, "Hop3x est en maintenance, veuillez essayer ultérieurement");
                System.exit(0);
            } else if (message.getType().equals("m-118")) {
                JOptionPane.showMessageDialog((Component)null, "Votre Client Etudiant va être mis a jour automatiquement,\nEn cas de problême relancez à partir d'Hop3xE.jar\n http://hop3x.univ-lemans.fr/Hop3xE.jar\nDans un terminal : java -jar Hop3xE.jar");
                H3Commun.installe(H3Etudiant.getLogger(), "hop3xEtudiant");
            } else {
                String projet;
                if (message.getType().equals("m-091")) {
                    PanelConsigneQuestions pcq = H3Etudiant.getFenetre().getPanelConsigneQuestion();
                    int numQuestion = (Integer)message.getArgument("m-091");
                    Actions.TEST_UNITAIRE.setEnabled(false);
                    if (H3Etudiant.isTestunitaire()) {
                        Actions.TEST_UNITAIRE.setEnabled(numQuestion != 0);
                    }

                    H3Etudiant.setQuestionCourante(numQuestion);
                    if (numQuestion != 0) {
                        pcq.getTxtQuestion().setText((String)message.getArgument("m-022"));
                        pcq.getPanelQuestions().setBorder(BorderFactory.createTitledBorder((Border)null, "Question " + numQuestion + " / " + H3Etudiant.getNombreDeQuestions(), 2, 0, new Font("Comic Sans MS", 0, 12)));
                        if (H3Etudiant.isTestunitaire()) {
                            projet = (String)message.getArgument("m-057");
                            Actions.ouvrirNoeudDansExplorateur(projet);
                            H3Etudiant.getTraceur().envoyer(Evenement.getEvenementSelectionQuestion(numQuestion, projet));
                        } else {
                            projet = "Question";
                            if (numQuestion < 10) {
                                projet = projet + "0";
                            }

                            projet = projet + String.valueOf(numQuestion);
                            Actions.ouvrirNoeudDansExplorateur(projet);
                            H3Etudiant.getTraceur().envoyer(Evenement.getEvenementSelectionQuestion(numQuestion));
                        }
                    } else {
                        pcq.getTxtQuestion().setText("");
                        pcq.getPanelQuestions().setBorder(BorderFactory.createTitledBorder((Border)null, "Pas de question", 2, 0, new Font("Comic Sans MS", 0, 12)));
                    }

                    pcq.repaint();
                    pcq.getTxtQuestion().setCaretPosition(0);
                    pcq.setPreferredSize(new Dimension(395, pcq.getHeight()));
                } else if (message.getType().equals("m-006")) {
                    if (message.getArgument("m-060").equals("m-053")) {
                        JOptionPane.showMessageDialog(H3Etudiant.getFenetre(), "Mot de Passe changé avec Succès", "SUCCES", 1);
                        if ((Boolean)message.getArgument("m-048")) {
                            try {
                                H3Etudiant.getOut().writeObject(Requete.getRequeteConnexion((String)message.getArgument("m-080"), (String)message.getArgument("m-055"), H3Commun.getVersionEtudiant()));
                            } catch (IOException var12) {
                                var12.printStackTrace();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(H3Etudiant.getFenetre(), "Ancien mot de passe incorrect ", "ECHEC", 0);
                        if ((Boolean)message.getArgument("m-048") && H3Commun.changerDeMotDePasse(H3Etudiant.getFenetre(), H3Etudiant.getLogger(), H3Etudiant.getOut(), (String)message.getArgument("m-080"), true) == 1) {
                            System.exit(0);
                        }
                    }
                } else if (message.getType().equals("m-018")) {
                    H3Etudiant.getLogger().info("Première connexion, Il faut changer de mot de passe pour " + message.getArgument("m-080"));
                    if (H3Commun.changerDeMotDePasse(H3Etudiant.getFenetre(), H3Etudiant.getLogger(), H3Etudiant.getOut(), (String)message.getArgument("m-080"), true) == 1) {
                        System.exit(0);
                    }
                } else if (message.getType().equals("m-082")) {
                    JOptionPane.showMessageDialog(H3Etudiant.getFenetre(), message.getArgument("m-048"), "Erreur !!!", 0);
                    Connection.saisieIdentifiants(H3Etudiant.getFenetre(), H3Etudiant.getLogger(), H3Etudiant.getOut(), "Etudiant", H3Commun.getVersionEtudiant());
                } else if (message.getType().equals("m-007")) {
                    try {
                        H3Etudiant.choixNouvelleSession();
                    } catch (IOException var10) {
                        var10.printStackTrace();
                    } catch (ClassNotFoundException var11) {
                        var11.printStackTrace();
                    }
                } else if (message.getType().equals("m-023")) {
                    Etudiant etudiant = (Etudiant)message.getArgument("m-023");
                    H3Etudiant.setEtudiant(etudiant);
                    Hop3xLogFormater mf = new Hop3xLogFormater(etudiant.getNomComplet());
                    Handler[] var23 = H3Etudiant.getLogger().getHandlers();
                    int var5 = var23.length;

                    for(int var6 = 0; var6 < var5; ++var6) {
                        Handler handler = var23[var6];
                        handler.setFormatter(mf);
                    }

                    if (H3Etudiant.getSessionsDemarrees().isEmpty() && H3Etudiant.getSessionsConnectées().isEmpty()) {
                        JOptionPane.showMessageDialog(H3Etudiant.getFenetre(), "Pas de session disponible", "Erreur !!!", 0);
                        Connection.saisieIdentifiants(H3Etudiant.getFenetre(), H3Etudiant.getLogger(), H3Etudiant.getOut(), "Etudiant", H3Commun.getVersionEtudiant());
                    } else {
                        try {
                            if (H3Etudiant.choixDeLaSession() == null) {
                                H3Etudiant.getLogger().info("Annulation de la connexion");
                                System.exit(0);
                            }
                        } catch (Exception var9) {
                            H3Etudiant.getLogger().severe("Connexion Impossible " + var9.getMessage());
                            JOptionPane.showMessageDialog((Component)null, "Vous êtes déconnecté\nL'application va se terminer !!");
                            System.exit(0);
                        }
                    }
                } else if (message.getType().equals("m-074")) {
                    try {
                        H3Etudiant.creerWorkspace(H3Etudiant.getTrace());
                        H3Etudiant.getFenetre().setVisible(true);
                        H3Etudiant.getTraceur().connecter(H3Etudiant.getOut());
                    } catch (Exception var8) {
                        var8.printStackTrace();
                    }
                } else if (message.getType().equals("m-031")) {
                    if (!Main.isSimulation()) {
                        (new PlayWave(H3Etudiant.FICHIER_SON_FERMETURE)).joue();
                        if (message.getArgument("m-012").equals("true")) {
                            if (message.getArgument("m-078") != null) {
                                JOptionPane.showMessageDialog((Component)null, message.getArgument("m-078"));
                            } else {
                                JOptionPane.showMessageDialog((Component)null, "L'application va se terminer !!");
                            }
                        }

                        H3Etudiant.setDeconnecterEnQuittant(false);
                    }

                    System.exit(0);
                } else if (message.getType().equals("m-046")) {
                    (new PlayWave(H3Etudiant.FICHIER_SON_MESSAGE)).joue();
                    new DialogueReceptionMessage((String)message.getArgument("m-078"), (String)message.getArgument("m-079"));
                    Evenement evenement = Evenement.getEvenementMessageTuteurVersEtudiant((String)message.getArgument("m-078"), H3Etudiant.getNomDeLaSession(), (String)message.getArgument("m-079"));
                    H3Etudiant.getTraceur().envoyer(evenement);
                } else if (message.getType().equals("m-109-1")) {
                    String sortie = (String)message.getArgument("m-078");
                    Boolean resultat = (Boolean)message.getArgument("m-089");
                    projet = (String)message.getArgument("m-057");
                    Integer question = (Integer)message.getArgument("m-091");
                    PanelConsigneQuestions panel = H3Etudiant.getFenetre().getPanelConsigneQuestion();
                    String nouvel_etat = (String)message.getArgument("m-095-10");
                    H3Etudiant.setTracageTU(nouvel_etat);
                    H3Etudiant.getTraceur().envoyer(Evenement.getEvenementTestUnitaire(nouvel_etat));
                    panel.mettreAJourPanelResultat(resultat, question);
                    JOptionPane.showMessageDialog((Component)null, sortie, "Test Unitaire", 1);
                }
            }
        }

    }

    private void traiter(Requete requete) {
        String tuteur;
        if (requete.getType().equals("r-059-1")) {
            tuteur = requete.getArgument("r-060");
            String adresse = requete.getArgument("r-008");
            Actions.ACCEDERVIDEOINDIVIDUELLE.setEnabled(true);
            Actions.ACCEDERVIDEOSESSION.setEnabled(true);
            String message = "<div align=\"center\"> Contactez " + tuteur + "<br>en cliquant sur le <strong>bouton Lancer la vidéo</strong><br>";
            message = message + "Si le navigateur ne s'ouvre pas vous pouver le lancer manuellement<br>sur l'adresse ";
            message = message + adresse + "</div>";
            new InvitationVideo(message, adresse);
            H3Etudiant.getTraceur().envoyer(Evenement.getEvenementDemarrerVideo(tuteur));
        } else if (requete.getType().equals("r-059-4")) {
            tuteur = requete.getArgument("r-060");
            H3Etudiant.getTraceur().envoyer(Evenement.getEvenementDesactiverVideo(tuteur));
            Actions.ACCEDERVIDEOINDIVIDUELLE.setEnabled(false);
            Actions.ACCEDERVIDEOSESSION.setEnabled(false);
        }

    }

    private void traiter(Evenement e) {
        if (e.getType().equals("MT")) {
            int debut = Integer.parseInt(e.get("POSDEB"));
            int fin = Integer.parseInt(e.get("POSFIN"));
            Projet projet = H3Etudiant.getFenetre().getExplorateur().getProjet();
            if (!e.get("F").equals("")) {
                H3Etudiant.getFenetre().getEditeur().ouvrirFichier(projet.getFichierParNom(e.get("F")));
                MonRSyntaxTextArea page = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource();
                page.setSelectionStart(debut);
                page.setSelectionEnd(fin);
                page.requestFocus();
            }

            (new PlayWave(H3Etudiant.FICHIER_SON_DEBUT_TEXTE)).joue();
            H3Etudiant.getTraceur().envoyer(e);
            Actions.MONTRERHISTORIQUEMESSAGE.setEnabled(true);
            H3Etudiant.getHistoriqueMessage().add(e);
            Actions.MONTRERHISTORIQUEMESSAGE.actionPerformed((ActionEvent)null);
        } else if (e.getType().equals("INITIERCAUSERIE")) {
            (new PlayWave(H3Etudiant.FICHIER_SON_DEBUT_CAUSETTE)).joue();
            H3Etudiant.okJEcouteVasYCause(e);
            JOptionPane.showMessageDialog((Component)null, "Tu peux parler !!! \nN'oublie pas de cliquer sur OK", "L'enseignant t'appelle ", 1);
        } else if (e.getType().equals("TERMINERCAUSERIE")) {
            (new PlayWave(H3Etudiant.FICHIER_SON_FIN_CAUSETTE)).joue();
            if (H3Etudiant.getMasterModelEtudiant() != null) {
                H3Etudiant.getMasterModelEtudiant().getChatModel().disconnect();
            }

            H3Etudiant.getTraceur().envoyer(e);
        }

    }
}
