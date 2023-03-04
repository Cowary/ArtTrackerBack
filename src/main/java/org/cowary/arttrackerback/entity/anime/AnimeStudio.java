package org.cowary.arttrackerback.entity.anime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Getter
@Setter
@ToString

@Entity(name = "anime_studio")
public class AnimeStudio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long animeId;
    private Long studioId;


    public AnimeStudio(Long animeId, Long studioId) {
        this.animeId = animeId;
        this.studioId = studioId;
    }

    public AnimeStudio() {
    }

}
