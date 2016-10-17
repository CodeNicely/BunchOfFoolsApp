package app.bunchoffools.codenicely.bunchoffools.developers.model;

import app.bunchoffools.codenicely.bunchoffools.developers.DevelopersCallback;

/**
 * Created by meghal on 17/10/16.
 */

public interface DeveloperProvider {

    void requestDevelopersData(DevelopersCallback developersCallback);

}
