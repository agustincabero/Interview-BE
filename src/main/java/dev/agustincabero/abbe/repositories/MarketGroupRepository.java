package dev.agustincabero.abbe.repositories;

import dev.agustincabero.abbe.models.MarketGroup;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MarketGroupRepository extends BrandedEntityRepository<MarketGroup> {
    boolean existsByBrandPublicIdAndNameAndDeletedFalse(UUID brandPublicId, String name);
}
