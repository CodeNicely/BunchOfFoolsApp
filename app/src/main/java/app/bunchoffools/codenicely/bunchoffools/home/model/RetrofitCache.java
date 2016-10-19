package app.bunchoffools.codenicely.bunchoffools.home.model;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import app.bunchoffools.codenicely.bunchoffools.helper.MyApplication;
import app.bunchoffools.codenicely.bunchoffools.helper.utils.Utils;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by meghal on 19/10/16.
 */

public class RetrofitCache {

    public static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {


            Request request = chain.request();
            if (!Utils.isNetworkAvailable(MyApplication.getContext())) {
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS).build();

                request = request.newBuilder().cacheControl(cacheControl).build();

            }

            return chain.proceed(request);

        }
    };


    public static Cache provideCache(){

        Cache cache=null;
        try {
            cache = new Cache(new File(MyApplication.getContext().getCacheDir(), "Responses"), 10 * 1024 * 1024);
        }
        catch (Exception e){
            Log.e("Extra","Could nt create cache"+e.toString());
            e.printStackTrace();

        }

        return cache;

    }
}
