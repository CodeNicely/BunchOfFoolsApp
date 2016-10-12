package app.bunchoffools.codenicely.bunchoffools.about_us.model;

import app.bunchoffools.codenicely.bunchoffools.about_us.AboutUsCallBack;
import app.bunchoffools.codenicely.bunchoffools.about_us.api.AboutUsApi;
import app.bunchoffools.codenicely.bunchoffools.about_us.model.data.AboutUsData;
import app.bunchoffools.codenicely.bunchoffools.helper.Urls;
import app.bunchoffools.codenicely.bunchoffools.join_us.api.JoinUsApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by meghal on 13/10/16.
 */

public class RetrofitAboutUsProvider implements AboutUsProvider {

    AboutUsApi aboutUsApi;

    public RetrofitAboutUsProvider(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        aboutUsApi = retrofit.create(AboutUsApi.class);

    }


    @Override
    public void requestAboutUs(final AboutUsCallBack aboutUsCallBack) {

        Call<AboutUsData> aboutUsDataCall=aboutUsApi.getAboutUsData();

        aboutUsDataCall.enqueue(new Callback<AboutUsData>() {
            @Override
            public void onResponse(Call<AboutUsData> call, Response<AboutUsData> response) {

                aboutUsCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<AboutUsData> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }
}
