package app.bunchoffools.codenicely.bunchoffools.join_us;

import app.bunchoffools.codenicely.bunchoffools.join_us.model.data.JoinUsData;

/**
 * Created by meghal on 12/10/16.
 */

public interface JoinUsCallback {

    void onSuccess(JoinUsData joinUsData);
    void onFailure();


}
