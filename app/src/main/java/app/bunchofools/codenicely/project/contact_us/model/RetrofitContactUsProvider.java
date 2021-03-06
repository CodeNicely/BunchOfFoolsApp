package app.bunchofools.codenicely.project.contact_us.model;

import app.bunchofools.codenicely.project.contact_us.ContactUsCallback;
import app.bunchofools.codenicely.project.contact_us.api.ContactUsApi;
import app.bunchofools.codenicely.project.contact_us.model.data.ContactUsData;
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
 * Created by meghal on 15/10/16.
 */

public class RetrofitContactUsProvider implements ContactUsProvider {

    private ContactUsApi contactUsApi;
    private Call<ContactUsData> contactUsDataCall;

    public RetrofitContactUsProvider() {

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

        contactUsApi = retrofit.create(ContactUsApi.class);

    }

    @Override
    public void requestContactUs(final ContactUsCallback contactUsCallback) {


        contactUsDataCall = contactUsApi.requestContactUs();
        contactUsDataCall.enqueue(new Callback<ContactUsData>() {
            @Override
            public void onResponse(Call<ContactUsData> call, Response<ContactUsData> response) {

                contactUsCallback.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<ContactUsData> call, Throwable t) {


                t.printStackTrace();
                contactUsCallback.onFailure();
            }
        });


    }

    @Override
    public void onDestroy() {

        contactUsDataCall.cancel();
    }
}