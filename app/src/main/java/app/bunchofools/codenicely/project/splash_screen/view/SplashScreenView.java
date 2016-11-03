package app.bunchofools.codenicely.project.splash_screen.view;

import android.content.pm.PackageManager;

import app.bunchofools.codenicely.project.splash_screen.model.data.SplashScreenData;

/**
 * Created by meghal on 23/10/16.
 */

public interface SplashScreenView {


    void onVersionReceived(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException;
    void onFailed();
}
