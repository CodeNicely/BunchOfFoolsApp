package app.bunchofools.codenicely.project.splash_screen.presenter;

import android.content.pm.PackageManager;

import app.bunchofools.codenicely.project.splash_screen.SplashScreenCallback;
import app.bunchofools.codenicely.project.splash_screen.model.SplashScreenProvider;
import app.bunchofools.codenicely.project.splash_screen.model.data.SplashScreenData;
import app.bunchofools.codenicely.project.splash_screen.view.SplashScreenView;

/**
 * Created by meghal on 23/10/16.
 */

public class SplashScreenPresenterImpl implements SplashScreenPresenter {


    private SplashScreenView splashScreenView;
    private SplashScreenProvider splashScreenProvider;

    public SplashScreenPresenterImpl(SplashScreenView splashScreenView, SplashScreenProvider splashScreenProvider) {
        this.splashScreenView = splashScreenView;
        this.splashScreenProvider = splashScreenProvider;
    }

    @Override
    public void sendFcm(String fcm) {

        splashScreenProvider.sendFcm(fcm,new SplashScreenCallback() {

            @Override
            public void onSuccess(SplashScreenData splashScreenData) {

                if(splashScreenData.isSuccess()){
                    try {
                        splashScreenView.onVersionReceived(splashScreenData);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailed() {
                splashScreenView.onFailed();
            }
        });


    }
}
