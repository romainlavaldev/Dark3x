package hophophop.etudiant;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.*;
import hophophop.commun.modele.H3Commun;
import hophophop.etudiant.modele.H3Etudiant;
import hophophop.etudiant.vue.Actions;
import hophophop.etudiant.vue.Fenetre;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.formdev.flatlaf.*;

public class Main {
    private static String user;
    private static String langage;
    private static String session;
    private static int vitesse;
    private static String serveur = null;
    private static String port = null;
    private static boolean simulation = false;

    public Main() {
    }

    public static void main(String[] args) {

        FlatMaterialDesignDarkIJTheme.setup();

        final Logger logger = H3Etudiant.getLogger();
        logger.info("VERSION DU CLIENT  HOP3X : " + H3Commun.getVersionEtudiant());
        logger.info("java.vendor     : " + System.getProperty("java.vendor"));
        logger.info("java.vendor.url : " + System.getProperty("java.vendor.url"));
        logger.info("java.home       : " + System.getProperty("java.home"));
        logger.info("java.version    : " + System.getProperty("java.version"));
        logger.info("java.class.path : " + System.getProperty("java.class.path"));
        logger.info("os.arch         : " + System.getProperty("os.arch"));
        logger.info("user.dir        : " + System.getProperty("user.dir"));
        logger.info("user.home       : " + System.getProperty("user.home"));
        logger.info("user.name       : " + System.getProperty("user.name"));
        if (args.length >= 4) {
            simulation = true;
            user = args[0];
            session = args[1];
            langage = args[2];
            vitesse = Integer.parseInt(args[3]);
            logger.info("SIMULATION, user=" + user + ", session=" + session + ", lanagage=" + langage + ", vitesse = " + vitesse);
            if (args.length >= 5) {
                serveur = args[4];
                port = args[5];
                logger.info("Serveur=" + serveur + ", Port=" + port);
            }

            logger.info("Connecter : " + user + " Session : " + session + " - Langage : " + langage + " - Vitesse : " + vitesse + " - Serveur : " + serveur + " - Port : " + port);
        }


        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                Actions.enregistrerLesFichiersDuProjets();
                Long timeMillis = Calendar.getInstance().getTimeInMillis() - H3Etudiant.getDebutConnexion();
                String dureeMillis = timeMillis.toString();
                long heure = timeMillis / 3600000L;
                long minute = timeMillis % 3600000L / 60000L;
                long seconde = timeMillis % 3600000L % 60000L / 1000L;
                String duree = String.format("%dh:%dm:%ds", heure, minute, seconde);
                H3Etudiant.getLogger().info("Durée de cette Session : " + duree);
            }
        });
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                logger.info("Permission Exécutable pur les outils");
                File repertoire = new File(H3Etudiant.DOSSIER_OUTILS);
                File[] fichiers = repertoire.listFiles();
                int nbFichiers = fichiers.length;

                for(int i = 0; i < nbFichiers; ++i) {
                    File f = fichiers[i];
                    f.setExecutable(true);
                }

                logger.info("Démarrage HoP3sEtudiant");
                Fenetre fen = new Fenetre(Main.simulation);
                H3Etudiant.setFenetre(fen);
            }
        });
        connecterEtudiant();
    }

    public static void connecterEtudiant() {
        try {
            String serveur = getServeur();
            String port = getPort();
            if (serveur != null) {
                H3Etudiant.connecter(serveur, Integer.parseInt(port));
            } else {
                H3Etudiant.getLogger().info("Je tente sur le serveur défini dans la config");
                H3Etudiant.connecter();
            }
        } catch (UnknownHostException var2) {
            JOptionPane.showMessageDialog(H3Etudiant.getFenetre(), "Je ne peux pas me connecter, UnknownHostException", "Erreur !!!", 0);
            H3Etudiant.getLogger().severe("Erreur de connexion : Hote inaccessible/Inconnu : " + Main.serveur + ":" + Main.port);
            System.exit(0);
        } catch (NumberFormatException var3) {
            JOptionPane.showMessageDialog(H3Etudiant.getFenetre(), "Probleme sur la ligne de commande pour le port : \"+port", "Erreur !!!", 0);
            H3Etudiant.getLogger().severe("Erreur sur la ligne de commande pour le port : " + Main.port);
            System.exit(0);
        } catch (IOException var4) {
            JOptionPane.showMessageDialog(H3Etudiant.getFenetre(), "IOException : " + var4.getMessage(), "Erreur !!!", 0);
            H3Etudiant.getLogger().severe("IOException : " + var4.getMessage());
            System.exit(0);
        }

    }

    public static String getUser() {
        return user;
    }

    public static String getLangage() {
        return langage;
    }

    public static String getSession() {
        return session;
    }

    public static int getVitesse() {
        return vitesse;
    }

    public static boolean isSimulation() {
        return simulation;
    }

    public static String getServeur() {
        return serveur;
    }

    public static void setServeur(String serveur) {
        Main.serveur = serveur;
    }

    public static String getPort() {
        return port;
    }
}
