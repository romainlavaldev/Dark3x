//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.projet;

import hophophop.commun.modele.Evenement;
import hophophop.etudiant.modele.H3Etudiant;
import hophophop.etudiant.modele.fichier.Fichier;
import hophophop.etudiant.modele.observateurs.ObservateurProjet;
import hophophop.etudiant.vue.Actions;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.DefaultCompletionProvider;

public class ProjetNxc extends Projet {
    static boolean SIMULATEUR = false;

    public static boolean isSIMULATEUR() {
        return SIMULATEUR;
    }

    public static void setSIMULATEUR(boolean simulateur) {
        SIMULATEUR = simulateur;
    }

    public ProjetNxc(String nom, boolean compilationProjet) {
        super(nom, compilationProjet);
        this.exprReguliereTypeFichier = "([^.]*[.]nxc$)";
        H3Etudiant.getLogger().info("Plateforme : " + System.getProperty("os.name"));
    }

    public String getCompilateur() {
        String compilateur = "";
        if (isSIMULATEUR()) {
            compilateur = "gcc -Wno-main-return-type  -I " + H3Etudiant.DOSSIER_CODELAB + " -include init.h ";
            return compilateur;
        } else {
            if (System.getProperty("os.name").indexOf("Windows") != -1) {
                compilateur = H3Etudiant.DOSSIER_OUTILS + "nbc.exe ";
            } else if (System.getProperty("os.name").indexOf("Mac") != -1) {
                compilateur = H3Etudiant.DOSSIER_OUTILS + "nbcMacOsX ";
            } else if (System.getProperty("os.name").indexOf("Linux") != -1) {
                compilateur = H3Etudiant.DOSSIER_OUTILS + "nbcLinux ";
            }

            return compilateur + " -w- -sm- ";
        }
    }

    public String getExecuteur() {
        String executeur = "";
        if (isSIMULATEUR()) {
            executeur = "./";
            return executeur;
        } else {
            executeur = this.getCompilateur();
            return executeur + " -S=usb -d ";
        }
    }

    public int compiler(boolean manuelle) {
        return -1;
    }

    public void executer(String main) {
        int valeurDeSortie = 0;
        final String commande;
        if (isSIMULATEUR()) {
            commande = this.getRepertoire() + main.replace(".nxc", "");
            H3Etudiant.getLogger().info("Exécution : " + commande);
            (new Thread() {
                public void run() {
                    try {
                        H3Etudiant.getFenetre().getConsole().efface();
                        Process processus = Runtime.getRuntime().exec(commande);
                        H3Etudiant.getLogger().info("Création du Processus de Pid : " + processus.pid());
                        H3Etudiant.setProcessusExecution(processus);
                        Actions.EXECUTER.setEnabled(false);
                        processus.waitFor();
                        Actions.EXECUTER.setEnabled(true);
                        Thread.sleep(500L);
                        H3Etudiant.arreterSimulateur();
                        String sortie = H3Etudiant.getFenetre().getConsole().getText();
                        Evenement evenement = Evenement.getEvenementExecution(ProjetNxc.this.getNom(), sortie);
                        H3Etudiant.getTraceur().envoyer(evenement);
                    } catch (IOException var4) {
                        var4.printStackTrace();
                    } catch (InterruptedException var5) {
                        var5.printStackTrace();
                    }

                }
            }).start();
        } else {
            commande = this.getExecuteur() + this.getRepertoire() + main;
            H3Etudiant.getLogger().info("Exécution : " + commande);

            try {
                Process processus = Runtime.getRuntime().exec(commande);
                String sortie = "";
                BufferedReader bufferSortie = new BufferedReader(new InputStreamReader(processus.getInputStream()));
                BufferedReader bufferErreur = new BufferedReader(new InputStreamReader(processus.getErrorStream()));

                try {
                    valeurDeSortie = processus.waitFor();
                } catch (InterruptedException var11) {
                    var11.printStackTrace();
                }

                String ligne;
                while(bufferSortie.ready() && (ligne = bufferSortie.readLine()) != null) {
                    sortie = sortie + ligne + "\n";
                }

                while(bufferErreur.ready() && (ligne = bufferErreur.readLine()) != null) {
                    sortie = sortie + ligne + "\n";
                }

                if (valeurDeSortie != 0) {
                    if (valeurDeSortie == 1) {
                        H3Etudiant.getLogger().info("Problème de compilation");
                    } else if (valeurDeSortie == 2) {
                        new JOptionPane();
                        JOptionPane.showMessageDialog((Component)null, "1) Vérifiez que le robot est allumé et branché au port USB,\n2) Si c'est le cas, Détruisez les fichiers de la mémoire (menu Settings/Delete Files).", "Problème sur la brique Robot", 0);
                        H3Etudiant.getLogger().info("NXT n'est pas connecte au port USB");
                    } else {
                        H3Etudiant.getLogger().severe("Valeur de sortie inconnue : " + valeurDeSortie);
                    }
                }

                Iterator var9 = this.getObservateurs().iterator();

                while(var9.hasNext()) {
                    ObservateurProjet observateur = (ObservateurProjet)var9.next();
                    observateur.executionProjet(this, sortie);
                }

                Evenement evenement = Evenement.getEvenementExecution(this.getNom(), sortie);
                H3Etudiant.getTraceur().envoyer(evenement);
            } catch (IOException var12) {
                var12.printStackTrace();
            }
        }

    }

    public List<String> getFichiersMain() {
        Pattern pattern = Pattern.compile(".*^(.*\\s)?main\\s?\\(.*\\).*\\{.*\\}.*", 40);
        List<String> mains = new ArrayList();
        Iterator var3 = this.getFichiers().iterator();

        while(var3.hasNext()) {
            Fichier fichier = (Fichier)var3.next();

            try {
                String code = fichier.getSource().getText(0, fichier.getSource().getLength());
                if (pattern.matcher(code).matches()) {
                    mains.add(fichier.getNom());
                }
            } catch (BadLocationException var6) {
                var6.printStackTrace();
            }
        }

        return mains;
    }

    public String getType() {
        return "Nxc";
    }

    public int compileCommande(boolean manuelle, String commande) {
        int valeurDeSortie = 0;
        String sortie = "";
        H3Etudiant.getLogger().info("Compilation : " + commande);

        try {
            Process processus = Runtime.getRuntime().exec(commande);
            BufferedReader bufferSortie = new BufferedReader(new InputStreamReader(processus.getInputStream()));
            BufferedReader bufferErreur = new BufferedReader(new InputStreamReader(processus.getErrorStream()));

            try {
                valeurDeSortie = processus.waitFor();
            } catch (InterruptedException var9) {
                var9.printStackTrace();
            }

            String ligne;
            while(bufferSortie.ready() && (ligne = bufferSortie.readLine()) != null) {
                sortie = sortie + ligne + "\n";
            }

            while(bufferErreur.ready() && (ligne = bufferErreur.readLine()) != null) {
                sortie = sortie + ligne + "\n";
            }
        } catch (IOException var10) {
            var10.printStackTrace();
        }

        Evenement evenement;
        if (manuelle) {
            evenement = Evenement.getEvenementCompilationManuelle(this.getNom(), sortie);
        } else {
            evenement = Evenement.getEvenementCompilationAutomatique(this.getNom(), sortie);
        }

        H3Etudiant.getTraceur().envoyer(evenement);
        Iterator var12 = this.getObservateurs().iterator();

        while(var12.hasNext()) {
            ObservateurProjet observateur = (ObservateurProjet)var12.next();
            observateur.compilationProjet(this, sortie, this.getListeErreur(), this.getEnsembleFichierAvecErreur(), true);
        }

        return valeurDeSortie;
    }

    public int compilerUnFichier(boolean manuelle, Fichier fichierVisible) {
        this.enregistrer();
        String nomDuFichierNxc = fichierVisible.getFichierSource();
        String commande = this.getCompilateur();
        if (isSIMULATEUR()) {
            String nomDuFichierC = nomDuFichierNxc.replace(".nxc", ".c");
            String nomDuFichierExecutable = nomDuFichierNxc.replace(".nxc", "");
            commande = commande + nomDuFichierC;

            try {
                Files.copy((new File(nomDuFichierNxc)).toPath(), (new File(nomDuFichierC)).toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException var8) {
                var8.printStackTrace();
            }

            commande = commande + " -o " + nomDuFichierExecutable;
            if (System.getProperty("os.name").indexOf("Windows") != -1) {
                commande = commande + " -lws2_32";
            }

            commande = commande + " -lm";
        } else {
            commande = commande + nomDuFichierNxc;
        }

        return this.compileCommande(manuelle, commande);
    }

    public void initialiseCompletionProvider() {
        DefaultCompletionProvider provider = H3Etudiant.getProvider();
        provider.clear();
        provider.addCompletion(new BasicCompletion(provider, "motorOn("));
        provider.addCompletion(new BasicCompletion(provider, "motorOff("));
        provider.addCompletion(new BasicCompletion(provider, "resetMotorRotationCount("));
        provider.addCompletion(new BasicCompletion(provider, "motorRotationCount("));
        provider.addCompletion(new BasicCompletion(provider, "getSensorValue("));
        provider.addCompletion(new BasicCompletion(provider, "printStr("));
        provider.addCompletion(new BasicCompletion(provider, "printFormat("));
        provider.addCompletion(new BasicCompletion(provider, "printInt("));
        provider.addCompletion(new BasicCompletion(provider, "printFloat("));
        provider.addCompletion(new BasicCompletion(provider, "printStrLn("));
        provider.addCompletion(new BasicCompletion(provider, "printIntLn("));
        provider.addCompletion(new BasicCompletion(provider, "printFloatLn("));
        provider.addCompletion(new BasicCompletion(provider, "printLongLn("));
        provider.addCompletion(new BasicCompletion(provider, "printLong("));
        provider.addCompletion(new BasicCompletion(provider, "getNumPadValue("));
        provider.addCompletion(new BasicCompletion(provider, "newline("));
        provider.addCompletion(new BasicCompletion(provider, "waitFor("));
        provider.addCompletion(new BasicCompletion(provider, "systemTime("));
        provider.addCompletion(new BasicCompletion(provider, "initRandom("));
        provider.addCompletion(new BasicCompletion(provider, "randomInt("));
        provider.addCompletion(new BasicCompletion(provider, "clearScreen("));
        provider.addCompletion(new BasicCompletion(provider, "setColor("));
        provider.addCompletion(new BasicCompletion(provider, "drawPoint("));
        provider.addCompletion(new BasicCompletion(provider, "drawLine("));
        provider.addCompletion(new BasicCompletion(provider, "drawCircle("));
        provider.addCompletion(new BasicCompletion(provider, "turtleShow("));
        provider.addCompletion(new BasicCompletion(provider, "turtleHide("));
        provider.addCompletion(new BasicCompletion(provider, "turtleGoto("));
        provider.addCompletion(new BasicCompletion(provider, "turtleReset("));
        provider.addCompletion(new BasicCompletion(provider, "turtleForward("));
        provider.addCompletion(new BasicCompletion(provider, "turtleTurnLeft("));
        provider.addCompletion(new BasicCompletion(provider, "turtleTurnRight("));
        provider.addCompletion(new BasicCompletion(provider, "COLOR_BLACK"));
        provider.addCompletion(new BasicCompletion(provider, "COLOR_WHITE"));
        provider.addCompletion(new BasicCompletion(provider, "COLOR_RED"));
        provider.addCompletion(new BasicCompletion(provider, "COLOR_GREEN"));
        provider.addCompletion(new BasicCompletion(provider, "COLOR_YELLOW"));
        provider.addCompletion(new BasicCompletion(provider, "COLOR_BLUE"));
        provider.addCompletion(new BasicCompletion(provider, "COLOR_ERROR"));
        provider.addCompletion(new BasicCompletion(provider, "OUT_A"));
        provider.addCompletion(new BasicCompletion(provider, "OUT_B"));
        provider.addCompletion(new BasicCompletion(provider, "OUT_C"));
        provider.addCompletion(new BasicCompletion(provider, "OUT_AB"));
        provider.addCompletion(new BasicCompletion(provider, "OUT_AC"));
        provider.addCompletion(new BasicCompletion(provider, "OUT_BC"));
        provider.addCompletion(new BasicCompletion(provider, "OUT_ABC"));
        provider.addCompletion(new BasicCompletion(provider, "IN_1"));
        provider.addCompletion(new BasicCompletion(provider, "IN_2"));
        provider.addCompletion(new BasicCompletion(provider, "IN_3"));
        provider.addCompletion(new BasicCompletion(provider, "IN_4"));
    }
}
