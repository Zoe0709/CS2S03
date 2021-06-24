/**
 * This is a customize exception
 */

class BadCommandException extends RuntimeException {
    BadCommandException(String message) {
        super(message);
    }
}
