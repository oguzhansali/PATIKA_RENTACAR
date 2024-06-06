package view;

import business.BrandManager;
import entity.Brand;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminView extends Layout {
    private JPanel container;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JTabbedPane tab_menu;
    private JButton btn_logout;
    private JPanel pnl_brand;
    private JTable tbl_brand;
    private JScrollPane scl_brand;
    private User user;
    private DefaultTableModel tmdl_brand = new DefaultTableModel();
    private BrandManager brandManager;
    private JPopupMenu brandMenu;

    public AdminView(User user) {
        this.brandManager = new BrandManager();
        this.add(container);
        this.guiInitilaze(1000, 500);
        this.user = user;

        if (this.user == null) {
            dispose();
        }
        this.lbl_welcome.setText("Hoşgeldiniz : " + this.user.getUsername());

        loadBrandTable();
        this.tbl_brand.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                int selected_row = tbl_brand.rowAtPoint(e.getPoint());
                tbl_brand.setRowSelectionInterval(selected_row,selected_row);
            }
        });


        this.brandMenu = new JPopupMenu();
        this.brandMenu.add("Yeni").addActionListener(e -> {
            BrandView brandView = new BrandView(null);
            brandView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadBrandTable();
                }
            });
        });
        this.brandMenu.add("Güncelle").addActionListener(e -> {
            int selectBrandId= Integer.parseInt(tbl_brand.getValueAt(tbl_brand.getSelectedRow(),0).toString());
            BrandView brandView = new BrandView(this.brandManager.getById(selectBrandId));
            brandView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadBrandTable();
                }
            });
        });
        this.brandMenu.add("Sil");
        /*
        //Sağ tıklama sorununu bu şekilde çözdüm!!!
        this.tbl_brand.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }

            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    brandMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });*/

        this.tbl_brand.setComponentPopupMenu(brandMenu);
    }

    public void loadBrandTable(){
        Object[] col_brand = {"Marka ID", "Marka Adı"};
        ArrayList<Brand> brandList = this.brandManager.findAll();
        this.tmdl_brand.setColumnIdentifiers(col_brand);


        this.tbl_brand.setModel(this.tmdl_brand);
        this.tbl_brand.getTableHeader().setReorderingAllowed(false);
        this.tbl_brand.setEnabled(false);//Verilerin değişmesini false yapar.

        DefaultTableModel clearModel = (DefaultTableModel) tbl_brand.getModel();
        clearModel.setRowCount(0);
        for (Brand brand : brandList) {
            Object[] obj = {brand.getId(), brand.getName()};
            this.tmdl_brand.addRow(obj);
        }


    }


}

