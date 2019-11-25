package com.sdm.StarRental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sdm.StarRental.dataMapper.VehicleDM;
import com.sdm.StarRental.model.Vehicle;
import com.sdm.StarRental.objectUtilities.Utilities;
import com.sdm.StarRental.unitOfWork.VehicleUnitOfWork;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
public class VehicleController {

private static Logger logger = LoggerFactory.getLogger(VehicleController.class);
	
	private VehicleDM vehicleDataMapper;
	
	private VehicleUnitOfWork vehicleUnitOfWork;
	
	public VehicleController() {
		vehicleDataMapper = new VehicleDM();
		vehicleUnitOfWork = VehicleUnitOfWork.getInstance();
		
	}
	
	@RequestMapping(value = "/backtoadminmainpage", method = RequestMethod.GET)
	public String redirectUserAdmin(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession)
			throws Exception {
		System.out.println("Here for admin........");
		if (Utilities.validateSession(httpSession)) {

	model.addAttribute("loggedinusername", httpSession.getAttribute("userNameLoggedIn"));
			return "adminPanel";
		}

		return "unauthorized";

	}
	

	@RequestMapping(value = "/backtomainpage", method = RequestMethod.GET)
	public String redirectUser(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession)
			throws Exception {

		if (Utilities.validateSession(httpSession)) {

			model.addAttribute("loggedinusername", httpSession.getAttribute("userNameLoggedIn"));


			String userType = String.valueOf(httpSession.getAttribute("userType"));
			System.out.println("session user type is.................... " + userType);
			
			if(userType.equalsIgnoreCase("admin")){
				return "adminPanel";
			}

			return "clerkMainPage";
		}

		return "unauthorized";

	}
	

	@RequestMapping(value = "/createNewVehicle",method = RequestMethod.GET)
	public String createNewVehicle(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession) {
		return "createNewVehicle";
	}
	
	@RequestMapping(value = "/createVehicleForm",method = RequestMethod.POST)
	public String createNewVehicleForm(@RequestParam Map<String, String> reqPar,@RequestParam  MultipartFile file, ModelMap modelMap, HttpSession httpSession, Vehicle req2) throws Exception {
	//	if (Utilities.validateSession(httpSession)) {
		String type = reqPar.get("type"); 
		String make = reqPar.get("make"); 
		String model = reqPar.get("model");  
		int year =Integer.parseInt(reqPar.get("year")); 
		String color = reqPar.get("color"); 
		String vehicleLicensePlate= reqPar.get("licensePlate");
		Vehicle vehicle = new Vehicle(type, year, model, color, vehicleLicensePlate, "Available", make);
		vehicleUnitOfWork.create(vehicle); 
		return "adminPanel";
		//}else {
		//	return "unauthorized";
		//}
	}
	
	 @RequestMapping(value = "/modifyCatalog", method = RequestMethod.GET)
     public String loadCatalogToModify(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession)
             throws Exception {

      //   if (Utilities.validateSession(httpSession)) {

             ArrayList<Vehicle> vehicles = vehicleDataMapper.getAllVehicles();
             if (!vehicles.isEmpty()) {
                 logger.info("Catalog length" + vehicles.size());
                 Vehicle v;
                 
                 if (vehicles != null) {
                     // ModelMap -> default java method : automatically maps String/key on the page
                     // to object passed
                     model.addAttribute("modifyCatalogfound", "RESULT_FOUND");
                     model.addAttribute("modifyCatalogresults", vehicles);

                 }
             } else {
                 model.addAttribute("modifyCatalogfound", "RESULTS_NOT_FOUND");
             }

             return "modifyCatalog";
       //  } else {
       //      return "unauthorized";
       //  }

     }

	 @RequestMapping(value = "/searchmodifyCatalog", method = RequestMethod.POST)
     public String filterCatalogForBrowseCatalog(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession)
             throws Exception {

         ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
         // One search criteria
         if (!reqPar.get("model").equals("") && reqPar.get("make").equals("")  && reqPar.get("status").equals("")&& reqPar.get("color").equals("")
                 && reqPar.get("year").equals("")) {
             vehicles = vehicleDataMapper.getVehicleFromOneCriteria(reqPar.get("model"), null, "model");
         } else if (reqPar.get("model").equals("") && reqPar.get("make").equals("") && !reqPar.get("status").equals("") && reqPar.get("color").equals("")
                 && reqPar.get("year").equals("")) {
             vehicles = vehicleDataMapper.getVehicleFromOneCriteria(reqPar.get("status"), null, "status");
         } else if (reqPar.get("model").equals("") && reqPar.get("status").equals("") && reqPar.get("make").equals("") && !reqPar.get("color").equals("")
                 && reqPar.get("year").equals("")) {
             vehicles = vehicleDataMapper.getVehicleFromOneCriteria(reqPar.get("color"), null, "color");
         } else if (reqPar.get("model").equals("") && reqPar.get("status").equals("") && reqPar.get("make").equals("") && reqPar.get("color").equals("")
                 && !reqPar.get("year").equals("")) {
             vehicles = vehicleDataMapper.getVehicleFromOneCriteria(reqPar.get("year"), reqPar.get("condition"), "year");
         }
         else if (reqPar.get("model").equals("") && !reqPar.get("make").equals("") && reqPar.get("status").equals("") && reqPar.get("color").equals("")
                 && reqPar.get("year").equals("")) {
        	 System.out.println("Status search  ");
             vehicles = vehicleDataMapper.getVehicleFromOneCriteria(reqPar.get("make"), null, "make");
         } 
         
         // Two search criteria
         else if (!reqPar.get("model").equals("") && !reqPar.get("make").equals("") && reqPar.get("color").equals("")
                 && reqPar.get("year").equals("")) {
             vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("model"), reqPar.get("make"), null, "model",
                     "make");
         } else if (!reqPar.get("model").equals("") && reqPar.get("make").equals("") && !reqPar.get("color").equals("")
                 && reqPar.get("year").equals("")) {

             vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("model"), reqPar.get("color"), null, "model",
                     "color");

         } else if (!reqPar.get("model").equals("") && reqPar.get("make").equals("") && reqPar.get("color").equals("")
                 && !reqPar.get("year").equals("")) {

             vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("year"), reqPar.get("model"),
                     reqPar.get("condition"), "year", "model");

         } else if (reqPar.get("model").equals("") && !reqPar.get("make").equals("") && !reqPar.get("color").equals("")
                 && reqPar.get("year").equals("")) {

             vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("color"), reqPar.get("make"), null, "color",
                     "make");
         } else if (reqPar.get("model").equals("") && !reqPar.get("make").equals("") && reqPar.get("color").equals("")
                 && !reqPar.get("year").equals("")) {

             vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("year"), reqPar.get("make"),
                     reqPar.get("condition"), "year", "make");
         } else if (reqPar.get("model").equals("") && reqPar.get("make").equals("") && !reqPar.get("color").equals("")
                 && !reqPar.get("year").equals("")) {

             vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("year"), reqPar.get("color"),
                     reqPar.get("condition"), "year", "color");
         }

         // Three condition
         else if (!reqPar.get("model").equals("") && !reqPar.get("make").equals("") && !reqPar.get("color").equals("")
                 && reqPar.get("year").equals("")) {

             vehicles = vehicleDataMapper.getVehicleFromThreeCriteria(reqPar.get("model"), reqPar.get("make"),
                     reqPar.get("color"), null, "model", "make", "color");

         } else if (!reqPar.get("model").equals("") && !reqPar.get("make").equals("") && reqPar.get("color").equals("")
                 && !reqPar.get("year").equals("")) {

             vehicles = vehicleDataMapper.getVehicleFromThreeCriteria(reqPar.get("year"), reqPar.get("model"),
                     reqPar.get("make"), reqPar.get("condition"), "year", "model", "make");

         } else if (reqPar.get("model").equals("") && !reqPar.get("make").equals("") && !reqPar.get("color").equals("")
                 && !reqPar.get("year").equals("")) {

             vehicles = vehicleDataMapper.getVehicleFromThreeCriteria(reqPar.get("year"), reqPar.get("make"),
                     reqPar.get("color"), reqPar.get("condition"), "year", "make", "color");

         } else if (!reqPar.get("model").equals("") && reqPar.get("make").equals("") && !reqPar.get("color").equals("")
                 && !reqPar.get("year").equals("")) {

             vehicles = vehicleDataMapper.getVehicleFromThreeCriteria(reqPar.get("year"), reqPar.get("model"),
                     reqPar.get("color"), reqPar.get("condition"), "year", "model", "color");

         }
         // All search criteria
         else if (!reqPar.get("model").equals("") && !reqPar.get("make").equals("") && !reqPar.get("color").equals("")
                 && !reqPar.get("year").equals("")) {
             vehicles = vehicleDataMapper.getVehicleFromAllCriteria(reqPar.get("year"), reqPar.get("model"),
                     reqPar.get("make"), reqPar.get("color"), reqPar.get("condition"), "year", "model", "make", "color", null, null, null, null, null, null);
         }

         else if (reqPar.get("model").equals("") && reqPar.get("make").equals("") && reqPar.get("color").equals("")
                 && reqPar.get("year").equals("")) {
             vehicles = vehicleDataMapper.getAllVehicles();

         }

		
		  if(reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("year")) {
		  vehicles=vehicleDataMapper.sortByYear(vehicles);
		  
		  }else if(reqPar.containsKey("sortBy") &&
		  reqPar.get("sortBy").equals("model")) {
		  vehicles=vehicleDataMapper.sortByModel(vehicles);
		  
		  }else if(reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("make"))
		  { vehicles=vehicleDataMapper.sortByMake(vehicles);
		  
		  }else if(reqPar.containsKey("sortBy") &&
		  reqPar.get("sortBy").equals("licensePlate")) {
		  vehicles=vehicleDataMapper.sortByLicensePlate(vehicles);
		  
		  }else if(reqPar.containsKey("sortBy") &&
		  reqPar.get("sortBy").equals("color")) {
		  vehicles=vehicleDataMapper.sortByColor(vehicles);
		  
		  }else if(reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("type"))
		  { vehicles=vehicleDataMapper.sortByType(vehicles);
		  
		  }
		  
		  else if(reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("status"))
		  { vehicles=vehicleDataMapper.sortByStatus(vehicles);
		  
		  }
		 
         logger.info("Catalog length" + vehicles.size());
         model.addAttribute("SearchParams", reqPar);
         if (vehicles != null) {
             model.addAttribute("modifyCatalogfound", "RESULT_FOUND");
             model.addAttribute("modifyCatalogresults", vehicles);
         } else {
             model.addAttribute("modifyCatalogfound", "RESULT_NOT_FOUND");
             model.addAttribute("modifyCatalogresults", "Cannot find item!!");
         }

         return "modifyCatalog";

     }
	 
     @RequestMapping(value = "/modifyCatalogItem", method = RequestMethod.POST)
     public String modifyCatalogItem(@RequestParam Map<String, String> reqParam, ModelMap model, HttpSession httpSession)
             throws Exception {

         Vehicle selectedVehicle = vehicleDataMapper.getVehicleByLicenseNo(reqParam.get("licenseNumber"));

         if(selectedVehicle != null) {
     		//Vehicle v5 = new Vehicle("SUV", 1999, "BENZ", "BROWN", "XY7 224", "RENTED", "MERC");
        	 Vehicle modifiedElement = new Vehicle (reqParam.get("type"), Integer.parseInt(reqParam.get("year")), reqParam.get("model"),reqParam.get("color"), selectedVehicle.getvehicleLicensePlate(), selectedVehicle.getStatus(), reqParam.get("make"));
        	 vehicleUnitOfWork.update(modifiedElement);
         }

         ArrayList<Vehicle> clients = vehicleDataMapper.getAllVehicles();

         if (!clients.isEmpty()) {
             model.addAttribute("modifyCatalogfound", "RESULT_FOUND");
             model.addAttribute("modifyCatalogresults", clients);
             logger.info("found");
         } else {
             model.addAttribute("modifyCatalogfound", "RESULT_NOT_FOUND");
             model.addAttribute("modifyCatalogresults", "Cannot find item!!");
         }

         return "modifyCatalog";
     }

     @RequestMapping(value = "/deleteVehicle",method = RequestMethod.GET)
     public String deleteVehicle(@RequestParam Map<String,String> reqParam, ModelMap model, HttpSession httpSession)
             throws Exception {

             ArrayList<Vehicle> vehicle = vehicleDataMapper.getAllVehicles();

             if (vehicle != null) {
                 model.addAttribute("vehicle_found", "RESULT_FOUND");
                 model.addAttribute("vehicle_results", vehicle );
             }
             else {
                 model.addAttribute("vehicle_found", "RESULT_NOT_FOUND");
                 model.addAttribute("vehicle_results", "Cannot find Vehicle!!");
             }
             return "deleteVehicle";
       
       
     
     }

     @RequestMapping(value = "/searchDelVehicleForm",method = RequestMethod.POST)
     public String filterCatalogForDeletForm(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession)
  			throws Exception {

  		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
  		// One search criteria
  		if (!reqPar.get("model").equals("") && reqPar.get("make").equals("") && reqPar.get("color").equals("")
  				&& reqPar.get("year").equals("")) {
  			vehicles = vehicleDataMapper.getVehicleFromOneCriteria(reqPar.get("model"), null, "model");
  		} else if (reqPar.get("model").equals("") && !reqPar.get("make").equals("") && reqPar.get("color").equals("")
  				&& reqPar.get("year").equals("")) {
  			vehicles = vehicleDataMapper.getVehicleFromOneCriteria(reqPar.get("make"), null, "make");
  		} else if (reqPar.get("model").equals("") && reqPar.get("make").equals("") && !reqPar.get("color").equals("")
  				&& reqPar.get("year").equals("")) {
  			vehicles = vehicleDataMapper.getVehicleFromOneCriteria(reqPar.get("color"), null, "color");
  		} else if (reqPar.get("model").equals("") && reqPar.get("make").equals("") && reqPar.get("color").equals("")
  				&& !reqPar.get("year").equals("")) {
  			vehicles = vehicleDataMapper.getVehicleFromOneCriteria(reqPar.get("year"), reqPar.get("condition"), "year");
  		}
  		// Two search criteria
  		else if (!reqPar.get("model").equals("") && !reqPar.get("make").equals("") && reqPar.get("color").equals("")
  				&& reqPar.get("year").equals("")) {
  			vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("model"), reqPar.get("make"), null, "model",
  					"make");
  		} else if (!reqPar.get("model").equals("") && reqPar.get("make").equals("") && !reqPar.get("color").equals("")
  				&& reqPar.get("year").equals("")) {

  			vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("model"), reqPar.get("color"), null, "model",
  					"color");

  		} else if (!reqPar.get("model").equals("") && reqPar.get("make").equals("") && reqPar.get("color").equals("")
  				&& !reqPar.get("year").equals("")) {

  			vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("year"), reqPar.get("model"),
  					reqPar.get("condition"), "year", "model");

  		} else if (reqPar.get("model").equals("") && !reqPar.get("make").equals("") && !reqPar.get("color").equals("")
  				&& reqPar.get("year").equals("")) {

  			vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("color"), reqPar.get("make"), null, "color",
  					"make");
  		} else if (reqPar.get("model").equals("") && !reqPar.get("make").equals("") && reqPar.get("color").equals("")
  				&& !reqPar.get("year").equals("")) {

  			vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("year"), reqPar.get("make"),
  					reqPar.get("condition"), "year", "make");
  		} else if (reqPar.get("model").equals("") && reqPar.get("make").equals("") && !reqPar.get("color").equals("")
  				&& !reqPar.get("year").equals("")) {

  			vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("year"), reqPar.get("color"),
  					reqPar.get("condition"), "year", "color");
  		}

  		// Three condition
  		else if (!reqPar.get("model").equals("") && !reqPar.get("make").equals("") && !reqPar.get("color").equals("")
  				&& reqPar.get("year").equals("")) {

  			vehicles = vehicleDataMapper.getVehicleFromThreeCriteria(reqPar.get("model"), reqPar.get("make"),
  					reqPar.get("color"), null, "model", "make", "color");

  		} else if (!reqPar.get("model").equals("") && !reqPar.get("make").equals("") && reqPar.get("color").equals("")
  				&& !reqPar.get("year").equals("")) {

  			vehicles = vehicleDataMapper.getVehicleFromThreeCriteria(reqPar.get("year"), reqPar.get("model"),
  					reqPar.get("make"), reqPar.get("condition"), "year", "model", "make");

  		} else if (reqPar.get("model").equals("") && !reqPar.get("make").equals("") && !reqPar.get("color").equals("")
  				&& !reqPar.get("year").equals("")) {

  			vehicles = vehicleDataMapper.getVehicleFromThreeCriteria(reqPar.get("year"), reqPar.get("make"),
  					reqPar.get("color"), reqPar.get("condition"), "year", "make", "color");

  		} else if (!reqPar.get("model").equals("") && reqPar.get("make").equals("") && !reqPar.get("color").equals("")
  				&& !reqPar.get("year").equals("")) {

  			vehicles = vehicleDataMapper.getVehicleFromThreeCriteria(reqPar.get("year"), reqPar.get("model"),
  					reqPar.get("color"), reqPar.get("condition"), "year", "model", "color");

  		}
  		// All search criteria
  		else if (!reqPar.get("model").equals("") && !reqPar.get("make").equals("") && !reqPar.get("color").equals("")
  				&& !reqPar.get("year").equals("")) {
  			vehicles = vehicleDataMapper.getVehicleFromAllCriteria(reqPar.get("year"), reqPar.get("model"),
  					reqPar.get("make"), reqPar.get("color"), reqPar.get("condition"), "year", "model", "make", "color", null, null, null, null, null, null);
  		}

  		else if (reqPar.get("model").equals("") && reqPar.get("make").equals("") && reqPar.get("color").equals("")
  				&& reqPar.get("year").equals("")) {
  			vehicles = vehicleDataMapper.getAllVehicles();

  		}
  		
  		if(reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("year")) {
  			vehicles=vehicleDataMapper.sortByYear(vehicles);
  			
  		}else if(reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("model")) {
  			vehicles=vehicleDataMapper.sortByModel(vehicles);
  			
  		}else if(reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("make")) {
  			vehicles=vehicleDataMapper.sortByMake(vehicles);
  			
  		}else if(reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("licensePlate")) {
  			vehicles=vehicleDataMapper.sortByLicensePlate(vehicles);
  			
  		}else if(reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("color")) {
  			vehicles=vehicleDataMapper.sortByColor(vehicles);
  			
  		}else if(reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("type")) {
  			vehicles=vehicleDataMapper.sortByType(vehicles);
  			
  		}
  		logger.info("Catalog length" + vehicles.size());
  		model.addAttribute("SearchParams", reqPar);
  		if (vehicles != null) {
  			model.addAttribute("vehicle_found", "RESULT_FOUND");
  			model.addAttribute("vehicle_results", vehicles);
  		} else {
  			model.addAttribute("vehicle_found", "RESULT_NOT_FOUND");
  			model.addAttribute("vehicle_results", "Cannot find item!!");
  		}

  		return "deleteVehicle";

  	}
       
     @RequestMapping(value = "/deleteVehicleInfo",method = RequestMethod.POST)
     public String deleteVehicleAction(@RequestParam Map<String,String> reqParam, ModelMap model,HttpSession httpSession) throws Exception{

         
     	String vehicleLicense = reqParam.get("licensePlate").replace("_", " ");
     	logger.info(reqParam.get("licensePlate"));
        vehicleUnitOfWork.delete(vehicleLicense);
  logger.info("Deleted ");
         ArrayList<Vehicle> vehicles = vehicleDataMapper.getAllVehicles();

         if(!vehicles.isEmpty()){
             model.addAttribute("vehicle_found","RESULT_FOUND");
             model.addAttribute("vehicle_results",vehicles);
             logger.info("found");
         }
         else {
             model.addAttribute("vehicle_found", "RESULT_NOT_FOUND");
             model.addAttribute("vehicle_results", "Cannot find item!!");
         }

         return "deleteVehicle";
     }

     @RequestMapping(value = "/viewCatalog", method = RequestMethod.GET)
 	public String loadVehicleCatalogToBrowse(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession)
 			throws Exception {

 		
 			ArrayList<Vehicle> vehicles = vehicleDataMapper.getAllVehicles();
 			if (!vehicles.isEmpty()) {
 				logger.info("Catalog length" + vehicles.size());

 				if (vehicles != null) {
 					// ModelMap -> default java method : automatically maps String/key on the page
 					// to object passed
 					model.addAttribute("browsecatalogfound", "RESULT_FOUND");
 					model.addAttribute("browsecatalogresults", vehicles);

 				}
 			} else {
 				model.addAttribute("browsecatalogfound", "RESULTS_NOT_FOUND");
 			}

 			return "viewCatalog";
 		

 	}

 	/**
 	 * This method is for viewing the details of the vehicles.
 	 * @param licenseNo
 	 * @return
 	 */
 	@RequestMapping(value = "/browsecatalog/{licenseNo}", method = RequestMethod.GET)
 	public ResponseEntity<Vehicle> getCatalogItem(@PathVariable String licenseNo, ModelMap model, HttpSession httpSession) {
 		try {
 			return ResponseEntity.ok(vehicleDataMapper.getVehicleByLicenseNo(licenseNo));
 		} catch (Exception e) {
 			if (e instanceof NullPointerException) {
 				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Vehicle());
 			}
 			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Vehicle());
 		}
 	}

 	/**
 	 * This method is for filtering the table with a combination of 4 search criteria
 	 * @param reqPar
 	 * @param model
 	 * @param httpSession
 	 * @return
 	 * @throws Exception
 	 */
 	@RequestMapping(value = "/searchbrowsecatalog", method = RequestMethod.POST)
 	public String filterVehicleCatalog(@RequestParam Map<String, String> reqPar, ModelMap model, HttpSession httpSession)
 			throws Exception {

 		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
 		// One search criteria
 		if (!reqPar.get("model").equals("") && reqPar.get("make").equals("") && reqPar.get("color").equals("")
 				&& reqPar.get("year").equals("")) {
 			vehicles = vehicleDataMapper.getVehicleFromOneCriteria(reqPar.get("model"), null, "model");
 		} else if (reqPar.get("model").equals("") && !reqPar.get("make").equals("") && reqPar.get("color").equals("")
 				&& reqPar.get("year").equals("")) {
 			vehicles = vehicleDataMapper.getVehicleFromOneCriteria(reqPar.get("make"), null, "make");
 		} else if (reqPar.get("model").equals("") && reqPar.get("make").equals("") && !reqPar.get("color").equals("")
 				&& reqPar.get("year").equals("")) {
 			vehicles = vehicleDataMapper.getVehicleFromOneCriteria(reqPar.get("color"), null, "color");
 		} else if (reqPar.get("model").equals("") && reqPar.get("make").equals("") && reqPar.get("color").equals("")
 				&& !reqPar.get("year").equals("")) {
 			vehicles = vehicleDataMapper.getVehicleFromOneCriteria(reqPar.get("year"), reqPar.get("condition"), "year");
 		}
 		// Two search criteria
 		else if (!reqPar.get("model").equals("") && !reqPar.get("make").equals("") && reqPar.get("color").equals("")
 				&& reqPar.get("year").equals("")) {
 			vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("model"), reqPar.get("make"), null, "model",
 					"make");
 		} else if (!reqPar.get("model").equals("") && reqPar.get("make").equals("") && !reqPar.get("color").equals("")
 				&& reqPar.get("year").equals("")) {

 			vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("model"), reqPar.get("color"), null, "model",
 					"color");

 		} else if (!reqPar.get("model").equals("") && reqPar.get("make").equals("") && reqPar.get("color").equals("")
 				&& !reqPar.get("year").equals("")) {

 			vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("year"), reqPar.get("model"),
 					reqPar.get("condition"), "year", "model");

 		} else if (reqPar.get("model").equals("") && !reqPar.get("make").equals("") && !reqPar.get("color").equals("")
 				&& reqPar.get("year").equals("")) {

 			vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("color"), reqPar.get("make"), null, "color",
 					"make");
 		} else if (reqPar.get("model").equals("") && !reqPar.get("make").equals("") && reqPar.get("color").equals("")
 				&& !reqPar.get("year").equals("")) {

 			vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("year"), reqPar.get("make"),
 					reqPar.get("condition"), "year", "make");
 		} else if (reqPar.get("model").equals("") && reqPar.get("make").equals("") && !reqPar.get("color").equals("")
 				&& !reqPar.get("year").equals("")) {

 			vehicles = vehicleDataMapper.getVehicleFromTwoCriteria(reqPar.get("year"), reqPar.get("color"),
 					reqPar.get("condition"), "year", "color");
 		}

 		// Three condition
 		else if (!reqPar.get("model").equals("") && !reqPar.get("make").equals("") && !reqPar.get("color").equals("")
 				&& reqPar.get("year").equals("")) {

 			vehicles = vehicleDataMapper.getVehicleFromThreeCriteria(reqPar.get("model"), reqPar.get("make"),
 					reqPar.get("color"), null, "model", "make", "color");

 		} else if (!reqPar.get("model").equals("") && !reqPar.get("make").equals("") && reqPar.get("color").equals("")
 				&& !reqPar.get("year").equals("")) {

 			vehicles = vehicleDataMapper.getVehicleFromThreeCriteria(reqPar.get("year"), reqPar.get("model"),
 					reqPar.get("make"), reqPar.get("condition"), "year", "model", "make");

 		} else if (reqPar.get("model").equals("") && !reqPar.get("make").equals("") && !reqPar.get("color").equals("")
 				&& !reqPar.get("year").equals("")) {

 			vehicles = vehicleDataMapper.getVehicleFromThreeCriteria(reqPar.get("year"), reqPar.get("make"),
 					reqPar.get("color"), reqPar.get("condition"), "year", "make", "color");

 		} else if (!reqPar.get("model").equals("") && reqPar.get("make").equals("") && !reqPar.get("color").equals("")
 				&& !reqPar.get("year").equals("")) {

 			vehicles = vehicleDataMapper.getVehicleFromThreeCriteria(reqPar.get("year"), reqPar.get("model"),
 					reqPar.get("color"), reqPar.get("condition"), "year", "model", "color");

 		}
 		// All search criteria
 		else if (!reqPar.get("model").equals("") && !reqPar.get("make").equals("") && !reqPar.get("color").equals("")
 				&& !reqPar.get("year").equals("")) {
 			vehicles = vehicleDataMapper.getVehicleFromAllCriteria(reqPar.get("year"), reqPar.get("model"),
 					reqPar.get("make"), reqPar.get("color"), reqPar.get("condition"), "year", "model", "make", "color", null, null, null, null, null, null);
 		}

 		else if (reqPar.get("model").equals("") && reqPar.get("make").equals("") && reqPar.get("color").equals("")
 				&& reqPar.get("year").equals("")) {
 			vehicles = vehicleDataMapper.getAllVehicles();

 		}
 		
		
		  if(reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("year")) {
		  vehicles=vehicleDataMapper.sortByYear(vehicles);
		  
		  }else if(reqPar.containsKey("sortBy") &&
		  reqPar.get("sortBy").equals("model")) {
		  vehicles=vehicleDataMapper.sortByModel(vehicles);
		  
		  }else if(reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("make"))
		  { vehicles=vehicleDataMapper.sortByMake(vehicles);
		  
		  }else if(reqPar.containsKey("sortBy") &&
		  reqPar.get("sortBy").equals("licensePlate")) {
		  vehicles=vehicleDataMapper.sortByLicensePlate(vehicles);
		  
		  }else if(reqPar.containsKey("sortBy") &&
		  reqPar.get("sortBy").equals("color")) {
		  vehicles=vehicleDataMapper.sortByColor(vehicles);
		  
		  }else if(reqPar.containsKey("sortBy") && reqPar.get("sortBy").equals("type"))
		  { vehicles=vehicleDataMapper.sortByType(vehicles);
		  
		  }
		 
 		logger.info("Catalog length" + vehicles.size());
 		model.addAttribute("SearchParams", reqPar);
 		if (vehicles != null) {
 			model.addAttribute("browsecatalogfound", "RESULT_FOUND");
 			model.addAttribute("browsecatalogresults", vehicles);
 		} else {
 			model.addAttribute("browsecatalogfound", "RESULT_NOT_FOUND");
 			model.addAttribute("browsecatalogresults", "Cannot find item!!");
 		}

 		return "viewCatalog";

 	}



}
