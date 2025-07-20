package LoggingFramework;

import LoggingFramework.Appender.LogAppender;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Logger {
    private LogLevel minLogLevel;
    private final List<LogAppender> appenders;
    private final ExecutorService executor;

    public Logger(LogLevel minLogLevel, List<LogAppender> appenders) {
        this.minLogLevel = minLogLevel;
        this.appenders = appenders;
        this.executor = Executors.newSingleThreadExecutor();
    }

    public void log(LogLevel logLevel, String message) {
        if(!logLevel.isAsSevereAs(minLogLevel)){
            return;
        }

        LogMessage logMessage = new LogMessage(logLevel, message);
        executor.submit(() -> appenders.forEach(a -> a.append(logMessage)));
    }

    public void setMinLevel(LogLevel minLevel) {
        this.minLogLevel = minLevel;
    }

    public void shutdown() {
        executor.shutdown();
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }

}
