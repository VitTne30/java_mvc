package Controller;

import DbConnection.DataConnection;
import Model.ModelCustomer;
import View.CustomerView;
import View.InsertModifyView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class InsertModifyController {

    private DataConnection databaseConnection;
    private Connection con;
    private InsertModifyView idmView;
    private CustomerView cusView;
    private boolean check;
    private int CusId;

    public InsertModifyController(InsertModifyView newIdm, CustomerView newCus) throws SQLException {
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();
        this.idmView = newIdm;
        this.cusView = newCus;
        check = false;
        //
        idmView.getTxtId().setText(String.valueOf(getMaxId()));
        //
        ////insert component
        idmView.getBtnConfirm().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertComponent();
                if (check) {
                    CustomerView newcus = new CustomerView();
                    try {
                        CustomerController newSer = new CustomerController(newcus);
                    } catch (SQLException ex) {
                        Logger.getLogger(InsertModifyController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    cusView.removeAll();
                    cusView.add(newcus);
                    cusView.revalidate();
                    cusView.repaint();
                    databaseConnection.releaseConnection(con);

                }
            }
        });
        ////Cancel Component
        idmView.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerView newcus = new CustomerView();
                try {
                    CustomerController newSer = new CustomerController(newcus);
                } catch (SQLException ex) {
                    Logger.getLogger(InsertModifyController.class.getName()).log(Level.SEVERE, null, ex);
                }
                cusView.removeAll();
                cusView.add(newcus);
                cusView.revalidate();
                cusView.repaint();
                databaseConnection.releaseConnection(con);
            }
        });
    }
    /////////////////////////////////
    public InsertModifyController(InsertModifyView newIdm, CustomerView newCusView,int cusId ) throws SQLException{
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();
        this.idmView = newIdm;
        this.cusView = newCusView;
        this.CusId =cusId;
        check = false;
        ModelCustomer fillCus = getCustomer(CusId);
        
        //
        idmView.getTxtId().setText(String.valueOf(CusId));
        idmView.getTitleLb().setText("Sửa thông tin khách hàng");
        idmView.getTxtName().setText(fillCus.getName());
        idmView.getTxtPhone().setText(fillCus.getPhone());
        idmView.getTxtEmail().setText(fillCus.getEmail());
        //
        ////modify component
        idmView.getBtnConfirm().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyComponent();
                if (check) {
                    CustomerView newcus = new CustomerView();
                    try {
                        CustomerController newSer = new CustomerController(newcus);
                    } catch (SQLException ex) {
                        Logger.getLogger(InsertModifyController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    cusView.removeAll();
                    cusView.add(newcus);
                    cusView.revalidate();
                    cusView.repaint();
                    databaseConnection.releaseConnection(con);

                }
            }
        });
        ////Cancel Component
        idmView.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerView newcus = new CustomerView();
                try {
                    CustomerController newSer = new CustomerController(newcus);
                } catch (SQLException ex) {
                    Logger.getLogger(InsertModifyController.class.getName()).log(Level.SEVERE, null, ex);
                }
                cusView.removeAll();
                cusView.add(newcus);
                cusView.revalidate();
                cusView.repaint();
                databaseConnection.releaseConnection(con);
            }
        });
    }

    private void insertComponent() {
        String email = idmView.getTxtEmail().getText();
        if (!idmView.getTxtName().getText().isEmpty()
                || !idmView.getTxtPhone().getText().isEmpty()
                || !idmView.getTxtEmail().getText().isEmpty()) {
            if (idmView.getTxtPhone().getText().length() == 10) {
                if (checkEmail(email)) {
                    try {
                        //Insert customer
                        String sql_ND = "INSERT INTO tbl_khachhang (id_cus,hoten, sdt,email) VALUES (?,?,?,?)";
                        PreparedStatement p = con.prepareStatement(sql_ND);
                        p.setInt(1, getMaxId());
                        p.setString(2, idmView.getTxtName().getText().trim());
                        p.setString(3, idmView.getTxtPhone().getText());
                        p.setString(4, idmView.getTxtEmail().getText());
                        p.execute();

                        JOptionPane.showMessageDialog(idmView, "Thêm khách hàng thành công!",
                                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        check = true;
                        p.close();
                        databaseConnection.releaseConnection(con);
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(idmView, "Email không hợp lệ!",
                            "Thông báo", JOptionPane.ERROR_MESSAGE);
                    idmView.getTxtEmail().requestFocus();
                }

            } else {
                JOptionPane.showMessageDialog(idmView, "Số điện thoại không hợp lệ!",
                        "Thông báo", JOptionPane.ERROR_MESSAGE);
                idmView.getTxtPhone().requestFocus();
            }

        } else {
            JOptionPane.showMessageDialog(idmView, "Vui lòng điền đầy đủ thông tin!",
                    "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    //////////////////////
    private void modifyComponent() {
        String email = idmView.getTxtEmail().getText();
        if (!idmView.getTxtName().getText().isEmpty()
                || !idmView.getTxtPhone().getText().isEmpty()
                || !idmView.getTxtEmail().getText().isEmpty()) {
            if (idmView.getTxtPhone().getText().length() == 10) {
                if (checkEmail(email)) {
                    try {
                        //Modify customer
                        String sql_ND = "UPDATE tbl_khachhang SET hoten=?,sdt=?,email=? WHERE id_cus=?";
                        PreparedStatement p = con.prepareStatement(sql_ND);
                        p.setString(1, idmView.getTxtName().getText().trim());
                        p.setString(2, idmView.getTxtPhone().getText());
                        p.setString(3, idmView.getTxtEmail().getText());
                        p.setInt(4, CusId);
                        p.execute();
                        JOptionPane.showMessageDialog(idmView, "Cập nhât thông tin khách hàng thành công!",
                                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        check = true;
                        p.close();
                        databaseConnection.releaseConnection(con);
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(idmView, "Email không hợp lệ!",
                            "Thông báo", JOptionPane.ERROR_MESSAGE);
                    idmView.getTxtEmail().requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(idmView, "Số điện thoại không hợp lệ!",
                        "Thông báo", JOptionPane.ERROR_MESSAGE);
                idmView.getTxtPhone().requestFocus();
            }

        } else {
            JOptionPane.showMessageDialog(idmView, "Vui lòng điền đầy đủ thông tin!",
                    "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getMaxId() throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT MAX(id_cus) as id FROM tbl_khachhang");
        ResultSet r = p.executeQuery();
        r.next();
        int id = r.getInt("id") + 1;
        r.close();
        p.close();
        return id;
    }
    private ModelCustomer getCustomer(int id) throws SQLException {
         ModelCustomer customer = null;
        PreparedStatement p = con.prepareStatement("SELECT hoten,sdt,email FROM tbl_khachhang WHERE id_cus = ?");
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        while(r.next()){
            String name = r.getString(1);
            String phone = r.getString(2);
            String emString = r.getString(3);
            customer = new ModelCustomer(id, name, phone, emString);
        }
        r.close();
        p.close();
        return customer;
    }
    

    private boolean checkEmail(String s) {
        final String EMAIL_PATTERN
                = "^[_A-Za-z0-9]+(\\.[_A-Za-z0-9]+)*@gmail\\.com$";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
