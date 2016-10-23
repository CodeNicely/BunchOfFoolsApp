package app.bunchoffools.codenicely.bunchoffools.home.model;

import app.bunchoffools.codenicely.bunchoffools.home.HomeCallback;

/**
 * Created by meghal on 15/10/16.
 */

public interface HomeProvider  {

    void requestHome(HomeCallback homeCallback);
    void onDestroy();

}
