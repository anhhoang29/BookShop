package com.r2s.config;

import com.r2s.enums.ErrorCodeEnum;
import com.r2s.model.ResponseBuild;
import com.r2s.model.ResponseModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ResponseBuild responseBuild;
    @Autowired
    private MessageSource messageSource;


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ResponseModel response = new ResponseModel(ErrorCodeEnum.UNAUTHORIZED);
        try {
            ErrorCodeEnum errorCode;
            if (status.value() == 401) {
                errorCode = ErrorCodeEnum.UNAUTHORIZED;
            } else if (status.value() == 403) {
                errorCode = ErrorCodeEnum.FORBIDDEN;
            } else if (status.value() >= 400 && status.value() < 500) {
                errorCode = ErrorCodeEnum.BAD_REQUEST;
                logger.warn("REST-API user error" + StringUtils.join(ExceptionUtils.getRootCauseStackTrace(ex), ","));
            } else {
                errorCode = ErrorCodeEnum.INTERNAL_SERVER_ERROR;
                logger.error("REST-API server error" + StringUtils.join(ExceptionUtils.getRootCauseStackTrace(ex), ","));
            }
            response = new ResponseModel(errorCode, null);
            logger.warn(String.format("Internal exception -> %s", response.toString()));
        } catch (Exception e) {
            logger.warn("AppExceptionHandler -> handleExceptionInternal error" + StringUtils.join(ExceptionUtils.getRootCauseStackTrace(e), ","));
        }
        return new ResponseEntity(response, response.getStatus());
    }
}