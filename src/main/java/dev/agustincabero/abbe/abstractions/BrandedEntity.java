package dev.agustincabero.abbe.abstractions;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class BrandedEntity extends BaseEntity {
    private UUID brandPublicId;

    protected BrandedEntity() {} // For JPA

    public BrandedEntity(UUID brandPublicId, String name) {
        super(name);
        this.brandPublicId = brandPublicId;
    }
}
