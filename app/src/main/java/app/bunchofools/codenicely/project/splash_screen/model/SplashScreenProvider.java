package app.bunchofools.codenicely.project.splash_screen.model;

import app.bunchofools.codenicely.project.splash_screen.SplashScreenCallback;

/**
 * Created by meghal on 23/10/16.
 */

public interface SplashScreenProvider {

    void sendFcm(String fcm,SplashScreenCallback splashScreenCallback);

}
