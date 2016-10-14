package app.bunchoffools.codenicely.bunchoffools.home.view;

import app.bunchoffools.codenicely.bunchoffools.home.model.data.HomeData;

/**
 * Created by meghal on 15/10/16.
 */

public interface HomeView {

    void showLoader(boolean show);
    void showMessage(String message);
    void setData(HomeData homeData);

}
