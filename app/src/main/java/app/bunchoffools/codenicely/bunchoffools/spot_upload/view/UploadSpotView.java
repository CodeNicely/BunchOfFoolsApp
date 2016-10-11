package app.bunchoffools.codenicely.bunchoffools.spot_upload.view;

import java.io.File;

/**
 * Created by meghal on 11/10/16.
 */

public interface UploadSpotView {


    void showLoader(boolean show);

    void showMessage(String message);

    /**
     * This method is for checking camera permission.
     * Applicable only for devices with Api 23 or more.
     *
     * @return
     */
    boolean checkPermissionForCamera();

    /**
     * This method is for checking gallery permission.
     * Applicable only for devices with api 23 or more.
     *
     * @return
     */
    boolean checkPermissionForGallery();

    /**
     * This function is for requesting camera permission if user does'nt have taken permission
     * previously.
     *
     * @return
     */
    boolean requestCameraPermission();

    /**
     * This function is for requesting gallery permission if user does'nt have taken permission
     * previously.
     *
     * @return
     */
    boolean requestGalleryPermission();


    /**
     * This method is called when user chooses to open camera.
     */
    void showCamera();

    /**
     * This method is called when user chooses to open gallery.
     */

    void showGallery();

    void fileFromPath(String filePath);



}