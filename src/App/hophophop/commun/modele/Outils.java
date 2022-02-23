//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.awt.Component;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JOptionPane;

public class Outils {
    public Outils() {
    }

    public static int compterOccurrences(String maChaine, String recherche) {
        return maChaine.length() - maChaine.replace(recherche, "").length();
    }

    public static Properties getConfiguration(String nomFichier) throws IOException {
        File fichierConfig = new File(nomFichier);
        Properties configuration = new Properties();

        try {
            configuration.loadFromXML(new FileInputStream(fichierConfig));
        } catch (FileNotFoundException var4) {
            JOptionPane.showMessageDialog((Component)null, "Problème de configuration\n, Arrêt de l'applicationr : " + var4.getMessage(), "Erreur !!!", 0);
            System.exit(0);
        }

        return configuration;
    }

    public static void deleteRep(File r) {
        File[] fileList = r.listFiles();

        for(int i = 0; i < fileList.length; ++i) {
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
        int deplacement = 0;

        int octetsLus;
        for(float alreadyRead = 0.0F; deplacement < taille; deplacement += octetsLus) {
            octetsLus = entree.read(donnees, deplacement, donnees.length - deplacement);
            alreadyRead += (float)octetsLus;
            if (octetsLus == -1) {
                break;
            }
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
}
