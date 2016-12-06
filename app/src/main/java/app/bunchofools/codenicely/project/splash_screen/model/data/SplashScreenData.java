package app.bunchofools.codenicely.project.splash_screen.model.data;

/**
 * Created by meghal on 23/10/16.
 */

public class SplashScreenData {


    private boolean success;
    private String message;
    private int version_code;
    private boolean compulsory_update;

    public SplashScreenData(boolean success, String message, int version_code, boolean compulsory_update) {
        this.success = success;
        this.message = message;
        this.version_code = version_code;
        this.compulsory_update = compulsory_update;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getVersion_code() {
        return version_code;
    }

    public boolean isCompulsory_update() {
        return compulsory_update;
    }
}
