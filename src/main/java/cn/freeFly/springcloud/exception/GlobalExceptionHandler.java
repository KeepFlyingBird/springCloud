package cn.freeFly.springcloud.exception;

import cn.freeFly.springcloud.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //处理自定义的异常
    @ExceptionHandler(AsyncException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BaseResponse AsyncFailError(AsyncException e){
        log.error("异步操作异常：",e);
        return BaseResponse.failed(e.getMessage());
    }

    //其他未处理的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BaseResponse exceptionHandler(Exception e){
        log.error("系统异常：",e);
        return BaseResponse.failed(e.getMessage());
    }
}
