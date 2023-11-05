package org.cowary.arttrackerback.dbCase.tv;

import org.cowary.arttrackerback.dbCase.MediaCrud;
import org.cowary.arttrackerback.dbCase.UserService;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.cowary.arttrackerback.entity.tv.Tv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cowary.arttrackerback.repo.tv.TvRepo;
import org.cowary.arttrackerback.util.DateUtil;

import java.util.List;

@Component
public class TvCrud  {

    @Autowired
    TvRepo tvRepo;
    @Autowired
    UserService userService;

    public List<Tv> getAllByUserId(long userId) {
        return tvRepo.findAllByUsrId(userId);
    }

    public Tv findById(long id) {
        return tvRepo.findById(id).orElseThrow();
    }

    public Tv findByOriginalTitle(String originalTitle) {
        return tvRepo.findByOriginalTitle(originalTitle).orElse(null);
    }

    public void save(Tv tv) {
        tv.setLastUpd(DateUtil.now());
        tvRepo.save(tv);
    }

    public void delete(Tv tv) {
        tvRepo.delete(tv);
    }

    public void deleteById(long id) {
        tvRepo.deleteById(id);
    }
}
