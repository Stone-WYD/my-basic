package com.njxnet.yccc.config;

import cn.dev33.satoken.exception.DisableServiceException;
import cn.dev33.satoken.exception.NotLoginException;
import com.njxnet.framework.common.exception.BaseException;
import com.njxnet.framework.common.exception.BaseResultStatusCode;
import com.njxnet.framework.common.model.AjaxResult;
import com.njxnet.framework.common.utils.AjaxResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.njxnet.yccc.exception.ResultStatusCode.LOGIN_FREEZE;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 账号被冻结
    @ExceptionHandler(DisableServiceException.class)
    public AjaxResult onDisableServiceException(DisableServiceException de){
        // 异常信息输出
        log.error(de.getMessage());
        AjaxResult result = new AjaxResult<>(LOGIN_FREEZE.getCode(), LOGIN_FREEZE.getName());
        return result;
    }

    // 登录或者鉴权异常处理
    @ExceptionHandler(NotLoginException.class)
    public AjaxResult onNotLoginException(NotLoginException nle){
        // 异常信息输出
        log.error(nle.getMessage());

        // 判断场景值，定制化异常信息
        String message;
        if(nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "token过期，请重新登录";
        }
        else if(nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token无效";
        }
        else if(nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token过期，请重新登录";
        }
        else if(nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "token已被顶下线";
        }
        else if(nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "token已被踢下线";
        }
        else {
            message = "当前会话未登录";
        }
        AjaxResult result = AjaxResultUtil.getDefaultFalseAjaxResult(new AjaxResult<>());
        result.setMessage(message);
        return result;
    }

    // 校验异常处理 BindException
    @ExceptionHandler(BindException.class)
    public AjaxResult onBindException(BindException e){
        // 异常信息输出到日志
        e.printStackTrace();

        AjaxResult myResult = AjaxResultUtil.getDefaultFalseAjaxResult(new AjaxResult<>());
        // 获取校验结果
        BindingResult bindingResult = e.getBindingResult();
        // 获取校验错误的信息
        Map<String, String> errorMap = new HashMap<>();
        if (bindingResult != null) {
            bindingResult.getFieldErrors().forEach(fe->errorMap.put(fe.getField(), fe.getDefaultMessage()));
        }
        // 将错误信息封装成Result后返回
        myResult.setCode(BaseResultStatusCode.ERROR.getCode());
        myResult.setData(errorMap);
        return myResult;

    }

    // 校验异常处理 MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult onMethodArgumentNotValidException(MethodArgumentNotValidException e){
        // 异常信息输出到日志
        e.printStackTrace();

        AjaxResult myResult = AjaxResultUtil.getDefaultFalseAjaxResult(new AjaxResult<>());
        // 获取校验结果
        BindingResult bindingResult = e.getBindingResult();
        // 获取校验错误的信息
        Map<String, String> errorMap = new HashMap<>();
        if (bindingResult != null) {
            bindingResult.getFieldErrors().forEach(fe->errorMap.put(fe.getField(), fe.getDefaultMessage()));
        }
        // 将错误信息封装成Result后返回
        myResult.setCode(BaseResultStatusCode.ERROR.getCode());
        myResult.setData(errorMap);
        return myResult;
    }

    @ExceptionHandler(BaseException.class)
    public AjaxResult onBaseException(BaseException be) {
        AjaxResult ajaxResult = new AjaxResult();
        Integer errorCode = be.getCode();
        ajaxResult.setCode(errorCode);
        ajaxResult.setMessage(be.getMessage());
        return ajaxResult;
    }

    @ExceptionHandler(RuntimeException.class)
    public AjaxResult onRuntimeException(RuntimeException runtimeException) {
        BaseException be;
        if (runtimeException.getCause() instanceof BaseException){
            be = (BaseException) runtimeException.getCause();
        } else throw runtimeException;

        AjaxResult ajaxResult = new AjaxResult();
        Integer errorCode = be.getCode();
        ajaxResult.setCode(errorCode);
        ajaxResult.setMessage(be.getMessage());
        return ajaxResult;
    }

    // 加入一个通用的全局异常处理，主要用于打印日志
    @ExceptionHandler(Exception.class)
    public AjaxResult onException(Exception e) throws Exception{
        // 将异常打印出来
        log.error(e.getMessage());
        // 返回默认结果
        AjaxResult<String> ajaxResult = new AjaxResult();
        Integer errorCode = BaseResultStatusCode.ERROR.getCode();
        ajaxResult.setCode(errorCode);
        ajaxResult.setMessage(BaseResultStatusCode.ERROR.getName());
        return ajaxResult;
    }

}
