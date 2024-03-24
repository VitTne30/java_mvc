package Controller.Services;

import Controller.DbConnection.DataConnection;
import View.AccountView;
import View.MainView;
import View.CustomerView;
import View.LoginAndRegisterView;
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
public class MainService {

    private DataConnection databaseConnection;
    private Connection con;
    private MainView mainView;

    public MainService(MainView newView) throws SQLException {
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
                    AccountService accSer = new AccountService(accView);
                } catch (SQLException ex) {
                    Logger.getLogger(MainService.class.getName()).log(Level.SEVERE, null, ex);
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
                    CustomerService accSer = new CustomerService(accView);
                } catch (SQLException ex) {
                    Logger.getLogger(MainService.class.getName()).log(Level.SEVERE, null, ex);
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
            
            }
        });
        //ShowRevenue
        mainView.getBtnRevenue().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        //ShowAuthor
        mainView.getBtnAuthor().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        //ShowCategory
        mainView.getBtnCate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        //ShowOrder
        mainView.getBtnOrder().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        //ButtonLogout
        mainView.getBtnLogout().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(mainView, "Bạn có chắc chắn muốn đăng xuất?"
                        , "Thông báo", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION){
                    LoginAndRegisterView loginView = new LoginAndRegisterView();
                    try {
                        LoginAndRegisterService loginSer = new LoginAndRegisterService(loginView);
                    } catch (SQLException ex) {
                        Logger.getLogger(MainService.class.getName()).log(Level.SEVERE, null, ex);
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
                int result = JOptionPane.showConfirmDialog(mainView, "Bạn có chắc chắn muốn thoát chương trình?"
                        , "Thông báo", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION){
                    mainView.dispose();
                }
            }
        });

    }

}
