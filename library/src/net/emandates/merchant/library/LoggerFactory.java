package net.emandates.merchant.library;

import net.emandates.merchant.library.release.ReleaseInfo;

class LoggerFactory implements ILoggerFactory {
    @Override
    public ILogger Create() {
        return new Logger(ReleaseInfo.getReleaseInfo()
                                     .getVersion());
    }
}
