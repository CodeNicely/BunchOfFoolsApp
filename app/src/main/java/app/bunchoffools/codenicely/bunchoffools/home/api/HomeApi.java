package app.bunchoffools.codenicely.bunchoffools.home.api;

import app.bunchoffools.codenicely.bunchoffools.helper.Urls;
import app.bunchoffools.codenicely.bunchoffools.home.model.data.HomeData;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by meghal on 15/10/16.
 */

public interface HomeApi {

    @GET(Urls.SUB_URL_HOME)
    Call<HomeData> requestHome();


}
