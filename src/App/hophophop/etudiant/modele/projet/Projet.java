//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.projet;

import hophophop.commun.modele.Evenement;
import hophophop.etudiant.modele.H3Etudiant;
import hophophop.etudiant.modele.fichier.Fichier;
import hophophop.etudiant.modele.observateurs.ObservateurProjet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public abstract class Projet {
    private List<ObservateurProjet> observateurs;
    private String nom;
    private String parametreLigneDeCommande;
    private List<Fichier> fichiers;
    protected String exprReguliereTypeFichier;
    private boolean compilationProjet;
    private List<Erreur> listeErreur;
    private Set<String> ensembleNomFichierAvecErreur;
    private Set<Fichier> ensembleFichierAvecErreur;
    private String commandeExecution;

    public Fichier getFichierParNom(String nomDeFichier) {
        Iterator var2 = this.getFichiers().iterator();

        Fichier fic;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            fic = (Fichier)var2.next();
        } while(!fic.getNom().equals(nomDeFichier));

        return fic;
    }

    public boolean isCompilationProjet() {
        return this.compilationProjet;
    }

    public void setCompilationProjet(boolean compilationProjet) {
        this.compilationProjet = compilationProjet;
    }

    public String getExprReguliereTypeFichier() {
        return this.exprReguliereTypeFichier;
    }

    public Projet() {
        this.observateurs = new ArrayList();
        this.parametreLigneDeCommande = "";
        this.fichiers = new ArrayList();
        this.listeErreur = new ArrayList();
        this.ensembleNomFichierAvecErreur = new HashSet();
        this.ensembleFichierAvecErreur = new HashSet();
        this.commandeExecution = "";
        this.initialiseCompletionProvider();
    }

    public Projet(String nom) {
        this();
        this.nom = nom;
        (new File(this.getRepertoire())).mkdirs();
        this.enregistrer();
        this.setCompilationProjet(H3Etudiant.isCompilationProjet());
        if (System.getProperty("os.name").indexOf("Windows") != -1) {
            this.commandeExecution = "cmd.exe /c start " + H3Etudiant.DOSSIER_OUTILS + "Hop3xExecutionWin.cmd ";
        } else if (System.getProperty("os.name").indexOf("Mac") != -1) {
            this.commandeExecution = "/usr/X11/bin/xterm -lf " + H3Etudiant.DOSSIER_DONNEES + "trace.log -l -geometry 100x30+400+400 -en UTF-8 -title LETITRE -e " + H3Etudiant.DOSSIER_OUTILS + "Hop3xExecutionMac ";
        } else if (System.getProperty("os.name").indexOf("Linux") != -1) {
            this.commandeExecution = "xterm -lf " + H3Etudiant.DOSSIER_DONNEES + "trace.log -l -geometry 100x30+400+400 -en UTF-8 -title LETITRE -e ./" + H3Etudiant.DOSSIER_OUTILS + "Hop3xExecutionLinux ";
        }

    }

    public Projet(String nom2, boolean compilationProjet) {
        this(nom2);
        this.compilationProjet = compilationProjet;
    }

    public void ajouterObservateur(ObservateurProjet observateur) {
        this.observateurs.add(observateur);
    }

    public void supprimerObservateur(ObservateurProjet observateur) {
        this.observateurs.remove(observateur);
    }

    public String getCommandeExecution() {
        return this.commandeExecution;
    }

    public void setCommandeExecution(String commande) {
        this.commandeExecution = commande;
    }

    public String getNom() {
        return this.nom;
    }

    public abstract int compiler(boolean var1);

    public abstract int compilerUnFichier(boolean var1, Fichier var2);

    public void executer(final String laCommande) {
        String[] lesArguments = laCommande.split(" ");
        String[] decoupe = lesArguments[lesArguments.length - 1].split("/");
        final String titre = decoupe[decoupe.length - 1];
        H3Etudiant.getLogger().info("Path : " + System.getenv("PATH"));
        H3Etudiant.getLogger().info("Plateforme : " + System.getProperty("os.name"));
        (new Thread() {
            public void run() {
                try {
                    String sortie = "";
                    String commande = Projet.this.commandeExecution + laCommande;
                    String nomDuFichierLog = System.currentTimeMillis() + ".log";
                    H3Etudiant.getLogger().info("Nom du Fichier Log = " + nomDuFichierLog);
                    commande = commande.replace("trace.log", nomDuFichierLog);
                    commande = commande.replace("LETITRE", titre);
                    H3Etudiant.getLogger().info("Ligne de commande Exécution :\n\t" + commande);
                    H3Etudiant.getLogger().info("Exécution : " + commande);
                    Process processus = Runtime.getRuntime().exec(commande);
                    new ProcessBuilder(new String[0]);
                    processus.waitFor();
                    H3Etudiant.getLogger().info("Exécution terminée : " + commande);
                    File myFile = new File(H3Etudiant.DOSSIER_DONNEES + nomDuFichierLog);
                    H3Etudiant.getLogger().info("Fichier de Log d'éxécution : " + myFile.getAbsolutePath());
                    if (!myFile.exists()) {
                        sortie = "Le résultat de l'éxécution ne peut pas être affichée ici dans cet environnement : " + System.getProperty("os.name") + "\n";
                    } else {
                        InputStream flux = new FileInputStream(myFile);
                        InputStreamReader lecture = new InputStreamReader(flux, "UTF-8");

                        BufferedReader buff;
                        String ligne;
                        for(buff = new BufferedReader(lecture); (ligne = buff.readLine()) != null; sortie = sortie + ligne + "\n") {
                        }

                        buff.close();

                        int index;
                        for(sortie = sortie.replace("\b \b", "\b"); (index = sortie.indexOf(8)) != -1; sortie = sortie.substring(0, index - 1) + sortie.substring(index + 1)) {
                        }

                        myFile.delete();
                        sortie = sortie.replace("\u001b[H\u001b[2J", "");
                        StringBuffer resultat = new StringBuffer(sortie);
                        int i = 0;

                        while(true) {
                            if (i >= resultat.length()) {
                                sortie = resultat.toString();
                                if (sortie.length() > 2048) {
                                    sortie = sortie.substring(0, 2048) + "\n######LA SORTIE A ETE TRONQUEE #########";
                                }
                                break;
                            }

                            if (resultat.charAt(i) < ' ' && resultat.charAt(i) != '\n' && resultat.charAt(i) != '\t') {
                                H3Etudiant.getLogger().warning("Caractère <" + resultat.charAt(i) + "> pas Imprimable supprimé");
                                resultat.deleteCharAt(i);
                            } else {
                                ++i;
                            }
                        }
                    }

                    processus.destroy();
                    Iterator var15 = Projet.this.getObservateurs().iterator();

                    while(var15.hasNext()) {
                        ObservateurProjet observateur = (ObservateurProjet)var15.next();
                        observateur.executionProjet(Projet.this, sortie);
                    }

                    Evenement evenement = Evenement.getEvenementExecution(Projet.this.getNom(), sortie);
                    H3Etudiant.getTraceur().envoyer(evenement);
                } catch (Exception var14) {
                    var14.printStackTrace();
                }

            }
        }).start();
    }

    public void enregistrer() {
        Element root = new Element("PROJET");
        Document doc = new Document(root);
        root.setAttribute(new Attribute("NOM", this.nom));
        root.setAttribute(new Attribute("TYPE", this.getType()));
        Iterator var3 = this.fichiers.iterator();

        while(var3.hasNext()) {
            Fichier fichier = (Fichier)var3.next();
            root.addContent((new Element("FICHIER")).setText(fichier.getNom()));
        }

        try {
            XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
            File fichier = new File(this.getRepertoire() + this.nom + ".xml");
            fichier.getParentFile().mkdirs();
            out.output(doc, new FileOutputStream(fichier));
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public String getRepertoire() {
        return (H3Etudiant.DOSSIER_TRAVAIL + H3Etudiant.getNomDeLaSession() + File.separator + this.nom + File.separator).replace(' ', '_');
    }

    public String getRepertoireAbsolu() {
        return (new File(this.getRepertoire())).getAbsolutePath() + File.separator;
    }

    public void ajouterFichier(Fichier fichier) {
        this.fichiers.add(fichier);
        Evenement evenementAjout = Evenement.getEvenementAjoutFichier(fichier.getProjet().getNom(), fichier.getNom(), fichier.getType());
        H3Etudiant.getTraceur().envoyer(evenementAjout);
        Iterator var3 = this.observateurs.iterator();

        while(var3.hasNext()) {
            ObservateurProjet observateur = (ObservateurProjet)var3.next();
            observateur.ajoutFichier(fichier);
        }

        this.enregistrer();
    }

    public void supprimerFichier(Fichier fichier) {
        this.fichiers.remove(fichier);
        Evenement evenement = Evenement.getEvenementSuppressionFichier(fichier.getProjet().getNom(), fichier.getNom(), fichier.getType());
        H3Etudiant.getTraceur().envoyer(evenement);
        Iterator var3 = this.observateurs.iterator();

        while(var3.hasNext()) {
            ObservateurProjet observateur = (ObservateurProjet)var3.next();
            observateur.suppressionFichier(fichier);
        }

        File myFile = new File(fichier.getFichierSource());
        if (myFile.delete()) {
            H3Etudiant.getLogger().info("fichier supprimé :" + fichier.getFichierSource());
        }

        this.enregistrer();
    }

    public List<Fichier> getFichiers() {
        return this.fichiers;
    }

    public abstract List<String> getFichiersMain();

    protected List<ObservateurProjet> getObservateurs() {
        return this.observateurs;
    }

    public abstract String getType();

    public Set<String> getEnsembleNomFichierAvecErreur() {
        return this.ensembleNomFichierAvecErreur;
    }

    public List<Erreur> getListeErreur() {
        return this.listeErreur;
    }

    public Set<Fichier> getEnsembleFichierAvecErreur() {
        return this.ensembleFichierAvecErreur;
    }

    public String getParametreLigneDeCommande() {
        return this.parametreLigneDeCommande;
    }

    public void setParametreLigneDeCommande(String parametreLigneDeCommande) {
        this.parametreLigneDeCommande = parametreLigneDeCommande;
    }

    public void initialiseCompletionProvider() {
        DefaultCompletionProvider provider = H3Etudiant.getProvider();
        provider.clear();
    }
}
