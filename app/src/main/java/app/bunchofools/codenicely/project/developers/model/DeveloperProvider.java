package app.bunchofools.codenicely.project.developers.model;

import app.bunchofools.codenicely.project.developers.DevelopersCallback;

/**
 * Created by meghal on 17/10/16.
 */

public interface DeveloperProvider {

    void requestDevelopersData(DevelopersCallback developersCallback);
    void onDestroy();
}
