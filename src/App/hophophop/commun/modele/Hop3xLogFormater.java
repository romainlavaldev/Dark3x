//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Hop3xLogFormater extends Formatter {
    private static final DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss.SSS");
    private String identification;

    public Hop3xLogFormater(String identification) {
        this.identification = identification;
    }

    public Hop3xLogFormater() {
    }

    public String format(LogRecord record) {
        String chaine = "";
        if (this.identification != null) {
            chaine = chaine + "[" + this.identification + "] - ";
        }

        chaine = chaine + df.format(new Date(record.getMillis())) + " - ";
        chaine = chaine + "[" + record.getLevel() + "] - ";
        chaine = chaine + this.formatMessage(record) + " - ";
        chaine = chaine + "[" + record.getSourceClassName() + ".";
        chaine = chaine + record.getSourceMethodName() + "]\n";
        return chaine;
    }

    public String getHead(Handler h) {
        return super.getHead(h);
    }

    public String getTail(Handler h) {
        return super.getTail(h);
    }
}
