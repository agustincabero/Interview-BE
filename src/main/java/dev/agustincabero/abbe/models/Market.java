package dev.agustincabero.abbe.models;

import dev.agustincabero.abbe.abstractions.BrandedEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class Market extends BrandedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private UUID marketGroupPublicId;
    private UUID countryPublicId;

    public Market(UUID brandPublicId, String name, UUID marketGroupPublicId, UUID countryPublicId) {
        super(brandPublicId, name);
        this.countryPublicId = countryPublicId;
        this.marketGroupPublicId = marketGroupPublicId;
    }

    protected Market() {} // for JPA
}
