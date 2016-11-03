package app.bunchofools.codenicely.project.gallery.model;

import app.bunchofools.codenicely.project.gallery.GalleryCallback;

/**
 * Created by meghal on 13/10/16.
 */

public interface GalleryProvider {

    void getImageUrls(GalleryCallback galleryCallback);
    void onDestroy();

}
