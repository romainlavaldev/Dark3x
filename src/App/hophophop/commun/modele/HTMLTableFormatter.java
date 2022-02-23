//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class HTMLTableFormatter extends Formatter {
    public HTMLTableFormatter() {
    }

    public String format(LogRecord record) {
        return "  <tr><td>" + record.getMillis() + "</td><td>" + record.getMessage() + "</td></tr>\n";
    }

    public String getHead(Handler h) {
        return "<table border>\n  <tr><th>Time</th><th>Log Message</th></tr>\n";
    }

    public String getTail(Handler h) {
        return "</table>\n";
    }
}
