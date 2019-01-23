package com.hyzs.cidyth.core.exception;

import com.google.common.collect.Maps;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Map;

/**
 * Created by 1 on 2018/7/5.
 */
@RestControllerAdvice
public class GlobalException {

    /**
     * ServiceException异常处理
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity serviceException(ServiceException e){
        Map<String, Object> mapResult = Maps.newHashMap();
        mapResult.put("code", e.getErrCode());
        mapResult.put("message", e.getMessage());
        ResponseEntity entity = new ResponseEntity(mapResult, HttpStatus.OK);
        return entity;
    }

    /**
     * MethodArgumentNotValidException异常处理
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, Object> mapResult = Maps.newHashMap();
        mapResult.put("code", "2000");
        mapResult.put("message", "参数校验失败");
        ResponseEntity entity = new ResponseEntity(mapResult, HttpStatus.OK);
        return entity;
    }

    /**
     * ConstraintViolationException异常处理
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(ConstraintViolationException e){
        Map<String, Object> mapResult = Maps.newHashMap();
        mapResult.put("code", "2000");
        mapResult.put("message", "参数校验失败");
        ResponseEntity entity = new ResponseEntity(mapResult, HttpStatus.OK);
        return entity;
    }

    /**
     * ConstraintViolationException异常处理
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity bindException(BindException e){
        Map<String, Object> mapResult = Maps.newHashMap();
        mapResult.put("code", "2000");
        mapResult.put("message", "参数校验失败");
        ResponseEntity entity = new ResponseEntity(mapResult, HttpStatus.OK);
        return entity;
    }



}
