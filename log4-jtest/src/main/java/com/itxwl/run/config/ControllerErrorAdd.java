package com.itxwl.run.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Auther: 薛
 * @Date: 2020/6/2 09:12
 * @Description:
 */
@ControllerAdvice
public class ControllerErrorAdd {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> integerResponseEntity(RuntimeException rn)
    {
       return ResponseEntity.status(500).body(rn.getMessage());
    }
}
