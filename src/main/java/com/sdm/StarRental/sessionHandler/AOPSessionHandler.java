package com.sdm.StarRental.sessionHandler;

import com.sdm.StarRental.exceptions.SessionUnAuthorizedException;
import com.sdm.StarRental.objectUtilities.Utilities;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;


import javax.servlet.http.HttpSession;

public class AOPSessionHandler {



    @Before("execution(* com.sdm.StarRental.controller..*(..)) && !execution(* com.sdm.StarRental.controller.LogInPageController.*(..))&& !execution(* com.sdm.StarRental.controller.clerkManagePageController.*(..))")



    public void beforeControllerValidateSession(JoinPoint joinPoint) throws Exception {

        //Advice
        if(!Utilities.validateSession((HttpSession) joinPoint.getArgs()[2])){
            throw new SessionUnAuthorizedException("User not found");
        }
    }

}
