package io.ideploy.web.controller;

import io.ideploy.common.constants.ApiCode;
import io.ideploy.common.entity.RestResult;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author code4china
 * @description 系统默认的异常处理拦截组件
 * @date Created in 16:38 2018/8/29
 */
@ControllerAdvice
public class DefaultControllerAdvice {

    private static Logger logger= LoggerFactory.getLogger(DefaultControllerAdvice.class);

    private static final int MAX_ERR_LEN = 80;

    @ExceptionHandler(value = Throwable.class)
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Throwable e) {
        logger.error("系统内部异常", e);

        /** 重定向500错误页面 **/
        List<MediaType> acceptMedias = MediaType.parseMediaTypes(request.getHeader("Accept"));
        if(isMediaAccept(acceptMedias, MediaType.TEXT_HTML)){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            modelAndView.setViewName("/500.html");
            return modelAndView;
        }

        /** ajax接口请求，返回默认json默认错误 **/
        return buildApiResult(e);
    }

    private ResponseEntity buildApiResult(Throwable e){
        String message = e.getMessage();
        if(StringUtils.isBlank(message)){
            message = "系统内部错误";
        } else if(message.length() > MAX_ERR_LEN){
            message = "服务器异常:"+message.substring(0, MAX_ERR_LEN - 3) + "...";
        }
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(new RestResult(ApiCode.FAILURE, message, null), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean isMediaAccept(List<MediaType> mediaTypes, MediaType mediaType){
        for (MediaType accept: mediaTypes){
            if(mediaType.includes(accept)){
                return true;
            }
        }
        return false;
    }

}
