package app.bunchoffools.codenicely.bunchoffools.contact_us.presenter;

import android.support.v4.content.ContextCompat;

import app.bunchoffools.codenicely.bunchoffools.R;
import app.bunchoffools.codenicely.bunchoffools.contact_us.ContactUsCallback;
import app.bunchoffools.codenicely.bunchoffools.contact_us.model.ContactUsProvider;
import app.bunchoffools.codenicely.bunchoffools.contact_us.model.data.ContactUsData;
import app.bunchoffools.codenicely.bunchoffools.contact_us.view.ContactUsView;
import app.bunchoffools.codenicely.bunchoffools.helper.MyApplication;

/**
 * Created by meghal on 15/10/16.
 */

public class ContactUsPresenterImpl implements ContactUsPresenter {

    private ContactUsView contactUsView;
    private ContactUsProvider contactUsProvider;

    public ContactUsPresenterImpl(ContactUsView contactUsView, ContactUsProvider contactUsProvider) {
        this.contactUsView = contactUsView;
        this.contactUsProvider = contactUsProvider;
    }

    @Override
    public void requestContactUs() {

        contactUsView.showLoader(true);
        contactUsProvider.requestContactUs(new ContactUsCallback() {
            @Override
            public void onSuccess(ContactUsData contactUsData) {

                contactUsView.showLoader(false);
                if(contactUsData.isSuccess()){
                    contactUsView.setData(contactUsData);
                    contactUsView.showMessage(contactUsData.getMessage());
                }else
                {
                    contactUsView.showMessage(contactUsData.getMessage());
                }
            }

            @Override
            public void onFailure() {

                contactUsView.showLoader(false);
                contactUsView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));
            }
        });

    }
}
