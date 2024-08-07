package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends Layout {
    private JPanel container;
    private JPanel w_top;
    private JLabel lbl_welome;
    private JPanel w_buttom;
    private JLabel lbl_welcome2;
    private JTextField fld_username;
    private JPasswordField fld_pass;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private JButton btn_login;
    private final UserManager userManager;


    public LoginView(){
        this.userManager=new UserManager();
        this.add(container);
        this.guiInitilaze(400,400);

        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_username,this.fld_pass};
            if (Helper.isFieldListEmpty(checkFieldList)){
                Helper.showMsg("fill");
            }else {
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(),this.fld_pass.getText());
                if (loginUser==null){
                    Helper.showMsg("notFound");
                }else {
                   // if loginUser.getRole()=="admin"
                    AdminView adminView=new AdminView(loginUser);
                   // else
                      // "yeni oluştutdugun"
                    dispose();
                }
            }

        });
    }




}
