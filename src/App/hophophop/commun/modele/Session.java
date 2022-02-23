//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.io.Serializable;
import java.util.Calendar;

public class Session implements Comparable<Session>, Serializable {
    public static final int LECTURE_SEULE = 1;
    private String langage;
    private boolean groupee;
    private String nom;
    private boolean readOnly;
    private boolean testUnitaire;
    private long debut;
    private long fin;
    private String scenario;
    private boolean compilationProjet;

    public boolean isTestUnitaire() {
        return this.testUnitaire;
    }

    public void setTestUnitaire(boolean testUnitaire) {
        this.testUnitaire = testUnitaire;
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getLangage() {
        return this.langage;
    }

    public void setLangage(String langage) {
        this.langage = langage;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getDebut() {
        return this.debut;
    }

    public void setDebut(long debut) {
        this.debut = debut;
    }

    public long getFin() {
        return this.fin;
    }

    public void setFin(long fin) {
        this.fin = fin;
    }

    public String getScenario() {
        return this.scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public boolean isCompilationProjet() {
        return this.compilationProjet;
    }

    public void setCompilationProjet(boolean compilationProjet) {
        this.compilationProjet = compilationProjet;
    }

    public Session(String nom, String langage, long debut, long fin, boolean groupee) {
        this(nom);
        if (langage != null) {
            this.langage = langage;
        }

        this.debut = debut;
        this.fin = fin;
        this.groupee = groupee;
    }

    public Session(String nom) {
        this();
        this.nom = nom;
    }

    public String toString() {
        return this.nom;
    }

    public Session() {
        this.langage = "";
    }

    public int compareTo(Session o) {
        return this.getNom().compareTo(o.getNom());
    }

    public boolean isClose() {
        Long maintenant = Calendar.getInstance().getTimeInMillis();
        return this.debut >= maintenant || this.fin <= maintenant;
    }
}
