package app.bunchoffools.codenicely.bunchoffools.gallery.presenter;

import app.bunchoffools.codenicely.bunchoffools.gallery.GalleryCallback;
import app.bunchoffools.codenicely.bunchoffools.gallery.model.GalleryProvider;
import app.bunchoffools.codenicely.bunchoffools.gallery.model.data.GalleryData;
import app.bunchoffools.codenicely.bunchoffools.gallery.view.GalleryView;

/**
 * Created by meghal on 13/10/16.
 */

public class GalleryPresenterImpl implements GalleryPresenter {

    private GalleryView galleryView;
    private GalleryProvider galleryProvider;

    public GalleryPresenterImpl(GalleryView galleryView, GalleryProvider galleryProvider) {
        this.galleryView = galleryView;
        this.galleryProvider = galleryProvider;
    }

    @Override
    public void getImageUrls() {

        galleryView.showLoader(true);
        galleryProvider.getImageUrls(new GalleryCallback() {
            @Override
            public void onSuccess(GalleryData galleryData) {

                galleryView.showLoader(false);

                if(galleryData.isSuccess())
                    galleryView.setData(galleryData);
                else
                    galleryView.showMessage(galleryData.getMessage());
            }

            @Override
            public void onFailure() {

            }
        });

    }
}
