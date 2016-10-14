package app.bunchoffools.codenicely.bunchoffools.home.model.data;

/**
 * Created by meghal on 15/10/16.
 */

public class HomeSliderData {

    private int id;
    private String image_url;
    private String message;

    public HomeSliderData(int id, String image_url, String message) {
        this.id = id;
        this.image_url = image_url;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getMessage() {
        return message;
    }
}
