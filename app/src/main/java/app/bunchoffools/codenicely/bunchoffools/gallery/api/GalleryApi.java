package app.bunchoffools.codenicely.bunchoffools.gallery.api;

import app.bunchoffools.codenicely.bunchoffools.gallery.model.data.GalleryData;
import app.bunchoffools.codenicely.bunchoffools.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by meghal on 13/10/16.
 */

public interface GalleryApi {


    @GET(Urls.SUB_URL_GALLERY)
    Call<GalleryData> getImageUrls();

}
