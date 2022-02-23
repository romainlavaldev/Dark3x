//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele;

import hophophop.commun.modele.MessageCLWS;
import hophophop.etudiant.vue.Actions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurEtudiant implements Runnable {
    private ServerSocket listener;
    private Socket socket;
    private int port;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ServeurEtudiant(int n) {
        this.port = n;

        try {
            this.listener = new ServerSocket(n);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public void run() {
        H3Etudiant.getLogger().info("Démarrage du serveur pour CodeLabWS sur le port " + this.port);

        try {
            this.socket = this.listener.accept();
            this.in = new ObjectInputStream(this.socket.getInputStream());

            while(true) {
                while(true) {
                    try {
                        Object o = this.in.readObject();
                        if (o instanceof MessageCLWS) {
                            MessageCLWS message = (MessageCLWS)o;
                            H3Etudiant.getLogger().info("Reception d'un message du Simulateur de type : " + message.getType());
                            this.traiter(message);
                        } else {
                            H3Etudiant.getLogger().warning("Réception d'un Objet de type " + o.getClass());
                        }
                    } catch (ClassNotFoundException var3) {
                        var3.printStackTrace();
                    }
                }
            }
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }

    private void traiter(MessageCLWS message) {
        if (!message.getType().equals("CL_HALT") && !message.getType().equals("CL_CLOSE")) {
            String aEcrire;
            if (message.getType().equals("CL_PRINTSTR")) {
                aEcrire = (String)message.getArgument("VALEUR");
                aEcrire = aEcrire.replaceAll("<br/>", "\n");
                aEcrire = aEcrire.replaceAll("<tab/>", "\t");
                aEcrire = aEcrire.replaceAll("<and/>", "&");
                aEcrire = aEcrire.replaceAll("<eq/>", "=");
                H3Etudiant.getFenetre().getConsole().ecrire(aEcrire);
            } else if (message.getType().equals("CL_PRINTINT")) {
                aEcrire = String.valueOf(message.getArgument("VALEUR"));
                H3Etudiant.getFenetre().getConsole().ecrire(aEcrire);
            } else if (message.getType().equals("CL_PRINTFLOAT")) {
                aEcrire = String.valueOf(message.getArgument("VALEUR"));
                H3Etudiant.getFenetre().getConsole().ecrire(aEcrire);
            }
        } else {
            if (H3Etudiant.getProcessusExecution() != null) {
                H3Etudiant.getLogger().info("Arrêt du Processus  de Pid :" + H3Etudiant.getProcessusExecution().pid());
                H3Etudiant.getProcessusExecution().destroy();
                H3Etudiant.clearProcessusExecution();
            }

            if (message.getType().equals("CL_CLOSE")) {
                Actions.OUVRIRLESIMULATEUR.setEnabled(true);
                Actions.FERMERLESIMULATEUR.setEnabled(false);
                Actions.DEMARRERSIMULATEUR.setEnabled(false);
                H3Etudiant.arreterSimulateur();
            }
        }

    }
}
