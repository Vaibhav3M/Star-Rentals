package com.sdm.StarRental.controller;

import com.sdm.StarRental.dataMapper.ClientDM;
import com.sdm.StarRental.dataMapper.TransactionDM;
import com.sdm.StarRental.dataMapper.VehicleDM;
import com.sdm.StarRental.model.Client;
import com.sdm.StarRental.model.Transaction;
import com.sdm.StarRental.model.Vehicle;
import com.sdm.StarRental.objectUtilities.Utilities;
import com.sdm.StarRental.unitOfWork.ClientUnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class ManageClientController {

    private static Logger logger = LoggerFactory.getLogger(ManageClientController.class);

    ClientDM clientDM;
    ClientUnitOfWork clientUnitOfWork;
    VehicleDM vehicleDM;
    TransactionDM transactionDM;
    public ManageClientController(){
        clientDM = new ClientDM();
        vehicleDM = new VehicleDM();
        clientUnitOfWork  = ClientUnitOfWork.getInstance(clientDM);
        transactionDM = new TransactionDM();

        
    }

    @RequestMapping(value = "/createClient",method = RequestMethod.GET)
    public String createClient(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession) {
        if (Utilities.validateSession(httpSession)) {
            model.addAttribute("loggedinusername", httpSession.getAttribute("userNameLoggedIn"));
            return "createClient";
        }else {
            return "unauthorized";
        }
    }


    @RequestMapping(value = "/createClientForm",method = RequestMethod.POST)
    public String createClientForm(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession) throws Exception {
        if (Utilities.validateSession(httpSession)) {
            model.addAttribute("loggedinusername", httpSession.getAttribute("userNameLoggedIn"));
            String licenseNumber = reqPar.get("licenseNumber");
            String firstName = reqPar.get("firstName");
            String lastName = reqPar.get("lastName");
            String phoneNumber = reqPar.get("phoneNumber");
            String licenseExpiryDate = reqPar.get("License_Expiry_Date");

            Client client = new Client();
            client.setLicenseNumber(licenseNumber);
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setPhoneNumber(phoneNumber);
            client.setLicenseExpiryDate(licenseExpiryDate);


            if(client != null){
                clientUnitOfWork.create(client);
            }

                return "createClient";

        }else {
            return "unauthorized";
        }
    }

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
        ArrayList<Transaction> relatedTransactions = new ArrayList<Transaction>();
        ArrayList<String> relatedClient =  new ArrayList<String>();
        ArrayList<Vehicle> rentedVehicles = new ArrayList<>(vehicleDM.getVehicleFromOneCriteria("Rented", null, "status"));

    	System.out.println("vehicles rented  "+rentedVehicles );

        ArrayList<Client> clients = clientDM.getAllClientsService();
        for (Vehicle vehicle : rentedVehicles) {
        	  ArrayList<Transaction> transactions = transactionDM.getTransactionForVehicleService(vehicle.getvehicleLicensePlate());
            if (!transactions.isEmpty()) {
                Transaction currTransaction = transactions.get(transactions.size() - 1);
                if (currTransaction != null && currTransaction.getStatus().contains("Rented")) {               
                	relatedTransactions.add(currTransaction);
                		relatedClient.add(currTransaction.getClientLicenseNumber().trim());

                }
            }

        }
        
    	

        if(relatedClient.contains(clientLicenseNumber)) {
        System.out.println("Client has a vehicle rented ");

        }
        else if(!relatedClient.contains(clientLicenseNumber)) {
        	clientUnitOfWork.delete(clientLicenseNumber);	
        	System.out.println("deleted" );


        }
        else {
        	System.out.println("Cannot delete" );

        }



        
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




    @RequestMapping(value = "/modifyClient", method = RequestMethod.GET)
    public String getCustomerList1(@RequestParam Map<String, String> reqParam, ModelMap model, HttpSession httpSession)
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
    public String getClientInfo1(@RequestParam Map<String, String> reqParam, ModelMap modelMap, HttpSession httpSession)
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

        Client client = new Client();
        client.setLicenseNumber(reqParam.get("licenseNumber"));
        client.setLicenseExpiryDate(reqParam.get("licenseExpDate"));
        client.setFirstName(reqParam.get("firstName"));
        client.setLastName(reqParam.get("lastName"));
        client.setPhoneNumber(reqParam.get("phoneNumber"));

        clientUnitOfWork.update(client);

        ArrayList<Client> clients = clientDM.getAllClientsService();

        if (!clients.isEmpty()) {
            model.addAttribute("client_found", "RESULT_FOUND");
            model.addAttribute("client_results", clients);
        } else {
            model.addAttribute("client_found", "RESULT_NOT_FOUND");
            model.addAttribute("client_results", "Cannot find item!!");
        }

        return "modifyClient";
    }

    @RequestMapping(value = "/backtomanagepage", method = RequestMethod.GET)
    public String backToMainPage(){

        return "clerkManagePage";
    }

}
