package org.cowary.arttrackerback.dbCase.anime;

import org.cowary.arttrackerback.dbCase.MediaCrud;
import org.cowary.arttrackerback.dbCase.UserService;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cowary.arttrackerback.repo.anime.AnimeRepo;
import org.cowary.arttrackerback.util.DateUtil;

import java.util.List;

@Component
public class AnimeCrud implements MediaCrud<Anime> {

    @Autowired
    AnimeRepo animeRepo;
    @Autowired
    UserService userService;


    public List<Anime> getAllByUserId(long userId) {
        return animeRepo.findAllByUsrId(userId);
    }

    public void save(Anime anime) {
        anime.setLastUpd(DateUtil.now());
        anime.setUsrId(userService.getIdCurrentUser());
        animeRepo.save(anime);
    }

    public Anime getById(long id) {
        return animeRepo.findById(id).orElseThrow();
    }

    public void delete(Anime anime) {
        animeRepo.delete(anime);
    }

    public void deleteById(long id) {
        animeRepo.deleteById(id);
    }

    @Override
    public List<Anime> getAll(long userId, String status) {
        if(status.equals("")) return animeRepo.findAllByUsrId(userId);
        else return animeRepo.findByStatusAndUsrId(status, userId);
    }
}
