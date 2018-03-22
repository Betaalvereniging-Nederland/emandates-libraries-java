package net.emandates.merchant.library.misc;

import java.util.UUID;

/**
 * Class that automatically generates MessageId's. You may use this to set the MessageId field manually,
 * or you can use the constructors for NewMandateRequest, AmendmentRequest or CancellationRequest to do it automatically.
 */
public class MessageIdGenerator {

    /**
     * @return Returns a string of 16 alphanumeric characters
     */
    public static String New() {
        return Integer.toHexString(UUID.randomUUID().hashCode()) + Integer.toHexString(UUID.randomUUID().hashCode());
    }
}
