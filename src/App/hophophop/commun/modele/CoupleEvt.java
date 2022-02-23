package hophophop.commun.modele;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CoupleEvt implements Serializable {
    String typeEvenement;
    String valEvenement;
    private static Map<String, String> decodeEvenement = new HashMap();

    public static Map<String, String> getDecodeEvenement() {
        return decodeEvenement;
    }

    public String toString() {
        return (String)decodeEvenement.get(this.typeEvenement) + " : " + this.valEvenement;
    }

    public String getTypeEvenement() {
        return this.typeEvenement;
    }

    public void setTypeEvenement(String typeEvenement) {
        this.typeEvenement = typeEvenement;
    }

    public String getValEvenement() {
        return this.valEvenement;
    }

    public void setValEvenement(String valEvenement) {
        this.valEvenement = valEvenement;
    }

    public CoupleEvt(String typeEvenement, String valEvenement) {
        this.typeEvenement = typeEvenement;
        this.valEvenement = valEvenement;
    }

    static {
        decodeEvenement.put("AF", "Ajout du Fichier");
        decodeEvenement.put("AP", "Ajout du Projet");
        decodeEvenement.put("SF", "Suppression du Fichier");
        decodeEvenement.put("SP", "Suppression du Projet");
    }
}
