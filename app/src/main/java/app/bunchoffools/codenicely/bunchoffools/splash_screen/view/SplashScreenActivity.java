package app.bunchoffools.codenicely.bunchoffools.splash_screen.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.bunchoffools.codenicely.bunchoffools.R;
import app.bunchoffools.codenicely.bunchoffools.helper.MyApplication;
import app.bunchoffools.codenicely.bunchoffools.home.view.HomeActivity;
import app.bunchoffools.codenicely.bunchoffools.splash_screen.model.RetrofitSplashScreenProvider;
import app.bunchoffools.codenicely.bunchoffools.splash_screen.model.data.SplashScreenData;
import app.bunchoffools.codenicely.bunchoffools.splash_screen.presenter.SplashScreenPresenter;
import app.bunchoffools.codenicely.bunchoffools.splash_screen.presenter.SplashScreenPresenterImpl;

public class SplashScreenActivity extends Activity implements SplashScreenView {

    private Handler handler;
    private SplashScreenPresenter splashScreenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashScreenPresenter = new SplashScreenPresenterImpl(this, new RetrofitSplashScreenProvider());
        splashScreenPresenter.sendFcm(MyApplication.getFcm_token());
    }

    @Override
    public void onVersionReceived(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException {

        if (getPackageManager().getPackageInfo(getPackageName(), 0).versionCode < splashScreenData.getVersion_code()) {


            final AlertDialog ad = new AlertDialog.Builder(this)
                    .create();
            ad.setCancelable(false);
            ad.setTitle("App Update Available");
            ad.setMessage("Please update the app for better experience");
            ad.setButton(DialogInterface.BUTTON_POSITIVE, "Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();
                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }

                    finish();
                }
            });
            ad.setButton(DialogInterface.BUTTON_NEGATIVE, "Not Now", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();
                    startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                    finish();

                }
            });
            ad.show();


        }else{
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                    finish();

                }
            }, 3000);
        }



    }

    @Override
    public void onFailed() {

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                finish();

            }
        }, 3000);

    }
}
