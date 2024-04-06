package Controller;

import DbConnection.DataConnection;
import Model.ModelCustomer;
import View.AddCustomerView;
import View.OrderView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class AddCustomerController {

    private AddCustomerView AView;
    private final DataConnection db;
    private final Connection conn;
    private OrderView OView;
    private String name;
    private String state = "ko";
    private ArrayList<ModelCustomer> arrCus;
    private int id_order;

    public AddCustomerController(AddCustomerView one, OrderView two, int id) throws SQLException {
        this.AView = one;
        this.OView = two;
        this.id_order = id;
        db = DataConnection.getInstance();
        conn = (Connection) db.getConnection();
        this.AView.getAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkJTf()) {
                    name = AView.getJtfName().getText();
                    String email = AView.getJtfEmail().getText();
                    String sdt = AView.getJtfSdt().getText();
                    try {
                        String sql = "INSERT INTO `tbl_khachhang`(`id_cus`, `hoten`, `sdt`, `email`)"
                                + " VALUES ('" + getId() + "','" + name + "','" + sdt + "','" + email + "');";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        int affectedRow = stmt.executeUpdate();
                        if (affectedRow > 0) {
                            JOptionPane.showMessageDialog(AView, "Thêm khách hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            stmt.close();
                            db.releaseConnection(conn);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
        AView.getCancel().addActionListener((ActionEvent e) -> {
            if (AView.getAdd().getModel().isPressed()) {
                OView.getJtfCustomer().setText(name);
                try {
                    getAllCustomer();
                    updateCus(arrCus.size() - 1);
                } catch (SQLException ex) {
                    Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }

                OView.getAddNewCustomer().setText("Thêm");
            }
            AView.dispose();
        });
    }

    private void updateCus(int index) throws SQLException {
        String sql = "UPDATE `tbl_donhang` SET `id_cus`='" + arrCus.get(index).getId() + "' WHERE id_donhang = '" + id_order + "';";
        PreparedStatement stmt = conn.prepareStatement(sql);
        int affectedRow = stmt.executeUpdate();
        if (affectedRow > 0) {
            JOptionPane.showMessageDialog(OView, "Cập nhập đơn hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(OView, "Cập nhập đơn hàng không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        stmt.close();
        db.releaseConnection(conn);
    }

    private boolean validateEmail(String email) {
        String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        Matcher matcher;
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void showMessengerError(String error) {
        JOptionPane.showMessageDialog(AView,
                error, "Thông báo",
                JOptionPane.ERROR_MESSAGE);
    }

    public String getState() {
        return state;
    }

    private int getId() throws SQLException {
        int id = 1;
        ArrayList<Integer> arr = new ArrayList<>();
        String sql = "SELECT id_cus FROM tbl_khachhang";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            arr.add(result.getInt("id"));

        }
        if (!arr.isEmpty()) {
            id = arr.get(arr.size() - 1) + 1;
        }
        stmt.close();
        result.close();
        db.releaseConnection(conn);
        return id;
    }

    private void getAllCustomer() throws SQLException {
        arrCus = new ArrayList<>();
        String sql = "SELECT id_cus, hoten FROM tbl_khachhang;";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            ModelCustomer temp = new ModelCustomer(result.getInt("id_cus"),
                    result.getString("hoten"));
            arrCus.add(temp);
        }
        result.close();
        stmt.close();
        db.releaseConnection(conn);
    }

    private boolean validateNumberPhone(String sdt) {
        String phonePattern = "^0\\d{9}$";
        Pattern pattern = Pattern.compile(phonePattern);
        Matcher matcher = pattern.matcher(sdt);
        return matcher.matches();

    }

    private boolean checkJTf() {
        if (AView.getJtfName().getText().equals("")) {
            showMessengerError("Vui lòng nhập họ và tên!");
            return false;
        } else if (AView.getJtfEmail().getText().equals("")) {
            showMessengerError("Vui lòng nhập email!");
            return false;
        } else if (AView.getJtfSdt().getText().equals("")) {
            showMessengerError("Vui lòng nhập số điện thoại!");
            return false;
        } else if (!validateEmail(AView.getJtfEmail().getText())) {
            showMessengerError("Vui lòng nhập đúng định dạng email: abc@gmail.com...");
            AView.getJtfEmail().setText("");
            return false;
        } else if (!validateNumberPhone(AView.getJtfSdt().getText())) {
            showMessengerError("Vui lòng nhập đúng định dạng SDT: 0123456xxx");
            AView.getJtfSdt().setText("");
            return false;
        }
        return true;
    }

}
