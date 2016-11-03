package app.bunchofools.codenicely.project.join_us.model;

import app.bunchofools.codenicely.project.helper.Urls;
import app.bunchofools.codenicely.project.join_us.JoinUsCallback;
import app.bunchofools.codenicely.project.join_us.api.JoinUsApi;
import app.bunchofools.codenicely.project.join_us.model.data.JoinUsData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by meghal on 12/10/16.
 */

public class RetrofitJoinUsProvider implements JoinUsProvider {


    private JoinUsApi joinUsApi;

    public RetrofitJoinUsProvider() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        joinUsApi = retrofit.create(JoinUsApi.class);

    }

    @Override
    public void requestJoin(String name, String mobile, String email, final JoinUsCallback joinUsCallback) {

        Call<JoinUsData> joinUsDataCall= joinUsApi.requestJoin(name, mobile, email);

        joinUsDataCall.enqueue(new Callback<JoinUsData>() {
            @Override
            public void onResponse(Call<JoinUsData> call, Response<JoinUsData> response) {


                joinUsCallback.onSuccess(response.body());

            }
            @Override
            public void onFailure(Call<JoinUsData> call, Throwable t) {

                joinUsCallback.onFailure();
                t.printStackTrace();
            }
        });


    }
}
