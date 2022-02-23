//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.vue;

import hophophop.etudiant.modele.H3Etudiant;
import hophophop.etudiant.modele.fichier.Fichier;
import hophophop.etudiant.modele.projet.Projet;
import hophophop.etudiant.vue.Explorateur.NoeudFichier;
import hophophop.etudiant.vue.Explorateur.NoeudProjet;
import java.awt.EventQueue;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.JTree.DropLocation;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.tree.TreePath;

public class MyTransferHandler extends TransferHandler {
    public MyTransferHandler() {
    }

    public boolean canImport(TransferSupport info) {
        info.setShowDropLocation(true);
        return true;
    }

    public boolean importData(TransferSupport supportP) {
        TransferSupport support = supportP;
        if (!this.canImport(supportP)) {
            return false;
        } else {
            Transferable data = supportP.getTransferable();

            try {
                final Explorateur expl = (Explorateur)support.getComponent();
                Object o = data.getTransferData(DataFlavor.javaFileListFlavor);
                JTree.DropLocation dl = (JTree.DropLocation)support.getDropLocation();
                JTree tree = (JTree)support.getComponent();
                int dropRow = tree.getRowForPath(dl.getPath());
                if (o instanceof NoeudFichier) {
                    TreePath tp = expl.getPathForRow(dropRow);
                    Projet projetDestination = ((NoeudProjet)tp.getPathComponent(1)).getProjet();
                    Projet projetSource = ((NoeudProjet)((NoeudFichier)o).getParent()).getProjet();
                    Fichier leFichierACopier = ((NoeudFichier)o).getFichier();
                    H3Etudiant.getLogger().info("DRAG&DROP :: Le Fichier : " + leFichierACopier.getNom() + " a été copié depuis le Projet <" + projetSource.getNom() + ">, vers le projet <" + projetDestination.getNom() + ">");
                    if (projetSource.getNom().equals(projetDestination.getNom())) {
                        H3Etudiant.getLogger().warning("DROP :: Source et Destination identiques : " + projetSource.getNom() + " !!!!!!!");
                        EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                JOptionPane.showMessageDialog(expl.getParent(), "Projets Source et Destination identiques  !!!!!!!");
                            }
                        });
                    } else {
                        Actions.importerFichier(leFichierACopier, projetSource, projetDestination);
                    }
                } else {
                    final List<File> resultat = (List)data.getTransferData(DataFlavor.javaFileListFlavor);
                    Thread t = new Thread() {
                        public void run() {
                            Iterator var1 = resultat.iterator();

                            while(var1.hasNext()) {
                                File f = (File)var1.next();
                                if (f.isDirectory()) {
                                    Actions.importerRepertoire(f);
                                } else if (f.isFile()) {
                                    Actions.importerFichier(f, expl.getProjet());
                                }
                            }

                        }
                    };
                    t.start();
                }
            } catch (UnsupportedFlavorException var13) {
                var13.printStackTrace();
            } catch (IOException var14) {
                var14.printStackTrace();
            }

            return false;
        }
    }

    protected void exportDone(JComponent c, Transferable t, int action) {
        if (action == 2) {
            ((JLabel)c).setText("");
        }

    }

    protected Transferable createTransferable(JComponent c) {
        Object o = ((Explorateur)c).getSelectionPath().getLastPathComponent();
        return o instanceof NoeudFichier ? (NoeudFichier)o : null;
    }

    public int getSourceActions(JComponent c) {
        return 2;
    }
}
