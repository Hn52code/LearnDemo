package com.zhn.demo.spring.web.exc;

import com.zhn.demo.spring.web.restresult.ApiResponseResult;
import com.zhn.demo.spring.web.restresult.ApiResultUtil;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * 全局异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /* http请求 异常错误 */

    /* 400 请求错误 */
    @ExceptionHandler({
            ServletRequestBindingException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            MissingServletRequestPartException.class,
            MissingServletRequestParameterException.class,
    })
    public ApiResponseResult handle400Exception(HttpServletRequest request) {
        return ApiResultUtil.createHttpExceptionResult(HttpStatus.BAD_REQUEST.value(),
                "错误请求",
                request.getRequestURI());
    }

    @ExceptionHandler(BindException.class)
    public ApiResponseResult handle400BindingException(HttpServletRequest request, BindException e) {
        StringBuilder buffer = new StringBuilder();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        for (ObjectError err : errors) {
            buffer.append(err.getDefaultMessage()).append(";");
        }
        return ApiResultUtil.createHttpExceptionResult(HttpStatus.BAD_REQUEST.value(),
                "请求参数绑定错误：" + buffer,
                request.getRequestURI());
    }

    /* 400 错误中，接口方法中被验证注解修饰的复杂类型参时，验证错误时抛出异常 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponseResult handle400NotValidException(HttpServletRequest request,
                                                        MethodArgumentNotValidException e) {
        StringBuilder buffer = new StringBuilder();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        for (ObjectError err : errors) {
            buffer.append(err.getDefaultMessage()).append(";");
        }
        return ApiResultUtil.createHttpExceptionResult(HttpStatus.BAD_REQUEST.value(),
                "请求参数验证错误,obj类型--: " + buffer,
                request.getRequestURI());
    }

    /* 400错误中，接口方法中被验证注解修饰的简单类型参时，验证错误抛出该异常 */
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponseResult handle400ConstraintViolationException(HttpServletRequest request,
                                                                   ConstraintViolationException e) {
        StringBuilder buffer = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            for (ConstraintViolation constraintViolation : constraintViolations) {
                buffer.append(constraintViolation.getMessage()).append(";");
            }
        }
        return ApiResultUtil.createHttpExceptionResult(HttpStatus.BAD_REQUEST.value(),
                "请求参数验证错误,简单类型--: " + buffer,
                request.getRequestURI());
    }

    /* 404 资源找不到错误 */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResponseResult handle404Exception(HttpServletRequest request) {
        return ApiResultUtil.createHttpExceptionResult(HttpStatus.NOT_FOUND.value(),
                "找不到资源",
                request.getRequestURI());
    }

    /* 405 方法不允许错误 */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResponseResult handle405Exception(HttpServletRequest request) {
        return ApiResultUtil.createHttpExceptionResult(HttpStatus.METHOD_NOT_ALLOWED.value(),
                "请求方法不允许",
                request.getRequestURI());
    }

    /* 406 不接受 无法使用请求的内容特性响应请求的网页 */
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ApiResponseResult handle406Exception(HttpServletRequest request) {
        return ApiResultUtil.createHttpExceptionResult(HttpStatus.NOT_ACCEPTABLE.value(),
                "拒绝请求",
                request.getRequestURI());
    }

    /* 415  服务器无法处理请求附带的媒体格式 */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ApiResponseResult handle415Exception(HttpServletRequest request) {
        return ApiResultUtil.createHttpExceptionResult(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                "不支持的媒体类型",
                request.getRequestURI());
    }

    /* 500 服务器内部错误 */
    @ExceptionHandler({
            MissingPathVariableException.class,
            ConversionNotSupportedException.class,
            HttpMessageNotWritableException.class,
            Exception.class
    })
    public ApiResponseResult handle500Exception(HttpServletRequest request) {
        return ApiResultUtil.createHttpExceptionResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "服务内部异常",
                request.getRequestURI());
    }

    /* 503 服务不可用 */
    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ApiResponseResult handle503Exception(HttpServletRequest request) {
        return ApiResultUtil.createHttpExceptionResult(HttpStatus.SERVICE_UNAVAILABLE.value(),
                "服务不可用",
                request.getRequestURI());
    }

    /*自定义错误*/
    /* 业务异常包含所有业务异常捕捉 */
    @ExceptionHandler(BusinessException.class)
    public ApiResponseResult handleLoginException(HttpServletRequest request, BusinessException e) {
        return ApiResultUtil.createBusinessExceptionResult(e.getCode(), e.getMessage(), request.getRequestURI());
    }

}
