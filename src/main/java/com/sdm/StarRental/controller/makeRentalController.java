package com.sdm.StarRental.controller;

import com.sdm.StarRental.dataMapper.ClientDM;
import com.sdm.StarRental.dataMapper.TransactionDM;
import com.sdm.StarRental.dataMapper.VehicleDM;
import com.sdm.StarRental.model.Client;
import com.sdm.StarRental.model.Transaction;
import com.sdm.StarRental.model.Vehicle;
import com.sdm.StarRental.tableDataGateway.VehicleTDG;
import com.sdm.StarRental.unitOfWork.ClientUnitOfWork;
import com.sdm.StarRental.unitOfWork.TransactionUnitOfWork;
import com.sdm.StarRental.unitOfWork.VehicleUnitOfWork;
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
public class makeRentalController {

        private static Logger logger = LoggerFactory.getLogger(makeRentalController.class);

        private VehicleDM vehicleDM;
        private ClientDM clientDM;

        private VehicleUnitOfWork vehicleUnitOfWork;
        private TransactionUnitOfWork transactionUnitOfWork;

        public makeRentalController(){
            vehicleDM = new VehicleDM();
            clientDM = new ClientDM();
            vehicleUnitOfWork = VehicleUnitOfWork.getInstance();

            transactionUnitOfWork = TransactionUnitOfWork.getInstance();

        }

        ArrayList<Client> gClients;
        ArrayList<Vehicle> gVehicles;
        String selectedClient="";
        String selectedCar="";
        ArrayList<Vehicle> searchedVehicles;
        ArrayList<Client> searchedClients;


        @RequestMapping(value = "/makeRental", method = RequestMethod.GET)
        public String makeRental(@RequestParam Map<String, String> reqPar, ModelMap modelMap, HttpSession httpSession)
			throws Exception {
        //	if (Utilities.validateSession(httpSession)) {

        gVehicles = vehicleDM.getVehicleFromOneCriteria("Available", null, "status");
        gClients = clientDM.getAllClientsService();

        if (!gVehicles.isEmpty()) {
            modelMap.addAttribute("catalog_vehicle_found", "RESULT_FOUND");
            modelMap.addAttribute("catalogVehicleResult", gVehicles);
        } else {
            modelMap.addAttribute("catalog_vehicle_found", "RESULT_NOT_FOUND");
            modelMap.addAttribute("catalogVehicleResult", "No Cars available for renting.");
        }

        if (!gClients.isEmpty()) {
            modelMap.addAttribute("client_found", "RESULT_FOUND");
            modelMap.addAttribute("client_results", gClients);
        } else {
            modelMap.addAttribute("client_found", "RESULT_NOT_FOUND");
            modelMap.addAttribute("client_results", "No clients in the system.");
        }

        return "makeRental";
        //	} else {
        //	return "unauthorized";
        //}
    }


        @RequestMapping(value = "/searchClient", method = RequestMethod.GET)
        public String searchClient(@RequestParam Map<String, String> reqParam, ModelMap modelMap, HttpSession httpSession)
			throws Exception {
        if (searchedClients == null) {
            searchedClients = new ArrayList<>();
        } else {
            searchedClients.clear();
        }

        ArrayList<Client> clients = new ArrayList<>();

        if (!reqParam.get("First_Name").equals("") && reqParam.get("License_Number").equals("")) {
            clients = clientDM.getClientDetailsOneParamService("First_Name", reqParam.get("First_Name"));
        } else if (reqParam.get("First_Name").equals("") && !reqParam.get("License_Number").equals("")) {
            clients = clientDM.getClientDetailsOneParamService("License_Number", reqParam.get("License_Number"));
        } else if (!reqParam.get("First_Name").equals("") && !reqParam.get("License_Number").equals("")) {
            clients = clientDM.getClientDetailsTwoParamService("First_Name", reqParam.get("First_Name"), "License_Number",
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

        return "makeRental";
    }


        @RequestMapping(value = "/searchCar", method = RequestMethod.GET)
        public String searchCar(@RequestParam Map<String, String> reqPar, ModelMap modelMap, HttpSession httpSession)
			throws Exception {
        System.out.println(reqPar);
        if (searchedVehicles == null) {
            searchedVehicles = new ArrayList<>();
        }else {searchedVehicles.clear();
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

            modelMap.addAttribute("catalog_vehicle_found", "RESULT_FOUND");
            modelMap.addAttribute("catalogVehicleResult", vehicles);
            searchedVehicles = vehicles;

        } else {
            if (searchedVehicles != null) {
                searchedVehicles.clear();
            }
            modelMap.addAttribute("catalog_vehicle_found", "RESULT_NOT_FOUND");
            modelMap.addAttribute("catalogVehicleResult", "Cannot find vehicles to display.");
        }
        if (searchedClients != null && !searchedClients.isEmpty()) {
            modelMap.addAttribute("client_found", "RESULT_FOUND");
            modelMap.addAttribute("client_results", searchedClients);
        } else if (gClients != null && !gClients.isEmpty()) {
            modelMap.addAttribute("client_found", "RESULT_FOUND");
            modelMap.addAttribute("client_results", gClients);
        } else {
            modelMap.addAttribute("client_found", "RESULT_NOT_FOUND");
            modelMap.addAttribute("client_results", "Cannot find clients to display.");
        }

        return "makeRental";
    }


        @RequestMapping(value = "/tableSelectClient", method = RequestMethod.GET)
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
        return "makeRental";
    }


        @RequestMapping(value = "/tableSelectCar", method = RequestMethod.GET)
        public String tableSelectCar(@RequestParam Map<String, String> reqPar, ModelMap modelMap, HttpSession httpSession)
			throws Exception {
        // table button select
        System.out.println(reqPar);
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
        //	}
        return "makeRental";
    }

        @RequestMapping(value = "/createRental", method = RequestMethod.POST)
        public String makeRentalDates(@RequestParam Map<String, String> reqPar, ModelMap modelMap, HttpSession httpSession)
			throws Exception {


        Vehicle vehicleSelected = vehicleDM.getVehicleByLicenseNo(selectedCar.replace("_", " "));
        Client clientSelected = clientDM.getClientDetailsOneParamService("licenseNumber", selectedClient).get(0);

        // checks
        if (reqPar.get("tillDate").isEmpty() || reqPar.get("fromDate").isEmpty() || selectedCar.isEmpty() || selectedClient.isEmpty()) {
            return "makeRental";
        }

        // create transaction
            Transaction transaction = new Transaction();
            transaction.setTransactionType("Rental");
            transaction.setStatus("Rented");
            transaction.setVehicleLicensePlate(vehicleSelected.getvehicleLicensePlate());
            transaction.setClientLicenseNumber(clientSelected.getLicenseNumber());
            transaction.setBookingFrom(reqPar.get("fromDate"));
            transaction.setBookingTill(reqPar.get("tillDate"));
            transaction.setTimeStamp("");
            transaction.setTransactionBy(httpSession.getAttribute("userNameLoggedIn").toString());

            transactionUnitOfWork.create(transaction);

            //	logger.info("Here2");
         // update vehicle status
            vehicleSelected.setStatus("Rented");
            vehicleUnitOfWork.update(vehicleSelected);



        selectedClient="";
        selectedCar="";
        if(searchedVehicles!=null) {
            searchedVehicles.clear();}
        if(searchedClients!=null) {
            searchedClients.clear();
        }
        return "clerkManagePage";
    }

}
