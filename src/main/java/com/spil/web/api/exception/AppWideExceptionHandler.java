package com.spil.web.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class AppWideExceptionHandler {

    Logger logger = LoggerFactory.getLogger(AppWideExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    public String globalExceptionHandler(Throwable t){
        logger.error(null,t);
        return "Something went wrong";
    }
}
