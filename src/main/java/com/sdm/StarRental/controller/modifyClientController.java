package com.sdm.StarRental.controller;

import com.sdm.StarRental.dataMapper.ClientDM;
import com.sdm.StarRental.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class modifyClientController {

    private static Logger logger = LoggerFactory.getLogger(modifyClientController.class);

    @Autowired
    private ClientDM clientDM;


    @RequestMapping(value = "/modifyClient", method = RequestMethod.GET)
    public String getCustomerList(@RequestParam Map<String, String> reqParam, ModelMap model, HttpSession httpSession)
            throws Exception {

        // TODO: check valid session

        //if (Utilities.validateSession(httpSession)) {

        ArrayList<Client> users = clientDM.getAllClientsService();

        if (users != null) {
            model.addAttribute("client_found", "RESULT_FOUND");
            model.addAttribute("client_results", users);
        } else {
            model.addAttribute("client_found", "RESULT_NOT_FOUND");
            model.addAttribute("client_results", "Cannot find item!!");
        }
        return "modifyClient";
        //} else {
        //	return "unauthorized";
        //}

    }


    @RequestMapping(value = "/searchClientForm", method = RequestMethod.POST)
    public String getClientInfo(@RequestParam Map<String, String> reqParam, ModelMap modelMap, HttpSession httpSession)
            throws Exception {

        ArrayList<Client> users = new ArrayList<>();

        // One search criteria
        if (!reqParam.get("First_Name").equals("") && reqParam.get("Last_Name").equals("")
                && reqParam.get("License_Number").equals("")) {
            users = clientDM.getClientDetailsOneParamService("First_Name", reqParam.get("First_Name"));
        } else if (reqParam.get("First_Name").equals("") && !reqParam.get("Last_Name").equals("")
                && reqParam.get("License_Number").equals("")) {
            logger.info("");
            users = clientDM.getClientDetailsOneParamService("Last_Name", reqParam.get("Last_Name"));
        } else if (reqParam.get("First_Name").equals("") && reqParam.get("Last_Name").equals("")
                && !reqParam.get("License_Number").equals("")) {
            users = clientDM.getClientDetailsOneParamService("License_Number", reqParam.get("License_Number"));
        }

        // Two search criteria
        else if (!reqParam.get("First_Name").equals("") && !reqParam.get("Last_Name").equals("")
                && reqParam.get("License_Number").equals("")) {
            users = clientDM.getClientDetailsTwoParamService("First_Name", reqParam.get("First_Name"), "Last_Name",
                    reqParam.get("Last_Name"));
        } else if (!reqParam.get("First_Name").equals("") && reqParam.get("Last_Name").equals("")
                && !reqParam.get("License_Number").equals("")) {
            logger.info("");
            users = clientDM.getClientDetailsTwoParamService("First_Name", reqParam.get("First_Name"), "License_Number",
                    reqParam.get("License_Number"));
        } else if (reqParam.get("First_Name").equals("") && !reqParam.get("Last_Name").equals("")
                && !reqParam.get("License_Number").equals("")) {
            users = clientDM.getClientDetailsTwoParamService("Last_Name", reqParam.get("Last_Name"), "License_Number",
                    reqParam.get("License_Number"));
        }
        // Three search criteria
        else if (!reqParam.get("First_Name").equals("") && !reqParam.get("Last_Name").equals("")
                && !reqParam.get("License_Number").equals("")) {
            users = clientDM.getClientDetailsThreeParamService(reqParam.get("First_Name"), reqParam.get("Last_Name"),
                    reqParam.get("License_Number"));
        }

        if (!users.isEmpty()) {
            modelMap.addAttribute("client_found", "RESULT_FOUND");
            modelMap.addAttribute("client_results", users);
            logger.info("found");
        } else {
            modelMap.addAttribute("client_found", "RESULT_NOT_FOUND");
            modelMap.addAttribute("client_results", "Cannot find item!!");
        }

        return "modifyClient";
    }


    @RequestMapping(value = "/modifyClientInfo", method = RequestMethod.POST)
    public String updateClientInfo(@RequestParam Map<String, String> reqParam, ModelMap model, HttpSession httpSession)
            throws Exception {

        clientDM.modifyClientService(reqParam.get("firstName"), reqParam.get("lastName"),
                reqParam.get("phoneNumber"), reqParam.get("licenseNumber"),reqParam.get("licenseExpDate"));

        ArrayList<Client> clients = clientDM.getAllClientsService();

        if (!clients.isEmpty()) {
            model.addAttribute("client_found", "RESULT_FOUND");
            model.addAttribute("client_results", clients);
            logger.info("found");
        } else {
            model.addAttribute("client_found", "RESULT_NOT_FOUND");
            model.addAttribute("client_results", "Cannot find item!!");
        }

        return "modifyClient";
    }

}
