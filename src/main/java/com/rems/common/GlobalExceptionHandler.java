package com.rems.common;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(HttpServletRequest req, Exception ex) throws Exception {
        
    	//Storing stack trace inside StringWriter 
    	StringWriter errors = new StringWriter();
    	ex.printStackTrace(new PrintWriter(errors));
    	
    	logger.error(errors.toString());
        
        ModelAndView modelAndView = new ModelAndView();                
        modelAndView.setViewName("error/exception");
        modelAndView.addObject("url", req.getRequestURI());
        
        return modelAndView;
    }

}
