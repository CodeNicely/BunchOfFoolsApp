package app.bunchoffools.codenicely.bunchoffools.about_us;

import app.bunchoffools.codenicely.bunchoffools.about_us.model.data.AboutUsData;

/**
 * Created by meghal on 13/10/16.
 */

public interface AboutUsCallBack {



    void onSuccess(AboutUsData aboutUsData);
    void onFailure();

}
