package com.optimagrowth.licensingservice.license.controller;

import static java.util.Collections.singletonMap;

import com.optimagrowth.licensingservice.license.model.utils.ErrorMessage;
import com.optimagrowth.licensingservice.license.model.utils.ResponseWrapper;
import com.optimagrowth.licensingservice.license.model.utils.RestErrorList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    public @ResponseBody ResponseEntity<ResponseWrapper> handleException(
        HttpServletRequest request,
        ResponseWrapper responseWrapper
    ) {
        return ResponseEntity.ok(responseWrapper);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseWrapper> handleIOException(
        HttpServletRequest request,
        RuntimeException e
    ) {
        RestErrorList errorList = new RestErrorList(
            HttpStatus.NOT_ACCEPTABLE,
            ErrorMessage.builder().message(e.getMessage()).detail(e.getMessage()).build()
        );
        ResponseWrapper responseWrapper = new ResponseWrapper(
            null,
            singletonMap("status", HttpStatus.NOT_ACCEPTABLE),
            errorList
        );

        return ResponseEntity.ok(responseWrapper);
    }
}
