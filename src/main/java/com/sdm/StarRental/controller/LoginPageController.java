package com.sdm.StarRental.controller;
import java.util.ArrayList;

import java.util.Map;

import javax.servlet.http.HttpSession;


import com.sdm.StarRental.dataMapper.UserDM;
import com.sdm.StarRental.dataMapper.VehicleDM;
import com.sdm.StarRental.model.User;
import com.sdm.StarRental.model.Vehicle;
import com.sdm.StarRental.tableDataGateway.UserTDG;
import com.sdm.StarRental.unitOfWork.VehicleUnitOfWork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;



/**
 * This controller manages the login of the user and redirects the user to the
 * relevant main page.
 * 
 * @author arejea
 *
 */

@Controller
@SessionAttributes("name")
public class LoginPageController {

	private static Logger logger = LoggerFactory.getLogger(LoginPageController.class);

	@Autowired
	private UserDM userService;
	@Autowired
	private VehicleUnitOfWork test;;
	
	/**
	 * This method loads the login page
	 * 
	 * @param reqPar
	 * @param model
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value = "/loginpage", method = RequestMethod.GET)
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String getUser(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession)
			throws Exception {
		//test();
		
		return "loginPage";

	}

	/**
	 * This method opens the Main page for the user
	 * 
	 * @param reqPar
	 * @param model
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userHomePagee", method = RequestMethod.POST)
	public String redirectUser(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession)
			throws Exception {

		String username = reqPar.get("userName");
		String pass = reqPar.get("userPass");
		
		System.out.println("inside login controller"+ username+pass ); 
		boolean user = userService.authUser(username, pass);

		if (user) {

			User userObj = userService.getUser(username, pass);
	
			// if user == admin
			if (userObj.getUserType().equals("A")) {
				// if its a new session as admin
				if (httpSession.getAttribute("userLoggedIn") == null) {
					httpSession.setAttribute("userType", "admin");
					httpSession.setAttribute("userLoggedIn", 1);
					httpSession.setAttribute("userNameLoggedIn", username);
					return "adminPanel";
				}
	
				// check if same admin is logged in
				if (userObj.getUserName().equals(httpSession.getAttribute("userNameLoggedIn").toString())) {
					return "adminPanel";
				}
				
				// show pop up instead ?
				return "adminAlreadyPresent";
			}
			// if user == clerk
			else if (userObj.getUserType().equals("C")) {
				httpSession.setAttribute("userType", "clerk");
				httpSession.setAttribute("userLoggedIn", 1);
				httpSession.setAttribute("userNameLoggedIn", username);
				return "clerkMainPage";
			}
		}

		model.addAttribute("Invaliduser", "No User found");
		//return "loginpage";
		return "loginPage";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String userLogOut(HttpSession httpSession) {
		/*
		 * if(Utilities.validateSession(httpSession)) { httpSession.invalidate(); }
		 */
		
		return "loginPage";
	}
	
	
	
	public void test() throws Exception {
		//INSERT INTO c_catalog (type,make,model,year,color,vehicleLicensePlate,status) 
		//VALUES ('HYUN','MERC','BENZ',1999,'WHITE','XZ7 327','RENTED')

		Vehicle v = new Vehicle("SUV", 1999, "BENZ", "WHITE", "AY7 324", "RENTED", "MERC");
		Vehicle v1 = new Vehicle("SUV", 1998, "BENZ", "WHITE", "XZ8 225", "RENTED", "MERC");
		Vehicle v2 = new Vehicle("SUV", 1999, "BENZ", "WHITE", "BZ9 226", "RENTED", "MERC");
		Vehicle v3 = new Vehicle("SUV", 1997, "BENZ", "WHITE", "XZ6 327", "RENTED", "MERC");
		Vehicle v4 = new Vehicle("SUV", 1996, "BENZ", "WHITE", "XZ5 338", "RENTED", "MERC");
		//Vehicle v5 = new Vehicle("SUV", 1994, "BENZ", "GREY", "XZ4 229", "RENTED", "MERC");
		Vehicle v6 = new Vehicle("SUV", 1994, "BENZ", "PINK", "XZ3 229", "RENTED", "MERC");
		
		//test.create(v6);
		Vehicle v5 = new Vehicle("SUV", 1999, "BENZ", "BROWN", "XY7 224", "RENTED", "MERC");
		
		test.update(v5);
		test.create(v);
		v = new Vehicle("SUV", 1899, "BENZ", "WHITE", "XY7 324", "RENTED", "MERC");
		test.delete("XY7 324");
		test.update(v);
		test.create(v2);
		//test.create(v2);
		System.out.println("vehicle added");
		
		
	}
}
