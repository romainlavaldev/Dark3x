//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele;

import hophophop.etudiant.modele.fichier.FichierAutre;
import hophophop.etudiant.modele.projet.Projet;
import java.util.ArrayList;
import java.util.List;

public class Dossier {
    List<FichierAutre> fichiers = new ArrayList();
    String nom;
    Projet projet;

    public Dossier(Projet projet, String string) {
        this.projet = projet;
        this.nom = string;
    }

    public Projet getProjet() {
        return this.projet;
    }

    public void ajouteFichier(FichierAutre fichier) {
        this.fichiers.add(fichier);
    }

    public void SupprimeFichier(FichierAutre fichier) {
        this.fichiers.remove(fichier);
    }

    public String getNom() {
        return this.nom;
    }

    public List<FichierAutre> getFichiers() {
        return this.fichiers;
    }
}
