package app.bunchoffools.codenicely.bunchoffools.home.model.data;

import java.util.List;

/**
 * Created by meghal on 15/10/16.
 */

public class HomeData {

    private boolean success;
    private String message;
    private List<HomeSliderData> slider_data;
    private FbFeeds feeds;

    public HomeData(boolean success, String message, List<HomeSliderData> slider_data, FbFeeds feeds) {
        this.success = success;
        this.message = message;
        this.slider_data = slider_data;
        this.feeds = feeds;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<HomeSliderData> getSlider_data() {
        return slider_data;
    }

    public FbFeeds getFeeds() {
        return feeds;
    }
}
