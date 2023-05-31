package org.cowary.arttrackerback.integration.api.shiki;

import org.cowary.arttrackerback.integration.api.kin.TitleApi;
import org.cowary.arttrackerback.integration.model.shiki.MangaModel;

import java.util.List;

public class MangaApi extends TitleApi {

    public List<MangaModel> searchByName(String keyword) {
        return List.of(super.searchByName(keyword, "URL_MANGA", MangaModel[].class));
    }

    public MangaModel getById(int id) {
        MangaModel model = super.getById(id, "URL_MANGA", MangaModel.class);
        model.setRoleModels(super.getRoleById(id, "URL_MANGA"));

        return model;
    }
}
