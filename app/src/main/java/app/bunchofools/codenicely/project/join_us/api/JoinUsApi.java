package app.bunchofools.codenicely.project.join_us.api;

import app.bunchofools.codenicely.project.helper.Urls;
import app.bunchofools.codenicely.project.join_us.model.data.JoinUsData;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by meghal on 12/10/16.
 */

public interface JoinUsApi {

    @FormUrlEncoded
    @POST(Urls.SUB_URL_JOIN_US)
    Call<JoinUsData> requestJoin(@Field("name") String name, @Field("mobile") String mobile,
                                 @Field("email") String email);


}
