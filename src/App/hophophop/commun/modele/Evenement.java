//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jdom.Element;

public class Evenement implements Serializable, Comparable {
    private static final long serialVersionUID = 424148277175329149L;
    public static final String CONNECTION = "CONNECTION";
    public static final String DECONNECTION = "DECONNECTION";
    private static final String RACINE = "E";
    public static final String ANNOTATION = "ANNOTATION";
    public static final String FIN_SESSION = "FIN";
    public static final String EXECUTION = "E";
    public static final String SUPPRESSION_TEXTE = "ST";
    public static final String INSERTION_TEXTE = "IT";
    public static final String AJOUT_PROJET = "AP";
    public static final String SUPPRESSION_PROJET = "SP";
    public static final String OUVERTURE_FICHIER = "OF";
    public static final String SUPPRESSION_FICHIER = "SF";
    public static final String AJOUT_FICHIER = "AF";
    public static final String ENREGISTREMENT_FICHIER = "EF";
    public static final String ENREGISTREMENT_PROJET = "EP";
    private static final String TEMPS = "T";
    public static final String DATE = "D";
    public static final String POSITION = "N";
    public static final String DEBUT = "A";
    public static final String FIN = "Z";
    public static final String FICHIER = "F";
    public static final String FICHIERSON = "ENREGISTREMENT";
    public static final String PROJET = "P";
    public static final String SORTIE = "S";
    private static final String TYPE = "K";
    public static final String SESSION = "SESSION";
    public static final String TEXTESUPPRIME = "D";
    public static final String TYPE_FICHIER = "TF";
    public static final String TYPE_PROJET = "TP";
    public static final String TEXTE = "T";
    private static final String SELECTIONERREUR = "SE";
    private static final String MESSAGEERREUR = "M";
    private static final String NUMERODELIGNE = "L";
    public static final String MESSAGETUTEURVERSETUDIANT = "MT";
    public static final String MESSAGESERVEURVERSETUDIANT = "MS";
    private static final String HEURE = "H";
    public static final String MESSAGE = "MESSAGE";
    public static final String ETUDIANT = "ETUDIANT";
    private static final String HEUREFICHIER = "HEUREFICHIER";
    public static final String POSDEB = "POSDEB";
    public static final String POSFIN = "POSFIN";
    public static final String ENSEIGNANT = "ENSEIGNANT";
    public static final String TUTEUR = "TUTEUR";
    public static final String SELECTIONQUESTION = "SQ";
    public static final String COMPILATIONMANUELLE = "CM";
    public static final String COMPILATIONAUTOMATIQUE = "CA";
    public static final String COMPILATIONAUTOMATIQUEECHEC = "CAE";
    public static final String COMPILATIONMANUELLEECHEC = "CME";
    public static final String APPELAUSECOURS = "HELP";
    public static final String INITIERCAUSERIE = "INITIERCAUSERIE";
    public static final String TERMINERCAUSERIE = "TERMINERCAUSERIE";
    public static final String NAVIGATIONQUESTION = "NQ";
    public static String NUMEROQUESTION = "NQ";
    public static String QUESTIONAVANT = "QAVANT";
    public static String QUESTIONAPRES = "QAPRES";
    private static String ENONCEQUESTION = "EQ";
    public static final String INTERVENTION = "INTERVENTION";
    public static final String CONFIRMERINTENTION = "CONFIRMERINTENTION";
    public static final String CONFIRMERINTERVENTION = "CONFIRMER_INTERVENTION";
    public static final String TYPEINTERVENTION = "TYPE_INTERVENTION";
    public static final String CONTENUINTERVENTION = "CONTENU_INTERVENTION";
    public static final String TIMEFEEDBACK = "TIME_FEEDBACK";
    public static final String TIMEINTERVENTION = "TIME_INTERVENTION";
    public static final String ETATINTERVENTION = "ETAT_INTERVENTION";
    public static final String ANNOTERINTERVENTION = "ANNOTER_INTERVENTION";
    public static final String MODIFIERETATINTERVENTION = "MODIFIER_ETAT_INTERVENTION";
    public static final String INDICATEURS = "INDICATEURS";
    public static final String INDICATEURSSELECTIONNES = "INDICATEURS_SELECTIONNES";
    public static final String INTERVENIRDENOUVEAU = "INTERVENIR_DE_NOUVEAU";
    public static final String RECALCULERMETAINDICATEURS = "RECALCULER_METAINDICATEURS";
    public static final String COPIERTEXTE = "COPIER";
    public static final String COUPERTEXTE = "COUPER";
    public static final String COLLERTEXTE = "COLLER";
    public static final String MOTIF = "MOTIF";
    private static final String LOCALISATION = "LIEU";
    private static final String PORT = "PORT";
    public static final String DUREE = "DUREE";
    private static final String DUREEMILLIS = "DUREE_MILLI";
    private static final String PLATEFORME = "PLATEFORME";
    public static final String COMPILATION_PROJET = "COMPILATION_PROJET";
    public static final String TESTUNITAIRE = "TEST_UNITAIRE";
    public static final String ETAT = "ETAT";
    public static final String DEMARRERVIDEO = "VIDEO ON";
    public static final String DESACTIVERVIDEO = "VIDEO OFF";
    public static final String DEBUTRESTAURATION = "DEBUT_RESTAURATION";
    public static final String FINRESTAURATION = "FIN_RESTAURATION";
    private String type;
    private long temps;
    private Map<String, String> arguments;
    private String heure;

    private Evenement(String type) {
        this.type = type;
        this.arguments = new HashMap();
    }

    public Evenement(Element racine) {
        this.type = racine.getAttributeValue("K");
        this.temps = Long.parseLong(racine.getAttributeValue("T"));
        this.heure = racine.getAttributeValue("H");
        this.arguments = new HashMap();
        List<Element> elements = racine.getChildren();
        Iterator var3 = elements.iterator();

        while(var3.hasNext()) {
            Element element = (Element)var3.next();
            this.arguments.put(element.getName(), element.getText());
        }

    }

    public static Evenement getEvenementDemarrerVideo(String tuteur) {
        Evenement evenement = new Evenement("VIDEO ON");
        evenement.arguments.put("TUTEUR", tuteur);
        return evenement;
    }

    public static Evenement getEvenementConnection(String lieu, String plateforme) {
        Evenement evenement = new Evenement("CONNECTION");
        return evenement;
    }

    public static Evenement getEvenementTestUnitaire(String etat) {
        Evenement evenement = new Evenement("TEST_UNITAIRE");
        evenement.arguments.put("ETAT", etat);
        return evenement;
    }

    public static Evenement getEvenementDeconnection(String duree, String millis) {
        Evenement evenement = new Evenement("DECONNECTION");
        evenement.arguments.put("DUREE", duree);
        evenement.arguments.put("DUREE_MILLI", millis);
        return evenement;
    }

    public static Evenement getEvenementCopierTexte(String texte) {
        Evenement evenement = new Evenement("COPIER");
        evenement.arguments.put("T", texte.replace("\\n", "$€n€$").replace("\\t", "$€t€$").replace("\n", "\\n").replace("\t", "\\t"));
        return evenement;
    }

    public static Evenement getEvenementCollerTexte(String texte) {
        Evenement evenement = new Evenement("COLLER");
        evenement.arguments.put("T", texte.replace("\\n", "$€n€$").replace("\\t", "$€t€$").replace("\n", "\\n").replace("\t", "\\t"));
        return evenement;
    }

    public static Evenement getEvenementCouperTexte() {
        Evenement evenement = new Evenement("COUPER");
        return evenement;
    }

    public static Evenement getEvenementMessageTuteurVersEtudiant(String session, String nomEtudiant, Long heure, String nomFichier, int posDeb, int posFin, String message, String enseignant, String indicateurs) {
        Evenement evenement = new Evenement("MT");
        evenement.arguments.put("MESSAGE", message);
        evenement.arguments.put("ETUDIANT", nomEtudiant);
        evenement.arguments.put("SESSION", session);
        evenement.arguments.put("ENSEIGNANT", enseignant);
        evenement.arguments.put("HEUREFICHIER", (new Long(heure)).toString());
        evenement.arguments.put("F", nomFichier);
        evenement.arguments.put("POSDEB", (new Integer(posDeb)).toString());
        evenement.arguments.put("POSFIN", (new Integer(posFin)).toString());
        evenement.arguments.put("INDICATEURS", indicateurs);
        return evenement;
    }

    public static Evenement getEvenementAnnotation(String session, String nomEtudiant, Long heure, String nomFichier, int posDeb, int posFin, String message) {
        Evenement evenement = new Evenement("ANNOTATION");
        evenement.arguments.put("MESSAGE", message);
        evenement.arguments.put("ETUDIANT", nomEtudiant);
        evenement.arguments.put("SESSION", session);
        evenement.arguments.put("HEUREFICHIER", (new Long(heure)).toString());
        evenement.arguments.put("F", nomFichier);
        evenement.arguments.put("POSDEB", (new Integer(posDeb)).toString());
        evenement.arguments.put("POSFIN", (new Integer(posFin)).toString());
        return evenement;
    }

    public static Evenement getEvenementSelectionErreur(String projet, String nomFichier, int numLigne, String message) {
        Evenement evenement = new Evenement("SE");
        evenement.arguments.put("P", projet);
        evenement.arguments.put("F", nomFichier);
        evenement.arguments.put("L", (new Integer(numLigne)).toString());
        evenement.arguments.put("M", message);
        return evenement;
    }

    public static Evenement getEvenementFin() {
        Evenement evenement = new Evenement("FIN");
        return evenement;
    }

    public static Evenement getEvenementExecution(String projet, String sortie) {
        Evenement evenement = new Evenement("E");
        evenement.arguments.put("P", projet);
        evenement.arguments.put("S", sortie.replace("\\n", "$€n€$").replace("\\t", "$€t€$").replace("\n", "\\n").replace("\t", "\\t"));
        return evenement;
    }

    public static Evenement getEvenementAjoutProjet(String projet, String type, Boolean b) {
        Evenement evenement = new Evenement("AP");
        evenement.arguments.put("P", projet);
        evenement.arguments.put("TP", type);
        evenement.arguments.put("COMPILATION_PROJET", b.toString());
        return evenement;
    }

    public static Evenement getEvenementAjoutFichier(String projet, String fichier, String type) {
        Evenement evenement = new Evenement("AF");
        evenement.arguments.put("P", projet);
        evenement.arguments.put("F", fichier);
        evenement.arguments.put("TF", type);
        return evenement;
    }

    public static Evenement getEvenementSuppressionProjet(String projet) {
        Evenement evenement = new Evenement("SP");
        evenement.arguments.put("P", projet);
        return evenement;
    }

    public static Evenement getEvenementSuppressionFichier(String projet, String fichier, String type) {
        Evenement evenement = new Evenement("SF");
        evenement.arguments.put("P", projet);
        evenement.arguments.put("F", fichier);
        evenement.arguments.put("TF", type);
        return evenement;
    }

    public static Evenement getEvenementInsertionTexte(String projet, String fichier, String texte, int position) {
        Evenement evenement = new Evenement("IT");
        evenement.arguments.put("P", projet);
        evenement.arguments.put("F", fichier);
        evenement.arguments.put("T", texte.replace("\\n", "$€n€$").replace("\\t", "$€t€$").replace("\n", "\\n").replace("\t", "\\t"));
        evenement.arguments.put("N", String.valueOf(position));
        return evenement;
    }

    public static Evenement getEvenementSuppressionTexte(String projet, String fichier, int debut, int fin, String texteEnleve) {
        Evenement evenement = new Evenement("ST");
        evenement.arguments.put("P", projet);
        evenement.arguments.put("F", fichier);
        evenement.arguments.put("A", String.valueOf(debut));
        evenement.arguments.put("Z", String.valueOf(fin));
        evenement.arguments.put("D", texteEnleve);
        return evenement;
    }

    public String getType() {
        return this.type;
    }

    public long getTemps() {
        return this.temps;
    }

    public String get(String cle) {
        return (String)this.arguments.get(cle);
    }

    private void writeObject(ObjectOutputStream out) throws Exception {
        out.writeObject(this.toElement());
    }

    private void readObject(ObjectInputStream in) {
        Object oracine = null;

        try {
            oracine = in.readObject();
            if (oracine == null) {
                return;
            }

            Element racine = (Element)oracine;
            this.type = racine.getAttributeValue("K");
            this.temps = Long.parseLong(racine.getAttributeValue("T"));
            this.heure = racine.getAttributeValue("H");
            this.arguments = new HashMap();
            List<Element> elements = racine.getChildren();
            Iterator var5 = elements.iterator();

            while(var5.hasNext()) {
                Element element = (Element)var5.next();
                this.arguments.put(element.getName(), element.getText());
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        } catch (ClassNotFoundException var8) {
            var8.printStackTrace();
        } catch (ClassCastException var9) {
            var9.printStackTrace();
        }

    }

    public Element toElement() {
        Element racine = new Element("E");
        racine.setAttribute("K", this.type);
        racine.setAttribute("T", String.valueOf(this.temps));
        racine.setAttribute("H", String.valueOf(this.heure));
        Iterator var2 = this.arguments.keySet().iterator();

        while(var2.hasNext()) {
            String cle = (String)var2.next();
            racine.addContent((new Element(cle)).setText((String)this.arguments.get(cle)));
        }

        return racine;
    }

    public String toString() {
        return this.getType().equals("ST") ? "Evt " + this.getType() + "/" + this.get("D") + "/, heure : " + this.getTemps() + "/, temps : " + this.getTemps() : "Evt " + this.getType() + "/" + this.get("T") + "/, heure : " + this.getHeure() + "/, temps : " + this.getTemps();
    }

    public void setTemps(long timeInMillis) {
        this.temps = timeInMillis;
    }

    public void setHeure(String format) {
        this.heure = format;
    }

    public static Evenement getEvenementCompilationManuelle(String projet, String sortie) {
        Evenement evenement;
        if (sortie.equals("")) {
            evenement = new Evenement("CM");
        } else {
            evenement = new Evenement("CME");
        }

        evenement.arguments.put("P", projet);
        evenement.arguments.put("S", sortie);
        return evenement;
    }

    public static Evenement getEvenementCompilationAutomatique(String projet, String sortie) {
        Evenement evenement;
        if (sortie.equals("")) {
            evenement = new Evenement("CA");
        } else {
            evenement = new Evenement("CAE");
        }

        evenement.arguments.put("P", projet);
        evenement.arguments.put("S", sortie);
        return evenement;
    }

    public static Evenement getEvenementMessageTuteurVersEtudiant(String argument, String session, String tuteur) {
        Evenement evenement = new Evenement("MT");
        evenement.arguments.put("MESSAGE", argument);
        evenement.arguments.put("SESSION", session);
        evenement.arguments.put("TUTEUR", tuteur);
        return evenement;
    }

    public static Evenement getEvenementAppelAuSecours(String motifAppel, String etu) {
        Evenement evenement = new Evenement("HELP");
        evenement.arguments.put("MOTIF", motifAppel);
        evenement.arguments.put("ETUDIANT", etu);
        return evenement;
    }

    public static Evenement getEvenementInitierCauserie(String session, String etu, String enseignant, String nomDuFichier, String indicateurs) {
        Evenement evenement = new Evenement("INITIERCAUSERIE");
        evenement.arguments.put("ETUDIANT", etu);
        evenement.arguments.put("SESSION", session);
        evenement.arguments.put("ENSEIGNANT", enseignant);
        evenement.arguments.put("ENREGISTREMENT", nomDuFichier);
        evenement.arguments.put("INDICATEURS", indicateurs);
        return evenement;
    }

    public static Evenement getEvenementTerminerCauserie(String session, String etu, String enseignant, String nomDuFichierCauserie) {
        Evenement evenement = new Evenement("TERMINERCAUSERIE");
        evenement.arguments.put("ETUDIANT", etu);
        evenement.arguments.put("SESSION", session);
        evenement.arguments.put("ENSEIGNANT", enseignant);
        evenement.arguments.put("ENREGISTREMENT", nomDuFichierCauserie);
        return evenement;
    }

    public static Evenement getEvenementSelectionQuestion(int numQuestion, String projet) {
        Evenement evenement = getEvenementSelectionQuestion(numQuestion);
        evenement.arguments.put("P", projet);
        return evenement;
    }

    public static Evenement getEvenementSelectionQuestion(int numQuestion) {
        Evenement evenement = new Evenement("SQ");
        evenement.arguments.put(NUMEROQUESTION, String.valueOf(numQuestion).toString());
        return evenement;
    }

    public static Evenement getEvenementNavigationQuestion(int questionPrecedente, int questionCourante) {
        Evenement evenement = new Evenement("NQ");
        evenement.arguments.put(QUESTIONAVANT, String.valueOf(questionPrecedente).toString());
        evenement.arguments.put(QUESTIONAPRES, String.valueOf(questionCourante).toString());
        return evenement;
    }

    public static Evenement getEvenementIntervention(String etu, String ens, String Message) {
        Evenement evenement = new Evenement("INTERVENTION");
        evenement.arguments.put("ETUDIANT", etu);
        evenement.arguments.put("ENSEIGNANT", ens);
        evenement.arguments.put("MESSAGE", Message);
        return evenement;
    }

    public static Evenement getEvenementConfirmerIntention(String etu, String ens, String Message) {
        Evenement evenement = new Evenement("CONFIRMERINTENTION");
        evenement.arguments.put("ETUDIANT", etu);
        evenement.arguments.put("ENSEIGNANT", ens);
        evenement.arguments.put("MESSAGE", Message);
        return evenement;
    }

    public static Evenement getEvenementConfirmerIntervention(String session, String etu, String enseignant, String typeI, String contenu, String ind) {
        Evenement evenement = new Evenement("CONFIRMER_INTERVENTION");
        evenement.arguments.put("ETUDIANT", etu);
        evenement.arguments.put("SESSION", session);
        evenement.arguments.put("ENSEIGNANT", enseignant);
        evenement.arguments.put("TYPE_INTERVENTION", typeI);
        evenement.arguments.put("CONTENU_INTERVENTION", contenu);
        evenement.arguments.put("INDICATEURS_SELECTIONNES", ind);
        return evenement;
    }

    public static Evenement getEvenementConfirmerInterventionPourUTL(String time, String hour, String session, String etu, String enseignant, String typeI, String contenu, String ind, String tF) {
        Evenement evenement = new Evenement("CONFIRMER_INTERVENTION");
        evenement.setTemps(Long.parseLong(time));
        evenement.setHeure(hour);
        evenement.arguments.put("ETUDIANT", etu);
        evenement.arguments.put("SESSION", session);
        evenement.arguments.put("ENSEIGNANT", enseignant);
        evenement.arguments.put("TYPE_INTERVENTION", typeI);
        evenement.arguments.put("CONTENU_INTERVENTION", contenu);
        evenement.arguments.put("INDICATEURS_SELECTIONNES", ind);
        evenement.arguments.put("TIME_FEEDBACK", tF);
        return evenement;
    }

    public static Evenement getEvenementModifierEtatIntervention(String session, String etu, String enseignant, String nouvelEtat, String tempsIntervention, String annotation) {
        Evenement evenement = new Evenement("MODIFIER_ETAT_INTERVENTION");
        evenement.arguments.put("ETUDIANT", etu);
        evenement.arguments.put("SESSION", session);
        evenement.arguments.put("ENSEIGNANT", enseignant);
        evenement.arguments.put("ETAT_INTERVENTION", nouvelEtat);
        evenement.arguments.put("TIME_INTERVENTION", tempsIntervention);
        evenement.arguments.put("MESSAGE", annotation);
        return evenement;
    }

    public static Evenement getEvenementAnnoterIntervention(String session, String etu, String enseignant, String tempsIntervention, String annotation) {
        Evenement evenement = new Evenement("ANNOTER_INTERVENTION");
        evenement.arguments.put("ETUDIANT", etu);
        evenement.arguments.put("SESSION", session);
        evenement.arguments.put("ENSEIGNANT", enseignant);
        evenement.arguments.put("TIME_INTERVENTION", tempsIntervention);
        evenement.arguments.put("MESSAGE", annotation);
        return evenement;
    }

    public static Evenement getEvenementReIntervention(String session, String etu, String enseignant, String typeI, String tempsIntervention, String contenu, String ind) {
        Evenement evenement = new Evenement("INTERVENIR_DE_NOUVEAU");
        evenement.arguments.put("ETUDIANT", etu);
        evenement.arguments.put("SESSION", session);
        evenement.arguments.put("ENSEIGNANT", enseignant);
        evenement.arguments.put("TYPE_INTERVENTION", typeI);
        evenement.arguments.put("TIME_INTERVENTION", tempsIntervention);
        evenement.arguments.put("CONTENU_INTERVENTION", contenu);
        evenement.arguments.put("INDICATEURS_SELECTIONNES", ind);
        return evenement;
    }

    public static Evenement getEvenementRecalulMetaIndicateurs(String session, String etu, String enseignant, String tempsIntervention) {
        Evenement evenement = new Evenement("RECALCULER_METAINDICATEURS");
        evenement.arguments.put("ETUDIANT", etu);
        evenement.arguments.put("SESSION", session);
        evenement.arguments.put("ENSEIGNANT", enseignant);
        evenement.arguments.put("TIME_INTERVENTION", tempsIntervention);
        return evenement;
    }

    public String getHeure() {
        return this.heure;
    }

    public int compareTo(Object o) {
        return ((Evenement)o).getTemps() < this.getTemps() ? 1 : -1;
    }

    public Map<String, String> getArguments() {
        return this.arguments;
    }

    public static Evenement getEvenementDesactiverVideo(String tuteur) {
        Evenement evenement = new Evenement("VIDEO OFF");
        evenement.arguments.put("TUTEUR", tuteur);
        return evenement;
    }
}
