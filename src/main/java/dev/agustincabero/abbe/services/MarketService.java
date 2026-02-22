package dev.agustincabero.abbe.services;

import dev.agustincabero.abbe.dtos.MarketDto;
import dev.agustincabero.abbe.models.Brand;
import dev.agustincabero.abbe.models.Country;
import dev.agustincabero.abbe.models.Market;
import dev.agustincabero.abbe.models.MarketGroup;
import dev.agustincabero.abbe.repositories.BrandRepository;
import dev.agustincabero.abbe.repositories.CountryRepository;
import dev.agustincabero.abbe.repositories.MarketGroupRepository;
import dev.agustincabero.abbe.repositories.MarketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MarketService {
    private final MarketRepository marketRepository;
    private final MarketGroupRepository marketGroupRepository;
    private final CountryRepository countryRepository;
    private final BrandRepository brandRepository;

    public void deleteAllByMarketGroupPublicId(UUID marketGroupPublicId) {
        List<Market> marketsFromThisMarketGroup = marketRepository.findAllByMarketGroupPublicIdAndDeletedFalse(marketGroupPublicId);
        marketsFromThisMarketGroup.forEach(market -> {
            market.setDeleted(true);
        });

        marketRepository.saveAll(marketsFromThisMarketGroup);
    }

    @Transactional
    public boolean upsertMarkets(UUID marketGroupPublicId, List<UUID> inputCountries) {
        Optional<MarketGroup> marketGroup = marketGroupRepository.findByPublicIdAndDeletedFalse(marketGroupPublicId);
        validateMarketGroup(marketGroup);
        List<Country> allCountries = countryRepository.findAllByDeletedFalseOrderByNameAsc().toList();
        validateCountries(allCountries, inputCountries);
        UUID brandPublicId = marketGroup.get().getBrandPublicId();
        Optional<Brand> brand = brandRepository.findByPublicIdAndDeletedFalse(brandPublicId);

        if (brand.isEmpty()) {
            throw new RuntimeException("Brand not found");
        }

        List<Market> existingMarkets = marketRepository.findAllByMarketGroupPublicIdAndDeletedFalse(marketGroupPublicId);

        softDeleteMarketsNotPresentInNewList(existingMarkets, inputCountries);
        addNewMarkets(existingMarkets, inputCountries, brand.get(), marketGroupPublicId, allCountries);

        marketRepository.saveAll(existingMarkets);
        return true;
    }

    private void addNewMarkets(List<Market> existingMarkets, List<UUID> inputCountries, Brand brand, UUID marketGroupPublicId, List<Country> countriesInDB) {
        // Add new markets for countries that are not already in the existing markets
        inputCountries.forEach(countryPublicId -> {
            boolean exists = existingMarkets.stream()
                    .anyMatch(market -> market.getCountryPublicId().equals(countryPublicId) && !market.isDeleted());

            if (!exists) {
                Market newMarket = new Market(brand.getPublicId(), generateNameForMarket(brand.getName(), countryPublicId, countriesInDB), marketGroupPublicId, countryPublicId);
                marketRepository.save(newMarket);
            }
        });
    }

    private String generateNameForMarket(String brandName, UUID countryPublicId, List<Country> countriesInDB) {
        Optional<Country> country = countriesInDB.stream()
                .filter(c -> c.getPublicId().equals(countryPublicId))
                .findFirst();

        if (country.isEmpty()) {
            throw new RuntimeException("Country not found");
        }

        return brandName + " - " + country.get().getName();
    }

    private void softDeleteMarketsNotPresentInNewList(List<Market> existingMarkets, List<UUID> countries) {
        existingMarkets.forEach(market -> {
            if (!countries.contains(market.getCountryPublicId())) {
                market.setDeleted(true);
            }
        });
    }

    private void validateMarketGroup(Optional<MarketGroup> marketGroup) {
        if (marketGroup.isEmpty()) {
            throw new RuntimeException("Market group not found");
        }
    }

    private void validateCountries(List<Country> allCountries, List<UUID> inputCountries) {
        inputCountries.forEach(country -> {
            boolean exists = allCountries.stream()
                    .anyMatch(c -> c.getPublicId().equals(country));

            if (!exists) {
                throw new RuntimeException("Country with public ID " + country + " not found");
            }
        });
    }

    public List<MarketDto> findAllByMarketGroupPublicIdAndDeletedFalse(UUID marketGroupPublicId) {
        return marketRepository.findAllByMarketGroupPublicIdAndDeletedFalse(marketGroupPublicId)
                .stream()
                .map(MarketDto::from)
                .toList();
    }
}
