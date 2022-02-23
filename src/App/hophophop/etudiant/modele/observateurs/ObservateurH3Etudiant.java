//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.observateurs;

import hophophop.etudiant.modele.projet.Projet;

public interface ObservateurH3Etudiant {
    void connexion();

    void deconnexion();

    void ajoutProjet(Projet var1);

    void suppressionProjet(Projet var1);
}
