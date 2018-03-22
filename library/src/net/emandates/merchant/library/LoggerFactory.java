package net.emandates.merchant.library;

class LoggerFactory implements ILoggerFactory {
    @Override
    public ILogger Create() {
        return new Logger();
    }
}
