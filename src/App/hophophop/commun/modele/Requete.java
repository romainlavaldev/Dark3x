//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import hophophop.commun.modele.H3Commun.ACTEUR;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jdom.Element;

public class Requete implements Serializable {
    private static final long serialVersionUID = 4498863181747357292L;
    private static final String RACINE = "RACINE";
    private static final String TYPE = "TYPE";
    public static final String ARRETEROBSERVER = "r-001";
    public static final String AUSECOURS = "r-002";
    public static final String CHANGEMENTMOTDEPASSEPREMIERECONNEXION = "r-003";
    public static final String CHANGEMENTMOTDEPASSEVOLONTAIRE = "r-004";
    public static final String CHANGEMENTPASSWORD = "r-005";
    public static final String CHANGERSESSION = "r-006";
    public static final String CONFIRMATION = "r-007";
    public static final String CONNEXION = "r-008";
    public static final String DECONNECTEETUDIANT = "r-009";
    public static final String DECONNECTETOUTETUDIANT = "r-010";
    public static final String DEMANDECODESOURCE = "r-011";
    public static final String ENONCE = "r-012";
    public static final String ENSEIGNANT = "r-013";
    public static final String ENVOYERMESSAGEETUDIANT = "r-014";
    public static final String ENVOYERNOMFICHIERS = "r-015";
    public static final String ENVOYERTRACELOCALE = "r-016";
    public static final String ETUDIANT = "r-017";
    public static final String ETUDIANTS = "r-018";
    public static final String FICHIER = "r-019";
    public static final String GENERERSOURCEETUDIANT = "r-020";
    public static final String GETDATAS = "r-021";
    public static final String GETETUDIANTDUGROUPE = "r-022";
    public static final String GETINFOSSESSIONS = "r-023";
    public static final String GETLISTEETUDIANTSANSGROUPE = "r-024";
    public static final String GETLISTEGROUPES = "r-025";
    public static final String GETLISTEGROUPESPOURMODIFIERETUDIANT = "r-0251";
    public static final String GETLISTEGROUPESPOURMODIFIERGROUPE = "r-0252";
    public static final String GETLISTESESSIONS = "r-026";
    public static final String GETLISTESESSIONSPOURMODIFIERSESSIONS = "r-0261";
    public static final String GETLISTETUTEURS = "r-027";
    public static final String GETOBSERVERLISTETUTEURS = "r-027-OB";
    public static final String GETLISTEUTILISATEURSCONNECTES = "r-028";
    public static final String ID = "r-029";
    public static final String IDENTIFIER = "r-030";
    public static final String IMPORTER = "r-031";
    public static final String IP = "r-032";
    public static final String LANGAGE = "r-033";
    public static final String LOCALE = "r-034";
    public static final String MESSAGE = "r-035";
    public static final String MOTIF = "r-036";
    public static final String NOM = "r-037";
    public static final String NOUVEAUNOM = "r-0371";
    public static final String OBSERVER = "r-038";
    public static final String OKVASYCAUSE = "r-039";
    public static final String PASSWORD = "r-040";
    public static final String PLATEFORME = "r-041";
    public static final String PRENOM = "r-042";
    public static final String PROJET = "r-043";
    public static final String SALLES = "r-044";
    public static final String SESSION = "r-045";
    public static final String RENOMMERSESSION = "r-0451";
    public static final String SESSION_ID = "r-046";
    public static final String SESSIONS = "r-047";
    public static final String SIMULERSESSION = "r-048";
    public static final String TIMESTAMP = "r-049";
    public static final String TRACE = "r-050";
    public static final String USERNAME = "r-051";
    public static final String VITESSE = "r-052";
    public static final String RESETPASSWORD = "r-053";
    public static final String RESETPASSWORDGROUPE = "r-054";
    public static final String GETHORAIRESSESSION = "r-055";
    public static final String GROUPE = "r-056";
    public static final String RENOMMERGROUPE = "r-057";
    public static final String ACTEUR = "r-058";
    public static final String DEMARRERVIDEO = "r-059-1";
    public static final String DEMARRERVIDEOINDIVIDUELLE = "r-059-2";
    public static final String DEMARRERVIDEOSESSION = "r-059-3";
    public static final String DESACTIVERVIDEO = "r-059-4";
    public static final String TUTEUR = "r-060";
    public static final String PASDETRACE = "r-062";
    public static final String CREERPROMOTION = "r-063";
    public static final String TP = "r-064";
    public static final String TD = "r-065";
    public static final String RENOMMERPROMOTION = "r-066";
    public static final String SEMESTRE = "r-067";
    public static final String RESTAURER = "r-068";
    public static final String DATERESTAURATION = "r-069";
    public static final String VERSIONJAVA = "r-070";
    public static final String VERSIONCLIENT = "r-071";
    public static final String GETTRACESESSION = "r-074";
    public static final String QUESTION = "r-075";
    private String type;
    private Map<String, String> arguments;

    public static Requete getRequeteDroidDemandeSalles() {
        return new Requete("r-044");
    }

    public static Requete getRequeteDroidDemandeSessions() {
        return new Requete("r-047");
    }

    public static Requete getRequeteDroidEtudiantsConnectes(String nomDeLaSession) {
        Requete requete = new Requete("r-018");
        requete.addArgument("r-045", nomDeLaSession);
        return requete;
    }

    public static Requete getRequeteChangerMotDePasse(String username, String olpPassSha, String newPassSha, boolean premiereConnexion) {
        Requete requete = new Requete("r-005");
        requete.addArgument("r-051", username);
        requete.addArgument("r-040", olpPassSha);
        requete.addArgument("r-035", newPassSha);
        if (premiereConnexion) {
            requete.addArgument("r-036", "r-003");
        } else {
            requete.addArgument("r-036", "r-004");
        }

        return requete;
    }

    public static Requete getRequeteResetMotDePasse(ACTEUR acteur, String username) {
        Requete requete = new Requete("r-053");
        requete.addArgument("r-051", username);
        requete.addArgument("r-058", acteur.toString());
        return requete;
    }

    public static Requete getRequeteResetMotDePasseGroupe(String nomDuGroupe) {
        Requete requete = new Requete("r-054");
        requete.addArgument("r-037", nomDuGroupe);
        return requete;
    }

    public static Requete getRequeteFichierSourceEtudiant(String laSession) {
        Requete requete = new Requete("r-020");
        requete.addArgument("r-045", laSession);
        return requete;
    }

    public static Requete getRequeteFichierSourceEtudiant(String laSession, String nomComplet) {
        Requete requete = getRequeteFichierSourceEtudiant(laSession);
        requete.addArgument("r-017", nomComplet);
        return requete;
    }

    public static Requete getRequeteDemandeSessions() {
        return new Requete("r-047");
    }

    public static Requete getRequeteImporterSession(String nomDeLaSession) {
        Requete requete = new Requete("r-031");
        requete.addArgument("r-045", nomDeLaSession);
        return requete;
    }

    public static Requete getRequeteObserverUtilisateur(String nomDeLaSession, String username) {
        Requete requete = new Requete("r-038");
        requete.addArgument("r-051", username);
        requete.addArgument("r-045", nomDeLaSession);
        return requete;
    }

    public static Requete getRequeteObserverUtilisateur(String nomDeLaSession, String username, String timeStamp) {
        Requete requete = getRequeteObserverUtilisateur(nomDeLaSession, username);
        requete.addArgument("r-049", timeStamp);
        return requete;
    }

    public static Requete getRequeteObserverUtilisateur(String nomDeLaSession, String username, String timeStamp, String question) {
        Requete requete = getRequeteObserverUtilisateur(nomDeLaSession, username, timeStamp);
        requete.addArgument("r-075", question);
        return requete;
    }

    public static Requete getRequeteEvenement(String nomDeLaSession, String username) {
        Requete requete = new Requete("r-038");
        requete.addArgument("r-051", username);
        requete.addArgument("r-045", nomDeLaSession);
        return requete;
    }

    public static Requete getRequeteEnvoyerTraceLocale(String username, String nomDeLaSession, String trace) {
        Requete requete = new Requete("r-016");
        requete.addArgument("r-051", username);
        requete.addArgument("r-045", nomDeLaSession);
        requete.addArgument("r-050", trace);
        return requete;
    }

    public static Requete getRequeteEnvoyerListeNomFichiers(String username, String nomDeLaSession) {
        Requete requete = new Requete("r-015");
        requete.addArgument("r-051", username);
        requete.addArgument("r-045", nomDeLaSession);
        return requete;
    }

    public static Requete getRequeteIdentifier(String username) {
        Requete requete = new Requete("r-030");
        requete.addArgument("r-051", username);
        return requete;
    }

    public static Requete getRequeteIdentifier(String username, String nomDeLaSession) {
        Requete requete = getRequeteIdentifier(username);
        requete.addArgument("r-045", nomDeLaSession);
        return requete;
    }

    public static Requete getRequeteIdentifier(String username, String nomDeLaSession, boolean simulation, String iP, String plateforme, String versionJava, String versionEtudiant, String dateRestauration, String aRestaurer) {
        Requete requete = getRequeteIdentifier(username, nomDeLaSession, simulation, iP, plateforme, versionJava, versionEtudiant);
        requete.addArgument("r-068", aRestaurer);
        requete.addArgument("r-069", dateRestauration);
        return requete;
    }

    public static Requete getRequeteIdentifier(String username, String nomDeLaSession, boolean simulation, String iP, String plateforme, String versionJava, String versionEtudiant) {
        Requete requete = getRequeteIdentifier(username, nomDeLaSession, iP, plateforme);
        requete.addArgument("r-070", versionJava);
        requete.addArgument("r-071", versionEtudiant);
        if (simulation) {
            requete.addArgument("r-034", "Oui");
        } else {
            requete.addArgument("r-034", "Non");
        }

        return requete;
    }

    public static Requete getRequeteIdentifier(String username, String nomDeLaSession, String iP, String plateforme) {
        Requete requete = getRequeteIdentifier(username, nomDeLaSession);
        requete.addArgument("r-032", iP);
        requete.addArgument("r-041", plateforme);
        return requete;
    }

    public static Requete getRequeteIdentifier(String username, String session, String iP, String plateforme, String javaVersion, String versionClient) {
        Requete requete = getRequeteIdentifier(username, session, iP, plateforme);
        requete.addArgument("r-070", javaVersion);
        requete.addArgument("r-071", versionClient);
        return requete;
    }

    public static Requete getRequeteIdentifier(String username, String nomDeLaSession, String langage, boolean b, String ip, String plateforme) {
        Requete requete = getRequeteIdentifier(username, nomDeLaSession, ip, plateforme);
        requete.addArgument("r-033", langage);
        if (b) {
            requete.addArgument("r-034", "Oui");
        } else {
            requete.addArgument("r-034", "Non");
        }

        return requete;
    }

    private Requete(String type) {
        this.type = type;
        this.arguments = new HashMap();
    }

    public String getType() {
        return this.type;
    }

    private void addArgument(String cle, String valeur) {
        this.arguments.put(cle, valeur);
    }

    public String getArgument(String cle) {
        return (String)this.arguments.get(cle);
    }

    private void writeObject(ObjectOutputStream out) throws Exception {
        Element racine = new Element("RACINE");
        racine.setAttribute("TYPE", this.type);
        Iterator var3 = this.arguments.keySet().iterator();

        while(var3.hasNext()) {
            String cle = (String)var3.next();
            racine.addContent((new Element(cle)).setText((String)this.arguments.get(cle)));
        }

        out.writeObject(racine);
    }

    private void readObject(ObjectInputStream in) throws Exception {
        Element racine = (Element)in.readObject();
        this.type = racine.getAttributeValue("TYPE");
        if (this.arguments == null) {
            this.arguments = new HashMap();
        }

        List<Element> elements = racine.getChildren();
        Iterator var4 = elements.iterator();

        while(var4.hasNext()) {
            Element element = (Element)var4.next();
            this.arguments.put(element.getName(), element.getText());
        }

    }

    public static Requete getRequeteArreterObserverEtudiant(String nomDeLaSession, String nomEtudiant) {
        Requete requete = new Requete("r-001");
        requete.addArgument("r-017", nomEtudiant);
        requete.addArgument("r-045", nomDeLaSession);
        return requete;
    }

    public static Requete getRequeteDemandeEnonce() {
        return new Requete("r-012");
    }

    public static Requete getRequeteSimuler(int vitesse) {
        Requete requete = new Requete("r-048");
        requete.addArgument("r-052", (new Integer(vitesse)).toString());
        return requete;
    }

    public static Requete getRequeteAppelAuSecours(String username, String nomDeLaSession, String motif) {
        Requete requete = new Requete("r-002");
        requete.addArgument("r-051", username);
        requete.addArgument("r-045", nomDeLaSession);
        requete.addArgument("r-036", motif);
        return requete;
    }

    public static Requete getRequeteOkVasYCause(String username, String nomDeLaSession, String enseignant) {
        Requete requete = new Requete("r-039");
        requete.addArgument("r-051", username);
        requete.addArgument("r-045", nomDeLaSession);
        requete.addArgument("r-013", enseignant);
        return requete;
    }

    public static Requete getRequeteDeconnectionEtudiant(String username, String session_nom, String confirmation) {
        Requete requete = new Requete("r-009");
        requete.addArgument("r-051", username);
        requete.addArgument("r-045", session_nom);
        requete.addArgument("r-007", confirmation);
        return requete;
    }

    public static Requete getRequeteDeconnectionToutLesEtudiants(String session_id) {
        Requete requete = new Requete("r-010");
        requete.addArgument("r-046", session_id);
        return requete;
    }

    public static Requete getRequeteDemandeCodeSource(String username, String nomDeLaSession, String projet, String fichier) {
        Requete requete = new Requete("r-011");
        requete.addArgument("r-051", username);
        requete.addArgument("r-045", nomDeLaSession);
        requete.addArgument("r-043", projet);
        requete.addArgument("r-019", fichier);
        return requete;
    }

    public static Requete getRequeteChangerDeSession(String username, String nomDeLaSession) {
        Requete requete = new Requete("r-006");
        requete.addArgument("r-051", username);
        requete.addArgument("r-045", nomDeLaSession);
        return requete;
    }

    public static Requete getRequeteConnexion(String username, String password, String versionClient) {
        Requete r = new Requete("r-008");
        r.addArgument("r-051", username);
        r.addArgument("r-040", password);
        r.addArgument("r-071", versionClient);
        return r;
    }

    public static Requete getRequeteConnexion(String username, String password, String ip, String plateforme, String versionJava, String versionClient) {
        Requete r = getRequeteConnexion(username, password, versionClient);
        r.addArgument("r-032", ip);
        r.addArgument("r-041", plateforme);
        r.addArgument("r-070", versionJava);
        return r;
    }

    public static Requete getRequeteAdminGetDatas() {
        return new Requete("r-021");
    }

    public static Requete getRequeteAdminEtudiantsDuGroupe(String nomDuGroupe) {
        Requete r = new Requete("r-022");
        r.addArgument("r-037", nomDuGroupe);
        return r;
    }

    public static Requete getRequeteAdminListeGroupes() {
        Requete r = new Requete("r-025");
        return r;
    }

    public static Requete getRequeteAdminListeGroupesPourModifierEtudiant() {
        Requete r = new Requete("r-0251");
        return r;
    }

    public static Requete getRequeteAdminListeGroupesPourModifierGroupe() {
        Requete r = new Requete("r-0252");
        return r;
    }

    public static Requete getRequeteListeEtudiantsSansGroupe() {
        Requete r = new Requete("r-024");
        return r;
    }

    public static Requete getRequeteAdminListeConnectes() {
        Requete r = new Requete("r-028");
        return r;
    }

    public static Requete getRequeteAdminListeTuteurs() {
        Requete r = new Requete("r-027");
        return r;
    }

    public static Requete getRequeteAdminObserverListeTuteurs() {
        Requete r = new Requete("r-027-OB");
        return r;
    }

    public static Requete getRequeteAdminListeSessions() {
        Requete r = new Requete("r-026");
        return r;
    }

    public static Requete getRequeteAdminListeSessionsPourModifierSession() {
        Requete r = new Requete("r-0261");
        return r;
    }

    public static Requete getRequeteAdminGetInfosSessions(String nomSession) {
        Requete r = new Requete("r-023");
        r.addArgument("r-045", nomSession);
        return r;
    }

    public static Requete getRequeteAdminGetHorairesSession(String sessionSelectionnee) {
        Requete r = new Requete("r-055");
        r.addArgument("r-045", sessionSelectionnee);
        return r;
    }

    public static Requete getRequeteRenommerSession(String nomSession, String nouveauNom) {
        Requete r = new Requete("r-0451");
        r.addArgument("r-045", nomSession);
        r.addArgument("r-0371", nouveauNom);
        return r;
    }

    public static Requete getRequeteRenommerGroupe(String nomGroupe, String nouveauNom) {
        Requete r = new Requete("r-057");
        r.addArgument("r-056", nomGroupe);
        r.addArgument("r-0371", nouveauNom);
        return r;
    }

    public static Requete getDemarrerVideo(String tuteur, String adresse) {
        Requete r = new Requete("r-059-1");
        r.addArgument("r-060", tuteur);
        r.addArgument("r-008", adresse);
        return r;
    }

    public static Requete getRequeteDemarrerVideo(String username, String session, String tuteur, String adresse) {
        Requete r = new Requete("r-059-2");
        r.addArgument("r-051", username);
        r.addArgument("r-045", session);
        r.addArgument("r-060", tuteur);
        r.addArgument("r-008", adresse);
        return r;
    }

    public static Requete getRequeteDemarrerVideo(String session, String tuteur, String adresse) {
        Requete r = new Requete("r-059-3");
        r.addArgument("r-045", session);
        r.addArgument("r-060", tuteur);
        r.addArgument("r-008", adresse);
        return r;
    }

    public static Requete getRequeteDesactiverVideo(String session, String tuteur) {
        Requete r = new Requete("r-059-4");
        r.addArgument("r-060", tuteur);
        r.addArgument("r-045", session);
        return r;
    }

    public static Requete getRequetePasDeTrace(String nomDeLaSession, String nomCompletTuteur) {
        Requete r = new Requete("r-062");
        r.addArgument("r-060", nomCompletTuteur);
        r.addArgument("r-045", nomDeLaSession);
        return r;
    }

    public static Requete getRequeteCreerPromotion(String txtNomPromo, String nbTD, String nbTP, String premierSemestre) {
        Requete r = new Requete("r-063");
        r.addArgument("r-037", txtNomPromo);
        r.addArgument("r-065", nbTD);
        r.addArgument("r-064", nbTP);
        r.addArgument("r-067", premierSemestre);
        return r;
    }

    public static Requete getRequeteRenommerPromotion(String ancienNom, String nouveauNom) {
        Requete r = new Requete("r-066");
        r.addArgument("r-037", ancienNom);
        r.addArgument("r-0371", nouveauNom);
        return r;
    }

    public static long getSerialversionuid() {
        return 4498863181747357292L;
    }

    public static Requete getRequeteAdminGetTraceSession(String nomSession) {
        Requete r = new Requete("r-074");
        r.addArgument("r-037", nomSession);
        return r;
    }
}
