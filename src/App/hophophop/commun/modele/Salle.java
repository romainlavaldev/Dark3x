//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Salle implements Serializable {
    private String num;
    private int nbRang;
    private List<Place> lesPlaces;

    public Salle(String num, int nbRang) {
        this.num = num;
        this.nbRang = nbRang;
        this.lesPlaces = new ArrayList();
    }

    public String getNum() {
        return this.num;
    }

    public void addPlaces(List<Place> places) {
        this.lesPlaces.addAll(places);
    }

    public void addPlace(Place p) {
        this.lesPlaces.add(p);
    }

    public List<Place> getLesPlaces() {
        return this.lesPlaces;
    }

    public void setPlace(int indice, Etudiant etu) {
        Place p = (Place)this.lesPlaces.get(indice);
        p.setEtudiant(etu);
        this.lesPlaces.remove(indice);
        this.lesPlaces.add(indice, p);
    }

    public int getNbRang() {
        return this.nbRang;
    }

    public String toString() {
        return this.num;
    }
}
