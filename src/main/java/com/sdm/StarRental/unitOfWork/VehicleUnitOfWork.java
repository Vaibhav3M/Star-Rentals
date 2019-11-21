package com.sdm.StarRental.unitOfWork;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sdm.StarRental.Enum.unitOfWorkAction;
import com.sdm.StarRental.dataMapper.VehicleDM;
import com.sdm.StarRental.model.Vehicle;
import com.sdm.StarRental.model.unitOfWork;

@Service
@Scope("singleton")
public class VehicleUnitOfWork implements IUnitOfWork<Vehicle, String> {

	@Value("{unit-of-work.batch-no}")
	private String unitOfWorkBatchNo;
	
	private HashMap<String, unitOfWork<Vehicle>> jobs = new HashMap<String, unitOfWork<Vehicle>>();
	
	private VehicleDM vehicleDataMapper;
	
	
	public VehicleUnitOfWork() {
		
		this.vehicleDataMapper = new VehicleDM();

	}
	
	@Override
	public void commit() {
	

		if(jobs.size()==3) {
			jobs.forEach((key, element) -> {
				Vehicle vehicle = element.getE();
				System.out.println(element.getE().toString());
				if (element.getAction() == unitOfWorkAction.CREATE) {
					System.out.println("action is create");
					commitCreateVehicle(vehicle);
				} 
				else if(element.getAction() == unitOfWorkAction.UPDATE) {
					commitUpdateVehicle(vehicle);
				} else if(element.getAction() == unitOfWorkAction.DELETE) {
					commitDeleteVehicle(vehicle);
				}
			});
			jobs = new HashMap<String, unitOfWork<Vehicle>>();
		}
		
	}

	private void commitDeleteVehicle(Vehicle vehicle) {
		try {
			vehicleDataMapper.deleteVehicle(vehicle.getvehicleLicensePlate());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void commitUpdateVehicle(Vehicle vehicle) {
		try {
			
			vehicleDataMapper.modifyVehicle(vehicle.getType(),vehicle.getMake(),vehicle.getModel(), vehicle.getYear(), vehicle.getColor(),vehicle.getvehicleLicensePlate(),vehicle.getStatus());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void commitCreateVehicle(Vehicle vehicle) {
		System.out.println("CREATE elemnt commit "+ vehicle.getvehicleLicensePlate());

		try {
			vehicleDataMapper.addNewVehicle(vehicle.getType(), vehicle.getYear(), vehicle.getModel(), vehicle.getColor(),vehicle.getvehicleLicensePlate(), vehicle.getStatus(),vehicle.getMake());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void create(Vehicle element) {
		if(isDirty(element.getvehicleLicensePlate())) {

			jobs.replace(element.getvehicleLicensePlate(), mapToObject(element, unitOfWorkAction.CREATE));

			
		}
		else {
			System.out.println("CREATE elemnt key "+ element.getvehicleLicensePlate());
			jobs.put(element.getvehicleLicensePlate(), mapToObject(element, unitOfWorkAction.CREATE));
			
		}
		commit();
	}

	@Override
	public void update(Vehicle element) {
		 System.out.println("updatkey "+ element.getvehicleLicensePlate());
		boolean isDirty = isDirty(element.getvehicleLicensePlate());

		if(isDirty) {
			unitOfWork<Vehicle> value = jobs.get(element.getvehicleLicensePlate());
			
			 System.out.println("update elemnt in unit of work for "+ element.getvehicleLicensePlate() );
			
			if(value.getAction()==unitOfWorkAction.CREATE) {
			
				jobs.replace(element.getvehicleLicensePlate(), mapToObject(element, unitOfWorkAction.CREATE));		

			}
			
			
			else if(value.getAction()==unitOfWorkAction.UPDATE) {
				
				//jobs.replace(element.getvehicleLicensePlate(), mapToObject(element, unitOfWorkAction.UPDATE));	
				System.out.println("Vehicle is pending delete from system");	

			}
			
			else if(value.getAction()==unitOfWorkAction.DELETE) {
				
				System.out.println("Vehicle is pending delete from system");	

			}
			
		}
		
		else {
			jobs.put(element.getvehicleLicensePlate(), mapToObject(element, unitOfWorkAction.UPDATE));		

		}
				

		commit();
	}

	@Override
	public void delete(String key) {
		Vehicle element;
		try {
			if(!isDirty(key) && vehicleDataMapper.getVehicleByLicenseNo(key).getStatus().contains("Available") ) {
				 element = vehicleDataMapper.getVehicleByLicenseNo(key);
				jobs.put(key, mapToObject(element, unitOfWorkAction.DELETE));	
			}
else{
			
			System.out.println("Vehicle can not be modified, comit pending or vehicle is with customer");
}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		
		commit();
	}

	@Override
	public boolean isDirty(String key) {
		// TODO Auto-generated method stub
		
		if(jobs.containsKey(key)) {
			return true;
		}
		else {
			return false;
		}
	}
	

	private unitOfWork<Vehicle> mapToObject(Vehicle element, unitOfWorkAction action) {
		unitOfWork<Vehicle> object = new unitOfWork<Vehicle>(action,element);
		
		return object;

	}


}
