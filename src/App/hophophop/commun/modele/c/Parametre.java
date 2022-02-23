package hophophop.commun.modele.c;

public class Parametre extends Signature {
    private String variable;

    public Parametre(String parametre) {
        super(parametre);
        this.variable = parametre.split(":")[1];
    }

    public String getVariable() {
        return this.variable;
    }
}
