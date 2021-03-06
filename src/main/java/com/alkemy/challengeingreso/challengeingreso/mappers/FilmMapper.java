package com.alkemy.challengeingreso.challengeingreso.mappers;

import com.alkemy.challengeingreso.challengeingreso.dto.*;
import com.alkemy.challengeingreso.challengeingreso.entities.ChracterEntity;
import com.alkemy.challengeingreso.challengeingreso.entities.FilmEntity;
import com.alkemy.challengeingreso.challengeingreso.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmMapper {

    @Autowired
    private ChracterMapper chracterMapper;
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreService genreService;

    public FilmEntity filmDTO2Entity(FilmDTO dto){
        FilmEntity entity = new FilmEntity();
        entity.setId(dto.getId());
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(
                this.string2LocalDate(dto.getCreationDate())
        );
        entity.setRating(dto.getRating());
        entity.setGenre(genreService.retrieve(dto.getGenre()));
        return entity;
    }

    private LocalDate string2LocalDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(stringDate, formatter);
    }

    public FilmDTO filmEntity2DTO(FilmEntity entity, boolean loadChracters){
        FilmDTO dto = new FilmDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setRating(entity.getRating());
        dto.setGenre(genreMapper.genreEntity2DTO(entity.getGenre()));
        //dto.setGenreId(entity.getGenreId());
        if (loadChracters){
            List<ChracterDTO> chracterDTOS = this.chracterMapper.chracterEntityList2DTOList((entity.getChracters()), false);
            dto.setChracters(chracterDTOS);
        }
        return dto;
    }

    public List<FilmDTO> filmEntityList2DTOList(List<FilmEntity> entities, boolean loadChracters){
        List<FilmDTO> films = new ArrayList<>();
        for (FilmEntity entity: entities) {
            films.add(this.filmEntity2DTO(entity, loadChracters));
        }
        return films;
    }

    public FilmBasicDTO filmEntity2BasicDTO(FilmEntity entity){
        FilmBasicDTO dto = new FilmBasicDTO();
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate().toString());
        return dto;
    }

    public List<FilmBasicDTO> filmEntityList2DTOBasicList(List<FilmEntity> entities){
        List<FilmBasicDTO> dtos = new ArrayList<>();
        for (FilmEntity entity: entities) {
            dtos.add(filmEntity2BasicDTO(entity));
        }
        return dtos;
    }

    public List<FilmEntity> filmDTOList2EntityList(List<FilmDTO> dtos){
        List<FilmEntity> entities = new ArrayList<>();
        for (FilmDTO dto: dtos) {
            entities.add(filmDTO2Entity(dto));
        }
        return entities;
    }
}
