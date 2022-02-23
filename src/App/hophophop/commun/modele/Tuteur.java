//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class Tuteur extends Acteur implements Comparable<Tuteur>, Serializable {
    private List<Session> sessionsPossibles;
    private List<String> sessionsConnectes;
    private boolean administrateur;

    public Tuteur(String nom, String prenom, String username, List<Session> sessPoss, List<String> sessConn, boolean administrateur) {
        this(nom, prenom, username, administrateur);
        this.sessionsPossibles = sessPoss;
        this.sessionsConnectes = sessConn;
    }

    public Tuteur(String nom, String prenom, String username, boolean administrateur) {
        this(nom, prenom, username);
        this.administrateur = administrateur;
    }

    public Tuteur(String nom, String prenom, String username) {
        super(nom, prenom, username);
    }

    public List<Session> getSessionsPossibles() {
        return this.sessionsPossibles;
    }

    public Collection<?> getSessionsConnectes() {
        return this.sessionsConnectes;
    }

    public String toString() {
        return this.getNomComplet();
    }

    public String toStringDetail() {
        String retour = "Tuteur : " + this.getNomComplet();
        retour = retour + "\n\tUsername     : " + this.getUsername();
        retour = retour + "\n\tAppartient au(x) Groupe(s) : ";
        if (this.isAdmin()) {
            retour = retour + "\n\test Administrateur";
        } else {
            retour = retour + "\n\tn'est pas Administrateur";
        }

        retour = retour + "\n\tInscrit au(x) Session(s)    : " + this.getSessionsPossibles();
        retour = retour + "\n\tConnecté au(x) Sessions : " + this.getSessionsConnectes();
        return retour;
    }

    public boolean isAdmin() {
        return this.administrateur;
    }

    public int compareTo(Tuteur o) {
        return this.getNom().compareTo(o.getNom());
    }
}
