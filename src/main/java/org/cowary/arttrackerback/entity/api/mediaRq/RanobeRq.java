package org.cowary.arttrackerback.entity.api.mediaRq;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.cowary.arttrackerback.entity.ranobe.Ranobe;
import org.cowary.arttrackerback.entity.ranobe.RanobeVolume;

@Data
@AllArgsConstructor
public class RanobeRq {

    private Ranobe ranobe;
    private RanobeVolume ranobeVolume;
}
