package app.bunchoffools.codenicely.bunchoffools.home;

import app.bunchoffools.codenicely.bunchoffools.home.model.data.HomeData;

/**
 * Created by meghal on 15/10/16.
 */

public interface HomeCallback {


    void onSuccess(HomeData homeData);
    void onFailure();

}
