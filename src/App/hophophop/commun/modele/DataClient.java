//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.io.Serializable;
import java.util.Date;

public class DataClient implements Serializable {
    public static final Object ETUDIANT = "Etudiant";
    public static final Object ADMIN = "Admin";
    public static final Object TUTEUR = "Tuteur";
    private Date heureConnexion;
    private long dernierEvenement;
    private String nom;
    private String nomSession;
    private String lieu;
    private String type;
    private String plateforme;
    private String cle;
    private String versionJava;
    private String versionClient;

    public String getVersionJava() {
        return this.versionJava;
    }

    public DataClient(Date hc, long de, String nom, String nSession, String lieu, String plforme, String laVersionJava, String laVersionClient, String type, String cle) {
        this(hc, de, nom, nSession, lieu, plforme, laVersionJava, type, cle);
        this.versionClient = laVersionClient;
    }

    public DataClient(Date hc, long de, String nom, String nSession, String lieu, String plforme, String laVersionJava, String type, String cle) {
        this(hc, de, nom, nSession, lieu, plforme, type, cle);
        this.versionJava = laVersionJava;
    }

    public DataClient(Date hc, long de, String nom, String nSession, String lieu, String plforme, String type, String cle) {
        this.dernierEvenement = de;
        this.nomSession = nSession;
        this.nom = nom;
        this.plateforme = plforme;
        this.type = type;
        this.lieu = lieu;
        this.heureConnexion = hc;
        this.cle = cle;
    }

    public String getCle() {
        return this.cle;
    }

    public Date getHeureConnexion() {
        return this.heureConnexion;
    }

    public long getDernierEvenement() {
        return this.dernierEvenement;
    }

    public String getNom() {
        return this.nom;
    }

    public String getNomSession() {
        return this.nomSession;
    }

    public String getLieu() {
        return this.lieu;
    }

    public String getType() {
        return this.type;
    }

    public String getPlateforme() {
        return this.plateforme;
    }

    public String getVersionClient() {
        return this.versionClient;
    }
}
