package app.bunchoffools.codenicely.bunchoffools.splash_screen;

import app.bunchoffools.codenicely.bunchoffools.splash_screen.model.data.SplashScreenData;

/**
 * Created by meghal on 23/10/16.
 */

public interface SplashScreenCallback {

    void onSuccess(SplashScreenData splashScreenData);
    void onFailed();
}
