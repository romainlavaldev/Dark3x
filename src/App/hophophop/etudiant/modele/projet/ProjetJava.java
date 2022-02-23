//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.projet;

import hophophop.commun.modele.Evenement;
import hophophop.etudiant.modele.H3Etudiant;
import hophophop.etudiant.modele.fichier.Fichier;
import hophophop.etudiant.modele.observateurs.ObservateurProjet;
import java.awt.Component;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;

public class ProjetJava extends Projet {
    private static String commandeDoc;

    public static String getCommandeDoc() {
        return commandeDoc;
    }

    public static void setCommandeDoc(String cDoc) {
        commandeDoc = cDoc;
    }

    public ProjetJava() {
    }

    public ProjetJava(String nom, boolean compilationProjet) {
        super(nom, compilationProjet);
        this.exprReguliereTypeFichier = "([^.]*[.]java$)";
        commandeDoc = "javadoc -encoding \"UTF-8\" " + this.getRepertoire() + " --op " + this.getRepertoire() + "doc";
        if (System.getProperty("os.name").indexOf("Windows") != -1) {
            commandeDoc = "cmd.exe /c " + commandeDoc.replace("\\", "/");
        }

    }

    public List<Fichier> getFichiersJava() {
        List fichiersJava = new ArrayList();
        Iterator var2 = this.getFichiers().iterator();

        while(var2.hasNext()) {
            Fichier f = (Fichier)var2.next();
            if (f.getType().equals("Java")) {
                fichiersJava.add(f);
            }
        }

        return fichiersJava;
    }

    public int compiler(boolean manuelle) {
        this.enregistrer();
        String[] args = new String[this.getFichiersJava().size() + 5];
        args[0] = "-classpath";
        args[1] = this.getRepertoire();
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            JOptionPane.showMessageDialog((Component)null, "Pas de JDK installé, vous ne pouvez pas compiler du Java", "Erreur", 0);
            return -1;
        } else {
            H3Etudiant.getLogger().info("Compilateur trouvé : " + compiler);
            H3Etudiant.getLogger().info("Java version : " + System.getProperty("java.version"));
            H3Etudiant.getLogger().info("java.vendor : " + System.getProperty("java.vendor"));
            H3Etudiant.getLogger().info("java.home : " + System.getProperty("java.home"));
            H3Etudiant.getLogger().info("java.class.path : " + System.getProperty("java.class.path"));
            H3Etudiant.getLogger().info("os.arch : " + System.getProperty("os.arch"));
            H3Etudiant.getLogger().info("user.dir : " + System.getProperty("user.dir"));
            args[2] = "-d";
            args[3] = this.getRepertoire();

            for(int i = 4; i < args.length - 1; ++i) {
                args[i] = ((Fichier)this.getFichiersJava().get(i - 4)).getFichierSource();
            }

            args[args.length - 1] = "-nowarn";
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            List<File> listedesFichiersClasses = new ArrayList();
            listedesFichiersClasses = this.getListeFichierClass(new File(this.getRepertoire()), listedesFichiersClasses);
            Iterator var6 = listedesFichiersClasses.iterator();

            while(var6.hasNext()) {
                File fc = (File)var6.next();
                fc.delete();
            }

            File[] listedesFichiers = (new File(this.getRepertoire())).listFiles();

            int resultat;
            for(resultat = 0; resultat < listedesFichiers.length; ++resultat) {
                if (listedesFichiers[resultat].isDirectory()) {
                    listedesFichiers[resultat].delete();
                }
            }

            resultat = compiler.run((InputStream)null, (OutputStream)null, out, args);
            String sortieComp = out.toString();
            byte[] c = sortieComp.getBytes();

            for(int i = 0; i < sortieComp.length(); ++i) {
                if (c[i] == 26 || c[i] == 0) {
                    H3Etudiant.getLogger().severe("Indice Character :: " + i + " code : " + c[i] + " Caractère : " + sortieComp.charAt(i));
                    c[i] = 32;
                }
            }

            sortieComp = new String(c);
            Evenement evenement;
            if (manuelle) {
                evenement = Evenement.getEvenementCompilationManuelle(this.getNom(), sortieComp);
            } else {
                evenement = Evenement.getEvenementCompilationAutomatique(this.getNom(), sortieComp);
            }

            H3Etudiant.getTraceur().envoyer(evenement);
            this.getListeErreur().clear();
            this.getEnsembleFichierAvecErreur().clear();
            this.getEnsembleNomFichierAvecErreur().clear();
            String sortie = sortieComp.replace('\\', '/');
            Pattern p = Pattern.compile("[^/]*.java:\\d+:[^\n]*[\n][^\n]*");
            Matcher m = p.matcher(sortie);

            while(m.find()) {
                String erreur = m.group().replace('\n', ':');
                StringTokenizer st = new StringTokenizer(erreur, ":");
                String nomDeFichier = (String)st.nextElement();
                String numDeLigne = (String)st.nextElement();
                String message1 = (String)st.nextElement();

                String message2;
                for(message2 = (String)st.nextElement(); st.hasMoreElements(); message2 = message2 + st.nextElement()) {
                }

                this.getEnsembleNomFichierAvecErreur().add(nomDeFichier);
                this.getListeErreur().add(new Erreur(this.getFichierParNom(nomDeFichier), Integer.parseInt(numDeLigne), message1, message2));
            }

            Iterator var25 = this.getFichiers().iterator();

            while(var25.hasNext()) {
                Fichier fic = (Fichier)var25.next();
                if (this.getEnsembleNomFichierAvecErreur().contains(fic.getNom())) {
                    this.getEnsembleFichierAvecErreur().add(fic);
                }
            }

            var25 = this.getObservateurs().iterator();

            while(var25.hasNext()) {
                ObservateurProjet observateur = (ObservateurProjet)var25.next();
                observateur.compilationProjet(this, sortieComp, this.getListeErreur(), this.getEnsembleFichierAvecErreur(), true);
            }

            return resultat;
        }
    }

    public void executer(String main) {
        String commande = "java -classpath " + this.getRepertoire() + " " + main.replace(".java", "");
        H3Etudiant.getLogger().info("Commande Exécution : " + commande);
        super.executer(commande);
    }

    public List<String> getFichiersMain() {
        List<String> mains = new ArrayList();
        File chemin = new File(this.getRepertoire());
        List<File> listedesFichiersClasses = new ArrayList();
        listedesFichiersClasses = this.getListeFichierClass(chemin, listedesFichiersClasses);
        List<String> lstNomClasse = new ArrayList();
        Iterator var5 = listedesFichiersClasses.iterator();

        while(var5.hasNext()) {
            File fi = (File)var5.next();
            String classe = fi.getAbsolutePath().replace(chemin.getAbsolutePath(), "");
            classe = classe.replace(File.separatorChar, '.').replace(".class", "").substring(1);
            lstNomClasse.add(classe);
        }

        try {
            URL[] urlList = new URL[]{new URL("file:" + chemin.getAbsolutePath() + "/")};
            ClassLoader loader = new URLClassLoader(urlList);
            Iterator var18 = lstNomClasse.iterator();

            while(var18.hasNext()) {
                String cla = (String)var18.next();
                Class c = loader.loadClass(cla);

                try {
                    c.getMethod("main", String[].class);
                    mains.add(cla);
                } catch (NoSuchMethodException var11) {
                }
            }
        } catch (MalformedURLException var12) {
            H3Etudiant.getLogger().severe("Compilateur non trouvé " + var12.getMessage());
        } catch (ClassNotFoundException var13) {
            var13.printStackTrace();
        } catch (SecurityException var14) {
            var14.printStackTrace();
        }

        return mains;
    }

    private List<File> getListeFichierClass(File repertoire, List<File> listeFichierClasse) {
        if (repertoire.isDirectory()) {
            File[] list = repertoire.listFiles();
            if (list != null) {
                for(int i = 0; i < list.length; ++i) {
                    if (list[i].isDirectory()) {
                        listeFichierClasse = this.getListeFichierClass(list[i], listeFichierClasse);
                    }

                    if (list[i].getName().endsWith(".class")) {
                        listeFichierClasse.add(list[i]);
                    }
                }
            } else {
                H3Etudiant.getLogger().severe(repertoire + " : erreur de lecture.");
            }
        }

        return listeFichierClasse;
    }

    public String getType() {
        return "Java";
    }

    public int compilerUnFichier(boolean dialogue, Fichier fichier) {
        return 0;
    }

    public void initialiseCompletionProvider() {
        DefaultCompletionProvider provider = H3Etudiant.getProvider();
        provider.clear();
        provider.addCompletion(new ShorthandCompletion(provider, "sop", "System.out.println(", "System.out.println("));
        provider.addCompletion(new ShorthandCompletion(provider, "sep", "System.err.println(", "System.err.println("));
    }
}
