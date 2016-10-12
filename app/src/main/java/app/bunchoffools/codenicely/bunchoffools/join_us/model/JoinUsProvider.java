package app.bunchoffools.codenicely.bunchoffools.join_us.model;

import app.bunchoffools.codenicely.bunchoffools.join_us.JoinUsCallback;

/**
 * Created by meghal on 12/10/16.
 */

public interface JoinUsProvider {



    void requestJoin(String name, String mobile, String email, JoinUsCallback joinUsCallback);


}
