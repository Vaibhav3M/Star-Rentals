package com.sdm.StarRental.controller;

import com.sdm.StarRental.dataMapper.ClientDM;
import com.sdm.StarRental.dataMapper.TransactionDM;
import com.sdm.StarRental.dataMapper.VehicleDM;
import com.sdm.StarRental.model.Client;
import com.sdm.StarRental.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

public class makeReservationController {

    private static Logger logger =
            LoggerFactory.getLogger(makeRentalController.class);

    @Autowired
    private VehicleDM catalogService;
    @Autowired
    private TransactionDM transactionService;
    @Autowired
    private ClientDM clientService;

    ArrayList<Client> gClients;
    ArrayList<Vehicle> gVehicles;
    String selectedClient = "";
    String selectedCar = "";
    ArrayList<Vehicle> searchedVehicles;
    ArrayList<Client> searchedClients;


    @RequestMapping(value = "/reserve", method = RequestMethod.GET)
    public String makeReservationPageSetup (@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession) throws Exception
    {
        //  if (true) {

        gVehicles = catalogService.getVehicleFromOneCriteria("Available", null, "status");
        gClients =clientService.getAllClientsService();

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

        //	} else {
        //	  return "unauthorized";
        //}
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

        if (!reqParam.get("First_Name").equals("") && reqParam.get("License_Number").equals("")) {
            clients = clientService.getClientDetailsOneParamService("First_Name", reqParam.get("First_Name"));
        } else if (reqParam.get("First_Name").equals("") && !reqParam.get("License_Number").equals("")) {
            clients = clientService.getClientDetailsOneParamService("License_Number", reqParam.get("License_Number"));
        } else if (!reqParam.get("First_Name").equals("") && !reqParam.get("License_Number").equals("")) {
            clients = clientService.getClientDetailsTwoParamService("First_Name", reqParam.get("First_Name"), "License_Number",
                    reqParam.get("License_Number"));
        } else if (reqParam.get("First_Name").equals("") && reqParam.get("License_Number").equals("")) {
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
        if (!reqPar.get("LICENSE_PLATE").equals("")) {
            for (Vehicle c : gVehicles) {
                if (c.getvehicleLicensePlate().equalsIgnoreCase(reqPar.get("LICENSE_PLATE"))) {
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




    @RequestMapping(value = "/makereservation", method = RequestMethod.POST)
    public String makeReservation( @RequestParam Map<String, String> reqParam,ModelMap modelMap, HttpSession httpSession ) throws Exception {
        String firstName = reqParam.get("firstName").toString();
        String lastName = reqParam.get("lastName").toString();
        String licenseNumber = reqParam.get("licenseNumber").toString();
        String licensePlate = reqParam.get("licensePlate").toString();
        String from = reqParam.get("fromDate");
        String till =reqParam.get("tillDate");


        if(licenseNumber.isEmpty() || licensePlate.isEmpty() || from.isEmpty() || till.isEmpty()) return "createnewreservation";


        Vehicle vehicle = catalogService.getVehicleByLicenseNo(licensePlate);
        catalogService.modifyVehicle(vehicle.getType(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), vehicle.getColor(),
                vehicle.getvehicleLicensePlate(), "Reserved");
        transactionService.createTransactionService(licensePlate, "Reservation",licenseNumber, "Reserved",
                "",from, till, "" );
//httpSession.getAttribute("userNameLoggedIn").toString()
        return "clerkMainPage";
    }
}
