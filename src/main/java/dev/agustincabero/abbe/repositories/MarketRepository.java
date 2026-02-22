package dev.agustincabero.abbe.repositories;

import dev.agustincabero.abbe.models.Market;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MarketRepository extends BrandedEntityRepository<Market> {
    List<Market> findAllByMarketGroupPublicIdAndDeletedFalse(UUID marketGroupPublicId);
}
