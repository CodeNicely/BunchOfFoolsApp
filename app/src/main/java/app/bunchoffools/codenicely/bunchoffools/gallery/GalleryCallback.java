package app.bunchoffools.codenicely.bunchoffools.gallery;

import app.bunchoffools.codenicely.bunchoffools.gallery.model.data.GalleryData;

/**
 * Created by meghal on 13/10/16.
 */

public interface GalleryCallback {


    void onSuccess(GalleryData galleryData);
    void onFailure();

}
