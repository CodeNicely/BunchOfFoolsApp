package app.bunchoffools.codenicely.bunchoffools.contact_us.model;

import app.bunchoffools.codenicely.bunchoffools.contact_us.ContactUsCallback;
import app.bunchoffools.codenicely.bunchoffools.contact_us.api.ContactUsApi;
import app.bunchoffools.codenicely.bunchoffools.contact_us.model.data.ContactUsData;
import app.bunchoffools.codenicely.bunchoffools.helper.Urls;
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

public class RetrofitContactUsProvider implements ContactUsProvider {

    private ContactUsApi contactUsApi;
    public RetrofitContactUsProvider(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        contactUsApi=retrofit.create(ContactUsApi.class);

    }
    @Override
    public void requestContactUs(final ContactUsCallback contactUsCallback) {


        Call<ContactUsData> contactUsDataCall=contactUsApi.requestContactUs();
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
}