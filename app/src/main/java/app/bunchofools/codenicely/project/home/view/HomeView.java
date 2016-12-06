package app.bunchofools.codenicely.project.home.view;

import app.bunchofools.codenicely.project.home.model.data.HomeData;

/**
 * Created by meghal on 15/10/16.
 */

public interface HomeView {

    void showLoader(boolean show);
    void showMessage(String message);
    void setData(HomeData homeData);

}
