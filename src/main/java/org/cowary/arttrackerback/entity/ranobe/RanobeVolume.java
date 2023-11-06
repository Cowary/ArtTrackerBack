package org.cowary.arttrackerback.entity.ranobe;

import org.cowary.arttrackerback.entity.Media;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString

@Entity(name = "ranobe_volume")
public class RanobeVolume extends Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer chapters;
    private Integer chaptersEnd;
    private String status;
    private Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date lastUpd;
    private Long ranobeId;
    private Long usrId;
    @Transient
    private String originalTitle;
    @Transient
    private Integer volumes;
    @Transient
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Transient
    private Integer releaseYear;
    @Transient
    private Integer volumesEnd;
    @Transient
    private String type = "Ranobe";

    public void setCommonField(Ranobe ranobe) {
        originalTitle = ranobe.getOriginalTitle();
        volumes = ranobe.getVolumes();
        releaseDate = ranobe.getReleaseDate();
        releaseYear = ranobe.getReleaseYear();
        volumesEnd = ranobe.getVolumesEnd();
    }
}
