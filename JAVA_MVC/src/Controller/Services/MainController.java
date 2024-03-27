package Controller.Services;

import Controller.DbConnection.DataConnection;
import View.AccountView;
import View.MainView;
import View.CustomerView;
import View.DanhMucView;
import View.DoanhThuView;
import View.DonHangView;
import View.LoginView;
import View.NxbView;
import View.ProductView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class MainController {

    private DataConnection databaseConnection;
    private Connection con;
    private MainView mainView;

    public MainController(MainView newView) throws SQLException {
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();
        this.mainView = newView;
        //ShowAccountInfo
        mainView.getBtnInfo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.getChangePanel().removeAll();
                AccountView accView = new AccountView(mainView.getLoginUser());
                try {
                    AccountController accSer = new AccountController(accView);
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                mainView.getChangePanel().add(accView);
                mainView.getChangePanel().revalidate();
                mainView.getChangePanel().repaint();
                databaseConnection.releaseConnection(con);
                System.out.println("111");

            }
        });
        //ShowCustemer
        mainView.getBtnCustomer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.getChangePanel().removeAll();
                CustomerView accView = new CustomerView();
                try {
                    CustomerController accSer = new CustomerController(accView);
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                mainView.getChangePanel().add(accView);
                mainView.getChangePanel().revalidate();
                mainView.getChangePanel().repaint();
                databaseConnection.releaseConnection(con);
            }
        });
        //ShowStaff
        mainView.getBtnStaff().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //ShowProducts
        mainView.getBtnProduct().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.getChangePanel().removeAll();
                ProductView productView = new ProductView();
                try {
                    ProductController proSer = new ProductController(productView);
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                mainView.getChangePanel().add(productView);
                mainView.getChangePanel().revalidate();
                mainView.getChangePanel().repaint();
                databaseConnection.releaseConnection(con);
            }
        });
        //ShowRevenue
        mainView.getBtnRevenue().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.getChangePanel().removeAll();
                DoanhThuView dtView = new DoanhThuView();
                try {
                    DoanhThuController dtSer = new DoanhThuController(dtView);
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                mainView.getChangePanel().add(dtView);
                mainView.getChangePanel().revalidate();
                mainView.getChangePanel().repaint();
                databaseConnection.releaseConnection(con);
            }
        });
        //ShowAuthor
        mainView.getBtnAuthor().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.getChangePanel().removeAll();
                NxbView nxbView = new NxbView();
                try {
                    NxbController nxbSer = new NxbController(nxbView);
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                mainView.getChangePanel().add(nxbView);
                mainView.getChangePanel().revalidate();
                mainView.getChangePanel().repaint();
                databaseConnection.releaseConnection(con);
            }
        });
        //ShowCategory
        mainView.getBtnCate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.getChangePanel().removeAll();
                DanhMucView dmView = new DanhMucView();
                try {
                    DanhMucController dmSer = new DanhMucController(dmView);
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                mainView.getChangePanel().add(dmView);
                mainView.getChangePanel().revalidate();
                mainView.getChangePanel().repaint();
                databaseConnection.releaseConnection(con);
            }
        });
        //ShowOrder
        mainView.getBtnOrder().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.getChangePanel().removeAll();
                DonHangView orderView = new DonHangView();
//                try {
//                    DanhMucController dmSer = new DanhMucController(orderView);
//                } catch (SQLException ex) {
//                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//                }
                mainView.getChangePanel().add(orderView);
                mainView.getChangePanel().revalidate();
                mainView.getChangePanel().repaint();
                databaseConnection.releaseConnection(con);
            }
        });
        //ButtonLogout
        mainView.getBtnLogout().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(mainView, "Bạn có chắc chắn muốn đăng xuất?",
                        "Thông báo", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    LoginView loginView = new LoginView();
                    try {
                        LoginController loginSer = new LoginController(loginView);
                    } catch (SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    loginView.setVisible(true);
                    mainView.dispose();
                }
            }
        });
        //ButtonExit
        mainView.getBtnExit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(mainView, "Bạn có chắc chắn muốn thoát chương trình?",
                        "Thông báo", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    mainView.dispose();
                }
            }
        });

    }

}
