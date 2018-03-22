package net.emandates.merchant.library;


public interface ILogger {
    void Log( Configuration config, String message, Object... args);
    void LogXmlMessage(Configuration config, String content);
}
