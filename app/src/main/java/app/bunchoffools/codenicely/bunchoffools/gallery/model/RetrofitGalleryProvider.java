package app.bunchoffools.codenicely.bunchoffools.gallery.model;

import app.bunchoffools.codenicely.bunchoffools.gallery.GalleryCallback;
import app.bunchoffools.codenicely.bunchoffools.gallery.api.GalleryApi;
import app.bunchoffools.codenicely.bunchoffools.gallery.model.data.GalleryData;
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
 * Created by meghal on 13/10/16.
 */

public class RetrofitGalleryProvider implements GalleryProvider {

    private GalleryApi galleryApi;
    public RetrofitGalleryProvider(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        galleryApi=retrofit.create(GalleryApi.class);
    }

    @Override
    public void getImageUrls(final GalleryCallback galleryCallback) {

        Call<GalleryData> galleryDataCall=galleryApi.getImageUrls();
        galleryDataCall.enqueue(new Callback<GalleryData>() {
            @Override
            public void onResponse(Call<GalleryData> call, Response<GalleryData> response) {

                galleryCallback.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<GalleryData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
