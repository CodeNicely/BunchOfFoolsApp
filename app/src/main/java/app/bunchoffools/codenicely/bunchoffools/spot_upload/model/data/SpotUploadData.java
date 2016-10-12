package app.bunchoffools.codenicely.bunchoffools.spot_upload.model.data;

/**
 * Created by meghal on 12/10/16.
 */

public class SpotUploadData {



    boolean success;
    String message;

    public SpotUploadData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
