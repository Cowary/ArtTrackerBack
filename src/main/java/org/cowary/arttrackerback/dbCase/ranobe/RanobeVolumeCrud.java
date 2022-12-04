package org.cowary.arttrackerback.dbCase.ranobe;

import org.cowary.arttrackerback.dbCase.UserService;
import org.cowary.arttrackerback.entity.ranobe.Ranobe;
import org.cowary.arttrackerback.entity.ranobe.RanobeVolume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cowary.arttrackerback.repo.ranobe.RanobeVolumeRepo;
import org.cowary.arttrackerback.util.DateUtil;

import java.util.List;

@Component
public class RanobeVolumeCrud {

    @Autowired
    RanobeVolumeRepo ranobeVolumeRepo;
    @Autowired
    RanobeCrud ranobeCrud;
    @Autowired
    UserService userService;

    public void save(RanobeVolume ranobeVolume, Ranobe ranobe) {
        ranobeVolume.setLastUpd(DateUtil.now());
        ranobe.setLastUpd(DateUtil.now());
        ranobeCrud.save(ranobe);
        ranobeVolume.setRanobeId(ranobe.getId());
        ranobeVolume.setUsrId(userService.getIdCurrentUser());
        ranobeVolumeRepo.save(ranobeVolume);
    }

    public List<RanobeVolume> getAll(String status) {
        List<RanobeVolume> ranobeVolumes;
        long userId = userService.getIdCurrentUser();
        if(status.equals("")) ranobeVolumes = ranobeVolumeRepo.findAllByUsrId(userId);
        else ranobeVolumes = ranobeVolumeRepo.findAllByStatus(status);

        fill(ranobeVolumes);
        return ranobeVolumes;
    }

    public RanobeVolume getById(long id) {
        return ranobeVolumeRepo.findById(id).orElseThrow();
    }

    public void delete(RanobeVolume ranobeVolume) {
        ranobeVolumeRepo.delete(ranobeVolume);
    }

    private void fill(List<RanobeVolume> volumes) {
        for (RanobeVolume ranobeVolume : volumes) {
            Ranobe ranobe = ranobeCrud.findById(ranobeVolume.getRanobeId());
            ranobeVolume.setCommonField(ranobe);
        }
    }
}
