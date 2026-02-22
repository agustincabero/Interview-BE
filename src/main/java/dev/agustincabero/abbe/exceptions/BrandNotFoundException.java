package dev.agustincabero.abbe.exceptions;

import java.util.UUID;

public class BrandNotFoundException extends RuntimeException {

    public BrandNotFoundException(UUID brandPublicId) {
        super("Brand not found with publicId: " + brandPublicId);
    }
}
