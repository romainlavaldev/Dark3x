//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.io.Serializable;
import java.util.List;

public class Etudiant extends Acteur implements Serializable, Comparable<Etudiant> {
    private List<String> sessionsDemarrees;
    private List<String> sessionsConnectes;
    private String ip;
    private String password;
    private String mail;

    public Etudiant(String username, String nom, String prenom, String ip) {
        this(username, nom, prenom);
        this.ip = ip;
    }

    public Etudiant(String nom, String prenom, String username, List<String> sessionsDemarrees, List<String> sessionsConnectees) {
        super(nom, prenom, username);
        this.ip = null;
        this.sessionsDemarrees = sessionsDemarrees;
        this.sessionsConnectes = sessionsConnectees;
    }

    public Etudiant(String nom, String prenom, String username, List<String> sessionsDemarrees, List<String> sessionsConnectees, String ip) {
        this(nom, prenom, username, sessionsDemarrees, sessionsConnectees);
        this.ip = ip;
    }

    public Etudiant(String username, String nom, String prenom) {
        super(nom, prenom, username);
    }

    public Etudiant(String username, String nom, String prenom, String password, String mail) {
        this(username, nom, prenom);
        this.password = password;
        this.mail = mail;
    }

    public List<String> getSessionsConnectes() {
        return this.sessionsConnectes;
    }

    public List<String> getSessionsDemarrees() {
        return this.sessionsDemarrees;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String toString() {
        return this.getNom() + " " + this.getPrenom();
    }

    public String info() {
        return "Nom : " + this.getNom() + ", Prénom : " + this.getPrenom() + ", Login : " + this.getUsername();
    }

    public String toStringDetail() {
        String retour = "ETUDIANT : " + this.getNomComplet();
        retour = retour + "\n\tUsername     : " + this.getUsername();
        retour = retour + "\n\tNom          : " + this.getNom();
        retour = retour + "\n\tPrénom       : " + this.getPrenom();
        retour = retour + "\n\tAppartient au(x) Groupe(s) : ";
        retour = retour + "\n\tConnecté sur l'Ip : " + this.ip;
        return retour;
    }

    public int compareTo(Etudiant o) {
        return this.getNom().compareTo(o.getNom());
    }

    public boolean equals(Object etu) {
        return etu instanceof Etudiant ? this.username.equals(((Etudiant)etu).getUsername()) : false;
    }

    public String getPassword() {
        return this.password;
    }

    public String getMail() {
        return this.mail;
    }
}
