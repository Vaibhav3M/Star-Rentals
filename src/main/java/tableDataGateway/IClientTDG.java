package tableDataGateway;

import java.util.ArrayList;

import model.Client;

public interface IClientTDG {
	
	public boolean addNewClient(String licenseNumber, String firstName, String lastName, String phoneNo, String licenseExpiryDate) throws Exception;

	
	public boolean deleteClient(String licenseNumber) throws Exception;
	
	public boolean updateClient(String licenseNumber, String firstName, String lastName, String phoneNo, String licenseExpiryDate) throws Exception;
	
	public ArrayList<Client> getAllClients() throws Exception;
}
