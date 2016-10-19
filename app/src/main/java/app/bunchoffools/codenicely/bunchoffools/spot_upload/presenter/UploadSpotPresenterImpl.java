package app.bunchoffools.codenicely.bunchoffools.spot_upload.presenter;


import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import app.bunchoffools.codenicely.bunchoffools.R;
import app.bunchoffools.codenicely.bunchoffools.helper.MyApplication;
import app.bunchoffools.codenicely.bunchoffools.spot_upload.model.UploadSpotProvider;
import app.bunchoffools.codenicely.bunchoffools.spot_upload.model.data.SpotUploadData;
import app.bunchoffools.codenicely.bunchoffools.spot_upload.view.UploadSpotView;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static app.bunchoffools.codenicely.bunchoffools.helper.FileProvider.requestNewFile;

/**
 * Created by meghal on 11/10/16.
 */

public class UploadSpotPresenterImpl implements UploadSpotPresenter{


    private static final String TAG = "UploadSpotPresenterImpl" ;
    private UploadSpotView uploadSpotView;
    private UploadSpotProvider uploadSpotProvider;
    private Observable<SpotUploadData> spotUploadDataObservable;
    private Subscription subscription;
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



    @Override
    public void uploadSpot(String name, String mobile, String email, String location, Uri imageUri) {


        uploadSpotView.showLoader(true);
        try {
            spotUploadDataObservable=uploadSpotProvider.uploadSpot(name,mobile,email,location,imageUri);
            Log.i(TAG,"Value of Observable"+spotUploadDataObservable.toString());
            subscription=spotUploadDataObservable.subscribeOn(Schedulers.newThread()).
                    observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SpotUploadData>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                    uploadSpotView.showLoader(false);
                    uploadSpotView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));
                    e.printStackTrace();
                }

                @Override
                public void onNext(SpotUploadData spotUploadData) {

                    Log.i(TAG,"Response "+spotUploadData.toString());
                    if( spotUploadData.isSuccess()){
                        uploadSpotView.showLoader(false);
                        uploadSpotView.showMessage(spotUploadData.getMessage());
                    }else{
                        uploadSpotView.showLoader(false);
                        uploadSpotView.showMessage(spotUploadData.getMessage());
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
