package app.bunchofools.codenicely.project.developers;

import app.bunchofools.codenicely.project.developers.model.data.DeveloperData;

/**
 * Created by meghal on 17/10/16.
 */

public interface DevelopersCallback {

    void onSuccess(DeveloperData developerData);
    void onFailed();


}
