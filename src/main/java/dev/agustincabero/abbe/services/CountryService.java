package dev.agustincabero.abbe.services;

import dev.agustincabero.abbe.dtos.CountryDto;
import dev.agustincabero.abbe.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CountryService {

    private final CountryRepository countryRepository;

    public List<CountryDto> findAllCountries() {
        return countryRepository.findAllByDeletedFalseOrderByNameAsc()
                .map(CountryDto::from)
                .toList();
    }
}
