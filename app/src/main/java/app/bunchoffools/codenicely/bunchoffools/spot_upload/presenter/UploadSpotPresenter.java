package app.bunchoffools.codenicely.bunchoffools.spot_upload.presenter;

import android.net.Uri;

import java.io.File;

/**
 * Created by meghal on 11/10/16.
 */

public interface UploadSpotPresenter {


    /**
     * This function is called to open camera for clicking new image
     */
    void openCamera();

    /**
     * This function s called from view if user chooses to select images already present in gallery
     */
    void openGallery();

    void uploadSpot(String name, String mobile, String email, String location, File imageFile);

}
