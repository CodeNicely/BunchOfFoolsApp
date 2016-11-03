package app.bunchofools.codenicely.project.gallery.api;

import app.bunchofools.codenicely.project.gallery.model.data.GalleryData;
import app.bunchofools.codenicely.project.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by meghal on 13/10/16.
 */

public interface GalleryApi {


    @GET(Urls.SUB_URL_GALLERY)
    Call<GalleryData> getImageUrls();

}
