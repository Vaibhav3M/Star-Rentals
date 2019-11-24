package com.sdm.StarRental.controller;

import com.sdm.StarRental.dataMapper.TransactionDM;
import com.sdm.StarRental.model.Transaction;
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
public class viewTransactionController {


    private static Logger logger = LoggerFactory.getLogger(viewTransactionController.class);

    private TransactionDM transactionDM;

    public viewTransactionController(){
        transactionDM = new TransactionDM();
    }


    @RequestMapping(value = "/viewTransactions", method = RequestMethod.GET)
    public String loadTransactions(@RequestParam Map<Integer, Transaction> reqPar, ModelMap model, HttpSession httpSession) throws Exception {

        //if(Utilities.validateSession(httpSession)) {
        ArrayList<Transaction> transactions = transactionDM.getAllTransactionsService();
        if (!transactions.isEmpty()) {
            logger.info("Transaction length" + transactions.size());

            if (transactions != null) {
                model.addAttribute("transaction_found", "RESULT_FOUND");
                model.addAttribute("transaction_results", transactions);
            }

        } else {
            model.addAttribute("transaction_found", "RESULT_NOT_FOUND");
        }
        return "viewTransactions";
        //	}

        //	else {
        //		return "unauthorized";
        //	}
    }


    @RequestMapping(value = "/searchTransactions", method = RequestMethod.POST)
    public String filterTransactions(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession) throws Exception {

        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        //one criteria
        //by status
        if (!reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {
            transactions = transactionDM.getTransactionForStatusService(reqPar.get("status"));
        }

        //by license plate
        else if (reqPar.get("status").equals("") && !reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {
            transactions = transactionDM.getTransactionForVehicleService(reqPar.get("vehicleLicensePlate"));
        }

        //by license number
        else if (reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && !reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {
            transactions = transactionDM.getTransactionForClientService(reqPar.get("clientLicenseNumber"));
        }

        //by  transaction date
        else if (reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {
            transactions = transactionDM.getTransactionForTransactionDate(reqPar.get("timeStamp"));
        }

        //by rented from date
        else if (reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && !reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {
            transactions = transactionDM.getTransactionForRentedFromService(reqPar.get("bookingFrom"));
        }

        //by rented till date
        else if (reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && !reqPar.get("bookingTill").equals("")) {
            transactions = transactionDM.getTransactionForRentedTillService(reqPar.get("bookingTill"));
        }


        //Two Criteria
        //status and transaction date
        else if (!reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForTwoCriteriaService(reqPar.get("status"), reqPar.get("timeStamp"), "status", "timeStamp");

        }
        //status and license number
        else if (!reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && !reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForTwoCriteriaService(reqPar.get("status"), reqPar.get("clientLicenseNumber"), "status", "clientLicenseNumber");

        }

        //vehicleLicensePlate and license number
        else if (reqPar.get("status").equals("") && !reqPar.get("vehicleLicensePlate").equals("") && !reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForTwoCriteriaService(reqPar.get("vehicleLicensePlate"), reqPar.get("clientLicenseNumber"), "vehicleLicensePlate", "clientLicenseNumber");

        }
        //status and bookingFrom
        else if (!reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && !reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForTwoCriteriaService(reqPar.get("status"), reqPar.get("bookingFrom"), "status", "bookingFrom");

        }

        //status and vehicleLicensePlate
        else if (!reqPar.get("status").equals("") && !reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForTwoCriteriaService(reqPar.get("status"), reqPar.get("vehicleLicensePlate"), "status", "vehicleLicensePlate");

        }
        //status and bookingTill
        else if (!reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && !reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForTwoCriteriaService(reqPar.get("status"), reqPar.get("bookingTill"), "status", "bookingTill");

        }


        //timeStamp and license number
        else if (reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && !reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForTwoCriteriaService(reqPar.get("timeStamp"), reqPar.get("clientLicenseNumber"), "timeStamp", "clientLicenseNumber");

        }

        //timeStamp and vehicleLicensePlate
        else if (reqPar.get("status").equals("") && !reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForTwoCriteriaService(reqPar.get("timeStamp"), reqPar.get("vehicleLicensePlate"), "timeStamp", "vehicleLicensePlate");

        }


        //bookingFrom and license number
        else if (reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && !reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && !reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForTwoCriteriaService(reqPar.get("bookingFrom"), reqPar.get("clientLicenseNumber"), "bookingFrom", "clientLicenseNumber");

        }

        //bookingFrom and license plate
        else if (reqPar.get("status").equals("") && !reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && !reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForTwoCriteriaService(reqPar.get("bookingFrom"), reqPar.get("vehicleLicensePlate"), "bookingFrom", "vehicleLicensePlate");

        }

        //rented Till and license number
        else if (reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && !reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && !reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForTwoCriteriaService(reqPar.get("bookingTill"), reqPar.get("clientLicenseNumber"), "bookingTill", "clientLicenseNumber");

        }

        //rented Till and vehicleLicensePlate
        else if (reqPar.get("status").equals("") && !reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && !reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForTwoCriteriaService(reqPar.get("bookingTill"), reqPar.get("vehicleLicensePlate"), "bookingTill", "vehicleLicensePlate");

        }


        //rented from date and rented till date
        else if (reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && !reqPar.get("bookingFrom").equals("") && !reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForTwoCriteriaService(reqPar.get("bookingFrom"), reqPar.get("bookingTill"), "bookingFrom", "bookingTill");

        }

        //rented from date and timeStamp
        else if (reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && !reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForTwoCriteriaService(reqPar.get("bookingFrom"), reqPar.get("timeStamp"), "bookingFrom", "timeStamp");

        }

        //rented till date and timeStamp
        else if (reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && !reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForTwoCriteriaService(reqPar.get("bookingTill"), reqPar.get("timeStamp"), "bookingTill", "timeStamp");

        }


//Three parameter search
        //rented from date, rented till date and timeStamp
        else if (reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && !reqPar.get("bookingFrom").equals("") && !reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForThreeCriteriaService(reqPar.get("bookingFrom"), reqPar.get("bookingTill"), reqPar.get("timeStamp"), "bookingFrom", "bookingTill", "timeStamp");

        }
        //rented from date, rented till date and vehicleLicensePlate
        else if (reqPar.get("status").equals("") && !reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && !reqPar.get("bookingFrom").equals("") && !reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForThreeCriteriaService(reqPar.get("bookingFrom"), reqPar.get("bookingTill"), reqPar.get("vehicleLicensePlate"), "bookingFrom", "bookingTill", "vehicleLicensePlate");

        }

        //rented from date, rented till date and clientLicenseNumber
        else if (reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && !reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && !reqPar.get("bookingFrom").equals("") && !reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForThreeCriteriaService(reqPar.get("bookingFrom"), reqPar.get("bookingTill"), reqPar.get("clientLicenseNumber"), "bookingFrom", "bookingTill", "clientLicenseNumber");

        }


        //rented from date, rented till date and status
        else if (!reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && !reqPar.get("bookingFrom").equals("") && !reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForThreeCriteriaService(reqPar.get("bookingFrom"), reqPar.get("bookingTill"), reqPar.get("status"), "bookingFrom", "bookingTill", "status");

        }

        //status, timeStamp and vehicleLicensePlate
        else if (!reqPar.get("status").equals("") && !reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForThreeCriteriaService(reqPar.get("status"), reqPar.get("timeStamp"), reqPar.get("vehicleLicensePlate"), "status", "timeStamp", "vehicleLicensePlate");

        }


        //status, timeStamp and clientLicenseNumber
        else if (!reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && !reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForThreeCriteriaService(reqPar.get("status"), reqPar.get("timeStamp"), reqPar.get("clientLicenseNumber"), "status", "timeStamp", "clientLicenseNumber");

        }


        //clientLicenseNumber, timeStamp date and vehicleLicensePlate
        else if (reqPar.get("status").equals("") && !reqPar.get("vehicleLicensePlate").equals("") && !reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForThreeCriteriaService(reqPar.get("clientLicenseNumber"), reqPar.get("timeStamp"), reqPar.get("vehicleLicensePlate"), "clientLicenseNumber", "timeStamp", "vehicleLicensePlate");

        }


        //four criteria

        //status, timeStamp , clientLicenseNumber and vehicleLicensePlate
        else if (!reqPar.get("status").equals("") && !reqPar.get("vehicleLicensePlate").equals("") && !reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForFourCriteriaService(reqPar.get("status"), reqPar.get("timeStamp"), reqPar.get("clientLicenseNumber"), reqPar.get("vehicleLicensePlate"), "status", "timeStamp", "clientLicenseNumber", "vehicleLicensePlate");

        }

        //status, timeStamp , clientLicenseNumber and bookingFrom
        else if (!reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && !reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && !reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForFourCriteriaService(reqPar.get("status"), reqPar.get("timeStamp"), reqPar.get("clientLicenseNumber"), reqPar.get("bookingFrom"), "status", "timeStamp", "clientLicenseNumber", "bookingFrom");

        }

        //status, timeStamp , vehicleLicensePlate and bookingFrom
        else if (!reqPar.get("status").equals("") && !reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && !reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForFourCriteriaService(reqPar.get("status"), reqPar.get("timeStamp"), reqPar.get("vehicleLicensePlate"), reqPar.get("bookingFrom"), "status", "timeStamp", "vehicleLicensePlate", "bookingFrom");

        }


        //status, timeStamp , clientLicenseNumber and bookingTill
        else if (!reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && !reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && !reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForFourCriteriaService(reqPar.get("status"), reqPar.get("timeStamp"), reqPar.get("clientLicenseNumber"), reqPar.get("bookingTill"), "status", "timeStamp", "clientLicenseNumber", "bookingTill");

        }

        //status, timeStamp , vehicleLicensePlate and bookingTill
        else if (!reqPar.get("status").equals("") && !reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && !reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForFourCriteriaService(reqPar.get("status"), reqPar.get("timeStamp"), reqPar.get("vehicleLicensePlate"), reqPar.get("bookingTill"), "status", "timeStamp", "vehicleLicensePlate", "bookingTill");

        }
        //bookingFrom, timeStamp , clientLicenseNumber and bookingTill
        else if (reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && !reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && !reqPar.get("bookingFrom").equals("") && !reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForFourCriteriaService(reqPar.get("bookingFrom"), reqPar.get("timeStamp"), reqPar.get("clientLicenseNumber"), reqPar.get("bookingTill"), "bookingFrom", "timeStamp", "clientLicenseNumber", "bookingTill");

        }

        //bookingFrom, timeStamp , vehicleLicensePlate and bookingTill
        else if (reqPar.get("status").equals("") && !reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && !reqPar.get("bookingFrom").equals("") && !reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForFourCriteriaService(reqPar.get("bookingFrom"), reqPar.get("timeStamp"), reqPar.get("vehicleLicensePlate"), reqPar.get("bookingTill"), "bookingFrom", "timeStamp", "clientLicenseNumber", "bookingTill");

        }


        //all parameter search
        else if (!reqPar.get("status").equals("") && !reqPar.get("vehicleLicensePlate").equals("") && !reqPar.get("clientLicenseNumber").equals("")
                && !reqPar.get("timeStamp").equals("") && !reqPar.get("bookingFrom").equals("") && !reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getTransactionForAllCriteriaService(reqPar.get("status"), reqPar.get("vehicleLicensePlate"),
                    reqPar.get("clientLicenseNumber"), reqPar.get("timeStamp"), reqPar.get("bookingFrom"), reqPar.get("bookingTill"), "All");
        }


        //retrun all

        else if (reqPar.get("status").equals("") && reqPar.get("vehicleLicensePlate").equals("") && reqPar.get("clientLicenseNumber").equals("")
                && reqPar.get("timeStamp").equals("") && reqPar.get("bookingFrom").equals("") && reqPar.get("bookingTill").equals("")) {

            transactions = transactionDM.getAllTransactionsService();
        }


//        //sort
//        //by status
//        if (reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("status")) {
//            transactions = transactionDM.sortByStatusService(transactions);
//        }
//
//        //by vehicleLicensePlate
//        else if (reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("vehicleLicensePlate")) {
//            transactions = transactionDM.(transactions);
//        }
//
//        //by Client
//        else if (reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("clientclientLicenseNumber")) {
//            transactions = transactionDM.sortByClient(transactions);
//        }
//
//        //by tansactionDate
//        else if (reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("timeStamp")) {
//            transactions = transactionDM.sortBytimeStamp(transactions);
//        }
//
//        //by bookingFrom
//        else if (reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("bookingFrom")) {
//            transactions = transactionDM.sortBybookingFrom(transactions);
//        }
//
//        //by bookingTill
//        else if (reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("bookingTill")) {
//            transactions = transactionDM.sortBybookingTill(transactions);
//        }

        logger.info("Transaction length" + transactions.size());
        model.addAttribute("transaction_found", reqPar);


        if (transactions != null) {
            model.addAttribute("transaction_found", "RESULT_FOUND");
            model.addAttribute("transaction_results", transactions);
        } else {
            model.addAttribute("transaction_found", "RESULT_NOT_FOUND");
            model.addAttribute("transaction_results", "transactions do not exist");
        }


        return "viewTransactions";
    }

    @RequestMapping(value = "/backtoadminmainpage", method = RequestMethod.POST)
    public String backToMainPage(){

        return "adminPanel";
    }
}