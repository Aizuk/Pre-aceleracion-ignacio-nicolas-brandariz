package com.alkemy.challengeingreso.challengeingreso.controller;

import com.alkemy.challengeingreso.challengeingreso.dto.ChracterBasicDTO;
import com.alkemy.challengeingreso.challengeingreso.dto.ChracterDTO;
import com.alkemy.challengeingreso.challengeingreso.dto.FilmDTO;
import com.alkemy.challengeingreso.challengeingreso.service.ChracterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
public class ChracterController implements Serializable {

    @Autowired
    private ChracterService chracterService;

    @PostMapping
    public ResponseEntity<ChracterDTO> save(@RequestBody ChracterDTO chracter){
        ChracterDTO chracterSaved = chracterService.save(chracter);
        return ResponseEntity.status(HttpStatus.CREATED).body(chracterSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChracterDTO> update(@PathVariable Long id, @RequestBody ChracterDTO chracter){
        ChracterDTO chracterUpdated = chracterService.update(id, chracter);
        return ResponseEntity.ok().body(chracterUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.chracterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChracterDTO> getChracter(@PathVariable Long id){
        ChracterDTO detailedChracter = chracterService.getChracter(id);
        return ResponseEntity.ok().body(detailedChracter);
    }

    @GetMapping
    public ResponseEntity<List<ChracterBasicDTO>> getChracterByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Set<Long> filmsId
    ) {
        List<ChracterBasicDTO> chracters = chracterService.getByFilters(name,age,filmsId);
        return ResponseEntity.ok().body(chracters);
    }

    @PutMapping("/addFilm/{id}")
    public ResponseEntity<ChracterDTO> addFilm(@PathVariable Long id, @RequestParam(required = true) Long filmId){
        ChracterDTO chracterUpdated = chracterService.addFilm(id,filmId);
        return ResponseEntity.ok().body(chracterUpdated);
    }
}
