package app.bunchoffools.codenicely.bunchoffools.splash_screen.view;

import android.content.pm.PackageManager;

import app.bunchoffools.codenicely.bunchoffools.splash_screen.model.data.SplashScreenData;

/**
 * Created by meghal on 23/10/16.
 */

public interface SplashScreenView {


    void onVersionReceived(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException;
    void onFailed();
}
