import business.UserManager;
import core.Db;
import core.Helper;
import view.AdminView;
import view.LoginView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        Helper.setTheme();

        UserManager userManager=new UserManager();
       // AdminView loginView = new AdminView(userManager.findByLogin("admin","1234"));
        LoginView loginView=new LoginView();





    }
}
