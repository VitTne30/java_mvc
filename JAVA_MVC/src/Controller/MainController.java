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

public class MainController {

    private DataConnection databaseConnection;
    private Connection con;
    private MainView mainView;
    private ModelUser inUseUser;
    private AccountView accView;
    private CustomerView cusView;
    private NxbView nxbView;
    private DanhMucView dmView;
    private NhanVienView nvView;
    private ThongKeView dtView;
    private ProductView productView;

    public MainController(MainView newView) throws SQLException {
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();
        this.mainView = newView;
        //Role
        inUseUser = mainView.getLoginUser();
        if ("Quản lý".equals(inUseUser.getRole())) {
            createAdminView();
            mainView.add(mainView.getMenuAdmin());
            mainView.addMainGui();
            adminService();
        } else if ("Nhân viên".equals(inUseUser.getRole())) {
            createStaffView();
            mainView.add(mainView.getMenuStaff());
            mainView.addMenuStaff();
            staffService();
        }
        ///////
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
        //Button Create
        mainView.getBtnCreate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.getChangePanel().removeAll();
                OrderView orderView = new OrderView(mainView);
                try {
                    OrderController orCon = new OrderController(orderView);
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                mainView.getChangePanel().add(orderView);
                mainView.getChangePanel().revalidate();
                mainView.getChangePanel().repaint();
                databaseConnection.releaseConnection(con);
            }
        });
    }
    ////////////////////////////////////
    private void adminService() {
        //ShowAccountInfo
        mainView.getBtnInfo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.getChangePanel().removeAll();
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
                mainView.getChangePanel().add(cusView);
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
                mainView.getChangePanel().add(dmView);
                mainView.getChangePanel().revalidate();
                mainView.getChangePanel().repaint();
                databaseConnection.releaseConnection(con);
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
                mainView.getChangePanel().add(accView);
                mainView.getChangePanel().revalidate();
                mainView.getChangePanel().repaint();
                databaseConnection.releaseConnection(con);
            }
        });
        //ShowCustemer
        mainView.getSbtnCustomer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.getChangePanel().removeAll();
                mainView.getChangePanel().add(cusView);
                mainView.getChangePanel().revalidate();
                mainView.getChangePanel().repaint();
                databaseConnection.releaseConnection(con);
            }
        });
    }

    private void createAdminView() throws SQLException {
        accView = new AccountView(mainView.getLoginUser());
        AccountController accSer = new AccountController(accView);
        //
        cusView = new CustomerView();
        CustomerController cusSer = new CustomerController(cusView);
        //
        nvView = new NhanVienView();
        NhanVienController nvCon = new NhanVienController(nvView);
        //
        productView = new ProductView();
        ProductController proSer = new ProductController(productView);
        //
        dtView = new ThongKeView();
        ThongKeController dtSer = new ThongKeController(dtView);
        //
        dmView = new DanhMucView();
        DanhMucController dmSer = new DanhMucController(dmView);
        //
        nxbView = new NxbView();
        NxbController nxbSer = new NxbController(nxbView);
        
    }

    private void createStaffView() throws SQLException {
        accView = new AccountView(mainView.getLoginUser());
        AccountController accSer = new AccountController(accView);
        //
        cusView = new CustomerView();
        CustomerController cusSer = new CustomerController(cusView);

    }
}
