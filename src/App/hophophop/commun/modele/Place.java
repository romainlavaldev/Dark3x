//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.io.Serializable;

public class Place implements Serializable {
    private String num;
    private String ip;
    private String rang;
    private Etudiant etudiant;

    public Place(String rang, String num, String ip) {
        this.rang = rang;
        this.num = num;
        this.ip = ip;
        this.etudiant = null;
    }

    public String getRang() {
        return this.rang;
    }

    public String getNum() {
        return this.num;
    }

    public String getIp() {
        return this.ip;
    }

    public void setEtudiant(Etudiant etu) {
        this.etudiant = etu;
    }

    public void removeEtudiant(String etu) {
        this.etudiant = null;
    }

    public Etudiant getEtudiant() {
        return this.etudiant;
    }

    public String toString() {
        return this.etudiant != null ? this.etudiant.getPrenom() + " " + this.etudiant.getNom() : this.rang + this.num;
    }
}
