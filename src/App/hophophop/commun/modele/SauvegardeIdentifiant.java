//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class SauvegardeIdentifiant {
    static Element racine = new Element("Tuteur");
    static Document document;
    private static String username;
    private static String password;

    public SauvegardeIdentifiant() {
    }

    public static void enregistre(String username, String PassSha, String fichier, Logger logger) {
        logger.info("Enregistrement des identifiants de " + username + "/" + password);
        Attribute userLogin = new Attribute("username", username);
        racine.setAttribute(userLogin);
        Attribute userPass = new Attribute("password", PassSha);
        racine.setAttribute(userPass);
        enregistre(fichier, logger);
        lecture(fichier, logger);
    }

    private static void enregistre(String fichier, Logger logger) {
        try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            logger.info("Enregistrement des identifiants de l'utilisateur");
            sortie.output(document, new FileOutputStream(fichier));
        } catch (IOException var3) {
        }

    }

    public static void lecture(String fichier, Logger logger) {
        SAXBuilder sxb = new SAXBuilder();

        try {
            document = sxb.build(new File(fichier));
        } catch (JDOMException var4) {
            var4.printStackTrace();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        racine = document.getRootElement();
        username = racine.getAttribute("username").getValue();
        password = racine.getAttribute("password").getValue();
        logger.info("Lecture du fichier " + fichier + ", Username=" + username + ", Password=" + password);
    }

    public static String getPassword() {
        return password;
    }

    public static String getUsername() {
        return username;
    }

    static {
        document = new Document(racine);
    }
}
