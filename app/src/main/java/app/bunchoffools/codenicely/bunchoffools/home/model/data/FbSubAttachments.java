package app.bunchoffools.codenicely.bunchoffools.home.model.data;

import java.util.List;

/**
 * Created by meghal on 15/10/16.
 */

public class FbSubAttachments {

    private List<FbData> data;

    public FbSubAttachments(List<FbData> data) {
        this.data = data;
    }

    public List<FbData> getData() {
        return data;
    }

}
