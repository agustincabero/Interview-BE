package dev.agustincabero.abbe.dtos;

import dev.agustincabero.abbe.models.Brand;

import java.util.UUID;

public record BrandDto(
        UUID publicId,
        String name
) {
    public static BrandDto from(Brand brand) {
        return new BrandDto(brand.getPublicId(), brand.getName());
    }
}

// public Class BrandDto {
//     private UUID publicId;
//     private String name;
//
//     // Getters and setters
//
//     public UUID getPublicId() {
//         return publicId;
//     }
//     public void setPublicId(UUID publicId) {
//         this.publicId = publicId;
//     }
//
//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }
//
//     public static BrandDto from(Brand brand) {
//         BrandDto dto = new BrandDto();
//         dto.setPublicId(brand.getPublicId());
//         dto.setName(brand.getName());
//         return dto;
//     }
// }
