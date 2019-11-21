package com.sdm.StarRental.controller;

import com.sdm.StarRental.dataMapper.ClientDM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class createClientController {

    private static Logger logger = LoggerFactory.getLogger(createClientController.class);

    @Autowired
    ClientDM clientDM;

    @RequestMapping(value = "/createClient",method = RequestMethod.GET)
    public String createClient(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession) {
//        if(Utilities.validateSession(httpSession)) {
//            return "createnewclientform";
//        }else {
//            return "unauthorized";
//        }
        return "createClient";
    }


    @RequestMapping(value = "/createClientForm",method = RequestMethod.POST)
    public String createClientForm(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession) throws Exception {
       // if(Utilities.validateSession(httpSession)) {

            String licenseNumber = reqPar.get("licenseNumber");
            String firstName = reqPar.get("firstName");
            String lastName = reqPar.get("lastName");
            String phoneNumber = reqPar.get("phoneNumber");
            String licenseExpiryDate = reqPar.get("License_Expiry_Date");

            boolean result= clientDM.createClientService(firstName, lastName, phoneNumber, licenseNumber, licenseExpiryDate);
            if(result) {
                return "createClient";
            }else {
                return "loginpage";
            }
//        }else {
//            return "unauthorized";
//        }
    }
}
