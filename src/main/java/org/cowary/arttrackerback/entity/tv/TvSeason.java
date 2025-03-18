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

@Entity(name = "tv_seasons")
public class TvSeason extends Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer number;
    private Integer episodes;
    private Integer episodesEnd;
    private String status;
    private Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate lastUpd;
    @ManyToOne()
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "tv_id")
    private Tv tv;
    private Long usrId;
    @Transient
    private String type = "Tv";
}
