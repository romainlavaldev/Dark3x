//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue;

import codelab.CodeLabWS;
import hophophop.commun.modele.Evenement;
import hophophop.commun.modele.H3Commun;
import hophophop.commun.modele.Message;
import hophophop.commun.modele.Requete;
import hophophop.commun.vue.Icones;
import hophophop.commun.vue.dialogues.DialogueExecuter;
import hophophop.commun.vue.dialogues.DialogueHistoriqueInterventionsEnseignant;
import hophophop.etudiant.Main;
import hophophop.etudiant.modele.H3Etudiant;
import hophophop.etudiant.modele.fichier.Fichier;
import hophophop.etudiant.modele.fichier.FichierAutre;
import hophophop.etudiant.modele.fichier.FichierC;
import hophophop.etudiant.modele.fichier.FichierEv3;
import hophophop.etudiant.modele.fichier.FichierH;
import hophophop.etudiant.modele.fichier.FichierJava;
import hophophop.etudiant.modele.fichier.FichierMF;
import hophophop.etudiant.modele.fichier.FichierNxc;
import hophophop.etudiant.modele.fichier.FichierPython;
import hophophop.etudiant.modele.fichier.FichierRuby;
import hophophop.etudiant.modele.fichier.FichierScala;
import hophophop.etudiant.modele.fichier.FichierSpiC;
import hophophop.etudiant.modele.projet.Projet;
import hophophop.etudiant.modele.projet.ProjetC;
import hophophop.etudiant.modele.projet.ProjetEv3;
import hophophop.etudiant.modele.projet.ProjetJava;
import hophophop.etudiant.modele.projet.ProjetNxc;
import hophophop.etudiant.modele.projet.ProjetPython;
import hophophop.etudiant.modele.projet.ProjetRuby;
import hophophop.etudiant.modele.projet.ProjetScala;
import hophophop.etudiant.modele.projet.ProjetSpiC;
import hophophop.etudiant.vue.Explorateur.NoeudFichier;
import hophophop.etudiant.vue.Explorateur.NoeudProjet;
import hophophop.etudiant.vue.dialogues.DialogueFichier;
import hophophop.etudiant.vue.dialogues.DialogueLigneDeCommande;
import hophophop.etudiant.vue.dialogues.DialogueProjet;
import hophophop.etudiant.vue.dialogues.DialogueRenommerFichier;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public abstract class Actions {
    public static final Action BASCULE_THEME;
    public static final Action TEST_UNITAIRE;
    public static final Action OUVRIR_TERMINAL;
    public static final Action EFFACER_CONSOLE;
    public static final Action NOUVEAU_PROJET;
    public static final Action NOUVEAU_FICHIER;
    public static final Action COMPILER;
    public static final Action EXECUTER;
    public static final Action LIGNEDECOMMANDE;
    public static final Action QUITTER;
    public static final Action CHANGERDESESSION;
    public static final Action FERMER;
    public static final Action FERMER_TOUT;
    public static final Action COUPER;
    public static final Action COLLER;
    public static final Action COPIER;
    public static final Action SELECTIONNER_TOUT;
    public static final Action SUPPRIMER_FICHIER;
    public static final Action SUPPRIMER_PROJET;
    public static final Action UNDO;
    public static final Action DECALERADROITE;
    public static final Action DECALERAGAUCHE;
    public static final Action COMMENTERCODE;
    public static final Action RESTAURER;
    public static final Action AUSECOURS;
    public static final Action MONTRERHISTORIQUEMESSAGE;
    public static final Action EXECUTIONCOMMANDE_C;
    public static final Action COMPILATIONCOMMANDE_C;
    public static final Action MAKECIBLE_C;
    public static final Action IMPORTERREPERTOIRE;
    public static final Action GENERER_RUBYDOC;
    public static final Action PRECISERCOMMANDERDOC;
    public static final Action PRECISERCOMMANDERUBY;
    public static final Action PRECISERVISURDOC;
    public static final Action LANCERGLADE;
    public static final Action PRECISERCOMMANDERGLADE;
    public static final Action NOUVEAUGLADE;
    public static final Action REDO;
    public static final Action RENOMMER_FICHIER;
    public static final Action DECOMMENTERCODE;
    public static final Action CHANGERPASSWORD;
    public static final Action MODESIMULATEUR;
    public static final Action MODEROBOT;
    public static final Action OUVRIRLESIMULATEUR;
    public static final Action FERMERLESIMULATEUR;
    public static final Action DEMARRERSIMULATEUR;
    public static final Action ARRETERSIMULATEUR;
    public static final String SIMUSTART_CMD = "module=robot&commande=simu_start";
    public static final String SIMUSTOP_CMD = "module=robot&commande=simu_stop";
    public static final Action ACCEDERVIDEOINDIVIDUELLE;
    public static final Action ACCEDERVIDEOSESSION;

    public Actions() {
    }

    private static void supprimerFichierSelectionne() {
        Fichier f = H3Etudiant.getFenetre().getExplorateur().getFichier();
        Projet p = H3Etudiant.getFenetre().getExplorateur().getProjet();
        if (f != null) {
            TreeNode np = ((TreeNode)H3Etudiant.getFenetre().getExplorateur().getLastSelectedPathComponent()).getParent();
            NoeudProjet noeud = (NoeudProjet)np;
            int row = 1;

            for(Enumeration e = noeud.children(); e.hasMoreElements(); ++row) {
                TreeNode tf = (TreeNode)e.nextElement();
                if (tf instanceof NoeudFichier) {
                    NoeudFichier nf = (NoeudFichier)tf;
                    if (nf.getFichier().getNom().equals(H3Etudiant.getFenetre().getEditeur().getPageActuelle().getFichier().getNom())) {
                        H3Etudiant.getFenetre().getExplorateur().scrollPathToVisible(new TreePath(nf.getPath()));
                        H3Etudiant.getFenetre().getExplorateur().setSelectionRow(row);
                    }
                }
            }

            H3Etudiant.getLogger().info("Type du Fichier A supprimer : " + f.getType());
            p.supprimerFichier(f);
        } else {
            JOptionPane.showMessageDialog(H3Etudiant.getFenetre(), "Pas de fichier sélectionné", "Erreur !!!", 0);
        }

    }

    public static void deleterep(File f) {
        if (f.isDirectory()) {
            File[] var1 = f.listFiles();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                File fichier = var1[var3];
                deleterep(fichier);
            }
        }

        f.delete();
    }

    public static void demarrerVideo(String adresse) {
        try {
            Desktop.getDesktop().browse(new URI(adresse));
        } catch (URISyntaxException | IOException var2) {
            var2.printStackTrace();
        }

    }

    public static void importerRepertoire(File repertoire) {
        if (nouveauProjet(repertoire.getName()) != null) {
            Projet projet = H3Etudiant.getFenetre().getExplorateur().getProjet();
            String expReg = projet.getExprReguliereTypeFichier();
            File[] lf = repertoire.listFiles();
            Arrays.sort(lf);
            File[] var4 = lf;
            int var5 = lf.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                File fichier = var4[var6];
                String nomFichier = fichier.getName();
                if (Pattern.matches(expReg, nomFichier)) {
                    if (projet.getFichierParNom(fichier.getName()) != null) {
                        JOptionPane.showMessageDialog((Component)null, "Le fichier '" + fichier.getName() + "'\nexiste déjà dans\nce projet", "Attention !!!", 0, Icones.ERREUR_32);
                    } else {
                        Fichier fichierCree = null;
                        if (projet.getType().equals("C")) {
                            if (fichier.getName().toLowerCase().endsWith(".c")) {
                                fichierCree = new FichierC((ProjetC)projet, fichier);
                            } else if (fichier.getName().toLowerCase().endsWith(".h")) {
                                fichierCree = new FichierH((ProjetC)projet, fichier);
                            } else {
                                fichierCree = new FichierMF((ProjetC)projet, fichier);
                            }
                        } else if (projet.getType().equals("Ruby")) {
                            fichierCree = new FichierRuby((ProjetRuby)projet, fichier);
                        } else if (projet.getType().equals("Python")) {
                            fichierCree = new FichierPython((ProjetPython)projet, fichier);
                        } else if (projet.getType().equals("Java")) {
                            fichierCree = new FichierJava((ProjetJava)projet, fichier);
                        } else if (projet.getType().equals("Nxc")) {
                            fichierCree = new FichierNxc((ProjetNxc)projet, fichier);
                        } else if (projet.getType().equals("SpiC")) {
                            fichierCree = new FichierSpiC((ProjetSpiC)projet, fichier);
                        }

                        ouvrirClasseDansEditeur((Fichier)fichierCree);
                    }
                } else {
                    H3Etudiant.getLogger().warning("Fichier Ignoré : " + nomFichier);
                }
            }
        }

    }

    public static Projet nouveauProjet(String nom) {
        DialogueProjet dialogue = new DialogueProjet(H3Etudiant.getFenetre(), H3Etudiant.getLangage(), nom);
        if (dialogue.afficher() == 2) {
            boolean existeDeja = false;
            Iterator var3 = H3Etudiant.getProjets().iterator();

            while(var3.hasNext()) {
                Projet p = (Projet)var3.next();
                if (p.getNom().equals(dialogue.getNom())) {
                    JOptionPane.showMessageDialog((Component)null, "Le Nom de projet " + dialogue.getNom() + " est déjà utilisé dans cette Session ", "Attention !!!", 0, Icones.ERREUR_32);
                    existeDeja = true;
                }
            }

            if (!existeDeja) {
                Projet projet = null;
                if (dialogue.isJava()) {
                    projet = new ProjetJava(dialogue.getNom(), dialogue.isCompilationProjet());
                } else if (dialogue.isC()) {
                    projet = new ProjetC(dialogue.getNom(), dialogue.isCompilationProjet());
                } else if (dialogue.isRuby()) {
                    projet = new ProjetRuby(dialogue.getNom(), dialogue.isCompilationProjet());
                } else if (dialogue.isNxc()) {
                    projet = new ProjetNxc(dialogue.getNom(), dialogue.isCompilationProjet());
                } else if (dialogue.isPython()) {
                    projet = new ProjetPython(dialogue.getNom(), dialogue.isCompilationProjet());
                } else if (dialogue.isScala()) {
                    projet = new ProjetScala(dialogue.getNom(), dialogue.isCompilationProjet());
                } else if (dialogue.isEv3()) {
                    projet = new ProjetEv3(dialogue.getNom(), dialogue.isCompilationProjet());
                }

                H3Etudiant.getLogger().info("Ajout du Projet : " + ((Projet)projet).getNom());
                ajouterProjet((Projet)projet);
                return (Projet)projet;
            }
        }

        return null;
    }

    public static void ajouterProjet(Projet projet) {
        H3Etudiant.ajouterProjet(projet);
        projet.ajouterObservateur(H3Etudiant.getFenetre().getConsole());
        projet.ajouterObservateur(H3Etudiant.getFenetre().getEditeur());
        NOUVEAU_FICHIER.setEnabled(true);
    }

    public static void ouvrirClasseDansEditeur(Fichier fichier) {
        H3Etudiant.getFenetre().getEditeur().ouvrirFichier(fichier);
    }

    public static void ouvrirNoeudDansExplorateur(String projet) {
        H3Etudiant.getFenetre().getEditeur().ouvrirProjet(projet);
    }

    public static void enregistrerLesFichiersDuProjets() {
        Fichier.TRACER = false;
        Fenetre fenetre = H3Etudiant.getFenetre();
        if (fenetre != null) {
            fenetre.getEditeur().enregistrerTout();
        }

        Fichier.TRACER = true;
    }

    public static Fenetre getFenetre() {
        return H3Etudiant.getFenetre();
    }

    public static void importerFichier(File f, Projet projetDestination) {
        String nomDuFichier = f.getName();
        String extension = "";
        Fichier fc = null;
        int positionDuPoint = nomDuFichier.indexOf(".");
        if (positionDuPoint != -1) {
            extension = nomDuFichier.substring(positionDuPoint + 1);
        }

        if (projetDestination.getType() == "C") {
            if (extension.equals("c")) {
                fc = new FichierC(projetDestination, f);
            } else if (extension.equals("h")) {
                fc = new FichierH(projetDestination, f);
            } else if (nomDuFichier.equals("Makefile") && projetDestination.isCompilationProjet()) {
                fc = new FichierMF(projetDestination, f);
            }
        } else if (projetDestination.getType() == "Java") {
            if (extension.equals("java")) {
                fc = new FichierJava(projetDestination, f);
            }
        } else if (projetDestination.getType() == "Ruby") {
            if (extension.equals("rb") || extension.equals("glade")) {
                fc = new FichierRuby(projetDestination, f);
            }
        } else if (projetDestination.getType() == "Nxc") {
            if (extension.equals("nxc")) {
                fc = new FichierNxc(projetDestination, f);
            }
        } else if (projetDestination.getType() == "Python" && extension.equals("py")) {
            fc = new FichierPython(projetDestination, f);
        }

        if (fc != null) {
            H3Etudiant.getLogger().info("Ajout du fichier " + ((Fichier)fc).getNom() + " Au Projet " + projetDestination.getNom());
        }

    }

    public static void importerFichier(Fichier fichier, Projet projetSource, Projet projetDestination) {
        List<Fichier> listeFichierProjetDestination = projetDestination.getFichiers();
        if (projetDestination.getType().equals(projetSource.getType())) {
            if (projetDestination.getFichierParNom(fichier.getNom()) != null) {
                JOptionPane.showMessageDialog(getFenetre(), "Le fichier " + fichier.getNom() + " existe déjà dans le projet " + projetDestination.getNom());
            } else {
                Fichier fc = null;
                if (fichier.getType().equals("C")) {
                    fc = new FichierC(projetDestination, fichier.getNom(), true);
                } else if (fichier.getType().equals("H")) {
                    fc = new FichierH(projetDestination, fichier.getNom(), true);
                } else if (fichier.getType().equals("Makefile")) {
                    fc = new FichierMF(projetDestination, fichier.getNom(), true);
                } else if (fichier.getType().equals("Java")) {
                    fc = new FichierJava(projetDestination, fichier.getNom(), true, false);
                } else if (fichier.getType().equals("Ruby")) {
                    fc = new FichierRuby(projetDestination, fichier.getNom(), true);
                } else if (fichier.getType().equals("Nxc")) {
                    fc = new FichierNxc(projetDestination, fichier.getNom(), true);
                } else if (fichier.getType().equals("Python")) {
                    fc = new FichierPython(projetDestination, fichier.getNom(), true);
                }

                try {
                    if (fc != null) {
                        ((Fichier)fc).getSource().insertString(0, fichier.getTexte(), (AttributeSet)null);
                    }
                } catch (BadLocationException var6) {
                    var6.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(getFenetre(), "Les projets Source et Destination ne sont pas de même type");
        }

    }

    static {
        ACCEDERVIDEOSESSION = new AbstractAction("Démarrer Vidéo Session", Icones.JITSI_32) {
            public void actionPerformed(ActionEvent e) {
                String session = H3Etudiant.getNomDeLaSession();
                String adresse = "https://meet.jit.si/hop3x_" + session;
                Actions.demarrerVideo(adresse);
            }
        };
        ACCEDERVIDEOSESSION.setEnabled(false);
        ACCEDERVIDEOINDIVIDUELLE = new AbstractAction("Démarrer Conversation avec le tuteur", Icones.JITSI_32) {
            public void actionPerformed(ActionEvent e) {
                String username = H3Etudiant.getUserName();
                String adresse = "https://meet.jit.si/hop3x_" + username;
                Actions.demarrerVideo(adresse);
            }
        };
        ACCEDERVIDEOINDIVIDUELLE.setEnabled(false);
        BASCULE_THEME = new AbstractAction("Bascule Theme Sombre", Icones.CHOIXSESSION_32) {
            public void actionPerformed(ActionEvent e) {
            }
        };
        TEST_UNITAIRE = new AbstractAction("Lancer le test unitaire", Icones.TEST_UNITAIRE_32) {
            public void actionPerformed(ActionEvent e) {
                Projet projet = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getFichier().getProjet();
                Actions.enregistrerLesFichiersDuProjets();
                int resultatCompil = projet.compiler(false);
                if (resultatCompil == 0) {
                    int question = H3Etudiant.getQuestionCourante();
                    Map<String, String> lesFichiers = new HashMap();
                    Iterator var6 = projet.getFichiers().iterator();

                    while(var6.hasNext()) {
                        Fichier f = (Fichier)var6.next();
                        lesFichiers.put(f.getNom(), f.getTexte());
                    }

                    Message message = Message.getMessageTestUnitaire(H3Etudiant.getNomDeLaSession(), H3Etudiant.getScenario(), projet.getNom(), question, lesFichiers, H3Etudiant.getUserName(), H3Etudiant.getTracageTU());

                    try {
                        H3Etudiant.getOut().writeObject(message);
                    } catch (IOException var8) {
                        var8.printStackTrace();
                    }
                }

            }
        };
        TEST_UNITAIRE.putValue("ShortDescription", "Lancer un test Unitaire");
        TEST_UNITAIRE.putValue("AcceleratorKey", KeyStroke.getKeyStroke(84, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        TEST_UNITAIRE.setEnabled(true);
        FERMERLESIMULATEUR = new AbstractAction("Fermer le simulateur de Robot", Icones.SIMULATEUR_32) {
            public void actionPerformed(ActionEvent e) {
                H3Etudiant.getCodeLabWS().exit();
                H3Etudiant.setCodeLabWS((CodeLabWS)null);
                Actions.OUVRIRLESIMULATEUR.setEnabled(true);
                Actions.FERMERLESIMULATEUR.setEnabled(false);
            }
        };
        FERMERLESIMULATEUR.setEnabled(false);
        OUVRIRLESIMULATEUR = new AbstractAction("Ouvrir le simulateur de Robot", Icones.SIMULATEUR_32) {
            public void actionPerformed(ActionEvent e) {
                try {
                    H3Etudiant.getOutEtudiant().reset();
                } catch (IOException var3) {
                    var3.printStackTrace();
                }

                (new Thread() {
                    public void run() {
                        H3Etudiant.setCodeLabWS(H3Commun.ouvrirCodeLabs(H3Etudiant.getFenetre(), H3Etudiant.getOutEtudiant()));
                    }
                }).start();
                Actions.OUVRIRLESIMULATEUR.setEnabled(false);
                Actions.FERMERLESIMULATEUR.setEnabled(true);
            }
        };
        OUVRIRLESIMULATEUR.setEnabled(false);
        ARRETERSIMULATEUR = new AbstractAction("Arreter le simulateur de Robot", Icones.SIMULATEUR_32) {
            public void actionPerformed(ActionEvent e) {
                H3Etudiant.arreterSimulateur();
            }
        };
        ARRETERSIMULATEUR.setEnabled(false);
        DEMARRERSIMULATEUR = new AbstractAction("Démarrer le simulateur de Robot", Icones.SIMULATEUR_32) {
            public void actionPerformed(ActionEvent e) {
                H3Etudiant.demarrerSimulateur();
            }
        };
        DEMARRERSIMULATEUR.setEnabled(false);
        MODESIMULATEUR = new AbstractAction("Passer en mode simulateur", Icones.SIMULATEUR_32) {
            public void actionPerformed(ActionEvent e) {
                ProjetNxc.setSIMULATEUR(true);
                this.setEnabled(false);
                if (H3Etudiant.getFenetre().getEditeur().getPageActuelle() != null) {
                    H3Etudiant.getFenetre().getEditeur().getPageActuelle().getFichier().setaEteModifie(true);
                }

                H3Etudiant.getLogger().info("H3Etudiant::se connecter au serveur codelab : ");
                if (H3Etudiant.getSocketEtudiant() == null) {
                    try {
                        Socket socket = new Socket("localhost", 9000);
                        H3Etudiant.setSocketEtudiant(socket);
                        H3Etudiant.setOutEtudiant(new ObjectOutputStream(socket.getOutputStream()));
                    } catch (IOException var4) {
                        var4.printStackTrace();
                        this.setEnabled(true);
                    }
                }

                (new Thread() {
                    public void run() {
                        H3Etudiant.setCodeLabWS(H3Commun.ouvrirCodeLabs(H3Etudiant.getFenetre(), H3Etudiant.getOutEtudiant()));
                    }
                }).start();
            }
        };
        MODESIMULATEUR.putValue("ShortDescription", "Passer en Mode simulateur");
        MODESIMULATEUR.putValue("AcceleratorKey", KeyStroke.getKeyStroke(83, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        MODESIMULATEUR.setEnabled(true);
        MODEROBOT = new AbstractAction("Utiliser le Robot", Icones.ROBOT_32) {
            public void actionPerformed(ActionEvent e) {
                ProjetNxc.setSIMULATEUR(false);
                this.setEnabled(false);
                Actions.MODESIMULATEUR.setEnabled(true);
                Actions.COMPILER.setEnabled(true);
                Actions.DEMARRERSIMULATEUR.setEnabled(false);
                Actions.ARRETERSIMULATEUR.setEnabled(false);
            }
        };
        MODEROBOT.setEnabled(false);
        CHANGERPASSWORD = new AbstractAction("Changer de Mot de Passe", Icones.UTILISATEUR_32) {
            public void actionPerformed(ActionEvent e) {
                H3Commun.changerDeMotDePasse(H3Etudiant.getFenetre(), H3Etudiant.getLogger(), H3Etudiant.getOut(), H3Etudiant.getEtudiant().getUsername(), false);
            }
        };
        RENOMMER_FICHIER = new AbstractAction("Renommer le fichier", Icones.FICHIER_32) {
            public void actionPerformed(ActionEvent arg0) {
                Fichier fichier = H3Etudiant.getFenetre().getExplorateur().getFichier();
                Projet projet = H3Etudiant.getFenetre().getExplorateur().getProjet();
                String typeFichier = fichier.getType();
                String extension = "";
                int posPoint = fichier.getNom().indexOf(46);
                if (posPoint != -1) {
                    extension = fichier.getNom().substring(posPoint);
                }

                H3Etudiant.getLogger().info("Nom du Fichier : " + fichier.getNom() + " // Extension : " + extension);
                String leContenuDuFichier = "";

                try {
                    leContenuDuFichier = fichier.getSource().getText(0, fichier.getSource().getLength());
                } catch (BadLocationException var16) {
                    var16.printStackTrace();
                }

                boolean fichierGlade = fichier.getNom().endsWith(".glade");
                DialogueRenommerFichier dialogue = new DialogueRenommerFichier(H3Etudiant.getFenetre(), "Renommer Fichier", fichier.getNom(), projet);
                String nouveauNomSansExtension = null;
                if (dialogue.afficher() == 2) {
                    nouveauNomSansExtension = dialogue.getNouveauNom();
                }

                if (nouveauNomSansExtension != null) {
                    String nouveauNomAvecExtension = nouveauNomSansExtension + extension;
                    H3Etudiant.getLogger().info("Nom du nouveau Fichier : " + nouveauNomAvecExtension);
                    if (projet.getFichierParNom(nouveauNomAvecExtension) != null) {
                        JOptionPane.showMessageDialog((Component)null, "Le fichier '" + nouveauNomAvecExtension + "'\nexiste déjà dans\nce projet", "Attention !!!", 0, Icones.ERREUR_32);
                    } else {
                        String nouveauNom = nouveauNomSansExtension.replace(" ", "").replace(".", "");
                        Actions.supprimerFichierSelectionne();
                        Fichier fichierNouveau = null;
                        if (projet instanceof ProjetJava) {
                            if (fichier.getType().equals("Java")) {
                                fichierNouveau = new FichierJava((ProjetJava)projet, nouveauNom + ".java", true, false);
                            } else {
                                fichierNouveau = new FichierAutre((ProjetJava)projet, nouveauNomAvecExtension);
                            }
                        } else if (projet instanceof ProjetRuby) {
                            if (fichierGlade) {
                                fichierNouveau = new FichierRuby((ProjetRuby)projet, nouveauNom + ".glade", true);
                            } else {
                                fichierNouveau = new FichierRuby((ProjetRuby)projet, nouveauNom + ".rb", true);
                            }
                        } else if (projet instanceof ProjetC) {
                            if (extension.equals(".c")) {
                                fichierNouveau = new FichierC((ProjetC)projet, nouveauNom + ".c", true);
                            } else if (extension.equals(".h")) {
                                fichierNouveau = new FichierH((ProjetC)projet, nouveauNom + ".h", true);
                            } else {
                                fichierNouveau = new FichierMF((ProjetC)projet, fichier.getNom(), true);
                            }
                        } else if (projet instanceof ProjetNxc) {
                            fichierNouveau = new FichierNxc((ProjetNxc)projet, nouveauNom + ".nxc", true);
                        } else if (projet instanceof ProjetSpiC) {
                            fichierNouveau = new FichierSpiC((ProjetSpiC)projet, nouveauNom + ".c", true);
                        } else if (projet instanceof ProjetPython) {
                            fichierNouveau = new FichierPython((ProjetPython)projet, nouveauNom + ".py", true);
                        } else if (projet instanceof ProjetScala) {
                            fichierNouveau = new FichierScala((ProjetScala)projet, nouveauNom + ".scala", true);
                        }

                        try {
                            ((Fichier)fichierNouveau).getSource().insertString(0, leContenuDuFichier, (AttributeSet)null);
                        } catch (BadLocationException var15) {
                            var15.printStackTrace();
                        }

                        ((DefaultTreeModel)H3Etudiant.getFenetre().getExplorateur().getModel()).reload();
                        Actions.ouvrirClasseDansEditeur((Fichier)fichierNouveau);
                    }
                }

            }
        };
        RENOMMER_FICHIER.putValue("ShortDescription", "Renommer le Fichier sélectionné");
        RENOMMER_FICHIER.setEnabled(true);
        EFFACER_CONSOLE = new AbstractAction("Effacer la console", Icones.GOMME_32) {
            public void actionPerformed(ActionEvent arg0) {
                H3Etudiant.getFenetre().getConsole().efface();
            }
        };
        EFFACER_CONSOLE.putValue("ShortDescription", "Effacer le texte dans la console");
        OUVRIR_TERMINAL = new AbstractAction("Ouvrir un terminal dans le workspace", Icones.TERMINAL_32) {
            public void actionPerformed(ActionEvent arg0) {
                (new Thread() {
                    public void run() {
                        H3Etudiant.getFenetre().getEditeur().enregistrerTout();

                        try {
                            String rep = H3Etudiant.getFenetre().getExplorateur().getProjet().getRepertoire();
                            String commandeExecution = null;
                            H3Etudiant.getLogger().info("Ouverture d'un terminal, System : " + System.getProperty("os.name"));
                            if (System.getProperty("os.name").indexOf("Windows") != -1) {
                                commandeExecution = H3Etudiant.DOSSIER_OUTILS + "Hop3xExecuteIci.cmd " + rep + " cmd.exe /c start";
                                H3Etudiant.getLogger().info("Ouverture d'un Terminal : " + commandeExecution);
                            } else if (System.getProperty("os.name").indexOf("Mac") != -1) {
                                commandeExecution = "./" + H3Etudiant.DOSSIER_OUTILS + "Hop3xExecuteIci " + rep + " /usr/X11/bin/xterm -n";
                                H3Etudiant.getLogger().info("Ouverture d'un Terminal : " + commandeExecution);
                            } else if (System.getProperty("os.name").indexOf("Linux") != -1) {
                                commandeExecution = "./" + H3Etudiant.DOSSIER_OUTILS + "Hop3xExecuteIci " + rep + " xterm -n";
                                H3Etudiant.getLogger().info("Ouverture d'un Terminal : " + commandeExecution);
                            }

                            Process p = Runtime.getRuntime().exec(commandeExecution);
                            int var4 = p.waitFor();
                        } catch (InterruptedException var5) {
                            var5.printStackTrace();
                        } catch (IOException var6) {
                            var6.printStackTrace();
                        }

                    }
                }).start();
            }
        };
        OUVRIR_TERMINAL.putValue("ShortDescription", "Ouvrir un Terminal dans le Workspace");
        OUVRIR_TERMINAL.putValue("AcceleratorKey", KeyStroke.getKeyStroke(84, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() + 64));
        PRECISERCOMMANDERUBY = new AbstractAction("Ligne de commande Ruby") {
            public void actionPerformed(ActionEvent arg0) {
                String lc = ProjetRuby.getCommandeRuby();
                String resultat = JOptionPane.showInputDialog(H3Etudiant.getFenetre(), "                                             Ligne de commande pour Ruby                                             ", lc);
                if (resultat != null) {
                    ProjetRuby.setCommandeRuby(resultat);
                }

                H3Etudiant.getLogger().info("Nouvelle ligne de commande Ruby : " + ProjetRuby.getCommandeRuby());
            }
        };
        PRECISERCOMMANDERGLADE = new AbstractAction("Ligne de commande Glade") {
            public void actionPerformed(ActionEvent arg0) {
                ProjetRuby pr = (ProjetRuby)H3Etudiant.getFenetre().getExplorateur().getProjet();
                String lc = ProjetRuby.getCommandeGlade();
                String resultat = JOptionPane.showInputDialog(H3Etudiant.getFenetre(), "                                                                            Ligne de commande Glade                                                                            ", lc);
                if (resultat != null) {
                    ProjetRuby.setCommandeGlade(resultat + " ");
                }

                H3Etudiant.getLogger().info("Nouvelle ligne de commande glade : " + ProjetRuby.getCommandeGlade());
            }
        };
        NOUVEAUGLADE = new AbstractAction("Nouvelle Interface Glade") {
            public void actionPerformed(ActionEvent arg0) {
                ProjetRuby pr = (ProjetRuby)H3Etudiant.getFenetre().getExplorateur().getProjet();
                String nomFichier = JOptionPane.showInputDialog(H3Etudiant.getFenetre(), "Nom du fichier pour l'interface", pr.getNom());
                if (nomFichier != null) {
                    if (nomFichier.endsWith(".rb")) {
                        nomFichier = nomFichier.replace(".rb", "");
                    }

                    String nomFichierRuby = nomFichier + ".rb";
                    String nomFichierGlade = nomFichier + ".glade";
                    FichierRuby f = new FichierRuby(pr, nomFichierRuby);

                    try {
                        f.getSource().remove(0, f.getSource().getLength());
                        FileInputStream fs = new FileInputStream(H3Etudiant.DOSSIER_OUTILS + "glade.rb");
                        StringBuffer texte = new StringBuffer();
                        InputStreamReader reader = new InputStreamReader(fs, "UTF-8");
                        boolean var10 = false;

                        int c;
                        while((c = reader.read()) != -1) {
                            texte.insert(texte.length(), (char)c);
                        }

                        reader.close();
                        f.getSource().insertString(0, texte.toString(), (AttributeSet)null);
                        Actions.ouvrirClasseDansEditeur(f);
                        f = new FichierRuby(pr, nomFichierGlade);
                        f.getSource().remove(0, f.getSource().getLength());
                        fs = new FileInputStream(H3Etudiant.DOSSIER_OUTILS + "glade.glade");
                        texte = new StringBuffer();
                        reader = new InputStreamReader(fs, "UTF-8");
                        var10 = false;

                        while((c = reader.read()) != -1) {
                            texte.insert(texte.length(), (char)c);
                        }

                        reader.close();
                        f.getSource().insertString(0, texte.toString(), (AttributeSet)null);
                        Actions.ouvrirClasseDansEditeur(f);
                        Actions.enregistrerLesFichiersDuProjets();
                    } catch (FileNotFoundException var11) {
                        var11.printStackTrace();
                    } catch (UnsupportedEncodingException var12) {
                        var12.printStackTrace();
                    } catch (IOException var13) {
                        var13.printStackTrace();
                    } catch (BadLocationException var14) {
                        var14.printStackTrace();
                    }
                }

            }
        };
        LANCERGLADE = new AbstractAction("Ouvrir Glade", Icones.GLADE_32) {
            public void actionPerformed(ActionEvent arg0) {
                Actions.enregistrerLesFichiersDuProjets();
                ProjetRuby pr = (ProjetRuby)H3Etudiant.getFenetre().getExplorateur().getProjet();
                FichierRuby fichierGlade = (FichierRuby)H3Etudiant.getFenetre().getExplorateur().getFichier();
                String nomDuFichierGlade = fichierGlade.getFichierSource();
                if (!nomDuFichierGlade.endsWith(".glade")) {
                    JOptionPane.showMessageDialog(H3Etudiant.getFenetre(), "Ce n'est pas un fichier glade");
                } else {
                    File fichier = new File(nomDuFichierGlade);

                    try {
                        H3Etudiant.getLogger().info("Lancement de Glade : " + ProjetRuby.getCommandeGlade() + fichier.getAbsolutePath());
                        Process p = Runtime.getRuntime().exec(ProjetRuby.getCommandeGlade() + fichier.getAbsolutePath());

                        try {
                            p.waitFor();
                        } catch (InterruptedException var11) {
                            var11.printStackTrace();
                        }
                    } catch (IOException var12) {
                        var12.printStackTrace();
                    }

                    Fichier f = pr.getFichierParNom(fichierGlade.getNom().replace(".rb", ".glade"));

                    try {
                        f.getSource().remove(0, f.getSource().getLength());
                        FileInputStream fs = new FileInputStream(nomDuFichierGlade);
                        StringBuffer texte = new StringBuffer();
                        InputStreamReader reader = new InputStreamReader(fs, "UTF-8");
                        boolean var10 = false;

                        int c;
                        while((c = reader.read()) != -1) {
                            texte.insert(texte.length(), (char)c);
                        }

                        reader.close();
                        f.getSource().insertString(0, texte.toString(), (AttributeSet)null);
                        Actions.ouvrirClasseDansEditeur(f);
                    } catch (BadLocationException var13) {
                        var13.printStackTrace();
                    } catch (FileNotFoundException var14) {
                        var14.printStackTrace();
                    } catch (UnsupportedEncodingException var15) {
                        var15.printStackTrace();
                    } catch (IOException var16) {
                        var16.printStackTrace();
                    }
                }

            }
        };
        PRECISERCOMMANDERDOC = new AbstractAction("Ligne de commande pour rdoc") {
            public void actionPerformed(ActionEvent arg0) {
                ProjetRuby pr = (ProjetRuby)H3Etudiant.getFenetre().getExplorateur().getProjet();
                String lc = pr.getCommandeDoc();
                String resultat = JOptionPane.showInputDialog(H3Etudiant.getFenetre(), "                                                                            Ligne de commande rdoc                                                                            ", lc);
                if (resultat != null) {
                    pr.setCommandeDoc(resultat);
                }

                H3Etudiant.getLogger().info("Nouvelle ligne de commande rdoc : " + pr.getCommandeDoc());
            }
        };
        PRECISERVISURDOC = new AbstractAction("Ligne de commande pour visualiser la doc") {
            public void actionPerformed(ActionEvent arg0) {
                ProjetRuby pr = (ProjetRuby)H3Etudiant.getFenetre().getExplorateur().getProjet();
                String lc = pr.getCommandeVisuDoc();
                String resultat = JOptionPane.showInputDialog(H3Etudiant.getFenetre(), "                         Ligne de commande de visualisation doc                         ", lc);
                if (resultat != null) {
                    pr.setCommandeVisuDoc(resultat);
                }

                H3Etudiant.getLogger().info("Nouvelle ligne de commande pour la visualisation de la doc : " + pr.getCommandeVisuDoc());
            }
        };
        GENERER_RUBYDOC = new AbstractAction("Générer la rdoc") {
            public void actionPerformed(ActionEvent e) {
                Projet projet = H3Etudiant.getFenetre().getExplorateur().getProjet();
                projet.enregistrer();
                Actions.deleterep(new File(projet.getRepertoire() + "doc/"));

                Process p;
                try {
                    H3Etudiant.getLogger().info("Commande rdoc = " + ((ProjetRuby)projet).getCommandeDoc());
                    p = Runtime.getRuntime().exec(((ProjetRuby)projet).getCommandeDoc());
                    boolean stop = false;

                    while(!stop) {
                        try {
                            p.exitValue();
                            stop = true;
                        } catch (Exception var7) {
                        }
                    }
                } catch (IOException var8) {
                    H3Etudiant.getLogger().severe("Impossible de générer la Rdoc " + var8.getMessage());
                }

                try {
                    H3Etudiant.getLogger().info("Commande de Visualisation : " + ((ProjetRuby)projet).getCommandeVisuDoc());
                    p = Runtime.getRuntime().exec(((ProjetRuby)projet).getCommandeVisuDoc());
                } catch (IOException var6) {
                    H3Etudiant.getLogger().severe("Impossible de visualiser la Rdoc " + var6.getMessage());
                }

            }
        };
        GENERER_RUBYDOC.putValue("ShortDescription", "Générer la documentation du projet avec rdoc");
        GENERER_RUBYDOC.putValue("AcceleratorKey", KeyStroke.getKeyStroke(68, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() + 64));
        GENERER_RUBYDOC.setEnabled(true);
        IMPORTERREPERTOIRE = new AbstractAction("Importer les sources d'un répertoire", Icones.IMPORTER_32) {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(1);
                fc.showOpenDialog(H3Etudiant.getFenetre());
                File repertoire = fc.getSelectedFile();
                Actions.importerRepertoire(repertoire);
            }
        };
        IMPORTERREPERTOIRE.putValue("ShortDescription", "Importer les fichiers d'un répertoire");
        IMPORTERREPERTOIRE.putValue("AcceleratorKey", KeyStroke.getKeyStroke(73, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() + 64));
        IMPORTERREPERTOIRE.setEnabled(true);
        MAKECIBLE_C = new AbstractAction("Préciser une cible pour Make") {
            public void actionPerformed(ActionEvent arg0) {
                ProjetC pc = (ProjetC)H3Etudiant.getFenetre().getExplorateur().getProjet();
                String lc = pc.getCibleMake();
                String resultat = JOptionPane.showInputDialog(H3Etudiant.getFenetre(), "Cible Make", lc);
                if (resultat != null) {
                    pc.setCibleMake(resultat);
                }

                H3Etudiant.getLogger().info("Nouvelle Cible Make : " + pc.getCibleMake());
                Actions.COMPILER.setEnabled(true);
            }
        };
        EXECUTIONCOMMANDE_C = new AbstractAction("Changer la commande pour l'éxécution") {
            public void actionPerformed(ActionEvent arg0) {
                ProjetC pc = (ProjetC)H3Etudiant.getProjets().get(0);
                String lc = pc.getCommandeExecution();
                String resultat = JOptionPane.showInputDialog(H3Etudiant.getFenetre(), "Ligne de commande Exécution", lc);
                if (resultat != null) {
                    pc.setCommandeExecution(resultat);
                }

                H3Etudiant.getLogger().info("Nouvelle ligne de commande Exécution : " + pc.getCommandeExecution());
            }
        };
        COMPILATIONCOMMANDE_C = new AbstractAction("Changer la commande pour la compilation") {
            public void actionPerformed(ActionEvent arg0) {
                ProjetC pc = (ProjetC)H3Etudiant.getProjets().get(0);
                String lc = pc.getCommandeCompilation();
                String resultat = JOptionPane.showInputDialog(H3Etudiant.getFenetre(), "Ligne de commande Compilation", lc);
                if (resultat != null) {
                    pc.setCommandeCompilation(resultat);
                }

                H3Etudiant.getLogger().info("Nouvelle ligne de commande Compilation : " + pc.getCommandeCompilation());
            }
        };
        MONTRERHISTORIQUEMESSAGE = new AbstractAction("Montrer l'Historique des Messages reçus", Icones.MONTRERHISTORIQUE_32) {
            public void actionPerformed(ActionEvent e) {
                (new DialogueHistoriqueInterventionsEnseignant(H3Etudiant.getFenetre(), "message", H3Etudiant.getHistoriqueMessage(), false)).afficher();
            }
        };
        MONTRERHISTORIQUEMESSAGE.setEnabled(false);
        MONTRERHISTORIQUEMESSAGE.putValue("MnemonicKey", 77);
        MONTRERHISTORIQUEMESSAGE.putValue("ShortDescription", "Historique des interventions de l'enseignant");
        MONTRERHISTORIQUEMESSAGE.putValue("AcceleratorKey", KeyStroke.getKeyStroke(77, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        AUSECOURS = new AbstractAction("J'ai besoin d'aide", Icones.ASSISTANCE_32) {
            public void actionPerformed(ActionEvent e) {
                String motifAppel = JOptionPane.showInputDialog((Component)null, "Précisez le motif de la demande d'aide :");
                if (motifAppel == null) {
                    JOptionPane.showMessageDialog((Component)null, "Demande d'aide Annulée", "Attention !!", 1);
                } else {
                    H3Etudiant.appelerAUSecours(motifAppel);
                }

            }
        };
        RESTAURER = new AbstractAction("Restaurer la trace", Icones.RESTAURER_32) {
            public void actionPerformed(ActionEvent e) {
                JFileChooser dialogue = new JFileChooser();
                dialogue.setDialogTitle("Choisir un fichier de trace Hop3x à restaurer");
                dialogue.setCurrentDirectory(new File(H3Etudiant.getTraceur().DOSSIER_SESSION));
                dialogue.setApproveButtonText("Restaurer le fichier");
                dialogue.setFileFilter(new FileFilter() {
                    public boolean accept(File arg0) {
                        return arg0.getName().endsWith("h3x");
                    }

                    public String getDescription() {
                        return "Fichiers trace Hop3x";
                    }
                });
                if (dialogue.showOpenDialog((Component)null) == 0) {
                    String fichier = dialogue.getSelectedFile().getAbsolutePath();
                    H3Etudiant.envoyerTraceLocale(fichier);
                }

            }
        };
        RESTAURER.putValue("ShortDescription", "Restauration sur le serveur d'un fichier de trace local");
        RESTAURER.setEnabled(true);
        LIGNEDECOMMANDE = new AbstractAction("Exécuter avec des arguments", Icones.ARGUMENTS_32) {
            public void actionPerformed(ActionEvent e) {
                DialogueLigneDeCommande dialogue = new DialogueLigneDeCommande(H3Etudiant.getFenetre(), H3Etudiant.getFenetre().getExplorateur().getProjet().getParametreLigneDeCommande());
                if (dialogue.afficher() == 2) {
                    String param = dialogue.getChampParametres();
                    H3Etudiant.getFenetre().getExplorateur().getProjet().setParametreLigneDeCommande(param);
                    Actions.EXECUTER.actionPerformed(e);
                }

            }
        };
        LIGNEDECOMMANDE.putValue("ShortDescription", "Entrer les paramètres de la ligne de Commande");
        LIGNEDECOMMANDE.putValue("AcceleratorKey", KeyStroke.getKeyStroke(82, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() + 64));
        LIGNEDECOMMANDE.setEnabled(true);
        DECALERADROITE = new AbstractAction("Décaler vers la Droite", Icones.FLECHESDROITES_32) {
            public void actionPerformed(ActionEvent e) {
                String aDecaler = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getSelection();
                if (aDecaler != null) {
                    aDecaler = "\t" + aDecaler.replaceAll("\n", "\n\t");
                    if (aDecaler.lastIndexOf("\n\t") == aDecaler.length() - 2) {
                        aDecaler = aDecaler.substring(0, aDecaler.length() - 1);
                    }

                    H3Etudiant.getFenetre().getEditeur().getPageActuelle().couper();
                    int position = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().getCaretPosition();

                    try {
                        H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().getDocument().insertString(position, aDecaler, (AttributeSet)null);
                        H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().setSelectionStart(position);
                        H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().setSelectionEnd(position + aDecaler.length());
                    } catch (BadLocationException var5) {
                        var5.printStackTrace();
                    }
                }

            }
        };
        DECALERADROITE.putValue("ShortDescription", "Décale la selection d'une tabulation à droite");
        DECALERADROITE.putValue("AcceleratorKey", KeyStroke.getKeyStroke(9, 0));
        DECALERADROITE.setEnabled(false);
        DECALERAGAUCHE = new AbstractAction("Décaler vers la Gauche", Icones.FLECHESGAUCHES_32) {
            public void actionPerformed(ActionEvent e) {
                String aDecaler = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getSelection();
                if (aDecaler != null && aDecaler.charAt(0) == '\t') {
                    aDecaler = aDecaler.substring(1);
                    aDecaler = aDecaler.replaceAll("\n\t", "\n");
                    H3Etudiant.getFenetre().getEditeur().getPageActuelle().couper();
                    int position = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().getCaretPosition();

                    try {
                        H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().getDocument().insertString(position, aDecaler, (AttributeSet)null);
                        H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().setSelectionStart(position);
                        H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().setSelectionEnd(position + aDecaler.length());
                    } catch (BadLocationException var5) {
                        var5.printStackTrace();
                    }
                }

            }
        };
        DECALERAGAUCHE.putValue("ShortDescription", "Décale la selection d'une tabulation à gauche");
        DECALERAGAUCHE.putValue("AcceleratorKey", KeyStroke.getKeyStroke(9, 64));
        DECALERAGAUCHE.setEnabled(false);
        COMMENTERCODE = new AbstractAction("Commente la selection", Icones.COMMENTE_32) {
            public void actionPerformed(ActionEvent e) {
                String aCommenter = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getSelection();
                String texteSource = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().getText();
                String carCommentaire = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getFichier().getCommentaire();
                int positionDebutSelection = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().getPositionDebut();
                int positionFinSelection = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().getPositionFin();
                if (aCommenter != null) {
                    int positionDebutLigne;
                    if (positionDebutSelection > positionFinSelection) {
                        positionDebutLigne = positionFinSelection;
                        positionFinSelection = positionDebutSelection;
                        positionDebutSelection = positionDebutLigne;
                    } else if (positionDebutSelection == positionFinSelection) {
                        positionDebutSelection = positionFinSelection - aCommenter.length();
                    }

                    --positionFinSelection;
                    H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().setCaretPosition(positionFinSelection);
                    positionDebutLigne = positionDebutSelection;
                    if (aCommenter != null) {
                        while(positionDebutLigne > 0 && texteSource.charAt(positionDebutLigne) != '\n') {
                            --positionDebutLigne;
                            aCommenter = texteSource.charAt(positionDebutLigne) + aCommenter;
                        }

                        H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().setPositionDebut(positionDebutLigne);
                        H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().select(positionDebutLigne, positionFinSelection + 1);
                        aCommenter = aCommenter.replaceAll("\n", "\n" + carCommentaire + " ");
                        H3Etudiant.getFenetre().getEditeur().getPageActuelle().couper();
                        int position = positionDebutLigne;

                        try {
                            H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().getDocument().insertString(position, aCommenter, (AttributeSet)null);
                            H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().setCaretPosition(position + aCommenter.length());
                            H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().setPositionFin(positionDebutLigne + aCommenter.length());
                            H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().setSelectionStart(position);
                            H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().setSelectionEnd(position + aCommenter.length());
                        } catch (BadLocationException var10) {
                            var10.printStackTrace();
                        }
                    }
                }

            }
        };
        COMMENTERCODE.putValue("ShortDescription", "Commente la selection");
        COMMENTERCODE.putValue("AcceleratorKey", KeyStroke.getKeyStroke(87, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() + 64));
        COMMENTERCODE.setEnabled(true);
        DECOMMENTERCODE = new AbstractAction("Décommente la selection", Icones.DECOMMENTE_32) {
            public void actionPerformed(ActionEvent e) {
                String aDecommenter = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getSelection();
                String texteSource = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().getText();
                String carCommentaire = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getFichier().getCommentaire();
                if (aDecommenter != null) {
                    int positionDebutSelection = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().getPositionDebut();
                    int positionFinSelection = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().getPositionFin();
                    int positionDebutLigne;
                    if (positionDebutSelection > positionFinSelection) {
                        positionDebutLigne = positionFinSelection;
                        positionFinSelection = positionDebutSelection;
                        positionDebutSelection = positionDebutLigne;
                    } else if (positionDebutSelection == positionFinSelection) {
                        positionDebutSelection = positionFinSelection - aDecommenter.length();
                    }

                    for(positionDebutLigne = positionDebutSelection; positionDebutLigne > 0 && texteSource.charAt(positionDebutLigne) != '\n'; aDecommenter = texteSource.charAt(positionDebutLigne) + aDecommenter) {
                        --positionDebutLigne;
                    }

                    H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().select(positionDebutLigne, positionFinSelection);
                    if (aDecommenter.indexOf(carCommentaire) == 1) {
                        aDecommenter = aDecommenter.replaceAll(carCommentaire + " ", "");
                        H3Etudiant.getFenetre().getEditeur().getPageActuelle().couper();
                        int position = positionDebutLigne;

                        try {
                            H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().getDocument().insertString(position, aDecommenter, (AttributeSet)null);
                            H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().setCaretPosition(position + aDecommenter.length());
                            H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().setPositionFin(positionDebutLigne + aDecommenter.length());
                            H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().setSelectionStart(position);
                            H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().setSelectionEnd(position + aDecommenter.length());
                        } catch (BadLocationException var10) {
                            var10.printStackTrace();
                        }
                    }
                }

            }
        };
        DECOMMENTERCODE.putValue("ShortDescription", "Decommente la selection");
        DECOMMENTERCODE.putValue("AcceleratorKey", KeyStroke.getKeyStroke(88, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() + 64));
        DECOMMENTERCODE.setEnabled(true);
        REDO = new AbstractAction("Refaire la dernière Action", Icones.REDO_32) {
            public void actionPerformed(ActionEvent arg0) {
                if (H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().canRedo()) {
                    H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().redoLastAction();
                }

            }
        };
        REDO.putValue("ShortDescription", "Refaire la dernière Action");
        REDO.putValue("AcceleratorKey", KeyStroke.getKeyStroke(90, 320));
        UNDO = new AbstractAction("Annuler la dernière Action", Icones.UNDO_32) {
            public void actionPerformed(ActionEvent arg0) {
                if (H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().canUndo()) {
                    H3Etudiant.getFenetre().getEditeur().getPageActuelle().getrSyntaxCodeSource().undoLastAction();
                }

            }
        };
        UNDO.putValue("ShortDescription", "Annuler la dernière Action");
        UNDO.putValue("AcceleratorKey", KeyStroke.getKeyStroke(90, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        NOUVEAU_PROJET = new AbstractAction("Nouveau projet...", Icones.NOUVEAU_PROJET_32) {
            public void actionPerformed(ActionEvent arg0) {
                Actions.nouveauProjet((String)null);
            }
        };
        NOUVEAU_PROJET.putValue("MnemonicKey", 80);
        NOUVEAU_PROJET.putValue("ShortDescription", "Cree un nouveau projet");
        NOUVEAU_FICHIER = new AbstractAction("Nouveau fichier...", Icones.NOUVEAU_FICHIER_32) {
            public void actionPerformed(ActionEvent arg0) {
                DialogueFichier dialogue = new DialogueFichier(H3Etudiant.getFenetre(), H3Etudiant.getFenetre().getExplorateur().getProjet());
                if (dialogue.afficher() == 2) {
                    Projet projet = H3Etudiant.getFenetre().getExplorateur().getProjet();
                    if (projet.getFichierParNom(dialogue.getFichier()) != null) {
                        JOptionPane.showMessageDialog((Component)null, "Le fichier '" + dialogue.getFichier() + "'\nexiste déjà dans\nce projet", "Attention !!!", 0, Icones.ERREUR_32);
                        Actions.ouvrirClasseDansEditeur(projet.getFichierParNom(dialogue.getFichier()));
                    } else {
                        Fichier fichier = null;
                        if (dialogue.isFichierAutre()) {
                            fichier = new FichierAutre(projet, dialogue.getFichier());
                        } else if (projet instanceof ProjetJava) {
                            H3Etudiant.getLogger().info("" + dialogue.isInterface());
                            fichier = new FichierJava((ProjetJava)projet, dialogue.getFichier(), false, dialogue.isInterface());
                        } else if (projet instanceof ProjetRuby) {
                            fichier = new FichierRuby((ProjetRuby)projet, dialogue.getFichier());
                        } else if (projet instanceof ProjetC) {
                            if (dialogue.isC()) {
                                fichier = new FichierC((ProjetC)projet, dialogue.getFichier(), false);
                            } else if (dialogue.isH()) {
                                fichier = new FichierH((ProjetC)projet, dialogue.getFichier(), false);
                            } else {
                                fichier = new FichierMF((ProjetC)projet, dialogue.getFichier(), false);
                            }
                        } else if (projet instanceof ProjetNxc) {
                            fichier = new FichierNxc((ProjetNxc)projet, dialogue.getFichier(), false);
                        } else if (projet instanceof ProjetSpiC) {
                            fichier = new FichierSpiC((ProjetSpiC)projet, dialogue.getFichier(), false);
                        } else if (projet instanceof ProjetPython) {
                            fichier = new FichierPython((ProjetPython)projet, dialogue.getFichier(), false);
                        } else if (projet instanceof ProjetScala) {
                            fichier = new FichierScala((ProjetScala)projet, dialogue.getFichier(), false);
                        } else if (projet instanceof ProjetEv3) {
                            fichier = new FichierEv3((ProjetEv3)projet, dialogue.getFichier(), false);
                        }

                        H3Etudiant.getLogger().info("Ajout du fichier " + ((Fichier)fichier).getNom());
                        Actions.ouvrirClasseDansEditeur((Fichier)fichier);
                    }
                }

            }
        };
        NOUVEAU_FICHIER.putValue("MnemonicKey", 70);
        NOUVEAU_FICHIER.putValue("ShortDescription", "Cree un nouveau fichier");
        NOUVEAU_FICHIER.setEnabled(false);
        COMPILER = new AbstractAction("Compiler", Icones.COMPILER_32) {
            public void actionPerformed(ActionEvent arg0) {
                Projet projet = H3Etudiant.getFenetre().getExplorateur().getProjet();
                Actions.enregistrerLesFichiersDuProjets();
                if (projet.isCompilationProjet()) {
                    projet.compiler(true);
                } else {
                    projet.compilerUnFichier(true, H3Etudiant.getFenetre().getEditeur().getPageActuelle().getFichier());
                }

                this.setEnabled(false);
            }
        };
        COMPILER.putValue("MnemonicKey", 67);
        COMPILER.putValue("ShortDescription", "Compiler");
        COMPILER.putValue("AcceleratorKey", KeyStroke.getKeyStroke(66, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        COMPILER.setEnabled(false);
        EXECUTER = new AbstractAction("Executer", Icones.EXECUTER_32) {
            public void actionPerformed(ActionEvent e) {
                Projet projet = H3Etudiant.getFenetre().getExplorateur().getProjet();
                String paraLdC = "";
                String actionCommande = e.getActionCommand();
                if ("Exécuter avec des arguments".equals(actionCommande)) {
                    paraLdC = projet.getParametreLigneDeCommande();
                }

                Actions.enregistrerLesFichiersDuProjets();
                String fichierAffiche = H3Etudiant.getFenetre().getEditeur().getPageActuelle().getFichier().getNom();
                int resultatCompil = 0;
                int resultatCompilx;
                if (!projet.isCompilationProjet()) {
                    resultatCompilx = projet.compilerUnFichier(false, projet.getFichierParNom(fichierAffiche));
                } else {
                    resultatCompilx = projet.compiler(false);
                }

                if (resultatCompilx == 0) {
                    List<String> mains = projet.getFichiersMain();
                    if (!projet.isCompilationProjet()) {
                        Iterator i = mains.iterator();

                        while(i.hasNext()) {
                            String s = (String)i.next();
                            if (!s.equals(fichierAffiche)) {
                                i.remove();
                            }
                        }
                    }

                    if (mains.size() == 0) {
                        String message = "Pas de fichier exécutable";
                        H3Etudiant.getFenetre().getConsole().executionProjet(projet, message + "\n");
                        Evenement evenement = Evenement.getEvenementExecution(projet.getNom(), message);
                        H3Etudiant.getTraceur().envoyer(evenement);
                        JOptionPane.showMessageDialog(H3Etudiant.getFenetre(), message, "Erreur !!!", 0);
                    } else if (mains.size() == 1) {
                        Actions.COMPILER.setEnabled(false);
                        H3Etudiant.getLogger().info("Parametres fournis : " + paraLdC);
                        projet.executer((String)mains.get(0) + " " + paraLdC);
                    } else {
                        DialogueExecuter dialogue = new DialogueExecuter(H3Etudiant.getFenetre(), mains);
                        if (dialogue.afficher() == 2) {
                            Actions.COMPILER.setEnabled(false);
                            projet.executer(dialogue.getClasse() + " " + paraLdC);
                        }
                    }
                }

            }
        };
        EXECUTER.putValue("MnemonicKey", 69);
        EXECUTER.putValue("ShortDescription", "Exécuter");
        EXECUTER.putValue("AcceleratorKey", KeyStroke.getKeyStroke(82, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        EXECUTER.setEnabled(false);
        QUITTER = new AbstractAction("Quitter", Icones.QUITTER_32) {
            public void actionPerformed(ActionEvent arg0) {
                if (Main.isSimulation()) {
                    H3Etudiant.getFenetre().quitter(false);
                } else {
                    H3Etudiant.getFenetre().quitter(true);
                }

            }
        };
        QUITTER.putValue("MnemonicKey", 81);
        QUITTER.putValue("ShortDescription", "Quitte l'application");
        QUITTER.putValue("AcceleratorKey", KeyStroke.getKeyStroke(81, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        CHANGERDESESSION = new AbstractAction("Changer de Session", Icones.CHOIXSESSION_32) {
            public void actionPerformed(ActionEvent evt) {
                if (JOptionPane.showConfirmDialog(Actions.getFenetre(), "Etes vous sur de vouloir Changer de Session ?", "Attention !!", 0, 2, Icones.INFO_64) == 0) {
                    try {
                        H3Etudiant.getLogger().info("Demande de changement de Session pour " + H3Etudiant.getNomCompletEtudiant());
                        Actions.enregistrerLesFichiersDuProjets();
                        H3Etudiant.setTestunitaire(false);
                        long timeMillis = Calendar.getInstance().getTimeInMillis() - H3Etudiant.getDebutConnexion();
                        String dureeMillis = (new Long(timeMillis)).toString();
                        long heure = timeMillis / 3600000L;
                        long minute = timeMillis % 3600000L / 60000L;
                        long seconde = timeMillis % 3600000L % 60000L / 1000L;
                        String duree = String.format("%dh:%dm:%ds", heure, minute, seconde);
                        H3Etudiant.getLogger().info("Durée de cette Session : " + duree);
                        H3Etudiant.getOut().writeObject(Requete.getRequeteChangerDeSession(H3Etudiant.getEtudiant().getUsername(), H3Etudiant.getNomDeLaSession()));
                    } catch (Exception var12) {
                        H3Etudiant.getLogger().severe("Problème lors de changement de session : de " + H3Etudiant.getEtudiant().getNomComplet() + " / " + H3Etudiant.getEtudiant().getUsername() + " / " + H3Etudiant.getNomDeLaSession());
                        var12.printStackTrace();
                    }
                }

            }
        };
        CHANGERDESESSION.putValue("MnemonicKey", 68);
        CHANGERDESESSION.putValue("ShortDescription", "Changer de Session");
        CHANGERDESESSION.setEnabled(true);
        FERMER = new AbstractAction("Fermer", Icones.FERMER_16) {
            public void actionPerformed(ActionEvent evt) {
                H3Etudiant.getFenetre().getEditeur().fermer();
            }
        };
        FERMER.putValue("MnemonicKey", 70);
        FERMER.putValue("ShortDescription", "Ferme le fichier courant");
        FERMER.putValue("AcceleratorKey", KeyStroke.getKeyStroke(70, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        FERMER.setEnabled(false);
        FERMER_TOUT = new AbstractAction("Fermer tout", Icones.FERMER_TOUT_16) {
            public void actionPerformed(ActionEvent evt) {
                H3Etudiant.getFenetre().getEditeur().fermerTout();
            }
        };
        FERMER_TOUT.putValue("MnemonicKey", 69);
        FERMER_TOUT.putValue("ShortDescription", "Ferme tout les fichiers ouverts");
        FERMER_TOUT.putValue("AcceleratorKey", KeyStroke.getKeyStroke(70, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() + 64));
        FERMER_TOUT.setEnabled(false);
        COUPER = new AbstractAction("Couper", Icones.COUPER_32) {
            public void actionPerformed(ActionEvent evt) {
                H3Etudiant.getFenetre().getEditeur().getPageActuelle().couper();
            }
        };
        COUPER.putValue("MnemonicKey", 85);
        COUPER.putValue("ShortDescription", "Coupe la selection");
        COUPER.putValue("AcceleratorKey", KeyStroke.getKeyStroke(88, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        COUPER.setEnabled(false);
        COPIER = new AbstractAction("Copier", Icones.COPIER_32) {
            public void actionPerformed(ActionEvent evt) {
                H3Etudiant.getFenetre().getEditeur().getPageActuelle().copier();
            }
        };
        COPIER.putValue("MnemonicKey", 80);
        COPIER.putValue("ShortDescription", "Copie la selection");
        COPIER.putValue("AcceleratorKey", KeyStroke.getKeyStroke(67, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        COPIER.setEnabled(false);
        COLLER = new AbstractAction("Coller", Icones.COLLER_32) {
            public void actionPerformed(ActionEvent evt) {
                H3Etudiant.getFenetre().getEditeur().getPageActuelle().coller();
            }
        };
        COLLER.putValue("MnemonicKey", 76);
        COLLER.putValue("ShortDescription", "Colle le contenu du presse papier");
        COLLER.putValue("AcceleratorKey", KeyStroke.getKeyStroke(86, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        COLLER.setEnabled(false);
        SELECTIONNER_TOUT = new AbstractAction("Sélectionner tout", Icones.SELECTIONNER_TOUT_32) {
            public void actionPerformed(ActionEvent evt) {
                H3Etudiant.getFenetre().getEditeur().getPageActuelle().selectionnerTout();
            }
        };
        SELECTIONNER_TOUT.putValue("MnemonicKey", 83);
        SELECTIONNER_TOUT.putValue("ShortDescription", "Sélectionne tout le texte");
        SELECTIONNER_TOUT.putValue("AcceleratorKey", KeyStroke.getKeyStroke(65, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        SELECTIONNER_TOUT.setEnabled(false);
        SUPPRIMER_PROJET = new AbstractAction("Supprimer le projet", Icones.SUPPRIMER_DOSSIER_32) {
            public void actionPerformed(ActionEvent evt) {
                if (JOptionPane.showConfirmDialog(Actions.getFenetre(), "Etes vous sur de vouloir Supprimer le Projet ?", "Attention !!", 0, 2, Icones.INFO_64) == 0) {
                    Projet p = H3Etudiant.getFenetre().getExplorateur().getProjet();
                    if (p != null) {
                        H3Etudiant.supprimerProjet(p);
                    } else {
                        JOptionPane.showMessageDialog(H3Etudiant.getFenetre(), "Pas de projet sélectionné", "Erreur !!!", 0);
                    }
                }

            }
        };
        SUPPRIMER_PROJET.putValue("ShortDescription", "Supprime le projet");
        SUPPRIMER_PROJET.setEnabled(false);
        SUPPRIMER_FICHIER = new AbstractAction("Supprimer le fichier", Icones.SUPPRIMER_FICHIER_32) {
            public void actionPerformed(ActionEvent evt) {
                if (JOptionPane.showConfirmDialog(Actions.getFenetre(), "Etes vous sur de vouloir Supprimer le Fichier ?", "Attention !!", 0, 2, Icones.INFO_64) == 0) {
                    Actions.supprimerFichierSelectionne();
                }

            }
        };
        SUPPRIMER_FICHIER.putValue("ShortDescription", "Supprime le fichier");
        SUPPRIMER_FICHIER.setEnabled(false);
    }
}
