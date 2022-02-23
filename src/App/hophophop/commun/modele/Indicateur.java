//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class Indicateur implements Cloneable {
    private String type;
    private String nom;
    private String intitule;
    private String heure;
    private String temps;
    private String question;
    private String typeEvenement;
    private String concept;
    private List<Indicateur.Resultat> resultats;

    public Indicateur() {
    }

    public Indicateur(Element e) {
        this.nom = e.getAttributeValue("type");
        this.intitule = TableCorrespondance.changerNomIndicateur(e.getAttributeValue("type"));
        List<Element> res = e.getChildren("result");
        this.type = ((Element)res.get(0)).getAttributeValue("category");
        this.temps = ((Element)res.get(0)).getAttributeValue("time");
        this.heure = this.getHeure(((Element)res.get(0)).getAttributeValue("time"));
        this.question = ((Element)res.get(0)).getAttributeValue("currentQuestion");
        this.typeEvenement = ((Element)res.get(0)).getAttributeValue("event");
        String c = ((Element)res.get(0)).getAttributeValue("concept");
        this.concept = c == null ? "" : c;
        this.resultats = new ArrayList();
        Iterator var4 = res.iterator();

        while(var4.hasNext()) {
            Element elem = (Element)var4.next();
            this.resultats.add(new Indicateur.Resultat(elem));
        }

    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getHeure() {
        if (this.heure == null) {
            this.heure = this.getHeure(this.temps);
        }

        return this.heure;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public String getTemps() {
        return this.temps;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setTypeEvenement(String typeEvenement) {
        this.typeEvenement = typeEvenement;
    }

    public String getTypeEvenement() {
        return this.typeEvenement;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setConcept(String co) {
        this.concept = co;
    }

    public String getConcept() {
        return this.concept;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getIntitule() {
        return this.intitule;
    }

    public void setResultats(List<Indicateur.Resultat> r) {
        this.resultats = r;
    }

    public List<Indicateur.Resultat> getResultats() {
        return this.resultats;
    }

    public Element toElement() {
        Element e = new Element("INDICATEUR");
        e.setAttribute("type", this.type);
        e.setAttribute("nom", this.nom);
        e.setAttribute("intitule", this.intitule);
        e.setAttribute("temps", this.temps);
        e.setAttribute("heure", this.heure == null ? this.getHeure(this.temps) : this.heure);
        e.setAttribute("concept", this.concept);
        e.setAttribute("question", this.question);
        e.setAttribute("typeEvenement", this.typeEvenement);
        Iterator var2 = this.resultats.iterator();

        while(var2.hasNext()) {
            Indicateur.Resultat s = (Indicateur.Resultat)var2.next();
            Element res = new Element("RESULTAT");
            res.setAttribute("valeur", s.getValeur());
            res.setAttribute("localisation", s.getLocalisation());
            res.setAttribute("statut", s.getStatut() ? "OK" : "PAS_OK");
            res.setAttribute("calculable", s.estCalculable() ? "OK" : "PAS_OK");
            e.addContent(res);
        }

        return e;
    }

    public String toElementString() {
        Element el = this.toElement();
        String s = (new XMLOutputter()).outputString(el);
        return s;
    }

    public Object clone() {
        Object o = null;

        try {
            o = super.clone();
        } catch (CloneNotSupportedException var3) {
            var3.printStackTrace(System.err);
        }

        return o;
    }

    private String getHeure(String time) {
        Date d = null;

        try {
            d = new Date(Long.parseLong(time.trim()));
        } catch (Exception var4) {
            d = new Date();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss, SSS");
        return formatter.format(d);
    }

    public boolean hasResultatCalculable() {
        Iterator var1 = this.resultats.iterator();

        Indicateur.Resultat r;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            r = (Indicateur.Resultat)var1.next();
        } while(!r.estCalculable());

        return true;
    }

    public boolean hasResultatStatutPasOK() {
        Iterator var1 = this.resultats.iterator();

        Indicateur.Resultat r;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            r = (Indicateur.Resultat)var1.next();
        } while(r.getStatut());

        return true;
    }

    public String getValeurResultatPasOk() {
        String val = "";
        Iterator var2 = this.resultats.iterator();

        while(var2.hasNext()) {
            Indicateur.Resultat r = (Indicateur.Resultat)var2.next();
            if (!r.getStatut() && !r.getValeur().equals("")) {
                val = val + r.getValeur() + " - ";
            }
        }

        val = val.equals("") ? val : val.substring(0, val.lastIndexOf(" -"));
        return val;
    }

    public String getLocalisationResultatPasOk() {
        String loc = "";
        Iterator var2 = this.resultats.iterator();

        while(var2.hasNext()) {
            Indicateur.Resultat r = (Indicateur.Resultat)var2.next();
            if (!r.getStatut() && !r.getLocalisation().equals("")) {
                loc = loc + r.getLocalisation() + " / ";
            }
        }

        loc = loc.equals("") ? loc : loc.substring(0, loc.lastIndexOf(" /"));
        return loc;
    }

    public String getValeurResultats() {
        String val = "";

        Indicateur.Resultat r;
        for(Iterator var2 = this.resultats.iterator(); var2.hasNext(); val = val + r.getValeur() + " - ") {
            r = (Indicateur.Resultat)var2.next();
        }

        val = val.equals("") ? val : val.substring(0, val.lastIndexOf(" -"));
        return val;
    }

    public String getLocalisationResultats() {
        String loc = "";

        Indicateur.Resultat r;
        for(Iterator var2 = this.resultats.iterator(); var2.hasNext(); loc = loc + r.getLocalisation() + " / ") {
            r = (Indicateur.Resultat)var2.next();
        }

        loc = loc.equals("") ? loc : loc.substring(0, loc.lastIndexOf(" /"));
        return loc;
    }

    public int getNombreResultatsPasOk() {
        int nb = 0;
        Iterator var2 = this.resultats.iterator();

        while(var2.hasNext()) {
            Indicateur.Resultat r = (Indicateur.Resultat)var2.next();
            if (!r.getStatut()) {
                ++nb;
            }
        }

        return nb;
    }

    public int getNombreResultatsOk() {
        int nb = 0;
        Iterator var2 = this.resultats.iterator();

        while(var2.hasNext()) {
            Indicateur.Resultat r = (Indicateur.Resultat)var2.next();
            if (r.getStatut()) {
                ++nb;
            }
        }

        return nb;
    }

    public int getNombreResultats() {
        return this.resultats.size();
    }

    public class Resultat {
        private String valeur;
        private String localisation;
        private boolean statut;
        private boolean calculable;

        public Resultat(Element element) {
            this.valeur = element.getAttributeValue("value");
            this.localisation = element.getAttributeValue("location");
            this.statut = element.getAttributeValue("status").equals("OK");
            this.calculable = element.getAttributeValue("calculable").equals("OK");
        }

        public Resultat(String val, String loc, String st, String cal) {
            this.valeur = val;
            this.localisation = loc;
            this.statut = st.equals("OK");
            this.calculable = st.equals("OK");
        }

        public void setValeur(String valeur) {
            this.valeur = valeur;
        }

        public String getValeur() {
            return this.valeur;
        }

        public void setLocalisation(String localisation) {
            this.localisation = localisation;
        }

        public String getLocalisation() {
            return this.localisation;
        }

        public boolean getStatut() {
            return this.statut;
        }

        public boolean estCalculable() {
            return this.calculable;
        }
    }
}
