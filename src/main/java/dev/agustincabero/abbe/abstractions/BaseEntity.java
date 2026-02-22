package dev.agustincabero.abbe.abstractions;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class BaseEntity implements SoftDeletable, HasPublicId, HasName {
    private final UUID publicId = UUID.randomUUID();
    private final Instant created = Instant.now();
    private Instant updated;
    @Setter
    private boolean deleted = false;
    @NotBlank
    @Column(nullable = false)
    private String name;
    @Version
    private long version = 0L;

    protected BaseEntity() {} // For JPA

    public BaseEntity(String name) {
        this.name = name;
    }

    @PreUpdate
    protected void setUpdatedTimestamp() {
        this.updated = Instant.now();
    }
}
