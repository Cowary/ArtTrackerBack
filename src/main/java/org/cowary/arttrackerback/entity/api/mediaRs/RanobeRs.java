package org.cowary.arttrackerback.entity.api.mediaRs;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.cowary.arttrackerback.entity.ranobe.Ranobe;

@Data
@AllArgsConstructor
public class RanobeRs implements MediaRs {

    private Ranobe ranobe;
    private String posterUrl;


}
