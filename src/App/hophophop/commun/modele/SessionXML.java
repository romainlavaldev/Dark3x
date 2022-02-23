//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SessionXML extends Session implements Serializable {
    private List<String> lstGroupes = new ArrayList();
    private List<String> lstTuteurs = new ArrayList();
    private List<String> lstEtudiants = new ArrayList();

    public SessionXML() {
    }

    public String toXml() {
        String resultat = "<SESSION NOM=\"" + this.getNom() + "\"";
        if (this.getLangage() != null) {
            resultat = resultat + " LANGAGE=\"" + this.getLangage() + "\"";
        }

        resultat = resultat + " COMPILATION=\"";
        if (this.isCompilationProjet()) {
            resultat = resultat + "PROJET";
        } else {
            resultat = resultat + "FICHIER";
        }

        resultat = resultat + "\" DEBUT=\"" + this.getDebut() + "\" FIN=\"" + this.getFin() + "\" LECTURE=\"";
        if (this.isReadOnly()) {
            resultat = resultat + "1";
        } else {
            resultat = resultat + "0";
        }

        resultat = resultat + "\">\n";
        Iterator var2;
        String e;
        if (!this.lstGroupes.isEmpty()) {
            for(var2 = this.lstGroupes.iterator(); var2.hasNext(); resultat = resultat + "\t<GROUPE>" + e + "</GROUPES>\n") {
                e = (String)var2.next();
            }
        }

        if (!this.lstTuteurs.isEmpty()) {
            for(var2 = this.lstTuteurs.iterator(); var2.hasNext(); resultat = resultat + "\t<ENSEIGNANT>" + e + "</ENSEIGNANT>\n") {
                e = (String)var2.next();
            }
        }

        if (!this.lstEtudiants.isEmpty()) {
            for(var2 = this.lstEtudiants.iterator(); var2.hasNext(); resultat = resultat + "\t<ETUDIANT>" + e + "</ETUDIANT>\n") {
                e = (String)var2.next();
            }
        }

        resultat = resultat + "</SESSION>";
        return resultat;
    }

    public List<String> getLstGroupes() {
        return this.lstGroupes;
    }

    public List<String> getLstEtudiants() {
        return this.lstEtudiants;
    }

    public List<String> getLstTuteurs() {
        return this.lstTuteurs;
    }
}
