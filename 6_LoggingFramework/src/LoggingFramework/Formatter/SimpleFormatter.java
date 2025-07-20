package LoggingFramework.Formatter;

import LoggingFramework.LogMessage;

public class SimpleFormatter implements LogFormatter {
    @Override
    public String format(LogMessage message) {
        return String.format("[%s] [%s] [%s]: %s",
                message.getTimestamp(),
                message.getLevel(),
                message.getThreadName(),
                message.getMessage());
    }
}
