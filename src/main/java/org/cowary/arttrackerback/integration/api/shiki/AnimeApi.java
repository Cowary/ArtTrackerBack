package org.cowary.arttrackerback.integration.api.shiki;

import org.cowary.arttrackerback.integration.api.kin.TitleApi;
import org.cowary.arttrackerback.integration.model.shiki.AnimeModel;

import java.util.List;

public class AnimeApi extends TitleApi {

    public List<AnimeModel> searchByName(String keyword) {
        return List.of(super.searchByName(keyword, "URL_ANIME", AnimeModel[].class));
    }

    public AnimeModel getById(int id) {
        AnimeModel model = super.getById(id, "URL_ANIME", AnimeModel.class);
        model.setRoleModels(super.getRoleById(id, "URL_ANIME"));

        return model;
    }
}
