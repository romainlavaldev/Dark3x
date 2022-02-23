//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele;

import hophophop.commun.modele.Evenement;
import hophophop.etudiant.Main;
import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class Traceur {
    public String DOSSIER_ETUDIANT;
    public String DOSSIER_SESSION;
    private ObjectOutputStream out;
    private String fichierTraceLocale;
    private boolean tracerActif;
    private boolean flagRestoration;

    public Traceur() {
        this.DOSSIER_ETUDIANT = H3Etudiant.DOSSIER_TRACE + File.separator + H3Etudiant.getNomCompletEtudiant();
        this.DOSSIER_SESSION = this.DOSSIER_ETUDIANT + File.separator + H3Etudiant.getNomDeLaSession() + File.separator;
        this.tracerActif = false;
        this.flagRestoration = false;
        (new File(this.DOSSIER_ETUDIANT)).mkdirs();
        (new File(this.DOSSIER_SESSION)).mkdirs();
        this.fichierTraceLocale = this.DOSSIER_SESSION;
        Long timeEnMilli = Calendar.getInstance().getTimeInMillis();
        Date d = new Date(timeEnMilli);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.fichierTraceLocale = this.fichierTraceLocale + formatter.format(d) + "-";
        formatter = new SimpleDateFormat("HH");
        this.fichierTraceLocale = this.fichierTraceLocale + formatter.format(d) + "h";
        formatter = new SimpleDateFormat("mm");
        this.fichierTraceLocale = this.fichierTraceLocale + formatter.format(d) + "";
        this.fichierTraceLocale = this.fichierTraceLocale + ".xml";
        H3Etudiant.getLogger().info("Nom du fichier de Trace Local " + this.fichierTraceLocale);
        File[] lstFile = (new File(this.DOSSIER_SESSION)).listFiles(new FilenameFilter() {
            public boolean accept(File arg0, String arg1) {
                return arg1.endsWith("xml");
            }
        });

        for(int i = 0; i < lstFile.length; ++i) {
            lstFile[i].renameTo(new File(lstFile[i].getAbsolutePath() + ".h3x"));
        }

        try {
            OutputStreamWriter outSw = new OutputStreamWriter(new FileOutputStream(this.fichierTraceLocale), "UTF-8");
            outSw.write("<?xml version=\"1.1\" encoding=\"UTF-8\"?>\n<TRACE>\n");
            outSw.close();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public void connecter(ObjectOutputStream out) {
        this.out = out;
    }

    public void deconnecter() {
        try {
            this.envoyer(Evenement.getEvenementFin());
            this.out.close();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        this.out = null;
    }

    public void envoyer(Evenement evenement) {
        if (this.tracerActif) {
            if (!Main.isSimulation()) {
                this.sauvegardeLocale(this.getFichierTraceLocale(), evenement);
            }

            try {
                this.out.writeObject(evenement);
            } catch (IOException var3) {
                H3Etudiant.getLogger().severe("Evènement : " + evenement.getType());
                H3Etudiant.getLogger().severe("Communication est coupée avec le serveur " + var3.getMessage() + " : out = " + this.out);
                JOptionPane.showMessageDialog((Component)null, "La Communication est coupée avec le serveur\nL'application va se terminer\nVous devez vous reconnecter");
                System.exit(0);
            }
        }

    }

    public void sauvegardeLocale(String nomFichier, Evenement evenement) {
        long timeMillis = Calendar.getInstance().getTimeInMillis();
        Date d = new Date(timeMillis);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy-HH:mm:ss");
        evenement.setTemps(timeMillis);
        evenement.setHeure(formatter.format(d));

        try {
            Format format = Format.getRawFormat();
            format.setEncoding("UTF-8");
            XMLOutputter out = new XMLOutputter(format);
            OutputStreamWriter pw = new OutputStreamWriter(new FileOutputStream(nomFichier, true), "UTF-8");
            pw.write(new String(out.outputString(evenement.toElement())));
            pw.write("\n");
            pw.close();
        } catch (Exception var10) {
            var10.printStackTrace();
        }

    }

    public String getFichierTraceLocale() {
        return this.fichierTraceLocale;
    }

    public boolean isTracerActif() {
        return this.tracerActif;
    }

    public void setTracerActif(boolean tracerActif) {
        this.tracerActif = tracerActif;
    }
}
