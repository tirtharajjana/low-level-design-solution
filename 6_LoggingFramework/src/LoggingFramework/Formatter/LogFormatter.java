package LoggingFramework.Formatter;

import LoggingFramework.LogMessage;

public interface LogFormatter {
    String format(LogMessage logMessage);
}
