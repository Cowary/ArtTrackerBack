package org.cowary.arttrackerback.rest.converter;

import lombok.experimental.UtilityClass;
import org.cowary.arttrackerback.entity.ranobe.Ranobe;
import org.cowary.arttrackerback.entity.ranobe.RanobeVolume;
import org.cowary.arttrackerback.rest.dto.RanobeDto;
import org.cowary.arttrackerback.rest.dto.RanobeVolumeDto;

@UtilityClass
public class RanobeDtoConverter {

    public static RanobeDto convert(Ranobe ranobe) {
        return RanobeDto.builder()
                .id(ranobe.getId())
                .originalTitle(ranobe.getOriginalTitle())
                .title(ranobe.getTitle())
                .volumes(ranobe.getVolumes())
                .score(ranobe.getScore())
                .releaseDate(ranobe.getReleaseDate())
                .releaseYear(ranobe.getReleaseYear())
                .shikiId(ranobe.getShikiId())
                .lastUpd(ranobe.getLastUpd())
                .usrId(ranobe.getUsrId())
                .build();
    }

    public static Ranobe convert(RanobeDto ranobeDto) {
        return Ranobe.builder()
                .id(ranobeDto.getId())
                .originalTitle(ranobeDto.getOriginalTitle())
                .title(ranobeDto.getTitle())
                .volumes(ranobeDto.getVolumes())
                .score(ranobeDto.getScore())
                .releaseDate(ranobeDto.getReleaseDate())
                .releaseYear(ranobeDto.getReleaseYear())
                .shikiId(ranobeDto.getShikiId())
                .lastUpd(ranobeDto.getLastUpd())
                .usrId(ranobeDto.getUsrId())
                .build();
    }

    public static RanobeVolumeDto convert(RanobeVolume ranobeVolume) {
        return RanobeVolumeDto.builder()
                .id(ranobeVolume.getId())
                .title(ranobeVolume.getTitle())
                .number(ranobeVolume.getNumber())
                .status(ranobeVolume.getStatus())
                .score(ranobeVolume.getScore())
                .endDate(ranobeVolume.getEndDate())
                .ranobe(convert(ranobeVolume.getRanobe()))
                .usrId(ranobeVolume.getUsrId())
                .build();
    }

    public static RanobeVolume convert(RanobeVolumeDto ranobeVolumeDto) {
        return RanobeVolume.builder()
                .id(ranobeVolumeDto.getId())
                .title(ranobeVolumeDto.getTitle())
                .number(ranobeVolumeDto.getNumber())
                .status(ranobeVolumeDto.getStatus())
                .score(ranobeVolumeDto.getScore())
                .endDate(ranobeVolumeDto.getEndDate())
                .ranobe(convert(ranobeVolumeDto.getRanobe()))
                .usrId(ranobeVolumeDto.getUsrId())
                .build();
    }
}
