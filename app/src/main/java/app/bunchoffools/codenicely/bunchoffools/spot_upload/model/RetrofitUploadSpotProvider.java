package app.bunchoffools.codenicely.bunchoffools.spot_upload.model;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.IOException;

import app.bunchoffools.codenicely.bunchoffools.spot_upload.api.SpotUploadApi;
import app.bunchoffools.codenicely.bunchoffools.helper.Urls;
import app.bunchoffools.codenicely.bunchoffools.spot_upload.model.data.SpotUploadData;
import app.bunchoffools.codenicely.bunchoffools.utils.utils.BitmapUtils;
import app.bunchoffools.codenicely.bunchoffools.utils.utils.FileUtils;
import app.bunchoffools.codenicely.bunchoffools.utils.utils.UriUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

import static app.bunchoffools.codenicely.bunchoffools.helper.MyApplication.getContext;

/**
 * Created by meghal on 11/10/16.
 */

public class RetrofitUploadSpotProvider implements UploadSpotProvider {

    private Retrofit retrofit;
    private SpotUploadApi spotUploadApi;
    private Context context;
    public RetrofitUploadSpotProvider(Context context){

        this.context=context;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
        //        .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        spotUploadApi=retrofit.create(SpotUploadApi.class);

    }


    @Override
    public Observable<SpotUploadData> uploadSpot(String name, String mobile, String email, String location, Uri imageUri) throws IOException {

        RequestBody name1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), name);

        RequestBody mobile1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), mobile);
        RequestBody email1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), email);
        RequestBody location1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), location);
/*


*/
        if(imageUri!=null) {
        //    File imageFile=new File(imageUri.getPath());
        File imageFile=FileUtils.BitmapToFileConverter(context,BitmapUtils.filePathToBitmapConverter(UriUtils.uriToFilePathConverter(context, imageUri)));
            RequestBody fbody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);

            MultipartBody.Part image =
                    MultipartBody.Part.createFormData("image", imageFile.getName(), fbody);

            return spotUploadApi.uploadSpotDetails(name1,mobile1,email1,location1, image);
        }

        return null;


    }
}
