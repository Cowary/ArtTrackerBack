package org.cowary.arttrackerback.dbCase.tv;


import org.cowary.arttrackerback.dbCase.MediaCrud;
import org.cowary.arttrackerback.dbCase.UserService;
import org.cowary.arttrackerback.entity.tv.Tv;
import org.cowary.arttrackerback.entity.tv.TvSeason;
import org.cowary.arttrackerback.repo.tv.TvSeasonsRepo;
import org.cowary.arttrackerback.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        tvSeason.setLastUpd(DateUtil.now());
        tv.setLastUpd(DateUtil.now());
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

        fill(tvSeasons);
        return tvSeasons;
    }

    private void fill(List<TvSeason> tvSeasons) {
        for(TvSeason tvSeason : tvSeasons) {
            Tv tv = tvCrud.findById(tvSeason.getTvId());
            tvSeason.setCommonField(tv);
        }
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
