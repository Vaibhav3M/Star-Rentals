package com.sdm.StarRental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class clerkMainPageController {

    @RequestMapping(value = "/clerkMainPage", method = RequestMethod.GET)
    public String Main(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession) {
        //	if (Utilities.validateSession(httpSession)) {

        return "clerkMainPage";
        //	} else {
        //		return "unauthorized";
        //	}
    }
}
