package com.sdm.StarRental.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class clerkManagePageController {

    @RequestMapping(value = "/clerkManagePage", method = RequestMethod.GET)
    public String Main(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession) {
        //if(Utilities.validateSession(httpSession)) {


        model.addAttribute("loggedinusername", httpSession.getAttribute("userNameLoggedIn"));
        return "clerkManagePage";
    }

    //	return "unauthorized";
    //}
}
