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
    }

    public static Context getContext() {
        return context;
    }


}
