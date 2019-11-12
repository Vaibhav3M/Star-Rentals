package tableDataGateway;

import java.util.ArrayList;
import model.Client;


public interface IClientTDG {

    public boolean createClient(String firstName, String lastName, String phoneNumber,String licenseNumber, String licenseExpiryDate) throws Exception ;

    public boolean deleteClient(String licenseNumber) throws Exception;

    public boolean modifyClient(String firstName, String lastName, String phoneNumber,String licenseNumber, String licenseExpiryDate) throws Exception;

    public ArrayList<Client> getClientDetailsOneParam(String key, String value) throws Exception;

    public ArrayList<Client> getClientDetailsTwoParam(String key1,String value1, String key2, String value2) throws Exception;

    public ArrayList<Client> getClientDetailsThreeParam(String value1,String value2, String value3) throws Exception;

    public ArrayList<Client> getAllClients() throws Exception;

}
