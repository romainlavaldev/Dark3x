//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.projet;

import hophophop.etudiant.modele.fichier.Fichier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjetPython extends Projet {
    public ProjetPython() {
    }

    public ProjetPython(String nom, boolean compilationProjet) {
        super(nom, compilationProjet);
        this.exprReguliereTypeFichier = "([^.]*[.]py$)";
    }

    public int compiler(boolean dialogue) {
        this.enregistrer();
        return 0;
    }

    public void executer(String main) {
        String commande = "python " + this.getRepertoire() + main;
        super.executer(commande);
    }

    public List<String> getFichiersMain() {
        List<String> mains = new ArrayList();
        Iterator var2 = this.getFichiers().iterator();

        while(var2.hasNext()) {
            Fichier fichier = (Fichier)var2.next();
            mains.add(fichier.getNom());
        }

        return mains;
    }

    public String getType() {
        return "Python";
    }

    public int compilerUnFichier(boolean dialogue, Fichier fichier) {
        return 0;
    }
}
