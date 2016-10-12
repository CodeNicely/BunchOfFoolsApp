package app.bunchoffools.codenicely.bunchoffools.about_us.view;

import app.bunchoffools.codenicely.bunchoffools.about_us.model.data.AboutUsData;

/**
 * Created by meghal on 13/10/16.
 */

public interface AboutUsView {


    void showMessage(String message);
    void showLoader(boolean show);
    void setData(AboutUsData aboutUsData);


}
