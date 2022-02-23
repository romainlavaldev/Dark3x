//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import hophophop.commun.modele.Indicateur.Resultat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;

public class IndicateurIntervention extends Indicateur {
    private String typeFeedback;
    private String momentFeedback;
    private String etat;
    private String indication;

    public IndicateurIntervention(Element ind) {
        this.setNom(ind.getAttributeValue("nom"));
        this.setIntitule(ind.getAttributeValue("intitule"));
        this.setType(ind.getAttributeValue("type"));
        this.setTemps(ind.getAttributeValue("temps"));
        this.setHeure(ind.getAttributeValue("heure"));
        this.setQuestion(ind.getAttributeValue("question"));
        this.setTypeEvenement(ind.getAttributeValue("typeEvenement"));
        this.setConcept(ind.getAttributeValue("concept"));
        this.typeFeedback = ind.getAttributeValue("typeFeedback") == null ? "" : ind.getAttributeValue("typeFeedback");
        this.momentFeedback = ind.getAttributeValue("momentFeedback") == null ? "" : ind.getAttributeValue("momentFeedback");
        this.etat = ind.getAttributeValue("etat") == null ? "" : ind.getAttributeValue("etat");
        this.indication = ind.getAttributeValue("indication") == null ? "" : ind.getAttributeValue("indication");
        List<Resultat> resultats = new ArrayList();
        List<Element> results = ind.getChildren("RESULTAT");
        Iterator var4 = results.iterator();

        while(var4.hasNext()) {
            Element res = (Element)var4.next();
            Resultat r = new Resultat(res.getAttributeValue("valeur"), res.getAttributeValue("localisation"), res.getAttributeValue("statut"), res.getAttributeValue("calculable"));
            resultats.add(r);
        }

        this.setResultats(resultats);
    }

    public IndicateurIntervention() {
        this.typeFeedback = "";
        this.momentFeedback = "";
        this.etat = "EN_ATTENTE";
        this.indication = "";
    }

    public Element toElement() {
        Element ind = (Element)super.toElement().clone();
        ind.setAttribute("typeFeedback", this.typeFeedback);
        ind.setAttribute("momentFeedback", this.momentFeedback);
        ind.setAttribute("etat", this.etat == null ? "EN_ATTENTE" : this.etat);
        ind.setAttribute("indication", this.indication == null ? "" : this.indication);
        return ind;
    }

    public void setTypeFeedback(String typeF) {
        this.typeFeedback = typeF;
    }

    public String getTypeFeedback() {
        return this.typeFeedback;
    }

    public void setMomentFeedback(String momentF) {
        this.momentFeedback = momentF;
    }

    public String getMomentFeedback() {
        return this.momentFeedback;
    }

    public String getEtat() {
        return this.etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String afficherIndicateur() {
        String r = "Indicateur " + this.getNom() + " Heure: " + this.getHeure() + "\tType/Moment/Etat/Indication : " + this.typeFeedback + "/" + this.momentFeedback + "/" + this.etat + "/" + this.indication;
        return r;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getIndication() {
        return this.indication;
    }
}
