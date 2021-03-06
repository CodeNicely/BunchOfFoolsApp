package app.bunchofools.codenicely.project.home.model;


import app.bunchofools.codenicely.project.helper.Urls;
import app.bunchofools.codenicely.project.home.HomeCallback;
import app.bunchofools.codenicely.project.home.api.HomeApi;
import app.bunchofools.codenicely.project.home.model.data.HomeData;
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
 * Created by meghal on 15/10/16.
 */

public class RetrofitHomeProvider implements HomeProvider{

    private HomeApi homeApi;
    private Call<HomeData> homeDataCall;
    public RetrofitHomeProvider(){


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

        homeApi=retrofit.create(HomeApi.class);

    }
    @Override
    public void requestHome(final HomeCallback homeCallback) {

        homeDataCall=homeApi.requestHome();
        homeDataCall.enqueue(new Callback<HomeData>() {
            @Override
            public void onResponse(Call<HomeData> call, Response<HomeData> response) {
                homeCallback.onSuccess(response.body());
            }
            @Override
            public void onFailure(Call<HomeData> call, Throwable t) {


                homeCallback.onFailure();
                t.printStackTrace();
            }
        });

    }

    @Override
    public void onDestroy() {

        homeDataCall.cancel();
    }

}
