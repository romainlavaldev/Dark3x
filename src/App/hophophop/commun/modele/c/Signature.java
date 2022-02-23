package hophophop.commun.modele.c;

public class Signature {
    private String type;

    public String getType() {
        return this.type;
    }

    public Signature(String type) {
        this.type = type.split(":")[0];
    }
}
