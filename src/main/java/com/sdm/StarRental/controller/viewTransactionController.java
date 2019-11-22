package com.sdm.StarRental.controller;

import com.sdm.StarRental.dataMapper.TransactionDM;
import com.sdm.StarRental.model.Transaction;
import com.sdm.StarRental.objectUtilities.Utilities;
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
public class viewTransactionController {


    private static Logger logger = LoggerFactory.getLogger(viewTransactionController.class);

    @Autowired
    private TransactionDM transactionDM;


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
//        //by status
//        if (!reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//            transactions = transactionDM.getTransactionFromStatus(reqPar.get("status"));
//        }
//
//        //by license plate
//        else if (reqPar.get("status").equals("") && !reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//            transactions = transactionService.getTransactionFromVehicle(reqPar.get("licensePlate").replace(" ", "").trim());
//        }
//
//        //by license number
//        else if (reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && !reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//            transactions = transactionService.getTransactionFromClient(reqPar.get("licenseNumber"));
//        }
//
//        //by  transaction date
//        else if (reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//            transactions = transactionService.getTransactionFromDueDate(reqPar.get("transactionDate"));
//        }
//
//        //by rented from date
//        else if (reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && !reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//            transactions = transactionService.getTransactionFromRentedFrom(reqPar.get("validFrom"));
//        }
//
//        //by rented till date
//        else if (reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && !reqPar.get("validTill").equals("")) {
//            transactions = transactionService.getTransactionFromRentedTill(reqPar.get("validTill"));
//        }
//
//
//        //Two Criteria
//        //status and transaction date
//        else if (!reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromTwoCriteria(reqPar.get("status"), reqPar.get("transactionDate"), "status", "transactionDate");
//
//        }
//        //status and license number
//        else if (!reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && !reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromTwoCriteria(reqPar.get("status"), reqPar.get("licenseNumber"), "status", "licenseNumber");
//
//        }
//
//        //licensePlate and license number
//        else if (reqPar.get("status").equals("") && !reqPar.get("licensePlate").equals("") && !reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromTwoCriteria(reqPar.get("licensePlate"), reqPar.get("licenseNumber"), "licensePlate", "licenseNumber");
//
//        }
//        //status and rentedfrom
//        else if (!reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && !reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromTwoCriteria(reqPar.get("status"), reqPar.get("validFrom"), "status", "rentedFrom");
//
//        }
//
//        //status and licenseplate
//        else if (!reqPar.get("status").equals("") && !reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromTwoCriteria(reqPar.get("status"), reqPar.get("licensePlate"), "status", "licensePlate");
//
//        }
//        //status and rentedTill
//        else if (!reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && !reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromTwoCriteria(reqPar.get("status"), reqPar.get("validTill"), "status", "rentedTill");
//
//        }
//
//
//        //transactiondate and license number
//        else if (reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && !reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromTwoCriteria(reqPar.get("transactionDate"), reqPar.get("licenseNumber"), "transactionDate", "licenseNumber");
//
//        }
//
//        //transactiondate and licensePlate
//        else if (reqPar.get("status").equals("") && !reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromTwoCriteria(reqPar.get("transactionDate"), reqPar.get("licensePlate"), "transactionDate", "licensePlate");
//
//        }
//
//
//        //rentedFrom and license number
//        else if (reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && !reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && !reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromTwoCriteria(reqPar.get("validFrom"), reqPar.get("licenseNumber"), "rentedFrom", "licenseNumber");
//
//        }
//
//        //rentedFrom and license plate
//        else if (reqPar.get("status").equals("") && !reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && !reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromTwoCriteria(reqPar.get("validFrom"), reqPar.get("licensePlate"), "rentedFrom", "licensePlate");
//
//        }
//
//        //rented Till and license number
//        else if (reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && !reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && !reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromTwoCriteria(reqPar.get("validTill"), reqPar.get("licenseNumber"), "rentedTill", "licenseNumber");
//
//        }
//
//        //rented Till and licensePlate
//        else if (reqPar.get("status").equals("") && !reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && !reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromTwoCriteria(reqPar.get("validTill"), reqPar.get("licensePlate"), "rentedTill", "licensePlate");
//
//        }
//
//
//        //rented from date and rented till date
//        else if (reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && !reqPar.get("validFrom").equals("") && !reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromTwoCriteria(reqPar.get("validFrom"), reqPar.get("validTill"), "rentedFrom", "rentedTill");
//
//        }
//
//        //rented from date and transactiondate
//        else if (reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && !reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromTwoCriteria(reqPar.get("validFrom"), reqPar.get("transactionDate"), "rentedFrom", "transactionDate");
//
//        }
//
//        //rented till date and transactiondate
//        else if (reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && !reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromTwoCriteria(reqPar.get("validTill"), reqPar.get("transactionDate"), "rentedTill", "transactionDate");
//
//        }
//
//
////Three parameter search
//        //rented from date, rented till date and transactiondate
//        else if (reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && !reqPar.get("validFrom").equals("") && !reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromThreeCriteria(reqPar.get("validFrom"), reqPar.get("validTill"), reqPar.get("transactionDate"), "rentedFrom", "rentedTill", "transactionDate");
//
//        }
//        //rented from date, rented till date and licensePlate
//        else if (reqPar.get("status").equals("") && !reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && !reqPar.get("validFrom").equals("") && !reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromThreeCriteria(reqPar.get("validFrom"), reqPar.get("validTill"), reqPar.get("licensePlate"), "rentedFrom", "rentedTill", "licensePlate");
//
//        }
//
//        //rented from date, rented till date and licenseNumber
//        else if (reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && !reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && !reqPar.get("validFrom").equals("") && !reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromThreeCriteria(reqPar.get("validFrom"), reqPar.get("validTill"), reqPar.get("licenseNumber"), "rentedFrom", "rentedTill", "licenseNumber");
//
//        }
//
//
//        //rented from date, rented till date and status
//        else if (!reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && !reqPar.get("validFrom").equals("") && !reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromThreeCriteria(reqPar.get("validFrom"), reqPar.get("validTill"), reqPar.get("status"), "rentedFrom", "rentedTill", "status");
//
//        }
//
//        //status, transactiondate and licensePlate
//        else if (!reqPar.get("status").equals("") && !reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromThreeCriteria(reqPar.get("status"), reqPar.get("transactionDate"), reqPar.get("licensePlate"), "status", "transactionDate", "licensePlate");
//
//        }
//
//
//        //status, transactiondate and licenseNumber
//        else if (!reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && !reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromThreeCriteria(reqPar.get("status"), reqPar.get("transactionDate"), reqPar.get("licenseNumber"), "status", "transactionDate", "licenseNumber");
//
//        }
//
//
//        //licenseNumber, transactiondate date and licensePlate
//        else if (reqPar.get("status").equals("") && !reqPar.get("licensePlate").equals("") && !reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromThreeCriteria(reqPar.get("licenseNumber"), reqPar.get("transactionDate"), reqPar.get("licensePlate"), "licenseNumber", "transactionDate", "licensePlate");
//
//        }
//
//
//        //four criteria
//
//        //status, transactiondate , licenseNumber and licensePlate
//        else if (!reqPar.get("status").equals("") && !reqPar.get("licensePlate").equals("") && !reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromFourCriteria(reqPar.get("status"), reqPar.get("transactionDate"), reqPar.get("licenseNumber"), reqPar.get("licensePlate"), "status", "transactionDate", "licenseNumber", "licensePlate");
//
//        }
//
//        //status, transactiondate , licenseNumber and rentedFrom
//        else if (!reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && !reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && !reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromFourCriteria(reqPar.get("status"), reqPar.get("transactionDate"), reqPar.get("licenseNumber"), reqPar.get("validFrom"), "status", "transactionDate", "licenseNumber", "rentedFrom");
//
//        }
//
//        //status, transactiondate , licensePlate and rentedFrom
//        else if (!reqPar.get("status").equals("") && !reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && !reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromFourCriteria(reqPar.get("status"), reqPar.get("transactionDate"), reqPar.get("licensePlate"), reqPar.get("validFrom"), "status", "transactionDate", "licensePlate", "rentedFrom");
//
//        }
//
//
//        //status, transactiondate , licenseNumber and rentedTill
//        else if (!reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && !reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && !reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromFourCriteria(reqPar.get("status"), reqPar.get("transactionDate"), reqPar.get("licenseNumber"), reqPar.get("validTill"), "status", "transactionDate", "licenseNumber", "rentedTill");
//
//        }
//
//        //status, transactiondate , licensePlate and rentedTill
//        else if (!reqPar.get("status").equals("") && !reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && !reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromFourCriteria(reqPar.get("status"), reqPar.get("transactionDate"), reqPar.get("licensePlate"), reqPar.get("validTill"), "status", "transactionDate", "licensePlate", "rentedTill");
//
//        }
//        //rentedFrom, transactiondate , licenseNumber and rentedTill
//        else if (reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && !reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && !reqPar.get("validFrom").equals("") && !reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromFourCriteria(reqPar.get("validFrom"), reqPar.get("transactionDate"), reqPar.get("licenseNumber"), reqPar.get("validTill"), "rentedFrom", "transactionDate", "licenseNumber", "rentedTill");
//
//        }
//
//        //rentedFrom, transactiondate , licensePlate and rentedTill
//        else if (reqPar.get("status").equals("") && !reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && !reqPar.get("validFrom").equals("") && !reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromFourCriteria(reqPar.get("validFrom"), reqPar.get("transactionDate"), reqPar.get("licensePlate"), reqPar.get("validTill"), "rentedFrom", "transactionDate", "licenseNumber", "rentedTill");
//
//        }
//
//
//        //all parameter search
//        else if (!reqPar.get("status").equals("") && !reqPar.get("licensePlate").equals("") && !reqPar.get("licenseNumber").equals("")
//                && !reqPar.get("transactionDate").equals("") && !reqPar.get("validFrom").equals("") && !reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getTransactionFromAllCriteria(reqPar.get("status"), reqPar.get("licensePlate"),
//                    reqPar.get("licenseNumber"), reqPar.get("transactionDate"), reqPar.get("validFrom"), reqPar.get("validTill"), "All");
//        }
//
//
//        //retrun all
//
//        else if (reqPar.get("status").equals("") && reqPar.get("licensePlate").equals("") && reqPar.get("licenseNumber").equals("")
//                && reqPar.get("transactionDate").equals("") && reqPar.get("validFrom").equals("") && reqPar.get("validTill").equals("")) {
//
//            transactions = transactionService.getAllTransactions();
//        }
//
//
//        //sort
//        //by status
//        if (reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("status")) {
//            transactions = transactionService.sortByStatus(transactions);
//        }
//
//        //by licensePlate
//        else if (reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("licensePlate")) {
//            transactions = transactionService.sortBylicensePlate(transactions);
//        }
//
//        //by Client
//        else if (reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("licenseNumber")) {
//            transactions = transactionService.sortByClient(transactions);
//        }
//
//        //by tansactionDate
//        else if (reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("transactionDate")) {
//            transactions = transactionService.sortByTransactiondate(transactions);
//        }
//
//        //by rentedFrom
//        else if (reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("rentedFrom")) {
//            transactions = transactionService.sortByRentedFrom(transactions);
//        }
//
//        //by rentedTill
//        else if (reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("rentedTill")) {
//            transactions = transactionService.sortByRentedTill(transactions);
//        }
//
//        logger.info("Transaction length" + transactions.size());
//        model.addAttribute("transaction_found", reqPar);
//
//
//        if (transactions != null) {
//            model.addAttribute("transaction_found", "RESULT_FOUND");
//            model.addAttribute("transaction_results", transactions);
//        } else {
//            model.addAttribute("transaction_found", "RESULT_NOT_FOUND");
//            model.addAttribute("transaction_results", "transactions do not exist");
//        }


        return "viewTransactions";
    }
}