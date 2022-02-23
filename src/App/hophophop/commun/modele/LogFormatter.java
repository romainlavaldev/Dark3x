//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {
    public LogFormatter() {
    }

    public String format(LogRecord record) {
        return "Hop3xLog/" + record.getLevel() + " : " + (new Date(record.getMillis())).toString() + ", Message = " + record.getMessage() + ", SourceClassName = " + record.getSourceClassName() + ", SourceMethodName = " + record.getSourceMethodName() + "\n";
    }
}
