package com.sdm.StarRental.controller;

import com.sdm.StarRental.dataMapper.TransactionDM;
import com.sdm.StarRental.dataMapper.VehicleDM;
import com.sdm.StarRental.model.Transaction;
import com.sdm.StarRental.model.Vehicle;
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
public class returnRentalController {

    private static Logger logger = LoggerFactory.getLogger(returnRentalController.class);

    private VehicleDM vehicleDM;
    private TransactionDM transactionDM;

    private VehicleUnitOfWork vehicleUnitOfWork;
    private TransactionUnitOfWork transactionUnitOfWork;

    public returnRentalController(){
        vehicleDM = new VehicleDM();
        transactionDM = new TransactionDM();
        vehicleUnitOfWork = VehicleUnitOfWork.getInstance();
        transactionUnitOfWork = TransactionUnitOfWork.getInstance();


    }

    ArrayList<Transaction> relatedTransactions = new ArrayList<>();


    @RequestMapping(value = "/returnRental", method = RequestMethod.GET)
    public String returnVehicleEntry(@RequestParam Map<String, String> reqPar, ModelMap modelMap, HttpSession httpSession) throws Exception {

//        ArrayList<Catalog> rentedVehicles = catalogService.getVehicleFromOneCriteria("Rented", null, "status");



        ArrayList<Vehicle> rentedVehicles = new ArrayList<>();
        for(Vehicle vehicle : vehicleDM.getAllVehicles()) {
            if(vehicle.getStatus().equalsIgnoreCase("Rented")) {
                rentedVehicles.add(vehicle);
            }
        }

        relatedTransactions.clear();
        for (Vehicle vehicle : rentedVehicles) {
            ArrayList<Transaction> transactions = transactionDM.getTransactionForVehicleService(vehicle.getvehicleLicensePlate());
            if (!transactions.isEmpty()) {
                Transaction currTransaction = transactions.get(transactions.size() - 1);
                if (currTransaction != null && currTransaction.getStatus().equalsIgnoreCase("Rented")) {
                    relatedTransactions.add(currTransaction);
                }
            }

        }

        if (relatedTransactions != null) {
            modelMap.addAttribute("vehiclecatalogfound", "RESULT_FOUND");
            modelMap.addAttribute("transactionsResults", relatedTransactions);
        } else {
            modelMap.addAttribute("transactionsResults", "No vehicle is rented at the moment!!");
        }
        return "returnRental";
    }


    @RequestMapping(value = "/searchreturnclient", method = RequestMethod.POST)
    public String searchReturnVehicle(@RequestParam Map<String, String> reqParam, ModelMap modelMap, HttpSession httpSession) throws Exception {

        ArrayList<Transaction> searchedTransactions = new ArrayList<>();

        if (!reqParam.get("client_license").equals("") && reqParam.get("vehicle_licensePlate").equals("")) {

            for (Transaction transaction : relatedTransactions) {
                if (transaction.getClientLicenseNumber().equalsIgnoreCase(reqParam.get("client_license"))) {
                    searchedTransactions.add(transaction);
                }
            }

        } else if (reqParam.get("client_license").equals("") && !reqParam.get("vehicle_licensePlate").equals("")) {

            for (Transaction transaction : relatedTransactions) {
                if (transaction.getVehicleLicensePlate().equalsIgnoreCase(reqParam.get("vehicle_licensePlate"))) {
                    searchedTransactions.add(transaction);
                }

            }

        } else if (!reqParam.get("client_license").equals("") && !reqParam.get("vehicle_licensePlate").equals("")) {

            for (Transaction transaction : relatedTransactions) {
                if (transaction.getClientLicenseNumber().equalsIgnoreCase(reqParam.get("client_license")) && transaction.getVehicleLicensePlate().equalsIgnoreCase(reqParam.get("vehicle_licensePlate"))) {
                    searchedTransactions.add(transaction);
                }
            }
        }else if (reqParam.get("client_license").equals("") && reqParam.get("vehicle_licensePlate").equals("")) {

            searchedTransactions.addAll(relatedTransactions);
        }

        if (!searchedTransactions.isEmpty()) {
            modelMap.addAttribute("vehiclecatalogfound", "RESULT_FOUND");
            modelMap.addAttribute("transactionsResults", searchedTransactions);
        } else {
            modelMap.addAttribute("vehiclecatalogfound", "RESULT_NOT_FOUND");
            modelMap.addAttribute("transactionsResults", "Cannot find item!!");
        }

        return "returnRental";
    }



    @RequestMapping(value = "/returnClientForm",method = RequestMethod.POST)
    public String returnVehicleForm(@RequestParam Map<String,String> reqParam, ModelMap model,HttpSession httpSession) throws Exception{

        int transactionID = Integer.parseInt(reqParam.get("transactionID"));

        for (int i=0;i<relatedTransactions.size();i++){
            Transaction currTransaction = relatedTransactions.get(i);
            if(currTransaction.getTransactionID() == transactionID){

               // transactionDM.createTransactionService(currTransaction.getVehicleLicensePlate(),"Rental" ,currTransaction.getClientLicenseNumber(),"Return","","NA","NA",currTransaction.getTransactionBy());
                currTransaction.setStatus("Returned");
                transactionUnitOfWork.create(currTransaction);
                Vehicle vehicle = vehicleDM.getVehicleByLicenseNo(currTransaction.getVehicleLicensePlate());
                vehicle.setStatus("Available");
               // vehicleDM.modifyVehicle(vehicle.getType(),vehicle.getMake(),vehicle.getModel(),vehicle.getYear(),vehicle.getColor(),vehicle.getvehicleLicensePlate(),"Available");
                vehicleUnitOfWork.update(vehicle);
                relatedTransactions.remove(i);
            }
        }



        if (!relatedTransactions.isEmpty()) {
            model.addAttribute("vehiclecatalogfound", "RESULT_FOUND");
            model.addAttribute("transactionsResults", relatedTransactions);
        } else {
            model.addAttribute("vehiclecatalogfound", "RESULT_NOT_FOUND");
            model.addAttribute("transactionsResults", "Cannot find item!!");
        }


        return "returnRental";
    }

}
