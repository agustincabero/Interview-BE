package dev.agustincabero.abbe.repositories;

import dev.agustincabero.abbe.abstractions.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@NoRepositoryBean
public interface BaseEntityRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

    //Optional<T> findByPublicId(UUID publicId);

    Optional<T> findByPublicIdAndDeletedFalse(UUID publicId);

    Stream<T> findAllByDeletedFalseOrderByNameAsc();

    //Stream<T> findByNameAndDeletedFalse(String name);

    //Stream<T> findAllByNameContainingIgnoreCaseAndDeletedFalse(String name);
}
