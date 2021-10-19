package com.fdkj.ky.error;

import com.fdkj.ky.base.CusResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * 统一错误处理
 *
 * @author wyt
 */
@ControllerAdvice
public class CusExceptionHandler {
    private final String ERR_VALID = "参数校验失败";


    /**
     * 判断是否是Ajax请求
     *
     * @param request req
     * @return 是否是ajax请求
     */
    private boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null &&
                "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }

    /**
     * 处理自定义异常
     *
     * @param req req
     * @param e   异常
     * @return ret
     */
    @ExceptionHandler(value = BusinessException.class)
    public Object cusException(HttpServletRequest req, BusinessException e) {
        if (isAjax(req)) {
            CusResponseBody cusResponseBody = CusResponseBody.error(e.getCode(), e.getMessage());
            HttpStatus httpStatus = HttpStatus.resolve(e.getCode());
            return new ResponseEntity<>(cusResponseBody, httpStatus != null ? httpStatus : HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMsg", e.getMessage());
            modelAndView.addObject("errorCode", e.getCode());
            return modelAndView;
        }
    }

    /**
     * 处理参数验证异常
     *
     * @param req req
     * @param e   异常
     * @return ret
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object ValidException(HttpServletRequest req, MethodArgumentNotValidException e) {
        if (isAjax(req)) {
            CusResponseBody cusResponseBody = CusResponseBody.error(HttpStatus.BAD_REQUEST.value(), ERR_VALID);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.BAD_REQUEST);
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMsg", e.getMessage());
            modelAndView.addObject("errorCode", HttpStatus.BAD_REQUEST.value());
            return modelAndView;
        }
    }

    /**
     * 处理参数验证异常
     *
     * @param req req
     * @param e   异常
     * @return ret
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Object constraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException e) {
        if (isAjax(req)) {
            CusResponseBody cusResponseBody = CusResponseBody.error(HttpStatus.BAD_REQUEST.value(), ERR_VALID);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.BAD_REQUEST);
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMsg", e.getMessage());
            modelAndView.addObject("errorCode", HttpStatus.BAD_REQUEST.value());
            return modelAndView;
        }
    }

    /**
     * 处理其他异常
     *
     * @param req req
     * @param e   异常
     * @return ret
     */
    @ExceptionHandler(value = Exception.class)
    public Object exception(HttpServletRequest req, Exception e) {
        if (isAjax(req)) {
            CusResponseBody cusResponseBody = CusResponseBody.error(500, e.getMessage());
            return new ResponseEntity<>(cusResponseBody, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMsg", e.getMessage());
            modelAndView.addObject("errorCode", 500);
            return modelAndView;
        }
    }
}
