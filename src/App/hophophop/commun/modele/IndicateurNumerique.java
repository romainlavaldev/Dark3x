//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.io.Serializable;

public class IndicateurNumerique implements Serializable {
    private String intitule;
    private int valeur;

    public IndicateurNumerique(String intitule, int valeur) {
        this.intitule = intitule;
        this.valeur = valeur;
    }

    public String getIntitule() {
        return this.intitule;
    }

    public int getValeur() {
        return this.valeur;
    }
}
