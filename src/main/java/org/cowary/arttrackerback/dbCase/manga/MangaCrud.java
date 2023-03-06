package org.cowary.arttrackerback.dbCase.manga;

import org.cowary.arttrackerback.dbCase.UserService;
import org.cowary.arttrackerback.entity.manga.Manga;
import org.cowary.arttrackerback.repo.manga.MangaRepo;
import org.cowary.arttrackerback.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MangaCrud {

    @Autowired
    MangaRepo mangaRepo;
    @Autowired
    UserService userService;

    public List<Manga> findAll(String status) {
        long userId = userService.getIdCurrentUser();
        if(status.equals("")) return mangaRepo.findAllByUsrId(userId);
        else return mangaRepo.findAllByStatus(status);
    }

    public List<Manga> findAllByUserId(long userId) {
        return mangaRepo.findAllByUsrId(userId);
    }

    public Manga findByUserId(long userId) {
        return mangaRepo.findByUsrId(userId);
    }

    public void save(Manga manga) {
        manga.setLastUpd(DateUtil.now());
        manga.setUsrId(userService.getIdCurrentUser());
        mangaRepo.save(manga);
    }
}
