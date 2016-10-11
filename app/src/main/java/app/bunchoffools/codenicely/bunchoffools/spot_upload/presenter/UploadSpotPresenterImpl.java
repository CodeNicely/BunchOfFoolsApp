package app.bunchoffools.codenicely.bunchoffools.spot_upload.presenter;


import android.support.v4.content.FileProvider;

import java.io.File;

import app.bunchoffools.codenicely.bunchoffools.spot_upload.model.UploadSpotProvider;
import app.bunchoffools.codenicely.bunchoffools.spot_upload.view.UploadSpotView;

import static android.support.v4.content.FileProvider.*;
import static app.bunchoffools.codenicely.bunchoffools.helper.FileProvider.requestNewFile;

/**
 * Created by meghal on 11/10/16.
 */

public class UploadSpotPresenterImpl implements UploadSpotPresenter{


    private UploadSpotView uploadSpotView;
    private UploadSpotProvider uploadSpotProvider;

    public UploadSpotPresenterImpl(UploadSpotView uploadSpotView, UploadSpotProvider uploadSpotProvider) {
        this.uploadSpotView = uploadSpotView;
        this.uploadSpotProvider = uploadSpotProvider;
    }

    @Override
    public void openCamera() {
        File image = requestNewFile();

        if(uploadSpotView.checkPermissionForCamera()){
            uploadSpotView.fileFromPath(image.getPath());
            uploadSpotView.showCamera();
        }else{
            if(uploadSpotView.requestCameraPermission()){
                uploadSpotView.fileFromPath(image.getPath());

             uploadSpotView.showCamera();
            }
        }

    }

    @Override
    public void openGallery() {

        if(uploadSpotView.checkPermissionForGallery()){

            uploadSpotView.showGallery();
        }else{

            if(uploadSpotView.requestGalleryPermission()){
                uploadSpotView.showGallery();
            }
        }

    }
}
