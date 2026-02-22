package dev.agustincabero.abbe.services;

import dev.agustincabero.abbe.dtos.MarketDto;
import dev.agustincabero.abbe.dtos.MarketGroupDto;
import dev.agustincabero.abbe.exceptions.BrandNotFoundException;
import dev.agustincabero.abbe.models.Brand;
import dev.agustincabero.abbe.repositories.BrandRepository;
import dev.agustincabero.abbe.repositories.MarketGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MarketGroupService {
    private final MarketGroupRepository marketGroupRepository;
    private final BrandRepository brandRepository;
    private final MarketService marketService;

    @Transactional
    public boolean deleteMarketGroup(UUID marketGroupPublicId) {
        Optional<dev.agustincabero.abbe.models.MarketGroup> marketGroup = marketGroupRepository.findByPublicIdAndDeletedFalse(marketGroupPublicId);

        if (marketGroup.isEmpty()) {
            throw new IllegalArgumentException("Market group not found");
        }

        marketGroup.get().setDeleted(true);
        marketGroupRepository.save(marketGroup.get());
        marketService.deleteAllByMarketGroupPublicId(marketGroupPublicId);

        return true;
    }

    public List<MarketGroupDto> findAllMarketGroupsByBrand(UUID brandPublicId) {
        // Validate that the brand exists
        brandRepository.findByPublicIdAndDeletedFalse(brandPublicId)
                .orElseThrow(() -> new BrandNotFoundException(brandPublicId));

        // Fetch market groups for the brand
        return marketGroupRepository.findAllByBrandPublicIdAndDeletedFalse(brandPublicId)
                .stream()
                .map(MarketGroupDto::from)
                .toList();
    }

    public boolean createMarketGroup(String name, UUID brandPublicId) {
        // Validate that the brand exists
        Optional<Brand> brand = brandRepository.findByPublicIdAndDeletedFalse(brandPublicId);

        if (brand.isEmpty()) {
            throw new BrandNotFoundException(brandPublicId);
        }

        // Check for duplicate market group name within the same brand
        if (marketGroupRepository.existsByBrandPublicIdAndNameAndDeletedFalse(brand.get().getPublicId(), name)) {
            throw new IllegalArgumentException("Market group with the same name already exists for this brand");
        }

        // Create and save the new market group
        var marketGroup = new dev.agustincabero.abbe.models.MarketGroup(brand.get().getPublicId(), name);
        marketGroupRepository.save(marketGroup);

        return true;
    }

    public List<MarketDto> findAllMarketsByMarketGroup(UUID marketGroupPublicId) {
        return marketService.findAllByMarketGroupPublicIdAndDeletedFalse(marketGroupPublicId);
    }
}
