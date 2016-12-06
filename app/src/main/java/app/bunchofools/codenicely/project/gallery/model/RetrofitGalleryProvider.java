package app.bunchofools.codenicely.project.gallery.model;

import app.bunchofools.codenicely.project.gallery.GalleryCallback;
import app.bunchofools.codenicely.project.gallery.api.GalleryApi;
import app.bunchofools.codenicely.project.gallery.model.data.GalleryData;
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
 * Created by meghal on 13/10/16.
 */

public class RetrofitGalleryProvider implements GalleryProvider {

    private GalleryApi galleryApi;
    private Call<GalleryData> galleryDataCall;
    public RetrofitGalleryProvider(){

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

        galleryApi=retrofit.create(GalleryApi.class);
    }

    @Override
    public void getImageUrls(final GalleryCallback galleryCallback) {

        galleryDataCall=galleryApi.getImageUrls();
        galleryDataCall.enqueue(new Callback<GalleryData>() {
            @Override
            public void onResponse(Call<GalleryData> call, Response<GalleryData> response) {

                galleryCallback.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<GalleryData> call, Throwable t) {


                galleryCallback.onFailure();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onDestroy() {
        galleryDataCall.cancel();
    }
}
