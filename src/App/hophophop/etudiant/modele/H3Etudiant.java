//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele;

import codelab.CodeLabWS;
import hophophop.commun.modele.Connection;
import hophophop.commun.modele.Etudiant;
import hophophop.commun.modele.Evenement;
import hophophop.commun.modele.H3Commun;
import hophophop.commun.modele.Hop3xLogFormater;
import hophophop.commun.modele.Message;
import hophophop.commun.modele.Outils;
import hophophop.commun.modele.Requete;
import hophophop.commun.outils.TraceUtil;
import hophophop.etudiant.Main;
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
import hophophop.etudiant.modele.observateurs.ObservateurH3Etudiant;
import hophophop.etudiant.modele.projet.Projet;
import hophophop.etudiant.modele.projet.ProjetC;
import hophophop.etudiant.modele.projet.ProjetEv3;
import hophophop.etudiant.modele.projet.ProjetJava;
import hophophop.etudiant.modele.projet.ProjetNxc;
import hophophop.etudiant.modele.projet.ProjetPython;
import hophophop.etudiant.modele.projet.ProjetRuby;
import hophophop.etudiant.modele.projet.ProjetScala;
import hophophop.etudiant.modele.projet.ProjetSpiC;
import hophophop.etudiant.vue.Actions;
import hophophop.etudiant.vue.Explorateur;
import hophophop.etudiant.vue.Fenetre;
import hophophop.etudiant.vue.PanelConsigneQuestions;
import hophophop.etudiant.vue.dialogues.DialogueConnecterSession;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SocketHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jsresources.apps.chat.MasterModel;

public abstract class H3Etudiant {
    private static Logger logger;
    public static final String TITRE = "Hop3x Etudiant - ";
    public static final String DOSSIER_BASE = "hop3xEtudiant";
    public static final String DOSSIER_OUTILS;
    public static final String DOSSIER_CODELAB;
    public static final String DOSSIER_OUTILS_SS;
    public static final String DOSSIER_LOGS;
    public static final String DOSSIER_CONFIGURATION;
    public static final String DOSSIER_DONNEES;
    public static final String DOSSIER_TRACE;
    public static final String DOSSIER_TRAVAIL;
    private static String ip;
    private static final String FICHIER_CONFIG;
    public static final String DOSSIER_SONS;
    public static final String FICHIER_SON_DEBUT_CAUSETTE;
    public static final String FICHIER_SON_DEBUT_TEXTE;
    public static final String FICHIER_SON_FIN_CAUSETTE;
    public static final String FICHIER_SON_FERMETURE;
    public static final String FICHIER_SON_MESSAGE;
    public static final Color COULEUR_ERREUR;
    private static final String OU = "ev3";
    private static List<ObservateurH3Etudiant> observateurs;
    private static List<Projet> projets;
    private static Socket socket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static Traceur traceur;
    private static String langage;
    private static String nomDeLaSession;
    private static String trace;
    private static List<String> lstEnonce;
    private static int questionCourante;
    private static int nombreDeQuestions;
    private static String consigne;
    private static List<String> lstQuestions;
    private static int questionMaxSelectionnee;
    private static boolean deconnecterEnQuittant;
    private static List<Evenement> historiqueMessage;
    private static MasterModel masterModelEtudiant;
    private static boolean compilationProjet;
    private static long debutConnexion;
    private static boolean fin;
    private static Thread threadEcouteServeur;
    private static Fenetre fenetre;
    private static boolean confirmationSortie;
    private static DefaultCompletionProvider completionProvider;
    private static boolean autoCompletion;
    private static Etudiant etudiant;
    private static DialogueConnecterSession dialogue;
    private static int etatSession;
    private static Boolean testunitaire;
    private static String tracageTU;
    private static String projetCourant;
    private static Process processusExecution;
    private static CodeLabWS codeLabWS;
    private static Socket socketEtudiant;
    private static ObjectOutputStream outEtudiant;
    private static String scenario;

    public H3Etudiant() {
    }

    public static String getIp() {
        return ip;
    }

    public static boolean isDeconnecterEnQuittant() {
        return deconnecterEnQuittant;
    }

    public static void setDeconnecterEnQuittant(boolean deconnecterEnQuittant) {
        H3Etudiant.deconnecterEnQuittant = deconnecterEnQuittant;
    }

    public static Socket getSocketEtudiant() {
        return socketEtudiant;
    }

    public static void setSocketEtudiant(Socket socketEtudiant) {
        H3Etudiant.socketEtudiant = socketEtudiant;
    }

    public static ObjectOutputStream getOutEtudiant() {
        return outEtudiant;
    }

    public static void setOutEtudiant(ObjectOutputStream outEtudiant) {
        H3Etudiant.outEtudiant = outEtudiant;
    }

    public static boolean isAutoCompletion() {
        return autoCompletion;
    }

    public static void setAutoCompletion(boolean autoCompletion) {
        H3Etudiant.autoCompletion = autoCompletion;
    }

    public static boolean isConfirmationSortie() {
        return confirmationSortie;
    }

    public static void setConfirmationSortie(boolean confirmationSortie) {
        H3Etudiant.confirmationSortie = confirmationSortie;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static List<String> getSessionsDemarrees() {
        return etudiant.getSessionsDemarrees();
    }

    public static long getDebutConnexion() {
        return debutConnexion;
    }

    public static boolean isCompilationProjet() {
        return compilationProjet;
    }

    public static void setCompilationProjet(boolean compilationProjet) {
        H3Etudiant.compilationProjet = compilationProjet;
        if (compilationProjet) {
            logger.info("Le Mode de Compilation de cette Session est PROJET");
        } else {
            logger.info("Le Mode de Compilation de cette Session est FICHIER");
        }

    }

    public static Map<String, String> zipLesLogs() throws IOException {
        Path directoryToSend = (new File(DOSSIER_LOGS)).toPath();
        logger.info("Création de la map du dossier Logs de L'étudiant " + getNomCompletEtudiant());
        Map<String, String> logs = new HashMap();
        Files.walk(directoryToSend).forEach((someFileToSend) -> {
            String nomFichier = someFileToSend.getFileName().toString();
            if (nomFichier.endsWith(".log")) {
                logs.put(nomFichier, TraceUtil.compress64(someFileToSend.toString(), logger));
            }

        });
        return logs;
    }

    private static void ouvrirSSH() {
        final String commande = "ssh ev3";
        (new Thread() {
            public void run() {
                try {
                    Process processus = Runtime.getRuntime().exec(commande);
                    new ProcessBuilder(new String[0]);
                    processus.waitFor();
                } catch (IOException var3) {
                    var3.printStackTrace();
                } catch (InterruptedException var4) {
                    var4.printStackTrace();
                }

            }
        }).start();
    }

    public static synchronized void forcerDeconnectionEtudiant(String session_nom, boolean confirmation) throws Exception {
        synchronized(out) {
            logger.info("Je deconnecte (" + etudiant + ") de la session (" + session_nom + ")");
            out.writeObject(Requete.getRequeteDeconnectionEtudiant(getUserName(), session_nom, (new Boolean(confirmation)).toString()));
        }
    }

    public static void ajouterObservateur(ObservateurH3Etudiant observateur) {
        observateurs.add(observateur);
    }

    public static void supprimerObservateur(ObservateurH3Etudiant observateur) {
        observateurs.remove(observateur);
    }

    public static void ajouterProjet(Projet projet) {
        projets.add(projet);
        Evenement evenement = Evenement.getEvenementAjoutProjet(projet.getNom(), projet.getType(), projet.isCompilationProjet());
        getTraceur().envoyer(evenement);
        Iterator var2 = observateurs.iterator();

        while(var2.hasNext()) {
            ObservateurH3Etudiant observateur = (ObservateurH3Etudiant)var2.next();
            observateur.ajoutProjet(projet);
        }

    }

    public static void supprimerProjet(Projet projet) {
        projets.remove(projet);
        Evenement evenement = Evenement.getEvenementSuppressionProjet(projet.getNom());
        getTraceur().envoyer(evenement);
        getLogger().info("Suppression projet : " + projet.getNom());
        Iterator var2 = observateurs.iterator();

        while(var2.hasNext()) {
            ObservateurH3Etudiant observateur = (ObservateurH3Etudiant)var2.next();
            observateur.suppressionProjet(projet);
        }

        File repertoire = new File(projet.getRepertoire());
        File[] fichiers = repertoire.listFiles();

        for(int i = 0; i < fichiers.length; ++i) {
            fichiers[i].delete();
        }

        repertoire.delete();
    }

    public static void connecter(String ip, int port) throws UnknownHostException, IOException {
        logger.info("H3Etudiant::connecter : " + ip + ":" + port);
        if (socket == null) {
            socket = new Socket(ip, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            synchronized(out) {
                Connection.saisieIdentifiants(fenetre, logger, out, "Etudiant", H3Commun.getVersionEtudiant());
            }

            threadEcouteServeur = new Thread(new EcouteServeur());
            threadEcouteServeur.start();
            logger.info("Le thread d'écoute est démarré");
        }

    }

    public static void connecterEtudiant(int port) throws UnknownHostException, IOException {
    }

    public static void identifier(String session) throws IOException, ClassNotFoundException {
        setNomDeLaSession(session);

        try {
            ip = InetAddress.getLocalHost().toString().split("/")[1];
        } catch (UnknownHostException var9) {
            logger.severe("Problème de récupération de l'IP : " + var9.getMessage());
            var9.printStackTrace();
        }

        out.reset();
        out.writeObject(Message.getMessageInformationSession(session));
        Message message = (Message)in.readObject();
        String etat;
        if (message.getType().equals("m-035")) {
            setLangage((String)message.getArgument("m-038"));
            setTestunitaire((Boolean)message.getArgument("m-109-1"));
            setScenario((String)message.getArgument("m-095-6"));
            if (langage.equals("Ev3")) {
                ouvrirSSH();
            }

            if (langage.equals("Nxc")) {
                (new Thread(new ServeurEtudiant(9000))).start();
            }

            logger.info("=============== Ip          : " + ip);
            logger.info("=============== Os.name     : " + System.getProperty("os.name"));
            logger.info("=============== JavaVersion : " + H3Commun.getJavaVersion());
            logger.info("=============== Langage     : " + langage);
            if ((double)H3Commun.getJavaVersion() <= 1.8D && System.getProperty("os.name").indexOf("Windows") != -1) {
                etat = System.getProperty("java.home");
                logger.info("*****JAVAHOME = " + etat);
                etat = etat.replace("jre", "jdk") + File.separator + "jre" + File.separator;
                File jre = new File(etat);
                if (!jre.exists()) {
                    logger.severe("Pas de Jdk installé, Vous ne pourrez pas compiler du Java");
                } else {
                    System.setProperty("java.home", etat);
                    logger.info("NOUVEAU JAVAHOME : " + etat);
                }
            }

            setCompilationProjet((Boolean)message.getArgument("m-037"));
            setEtatSession((Integer)message.getArgument("m-058"));
            if (etatSession == 1) {
                etat = "\"Lecture Seule";
            } else if (etatSession == 2) {
                etat = "Fermée";
            } else {
                etat = "Ouverte";
            }

            logger.info("J'ai recu les Info de la session  : " + session + ", Etat : " + etat + " Pour " + etudiant.getNomComplet() + ", Langage : " + getLangage() + ", CompilationProjet " + isCompilationProjet() + ", Ip : " + getIp() + ", Plateforme : " + H3Commun.getPlateforme());
        } else {
            logger.severe("Arrêt Hop3x : Problème Reception d'information non conforme du Serveur");
            System.exit(0);
        }

        etat = DOSSIER_TRACE + File.separator + etudiant.getNomComplet() + File.separator + session;
        List<File> lf = new ArrayList();
        File fichierRestoration;
        if ((new File(etat)).exists()) {
            fichierRestoration = new File(etat);
            FilenameFilter filter = new FilenameFilter() {
                public boolean accept(File f, String name) {
                    return name.endsWith(".RESTORE");
                }
            };
            lf = Arrays.asList(fichierRestoration.listFiles(filter));
        }

        out.reset();
        if (!((List)lf).isEmpty()) {
            fichierRestoration = (File)((List)lf).get(0);
            BasicFileAttributes attr = Files.readAttributes(fichierRestoration.toPath(), BasicFileAttributes.class);
            getLogger().warning("Fichier de restoration : " + ((File)((List)lf).get(0)).getName() + ", du dossier " + etat);
            InputStreamReader inSr = new InputStreamReader(new FileInputStream(fichierRestoration), "UTF-8");
            StringWriter aRestorer = new StringWriter();

            int b;
            while((b = inSr.read()) != -1) {
                aRestorer.write(b);
            }

            aRestorer.flush();
            aRestorer.close();
            inSr.close();
            logger.info("Envoie de la demande d'identification sur la session");
            out.writeObject(Requete.getRequeteIdentifier(etudiant.getUsername(), session, Main.isSimulation(), ip, H3Commun.getPlateforme(), String.valueOf(H3Commun.getJavaVersion()), H3Commun.getVersionEtudiant(), String.valueOf(attr.creationTime().toMillis()), aRestorer.toString()));
            fichierRestoration.delete();
        } else {
            logger.info("Envoie de la demande d'identification sur la session");
            out.writeObject(Requete.getRequeteIdentifier(etudiant.getUsername(), session, Main.isSimulation(), ip, H3Commun.getPlateforme(), String.valueOf(H3Commun.getJavaVersion()), H3Commun.getVersionEtudiant()));
        }

        logger.info("Attente de la trace");
        trace = TraceUtil.decompress64((String)in.readObject(), logger);
        logger.info("Reception de la trace de longueur " + trace.length());
        traceur = new Traceur();
        traceur.connecter(out);
        traceur.setTracerActif(false);
        fichierRestoration = new File(traceur.getFichierTraceLocale());
        logger.info("Recopie de la trace dans le fichier local : " + traceur.getFichierTraceLocale());
        OutputStreamWriter outSw = new OutputStreamWriter(new FileOutputStream(fichierRestoration), "UTF-8");
        outSw.write(trace.substring(0, trace.length() - "</TRACE>".length()));
        outSw.close();
        if (!Main.isSimulation()) {
            creerWorkspace(trace);
        }

        traceur.setTracerActif(true);
        debutConnexion = Calendar.getInstance().getTimeInMillis();
        out.reset();
        out.writeObject(Requete.getRequeteDemandeEnonce());
        lstEnonce = (List)in.readObject();
        if (!lstEnonce.isEmpty()) {
            logger.info("Reception de l'enoncé de longueur " + lstEnonce.size());
            consigne = (String)lstEnonce.get(0);
            lstQuestions = lstEnonce.subList(1, lstEnonce.size());
            nombreDeQuestions = lstQuestions.size();
            PanelConsigneQuestions pcq = new PanelConsigneQuestions(getFenetre(), getQuestionCourante());
            pcq.setPreferredSize(new Dimension(395, pcq.getHeight()));
            getFenetre().setPanelQuestion(pcq);
            getFenetre().getPanelEditeurQuestion().add(pcq, "East");
            if (isTestunitaire()) {
                if (tracageTU == null) {
                    InitialiseIndicateurTestunitaire(lstQuestions.size());
                } else {
                    getFenetre().getPanelConsigneQuestion().mettreAJourPanelResultat();
                }

                Actions.TEST_UNITAIRE.setEnabled(true);
            }
        }

        etudiant.getSessionsConnectes().add(session);
        Iterator var14 = observateurs.iterator();

        while(var14.hasNext()) {
            ObservateurH3Etudiant observateur = (ObservateurH3Etudiant)var14.next();
            observateur.connexion();
        }

    }

    private static void setScenario(String argument) {
        if (argument != null) {
            getLogger().info("Scénario de la session : " + argument);
        } else {
            getLogger().info("Pas de scenario pour la session");
        }

        scenario = argument;
    }

    public static boolean isScenario() {
        return scenario != null;
    }

    public static String getScenario() {
        return scenario;
    }

    public static void setTestunitaire(Boolean argument) {
        testunitaire = argument;
        Actions.TEST_UNITAIRE.setEnabled(testunitaire);
    }

    private static void InitialiseIndicateurTestunitaire(int i) {
        tracageTU = "";

        for(int k = 0; k < i; ++k) {
            tracageTU = tracageTU + "N";
        }

    }

    public static void setTracageTU(String tracageTU) {
        H3Etudiant.tracageTU = tracageTU;
    }

    public static boolean isTestunitaire() {
        return testunitaire;
    }

    private static void setEtatSession(Integer integer) {
        etatSession = integer;
    }

    public static void setLangage(String langage) {
        H3Etudiant.langage = langage;
    }

    public static void connecter() throws UnknownHostException, IOException {
        Properties config = Outils.getConfiguration(FICHIER_CONFIG);
        String ip = config.getProperty("serveur");
        int port = Integer.parseInt(config.getProperty("port"));
        logger.info("Connexions sur le serveur : " + ip + " sur le port : " + port);
        String logIP = config.getProperty("log-serveur");
        int logPort = Integer.parseInt(config.getProperty("log-port"));

        try {
            Handler sHandler = new SocketHandler(logIP, logPort);
            sHandler.setFormatter(new Hop3xLogFormater());
            sHandler.setLevel(Level.ALL);
            sHandler.setEncoding("UTF-8");
            logger.addHandler(sHandler);
            logger.info("Connexions sur le serveur de Log : " + logIP + " sur le port : " + logPort);
        } catch (ConnectException var6) {
            logger.warning("Pas de serveur de log : " + logIP + " sur le port : " + logPort);
        }

        connecter(ip, port);
    }

    public static void deconnecter() throws Exception {
        logger.warning("Déconnection : " + getNomCompletEtudiant() + " de la session " + getNomDeLaSession());
        if (socket.isConnected()) {
            logger.warning("Déconnection du traceur : isConnected est vraie");
            traceur.deconnecter();
            socket.close();
            socket = null;
            Iterator var0 = observateurs.iterator();

            while(var0.hasNext()) {
                ObservateurH3Etudiant observateur = (ObservateurH3Etudiant)var0.next();
                observateur.deconnexion();
            }
        }

    }

    public static List<Projet> getProjets() {
        return projets;
    }

    public static Traceur getTraceur() {
        return traceur;
    }

    public static void creerWorkspace(String trace) {
        Document document = null;
        long heureEvent1 = 0L;
        long heureDepartSimulation = 0L;

        try {
            document = (new SAXBuilder()).build(new StringReader(trace + "</TRACE>"));
        } catch (JDOMException var21) {
            logger.severe("Impossible de créer la structure DOM depuis la trace " + trace + var21.getMessage());
            var21.printStackTrace();
        } catch (IOException var22) {
            logger.severe("Impossible de créer la structure DOM depuis la trace " + trace + var22.getMessage());
            var22.printStackTrace();
        }

        if (document != null) {
            Element racine = document.getRootElement();
            List<Element> elements = racine.getChildren();
            Map<String, ProjetEnConstruction> workspace = new HashMap();
            int cpt = 1;
            if (Main.isSimulation()) {
                Evenement event1 = new Evenement((Element)elements.get(0));
                heureEvent1 = event1.getTemps();
                heureDepartSimulation = Calendar.getInstance().getTimeInMillis();
            }

            FichierEnConstruction fichierEnConstructionAOuvrir = null;
            ProjetEnConstruction projetEnConstructionAOuvrir = null;
            Fichier fichierAOuvrir = null;
            Iterator var13 = elements.iterator();

            Evenement evenement;
            FichierEnConstruction fec;
            while(var13.hasNext()) {
                Element element = (Element)var13.next();
                evenement = new Evenement(element);
                Long heureEvent = evenement.getTemps();
                if (evenement.getType().equals("TEST_UNITAIRE")) {
                    setTracageTU(evenement.get("ETAT"));
                } else if (evenement.getType().equals("SQ")) {
                    setQuestionCourante(Integer.parseInt(evenement.get(Evenement.NUMEROQUESTION)));
                    if (testunitaire) {
                        projetCourant = evenement.get("P");
                    }
                } else if (evenement.getType().equals("NQ")) {
                    setQuestionCourante(Integer.parseInt(evenement.get(Evenement.QUESTIONAPRES)));
                } else if (evenement.getType().equals("MT")) {
                    Actions.MONTRERHISTORIQUEMESSAGE.setEnabled(true);
                    historiqueMessage.add(evenement);
                } else if (evenement.getType().equals("AP")) {
                    workspace.put(evenement.get("P"), new ProjetEnConstruction(evenement.get("P"), evenement.get("TP"), evenement.get("COMPILATION_PROJET")));
                } else if (evenement.getType().equals("AF")) {
                    ((ProjetEnConstruction)workspace.get(evenement.get("P"))).ajouterFichier(new FichierEnConstruction(evenement.get("F"), evenement.get("TF")));
                    fichierEnConstructionAOuvrir = ((ProjetEnConstruction)workspace.get(evenement.get("P"))).getFichier(evenement.get("F"));
                    projetEnConstructionAOuvrir = (ProjetEnConstruction)workspace.get(evenement.get("P"));
                } else {
                    int position;
                    if (evenement.getType().equals("IT")) {
                        ProjetEnConstruction projet = (ProjetEnConstruction)workspace.get(evenement.get("P"));
                        if (projet != null) {
                            position = Integer.parseInt(evenement.get("N"));
                            String texte = evenement.get("T");
                            if (texte == "") {
                                texte = " ";
                            } else {
                                texte = texte.replace("\\n", "\n").replace("\\t", "\t");
                                texte = texte.replace("$€n€$", "\\n").replace("$€t€$", "\\t");
                            }

                            fichierEnConstructionAOuvrir = ((ProjetEnConstruction)workspace.get(evenement.get("P"))).getFichier(evenement.get("F"));
                            if (fichierEnConstructionAOuvrir == null) {
                                getLogger().warning("Tentative d'insertion de texte dans un fichier inexistant !!! " + evenement.get("F"));
                            } else {
                                fichierEnConstructionAOuvrir.inserer(texte, position);
                            }

                            projetEnConstructionAOuvrir = (ProjetEnConstruction)workspace.get(evenement.get("P"));
                        } else {
                            getLogger().warning("Tentative d'insertion de texte dans un fichier d'un projet inexistant !!! " + evenement.get("P"));
                        }
                    } else if (evenement.getType().equals("ST")) {
                        int debut = Integer.parseInt(evenement.get("A"));
                        position = Integer.parseInt(evenement.get("Z"));
                        ProjetEnConstruction projet = (ProjetEnConstruction)workspace.get(evenement.get("P"));
                        if (projet != null) {
                            fichierEnConstructionAOuvrir = ((ProjetEnConstruction)workspace.get(evenement.get("P"))).getFichier(evenement.get("F"));
                            if (fichierEnConstructionAOuvrir == null) {
                                getLogger().warning("Tentative de suppression de texte dans un fichier inexistant !!! " + evenement.get("F"));
                            } else {
                                fichierEnConstructionAOuvrir.supprimer(debut, position);
                            }

                            projetEnConstructionAOuvrir = (ProjetEnConstruction)workspace.get(evenement.get("P"));
                        } else {
                            getLogger().warning("Tentative de suppression de texte dans un fichier d'un projet inexistant !!! " + evenement.get("P"));
                        }

                        projetEnConstructionAOuvrir = (ProjetEnConstruction)workspace.get(evenement.get("P"));
                    } else if (evenement.getType().equals("SF")) {
                        fec = ((ProjetEnConstruction)workspace.get(evenement.get("P"))).getFichier(evenement.get("F"));
                        ((ProjetEnConstruction)workspace.get(evenement.get("P"))).supprimerFichier(fec);
                    } else if (evenement.getType().equals("SP")) {
                        workspace.remove(evenement.get("P"));
                    }
                }

                if (Main.isSimulation()) {
                    Long temps = heureEvent - heureEvent1 - (Calendar.getInstance().getTimeInMillis() - heureDepartSimulation);

                    try {
                        if (temps > 0L) {
                            Thread.sleep(temps);
                        }
                    } catch (InterruptedException var20) {
                        var20.printStackTrace();
                    }

                    if (cpt % 100 == 0) {
                        logger.info(getNomCompletEtudiant() + " : " + cpt);
                    }

                    getTraceur().envoyer(evenement);
                    ++cpt;
                }
            }

            if (Main.isSimulation()) {
                logger.info("Fin de Simulation de " + getNomCompletEtudiant());
                Actions.QUITTER.actionPerformed((ActionEvent)null);
                getTraceur().setTracerActif(false);
            }

            var13 = workspace.values().iterator();

            while(var13.hasNext()) {
                ProjetEnConstruction pec = (ProjetEnConstruction)var13.next();
                evenement = null;
                Object p;
                if (pec.getType().equals("Java")) {
                    p = new ProjetJava(pec.getNom(), pec.isCompilationProjet());
                } else if (pec.getType().equals("C")) {
                    p = new ProjetC(pec.getNom(), pec.isCompilationProjet());
                } else if (pec.getType().equals("Ruby")) {
                    p = new ProjetRuby(pec.getNom(), pec.isCompilationProjet());
                } else if (pec.getType().equals("Nxc")) {
                    p = new ProjetNxc(pec.getNom(), pec.isCompilationProjet());
                } else if (pec.getType().equals("SpiC")) {
                    p = new ProjetSpiC(pec.getNom(), pec.isCompilationProjet());
                } else if (pec.getType().equals("Python")) {
                    p = new ProjetPython(pec.getNom(), pec.isCompilationProjet());
                } else if (pec.getType().equals("Scala")) {
                    p = new ProjetScala(pec.getNom(), pec.isCompilationProjet());
                } else {
                    if (!pec.getType().equals("Ev3")) {
                        logger.severe("Impossible de créer le workspace étudiant : projet d'un type inconnu" + pec.getType());
                        return;
                    }

                    p = new ProjetEv3(pec.getNom(), pec.isCompilationProjet());
                }

                Actions.ajouterProjet((Projet)p);
                Iterator var26 = pec.getFichiers().iterator();

                while(var26.hasNext()) {
                    fec = (FichierEnConstruction)var26.next();
                    Fichier f = null;
                    if (pec.getType().equals("Java")) {
                        if (fec.getType().equals("Java")) {
                            f = new FichierJava((Projet)p, fec.getNom(), fec.getContenu().toString());
                        } else {
                            f = new FichierAutre((Projet)p, fec.getNom(), fec.getContenu().toString());
                        }

                        ((Projet)p).ajouterFichier((Fichier)f);
                    } else if (pec.getType().equals("C")) {
                        if (fec.getNom().toLowerCase().endsWith(".c")) {
                            f = new FichierC((Projet)p, fec.getNom(), fec.getContenu().toString());
                        } else if (fec.getNom().toLowerCase().endsWith(".h")) {
                            f = new FichierH((Projet)p, fec.getNom(), fec.getContenu().toString());
                        } else {
                            f = new FichierMF((Projet)p, fec.getNom(), fec.getContenu().toString());
                        }

                        ((Projet)p).ajouterFichier((Fichier)f);
                    } else if (pec.getType().equals("H")) {
                        f = new FichierH((Projet)p, fec.getNom(), fec.getContenu().toString());
                        ((Projet)p).ajouterFichier((Fichier)f);
                    } else if (pec.getType().equals("Ruby")) {
                        f = new FichierRuby((Projet)p, fec.getNom(), fec.getContenu().toString());
                        ((Projet)p).ajouterFichier((Fichier)f);
                    } else if (pec.getType().equals("Nxc")) {
                        f = new FichierNxc((Projet)p, fec.getNom(), fec.getContenu().toString());
                        ((Projet)p).ajouterFichier((Fichier)f);
                    } else if (pec.getType().equals("Ev3")) {
                        f = new FichierEv3((Projet)p, fec.getNom(), fec.getContenu().toString());
                        ((Projet)p).ajouterFichier((Fichier)f);
                    } else if (pec.getType().equals("SpiC")) {
                        f = new FichierSpiC((Projet)p, fec.getNom(), fec.getContenu().toString());
                        ((Projet)p).ajouterFichier((Fichier)f);
                    } else if (pec.getType().equals("Python")) {
                        f = new FichierPython((Projet)p, fec.getNom(), fec.getContenu().toString());
                        ((Projet)p).ajouterFichier((Fichier)f);
                    } else if (pec.getType().equals("Scala")) {
                        f = new FichierScala((Projet)p, fec.getNom(), fec.getContenu().toString());
                        ((Projet)p).ajouterFichier((Fichier)f);
                    }

                    if (((Projet)p).getNom().equals(projetEnConstructionAOuvrir.getNom()) && ((Fichier)f).getNom().equals(fichierEnConstructionAOuvrir.getNom())) {
                        fichierAOuvrir = f;
                    }
                }
            }

            if (fichierAOuvrir != null && !testunitaire) {
                fenetre.getExplorateur().fermerToutLesNoeuds();
                Actions.ouvrirClasseDansEditeur((Fichier)fichierAOuvrir);
            }
        }

    }

    public static String getNomDeLaSession() {
        return nomDeLaSession;
    }

    public static void setNomDeLaSession(String session) {
        nomDeLaSession = session;
    }

    public static String getNomCompletEtudiant() {
        return etudiant.getNomComplet();
    }

    public static String getLangage() {
        return langage;
    }

    public static ObjectInputStream getIn() {
        return in;
    }

    public static String getOu() {
        return "ev3";
    }

    public static String getTrace() {
        return trace;
    }

    public static ObjectOutputStream getOut() {
        return out;
    }

    public static List<String> getLstEnonce() {
        return lstEnonce;
    }

    public static void setQuestionCourante(int i) {
        questionCourante = i;
    }

    public static int getQuestionCourante() {
        return questionCourante;
    }

    public static int getNombreDeQuestions() {
        return nombreDeQuestions;
    }

    public static void setNombreDeQuestions(int nombreDeQuestions) {
        H3Etudiant.nombreDeQuestions = nombreDeQuestions;
    }

    public static List<String> getLstQuestions() {
        return lstQuestions;
    }

    public static String getConsigne() {
        return consigne;
    }

    public static void envoyerTraceLocale(String nomDuFichier) {
        try {
            InputStreamReader inSr = new InputStreamReader(new FileInputStream(nomDuFichier), "UTF-8");
            StringWriter outSW = new StringWriter();

            int b;
            while((b = inSr.read()) != -1) {
                outSW.write(b);
            }

            outSW.flush();
            outSW.close();
            inSr.close();
            out.writeObject(Requete.getRequeteEnvoyerTraceLocale(etudiant.getNomComplet(), nomDeLaSession, outSW.toString()));
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public static void appelerAUSecours(String motifAppel) {
        try {
            out.writeObject(Requete.getRequeteAppelAuSecours(etudiant.getNomComplet(), nomDeLaSession, motifAppel));
            Evenement evenement = Evenement.getEvenementAppelAuSecours(motifAppel, etudiant.getNomComplet());
            getTraceur().envoyer(evenement);
            logger.info("Appel au Secours : " + etudiant.getNomComplet());
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public static void okJEcouteVasYCause(Evenement e) {
        try {
            setMasterModelEtudiant(new MasterModel());
            getMasterModelEtudiant().getChatModel().setListen(true);
            logger.info("Creation du MasterModel : " + getMasterModelEtudiant());
            out.writeObject(Requete.getRequeteOkVasYCause(etudiant.getNomComplet(), nomDeLaSession, e.get("ENSEIGNANT")));
            getTraceur().envoyer(e);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public static void setMasterModelEtudiant(MasterModel masterModel) {
        masterModelEtudiant = masterModel;
    }

    public static MasterModel getMasterModelEtudiant() {
        return masterModelEtudiant;
    }

    public static List<Evenement> getHistoriqueMessage() {
        return historiqueMessage;
    }

    public static int getQuestionMaxSélectionnee() {
        return questionMaxSelectionnee;
    }

    public static void setQuestionMaxSélectionnee(int questionMaxSélectionnee) {
        questionMaxSelectionnee = questionMaxSélectionnee;
    }

    public static void setFenetre(Fenetre fen) {
        fenetre = fen;
    }

    public static Fenetre getFenetre() {
        return fenetre;
    }

    public static boolean getConfirmation() {
        return confirmationSortie;
    }

    public static DefaultCompletionProvider getProvider() {
        return completionProvider;
    }

    public static String getUserName() {
        return etudiant.getUsername();
    }

    public static List<String> getSessionsConnectées() {
        return etudiant.getSessionsConnectes();
    }

    public static String getPrenom() {
        return etudiant.getPrenom();
    }

    public static String getNom() {
        return etudiant.getNom();
    }

    public static void setEtudiant(Etudiant letudiant) {
        etudiant = letudiant;
    }

    public static String choixDeLaSession() {
        String quelleSession = null;
        boolean compilationProjet = true;
        int retour;
        String username;
        if (!Main.isSimulation()) {
            dialogue = new DialogueConnecterSession((String)null);
            retour = dialogue.afficher();
        } else {
            getLogger().info("Session Locale");
            quelleSession = Main.getSession();
            username = Main.getUser();
            String quelLangage = Main.getLangage();
            retour = 2;
        }

        if (retour == 2) {
            try {
                quelleSession = dialogue.getNomSessionSelectionnee();
                username = etudiant.getUsername();
                getLogger().info("Identification auprès du serveur " + quelleSession + " " + username);
                identifier(quelleSession);
                getFenetre().setTitle("Hop3x Etudiant -  " + H3Commun.getTitreEtudiant() + "    -    (" + getNomCompletEtudiant() + ")/(" + getNomDeLaSession() + ")");
                if (getLangage().equals("Nxc")) {
                    Actions.MODESIMULATEUR.actionPerformed((ActionEvent)null);
                } else {
                    getFenetre().setVisible(!Main.isSimulation());
                }
            } catch (IOException var6) {
                var6.printStackTrace();
            } catch (ClassNotFoundException var7) {
                var7.printStackTrace();
            }
        }

        return quelleSession;
    }

    public static void choixNouvelleSession() throws IOException, ClassNotFoundException {
        String nomSessionActuelle = getNomDeLaSession();
        String nomSessionSelectionnee = nomSessionActuelle;
        String qui = etudiant.getUsername();
        getFenetre().getEditeur().fermerTout();
        getProjets().clear();
        Explorateur expl = getFenetre().getExplorateur();
        expl.setRacine(new DefaultMutableTreeNode());
        ((DefaultTreeModel)expl.getModel()).setRoot(expl.getRacine());
        ((DefaultTreeModel)getFenetre().getExplorateur().getModel()).reload();
        if (getFenetre().getPanelConsigneQuestion() != null) {
            getFenetre().getPanelEditeurQuestion().remove(getFenetre().getPanelConsigneQuestion());
        }

        logger.warning("Demande de changement de Session : " + nomSessionActuelle + "(" + qui + ")");
        logger.info("Je supprime la session " + nomSessionActuelle + " des sessions Connectées");
        getEtudiant().getSessionsConnectes().remove(nomSessionActuelle);
        DialogueConnecterSession dialogueSession = new DialogueConnecterSession(nomSessionActuelle);
        if (dialogueSession.afficher() == 2) {
            nomSessionSelectionnee = dialogueSession.getNomSessionSelectionnee();
            getLogger().info("Nouvelle Session " + nomSessionSelectionnee + "(" + qui + ")");
        } else {
            getLogger().info("Annulation du Changement de Session");
        }

        identifier(nomSessionSelectionnee);
        getFenetre().setTitle("Hop3x Etudiant -  " + H3Commun.getTitreEtudiant() + "    -    (" + getNomCompletEtudiant() + ")/(" + getNomDeLaSession() + ")");
        getFenetre().setVisible(true);
    }

    public static Etudiant getEtudiant() {
        return etudiant;
    }

    public static boolean isSessionLectureSeule() {
        return etatSession == 1;
    }

    public static String getTracageTU() {
        return tracageTU;
    }

    public static void setProcessusExecution(Process exec) {
        processusExecution = exec;
    }

    public static Process getProcessusExecution() {
        return processusExecution;
    }

    public static void clearProcessusExecution() {
        processusExecution = null;
    }

    public static void setCodeLabWS(CodeLabWS codelab) {
        codeLabWS = codelab;
        Actions.OUVRIRLESIMULATEUR.setEnabled(false);
        Actions.FERMERLESIMULATEUR.setEnabled(true);
        Actions.DEMARRERSIMULATEUR.setEnabled(true);
        Actions.ARRETERSIMULATEUR.setEnabled(false);
        Actions.MODEROBOT.setEnabled(true);
        Actions.COMPILER.setEnabled(true);
    }

    public static CodeLabWS getCodeLabWS() {
        return codeLabWS;
    }

    public static void demarrerSimulateur() {
        getCodeLabWS().simu_start();
        logger.info("Démarrage du Simulateur");
        Actions.DEMARRERSIMULATEUR.setEnabled(false);
        Actions.ARRETERSIMULATEUR.setEnabled(true);
    }

    public static void arreterSimulateur() {
        if (getCodeLabWS() != null) {
            getCodeLabWS().simu_stop();
            getLogger().info("Arrêt du Simulateur");
        }

        Actions.DEMARRERSIMULATEUR.setEnabled(true);
        Actions.ARRETERSIMULATEUR.setEnabled(false);
    }

    static {
        DOSSIER_OUTILS = "hop3xEtudiant" + File.separator + "outilsHop3x" + File.separator;
        DOSSIER_CODELAB = DOSSIER_OUTILS + "codelab" + File.separator + "includes";
        DOSSIER_OUTILS_SS = "hop3xEtudiant" + File.separator + "outilsHop3x";
        DOSSIER_LOGS = "hop3xEtudiant" + File.separator + "logs" + File.separator;
        DOSSIER_CONFIGURATION = "hop3xEtudiant" + File.separator + "conf" + File.separator;
        DOSSIER_DONNEES = "hop3xEtudiant" + File.separator + "data" + File.separator;
        DOSSIER_TRACE = DOSSIER_DONNEES + "trace" + File.separator;
        DOSSIER_TRAVAIL = DOSSIER_DONNEES + "workspace" + File.separator;
        FICHIER_CONFIG = DOSSIER_CONFIGURATION + "config.xml";
        DOSSIER_SONS = DOSSIER_CONFIGURATION + "sons" + File.separator;
        FICHIER_SON_DEBUT_CAUSETTE = DOSSIER_SONS + "debut.wav";
        FICHIER_SON_DEBUT_TEXTE = DOSSIER_SONS + "fin.wav";
        FICHIER_SON_FIN_CAUSETTE = DOSSIER_SONS + "fin.wav";
        FICHIER_SON_FERMETURE = DOSSIER_SONS + "fermeture.wav";
        FICHIER_SON_MESSAGE = DOSSIER_SONS + "message.wav";
        COULEUR_ERREUR = new Color(255, 204, 204);
        trace = "";
        questionCourante = 0;
        deconnecterEnQuittant = true;
        confirmationSortie = true;
        autoCompletion = true;
        tracageTU = null;
        projetCourant = "";
        processusExecution = null;
        new Date();
        (new File(DOSSIER_LOGS)).mkdirs();
        SimpleDateFormat formatter = new SimpleDateFormat("-dd-MM-yy HH'h'mm");
        logger = Logger.getLogger("Hop3xEtudiant");
        logger.setUseParentHandlers(false);
        Hop3xLogFormater mf = new Hop3xLogFormater();

        try {
            Handler fHandler = new FileHandler(DOSSIER_LOGS + "Hop3xEtudiant" + formatter.format(new Date()) + ".log");
            fHandler.setEncoding("UTF-8");
            fHandler.setFormatter(mf);
            fHandler.setLevel(Level.ALL);
            logger.addHandler(fHandler);
        } catch (SecurityException var7) {
            logger.severe("Création FileHander pour logger " + var7.getMessage());
        } catch (IOException var8) {
            logger.severe("Création FileHander pour logger " + var8.getMessage());
        }

        Handler cHandler = new ConsoleHandler();
        cHandler.setFormatter(mf);
        cHandler.setLevel(Level.ALL);

        try {
            cHandler.setEncoding("UTF-8");
        } catch (SecurityException var5) {
            logger.severe("Création ConsoleHandler pour logger " + var5.getMessage());
        } catch (UnsupportedEncodingException var6) {
            logger.severe("Création ConsoleHandler pour logger " + var6.getMessage());
        }

        logger.addHandler(cHandler);
        completionProvider = new DefaultCompletionProvider();
        historiqueMessage = new LinkedList();
        observateurs = new ArrayList();
        projets = new ArrayList();
        lstQuestions = new ArrayList();
        (new File(DOSSIER_TRACE)).mkdirs();
        File wkspce = new File(DOSSIER_TRAVAIL);
        if (wkspce.exists()) {
            Outils.deleteRep(wkspce);
        }

        (new File(DOSSIER_TRAVAIL)).mkdirs();
    }
}
