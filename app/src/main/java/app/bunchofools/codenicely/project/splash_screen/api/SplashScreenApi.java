package app.bunchofools.codenicely.project.splash_screen.api;

import app.bunchofools.codenicely.project.helper.Urls;
import app.bunchofools.codenicely.project.splash_screen.model.data.SplashScreenData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by meghal on 23/10/16.
 */

public interface SplashScreenApi {


    @GET(Urls.SUB_URL_SPLASH_SCREEN)
    Call<SplashScreenData> sendFcm(@Query("fcm") String fcm);

}
