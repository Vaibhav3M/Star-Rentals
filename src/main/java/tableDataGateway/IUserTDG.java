package tableDataGateway;

import model.User;

public interface IUserTDG {

    public User getUser(String userType, String username, String password)throws Exception;
    public boolean authUser(String userType, String username,String password) throws Exception;
}
