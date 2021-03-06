package com.alkemy.challengeingreso.challengeingreso.controller;

import com.alkemy.challengeingreso.challengeingreso.dto.GenreDTO;
import com.alkemy.challengeingreso.challengeingreso.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAll(){
        List<GenreDTO> genres = genreService.getAllGenres();
        return ResponseEntity.ok().body(genres);
    }

    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO genre){
        GenreDTO genreSaved = genreService.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreSaved);

    }
}
