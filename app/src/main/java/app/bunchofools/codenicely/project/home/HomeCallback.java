package app.bunchofools.codenicely.project.home;

import app.bunchofools.codenicely.project.home.model.data.HomeData;

/**
 * Created by meghal on 15/10/16.
 */

public interface HomeCallback {


    void onSuccess(HomeData homeData);
    void onFailure();

}
