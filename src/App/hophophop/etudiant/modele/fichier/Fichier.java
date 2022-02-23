//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.fichier;

import hophophop.commun.modele.Evenement;
import hophophop.etudiant.modele.H3Etudiant;
import hophophop.etudiant.modele.observateurs.ObservateurFichier;
import hophophop.etudiant.modele.projet.Projet;
import hophophop.etudiant.modele.projet.ProjetNxc;
import hophophop.etudiant.vue.Actions;
import hophophop.etudiant.vue.Editeur.Page;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;

public abstract class Fichier {
    public static boolean TRACER = true;
    protected List<ObservateurFichier> observateurs;
    private String nom;
    private Projet projet;
    private RSyntaxDocument source;
    private String commentaire;
    private StringBuffer texteSource;
    protected String sauvegarde;
    private boolean enregistre;
    private boolean aEteModifie;
    private Page laPage;
    private String syntaxStyle;

    public String getCommentaire() {
        return this.commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public boolean isaEteModifie() {
        return this.aEteModifie;
    }

    public void setSyntaxStyle(String syntaxStyle) {
        this.source.setSyntaxStyle("text/c");
        this.syntaxStyle = syntaxStyle;
    }

    private Fichier(Projet projet) {
        this.projet = projet;
        this.laPage = null;
        this.observateurs = new ArrayList();
        this.sauvegarde = "";
        this.source = new RSyntaxDocument((String)null);
        this.texteSource = new StringBuffer();
        this.aEteModifie = true;
        this.source.addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent evt) {
            }

            public void insertUpdate(DocumentEvent evt) {
                Fichier.this.setEnregistre(false);
                if (!Fichier.this.getNom().endsWith(".h") && !Fichier.this.getNom().endsWith(".glade")) {
                    Actions.COMPILER.setEnabled(true);
                }

                Fichier.this.aEteModifie = true;

                try {
                    String texte = Fichier.this.getSource().getText(evt.getOffset(), evt.getLength());
                    Fichier.this.texteSource.insert(evt.getOffset(), texte);
                    int position = evt.getOffset();
                    Evenement evenement = Evenement.getEvenementInsertionTexte(Fichier.this.getProjet().getNom(), Fichier.this.getNom(), texte, position);
                    H3Etudiant.getTraceur().envoyer(evenement);
                } catch (BadLocationException var5) {
                    var5.printStackTrace();
                }

                Iterator var6 = Fichier.this.observateurs.iterator();

                while(var6.hasNext()) {
                    ObservateurFichier observateur = (ObservateurFichier)var6.next();
                    observateur.modificationFichier();
                }

            }

            public void removeUpdate(DocumentEvent evt) {
                Fichier.this.setEnregistre(false);
                if (!Fichier.this.getNom().endsWith(".h") && !Fichier.this.getNom().endsWith(".glade")) {
                    Actions.COMPILER.setEnabled(true);
                }

                Fichier.this.aEteModifie = true;
                int debut = evt.getOffset();
                int longueur = evt.getLength();
                int fin = debut + longueur;
                String texteEnleve = Fichier.this.texteSource.substring(debut, fin);
                Evenement evenement = Evenement.getEvenementSuppressionTexte(Fichier.this.getProjet().getNom(), Fichier.this.getNom(), debut, fin, texteEnleve.replace("\\n", "$€n€$").replace("\\t", "$€t€$").replace("\n", "\\n").replace("\t", "\\t"));
                H3Etudiant.getTraceur().envoyer(evenement);
                Fichier.this.texteSource.delete(debut, fin);
                Iterator var7 = Fichier.this.observateurs.iterator();

                while(var7.hasNext()) {
                    ObservateurFichier observateur = (ObservateurFichier)var7.next();
                    observateur.modificationFichier();
                }

            }
        });
    }

    public void setaEteModifie(boolean aEteModifie) {
        this.aEteModifie = aEteModifie;
    }

    public Fichier(Projet projet, String nom) {
        this(projet);
        this.nom = nom.substring(nom.lastIndexOf(File.separator) + 1);
        projet.ajouterFichier(this);
        this.enregistrer();
    }

    public Fichier(Projet projet, File fichier) {
        this(projet, fichier.getName());

        try {
            StringBuffer texte = new StringBuffer();
            InputStreamReader reader = new InputStreamReader(new FileInputStream(fichier), "UTF-8");
            boolean var5 = false;

            int c;
            while((c = reader.read()) != -1) {
                texte.insert(texte.length(), (char)c);
            }

            reader.close();
            this.source.insertString(0, texte.toString().replace("\r", ""), (AttributeSet)null);
            this.sauvegarde = this.source.getText(0, this.source.getLength());
            this.enregistre = true;
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    public Fichier(Projet projet, String nom, String contenu) {
        this(projet);
        this.nom = nom;

        try {
            this.source.insertString(0, contenu, (AttributeSet)null);
            this.sauvegarde = this.source.getText(0, this.source.getLength());
        } catch (BadLocationException var5) {
            var5.printStackTrace();
        }

        this.enregistre = true;
    }

    public void ajouterObservateur(ObservateurFichier observateur) {
        this.observateurs.add(observateur);
    }

    public void supprimerObservateur(ObservateurFichier observateur) {
        this.observateurs.remove(observateur);
    }

    public void recharger() {
        if (!this.enregistre) {
            try {
                this.source.remove(0, this.source.getLength());
            } catch (BadLocationException var4) {
                var4.printStackTrace();
            }

            try {
                this.source.insertString(0, this.sauvegarde, (AttributeSet)null);
            } catch (BadLocationException var3) {
                var3.printStackTrace();
            }

            this.enregistre = true;
            Iterator var1 = this.observateurs.iterator();

            while(var1.hasNext()) {
                ObservateurFichier observateur = (ObservateurFichier)var1.next();
                observateur.enregistrementFichier();
            }
        }

    }

    public void enregistrer() {
        try {
            File fichier = new File(this.getFichierSource());
            if (this.aEteModifie || !fichier.exists()) {
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fichier), "UTF-8");
                String chemin;
                if (this instanceof FichierJava && this.nom.endsWith(".java")) {
                    chemin = this.projet.getRepertoireAbsolu();
                    chemin = chemin.replaceAll(Matcher.quoteReplacement("\\"), "/");
                    String contenu = this.source.getText(0, this.source.getLength()).replaceAll("@H3_HOME/", chemin);
                    writer.write(contenu);
                } else if (this instanceof FichierRuby && this.nom.endsWith(".rb")) {
                    chemin = "# encoding: UTF-8\n\n";
                    writer.write(chemin + this.source.getText(0, this.source.getLength()));
                } else if (this instanceof FichierPython) {
                    chemin = "#coding: utf-8\n\n";
                    writer.write(chemin + this.source.getText(0, this.source.getLength()));
                } else if (this instanceof FichierNxc) {
                    if (ProjetNxc.isSIMULATEUR()) {
                        writer.write(this.source.getText(0, this.source.getLength()));
                    } else {
                        chemin = (new File(H3Etudiant.DOSSIER_OUTILS)).getAbsolutePath();
                        chemin = "#include \"" + chemin + "/MyDefs.h\"\n";
                        chemin = "#ifndef _MYDEFS_\n\t#define _MYDEFS_\n\t" + chemin + "#endif\n";
                        writer.write(chemin + this.source.getText(0, this.source.getLength()).replaceAll("void\\s+main", "task main"));
                    }
                } else if (this instanceof FichierSpiC) {
                    chemin = (new File(H3Etudiant.DOSSIER_OUTILS)).getAbsolutePath();
                    chemin = "#include \"" + chemin + "/SpiCdefs.h\"\n";
                    chemin = "#ifndef _SPICDEFS_\n\t#define _SPICDEFS_\n\t" + chemin + "#endif\n";
                    writer.write(chemin + this.source.getText(0, this.source.getLength()).replaceAll("void\\s+main", "int main"));
                } else {
                    writer.write(this.source.getText(0, this.source.getLength()));
                }

                writer.flush();
                writer.close();
                this.sauvegarde = this.source.getText(0, this.source.getLength());
                this.enregistre = true;
                Iterator var7 = this.observateurs.iterator();

                while(var7.hasNext()) {
                    ObservateurFichier observateur = (ObservateurFichier)var7.next();
                    observateur.enregistrementFichier();
                }

                this.aEteModifie = false;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public RSyntaxDocument getSource() {
        return this.source;
    }

    public String getNom() {
        return this.nom;
    }

    public Projet getProjet() {
        return this.projet;
    }

    public String getFichierSource() {
        return this.projet.getRepertoire() + this.nom;
    }

    public boolean isEnregistre() {
        return this.enregistre;
    }

    public String getTexte() {
        try {
            return this.source.getText(0, this.source.getLength());
        } catch (Exception var2) {
            return null;
        }
    }

    protected void setEnregistre(boolean enregistre) {
        this.enregistre = enregistre;
    }

    public abstract String getType();

    public void setPage(Page page) {
        this.laPage = page;
    }

    public Page getLaPage() {
        return this.laPage;
    }

    public String getSyntaxStyle() {
        return this.syntaxStyle;
    }
}
