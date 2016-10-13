package app.bunchoffools.codenicely.bunchoffools.gallery.view;

import app.bunchoffools.codenicely.bunchoffools.gallery.model.data.GalleryData;

/**
 * Created by meghal on 13/10/16.
 */

public interface GalleryView {

    void showLoader(boolean show);
    void showMessage(String message);
    void setData(GalleryData galleryData);
}
