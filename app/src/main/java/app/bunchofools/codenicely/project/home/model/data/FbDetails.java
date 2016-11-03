package app.bunchofools.codenicely.project.home.model.data;

/**
 * Created by meghal on 15/10/16.
 */

public class FbDetails {

    private String id;
    private String created_time;
    private FbAttachments attachments;

    public FbDetails(String id, String created_time, FbAttachments attachments) {
        this.id = id;
        this.created_time = created_time;
        this.attachments = attachments;
    }


    public String getId() {
        return id;
    }

    public String getCreated_time() {
        return created_time;
    }

    public FbAttachments getAttachments() {
        return attachments;
    }
}
