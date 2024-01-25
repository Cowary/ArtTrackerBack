package org.cowary.arttrackerback.entity.tv;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.cowary.arttrackerback.entity.Media;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString

@Entity(name = "tv")
public class Tv extends Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalTitle;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate releaseDate;
    private Integer releaseYear;
    private Integer score;
    private Integer seasons;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate lastUpd;
    private Long usrId;
    @OneToOne()
    @Cascade({CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "tv_integration_id")
    private TvIntegration tvIntegration;
    @Transient
    private String type = "Tv";

    public Tv(String originalTitle, String title, LocalDate releaseDate, Integer releaseYear, Integer seasons) {
        this.originalTitle = originalTitle;
        this.title = title;
        this.releaseDate = releaseDate;
        this.releaseYear = releaseYear;
        this.seasons = seasons;
    }

    public Tv() {
    }
}
