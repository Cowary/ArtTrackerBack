package org.cowary.arttrackerback.integration.api.kin;


import org.cowary.arttrackerback.integration.model.shiki.RoleModel;
import org.cowary.arttrackerback.integration.util.ApiUrl;
import org.cowary.arttrackerback.integration.util.RestTemp;

import java.net.URL;

abstract public class TitleApi {

    public TitleApi() {
        restTemp = new RestTemp();
    }

    private final RestTemp restTemp;

    public <T> T searchByName(String keyword, String urlTitle, Class<T> responseType) {
        URL url = new ApiUrl("shiki.properties").appendPathFromFile(urlTitle)
                .addQuery("search", keyword)
                .addQuery("limit", 5)
                .build();

        return restTemp.getBody(url, responseType);
    }

    public <T> T getById(int animeId, String urlTitle, Class<T> responseType) {
        URL urlAnime = new ApiUrl("shiki.properties").appendPathFromFile(urlTitle)
                .appendPath(animeId)
                .build();

        return restTemp.getBody(urlAnime, responseType);
    }

    public RoleModel[] getRoleById(int id, String urlTitle) {
        URL urlRole = new ApiUrl("shiki.properties").appendPathFromFile(urlTitle)
                .appendPath(id)
                .appendPathFromFile("URL_ROLES")
                .build();
        return restTemp.getBody(urlRole, RoleModel[].class);
    }
}

