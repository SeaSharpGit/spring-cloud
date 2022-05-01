package com.sea.springcloud.common.web.handler;

import com.sea.springcloud.common.core.entity.R;
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
    public R<Object> handlerThrowable(Throwable e, HttpServletRequest request) {
//        String url = request.getRequestURL().toString();
        log.error("全局异常：",e);
        return R.error(e.getLocalizedMessage());
    }

}
