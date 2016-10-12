package app.bunchoffools.codenicely.bunchoffools.about_us.presenter;

import app.bunchoffools.codenicely.bunchoffools.about_us.AboutUsCallBack;
import app.bunchoffools.codenicely.bunchoffools.about_us.model.AboutUsProvider;
import app.bunchoffools.codenicely.bunchoffools.about_us.model.data.AboutUsData;
import app.bunchoffools.codenicely.bunchoffools.about_us.view.AboutUsView;

/**
 * Created by meghal on 13/10/16.
 */

public class AboutUsPresenterImpl implements AboutUsPresenter{


    private AboutUsView aboutUsView;
    private AboutUsProvider aboutUsProvider;

    public AboutUsPresenterImpl(AboutUsView aboutUsView, AboutUsProvider aboutUsProvider) {
        this.aboutUsView = aboutUsView;
        this.aboutUsProvider = aboutUsProvider;
    }

    @Override
    public void requestAboutUs() {

        aboutUsView.showLoader(true);
        aboutUsProvider.requestAboutUs(new AboutUsCallBack() {
            @Override
            public void onSuccess(AboutUsData aboutUsData) {

                if(aboutUsData.isSuccess()){

                }
            }
            @Override
            public void onFailure() {

            }
        });

    }
}
