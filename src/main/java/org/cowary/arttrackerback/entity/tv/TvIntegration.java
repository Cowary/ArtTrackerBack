package org.cowary.arttrackerback.entity.tv;

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

@Entity(name = "tv_integration")
public class TvIntegration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long idTv;
    private Long idIntegration;

    public TvIntegration(String name, Long idTv, Long idIntegration) {
        this.name = name;
        this.idTv = idTv;
        this.idIntegration = idIntegration;
    }

    public TvIntegration() {
    }
}
