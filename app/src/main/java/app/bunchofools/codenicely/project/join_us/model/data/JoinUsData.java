package app.bunchofools.codenicely.project.join_us.model.data;

/**
 * Created by meghal on 12/10/16.
 */

public class JoinUsData {

    private String message;
    private boolean success;


    public JoinUsData(String message, boolean success) {
        this.message = message;
        this.success = success;
    }


    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

}
