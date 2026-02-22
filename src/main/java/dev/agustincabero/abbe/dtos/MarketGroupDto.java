package dev.agustincabero.abbe.dtos;

import dev.agustincabero.abbe.models.MarketGroup;

import java.util.UUID;

public record MarketGroupDto(
        UUID publicId,
        String name
) {
    public static MarketGroupDto from(MarketGroup marketGroup) {
        return new MarketGroupDto(marketGroup.getPublicId(), marketGroup.getName());
    }
}
