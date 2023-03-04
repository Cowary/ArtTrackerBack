package org.cowary.arttrackerback.entity.ranobe;


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

@Entity(name = "ranobe_role")
public class RanobeRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameEn;
    private String nameRu;
    private Long ranobeId;
    private Long personId;

    public RanobeRole(String nameEn, String nameRu, Long ranobeId, Long personId) {
        this.nameEn = nameEn;
        this.nameRu = nameRu;
        this.ranobeId = ranobeId;
        this.personId = personId;
    }

    public RanobeRole() {
    }
}
