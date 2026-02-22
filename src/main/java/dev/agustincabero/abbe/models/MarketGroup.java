package dev.agustincabero.abbe.models;

import dev.agustincabero.abbe.abstractions.BrandedEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class MarketGroup extends BrandedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public MarketGroup(UUID brandPublicId, String name) {
        super(brandPublicId, name);
    }

    protected MarketGroup() {}
}
