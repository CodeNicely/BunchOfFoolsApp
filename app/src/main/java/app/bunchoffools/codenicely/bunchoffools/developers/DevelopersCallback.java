package app.bunchoffools.codenicely.bunchoffools.developers;

import app.bunchoffools.codenicely.bunchoffools.developers.model.data.DeveloperData;

/**
 * Created by meghal on 17/10/16.
 */

public interface DevelopersCallback {

    void onSuccess(DeveloperData developerData);
    void onFailed();


}
