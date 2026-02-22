package dev.agustincabero.abbe.dtos;

import dev.agustincabero.abbe.models.Country;

import java.util.UUID;

public record CountryDto(
        UUID publicId,
        String name
) {
    public static CountryDto from(Country country) {
        return new CountryDto(country.getPublicId(), country.getName());
    }
}
