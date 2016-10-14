package app.bunchoffools.codenicely.bunchoffools.home.model;

import app.bunchoffools.codenicely.bunchoffools.helper.Urls;
import app.bunchoffools.codenicely.bunchoffools.home.HomeCallback;
import app.bunchoffools.codenicely.bunchoffools.home.api.HomeApi;
import app.bunchoffools.codenicely.bunchoffools.home.model.data.HomeData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by meghal on 15/10/16.
 */

public class RetrofitHomeProvider implements HomeProvider{

    private HomeApi homeApi;
    public RetrofitHomeProvider(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

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

        Call<HomeData> homeDataCall=homeApi.requestHome();
        homeDataCall.enqueue(new Callback<HomeData>() {
            @Override
            public void onResponse(Call<HomeData> call, Response<HomeData> response) {
                homeCallback.onSuccess(response.body());
            }
            @Override
            public void onFailure(Call<HomeData> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
