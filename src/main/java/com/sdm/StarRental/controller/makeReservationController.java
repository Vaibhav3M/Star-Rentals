package com.sdm.StarRental.controller;

import com.sdm.StarRental.dataMapper.ClientDM;
import com.sdm.StarRental.dataMapper.VehicleDM;
import com.sdm.StarRental.model.Client;
import com.sdm.StarRental.model.Transaction;
import com.sdm.StarRental.model.Vehicle;
import com.sdm.StarRental.objectUtilities.Utilities;
import com.sdm.StarRental.unitOfWork.TransactionUnitOfWork;
import com.sdm.StarRental.unitOfWork.VehicleUnitOfWork;
import org.joda.time.DateTime;
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
public class makeReservationController {

    private static Logger logger =
            LoggerFactory.getLogger(makeRentalController.class);

    private VehicleDM vehicleDM;
    private ClientDM clientDM;



    private VehicleUnitOfWork vehicleUnitOfWork;
    private TransactionUnitOfWork transactionUnitOfWork;

    public makeReservationController(){
        vehicleDM = new VehicleDM();
        clientDM = new ClientDM();
        transactionUnitOfWork = TransactionUnitOfWork.getInstance();
        vehicleUnitOfWork = VehicleUnitOfWork.getInstance();


    }

    ArrayList<Client> gClients;
    ArrayList<Vehicle> gVehicles;
    String selectedClient = "";
    String selectedCar = "";
    ArrayList<Vehicle> searchedVehicles;
    ArrayList<Client> searchedClients;


    @RequestMapping(value = "/createNewReservation", method = RequestMethod.GET)
    public String makeReservationPageSetup (@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession) throws Exception
    {
        if (Utilities.validateSession(httpSession)) {
            model.addAttribute("loggedinusername", httpSession.getAttribute("userNameLoggedIn"));
        ArrayList<Vehicle> availableVehicles = vehicleDM.getVehicleFromOneCriteria("Available", null, "status");
        gVehicles = new ArrayList<>();
        for(Vehicle v:availableVehicles){
            if(!vehicleUnitOfWork.isDirty(v.getvehicleLicensePlate())){
                gVehicles.add(v);
            }
        }
        gClients =  clientDM.getAllClientsService();

        if (!gVehicles.isEmpty()) {
            model.addAttribute("catalog_vehicle_found", "RESULT_FOUND");
            model.addAttribute("catalogVehicleResult", gVehicles);
        } else {
            model.addAttribute("catalog_vehicle_found", "RESULT_NOT_FOUND");
            model.addAttribute("catalogVehicleResult", "No Cars available for renting.");
        }

        if (!gClients.isEmpty()) {
            model.addAttribute("client_found", "RESULT_FOUND");
            model.addAttribute("client_results", gClients);
        } else {
            model.addAttribute("client_found", "RESULT_NOT_FOUND");
            model.addAttribute("client_results", "No clients in the system.");
        }

        return "createNewReservation";

        	} else {
        	  return "unauthorized";
        }
    }


    @RequestMapping(value = "/searchClientMakeReservation", method = RequestMethod.GET)
    public String searchClient(@RequestParam Map<String, String> reqParam, ModelMap modelMap, HttpSession httpSession)
            throws Exception {
        if (searchedClients == null) {
            searchedClients = new ArrayList<>();
        } else {
            searchedClients.clear();
        }

        ArrayList<Client> clients = new ArrayList<>();

        if (!reqParam.get("lastName").equals("") && reqParam.get("licenseNumber").equals("")) {
            clients = clientDM.getClientDetailsOneParamService("lastName", reqParam.get("lastName"));
        } else if (reqParam.get("lastName").equals("") && !reqParam.get("licenseNumber").equals("")) {
            clients = clientDM.getClientDetailsOneParamService("licenseNumber", reqParam.get("licenseNumber"));
        } else if (!reqParam.get("lastName").equals("") && !reqParam.get("licenseNumber").equals("")) {
            clients = clientDM.getClientDetailsTwoParamService("lastName", reqParam.get("lastName"), "licenseNumber",
                    reqParam.get("licenseNumber"));
        } else if (reqParam.get("lastName").equals("") && reqParam.get("licenseNumber").equals("")) {
            clients.addAll(gClients);
        }


        if (clients != null && !clients.isEmpty()) {

            modelMap.addAttribute("client_found", "RESULT_FOUND");
            modelMap.addAttribute("client_results", clients);
            searchedClients.addAll(clients);

        } else {
            if (searchedClients != null) {
                searchedClients.clear();
            }
            modelMap.addAttribute("client_found", "RESULT_NOT_FOUND");
            modelMap.addAttribute("client_results", "Cannot find clients to display.");
        }

        if (searchedVehicles != null && !searchedVehicles.isEmpty()) {
            modelMap.addAttribute("catalog_vehicle_found", "RESULT_FOUND");
            modelMap.addAttribute("catalogVehicleResult", searchedVehicles);
        } else if (gVehicles != null && !gVehicles.isEmpty()) {
            modelMap.addAttribute("catalog_vehicle_found", "RESULT_FOUND");
            modelMap.addAttribute("catalogVehicleResult", gVehicles);
        } else {
            modelMap.addAttribute("catalog_vehicle_found", "RESULT_NOT_FOUND");
            modelMap.addAttribute("catalogVehicleResult", "Cannot find vehicles to display.");
        }


        return "createNewReservation";
    }


    @RequestMapping(value = "/searchCarMakeReservation", method = RequestMethod.GET)
    public String searchCar(@RequestParam Map<String, String> reqPar, ModelMap modelMap, HttpSession httpSession)
            throws Exception {
        System.out.println(reqPar);
        if (searchedVehicles == null) {
            searchedVehicles = new ArrayList<>();
        }
        if (searchedClients == null) {
            searchedClients = new ArrayList<>();
        }
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        // Catalog vehicle;
        if (!reqPar.get("vehicleLicenseNumber").equals("")) {
            for (Vehicle c : gVehicles) {
                if (c.getvehicleLicensePlate().equalsIgnoreCase(reqPar.get("vehicleLicenseNumber"))) {
                    vehicles.add(c);
                }
            }
        } else {
            vehicles.addAll(gVehicles);
        }


        if (vehicles != null && !vehicles.isEmpty()) {
            if (searchedVehicles != null) {
                searchedVehicles.clear();
            }
            modelMap.addAttribute("catalog_vehicle_found", "RESULT_FOUND");
            modelMap.addAttribute("catalogVehicleResult", vehicles);
            searchedVehicles = vehicles;

        } else {
            if (searchedVehicles != null) {
                searchedVehicles.clear();
            }
            modelMap.addAttribute("catalog_vehicle_found", "RESULT_NOT_FOUND");
            modelMap.addAttribute("catalogVehicleResult", "Cannot find vehicle!!");
        }
        if (searchedClients != null && !searchedClients.isEmpty()) {
            modelMap.addAttribute("client_found", "RESULT_FOUND");
            modelMap.addAttribute("client_results", searchedClients);
        } else if (gClients != null && !gClients.isEmpty()) {
            modelMap.addAttribute("client_found", "RESULT_FOUND");
            modelMap.addAttribute("client_results", gClients);
        } else {
            modelMap.addAttribute("client_found", "RESULT_NOT_FOUND");
            modelMap.addAttribute("client_results", "Cannot find client");
        }

        return "createNewReservation";
    }



    @RequestMapping(value = "/tableSelectClientMR", method = RequestMethod.GET)
    public String tableSelectClient(@RequestParam Map<String, String> reqPar, ModelMap modelMap,
                                    HttpSession httpSession) throws Exception {
        // table button select
        if (reqPar.containsKey("selectClient")) {
            if (!reqPar.get("selectClient").equals("")) {
                selectedClient = reqPar.get("selectClient");
            }
        }

        if (!selectedClient.equals("")) {
            modelMap.addAttribute("selectedClient", selectedClient.replace("_", " "));
        }
        if (!selectedCar.equals("")) {
            modelMap.addAttribute("selectedCar", selectedCar.replace("_", " "));
        }

	/*	if (searchedClients != null && !searchedClients.isEmpty()) {
			modelMap.addAttribute("client_found", "RESULT_FOUND");
			modelMap.addAttribute("client_results", searchedClients);
		} else {*/
        modelMap.addAttribute("client_found", "RESULT_FOUND");
        modelMap.addAttribute("client_results", gClients);
        //}
        if (searchedVehicles != null && !searchedVehicles.isEmpty()) {
            modelMap.addAttribute("catalog_vehicle_found", "RESULT_FOUND");
            modelMap.addAttribute("catalogVehicleResult", searchedVehicles);
        } else {
            modelMap.addAttribute("catalog_vehicle_found", "RESULT_FOUND");
            modelMap.addAttribute("catalogVehicleResult", gVehicles);
        }

        return "createNewReservation";
    }

    @RequestMapping(value = "/tableSelectCarMR", method = RequestMethod.GET)
    public String tableSelectCar(@RequestParam Map<String, String> reqPar, ModelMap modelMap, HttpSession httpSession)
            throws Exception {

        if (reqPar.containsKey("selectCar")) {
            if (!reqPar.get("selectCar").equals("")) {
                selectedCar = reqPar.get("selectCar");
            }
        }
        if (!selectedCar.equals("")) {
            modelMap.addAttribute("selectedCar", selectedCar.replace("_", " "));
        }
        if (!selectedClient.equals("")) {
            modelMap.addAttribute("selectedClient", selectedClient.replace("_", " "));
        }

        if (searchedClients != null && !searchedClients.isEmpty()) {

            modelMap.addAttribute("client_found", "RESULT_FOUND");
            modelMap.addAttribute("client_results", searchedClients);

        } else {
            modelMap.addAttribute("client_found", "RESULT_FOUND");
            modelMap.addAttribute("client_results", gClients);
        }
		/*if (searchedVehicles != null && !searchedVehicles.isEmpty()) {
			modelMap.addAttribute("catalog_vehicle_found", "RESULT_FOUND");
			modelMap.addAttribute("catalogVehicleResult", searchedVehicles);
		} else {*/
        modelMap.addAttribute("catalog_vehicle_found", "RESULT_FOUND");
        modelMap.addAttribute("catalogVehicleResult", gVehicles);

        return "createNewReservation";
    }




    @RequestMapping(value = "/makeReservation", method = RequestMethod.POST)
    public String makeReservation( @RequestParam Map<String, String> reqParam,ModelMap modelMap, HttpSession httpSession ) throws Exception {

        String from = reqParam.get("fromDate");
        String till =reqParam.get("tillDate");


        Vehicle vehicleSelected = vehicleDM.getVehicleByLicenseNo(selectedCar.replace("_", " "));
        Client clientSelected = clientDM.getClientDetailsOneParamService("licenseNumber", selectedClient).get(0);

        if(selectedCar.isEmpty() || selectedClient.isEmpty() || from.isEmpty() || till.isEmpty()) return "createNewReservation";

        DateTime dateTime = new DateTime();

        // create transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionType("Reservation");
        transaction.setStatus("Reserved");
        transaction.setVehicleLicensePlate(vehicleSelected.getvehicleLicensePlate());
        transaction.setClientLicenseNumber(clientSelected.getLicenseNumber());
        transaction.setBookingFrom(reqParam.get("fromDate"));
        transaction.setBookingTill(reqParam.get("tillDate"));
        transaction.setTimeStamp(dateTime.toString());
        transaction.setTransactionBy(httpSession.getAttribute("userNameLoggedIn").toString());

        transactionUnitOfWork.create(transaction);
        // update vehicle status
        vehicleSelected.setStatus("Reserved");
        vehicleUnitOfWork.update(vehicleSelected);

        selectedClient="";
        selectedCar="";
        if(searchedVehicles!=null) {
            searchedVehicles.clear();}
        if(searchedClients!=null) {
            searchedClients.clear();
        }
        return "clerkMainPage";
    }

    @RequestMapping(value = "/backToManagePageNR", method = RequestMethod.POST)
    public String backToMainPage(){

        return "clerkManagePage";
    }
}
