//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.outils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

public class TraceUtil {
    public TraceUtil() {
    }

    public static void ecrire(String trace, String cheminFichier) {
        File fichier = new File(cheminFichier);

        try {
            OutputStreamWriter outSr = new OutputStreamWriter(new FileOutputStream(fichier), "UTF-8");
            outSr.write(trace);
            outSr.flush();
            outSr.close();
        } catch (FileNotFoundException | UnsupportedEncodingException var5) {
            var5.printStackTrace();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public static String lireFichierTrace(String fichierTrace) {
        String retour = lireFichier(fichierTrace);
        return retour.equals("") ? "<TRACE></TRACE>" : retour + "</TRACE>";
    }

    public static String lireFichier(String chemin) {
        try {
            File fichier = new File(chemin);
            InputStreamReader inSr = new InputStreamReader(new FileInputStream(fichier), "UTF-8");
            StringWriter outSW = new StringWriter();

            int b;
            while((b = inSr.read()) != -1) {
                outSW.write(b);
            }

            outSW.flush();
            outSW.close();
            return outSW.toString();
        } catch (Exception var5) {
            var5.printStackTrace();
            return "";
        }
    }

    public static String compress64(String cheminFichier, Logger log) {
        try {
            log.info("Compression de  : " + cheminFichier);
            return CompressionUtil.compressB64(lireFichier(cheminFichier));
        } catch (IOException var3) {
            log.severe("Probleme Dans la compression de  : " + cheminFichier + ", " + var3.getMessage());
            return null;
        }
    }

    public static String decompress64(String traceCompressee, Logger log) {
        try {
            log.info("Decompression de la trace de longueur : " + traceCompressee.length());
            return CompressionUtil.decompressB64(traceCompressee);
        } catch (IOException var3) {
            log.severe("Probleme Dans la Décompression de la trace : " + var3.getMessage());
            return null;
        }
    }
}
