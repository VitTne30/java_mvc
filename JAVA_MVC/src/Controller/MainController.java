package Controller;

import DbConnection.DataConnection;
import Model.ModelUser;
import View.AccountView;
import View.MainView;
import View.CustomerView;
import View.DanhMucView;
import View.ThongKeView;
import View.BillView;
import View.LoginView;
import View.NhanVienView;
import View.NxbView;
import View.OrderView;
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
    private ModelUser inUseUser;

    public MainController(MainView newView) throws SQLException {
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();
        this.mainView = newView;
        //Role
        inUseUser = mainView.getLoginUser();
        if ("Admin".equals(inUseUser.getRole())) {
//            mainView.getMenuAdmin().setVisible(true);
//            mainView.getMenuStaff().setVisible(false);
            mainView.add(mainView.getMenuAdmin());
            mainView.addMainGui();
            adminService();
        } else if ("Staff".equals(inUseUser.getRole())) {
//            mainView.getMenuStaff().setVisible(true);
//            mainView.getMenuAdmin().setVisible(false);
            mainView.add(mainView.getMenuStaff());
            mainView.addMenuStaff();
            staffService();
        }
    }

    ////////////////////////////////////
    private void adminService() {
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
                mainView.getChangePanel().removeAll();
                NhanVienView nvView = new NhanVienView();
                try {
                    NhanVienController nvCon = new NhanVienController(nvView);
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                mainView.getChangePanel().add(nvView);
                mainView.getChangePanel().revalidate();
                mainView.getChangePanel().repaint();
                databaseConnection.releaseConnection(con);
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
                ThongKeView dtView = new ThongKeView();
                try {
                    ThongKeController proSer = new ThongKeController(dtView);
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
        //////////////////
        //Button Create
        mainView.getBtnCreate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.getChangePanel().removeAll();
                OrderView orderView = new OrderView();
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

        //ShowBill
        mainView.getBtnOrder().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.getChangePanel().removeAll();
                BillView billView = new BillView();
                try {
                    BillController billSer = new BillController(billView);
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                mainView.getChangePanel().add(billView);
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
////////////////////////

    private void staffService() {
        //ShowAccountInfo
        mainView.getSbtnInfo().addActionListener(new ActionListener() {
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
        mainView.getSbtnCustomer().addActionListener(new ActionListener() {
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

        //////////////////
        //Button Create
        mainView.getSbtnCreate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.getChangePanel().removeAll();
                OrderView orderView = new OrderView();
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

        //ShowOrder
        mainView.getSbtnOrder().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.getChangePanel().removeAll();
                BillView billView = new BillView();
                try {
                    BillController billSer = new BillController(billView);
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                mainView.getChangePanel().add(billView);
                mainView.getChangePanel().revalidate();
                mainView.getChangePanel().repaint();
                databaseConnection.releaseConnection(con);
            }
        });
        //ButtonLogout
        mainView.getSbtnLogout().addActionListener(new ActionListener() {
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
        mainView.getSbtnExit().addActionListener(new ActionListener() {
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
