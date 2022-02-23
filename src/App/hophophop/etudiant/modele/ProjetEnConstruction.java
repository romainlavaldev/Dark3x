//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ProjetEnConstruction {
    private String nom;
    private String type;
    private boolean compilationProjet;
    private List<FichierEnConstruction> fichiers;

    public ProjetEnConstruction(String nom, String type, String compilationProjet) {
        this.nom = nom;
        this.type = type;
        if (compilationProjet == null) {
            this.compilationProjet = H3Etudiant.isCompilationProjet();
        } else if (compilationProjet.equals("true")) {
            this.compilationProjet = true;
        } else {
            this.compilationProjet = false;
        }

        this.fichiers = new ArrayList();
    }

    public boolean isCompilationProjet() {
        return this.compilationProjet;
    }

    public void setCompilationProjet(boolean compilationProjet) {
        this.compilationProjet = compilationProjet;
    }

    public String getNom() {
        return this.nom;
    }

    public String getType() {
        return this.type;
    }

    public void ajouterFichier(FichierEnConstruction fichier) {
        this.fichiers.add(fichier);
    }

    public void supprimerFichier(FichierEnConstruction fichier) {
        this.fichiers.remove(fichier);
    }

    public FichierEnConstruction getFichier(String nom) {
        Iterator var2 = this.fichiers.iterator();

        FichierEnConstruction fichier;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            fichier = (FichierEnConstruction)var2.next();
        } while(fichier.getNom().compareTo(nom) != 0);

        return fichier;
    }

    public List<FichierEnConstruction> getFichiers() {
        return this.fichiers;
    }
}
