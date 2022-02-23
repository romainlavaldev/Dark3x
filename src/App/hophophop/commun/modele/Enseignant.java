//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Enseignant implements Serializable, Comparable<Enseignant> {
    private String username;
    private String password;
    private String nom;
    private String prenom;
    private List<Session> sessions;

    public Enseignant(String ensUsername, String nomEns, String prenomEns, String passEns) {
        this.username = ensUsername;
        this.password = passEns;
        this.nom = nomEns;
        this.prenom = prenomEns;
        this.sessions = new ArrayList();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String toString() {
        return "Enseignant : Username = " + this.username;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public List<Session> getSessions() {
        return this.sessions;
    }

    public int compareTo(Enseignant o) {
        return (this.getPrenom() + " " + this.getNom()).compareTo(o.getPrenom() + " " + o.getNom());
    }
}
