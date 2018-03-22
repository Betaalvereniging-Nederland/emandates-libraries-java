package net.emandates.merchant.library;

/**
 * Custom Exception used by the Communicator classes
 */
public class CommunicatorException extends Exception {

    /**
     * Constructor that sets exception message
     * @param message the exception message
     */
    public CommunicatorException(String message) {
        super(message);
    }
    
    /**
     * Constructor that sets exception message and the cause
     * @param message the exception message
     * @param cause the cause
     */
    public CommunicatorException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructor that sets exception cause
     * @param cause the cause
     */
    public CommunicatorException(Throwable cause) {
        super(cause);
    }
}
