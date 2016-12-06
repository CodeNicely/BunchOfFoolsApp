package app.bunchofools.codenicely.project.splash_screen.model;

import app.bunchofools.codenicely.project.helper.Urls;
import app.bunchofools.codenicely.project.home.model.RetrofitCache;
import app.bunchofools.codenicely.project.splash_screen.SplashScreenCallback;
import app.bunchofools.codenicely.project.splash_screen.api.SplashScreenApi;
import app.bunchofools.codenicely.project.splash_screen.model.data.SplashScreenData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static app.bunchofools.codenicely.project.home.model.RetrofitCache.REWRITE_CACHE_CONTROL_INTERCEPTOR;

/**
 * Created by meghal on 23/10/16.
 */

public class RetrofitSplashScreenProvider implements SplashScreenProvider {

    private SplashScreenApi splashScreenApi;

    public RetrofitSplashScreenProvider(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR).cache(RetrofitCache.provideCache()).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        splashScreenApi=retrofit.create(SplashScreenApi.class);
    }

    @Override
    public void sendFcm(String fcm, final SplashScreenCallback splashScreenCallback) {

        Call<SplashScreenData> splashScreenDataCall=splashScreenApi.sendFcm(fcm);
        splashScreenDataCall.enqueue(new Callback<SplashScreenData>() {
            @Override
            public void onResponse(Call<SplashScreenData> call, Response<SplashScreenData> response) {
                splashScreenCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SplashScreenData> call, Throwable t) {
                splashScreenCallback.onFailed();
            }
        });

    }
}
