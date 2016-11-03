package app.bunchofools.codenicely.project.home.presenter;

import app.bunchofools.codenicely.project.R;
import app.bunchofools.codenicely.project.helper.MyApplication;
import app.bunchofools.codenicely.project.home.HomeCallback;
import app.bunchofools.codenicely.project.home.model.HomeProvider;
import app.bunchofools.codenicely.project.home.model.data.HomeData;
import app.bunchofools.codenicely.project.home.view.HomeView;

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

                    }else{

                        homeView.showMessage(homeData.getMessage());

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
