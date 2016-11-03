package app.bunchofools.codenicely.project.developers.api;

import app.bunchofools.codenicely.project.developers.model.data.DeveloperData;
import app.bunchofools.codenicely.project.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by meghal on 17/10/16.
 */

public interface DevelopersApi {

    @GET(Urls.SUB_URL_DEVELOPERS)
    Call<DeveloperData> requestDeveloperData();

}
