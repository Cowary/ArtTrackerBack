package org.cowary.arttrackerback.dbCase.tv;


import org.cowary.arttrackerback.dbCase.MediaCrud;
import org.cowary.arttrackerback.entity.tv.Tv;
import org.cowary.arttrackerback.entity.tv.TvSeason;
import org.cowary.arttrackerback.repo.tv.TvSeasonsRepo;
import org.cowary.arttrackerback.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TvSeasonsCrud implements MediaCrud<TvSeason> {

    @Autowired
    TvSeasonsRepo tvSeasonsRepo;
    @Autowired
    TvCrud tvCrud;
    @Autowired
    UserService userService;

    public void save(TvSeason tvSeason, Tv tv) {
        System.out.println(tv);
        tvSeason.setLastUpd(LocalDate.now());
        tv.setLastUpd(LocalDate.now());
        tvCrud.save(tv);
        tvSeason.setTvId(tv.getId());
        tvSeason.setUsrId(userService.getIdCurrentUser());
        tvSeasonsRepo.save(tvSeason);
    }

    @Override
    public List<TvSeason> getAll(long userId, String status) {
        List<TvSeason> tvSeasons;
        if(status.equals("")) tvSeasons = tvSeasonsRepo.findAllByUsrId(userId);
        else tvSeasons = tvSeasonsRepo.findAllByStatus(status);

        return tvSeasons;
    }

    public TvSeason getById(long id) {
        return tvSeasonsRepo.findById(id).orElseThrow();
    }

    public long getTvIdById(long id) {
        return getById(id).getTvId();
    }

    public void delete(TvSeason tvSeason) {
        tvSeasonsRepo.delete(tvSeason);
    }
}
