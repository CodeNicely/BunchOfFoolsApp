package app.bunchoffools.codenicely.bunchoffools.contact_us.api;

import android.net.Uri;

import app.bunchoffools.codenicely.bunchoffools.contact_us.model.data.ContactUsData;
import app.bunchoffools.codenicely.bunchoffools.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by meghal on 15/10/16.
 */

public interface ContactUsApi {


    @GET(Urls.SUB_URL_CONTACT_US)
    Call<ContactUsData> requestContactUs();

}
