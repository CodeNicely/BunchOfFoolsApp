package app.bunchofools.codenicely.project.about_us.model;

import app.bunchofools.codenicely.project.about_us.AboutUsCallBack;

/**
 * Created by meghal on 13/10/16.
 */

public interface AboutUsProvider {


    void requestAboutUs(AboutUsCallBack aboutUsCallBack);
    void onDestroy();

}
