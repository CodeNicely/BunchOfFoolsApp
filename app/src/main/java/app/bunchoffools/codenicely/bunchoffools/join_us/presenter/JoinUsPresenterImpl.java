package app.bunchoffools.codenicely.bunchoffools.join_us.presenter;

import app.bunchoffools.codenicely.bunchoffools.join_us.JoinUsCallback;
import app.bunchoffools.codenicely.bunchoffools.join_us.model.JoinUsProvider;
import app.bunchoffools.codenicely.bunchoffools.join_us.model.data.JoinUsData;
import app.bunchoffools.codenicely.bunchoffools.join_us.view.JoinUsView;

/**
 * Created by meghal on 12/10/16.
 */

public class JoinUsPresenterImpl implements JoinUsPresenter {

    private JoinUsView joinUsView;
    private JoinUsProvider joinUsProvider;

    public JoinUsPresenterImpl(JoinUsView joinUsView, JoinUsProvider joinUsProvider) {
        this.joinUsView = joinUsView;
        this.joinUsProvider = joinUsProvider;
    }

    @Override
    public void requestJoin(String name, String mobile, String email) {

        joinUsView.showLoading(true);
        joinUsProvider.requestJoin(name, mobile, email, new JoinUsCallback() {
            @Override
            public void onSuccess(JoinUsData joinUsData) {

                joinUsView.showLoading(false);
                if(joinUsData.isSuccess()){
                    joinUsView.showMessage(joinUsData.getMessage());
                }else{
                    joinUsView.showMessage(joinUsData.getMessage());
                }


            }

            @Override
            public void onFailure() {

            }
        });



    }
}
