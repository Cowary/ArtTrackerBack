package org.cowary.arttrackerback.integration.api.shiki;

import org.cowary.arttrackerback.integration.api.kin.TitleApi;
import org.cowary.arttrackerback.integration.model.shiki.RanobeModel;

import java.util.List;


public class RanobeApi extends TitleApi {

    public List<RanobeModel> searchByName(String keyword) {
        return List.of(super.searchByName(keyword, "URL_RANOBE", RanobeModel[].class));
    }

    public RanobeModel getById(int id) {
        RanobeModel model = super.getById(id, "URL_RANOBE", RanobeModel.class);
        model.setRoleModels(super.getRoleById(id, "URL_RANOBE"));

        return model;
    }
}
