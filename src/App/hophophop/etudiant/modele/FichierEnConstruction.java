//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele;

class FichierEnConstruction {
    private String nom;
    private String type;
    private StringBuffer contenu;

    public FichierEnConstruction(String nom, String type) {
        this.nom = nom;
        this.type = type;
        this.contenu = new StringBuffer();
    }

    public StringBuffer getContenu() {
        return this.contenu;
    }

    public String getNom() {
        return this.nom;
    }

    public String getType() {
        return this.type;
    }

    public void inserer(String texte, int position) {
        try {
            this.contenu.insert(position, texte);
        } catch (StringIndexOutOfBoundsException var4) {
            H3Etudiant.getLogger().warning("Probleme inserer Fichier en construction TEXTE : " + texte + " POSITION " + position);
        }

    }

    public void supprimer(int debut, int fin) {
        try {
            this.contenu.delete(debut, fin);
        } catch (StringIndexOutOfBoundsException var4) {
            H3Etudiant.getLogger().warning("Probleme supprimer Fichier en construction DEBUT : " + debut + " FIN " + fin);
        }

    }
}
