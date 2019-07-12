package com.zlw.framework.web;

import com.zlw.commons.core.ErrorCode;
import com.zlw.commons.core.ResultData;
import com.zlw.commons.exception.GlobalBusinessException;
import com.zlw.commons.exception.GlobalRuntimeException;
import com.zlw.commons.logging.exception.GlobalLoggerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


public abstract class BaseController {


    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 统一处理 BusinessException 异常 ， 其余异常需要自己处理
     *
     * @param e ：BusinessException
     */
    @ExceptionHandler(GlobalBusinessException.class)
    @ResponseBody
    public ResultData<Object> businessException(GlobalBusinessException e) {
        if (e instanceof GlobalBusinessException) {
            String code = e.getCode();
            String message = e.getMessage();
            logger.error("GlobalBusinessException code : {} , message : {}", code, message);
            return ResultData.newResultData(code, message);
        }
        GlobalLoggerError.error(e);
        return ResultData.newResultData(ErrorCode.ADD_FAILOR, ErrorCode.ADD_FAILOR_MSG);
    }

    @ExceptionHandler(GlobalRuntimeException.class)
    @ResponseBody
    public ResultData<Object> runtimeException(GlobalRuntimeException e) {
        GlobalLoggerError.error(e);
        if (e instanceof GlobalRuntimeException) {
            String code = e.getCode();
            String message = e.getMessage();
            logger.error("GlobalBusinessException code : {} , message : {}", code, message);
            return ResultData.newResultData(code, message);
        }
        return ResultData.newResultData(ErrorCode.ADD_FAILOR, ErrorCode.ADD_FAILOR_MSG);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultData<Object> exception(Exception e) {
        GlobalLoggerError.error(e);
        if (e instanceof Exception) {
            String code = ErrorCode.FAILOR;
            String message = e.getMessage();
            logger.error("GlobalBusinessException code : {} , message : {}", code, message);
            return ResultData.newResultData(code, ErrorCode.FAILOR_MSG);
        }
        return ResultData.newResultData(ErrorCode.ADD_FAILOR, ErrorCode.ADD_FAILOR_MSG);
    }

}

