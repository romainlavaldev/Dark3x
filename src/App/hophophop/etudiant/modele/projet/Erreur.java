//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.etudiant.modele.projet;

import hophophop.etudiant.modele.fichier.Fichier;

public class Erreur {
    private Fichier leFichier;
    private int numeroDeLigne;
    private String messageErreur;
    private String messageErreurComplement;

    public Erreur(Fichier fichier, int numeroDeLigne, String messageErreur, String messageErreurComplement) {
        this.leFichier = fichier;
        this.numeroDeLigne = numeroDeLigne;
        this.messageErreur = messageErreur;
        this.messageErreurComplement = messageErreurComplement;
    }

    public int getNumeroDeLigne() {
        return this.numeroDeLigne;
    }

    public String getMessageErreur() {
        return this.messageErreur;
    }

    public String getMessageErreurComplement() {
        return this.messageErreurComplement;
    }

    public String toString() {
        String retour = "\tNom de Fichier  = " + this.getLeFichier().getNom() + "\n\tNuméro de Ligne = " + this.getNumeroDeLigne() + "\n\tMessage         = " + this.getMessageErreur() + "\n\tMessage suite   = " + this.getMessageErreurComplement();
        return retour;
    }

    public Fichier getLeFichier() {
        return this.leFichier;
    }
}
