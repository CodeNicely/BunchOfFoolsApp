package app.bunchoffools.codenicely.bunchoffools.contact_us.view;

import app.bunchoffools.codenicely.bunchoffools.contact_us.model.data.ContactUsData;

/**
 * Created by meghal on 15/10/16.
 */

public interface ContactUsView {


    void showLoader(boolean show);
    void showMessage(String message);
    void setData(ContactUsData contactUsData);

}
