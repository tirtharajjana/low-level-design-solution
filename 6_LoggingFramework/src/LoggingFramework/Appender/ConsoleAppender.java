package LoggingFramework.Appender;

import LoggingFramework.Formatter.LogFormatter;
import LoggingFramework.LogMessage;

public class ConsoleAppender implements LogAppender{
    private final LogFormatter formatter;

    public ConsoleAppender(LogFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void append(LogMessage logMessage) {
        System.out.println(formatter.format(logMessage));
    }
}
