import java.io.Serializable;

public class BaseObject implements Serializable {
    private HeaderObject headerObject = new HeaderObject();
    private PagingObject pagingObject;

    public PagingObject getPagingObject() {
        return pagingObject;
    }

    public void setPagingObject(PagingObject pagingObject) {
        this.pagingObject = pagingObject;
    }

    public HeaderObject getHeader() {
        return headerObject;
    }

    public void setHeader(HeaderObject headerObject) {
        this.headerObject = headerObject;
    }
}
