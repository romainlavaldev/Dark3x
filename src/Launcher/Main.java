import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Main {
  public static final String DOSSIER_HOME = ".";
  
  public static final String DOSSIER_BASE = "hop3xEtudiant";
  
  public static final String DOSSIER_LIB = "hop3xEtudiant" + File.separator + "lib";
  
  public static final String DOSSIER_OUTILS = "hop3xEtudiant" + File.separator + "outilsHop3x" + File.separator;
  
  public static final String DOSSIER_LOGS = "hop3xEtudiant" + File.separator + "logs" + File.separator;
  
  private static Logger logger;
  
  private static Frame fenetre;
  
  public static void deleteRep(File r) {
    File[] fileList = r.listFiles();
    for (int i = 0; i < fileList.length; i++) {
      if (fileList[i].isDirectory()) {
        deleteRep(fileList[i]);
        fileList[i].delete();
      } else {
        fileList[i].delete();
      } 
    } 
    r.delete();
  }
  
  public static void downloadUtil(String url, String destination) throws IOException {
    URL u = new URL(url);
    URLConnection uc = u.openConnection();
    int taille = uc.getContentLength();
    InputStream brut = uc.getInputStream();
    InputStream entree = new BufferedInputStream(brut);
    byte[] donnees = new byte[taille];
    int octetsLus = 0;
    int deplacement = 0;
    float alreadyRead = 0.0F;
    while (deplacement < taille) {
      octetsLus = entree.read(donnees, deplacement, donnees.length - deplacement);
      alreadyRead += octetsLus;
      if (octetsLus == -1)
        break; 
      deplacement += octetsLus;
    } 
    entree.close();
    String fichier = u.getFile();
    fichier = destination + fichier;
    FileOutputStream fichierSortie = new FileOutputStream(fichier);
    fichierSortie.write(donnees);
    fichierSortie.flush();
    fichierSortie.close();
  }
  
  public static void unzip(String zipfile, String folder) throws IOException {
    ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipfile)));
    ZipEntry ze = null;
    try {
      while ((ze = zis.getNextEntry()) != null) {
        File f = new File(folder, ze.getName());
        if (ze.isDirectory()) {
          f.mkdirs();
          continue;
        } 
        f.getParentFile().mkdirs();
        OutputStream fos = new BufferedOutputStream(new FileOutputStream(f));
        try {
          try {
            byte[] buf = new byte[8192];
            int bytesRead;
            while (-1 != (bytesRead = zis.read(buf)))
              fos.write(buf, 0, bytesRead); 
          } finally {
            fos.close();
          } 
        } catch (IOException ioe) {
          f.delete();
          throw ioe;
        } 
      } 
    } finally {
      zis.close();
    } 
  }
  
  public static void main(String[] args) throws InterruptedException {
    System.out.println("Dossier de Base : hop3xEtudiant");
    (new File(DOSSIER_LOGS)).mkdirs();
    SimpleDateFormat formatter = new SimpleDateFormat("-dd-MM-yy HH'h'mm");
    logger = Logger.getLogger("LoggerHop3xEtudiant");
    logger.setUseParentHandlers(false);
    try {
      Handler fHandler = new FileHandler(DOSSIER_LOGS + "LoaderHop3xEtudiant" + formatter.format(new Date()) + ".log");
      fHandler.setEncoding("UTF-8");
      fHandler.setFormatter(new LogFormatter());
      fHandler.setLevel(Level.ALL);
      logger.addHandler(fHandler);
    } catch (SecurityException e) {
      logger.severe("CrFileHander pour logger " + e.getMessage());
    } catch (IOException e) {
      logger.severe("CrFileHander pour logger " + e.getMessage());
    } 
    Handler cHandler = new ConsoleHandler();
    cHandler.setFormatter(new LogFormatter());
    cHandler.setLevel(Level.ALL);
    try {
      cHandler.setEncoding("UTF-8");
    } catch (SecurityException e) {
      logger.severe("CrConsoleHandler pour logger " + e.getMessage());
    } catch (UnsupportedEncodingException e) {
      logger.severe("CrConsoleHandler pour logger " + e.getMessage());
    } 
    logger.addHandler(cHandler);
    new File(DOSSIER_LIB + File.separator + "Hop3xEtudiant.jar");
    new File(DOSSIER_LIB + File.separator + "jdom.jar");
    new File(DOSSIER_LIB + File.separator + "rsyntaxtextarea.jar");
    fenetre = new Frame();
    fenetre.setTitle("Chargement/Installation du Client HoP3x");
    fenetre.setLayout(new BorderLayout());
    fenetre.setSize(new Dimension(600, 250));
    fenetre.add(new JLabel("Veuillez Patienter"), "North");
    JTextArea jText = new JTextArea();
    fenetre.add(jText, "Center");
    jText.setText("Dde l'installation");
    fenetre.setAlwaysOnTop(true);
    fenetre.setLocationRelativeTo(fenetre.getOwner());
    fenetre.setVisible(true);
    logger.info("Tdu CLient....");
    jText.setText("\nTdu CLient Hop3x");
    /*
    try {
      
      String urlFichierClient = "http://hop3x.univ-lemans.fr/Hop3xEtudiant.zip";
      String fichierClient = "Hop3xEtudiant.zip";
      logger.info("Chargement : urlFichierClient : " + urlFichierClient + " // fichierClient : " + fichierClient);
      jText.setText(jText.getText() + "\n\tTdu CLient Hop3x");
      downloadUtil(urlFichierClient, ".");
      jText.setText(jText.getText() + "...Ok");
      String fichierZip = "." + File.separator + fichierClient;
      logger.info("Extraction du Client........");
      jText.setText(jText.getText() + "\n\tExtraction du Client Hop3x");
      unzip(fichierZip, ".");
      (new File(fichierZip)).delete();
      logger.info("Client ok");
      jText.setText(jText.getText() + "...Ok");
    } catch (IOException e) {
      logger.severe("Impossible de tle Client : Arrd'Hop3xEtudiant." + e.getMessage());
      logger.info("java.vendor : " + System.getProperty("java.vendor"));
      logger.info("java.vendor.url : " + System.getProperty("java.vendor.url"));
      logger.info("java.home : " + System.getProperty("java.home"));
      logger.info("java.class.path : " + System.getProperty("java.class.path"));
      logger.info("os.arch : " + System.getProperty("os.arch"));
      logger.info("user.dir : " + System.getProperty("user.dir"));
      logger.info("user.home : " + System.getProperty("user.home"));
      logger.info("user.name : " + System.getProperty("user.name"));
      logger.info("Chemin " + Main.class.getResource("Main.class"));
      JOptionPane.showMessageDialog(null, "Impossible de tle Client. ArrHop3x");
      System.exit(0);
    }*/
    jText.setText(jText.getText() + "\n\nLancement du client Etudiant");
    String commande = "java -jar ./hop3xEtudiant/lib/hop2x.jar ";
    logger.config("Commande de lancement du client Etudiant = " + commande);
    try {
      Runtime.getRuntime().exec(commande);
    } catch (IOException e) {
      e.printStackTrace();
    } 
    System.exit(0);
  }
}
