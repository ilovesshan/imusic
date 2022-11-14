package com.ilovesshan.imusic.handler;

import com.ilovesshan.imusic.common.R;
import com.ilovesshan.imusic.exception.CustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/14
 * @description:
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public R handleException(CustomException exception) {
        exception.printStackTrace();
        return R.fail(exception.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R handleException(Exception exception) {
        exception.printStackTrace();
        return R.error(exception.getMessage(), null);
    }
}
