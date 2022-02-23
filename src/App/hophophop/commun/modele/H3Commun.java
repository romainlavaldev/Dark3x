//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import codelab.CodeLabWS;
import hophophop.commun.vue.dialogues.DialogueChangementPassword;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public abstract class H3Commun {
    public static final SimpleDateFormat sdf = new SimpleDateFormat(" - dd/MM/yy - H:mm");
    public static final String VERSION_HOP3X = "VERSION_HOP3X";
    public static final String VERSION_ADMIN = "V.21.1116-189a";
    public static final String VERSION_TUTEUR = "V.22.0117-196t";
    public static final String VERSION_ETUDIANT = "V.22.0201-199e";
    public static final String VERSION_SERVEUR = "V.21.1116-189s";
    public static final String TITRE_ADMIN;
    public static final String TITRE_TUTEUR;
    public static final String TITRE_ETUDIANT;
    public static final String TITRE_SERVEUR;
    public static final String CLIENT_ETUDIANT = "hop3xEtudiant";
    public static final String CLIENT_TUTEUR = "hop3xTuteur";
    public static final String CLIENT_ADMIN = "hop3xAdmin";
    public static final String URL_HOP3X = "http://hop3x.univ-lemans.fr/";
    public static final String PLATEFORME;
    public static final String VERSION_JAVA;
    public static String nomFonte;
    public static Font LaFonteMenu;
    public static Font LaFonteExplorateur;
    public static Font LaFonteEditeur;
    public static Font LaFonteMessage;
    public static Font LaFonteCodeSource;
    public static final String C = "C";
    public static final String Ruby = "Ruby";
    public static final String Java = "Java";
    public static final String Python = "Python";
    public static final String Nxc = "Nxc";
    public static final String Scala = "Scala";
    public static final String Ev3 = "Ev3";
    public static final String SpiC = "SpiC";
    public static final String H = "H";
    public static final String Interface = "Interface Java";
    public static final String SourceC = "Source C";
    public static final String Autre = "Autre";
    public static final String Header = "Header (.h)";
    public static final String Makefile = "Makefile";
    public static final int DIMENSIONQUESTION = 395;
    public static final int PORT_SERVEUR_CLWS = 9000;
    public static final int PORT_CODELABWS = 8500;
    public static final String DOSSIER_LIBJUNIT;
    public static final String LIBJUNIT = "junit-platform-console-standalone-1.7.1.jar";
    public static final String CLASSEDETESTSUNITAIRES = "Hop3x_Tests.java";
    public static final String LANCEURDESTESTSUNITAIRES = "LanceurDesTests.java";
    private static long inactivite;

    public H3Commun() {
    }

    public static float getJavaVersion() {
        String javaVersion = System.getProperty("java.version");
        int indexP = javaVersion.indexOf(46);
        float version;
        if (indexP == -1) {
            version = Float.parseFloat(javaVersion);
        } else {
            String laVersion = javaVersion.substring(0, javaVersion.indexOf(46)) + '.' + javaVersion.substring(javaVersion.indexOf(46) + 1).substring(0, javaVersion.substring(javaVersion.indexOf(46) + 1).indexOf(46));
            version = Float.parseFloat(laVersion);
        }

        return version;
    }

    public static long getInactivite() {
        return inactivite;
    }

    public static String getVersionServeur() {
        return "V.21.1116-189s";
    }

    public static String getVersionTuteur() {
        return "V.22.0117-196t";
    }

    public static String getVersionEtudiant() {
        return "V.22.0201-199e";
    }

    public static String getVersionAdmin() {
        return "V.21.1116-189a";
    }

    public static Long getNumeroVersion(String versionClient) {
        return Long.parseLong(versionClient.substring(10, versionClient.length() - 1));
    }

    public static void setInactivite(long attente) {
        inactivite = attente;
    }

    public static CodeLabWS ouvrirCodeLabs(JFrame fenetre, ObjectOutputStream oos) {
        CodeLabWS cl = new CodeLabWS(0, oos, false);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();
        int hauteurDuMenu = 20;
        int largeurDuDock = 40;
        int hauteur = (int)screen.getHeight() - hauteurDuMenu;
        int largeur = (int)screen.getWidth() - largeurDuDock;
        double rapport = 0.8333333333333334D;
        float rap = 0.0F;
        cl.setBounds(new Rectangle(largeur / 2 + largeurDuDock, hauteurDuMenu, largeur / 2, (int)(rapport * (double)hauteur)));
        fenetre.setBounds(new Rectangle(largeurDuDock, hauteurDuMenu, largeur / 2, (int)(rapport * (double)hauteur)));
        fenetre.setVisible(true);
        cl.setVisible(true);
        cl.setZoom(7);
        return cl;
    }

    public static int changerDeMotDePasse(JFrame fenetre, Logger logger, ObjectOutputStream out, String username, boolean premiereConnexion) {
        if (premiereConnexion) {
            JOptionPane.showMessageDialog(fenetre, "C'est votre première connexion, veuillez choisir un nouveau mot de passe\n(Ne l'oubliez pas, il n'est pas stocké nous n'avons pas possibilité de vous le renvoyer", "Première Connexion", 1);
        }

        DialogueChangementPassword d = new DialogueChangementPassword(fenetre, "Changer de Mot de Passe", username);
        int retour = d.afficher();
        if (retour == 2) {
            String oldPassword = d.getOldPassword();
            String newPassword = d.getNewPassword();
            if (!oldPassword.equals("") && !newPassword.equals("")) {
                oldPassword = ShaUtil.getSha256(oldPassword);
                newPassword = ShaUtil.getSha256(newPassword);

                try {
                    logger.info("Envoi de la requete Changement de mot de passe : " + username + " : " + oldPassword + " : " + newPassword);
                    out.writeObject(Requete.getRequeteChangerMotDePasse(username, oldPassword, newPassword, premiereConnexion));
                } catch (IOException var10) {
                    logger.severe("Erreur d'envoie de Message Changement De Mot de Passe");
                    var10.printStackTrace();
                }
            }
        }

        return retour;
    }

    public static List<File> getFichiersRecursif(String racine, String filtre) {
        List<File> lesFichiers = new ArrayList();
        File f = new File(racine);
        File[] listFiles = f.listFiles();

        for(int i = 0; i < listFiles.length; ++i) {
            if (listFiles[i].isDirectory()) {
                lesFichiers.addAll(getFichiersRecursif(listFiles[i].toString(), filtre));
            } else if (listFiles[i].getName().endsWith(filtre)) {
                lesFichiers.add(listFiles[i]);
            }
        }

        return lesFichiers;
    }

    public static String localise(String ipExterne) {
        StringTokenizer ip = new StringTokenizer(ipExterne, ".");
        if (ip.nextToken().equals("172") && ip.nextToken().equals("18") && ip.nextToken().equals("41")) {
            int num = Integer.parseInt(ip.nextToken());
            if (num > 80 && num < 100) {
                return "Salle 130 : " + num;
            } else if (num > 110 && num < 130) {
                return "Salle 124 : " + num;
            } else if (num > 40 && num < 61) {
                return "Salle 114 : " + num;
            } else if (num > 20 && num < 41) {
                return "Salle 120 : " + num;
            } else if (num > 130 && num < 150) {
                return "Salle 122 : " + num;
            } else if ((num <= 10 || num >= 20) && (num <= 230 || num >= 240)) {
                return (num <= 1 || num >= 11) && (num <= 209 || num >= 230) ? "Ic2 ? : " + num : "Salle 110 : " + num;
            } else {
                return "Salle 112 : " + num;
            }
        } else if (ipExterne.indexOf("193.52.29") != -1) {
            return "Université";
        } else {
            return ipExterne.indexOf("172.18") != -1 ? "Wifi-Univ" : "Extérieur";
        }
    }

    public static void downloadUtil(String url, String destination) throws IOException {
        URL u = new URL(url);
        ReadableByteChannel readableByteChannel = Channels.newChannel(u.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(destination + u.getFile());
        fileOutputStream.getChannel().transferFrom(readableByteChannel, 0L, 9223372036854775807L);
    }

    public static void unzip(String zipfile, String folder) throws IOException {
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipfile)));
        ZipEntry ze = null;

        try {
            while((ze = zis.getNextEntry()) != null) {
                File f = new File(folder, ze.getName());
                if (ze.isDirectory()) {
                    f.mkdirs();
                } else {
                    f.getParentFile().mkdirs();
                    BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(f));

                    try {
                        try {
                            byte[] buf = new byte[8192];

                            int bytesRead;
                            while(-1 != (bytesRead = zis.read(buf))) {
                                fos.write(buf, 0, bytesRead);
                            }
                        } finally {
                            fos.close();
                        }
                    } catch (IOException var17) {
                        f.delete();
                        throw var17;
                    }
                }
            }
        } finally {
            zis.close();
        }

    }

    public static String getPlateforme() {
        return PLATEFORME;
    }

    public static String getVersionJava() {
        return VERSION_JAVA;
    }

    public static String getTitreAdmin() {
        return TITRE_ADMIN;
    }

    public static String getTitreTuteur() {
        return TITRE_TUTEUR;
    }

    public static String getTitreEtudiant() {
        return TITRE_ETUDIANT;
    }

    public static String getTitreServeur() {
        return TITRE_SERVEUR;
    }

    public static Logger creationLogger(String dossierLog) {
        (new File(dossierLog)).mkdirs();
        SimpleDateFormat formatter = new SimpleDateFormat("-dd-MM-yy HH'h'mm");
        Logger logger = Logger.getLogger("Logger " + dossierLog);
        logger.setUseParentHandlers(false);

        try {
            Handler fHandler = new FileHandler(dossierLog + "Logger" + dossierLog + formatter.format(new Date()) + ".log");
            fHandler.setEncoding("UTF-8");
            fHandler.setFormatter(new LogFormatter());
            fHandler.setLevel(Level.ALL);
            logger.addHandler(fHandler);
        } catch (SecurityException var7) {
            logger.severe("Création FileHander pour logger " + var7.getMessage());
        } catch (IOException var8) {
            logger.severe("Création FileHander pour logger " + var8.getMessage());
        }

        Handler cHandler = new ConsoleHandler();
        cHandler.setFormatter(new LogFormatter());
        cHandler.setLevel(Level.ALL);

        try {
            cHandler.setEncoding("UTF-8");
        } catch (SecurityException var5) {
            logger.severe("Création ConsoleHandler pour logger " + var5.getMessage());
        } catch (UnsupportedEncodingException var6) {
            logger.severe("Création ConsoleHandler pour logger " + var6.getMessage());
        }

        logger.addHandler(cHandler);
        return logger;
    }

    public static void installe(Logger logger, String dossierClient) {
        telechargeInstalleEtLance(logger, dossierClient);
    }

    public static void installe(String dossierClient) {
        installe(creationLogger(dossierClient), dossierClient);
    }

    public static void telechargeInstalleEtLance(Logger logger, String dossierClient) {
        String fichierClient = dossierClient.replaceFirst("h", "H") + ".zip";
        String urlFichierClient = "http://hop3x.univ-lemans.fr/" + fichierClient;
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Chargement/Installation du Client " + dossierClient);
        fenetre.setLayout(new BorderLayout());
        fenetre.setSize(new Dimension(600, 250));
        fenetre.add(new JLabel("Veuillez Patienter"), "North");
        JTextArea jText = new JTextArea();
        fenetre.add(jText, "Center");
        jText.setText("Début de l'installation");
        fenetre.setAlwaysOnTop(true);
        fenetre.setLocationRelativeTo(fenetre.getOwner());
        String dossier = ".";
        fenetre.setVisible(true);
        logger.info("Téléchargement du CLient...." + dossierClient);
        jText.setText("\nTéléchargement du Client " + dossierClient);

        String commande;
        try {
            logger.info("Chargement : urlFichierClient : " + urlFichierClient + ", fichierClient : " + fichierClient);
            jText.setText(jText.getText() + "\n  Téléchargement du CLient Hop3x : " + urlFichierClient);
            downloadUtil(urlFichierClient, dossier);
            jText.setText(jText.getText() + "...Ok");
            commande = dossier + File.separator + fichierClient;
            logger.info("Extraction du Client........");
            jText.setText(jText.getText() + "\n  Extraction du Client Hop3x : " + fichierClient);
            unzip(commande, dossier);
            (new File(commande)).delete();
            logger.info("Client ok");
            jText.setText(jText.getText() + "...Ok");
        } catch (IOException var10) {
            logger.severe("Impossible de télécharger " + fichierClient + ". Arrêt d'Hop3x." + var10.getMessage());
            logger.info("java.vendor : " + System.getProperty("java.vendor"));
            logger.info("java.vendor.url : " + System.getProperty("java.vendor.url"));
            logger.info("java.home : " + System.getProperty("java.home"));
            logger.info("java.class.path : " + System.getProperty("java.class.path"));
            logger.info("os.arch : " + System.getProperty("os.arch"));
            logger.info("user.dir : " + System.getProperty("user.dir"));
            logger.info("user.home : " + System.getProperty("user.home"));
            logger.info("user.name : " + System.getProperty("user.name"));
            JOptionPane.showMessageDialog((Component)null, "Impossible de télécharger " + fichierClient + ". Arrêt Hop3x");
            System.exit(0);
        }

        jText.setText(jText.getText() + "\n\nLancement du client " + dossierClient);
        commande = "java -cp ./" + dossierClient + "/lib/ -jar ./" + dossierClient + "/lib/" + dossierClient + ".jar";
        logger.info("Commande de lancement du client " + dossierClient + " = " + commande);

        try {
            Runtime.getRuntime().exec(commande);
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        System.exit(0);
    }

    static {
        TITRE_ADMIN = "V.21.1116-189a" + sdf.format(new Date());
        TITRE_TUTEUR = "V.22.0117-196t" + sdf.format(new Date());
        TITRE_ETUDIANT = "V.22.0201-199e" + sdf.format(new Date());
        TITRE_SERVEUR = "V.21.1116-189s" + sdf.format(new Date());
        PLATEFORME = System.getProperty("os.name");
        VERSION_JAVA = String.valueOf(getJavaVersion());
        nomFonte = "MonoLisa";
        LaFonteMenu = new Font(nomFonte, 0, 14);
        LaFonteExplorateur = new Font(nomFonte, 0, 12);
        LaFonteEditeur = new Font(nomFonte, 0, 11);
        LaFonteMessage = new Font(nomFonte, 0, 13);
        LaFonteCodeSource = new Font("MonoLisa", 0, 14);
        DOSSIER_LIBJUNIT = "lib" + File.separator + "junit" + File.separator;
        inactivite = 10000L;
    }

    public static enum ACTEUR {
        ETUDIANT,
        TUTEUR;

        private ACTEUR() {
        }
    }
}
