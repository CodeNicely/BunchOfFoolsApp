package app.bunchoffools.codenicely.bunchoffools.home.model.data;

import java.util.List;

/**
 * Created by meghal on 15/10/16.
 */

public class FbFeeds {

    private FbPaging paging;
    private List<FbDetails> data;

    public FbFeeds(FbPaging paging, List<FbDetails> data) {
        this.paging = paging;
        this.data = data;
    }

    public FbPaging getPaging() {
        return paging;
    }

    public List<FbDetails> getData() {
        return data;
    }
}
