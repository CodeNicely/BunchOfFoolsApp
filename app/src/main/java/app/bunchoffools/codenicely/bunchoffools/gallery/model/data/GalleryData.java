package app.bunchoffools.codenicely.bunchoffools.gallery.model.data;

import java.util.List;

/**
 * Created by meghal on 13/10/16.
 */

public class GalleryData {


    private boolean success;
    private String message;
    private List<String> gallery_images;


    public GalleryData(boolean success, String message, List<String> gallery_images) {
        this.success = success;
        this.message = message;
        this.gallery_images = gallery_images;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getGallery_images() {
        return gallery_images;
    }
}
