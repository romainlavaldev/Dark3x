//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdom.Element;

public class Message implements Serializable {
    private static final long serialVersionUID = 6584519795299102852L;
    public static final String AJOUTERETUDIANTS = "m-001";
    public static final String AJOUTERGROUPE = "m-002";
    public static final String AJOUTERSESSION = "m-003";
    public static final String AJOUTERTUTEUR = "m-004";
    public static final String AUSECOURS = "m-005";
    public static final String CHANGEMENTPASSWORD = "m-006";
    public static final String CHANGERSESSION = "m-007";
    public static final String CHARGERINDICATEURS = "m-008";
    public static final String CHARGERINTERVENTIONS = "m-009";
    public static final String CODESOURCE = "m-010";
    public static final String COMPILATIONS = "m-011";
    public static final String CONFIRMATION = "m-012";
    public static final String CONNNEXIONSBD = "m-013";
    public static final String CREER_OBSERVATION = "m-014";
    public static final String CREERWORKSPACE = "m-015";
    public static final String DECONNEXIONENSEIGNANT = "m-016";
    public static final String DECONNEXIONETUDIANT = "m-017";
    public static final String DEMANDECHANGEMENTPASSWORD = "m-018";
    public static final String DERNIEREVENEMENT = "m-019";
    public static final String DOCUMENT = "m-020";
    public static final String ECHEC = "m-021";
    public static final String ENONCE = "m-022";
    public static final String ETUDIANT = "m-023";
    public static final String ETUDIANTCONNECTE = "m-024";
    public static final String ETUDIANTSCONNECTES = "m-025";
    public static final String UTILISATEURSSCONNECTES = "m-025-1";
    public static final String ETUDIANTSINSCRITS = "m-026";
    public static final String ETUDIANTSNONCONNECTES = "m-027";
    public static final String EVENEMENT = "m-028";
    public static final String EXECUTIONS = "m-029";
    public static final String FEEDBACKTUTOR = "m-030";
    public static final String FERMERFENETRE = "m-031";
    public static final String FICHIER = "m-032";
    public static final String GROUPE = "m-033";
    public static final String INDICATEUR = "m-034";
    public static final String INFOSESSION = "m-035";
    public static final String IPETUDIANT = "m-036";
    public static final String ISCOMPILATIONPROJET = "m-037";
    public static final String LANGAGE = "m-038";
    public static final String LISTEDESFICHIERS = "m-039";
    public static final String LISTEENONCE = "m-040";
    public static final String LISTENOMFICHIERS = "m-041";
    public static final String LISTESESSIONS = "m-042";
    public static final String LISTESESSIONSCONNECTEES = "m-043";
    public static final String LISTESESSIONSPOSSIBLES = "m-044";
    public static final String LISTESETUDIANTS = "m-045";
    public static final String MESSAGEUTILISATEUR = "m-046";
    public static final String MESSAGEHUIVERSENSEIGNANT = "m-047";
    public static final String MOTIF = "m-048";
    public static final String NOM = "m-049";
    public static final String NOM_FICHIER = "m-050";
    public static final String NOUVELENSEIGNANT = "m-051";
    public static final String NOUVELETUDIANT = "m-052";
    public static final String OK = "m-053";
    public static final String OKVASYCAUSE = "m-054";
    public static final String PASSWORD = "m-055";
    public static final String PRENOM = "m-056";
    public static final String PROJET = "m-057";
    public static final String READONLY = "m-058";
    public static final String RECALCULERINDICATEURS = "m-059";
    public static final String RETOUR = "m-060";
    public static final String RETOURAJOUTERETUDIANTS = "m-061";
    public static final String RETOURAJOUTERTUTEUR = "m-062";
    public static final String RETOURDATAS_AJ = "m-063";
    public static final String RETOURETUDIANTDUGROUPE = "m-064";
    public static final String RETOURINFOSSESSIONS = "m-065";
    public static final String RETOURLISTEETUDIANTSANSGROUPE = "m-066";
    public static final String RETOURLISTEGROUPES = "m-067";
    public static final String RETOURLISTEGROUPESPOURMODIFIERETUDIANT = "m-067-1";
    public static final String RETOURLISTEGROUPESPOURMODIFIERGROUPE = "m-067-2";
    public static final String RETOURLISTENOMSSESSIONS = "m-068";
    public static final String RETOURLISTESESSIONSPOURMODIFIERSESSION = "m-068-1";
    public static final String RETOURLISTETUTEURS = "m-069";
    public static final String RETOURLISTEUTILISATEURSCONNECTES = "m-070";
    public static final String SALLES = "m-071";
    public static final String SEANCESETUDIANT = "m-072";
    public static final String SESSION = "m-073";
    public static final String SESSIONOUVERTE = "m-073-1";
    public static final String SESSIONFERMEE = "m-073-2";
    public static final String SESSIONARCHIVEE = "m-073-3";
    public static final String SIMULERFRAPPE = "m-074";
    public static final String SOURCES = "m-075";
    public static final String SOURCESETUDIANT = "m-076";
    public static final String TEMPSCUMULE = "m-077";
    public static final String TEXTE = "m-078";
    public static final String TUTEUR = "m-079";
    public static final String USERNAME = "m-080";
    public static final String UTILISATEUR = "m-081";
    public static final String UTILISATEURINCONNU = "m-082";
    public static final String WORKSPACE = "m-083";
    public static final String TESTSFONCTIONS = "m-084";
    public static final String RETOURRESETPASSWORD = "m-085";
    public static final String AJOUTERETUDIANTAUGROUPE = "m-086";
    public static final String RETOURHORAIRESSESSION = "m-087";
    public static final String HEUREDEBUT = "m-087-1";
    public static final String HEUREFIN = "m-087-2";
    public static final String NOUVEAUNOM = "m-088";
    public static final String RESULTAT = "m-089";
    public static final String RENOMMERSESSION = "m-090";
    public static final String QUESTION = "m-091";
    public static final String SUPPRIMERETUDIANTDUGROUPE = "m-092";
    public static final String LOGSERVEUR = "m-093";
    public static final String NBLIGNE = "m-093-1";
    public static final String RECHARGERDATAS = "m-094";
    public static final String CREATIONSESSIONPARLOT = "m-095";
    public static final String ANNEE = "m-095-1";
    public static final String PROMOTION = "m-095-2";
    public static final String SEMESTRE = "m-095-3";
    public static final String MATIERE = "m-095-4";
    public static final String INTITULE = "m-095-5";
    public static final String SCENARIO = "m-095-6";
    public static final String COMPILATION = "m-095-7";
    public static final String FERMEE = "m-095-8";
    public static final String NBGROUPE = "m-095-9";
    public static final String ACTIVITE = "m-095-10";
    public static final String JACODEP = "m-095-11";
    public static final String OBSERVERLISTENOMSGROUPE = "m-096";
    public static final String OBSERVERLISTENOMSPROMOTION = "m-097";
    public static final String GROUPESAFFECTES = "m-098";
    public static final String GROUPESNONAFFECTES = "m-099";
    public static final String AFFECTERGROUPESPROMOTION = "m-100";
    public static final String SUPPRIMERAFFECTATIONGROUPE = "m-101";
    public static final String OBSERVERLISTENOMSSESSION = "m-102";
    public static final String OBSERVERLISTESESSION = "m-102-1";
    public static final String OBSERVERTUTEURSESSION = "m-103";
    public static final String AJOUTERTUTEURSESSION = "m-104";
    public static final String SUPPRIMERTUTEURSESSION = "m-105";
    public static final String LECTURESEULE = "m-106";
    public static final String PREMIEREVENEMENT = "m-107";
    public static final String OBSERVERLISTETUTEURS = "m-108";
    public static final String TESTUNITAIRE = "m-109-1";
    public static final String LESCLASSESDETESTSUNITAIRES = "m-109-2";
    public static final String QUESTIONPROJET = "m-110";
    public static final String PASDETRACE = "m-111";
    public static final String NUMERO = "m-112";
    public static final String NONAFFECTES = "m-113";
    public static final String AFFECTES = "m-114";
    public static final String GROUPESAFFECTESNONAFFECTES = "m-115";
    public static final String INFORMATION = "m-116";
    public static final String SELECTIONNEPROMOTION = "m-117";
    public static final String MISEAJOUR = "m-118";
    public static final String LANCEUR = "m-119";
    public static final String VERSIONSERVEUR = "m-120";
    public static final String MAINTENANCE = "m-121";
    public static final String DECONNEXIONCLIENT = "m-122";
    public static final String NOMBRECONNEXIONS = "m-123";
    public static final String SESSIONGROUPEE = "m-124";
    public static final String DOSSIER_TRACE = "m-125";
    public static final String FICHIER_TRACE = "m-126";
    public static final String ARCHIVERSESSION = "m-127";
    public static final String RESTAURERSESSION = "m-128";
    public static final String DOSSIER_LOGS = "m-129";
    public static final String ADMIN = "m-130";
    private String type;
    private Map<String, Object> arguments;

    private Message(String type) {
        this.type = type;
        this.arguments = new HashMap();
    }

    public Map<String, Object> getArguments() {
        return this.arguments;
    }

    public String getType() {
        return this.type;
    }

    private void addArgument(String cle, Object valeur) {
        this.arguments.put(cle, valeur);
    }

    public Object getArgument(String cle) {
        return this.arguments.get(cle);
    }

    public static Message getMessageListeSalles(HashMap<String, Salle> lesSalles) {
        Message m = new Message("m-071");
        m.addArgument("m-071", lesSalles);
        return m;
    }

    public static Message getMessageSimulerFrappe() {
        Message m = new Message("m-074");
        return m;
    }

    public static int tailleDuMessage(Object m) {
        try {
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            ObjectOutputStream objectoutputstream = new ObjectOutputStream(bytearrayoutputstream);
            objectoutputstream.writeObject(m);
            byte[] abyte0 = bytearrayoutputstream.toByteArray();
            return abyte0.length;
        } catch (Exception var4) {
            var4.printStackTrace();
            return -1;
        }
    }

    public static Message getMessageConnexionNouvelEtudiant(Etudiant etudiant) {
        Message m = new Message("m-052");
        m.addArgument("m-023", etudiant);
        return m;
    }

    public static Message getMessageDeconnexionEtudiant(Etudiant etudiant, String nomSession) {
        Message m = new Message("m-017");
        m.addArgument("m-023", etudiant);
        m.addArgument("m-073", nomSession);
        return m;
    }

    public static Message getMessageConnexionNouvelEnseignant(String nomEnseignant, String nomSession) {
        Message m = new Message("m-051");
        m.addArgument("m-051", nomEnseignant);
        m.addArgument("m-073", nomSession);
        return m;
    }

    public static Message getMessageDeconnexionEnseignant(String nomEnseignant, String nomSession) {
        Message m = new Message("m-016");
        m.addArgument("m-016", nomEnseignant);
        m.addArgument("m-073", nomSession);
        return m;
    }

    public static Message getMessageListeEnonce(List<String> enonce) {
        Message m = new Message("m-040");
        m.addArgument("m-022", enonce);
        return m;
    }

    public static Message getMessageCreerObservation(String session, String nomEtudiant, String username, String nomFichier, String trace, Boolean connecte, String ip) {
        Message m = new Message("m-014");
        m.addArgument("m-073", session);
        m.addArgument("m-023", nomEtudiant);
        m.addArgument("m-080", nomEtudiant);
        m.addArgument("m-050", nomFichier);
        m.addArgument("m-020", trace);
        m.addArgument("m-024", connecte.toString());
        m.addArgument("m-036", ip);
        return m;
    }

    public static Message getMessageChargerIndicateurs(String session, String etudiant, String trace) {
        Message m = new Message("m-008");
        m.addArgument("m-073", session);
        m.addArgument("m-081", etudiant);
        m.addArgument("m-034", trace);
        return m;
    }

    public static Message getMessageChargerInterventions(String session, String enseignant, String trace) {
        Message m = new Message("m-009");
        m.addArgument("m-073", session);
        m.addArgument("m-081", enseignant);
        m.addArgument("m-020", trace);
        return m;
    }

    public static Message getMessageEvenement(String session, String utilisateur, Evenement evenement) {
        Message m = new Message("m-028");
        m.addArgument("m-073", session);
        m.addArgument("m-081", utilisateur);
        m.addArgument("m-028", evenement);
        return m;
    }

    public static Message getMessageFermerFenetre(String string, String confirmation) {
        Message m = new Message("m-031");
        m.addArgument("m-078", string);
        m.addArgument("m-012", confirmation);
        return m;
    }

    public static Message getMessageMessageEtudiant(String leMessage, String expediteur) {
        Message m = new Message("m-046");
        m.addArgument("m-078", leMessage);
        m.addArgument("m-079", expediteur);
        return m;
    }

    public static Message getMessageAuSecours(String nom, String session, String motif) {
        Message m = new Message("m-005");
        m.addArgument("m-073", session);
        m.addArgument("m-081", nom);
        m.addArgument("m-048", motif);
        return m;
    }

    public static Object getMessageOkVasYCause(String nom, String session) {
        Message m = new Message("m-054");
        m.addArgument("m-073", session);
        m.addArgument("m-081", nom);
        return m;
    }

    public static Message getMessageHuiVersEnseignants(String session, String nomEtu, Element ind) {
        Message m = new Message("m-047");
        m.addArgument("m-073", session);
        m.addArgument("m-081", nomEtu);
        m.addArgument("m-034", ind);
        return m;
    }

    public static Message getMessageFeedbackTutor(String session, String nomEns, Element ind) {
        Message m = new Message("m-030");
        m.addArgument("m-073", session);
        m.addArgument("m-081", nomEns);
        m.addArgument("m-034", ind);
        return m;
    }

    public static Message getMessageRecalculerIndicateurs(String session, String nomEtu) {
        Message m = new Message("m-059");
        m.addArgument("m-073", session);
        m.addArgument("m-081", nomEtu);
        return m;
    }

    public static Message getMessageListeFichiers(List<String> listeDesFichiers) {
        Message m = new Message("m-039");
        m.addArgument("m-041", listeDesFichiers);
        return m;
    }

    public static Message getMessageCreerWorkspace(Map<String, Map<String, StringBuilder>> workspace, Etudiant utilisateur, String session) {
        Message m = new Message("m-015");
        m.addArgument("m-083", workspace);
        m.addArgument("m-073", session);
        m.addArgument("m-081", utilisateur);
        return m;
    }

    public static Message getMessageCreerWorkspace(Map<String, Map<String, StringBuilder>> projets, Etudiant etudiant, String session, Long premierEvt, Long dernierEvt, Map<Long, String> compilations, Map<Long, String> executions, Map<Long, Seance> map, Long tempsCumule, String fichier, String projet) {
        Message m = getMessageCreerWorkspace(projets, etudiant, session);
        m.addArgument("m-019", dernierEvt);
        m.addArgument("m-107", premierEvt);
        m.addArgument("m-011", compilations);
        m.addArgument("m-029", executions);
        m.addArgument("m-072", map);
        m.addArgument("m-077", tempsCumule);
        m.addArgument("m-032", fichier);
        m.addArgument("m-057", projet);
        return m;
    }

    public static Message getMessageCodeSource(String codeSource, String utilisateur, String session) {
        Message m = new Message("m-010");
        m.addArgument("m-020", codeSource);
        m.addArgument("m-073", session);
        m.addArgument("m-081", utilisateur);
        return m;
    }

    public static Message getMessageChangerSession() {
        Message m = new Message("m-007");
        return m;
    }

    public static Message getMessageEnvoieEtudiant(Etudiant etu) {
        Message m = new Message("m-023");
        m.addArgument("m-023", etu);
        return m;
    }

    public static Message getMessageEnvoieTuteur(Tuteur tuteur) {
        Message m = new Message("m-079");
        m.addArgument("m-079", tuteur);
        return m;
    }

    public static Message getMessageInformationSession(String nomSession) {
        Message m = new Message("m-035");
        m.addArgument("m-049", nomSession);
        return m;
    }

    public static Message getMessageEnvoieInfoSession(String nomSession, String langage2, boolean isCompilationProject, int etatSession, boolean testunitaire, String scenario) {
        Message m = new Message("m-035");
        m.addArgument("m-049", nomSession);
        m.addArgument("m-038", langage2);
        m.addArgument("m-037", isCompilationProject);
        m.addArgument("m-109-1", testunitaire);
        m.addArgument("m-058", etatSession);
        m.addArgument("m-095-6", scenario);
        return m;
    }

    public static Message getMessageEnvoieInfoSession(String nomSession, String langage2, boolean isCompilationProject, int etatSession, boolean testunitaire, String scenario, String lesTestsFonctions, Map<String, String> lesClassesDeTestsUnitaires) {
        Message m = getMessageEnvoieInfoSession(nomSession, langage2, isCompilationProject, etatSession, testunitaire, scenario);
        if (lesTestsFonctions != null) {
            m.addArgument("m-084", lesTestsFonctions);
        }

        if (testunitaire) {
            m.addArgument("m-109-2", lesClassesDeTestsUnitaires);
        }

        return m;
    }

    public static Message getMessageEnvoieListesEtudiants(List<Etudiant> etuC, List<Etudiant> etuNC) {
        Message m = new Message("m-045");
        m.addArgument("m-025", etuC);
        m.addArgument("m-027", etuNC);
        return m;
    }

    public static Message getMessageUtilisateurInconnu(String string) {
        Message m = new Message("m-082");
        m.addArgument("m-048", string);
        return m;
    }

    public static Message getMessageSourceEtudiant(String etudiant, String session, Map<String, Map<String, StringBuilder>> projets) {
        Message m = new Message("m-076");
        m.addArgument("m-023", etudiant);
        m.addArgument("m-073", session);
        m.addArgument("m-075", projets);
        return m;
    }

    public static Message getMessageConfirmationChangementPassword(String resultat, String username) {
        Message m = new Message("m-006");
        m.addArgument("m-060", resultat);
        m.addArgument("m-080", username);
        return m;
    }

    public static Message getMessageConfirmationChangementPassword(String resultat, String username, boolean premiereConnexion) {
        Message m = getMessageConfirmationChangementPassword(resultat, username);
        m.addArgument("m-048", premiereConnexion);
        return m;
    }

    public static Message getMessageConfirmationChangementPassword(String retour, String username, String password, boolean premiereConnexion) {
        Message m = getMessageConfirmationChangementPassword(retour, username, premiereConnexion);
        m.addArgument("m-055", password);
        return m;
    }

    public static Message getMessageDemandeChangementPassword(String username) {
        Message m = new Message("m-018");
        m.addArgument("m-080", username);
        return m;
    }

    public static Message getMessageUtilisateurAutorise(String username, String nomComplet) {
        Message m = new Message("m-079");
        m.addArgument("m-080", username);
        m.addArgument("m-049", nomComplet);
        return m;
    }

    public static Message getMessageAdminGetDatas(List<Tuteur> lt, List<String> ls, List<Groupe> lg) {
        Message m = new Message("m-063");
        m.addArgument("m-079", lt);
        m.addArgument("m-073", ls);
        m.addArgument("m-033", lg);
        return m;
    }

    public static Message getMessageAdminGetEtudiantDuGroupe(String nomGroupe, List<Etudiant> le) {
        Message m = new Message("m-064");
        m.addArgument("m-023", le);
        m.addArgument("m-033", nomGroupe);
        return m;
    }

    public static Message getMessageAdminAjouteSession(SessionXML ses) {
        Message m = new Message("m-003");
        m.addArgument("m-073", ses);
        return m;
    }

    public static Message getMessageAdminAjouteGroupe(Groupe g) {
        Message m = new Message("m-002");
        m.addArgument("m-033", g);
        return m;
    }

    public static Message getMessageAdminListeGroupes(List<String> listeGroupe) {
        Message m = new Message("m-067");
        m.addArgument("m-033", listeGroupe);
        return m;
    }

    public static Message getMessageAdminListeGroupesPourModifierEtudiant(List<String> listeGroupe) {
        Message m = new Message("m-067-1");
        m.addArgument("m-033", listeGroupe);
        return m;
    }

    public static Message getMessageAdminLesEtudiantsSansGroupe(List<Etudiant> le) {
        Message m = new Message("m-066");
        m.addArgument("m-023", le);
        return m;
    }

    public static Object getMessageAdminAjouteEtudiants(List<Etudiant> letu, String nomDuGroupe) {
        Message m = new Message("m-001");
        m.addArgument("m-023", letu);
        m.addArgument("m-033", nomDuGroupe);
        return m;
    }

    public static Message getMessageAdminRetourAjouterEtudiants(String nomDuGroupe, boolean retour) {
        Message m = new Message("m-061");
        m.addArgument("m-060", retour);
        m.addArgument("m-033", nomDuGroupe);
        return m;
    }

    public static Message getMessageAdminLesUtilisateursConnectes(List<DataClient> ldc, Long nbConnexionsBD, String versionServeur, Long nombreConnexionsEtus) {
        Message m = new Message("m-070");
        m.addArgument("m-081", ldc);
        m.addArgument("m-013", nbConnexionsBD);
        m.addArgument("m-120", versionServeur);
        m.addArgument("m-123", nombreConnexionsEtus);
        return m;
    }

    public static Message getMessageAdminDeconnecterClients(List<String> lesCles) {
        Message m = new Message("m-122");
        m.addArgument("m-081", lesCles);
        return m;
    }

    public static Message getMessageAdminListeTuteurs(List<Tuteur> listeTuteur) {
        Message m = new Message("m-069");
        m.addArgument("m-079", listeTuteur);
        return m;
    }

    public static Message getMessageAdminRetourAjouterTuteur(List<Tuteur> listeTuteur) {
        Message m = new Message("m-062");
        m.addArgument("m-079", listeTuteur);
        return m;
    }

    public static Message getMessageAdminAjouteTuteur(Tuteur aCreer) {
        Message m = new Message("m-004");
        m.addArgument("m-079", aCreer);
        return m;
    }

    public static Message getMessageAdminListeSessions(List<String> ls) {
        Message m = new Message("m-068");
        m.addArgument("m-073", ls);
        return m;
    }

    public static Message getMessageRetourInfosSessions(String infosDeLaSession) {
        Message m = new Message("m-065");
        m.addArgument("m-073", infosDeLaSession);
        return m;
    }

    public static Message getMessageConfirmationResetPassword(String ok, String username) {
        Message m = new Message("m-085");
        m.addArgument("m-080", username);
        m.addArgument("m-060", ok);
        return m;
    }

    public static Message getMessageAdminListeGroupesPourModifierGroupe(List<String> listeGroupe, List<Etudiant> listeEtusSansGroupe) {
        Message m = new Message("m-067-2");
        m.addArgument("m-033", listeGroupe);
        m.addArgument("m-023", listeEtusSansGroupe);
        return m;
    }

    public static Object getMessageAdminAjouteListeEtudiantAuGroupe(String grpAModifier, List<Etudiant> listeEtu) {
        Message m = new Message("m-086");
        m.addArgument("m-033", grpAModifier);
        m.addArgument("m-023", listeEtu);
        return m;
    }

    public static Object getMessageAdminSupprimeListeEtudiantDuGroupe(String grpAModifier, List<Etudiant> listeEtuASupprimer) {
        Message m = new Message("m-092");
        m.addArgument("m-033", grpAModifier);
        m.addArgument("m-023", listeEtuASupprimer);
        return m;
    }

    public static Message getMessageAdminListeSessionsPourModifierSession(List<String> lso, List<String> lsf, List<String> lsa, List<String> lss) {
        Message m = new Message("m-068-1");
        m.addArgument("m-073-1", lso);
        m.addArgument("m-073-2", lsf);
        m.addArgument("m-073-3", lsa);
        m.addArgument("m-095-6", lss);
        return m;
    }

    public static Message getMessageAdminHorairesSession(List<String> nomsSession, long debut, long fin) {
        Message m = new Message("m-087");
        m.addArgument("m-073", nomsSession);
        m.addArgument("m-087-1", debut);
        m.addArgument("m-087-2", fin);
        return m;
    }

    public static Message getMessageAdminHorairesSession(String nomSession, long debut, long fin, boolean lectureSeule, boolean groupee, String scenario) {
        Message m = new Message("m-087");
        m.addArgument("m-073", nomSession);
        m.addArgument("m-087-1", debut);
        m.addArgument("m-087-2", fin);
        m.addArgument("m-106", lectureSeule);
        m.addArgument("m-124", groupee);
        m.addArgument("m-095-6", scenario);
        return m;
    }

    public static Message getMessageAdminLectureSeule(String nomSession, boolean lectureSeule) {
        Message m = new Message("m-106");
        m.addArgument("m-073", nomSession);
        m.addArgument("m-106", lectureSeule);
        return m;
    }

    public static Message getMessageAdminReponseRenommerSession(String nomSession, String nouveauNom, boolean b) {
        Message m = new Message("m-090");
        m.addArgument("m-073", nomSession);
        m.addArgument("m-088", nouveauNom);
        m.addArgument("m-089", b);
        return m;
    }

    public static Message getMessageAdminReponseRenommerSession(String nomSession, String nouveauNom2, boolean b, String string) {
        Message m = getMessageAdminReponseRenommerSession(nomSession, nouveauNom2, b);
        m.addArgument("m-078", string);
        return m;
    }

    public static Message getMessageAdminMessageUtilisateurs(List<String> etudiants, String leMessage, String username) {
        Message m = new Message("m-046");
        m.addArgument("m-025-1", etudiants);
        m.addArgument("m-079", username);
        m.addArgument("m-078", leMessage);
        return m;
    }

    public static Message getEnonceQuestion(String nomDeLaSession, int qc) {
        Message m = new Message("m-091");
        m.addArgument("m-073", nomDeLaSession);
        m.addArgument("m-091", qc);
        return m;
    }

    public static Message getEnonceQuestionProjet(String nomDeProjet, String scenario) {
        Message m = new Message("m-110");
        m.addArgument("m-057", nomDeProjet);
        m.addArgument("m-095-6", scenario);
        return m;
    }

    public static Message getReponseEnonceQuestion(String enonce, String projet) {
        Message m = new Message("m-091");
        m.addArgument("m-022", enonce);
        m.addArgument("m-057", projet);
        return m;
    }

    public static Message getReponseEnonceQuestion(String enonce, String projet, int question) {
        Message m = getReponseEnonceQuestion(enonce, projet);
        m.addArgument("m-091", question);
        return m;
    }

    public static Message getMessageAdminLogServeur(int nbligneslog) {
        Message m = new Message("m-093");
        m.addArgument("m-093-1", nbligneslog);
        return m;
    }

    public static Message getMessageAdminReponseLogServeur(String resultat) {
        Message m = new Message("m-093");
        m.addArgument("m-078", resultat);
        return m;
    }

    public static Message getMessageAdminReponseCreationParLot(String resultat) {
        Message m = new Message("m-095");
        m.addArgument("m-078", resultat);
        return m;
    }

    public static Message getMessageAdminRechargerDatas() {
        Message m = new Message("m-094");
        return m;
    }

    public static Object getMessageAdminCreerSessionParLot(String annee, String promotion, String semestre, String matiere, String intitule, String langage, String scenario, String compilation, boolean fermee, String nbGroupe, String activite, boolean jacodep) {
        Message m = new Message("m-095");
        m.addArgument("m-095-1", annee);
        m.addArgument("m-095-2", promotion);
        m.addArgument("m-095-3", semestre);
        m.addArgument("m-095-4", matiere);
        m.addArgument("m-095-10", activite);
        m.addArgument("m-095-5", intitule);
        m.addArgument("m-038", langage);
        m.addArgument("m-095-6", scenario);
        m.addArgument("m-095-7", compilation);
        m.addArgument("m-095-8", fermee);
        m.addArgument("m-095-9", nbGroupe);
        m.addArgument("m-095-11", jacodep);
        return m;
    }

    public static Message getMessageAdminObserver(String TypeMessage) {
        Message m = new Message(TypeMessage);
        return m;
    }

    public static <T> Message getMessageReponseObserver(List<T> liste, String TypeMessage, String TypeParametre) {
        Message m = new Message(TypeMessage);
        m.addArgument(TypeParametre, liste);
        return m;
    }

    public static Message getMessageAdminListeGroupesAffectes(String promotion) {
        Message m = new Message("m-098");
        m.addArgument("m-095-2", promotion);
        return m;
    }

    public static Message getMessageAdminAssocierGroupePromotion(String promotion, List lg, int indexPromotion) {
        Message m = new Message("m-100");
        m.addArgument("m-033", lg);
        m.addArgument("m-095-2", promotion);
        m.addArgument("m-112", indexPromotion);
        return m;
    }

    public static Message getMessageAdminSupprimerAffectationGroupe(List<String> selectedValuesList, String promotion, int index) {
        Message m = new Message("m-101");
        m.addArgument("m-033", selectedValuesList);
        m.addArgument("m-095-2", promotion);
        m.addArgument("m-095-2", promotion);
        m.addArgument("m-112", index);
        return m;
    }

    public static Message getMessageAdminObserverTuteurSession(String laSession) {
        Message m = new Message("m-103");
        m.addArgument("m-073", laSession);
        return m;
    }

    public static Message getMessageObserverTuteurSession(List<Tuteur> liste) {
        Message m = new Message("m-103");
        m.addArgument("m-079", liste);
        return m;
    }

    public static Message getMessageAdminAjouterTuteurSession(Tuteur t, String session) {
        Message m = new Message("m-104");
        m.addArgument("m-079", t);
        m.addArgument("m-073", session);
        return m;
    }

    public static Message getMessageAdminSupprimerTuteurSession(String session, List<Tuteur> lstTuteuraSupprimer) {
        Message m = new Message("m-105");
        m.addArgument("m-079", lstTuteuraSupprimer);
        m.addArgument("m-073", session);
        return m;
    }

    public static Message getMessageAdminObserverListeTuteurs(List<Tuteur> lt) {
        Message m = new Message("m-108");
        m.addArgument("m-079", lt);
        return m;
    }

    public static Message getMessageTestUnitaire(String nomDeLaSession, String scenario, String projet, int question, Map<String, String> lesFichiers, String username, String etat_precedent) {
        Message m = new Message("m-109-1");
        m.addArgument("m-073", nomDeLaSession);
        m.addArgument("m-057", projet);
        m.addArgument("m-032", lesFichiers);
        m.addArgument("m-080", username);
        m.addArgument("m-095-6", scenario);
        m.addArgument("m-091", question);
        m.addArgument("m-095-10", etat_precedent);
        return m;
    }

    public static Object getMessageRetourTestUnitaire(String sortie, String projet, int question, boolean resultat, String nouvel_etat) {
        Message m = new Message("m-109-1");
        m.addArgument("m-057", projet);
        m.addArgument("m-089", resultat);
        m.addArgument("m-091", question);
        m.addArgument("m-078", sortie);
        m.addArgument("m-095-10", nouvel_etat);
        return m;
    }

    public static Message getMessageLesEtudiantsSansTrace(String nomSession, String nomTuteur, List<String> lEtusSt) {
        Message m = new Message("m-111");
        m.addArgument("m-079", nomTuteur);
        m.addArgument("m-073", nomSession);
        m.addArgument("m-045", lEtusSt);
        return m;
    }

    public static Message getMessageGroupeAffectesNonAffectes(List<String> listeA, List<String> listeNA, int promotion) {
        Message m = new Message("m-115");
        m.addArgument("m-114", listeA);
        m.addArgument("m-113", listeNA);
        m.addArgument("m-095-2", promotion);
        return m;
    }

    public static Message getMessageAdminInformation(String string) {
        Message m = new Message("m-116");
        m.addArgument("m-078", string);
        return m;
    }

    public static Message getMessageAdminSelectionnePromotion(String nouveauNom) {
        Message m = new Message("m-117");
        m.addArgument("m-095-2", nouveauNom);
        return m;
    }

    public static Message getMessageMiseAJour() {
        Message m = new Message("m-118");
        return m;
    }

    public static Message getMessageMaintenance() {
        return new Message("m-121");
    }

    public static Message getMessageMaintenance(boolean maintenance) {
        Message m = getMessageMaintenance();
        m.addArgument("m-121", maintenance);
        return m;
    }

    public static long getSerialversionuid() {
        return 6584519795299102852L;
    }

    public static Message getMessageAdminCreerSessionGroupee(List<Session> ls, String nomPropose) {
        Message m = new Message("m-124");
        m.addArgument("m-073", ls);
        m.addArgument("m-049", nomPropose);
        return m;
    }

    public static Message getMessageAdminRetourCreerSessionGroupee(String nom, boolean echec, String messageErreur) {
        Message m = new Message("m-124");
        m.addArgument("m-073", nom);
        m.addArgument("m-089", echec);
        m.addArgument("m-078", messageErreur);
        return m;
    }

    public static Message getMessageEnvoieDossierTraceZip(String nomSession, Map<String, String> tracesCompressees) {
        Message m = new Message("m-125");
        m.addArgument("m-125", tracesCompressees);
        m.addArgument("m-073", nomSession);
        return m;
    }

    public static Message getMessageTuteurObtenirTrace(String nomDeLaSession, String nomComplet) {
        Message m = new Message("m-126");
        m.addArgument("m-023", nomComplet);
        m.addArgument("m-073", nomDeLaSession);
        return m;
    }

    public static Message getMessageTuteurEnvoyerTrace(String nomDeLaSession, String nomComplet, String traceCompressee) {
        Message m = new Message("m-126");
        m.addArgument("m-023", nomComplet);
        m.addArgument("m-073", nomDeLaSession);
        m.addArgument("m-126", traceCompressee);
        return m;
    }

    public static Message getMessageArchiverSession(List<String> listeDesSessions) {
        Message m = new Message("m-127");
        m.addArgument("m-073", listeDesSessions);
        return m;
    }

    public static Message getMessageRestaurerSession(List<String> listeDesSessions) {
        Message m = new Message("m-128");
        m.addArgument("m-073", listeDesSessions);
        return m;
    }

    public static Message getMessageTuteurObtenirResultatTU(String nomDeLaSession, String nomComplet) {
        Message m = new Message("m-109-1");
        m.addArgument("m-073", nomDeLaSession);
        m.addArgument("m-023", nomComplet);
        return m;
    }

    public static Message getMessageReponseTestUnitaire(List<TestUnitaire> lstu) {
        Message m = new Message("m-109-1");
        m.addArgument("m-109-1", lstu);
        return m;
    }

    public static Message getMessageAdminRecupereLogEtudiant(String cle, String nomEtudiant) {
        Message m = new Message("m-129");
        m.addArgument("m-023", cle);
        m.addArgument("m-049", nomEtudiant);
        return m;
    }

    public static Message getMessageAdminRecupereLogEtudiant(String destinataire, String laCle, String nomEtudiant) {
        Message m = getMessageAdminRecupereLogEtudiant(laCle, nomEtudiant);
        m.addArgument("m-130", destinataire);
        return m;
    }

    public static Message getMessageReponseAdminRecupereLogEtudiant(String destinataire, Map<String, String> lesLogs, String nomEtudiant) {
        Message m = new Message("m-129");
        m.addArgument("m-129", lesLogs);
        m.addArgument("m-049", nomEtudiant);
        m.addArgument("m-130", destinataire);
        return m;
    }
}
