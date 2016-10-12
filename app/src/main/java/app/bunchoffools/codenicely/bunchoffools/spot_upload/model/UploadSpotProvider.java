package app.bunchoffools.codenicely.bunchoffools.spot_upload.model;

import android.net.Uri;

import java.io.File;
import java.io.IOException;

import app.bunchoffools.codenicely.bunchoffools.spot_upload.model.data.SpotUploadData;
import rx.Observable;

/**
 * Created by meghal on 11/10/16.
 */

public interface UploadSpotProvider {


    Observable<SpotUploadData> uploadSpot(String name,String mobile,String email,
                                                                      String location,Uri imageUri) throws IOException;

}
