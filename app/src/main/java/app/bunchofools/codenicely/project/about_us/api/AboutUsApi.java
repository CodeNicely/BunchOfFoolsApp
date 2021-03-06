package app.bunchofools.codenicely.project.about_us.api;

import app.bunchofools.codenicely.project.about_us.model.data.AboutUsData;
import app.bunchofools.codenicely.project.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by meghal on 13/10/16.
 */

public interface AboutUsApi {


    @GET(Urls.SUB_URL_ABOUT_US)
    Call<AboutUsData> getAboutUsData();


}
