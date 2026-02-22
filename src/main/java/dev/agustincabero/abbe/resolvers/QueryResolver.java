package dev.agustincabero.abbe.resolvers;

import dev.agustincabero.abbe.dtos.BrandDto;
import dev.agustincabero.abbe.dtos.CountryDto;
import dev.agustincabero.abbe.dtos.MarketDto;
import dev.agustincabero.abbe.dtos.MarketGroupDto;
import dev.agustincabero.abbe.exceptions.InvalidUuidFormatException;
import dev.agustincabero.abbe.services.BrandService;
import dev.agustincabero.abbe.services.CountryService;
import dev.agustincabero.abbe.services.MarketGroupService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class QueryResolver {
    private final BrandService brandService;
    private final CountryService countryService;
    private final MarketGroupService marketGroupService;

    public QueryResolver(BrandService brandService, CountryService countryService, MarketGroupService marketGroupService) {
        this.brandService = brandService;
        this.countryService = countryService;
        this.marketGroupService = marketGroupService;
    }

    @QueryMapping
    public List<BrandDto> findAllBrands() {
        return brandService.findAllBrands();
    }

    @QueryMapping
    public List<CountryDto> findAllCountries() {
        return countryService.findAllCountries();
    }

    @QueryMapping
    public List<MarketGroupDto> findAllMarketGroups(@Argument UUID brandPublicId) {
        return marketGroupService.findAllMarketGroupsByBrand(brandPublicId);
    }

    @QueryMapping
    public List<MarketDto> findAllMarketsByMarketGroup(@Argument UUID marketGroupPublicId) {
        return marketGroupService.findAllMarketsByMarketGroup(marketGroupPublicId);
    }
}

