package org.cowary.arttrackerback.entity.api.mediaRs;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.cowary.arttrackerback.entity.ranobe.Ranobe;
import org.cowary.arttrackerback.rest.dto.RanobeDto;

@Data
@AllArgsConstructor
public class RanobeRs implements MediaRs {

    private RanobeDto ranobe;
    private String posterUrl;


}
