package hophophop.commun.modele.c;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TestFonction {
    private String fonction;
    private List<Parametre> parametres;
    private Resultat resultat;
    private int points;

    public TestFonction(String ligne) {
        String[] st = ligne.split("\t");
        this.parametres = new ArrayList();
        List<String> ls = Arrays.asList(st);
        int taille = ls.size();
        this.fonction = (String)ls.get(0);

        for(int index = 1; index < taille - 2; ++index) {
            this.parametres.add(new Parametre((String)ls.get(index)));
        }

        this.resultat = new Resultat((String)ls.get(taille - 2));
        this.points = Integer.parseInt((String)ls.get(taille - 1));
    }

    @Override
    public String toString() {
        /*String type = switch (this.resultat.getType()) {
            case "int" -> "%d";
            case "float" -> "%f";
            case "char" -> "%c";
            case "char *" -> "%s";
            default -> this.resultat.getType();
        };*/

        String type;
        if (this.resultat.getType() == "int"){
            type = "%d";
        }
        else if (this.resultat.getType() == "float"){
            type = "%f";
        }
        else if (this.resultat.getType() == "char"){
            type = "%c";
        }
        else if (this.resultat.getType() == "char *"){
            type = "%s";
        }else{
            type = this.resultat.getType();
        }


        String fct = this.fonction + "(";

        Parametre p;
        for(Iterator var3 = this.parametres.iterator(); var3.hasNext(); fct = fct + p.getVariable() + ", ") {
            p = (Parametre)var3.next();
        }

        fct = fct.substring(0, fct.length() - 2);
        fct = fct + ")";
        String resultat = "printf(\"Test :: " + fct.replace("\"", "\\\"") + " -> Attendu : " + this.resultat.getValeur().replace("\"", "\\\"") + ", Calculé : " + type + "\\n\"," + fct;
        resultat = resultat + "); ";
        if (this.resultat.getType().equals("char *")) {
            resultat = resultat + "score_test += strcmp(" + fct + "," + this.resultat.getValeur() + ") ? 0:" + this.points + ";";
        } else {
            resultat = resultat + "score_test += " + fct + "==" + this.resultat.getValeur() + " ? " + this.points + ":0;";
        }

        return resultat;
    }

    public String getFonction() {
        return this.fonction;
    }
}
