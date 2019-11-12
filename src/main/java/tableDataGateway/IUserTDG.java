package tableDataGateway;

import java.sql.SQLException;

import model.User;

public interface IUserTDG {

	/**
	 * @param userName
	 * @param userPass
	 * @return
	 */
	public boolean addNewUser(String userName, String userPass, String name, String userType) throws SQLException, Exception;
	
	
	/**
	 * @param userName
	 * @param userPass
	 * @return
	 */
	public boolean deleteUser(String userName, String userPass) throws SQLException, Exception;
	
	
	/**
	 * @param userName
	 * @param userPass
	 * @param name
	 * @param userType
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public boolean modifyUser(String userName, String userPass, String name, String userType) throws SQLException, Exception;
	
	
	/**
	 * @param userName
	 * @param userPass
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public User authenticateUser(String userName, String userPass) throws SQLException, Exception;
	
	
}
