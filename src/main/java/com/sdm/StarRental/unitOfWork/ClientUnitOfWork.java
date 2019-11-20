package com.sdm.StarRental.unitOfWork;




import com.sdm.StarRental.Enum.unitOfWorkAction;
import com.sdm.StarRental.dataMapper.ClientDM;
import com.sdm.StarRental.model.Client;
import com.sdm.StarRental.model.unitOfWork;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;

public class ClientUnitOfWork implements IUnitOfWork<Client,String> {

	public ClientUnitOfWork(ClientDM clientDM) { this.clientDM = clientDM; }

	//TODO: assign unique values
	@Value("{unit-of-work.batch-no}")
	private String unitOfWorkBatchNo;

	private HashMap<String, unitOfWork<Client>> data = new HashMap();

	private ClientDM clientDM;


	@Override
	public void create(Client element) {
		data.put(element.getLicenseNumber(), mapToObject(element, unitOfWorkAction.CREATE));
		commit();
	}

	@Override
	public void update(Client element) {
		if(data.containsKey(element.getLicenseNumber())){
			data.put(element.getLicenseNumber(), mapToObject(element, unitOfWorkAction.UPDATE));
		}
		commit();
	}

	@Override
	public void delete(String key) {
		if(data.containsKey(key)){
			Client client = new Client();
			client.setLicenseNumber(key);
			data.put(key, mapToObject(client, unitOfWorkAction.DELETE));
		}
		commit();
	}

	@Override
	public boolean isDirty(String key) {
		return data.containsKey(key);
	}


	@Override
	public void commit() {

		if (data.size() == 5) {
			data.forEach((key, element) -> {
				Client client = element.getE();
				if (element.getAction() == unitOfWorkAction.CREATE) {
					commitCreateClient(client);
				} else if(element.getAction() == unitOfWorkAction.UPDATE) {
					commitUpdateClient(client);
				} else if(element.getAction() == unitOfWorkAction.DELETE) {
					commitDeleteClient(client);
				}
			});
			data = new HashMap<String, unitOfWork<Client>>();
		}

	}


	private void commitCreateClient(Client client) {
		try {
			clientDM.createClientService(client.getFirstName(),client.getLastName(),client.getPhoneNumber(),client.getLicenseNumber(),client.getLicenseExpiryDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void commitUpdateClient(Client client) {
		try {
			clientDM.modifyClientService(client.getFirstName(),client.getLastName(),client.getPhoneNumber(),client.getLicenseNumber(),client.getLicenseExpiryDate());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void commitDeleteClient(Client client) {
		try {
			clientDM.deleteClientService(client.getLicenseNumber());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private unitOfWork<Client> mapToObject(Client element, unitOfWorkAction action) {
		unitOfWork<Client> object = new unitOfWork<Client>(action, element);
		return object;

	}

}
