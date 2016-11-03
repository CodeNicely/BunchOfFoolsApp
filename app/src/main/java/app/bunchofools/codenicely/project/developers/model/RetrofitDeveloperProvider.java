package app.bunchofools.codenicely.project.developers.model;

import app.bunchofools.codenicely.project.developers.DevelopersCallback;
import app.bunchofools.codenicely.project.developers.api.DevelopersApi;
import app.bunchofools.codenicely.project.developers.model.data.DeveloperData;
import app.bunchofools.codenicely.project.helper.Urls;
import app.bunchofools.codenicely.project.home.model.RetrofitCache;
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
 * Created by meghal on 17/10/16.
 */

public class RetrofitDeveloperProvider implements DeveloperProvider{


    private DevelopersApi developersApi;
    private Call<DeveloperData> developersApiCall;
    public RetrofitDeveloperProvider(){

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
        developersApi=retrofit.create(DevelopersApi.class);
    }

    @Override
    public void requestDevelopersData(final DevelopersCallback developersCallback) {

        developersApiCall=developersApi.requestDeveloperData();
        developersApiCall.enqueue(new Callback<DeveloperData>() {
            @Override
            public void onResponse(Call<DeveloperData> call, Response<DeveloperData> response) {

                developersCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<DeveloperData> call, Throwable t) {
                developersCallback.onFailed();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onDestroy() {

    developersApiCall.cancel();
    }
}
