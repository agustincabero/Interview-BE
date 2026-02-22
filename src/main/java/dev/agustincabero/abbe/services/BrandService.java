package dev.agustincabero.abbe.services;

import dev.agustincabero.abbe.dtos.BrandDto;
import dev.agustincabero.abbe.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandService {

    private final BrandRepository brandRepository;

    public List<BrandDto> findAllBrands() {
        return brandRepository.findAllByDeletedFalse()
                .map(BrandDto::from)
                .toList();
    }
}