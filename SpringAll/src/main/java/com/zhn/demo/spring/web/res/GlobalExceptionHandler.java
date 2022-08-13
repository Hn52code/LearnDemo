package com.zhn.demo.spring.web.res;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * 全局异常：本bean中定义了全局异常统一处理
 * 由于：是针对restful风格的统一返回，所以使用@RestControllerAdvice，这意味这里定义的所有方法都返回json，
 * 但是对于一些接口本身不需要返回json，如文件下载（它用到了其他消息转换器（ArrayHttpMessageConvert，返回的是二进制）），
 * 页面跳转等，仍然要返回json，且该接口内发生异常将不被（标注了该异常类，且返回是json的异常处理方法）捕捉。
 * 所以使用@ControllerAdvice，对于一些定义返回json的接口，捕获该接口的异常的专用异常处理方法加入标注@ResponseBody
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /* 400错误，丢失URI参数 */
    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle400MissUriExc(HttpServletRequest request, MissingPathVariableException e) {
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "请求错误-> 丢失请求参数：URI " + e.getVariableName(),
                request.getRequestURI());
    }

    /* 400错误，丢失query参数 */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle400MissParasExc(HttpServletRequest request, MissingServletRequestParameterException e) {
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "请求错误-> 丢失请求参数：" + e.getParameterName() + ":" + e.getParameterType(),
                request.getRequestURI());
    }

    /* 400错误，丢失参数 */
    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle400MissParas2Exc(HttpServletRequest request, ServletRequestBindingException e) {
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "请求错误-> 丢失请求参数",
                request.getRequestURI());
    }

    /* 400 请求错误，类型转换不允许 */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle400TypeMismatchExc(HttpServletRequest request, MethodArgumentTypeMismatchException e) {
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "请求错误-> 请求参数与预定类型不符，参数(" + e.getName() + ")类型为" + e.getRequiredType(),
                request.getRequestURI());
    }

    /* 400 请求错误，请求参数不可读 */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle400NotReadableExc(HttpServletRequest request) {
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "错误请求-> 请求参数不可读，请确认content-type类型",
                request.getRequestURI());
    }

    /* 400 请求错误，接口方法中被验证注解修饰的“表单-复杂类型”参时，验证错误时抛出异常 */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle400BindExceptionExc(HttpServletRequest request, BindException e) {
        StringBuilder buffer = new StringBuilder();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        errors.forEach(k -> buffer.append(k.getDefaultMessage()).append("；"));
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "请求错误-> 请求参数无效：" + buffer,
                request.getRequestURI());
    }

    /* 400 错误中，接口方法中被验证注解修饰的“json-复杂类型”参时，验证错误时抛出异常 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle400NotValidExc(HttpServletRequest request, MethodArgumentNotValidException e) {
        StringBuilder buffer = new StringBuilder();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        errors.forEach(k -> buffer.append(k.getDefaultMessage()).append("；"));
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "请求错误-> 请求参数无效：" + buffer,
                request.getRequestURI());
    }

    /**
     * 400错误中，接口方法中被验证注解修饰的“简单”类型参时，验证错误抛出该异常。
     * 此处有个小BUG，当注解使用@Valid来注解参数后跟随BindingResult实例后，所产生的错误由本异常类型接收
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle400ConstraintExc(HttpServletRequest request, ConstraintViolationException e) {
        StringBuilder buffer = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        violations.forEach(k -> buffer.append(k.getMessage()).append("；"));
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "请求错误-> 请求参数无效：" + buffer,
                request.getRequestURI());
    }

    /* 400错误，丢失Multipart参数 */
    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle400UploadFileExc(HttpServletRequest request, MissingServletRequestPartException e) {
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "请求错误-> 文件参数丢失，" + e.getRequestPartName(),
                request.getRequestURI());
    }

    /* 400错误中，处理文件上传异常 */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle400UploadFileExc(HttpServletRequest request, MaxUploadSizeExceededException e) {
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "请求错误-> 文件上传超限，允许文件最大" + (e.getMaxUploadSize() / 1024 / 1024) + "MB",
                request.getRequestURI());
    }

    /* 400错误中，处理文件上传异常 */
    @ExceptionHandler(FileUploadException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle400UploadFileExc(HttpServletRequest request, FileUploadException e) {
        return ResultUtil.createHttpExcResult(HttpStatus.BAD_REQUEST.value(),
                "请求错误-> 文件上传错误，" + e.getMessage(),
                request.getRequestURI());
    }

    /* 404 资源找不到错误 */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handle404Exc(NoHandlerFoundException e) {
        return ResultUtil.createHttpExcResult(HttpStatus.NOT_FOUND.value(),
                "找不到服务，URI：" + e.getRequestURL() + " 方法：" + e.getHttpMethod(), null);
    }

    /* 405 方法不允许错误 */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result handle405Exc(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
        return ResultUtil.createHttpExcResult(HttpStatus.METHOD_NOT_ALLOWED.value(),
                "请求错误-> 方法不被允许，支持方法：" + Arrays.toString(e.getSupportedMethods()),
                request.getRequestURI());
    }

    /* 406 不接受 无法使用请求的内容特性响应请求的网页 */
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Result handle406Exc(HttpServletRequest request, HttpMediaTypeNotAcceptableException e) {
        return ResultUtil.createHttpExcResult(HttpStatus.NOT_ACCEPTABLE.value(),
                "请求错误-> 拒绝响应类型：" + e.getMessage(),
                request.getRequestURI());
    }

    /* 415 服务器无法处理请求附带的媒体格式 */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public Result handle415Exc(HttpServletRequest request, HttpMediaTypeNotSupportedException e) {
        return ResultUtil.createHttpExcResult(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                "请求错误-> 媒体类型不支持，content-type：" + e.getContentType() + "，请改为：" + e.getSupportedMediaTypes(),
                request.getRequestURI());
    }

    /* 500 服务器内部错误 */
    // HttpMessageNotWritableException.class
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handle500Exc(HttpServletRequest request, Exception e) {
        return ResultUtil.createHttpExcResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "服务端错误-> " + e.getMessage(),
                request.getRequestURI());
    }

    /* 503 服务不可用 */
    @ExceptionHandler(AsyncRequestTimeoutException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Result handle503Exc(HttpServletRequest request) {
        return ResultUtil.createHttpExcResult(HttpStatus.SERVICE_UNAVAILABLE.value(),
                "服务端错误-> 不可用，异步请求超时",
                request.getRequestURI());
    }

    /* 自定义错误 */
    /* 业务异常包含所有业务异常捕捉 */
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleCustomExc(HttpServletRequest request, CustomException e) {
        return ResultUtil.createCustomExcResult(e.getCode(), "业务错误-> " + e.getMessage(), request.getRequestURI());
    }

}
