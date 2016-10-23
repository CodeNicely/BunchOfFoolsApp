package app.bunchoffools.codenicely.bunchoffools.developers.presenter;

import app.bunchoffools.codenicely.bunchoffools.R;
import app.bunchoffools.codenicely.bunchoffools.developers.DevelopersCallback;
import app.bunchoffools.codenicely.bunchoffools.developers.model.DeveloperProvider;
import app.bunchoffools.codenicely.bunchoffools.developers.model.data.DeveloperData;
import app.bunchoffools.codenicely.bunchoffools.developers.view.DeveloperView;
import app.bunchoffools.codenicely.bunchoffools.helper.MyApplication;

/**
 * Created by meghal on 17/10/16.
 */

public class DevelopersPresenterImpl implements DevelopersPresenter{

    private DeveloperView developerView;
    private DeveloperProvider developerProvider;

    public DevelopersPresenterImpl(DeveloperView developerView, DeveloperProvider developerProvider) {
        this.developerView = developerView;
        this.developerProvider = developerProvider;
    }

    @Override
    public void requestDevelopersData() {

        developerView.showLoading(true);
        developerProvider.requestDevelopersData(new DevelopersCallback() {
            @Override
            public void onSuccess(DeveloperData developerData) {

                developerView.showLoading(false);
                if(developerData.isSuccess()){
                    developerView.setData(developerData.getCompanyData());
                }else {
                    developerView.showMessage(developerData.getMessage());
                }

            }

            @Override
            public void onFailed() {

                developerView.showLoading(false);
                developerView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));
            }
        });



    }

    @Override
    public void onDestroy() {
        developerProvider.onDestroy();
    }
}
