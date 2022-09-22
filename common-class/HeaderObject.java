import  java.io.Serializable;
import java.util.List;

public class HeaderObject implements  Serializable{
    private String statusCode;
    private Boolean success;
    private String statusMessage;

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    private List<ErrorObject> errorObjects;
    private List<SuccessObject> successObjects;
    private List<String> roleCodes;
    private String userId;
    private String userName;
    private String ipAddress;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<ErrorObject> getErrorObjects() {
        return errorObjects;
    }

    public void setErrorObjects(List<ErrorObject> errorObjects) {
        this.errorObjects = errorObjects;
    }

    public List<SuccessObject> getSuccessObjects() {
        return successObjects;
    }

    public void setSuccessObjects(List<SuccessObject> successObjects) {
        this.successObjects = successObjects;
    }

    public List<String> getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(List<String> roleCodes) {
        this.roleCodes = roleCodes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}