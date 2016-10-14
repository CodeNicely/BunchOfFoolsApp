package app.bunchoffools.codenicely.bunchoffools.join_us.api;

import app.bunchoffools.codenicely.bunchoffools.helper.Urls;
import app.bunchoffools.codenicely.bunchoffools.home.view.ImagesAdapter;
import app.bunchoffools.codenicely.bunchoffools.join_us.model.data.JoinUsData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by meghal on 12/10/16.
 */

public interface JoinUsApi {

    @FormUrlEncoded
    @POST(Urls.SUB_URL_JOIN_US)
    Call<JoinUsData> requestJoin(@Field("name") String name, @Field("mobile") String mobile,
                                 @Field("email") String email);


}
