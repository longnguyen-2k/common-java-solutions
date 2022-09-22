import java.io.Serializable;
import java.util.List;

public class SuccessObject implements Serializable {
    private String code;
    private String message;
    private String type;
    private List<ArgumentObject>argumentObjects;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
