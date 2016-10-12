package app.bunchoffools.codenicely.bunchoffools.spot_upload.api;

import app.bunchoffools.codenicely.bunchoffools.spot_upload.model.data.SpotUploadData;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by meghal on 12/10/16.
 */

public interface SpotUploadApi {

    @Multipart
    @POST(Urls.SUB_URL_SPOT_UPLOAD)
    Observable<SpotUploadData> uploadSpotDetails(@Part("name") RequestBody name, @Part("mobile") RequestBody mobile
            , @Part("email") RequestBody email, @Part("location") RequestBody location, @Part MultipartBody.Part image);
}
