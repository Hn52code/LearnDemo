package com.zhn.demo.spring.web.res;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    /* 400错误，处理ServletRequestBindingException， */
    @ExceptionHandler({
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            ServletRequestBindingException.class,
    })
    public Result handle400Exception1(HttpServletRequest request) {
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "请求错误：丢失请求参数",
                request.getRequestURI());
    }

    /* 400错误，MissingServletRequestPartException，所需请求部分丢失 */
    @ExceptionHandler(MissingServletRequestPartException.class)
    public Result handle400Exception2(HttpServletRequest request) {
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "请求错误：所需请求部分丢失，multipart",
                request.getRequestURI());
    }

    /* 400 请求错误 */
    @ExceptionHandler({
            ConversionNotSupportedException.class,
            TypeMismatchException.class,
    })
    public Result handle400Exception3(HttpServletRequest request) {
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "请求错误：类型不匹配",
                request.getRequestURI());
    }

    /* 400 请求错误 */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handle400Exception4(HttpServletRequest request) {
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "错误请求",
                request.getRequestURI());
    }

    @ExceptionHandler(BindException.class)
    public Result handle400Exception5(HttpServletRequest request, BindException e) {
        StringBuilder buffer = new StringBuilder();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        for (ObjectError err : errors) {
            buffer.append(err.getDefaultMessage()).append(";");
        }
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "请求错误：参数绑定异常" + buffer,
                request.getRequestURI());
    }

    /* 400 错误中，接口方法中被验证注解修饰的复杂类型参时，验证错误时抛出异常 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handle400Exception6(HttpServletRequest request,
                                      MethodArgumentNotValidException e) {
        StringBuilder buffer = new StringBuilder();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        for (ObjectError err : errors) {
            buffer.append(err.getDefaultMessage()).append(";");
        }
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "请求错误：无效参数，" + buffer,
                request.getRequestURI());
    }

    /* 400错误中，接口方法中被验证注解修饰的简单类型参时，验证错误抛出该异常 */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handle400Exception7(HttpServletRequest request,
                                      ConstraintViolationException e) {
        StringBuilder buffer = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            for (ConstraintViolation<?> constraintViolation : constraintViolations) {
                buffer.append(constraintViolation.getMessage()).append(";");
            }
        }
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "请求错误：无效参数，" + buffer,
                request.getRequestURI());
    }

    /* 404 资源找不到错误 */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handle404Exception(HttpServletRequest request) {
        return ResultUtil.createHttpExcResult(HttpStatus.NOT_FOUND.value(),
                "找不到服务",
                request.getRequestURI());
    }

    /* 405 方法不允许错误 */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handle405Exception(HttpServletRequest request) {
        return ResultUtil.createHttpExcResult(HttpStatus.METHOD_NOT_ALLOWED.value(),
                "请求错误：方法不被允许，" + request.getMethod(),
                request.getRequestURI());
    }

    /* 406 不接受 无法使用请求的内容特性响应请求的网页 */
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public Result handle406Exception(HttpServletRequest request) {
        return ResultUtil.createHttpExcResult(HttpStatus.NOT_ACCEPTABLE.value(),
                "请求错误：不接受响应类型",
                request.getRequestURI());
    }

    /* 415  服务器无法处理请求附带的媒体格式 */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handle415Exception(HttpServletRequest request) {
        return ResultUtil.createHttpExcResult(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                "请求错误：不支持请求媒体",
                request.getRequestURI());
    }

    /* 500 服务器内部错误 */
    // HttpMessageNotWritableException.class
    @ExceptionHandler(Exception.class)
    public Result handle500Exception(HttpServletRequest request, Exception e) {
        return ResultUtil.createHttpExcResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "服务端错误：" + e.getMessage(),
                request.getRequestURI());
    }

    /* 503 服务不可用 */
    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public Result handle503Exception(HttpServletRequest request) {
        return ResultUtil.createHttpExcResult(HttpStatus.SERVICE_UNAVAILABLE.value(),
                "服务端错误：不可用，异步请求超时",
                request.getRequestURI());
    }

    /* 自定义错误 */
    /* 业务异常包含所有业务异常捕捉 */
    @ExceptionHandler(CustomException.class)
    public Result handleLoginException(HttpServletRequest request, CustomException e) {
        return ResultUtil.createCustomExcResult(e.getCode(), "业务错误：" + e.getMessage(), request.getRequestURI());
    }

}
