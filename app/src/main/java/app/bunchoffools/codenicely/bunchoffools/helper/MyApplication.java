package app.bunchoffools.codenicely.bunchoffools.helper;

import android.app.Application;
import android.content.Context;

/**
 * Created by meghal on 11/10/16.
 */

public class MyApplication extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;

        FontsOverride.setDefaultFont(this, "DEFAULT", "comfortaa.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "homemade.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "nunito.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "patrick_hand.ttf");
    }

    public static Context getContext() {
        return context;
    }


}
