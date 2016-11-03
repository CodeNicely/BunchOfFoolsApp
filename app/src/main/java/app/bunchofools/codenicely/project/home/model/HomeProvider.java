package app.bunchofools.codenicely.project.home.model;

import app.bunchofools.codenicely.project.home.HomeCallback;

/**
 * Created by meghal on 15/10/16.
 */

public interface HomeProvider  {

    void requestHome(HomeCallback homeCallback);
    void onDestroy();

}
