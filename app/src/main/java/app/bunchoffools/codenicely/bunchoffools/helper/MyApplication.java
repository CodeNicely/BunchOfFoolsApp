package app.bunchoffools.codenicely.bunchoffools.helper;

import android.app.Application;
import android.content.Context;

import app.bunchoffools.codenicely.bunchoffools.helper.utils.TypefaceUtil;

/**
 * Created by meghal on 11/10/16.
 */

public class MyApplication extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/comfortaa.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/homemade.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/nunito.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/patrick_hand.ttf");

//        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/nunito.ttf");

    }

    public static Context getContext() {
        return context;
    }


}
