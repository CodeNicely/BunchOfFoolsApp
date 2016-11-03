package app.bunchofools.codenicely.project.home.model.data;

import android.graphics.drawable.Drawable;

/**
 * Created by meghal on 31/10/16.
 */

public class HomeGridData {

    private String title;
    private int image;

    public HomeGridData(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
}
