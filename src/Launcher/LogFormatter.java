import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

class LogFormatter extends Formatter
{

    LogFormatter()
    {
    }

    public String format(LogRecord record)
    {
        return (new StringBuilder()).append("Hop3xLog/").append(record.getLevel()).append(" : ").append((new Date(record.getMillis())).toString()).append(", Message = ").append(record.getMessage()).append(", SourceClassName = ").append(record.getSourceClassName()).append(", SourceMethodName = ").append(record.getSourceMethodName()).append("\n").toString();
    }
}
