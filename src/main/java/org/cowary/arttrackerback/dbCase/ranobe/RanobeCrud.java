package org.cowary.arttrackerback.dbCase.ranobe;

import org.cowary.arttrackerback.dbCase.MediaCrud;
import org.cowary.arttrackerback.dbCase.UserService;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.cowary.arttrackerback.entity.ranobe.Ranobe;
import org.cowary.arttrackerback.repo.ranobe.RanobeRep;
import org.cowary.arttrackerback.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RanobeCrud  {

    @Autowired
    RanobeRep ranobeRep;
    @Autowired
    UserService userService;

    public List<Ranobe> getAllByUserId(long userId) {
        return ranobeRep.findAllByUsrId(userId);
    }

    public void save(Ranobe ranobe) {
        ranobe.setLastUpd(DateUtil.now());
        ranobeRep.save(ranobe);
    }

    public Ranobe findById(Long id) {
        return ranobeRep.findById(id)
                .orElseThrow();
    }

    public Ranobe findByOriginalTitleAndUserId(String originalTitle ) {
        return ranobeRep.findRanobeByOriginalTitleAndUsrId(originalTitle, userService.getIdCurrentUser());
    }

    public void deleteById(long id) {
        ranobeRep.deleteById(id);
    }
}
