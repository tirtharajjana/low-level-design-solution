package LoggingFramework.Appender;

import LoggingFramework.LogMessage;

import java.util.logging.LogRecord;

public interface LogAppender {
    void append(LogMessage logMessage);
}
