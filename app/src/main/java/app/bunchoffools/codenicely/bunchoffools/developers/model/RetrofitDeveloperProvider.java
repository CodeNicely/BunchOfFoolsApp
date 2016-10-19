package app.bunchoffools.codenicely.bunchoffools.developers.model;

import app.bunchoffools.codenicely.bunchoffools.contact_us.model.RetrofitContactUsProvider;
import app.bunchoffools.codenicely.bunchoffools.developers.DevelopersCallback;
import app.bunchoffools.codenicely.bunchoffools.developers.api.DevelopersApi;
import app.bunchoffools.codenicely.bunchoffools.developers.model.data.DeveloperData;
import app.bunchoffools.codenicely.bunchoffools.helper.Urls;
import app.bunchoffools.codenicely.bunchoffools.home.model.RetrofitCache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static app.bunchoffools.codenicely.bunchoffools.home.model.RetrofitCache.REWRITE_CACHE_CONTROL_INTERCEPTOR;

/**
 * Created by meghal on 17/10/16.
 */

public class RetrofitDeveloperProvider implements DeveloperProvider{


    private DevelopersApi developersApi;
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

        Call<DeveloperData> developersApiCall=developersApi.requestDeveloperData();
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
}
