package hophophop.commun.modele;

import hophophop.commun.vue.dialogues.DialogueSaisieIdentifiant;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Connection {
    public Connection() {
    }

    public static String saisieIdentifiants(JFrame fenetre, Logger logger, ObjectOutputStream out, String type, String versionClient) {
        DialogueSaisieIdentifiant d = new DialogueSaisieIdentifiant(fenetre, type);
        if (d.afficher() == 2) {
            try {
                String passwordSha = ShaUtil.getSha256(d.getTxtPassword());
                logger.info("Demande connexion au serveur pour <" + d.getTxtUserName() + "> / +<" + passwordSha + ">");
                out.writeObject(Requete.getRequeteConnexion(d.getTxtUserName(), passwordSha, versionClient));
                return passwordSha;
            } catch (IOException var7) {
                logger.severe("Impossible d'envoyer la <Requete.getRequeteConnexion>" + var7.getMessage());
            }
        }

        return "";
    }

    public static String saisieIdentifiants(JFrame fenetre, Logger logger, ObjectOutputStream out, String type, String ip, String plateforme, String versionClient) {
        DialogueSaisieIdentifiant d = new DialogueSaisieIdentifiant(fenetre, type);
        if (d.afficher() == 2) {
            try {
                String passwordSha = ShaUtil.getSha256(d.getTxtPassword());
                logger.info("Demande connexion au serveur pour <" + d.getTxtUserName() + "> / +<" + passwordSha + ">");
                out.writeObject(Requete.getRequeteConnexion(d.getTxtUserName(), passwordSha, ip, plateforme, H3Commun.getVersionJava(), versionClient));
                return passwordSha;
            } catch (IOException var9) {
                logger.severe("Impossible d'envoyer la <Requete.getRequeteConnexion>" + var9.getMessage());
            }
        }

        return "";
    }
}
