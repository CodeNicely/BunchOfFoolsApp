package app.bunchoffools.codenicely.bunchoffools.home.model.data;

/**
 * Created by meghal on 15/10/16.
 */

public class FbImage {

    private int height;
    private int width;
    private String src;

    public FbImage(int height, int width, String src) {
        this.height = height;
        this.width = width;
        this.src = src;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getSrc() {
        return src;
    }
}
