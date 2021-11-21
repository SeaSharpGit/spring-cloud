package com.sea.springcloud.common.web.common;

import com.sea.springcloud.common.web.entity.MyResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public MyResult<String> handlerThrowable(Throwable e, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        log.error(url + "：" + e.getLocalizedMessage());
        return MyResult.error(e.getLocalizedMessage());
    }

}
