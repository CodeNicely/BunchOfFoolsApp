package app.bunchofools.codenicely.project.gallery.model.data;

import java.util.ArrayList;

/**
 * Created by meghal on 13/10/16.
 */

public class GalleryData {


    private boolean success;
    private String message;
    private ArrayList<String> gallery_images;


    public GalleryData(boolean success, String message, ArrayList<String> gallery_images) {
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

    public ArrayList<String> getGallery_images() {
        return gallery_images;
    }
}
