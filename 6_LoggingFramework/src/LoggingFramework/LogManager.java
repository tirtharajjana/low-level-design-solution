package LoggingFramework;

import LoggingFramework.Appender.ConsoleAppender;
import LoggingFramework.Appender.FileAppender;
import LoggingFramework.Appender.LogAppender;
import LoggingFramework.Formatter.SimpleFormatter;

import java.util.ArrayList;
import java.util.List;

public class LogManager {
    public static volatile Logger logger;

    public static Logger getLogger() {
        if(logger == null) {
            synchronized (LogManager.class) {
                if(logger == null) {
                    logger = new LoggerBuilder().setLevel(LogLevel.DEBUG)
                            .addAppender(new ConsoleAppender(new SimpleFormatter()))
                            .addAppender(new FileAppender("log.txt", new SimpleFormatter()))
                            .build();
                }
            }
        }

        return logger;
    }

    public static class LoggerBuilder{
        private LogLevel level;
        private final List<LogAppender>  appenders = new ArrayList<>();

        public LoggerBuilder setLevel(LogLevel level){
            this.level = level;
            return this;
        }

        public LoggerBuilder addAppender(LogAppender appender) {
            this.appenders.add(appender);
            return this;
        }

        public Logger build() {
            return new Logger(level, appenders);
        }
    }
}
