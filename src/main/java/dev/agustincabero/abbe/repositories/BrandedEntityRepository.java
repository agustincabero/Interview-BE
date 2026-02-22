package dev.agustincabero.abbe.repositories;

import dev.agustincabero.abbe.abstractions.BrandedEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface BrandedEntityRepository<T extends BrandedEntity> extends BaseEntityRepository<T> {

    List<T> findAllByBrandPublicId(UUID brandPublicId);

    List<T> findAllByBrandPublicIdAndDeletedFalse(UUID brandPublicId);

    List<T> findAllByBrandPublicIdAndNameContainingIgnoreCaseAndDeletedFalse(UUID brandPublicId, String name);
}
