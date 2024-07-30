package business;

import dao.UserDao;
import entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserManager {
    private final UserDao userDao;

    public UserManager(){
        this.userDao=new UserDao();
    }

    public User findByLogin(String username, String password){
        //farklı işlemler yapılabilir.
        return this.userDao.findByLogin(username,password);
    }
}
