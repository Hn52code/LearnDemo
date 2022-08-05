package com.zhn.demo.webexample.common;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

/**
 * 全局异常：本bean中定义了全局异常统一处理
 * 由于：是针对restful风格的统一返回，所以使用@RestControllerAdvice，这意味这里定义的所有方法都返回json，
 * 但是对于一些接口本身不需要返回json，如文件下载（它用到了其他消息转换器（ArrayHttpMessageConvert，返回的是二进制）），
 * 页面跳转等，仍然要返回json，且该接口内发生异常将不被（标注了该异常类，且返回是json的异常处理方法）捕捉。
 * 所以使用@ControllerAdvice，对于一些定义返回json的接口，捕获该接口的异常的专用异常处理方法加入标注@ResponseBody
 * 但是有
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /* http请求 异常错误 */


    /**
     * 400 请求错误：
     * 包括SpringMVC默认异常，还额外加入MaxUploadSizeExceededException(文件过大)和FileNotFoundException(上传文件，未选择文件)
     */
    @ExceptionHandler({
            ServletRequestBindingException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class,
            MissingServletRequestPartException.class,
            FileNotFoundException.class,
            MaxUploadSizeExceededException.class,
            BindException.class,
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class
    })
    public void handle400Exception(Exception e, HttpServletResponse response) throws IOException {
        WebResult errResult;
        if (e instanceof BindException) {
            BindException exception = (BindException) e;
            String errMsg = exception.getBindingResult().getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
            errResult = new WebResult(HttpStatus.BAD_REQUEST, "错误请求，参数绑定错误：" + errMsg);
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            // String errMsg = notValid.getBindingResult().getAllErrors().toString();
            String errMsg = exception.getBindingResult().getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
            // 上面方式简单明了，下面方式对后端友好
            // String errMsg = notValid.getBindingResult().getFieldError().toString();
            errResult = new WebResult(HttpStatus.BAD_REQUEST, "错误请求，对象参数验证错误：" + errMsg);
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException exception = (ConstraintViolationException) e;
            String errMsg = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(";"));
            errResult = new WebResult(HttpStatus.BAD_REQUEST, "错误请求，简单参数验证错误：" + errMsg);
        } else if (e instanceof FileNotFoundException) {
            // 读取文件异常
            errResult = new WebResult(HttpStatus.BAD_REQUEST, "请检查参数：" + e.getMessage());
        } else if (e instanceof MaxUploadSizeExceededException) {
            MaxUploadSizeExceededException exception = (MaxUploadSizeExceededException) e;
            long maxSize = (exception.getMaxUploadSize() / 1024 / 1024);
            errResult = new WebResult(HttpStatus.BAD_REQUEST,
                    "文件太大了，允许最大文件是" + maxSize + " MB");
        } else {
            errResult = new WebResult(HttpStatus.BAD_REQUEST,
                    "请检查参数：参数 -（丢失，无效，名称/值类型错误）");
        }
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(errResult));
        writer.close();
    }

    /* 404 资源找不到错误 */
    @ExceptionHandler({NoHandlerFoundException.class})
    public void handle404Exception(NoHandlerFoundException e, HttpServletResponse response) throws IOException {
        WebResult errResult = new WebResult(HttpStatus.NOT_FOUND, "资源找不到");
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(errResult));
        writer.close();
    }

    /* 405 方法不允许错误 */
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public WebResult handle405Exception(HttpRequestMethodNotSupportedException e) {
        return new WebResult(HttpStatus.METHOD_NOT_ALLOWED, "请求方法不被允许");
    }

    /* 406 不接受 无法使用请求的内容特性响应请求的网页 */
    // 客户端请求期望响应的媒体类型与服务器响应的媒体类型不一致造成的
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public WebResult handle406Exception(HttpMediaTypeNotAcceptableException e) {
        return new WebResult(HttpStatus.NOT_ACCEPTABLE, "客户端不接受服务器响应的媒体类型，请检查accept");
    }

    /* 415  服务器无法处理请求附带的媒体格式 */
    // 服务器不支持客户端请求的媒体类型
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public WebResult handle415Exception(HttpMediaTypeNotSupportedException e) {
        return new WebResult(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "不支持的媒体类型，请检查content-type");
    }

    /* 500 服务器内部错误 */
    @ResponseBody
    @ExceptionHandler({
            MissingPathVariableException.class,
            ConversionNotSupportedException.class,
            HttpMessageNotWritableException.class,
            Exception.class
    })
    public WebResult handle500Exception(Exception e) {
        log.error(e.getMessage());
        return new WebResult(HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    /* 自定义异常 */
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public WebResult handleBusinessException(BusinessException e) {
        return new WebResult(e.getErrNo(), e.getMessage());
    }

}
