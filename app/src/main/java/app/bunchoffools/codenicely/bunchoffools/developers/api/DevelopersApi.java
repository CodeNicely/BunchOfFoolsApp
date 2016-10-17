package app.bunchoffools.codenicely.bunchoffools.developers.api;

import app.bunchoffools.codenicely.bunchoffools.developers.model.data.DeveloperData;
import app.bunchoffools.codenicely.bunchoffools.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by meghal on 17/10/16.
 */

public interface DevelopersApi {

    @GET(Urls.SUB_URL_DEVELOPERS)
    Call<DeveloperData> requestDeveloperData();

}
