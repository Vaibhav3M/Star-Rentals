package com.sdm.StarRental.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

/**
 * The type Custom exception handler.
 */
@RestController
@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(value = {SessionUnAuthorizedException.class})
	public ModelAndView cSessionUnAuthorizedException(SessionUnAuthorizedException ex, WebRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("unauthorized");
		return mav;
	}

}