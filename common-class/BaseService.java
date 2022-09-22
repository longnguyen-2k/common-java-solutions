
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.List;

public abstract class BaseService {
    private static Logger logger = LoggerFactory.getLogger(BaseService.class);
    public static final String[] ignoreProperties = {"createdBy","createdDateTime","updatedBy","updatedDateTime"};
    protected static void mapSuccessValue(BaseObject request,BaseObject response){
        response.setHeader(new HeaderObject());
        BeanUtils.copyProperties(request.getHeader(),response.getHeader());
        response.getHeader().setSuccess(true);
    }
    protected  static void mapSuccessValue(BaseObject request,BaseObject response,String statusCode){
        mapSuccessValue(request,response);
        response.getHeader().setStatusCode(statusCode);
    }

    protected static  void mapFailValue(BaseObject request, BaseObject response, String statusCode,String message){
        response.setHeader(new HeaderObject());
        BeanUtils.copyProperties(request.getHeader(),response.getHeader());
        response.getHeader().setSuccess(false);
        response.getHeader().setStatusCode(statusCode);
        response.getHeader().setStatusMessage(message);
    }
    protected static void mapFailValue(BaseObject request, BaseObject response, String statusCode, String message, List<ErrorObject> errorObjects){
        mapFailValue(request,response,statusCode,message);
        if (errorObjects!=null){
            response.getHeader().setErrorObjects(errorObjects);
        }
    }

}
