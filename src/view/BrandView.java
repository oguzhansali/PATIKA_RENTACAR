package view;

import javax.swing.*;

import entity.Brand;

public class BrandView extends Layout {
    private JPanel container;
    private JLabel lbl_brand;
    private JLabel lbl_brand_name;
    private JTextField fld_brand_name;
    private JButton btn_brand_save;
    private Brand brand;

    public BrandView(Brand brand){
        this.add(container);
        this.guiInitilaze(300, 200);
        this.brand=brand;
    }
}
