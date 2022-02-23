//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.io.Serializable;
import java.util.Date;

public class TestUnitaire implements Serializable {
    private Date date;
    private int question;
    private boolean resultat;
    private String ancienEtat;
    private String nouvelEtat;
    private int nbSucces;
    private int nbEchecs;
    private int nbNonTraites;

    public TestUnitaire(Date date, int question, boolean resultat, String ancienEtat, String nouvelEtat, int nbSucces, int nbEchecs, int nbNonTraites) {
        this.date = date;
        this.question = question;
        this.resultat = resultat;
        this.ancienEtat = ancienEtat;
        this.nouvelEtat = nouvelEtat;
        this.nbSucces = nbSucces;
        this.nbEchecs = nbEchecs;
        this.nbNonTraites = nbNonTraites;
    }

    public Date getDate() {
        return this.date;
    }

    public int getQuestion() {
        return this.question;
    }

    public boolean isResultat() {
        return this.resultat;
    }

    public String getAncienEtat() {
        return this.ancienEtat;
    }

    public String getNouvelEtat() {
        return this.nouvelEtat;
    }

    public int getNbSucces() {
        return this.nbSucces;
    }

    public int getNbEchecs() {
        return this.nbEchecs;
    }

    public int getNbNonTraites() {
        return this.nbNonTraites;
    }
}
