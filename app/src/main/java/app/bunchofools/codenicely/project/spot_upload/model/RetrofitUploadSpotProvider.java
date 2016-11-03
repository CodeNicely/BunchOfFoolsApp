package app.bunchofools.codenicely.project.spot_upload.model;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.IOException;

import app.bunchofools.codenicely.project.spot_upload.api.SpotUploadApi;
import app.bunchofools.codenicely.project.helper.Urls;
import app.bunchofools.codenicely.project.spot_upload.model.data.SpotUploadData;
import app.bunchofools.codenicely.project.helper.utils.BitmapUtils;
import app.bunchofools.codenicely.project.helper.utils.FileUtils;
import app.bunchofools.codenicely.project.helper.utils.UriUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

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
    public Observable<SpotUploadData> uploadSpot(String name, String mobile, String email, String location,String description, Uri imageUri) throws IOException {

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
        RequestBody description1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), description);

/*


*/
        if(imageUri!=null) {
        //    File imageFile=new File(imageUri.getPath());
        File imageFile=FileUtils.BitmapToFileConverter(context,BitmapUtils.filePathToBitmapConverter(UriUtils.uriToFilePathConverter(context, imageUri)));
            RequestBody fbody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);

            MultipartBody.Part image =
                    MultipartBody.Part.createFormData("image", imageFile.getName(), fbody);

            return spotUploadApi.uploadSpotDetails(name1,mobile1,email1,location1,description1, image);
        }

        return null;


    }
}
