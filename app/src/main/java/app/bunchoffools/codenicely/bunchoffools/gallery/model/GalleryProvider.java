package app.bunchoffools.codenicely.bunchoffools.gallery.model;

import app.bunchoffools.codenicely.bunchoffools.gallery.GalleryCallback;

/**
 * Created by meghal on 13/10/16.
 */

public interface GalleryProvider {

    void getImageUrls(GalleryCallback galleryCallback);

}
