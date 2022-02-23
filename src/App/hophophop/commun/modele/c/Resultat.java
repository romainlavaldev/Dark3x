package hophophop.commun.modele.c;

public class Resultat extends Signature {
    private String valeur;

    public Resultat(String resultat) {
        super(resultat);
        this.valeur = resultat.split(":")[1];
    }

    public String getValeur() {
        return this.valeur;
    }
}
