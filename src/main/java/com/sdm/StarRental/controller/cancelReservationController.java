package com.sdm.StarRental.controller;

import com.sdm.StarRental.dataMapper.ClientDM;
import com.sdm.StarRental.dataMapper.TransactionDM;
import com.sdm.StarRental.dataMapper.VehicleDM;
import com.sdm.StarRental.model.Transaction;
import com.sdm.StarRental.model.Vehicle;
import com.sdm.StarRental.unitOfWork.TransactionUnitOfWork;
import com.sdm.StarRental.unitOfWork.VehicleUnitOfWork;
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
public class cancelReservationController {


    private static Logger logger = LoggerFactory.getLogger(cancelReservationController.class);

    private VehicleDM vehicleDM;
    private TransactionDM transactionDM;

    private VehicleUnitOfWork vehicleUnitOfWork;
    private TransactionUnitOfWork transactionUnitOfWork;

    public cancelReservationController(){
        vehicleDM = new VehicleDM();
        transactionDM = new TransactionDM();
        vehicleUnitOfWork = new VehicleUnitOfWork();

        transactionUnitOfWork =  TransactionUnitOfWork.getInstance();

    }

    ArrayList<Transaction> relatedReservations = new ArrayList<>();

    @RequestMapping(value = "/cancelReservation", method = RequestMethod.GET)
    public String cancel(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession)
            throws Exception {
        {
            //if (Utilities.validateSession(httpSession)) {
         //   model.addAttribute("loggedinusername", httpSession.getAttribute("userNameLoggedIn"));

            model.addAttribute("Vehicle", new Vehicle());

            ArrayList<Vehicle> rentedVehicles = vehicleDM.getVehicleFromOneCriteria("Reserved", null, "status");

            relatedReservations.clear();
            for (Vehicle vehicle : rentedVehicles) {
                ArrayList<Transaction> Reservations = transactionDM.getTransactionForVehicleService(vehicle.getvehicleLicensePlate());
                if (!Reservations.isEmpty()) {
                    Transaction currTransaction = Reservations.get(Reservations.size() - 1);
                    if (currTransaction != null) {
                        relatedReservations.add(currTransaction);
                    }
                }

            }

            if (relatedReservations != null) {
                model.addAttribute("ReservationsFound", "RESULT_FOUND");
                model.addAttribute("ReservationsResults", relatedReservations);
            } else {
                model.addAttribute("ReservationsResults", "No vehicle is reserved at the moment!!");
            }

            return "cancelReservation";
            //	} else
            //	return "unauthorized";
        }
    }

    @RequestMapping(value = "/cancelReservationForm", method = RequestMethod.POST)
    public String deleteReservationInfo(@RequestParam Map<String, String> reqParam, ModelMap model, HttpSession httpSession) throws Exception {

        String licensePlate = reqParam.get("vehicleLicensePlate");
        licensePlate = licensePlate.replace("_", " ");
        Vehicle vehicle = vehicleDM.getVehicleByLicenseNo(licensePlate);


        for (int i = 0; i < relatedReservations.size(); i++) {
            Transaction currTransaction = relatedReservations.get(i);
            if (currTransaction.getVehicleLicensePlate().equalsIgnoreCase(licensePlate)) {

               // transactionDM.createTransactionService(currTransaction.getVehicleLicensePlate(),currTransaction.getTransactionType(), currTransaction.getClientLicenseNumber(),"Available","now","NA","NA", currTransaction.getTransactionBy());
                currTransaction.setStatus("UnReserved");
                transactionUnitOfWork.create(currTransaction);
              //  vehicleDM.modifyVehicle(vehicle.getType(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), vehicle.getColor(), vehicle.getvehicleLicensePlate(), "Available");
                vehicle.setStatus("Available");
                vehicleUnitOfWork.update(vehicle);
                relatedReservations.remove(i);
            }
        }


        if (relatedReservations != null) {
            model.addAttribute("ReservationsFound", "RESULT_FOUND");
            model.addAttribute("ReservationsResults", relatedReservations);
        } else {
            model.addAttribute("ReservationsResults", "No vehicle is reserved at the moment!!");
        }


        return "cancelReservation";
    }

}
