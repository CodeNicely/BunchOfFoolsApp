package app.bunchoffools.codenicely.bunchoffools.splash_screen.model;

import app.bunchoffools.codenicely.bunchoffools.splash_screen.SplashScreenCallback;

/**
 * Created by meghal on 23/10/16.
 */

public interface SplashScreenProvider {

    void sendFcm(String fcm,SplashScreenCallback splashScreenCallback);

}
