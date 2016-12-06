package app.bunchofools.codenicely.project.developers.presenter;

import app.bunchofools.codenicely.project.R;
import app.bunchofools.codenicely.project.developers.DevelopersCallback;
import app.bunchofools.codenicely.project.developers.model.DeveloperProvider;
import app.bunchofools.codenicely.project.developers.model.data.DeveloperData;
import app.bunchofools.codenicely.project.developers.view.DeveloperView;
import app.bunchofools.codenicely.project.helper.MyApplication;

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
