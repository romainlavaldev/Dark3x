//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Groupe implements Comparable<Groupe>, Serializable {
    private List<Etudiant> etudiants;
    private String id;
    private String nom;

    public Groupe(String nom) {
        this.nom = nom;
        this.etudiants = new ArrayList();
    }

    public Groupe(String nom, List<Etudiant> listeE) {
        this(nom);
        this.etudiants = listeE;
    }

    public Groupe(String id, String nom) {
        this(nom);
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public String toString() {
        return this.getNom();
    }

    public int compareTo(Groupe o) {
        return this.getNom().compareTo(o.getNom());
    }

    public List<Etudiant> getEtudiants() {
        return this.etudiants;
    }
}
