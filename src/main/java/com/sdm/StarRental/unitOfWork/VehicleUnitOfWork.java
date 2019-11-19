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
	
	@Autowired
	public VehicleUnitOfWork(VehicleDM vehicleDataMapper ) {
		
		this.vehicleDataMapper = vehicleDataMapper;
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
			Vehicle newElement = element;
			jobs.remove(element, jobs.get(element));
			jobs.put(newElement.getvehicleLicensePlate(), mapToObject(newElement, unitOfWorkAction.CREATE));
			
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
		Vehicle newElement;
		if(isDirty) {
			 System.out.println("update elemnt in unit of work for "+ element.getvehicleLicensePlate() );
			unitOfWork<Vehicle> value = jobs.get(element.getvehicleLicensePlate());
			if(value.getAction()==unitOfWorkAction.CREATE) {
				newElement = element;
				jobs.remove(element.getvehicleLicensePlate(), jobs.get(element.getvehicleLicensePlate()));
				jobs.put(newElement.getvehicleLicensePlate(), mapToObject(newElement, unitOfWorkAction.CREATE));		

			}
			else if(jobs.get(element.getvehicleLicensePlate()).getAction()==unitOfWorkAction.UPDATE) {
				newElement = element;
				jobs.remove(element.getvehicleLicensePlate(), jobs.get(element.getvehicleLicensePlate()));
				jobs.put(newElement.getvehicleLicensePlate(), mapToObject(newElement, unitOfWorkAction.UPDATE));		

			}
			
			else if(jobs.get(element.getvehicleLicensePlate()).getAction()==unitOfWorkAction.DELETE) {
				newElement = element;
				jobs.remove(element.getvehicleLicensePlate(), jobs.get(element.getvehicleLicensePlate()));
				jobs.put(newElement.getvehicleLicensePlate(), mapToObject(newElement, unitOfWorkAction.DELETE));		

			}
			
		//	System.out.println("update elemnt key "+ newElement.getVehicleLicencePlate()+ "SIZE IS "+ jobs.size());

		}
		
		else {
			jobs.put(element.getvehicleLicensePlate(), mapToObject(element, unitOfWorkAction.UPDATE));		

		}
		
		
		
		commit();
	}

	@Override
	public void delete(String key) {
		Vehicle newElement;
		if(isDirty(key)) {
			String newKey = key;
			newElement = jobs.get(key).getE();
			jobs.remove(key, jobs.get(key));
			
			jobs.put(newKey, mapToObject(newElement, unitOfWorkAction.DELETE));	
		}
	else{
		try {
			newElement = vehicleDataMapper.getVehicleByLicenseNo(key);
			jobs.put(key, mapToObject(newElement, unitOfWorkAction.DELETE));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
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
