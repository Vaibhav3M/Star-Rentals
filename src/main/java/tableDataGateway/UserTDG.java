package tableDataGateway;

import model.User;

public class UserTDG implements IUserTDG {


    @Override
    public User getUser(String userType, String username, String password) throws Exception {
        return null;
    }

    @Override
    public boolean authUser(String userType, String username, String password) throws Exception {
        return false;
    }
}
