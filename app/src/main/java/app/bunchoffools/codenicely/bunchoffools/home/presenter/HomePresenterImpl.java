package app.bunchoffools.codenicely.bunchoffools.home.presenter;

import app.bunchoffools.codenicely.bunchoffools.R;
import app.bunchoffools.codenicely.bunchoffools.helper.MyApplication;
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
                if (homeView != null) {
                    homeView.showLoader(false);
                    if (homeData.isSuccess()) {


                        homeView.setData(homeData);

                    }
                }
            }

            @Override
            public void onFailure() {

                homeView.showLoader(false);
                homeView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));

            }
        });


    }

    @Override
    public void onDestroy() {

        homeProvider.onDestroy();
    }
}
