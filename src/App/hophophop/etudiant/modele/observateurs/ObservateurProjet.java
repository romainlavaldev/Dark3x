//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.observateurs;

import hophophop.etudiant.modele.fichier.Fichier;
import hophophop.etudiant.modele.projet.Erreur;
import hophophop.etudiant.modele.projet.Projet;
import java.util.List;
import java.util.Set;

public interface ObservateurProjet {
    void ajoutFichier(Fichier var1);

    void suppressionFichier(Fichier var1);

    void compilationProjet(Projet var1, String var2, List<Erreur> var3, Set<Fichier> var4, boolean var5);

    void executionProjet(Projet var1, String var2);
}
