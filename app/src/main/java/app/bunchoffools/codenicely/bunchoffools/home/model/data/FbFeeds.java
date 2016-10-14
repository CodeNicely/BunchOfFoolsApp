package app.bunchoffools.codenicely.bunchoffools.home.model.data;

/**
 * Created by meghal on 15/10/16.
 */

public class FbFeeds {

    private FbPaging paging;
    private FbDetails data;

    public FbFeeds(FbPaging paging, FbDetails data) {
        this.paging = paging;
        this.data = data;
    }

    public FbPaging getPaging() {
        return paging;
    }

    public FbDetails getData() {
        return data;
    }
}
