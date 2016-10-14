package app.bunchoffools.codenicely.bunchoffools.home.presenter;

import app.bunchoffools.codenicely.bunchoffools.home.HomeCallback;
import app.bunchoffools.codenicely.bunchoffools.home.model.HomeProvider;
import app.bunchoffools.codenicely.bunchoffools.home.model.data.HomeData;
import app.bunchoffools.codenicely.bunchoffools.home.view.HomeView;

/**
 * Created by meghal on 15/10/16.
 */

public class HomePresenterImpl implements HomePresenter {

    private HomeView homeView;
    private HomeProvider homeProvider;

    public HomePresenterImpl(HomeView homeView, HomeProvider homeProvider) {
        this.homeView = homeView;
        this.homeProvider = homeProvider;
    }

    @Override
    public void requestHome() {

        homeView.showLoader(true);

        homeProvider.requestHome(new HomeCallback() {
            @Override
            public void onSuccess(HomeData homeData) {

                homeView.showLoader(false);
                if(homeData.isSuccess()){

                    homeView.setData(homeData);
                    homeView.showMessage(homeData.getMessage());

                }
            }
            @Override
            public void onFailure() {

                homeView.showLoader(false);
                homeView.showMessage("Something Went Wrong - Unable to contact servers");


            }
        });



    }
}
