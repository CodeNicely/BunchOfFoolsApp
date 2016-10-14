package app.bunchoffools.codenicely.bunchoffools.home.model.data;

/**
 * Created by meghal on 15/10/16.
 */

public class FbData {

    private FbMedia media;
    private String title;
    private String type;
    private String url;
    private FbTarget target;
    private String description;
    private FbSubAttachments subattachments;

    public FbData(FbMedia media, String title, String type, String url, FbTarget target, String description, FbSubAttachments subattachments) {
        this.media = media;
        this.title = title;
        this.type = type;
        this.url = url;
        this.target = target;
        this.description = description;
        this.subattachments = subattachments;
    }


    public FbMedia getMedia() {
        return media;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public FbTarget getTarget() {
        return target;
    }

    public String getDescription() {
        return description;
    }

    public FbSubAttachments getSubattachments() {
        return subattachments;
    }
}
