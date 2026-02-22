package dev.agustincabero.abbe.exceptions;

public class InvalidUuidFormatException extends RuntimeException {

    public InvalidUuidFormatException(String invalidUuid) {
        super("Invalid UUID format: " + invalidUuid);
    }

    public InvalidUuidFormatException(String invalidUuid, Throwable cause) {
        super("Invalid UUID format: " + invalidUuid, cause);
    }
}
