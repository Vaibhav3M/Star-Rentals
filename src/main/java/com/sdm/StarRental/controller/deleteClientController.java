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
public class deleteClientController {
    private static Logger logger = LoggerFactory.getLogger(deleteClientController.class);

    @Autowired
    private ClientDM clientDM;


    @RequestMapping(value = "/deleteClient",method = RequestMethod.GET)
    public String getCustomerList(@RequestParam Map<String,String> reqParam, ModelMap model, HttpSession httpSession)
            throws Exception {

        //TODO: check valid session

        // if(Utilities.validateSession(httpSession)) {


        ArrayList<Client> users = clientDM.getAllClientsService();

        if (users != null) {
            model.addAttribute("client_found", "RESULT_FOUND");
            model.addAttribute("client_results", users);
        }
        else {
            model.addAttribute("client_found", "RESULT_NOT_FOUND");
            model.addAttribute("client_results", "Cannot find Client!!");
        }
        return "deleteClient";
        //   }else {
        //   	return "unauthorized";
        //   }


    }



    @RequestMapping(value = "/searchDelClientForm",method = RequestMethod.POST)
    public String getClientInfo(@RequestParam Map<String,String> reqParam, ModelMap modelMap, HttpSession httpSession) throws Exception {


        ArrayList<Client> clients = new ArrayList<>();


        if (!reqParam.get("First_Name").equals("") && reqParam.get("Last_Name").equals("") && reqParam.get("License_Number").equals("")) {
            clients = clientDM.getClientDetailsOneParamService("First_Name",reqParam.get("First_Name"));
        } else if (reqParam.get("First_Name").equals("") && !reqParam.get("Last_Name").equals("") && reqParam.get("License_Number").equals("")) {
            logger.info("");
            clients = clientDM.getClientDetailsOneParamService("Last_Name",reqParam.get("Last_Name"));
        } else if (reqParam.get("First_Name").equals("") && reqParam.get("Last_Name").equals("") && !reqParam.get("License_Number").equals("")) {
            clients = clientDM.getClientDetailsOneParamService("License_Number                                          ",reqParam.get("License_Number"));
        }

        if (!clients.isEmpty()) {
            modelMap.addAttribute("client_found", "RESULT_FOUND");
            modelMap.addAttribute("client_results", clients);
        } else {
            modelMap.addAttribute("client_found", "RESULT_NOT_FOUND");
            modelMap.addAttribute("client_results", "Cannot find item!!");
        }


        return "deleteClient";
    }


    @RequestMapping(value = "/deleteClientInfo",method = RequestMethod.POST)
    public String deleteClientInfo(@RequestParam Map<String,String> reqParam, ModelMap model,HttpSession httpSession) throws Exception{


        String clientLicenseNumber = reqParam.get("licenseNumber");

        clientDM.deleteClientService(clientLicenseNumber);

        ArrayList<Client> clients = clientDM.getAllClientsService();

        if(!clients.isEmpty()){
            model.addAttribute("client_found","RESULT_FOUND");
            model.addAttribute("client_results",clients);
            logger.info("found");
        }
        else {
            model.addAttribute("client_found", "RESULT_NOT_FOUND");
            model.addAttribute("client_results", "Cannot find item!!");
        }

        return "deleteClient";
    }


}
