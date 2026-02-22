package dev.agustincabero.abbe.dtos;

import dev.agustincabero.abbe.models.Market;

import java.util.UUID;

public record MarketDto(
        UUID publicId,
        String name,
        UUID marketGroupPublicId,
        UUID countryPublicId
) {
    public static MarketDto from(Market market) {
        return new MarketDto(market.getPublicId(), market.getName(), market.getMarketGroupPublicId(), market.getCountryPublicId());
    }
}
