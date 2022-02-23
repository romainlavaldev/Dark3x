package hophophop.commun.modele;

import java.io.Serializable;

public class Acteur implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String username;
    private String nom;
    private String prenom;

    public Acteur(String nom, String prenom, String username) {
        this(nom, prenom);
        this.username = username;
    }

    public Acteur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getUsername() {
        return this.username;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getNomComplet() {
        return this.getPrenom() + " " + this.getNom();
    }
}
