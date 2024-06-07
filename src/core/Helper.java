package core;

import javax.swing.*;
import java.awt.*;

public class Helper {
    private JTextField fld_model_year;

    public static void setTheme() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;

            }
        }
    }

    public static void showMsg(String str) {
        optionPaneTR();
        String msg;
        String title;
        switch (str) {
            case "fill":
                msg = "Lütfen tüm alanları doldurunuz!";
                title = "HATA!";
                break;
            case "done":
                msg = "İşlem Başarılı";
                title = "Sonuç";
                break;
            case "notFound":
                msg=" Kayıt Bulunamadı!";
                title="Bulunamadı.";
                break;
            case "error":
                msg="Hatalı işlem yaptınız.";
                title="Hata!";
            default:
                msg = str;
                title = "Mesaj";
                break;

        }

        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);

    }
    public static  boolean cofirm(String str){
        optionPaneTR();
        String msg;
        if (str.equals("sure")){
            msg="Bu işlemi yapmak istediğinine emin misin?";
        }else {
            msg=str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Emin misin ?",JOptionPane.YES_NO_OPTION)==0;
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }


    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) return true;

        }
        return false;
    }

    public static int getLocationPoint(String type, Dimension size){
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }

    public static void optionPaneTR(){
        UIManager.put("OptionPane.okButtonText","Tamam");
        UIManager.put("OptionPane.yesButtonText","Evet");
        UIManager.put("OptionPane.noButtonText","Hayır");
    }
}
