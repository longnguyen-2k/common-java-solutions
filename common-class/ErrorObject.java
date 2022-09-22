import java.io.Serializable;
import java.util.List;

public class ErrorObject implements Serializable {
    private String code;
    private String message;
    private String type;
    private List<ArgumentObject> argumentObjects;
    private String fieldId;

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

    public List<ArgumentObject> getArgumentObjects() {
        return argumentObjects;
    }

    public void setArgumentObjects(List<ArgumentObject> argumentObjects) {
        this.argumentObjects = argumentObjects;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }
}
