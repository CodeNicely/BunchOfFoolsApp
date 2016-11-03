package app.bunchofools.codenicely.project.splash_screen;

import app.bunchofools.codenicely.project.splash_screen.model.data.SplashScreenData;

/**
 * Created by meghal on 23/10/16.
 */

public interface SplashScreenCallback {

    void onSuccess(SplashScreenData splashScreenData);
    void onFailed();
}
