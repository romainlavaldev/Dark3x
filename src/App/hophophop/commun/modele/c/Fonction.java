package hophophop.commun.modele.c;

import java.util.Map;

public class Fonction {
    private String nom;
    private String type;
    private String entete;
    private String corps;
    private int lDebut;
    private int lFin;
    private Map<String, String> parametres;

    public Fonction(String entete, int ldebut, int lfin) {
        this.entete = entete;
        this.lDebut = ldebut;
        this.lFin = lfin;
    }

    public Fonction(String nomFonction, String typeFonction, String enteteFonction, String corpsFonction, int ldebut, int lfin) {
        this(enteteFonction, ldebut, lfin);
        this.nom = nomFonction;
        this.type = typeFonction;
        this.corps = corpsFonction;
    }

    public String getCode() {
        return this.type + " " + this.entete + "\n" + this.corps + "\n";
    }

    public String toString() {
        return "La Fonction " + this.nom + " -> " + this.lDebut + ":" + this.lFin + "\n" + this.type + " " + this.entete + "\n" + this.corps + "\n";
    }
}
