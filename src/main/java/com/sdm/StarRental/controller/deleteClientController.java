package com.sdm.StarRental.controller;

import com.sdm.StarRental.dataMapper.ClientDM;
import com.sdm.StarRental.model.Client;
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
public class deleteClientController {
    private static Logger logger = LoggerFactory.getLogger(deleteClientController.class);

    private ClientDM clientDM;
    private ClientUnitOfWork clientUnitOfWork;

    public deleteClientController(){
        clientDM = new ClientDM();
        clientUnitOfWork = new ClientUnitOfWork(clientDM);
    }




}
