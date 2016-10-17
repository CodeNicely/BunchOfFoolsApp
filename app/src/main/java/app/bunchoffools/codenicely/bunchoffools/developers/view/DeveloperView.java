package app.bunchoffools.codenicely.bunchoffools.developers.view;

import app.bunchoffools.codenicely.bunchoffools.developers.model.data.CompanyData;

/**
 * Created by meghal on 17/10/16.
 */

public interface DeveloperView {

    void showLoading(boolean show);
    void showMessage(String message);
    void setData(CompanyData companyData);


}
