package org.cowary.arttrackerback.dbCase.manga;

import org.cowary.arttrackerback.dbCase.UserService;
import org.cowary.arttrackerback.entity.manga.Manga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cowary.arttrackerback.repo.manga.MangaRep;
import org.cowary.arttrackerback.util.DateUtil;

import java.util.List;

@Component
public class MangaCrud {

    @Autowired
    MangaRep mangaRep;
    @Autowired
    UserService userService;

    public List<Manga> getAll(String status) {
        long userId = userService.getIdCurrentUser();
        if(status.equals("")) return mangaRep.findAllByUsrId(userId);
        else return mangaRep.findAllByStatus(status);
    }

    public void save(Manga manga) {
        manga.setLastUpd(DateUtil.now());
        manga.setUsrId(userService.getIdCurrentUser());
        mangaRep.save(manga);
    }
}
