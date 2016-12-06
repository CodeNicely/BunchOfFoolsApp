package app.bunchofools.codenicely.project.gallery;

import app.bunchofools.codenicely.project.gallery.model.data.GalleryData;

/**
 * Created by meghal on 13/10/16.
 */

public interface GalleryCallback {


    void onSuccess(GalleryData galleryData);
    void onFailure();

}
