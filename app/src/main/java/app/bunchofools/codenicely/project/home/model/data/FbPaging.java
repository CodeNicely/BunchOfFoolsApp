package app.bunchofools.codenicely.project.home.model.data;

/**
 * Created by meghal on 15/10/16.
 */

public class FbPaging {

    private String previous;
    private String next;

    public FbPaging(String previous, String next) {
        this.previous = previous;
        this.next = next;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

}
