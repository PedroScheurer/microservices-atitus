package br.edu.atitus.currencyservice.controllers;

import br.edu.atitus.currencyservice.dtos.CurrencyDTO;
import br.edu.atitus.currencyservice.entities.CurrencyEntity;
import br.edu.atitus.currencyservice.infrastructure.exceptions.CurrencyNotFoundException;
import br.edu.atitus.currencyservice.repositories.CurrencyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyRepository repository;

    public CurrencyController(CurrencyRepository repository) {
        this.repository = repository;
    }


    @GetMapping(path = "/convert",params = {"source", "target"})
    public ResponseEntity<CurrencyDTO> findBySourceAndTarget(@RequestParam String source,
                                                            @RequestParam String target){
        source = source.toUpperCase();
        target = target.toUpperCase();

        CurrencyEntity currency = repository.findBySourceCurrencyAndTargetCurrency(source, target)
                .orElseThrow(() -> new CurrencyNotFoundException("Currency not found."));

        CurrencyDTO dto = new CurrencyDTO(
                currency.getSourceCurrency(),
                currency.getTargetCurrency(),
                currency.getConversionRate(),
                "No enviroment"
        );

        return ResponseEntity.ok().body(dto);
    }
}
