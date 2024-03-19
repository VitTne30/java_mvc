package View;

import Controller.Services.ManageCustomerService;
import Model.ModelUser;
import Swing.Button;
import Swing.ImageLogo;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public class MainView extends JFrame {

    private JPanel menuAdmin,menuNhanVien,changePanel;
    private ModelUser loginUser;
    private JLabel infoLb, roleLb;
    private Button btnInfo, btnExit, btnLogout,btnStaff,
            btnProduct, btnRenevue,btnAuthor,btnCate,btnOrder,btnCustomer;
    private ImageLogo mainLogo;

    public MainView(ModelUser newUser) {
        super("Cửa hàng sách");
        setSize(1150, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        //
        this.loginUser = newUser;
        //
        menuAdmin = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                Color startColor = Color.decode("#F9E2AE");
                Color endColor = Color.decode("#A7D676");
                GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, 0, panelHeight, endColor);
                g2.setPaint(gradientPaint);
                g2.fillRect(0, 0, panelWidth, panelHeight);
            }
        };
        menuAdmin.setLayout(null);
        menuAdmin.setBounds(0, 0, 300, 800);
//        add(menuAdmin);
        //
        
        

    }

    public static void main(String[] args) throws SQLException {
        new MainView().setVisible(true);
    }

    private MainView() throws SQLException {
        super("Cửa hàng sách");
        setSize(1130, 850);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        //
        menuAdmin = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                Color startColor = Color.decode("#4286f4");
                Color endColor = Color.decode("#373B44");
                GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, 0, panelHeight, endColor);
                g2.setPaint(gradientPaint);
                g2.fillRect(0, 0, panelWidth, panelHeight);
            }
        };
        menuAdmin.setLayout(null);
        menuAdmin.setBounds(0, 0, 300, 850);
        add(menuAdmin);
        //
//        menuNhanVien = new JPanel(){
//        };
//        menuNhanVien.setLayout(null);
//        menuNhanVien.setBounds(0, 0, 300, 800);
//        menuNhanVien.setBackground(Color.red);
//        add(menuNhanVien);
        //changePanel
        changePanel = new JPanel();
        changePanel.setLayout(null);
        changePanel.setBounds(310, 0, 800, 800);
        ManageCustomerView mStaff = new ManageCustomerView();
        ManageCustomerService sStaff = new ManageCustomerService(mStaff);
        changePanel.add(mStaff);
        changePanel.setVisible(true);
        add(changePanel);
        //
        addMainGui();
    }

    private void addMainGui() {
        //MainLogo
//        mainLogo = new ImageLogo();
//        mainLogo.setBounds(10, 15, 72, 60);
//        mainLogo.setIcon(new ImageIcon(getClass().getResource("/Icon/Admin.png")));
//        menuPanel.add(mainLogo);
        //Label Role
        ImageIcon icon = new ImageIcon(getClass().getResource("/Icon/Admin.png"));
        roleLb = new JLabel("Quản lý cửa hàng",icon, JLabel.CENTER);
        roleLb.setFont(new Font("sansserif", 1, 24));
        roleLb.setForeground(new Color(255, 255, 255));
        roleLb.setBounds(10, 15, 280, 60);
        menuAdmin.add(roleLb);
        //Button Staff
        btnStaff = new Button();
        btnStaff.setFont(new Font("sansserif", 1, 15));
        btnStaff.setText("Quản lý nhân viên");
        btnStaff.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/staff.png")));
        btnStaff.setBounds(10, 126, 280, 40);
        menuAdmin.add(btnStaff);
        //Button Product
        btnProduct = new Button();
        btnProduct.setFont(new Font("sansserif", 1, 15));
        btnProduct.setText("Quản lý sản phẩm");
        btnProduct.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/product.png")));
        btnProduct.setBounds(10, 180, 280, 40);
        menuAdmin.add(btnProduct);
        //Button Revenue
        btnRenevue = new Button();
        btnRenevue.setFont(new Font("sansserif", 1, 15));
        btnRenevue.setText("Quản lý doanh thu");
        btnRenevue.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/revenue.png")));
        btnRenevue.setBounds(10, 234, 280, 40);
        menuAdmin.add(btnRenevue);
        //Button author
        btnAuthor = new Button();
        btnAuthor.setFont(new Font("sansserif", 1, 15));
        btnAuthor.setText("Quản lý tác giả");
        btnAuthor.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/author.png")));
        btnAuthor.setBounds(10, 288, 280, 40);
        menuAdmin.add(btnAuthor);
        //Button Category
        btnCate = new Button();
        btnCate.setFont(new Font("sansserif", 1, 15));
        btnCate.setText("Quản lý danh mục");
        btnCate.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/category.png")));
        btnCate.setBounds(10, 342, 280, 40);
        menuAdmin.add(btnCate);
        //Button Order
        btnOrder = new Button();
        btnOrder.setFont(new Font("sansserif", 1, 15));
        btnOrder.setText("Quản lý đơn hàng");
        btnOrder.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/order.png")));
        btnOrder.setBounds(10, 396, 280, 40);
        menuAdmin.add(btnOrder);
        //Button Customer
        btnCustomer = new Button();
        btnCustomer.setFont(new Font("sansserif", 1, 15));
        btnCustomer.setText("Quản lý khách hàng");
        btnCustomer.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/order.png")));
        btnCustomer.setBounds(10, 450, 280, 40);
        menuAdmin.add(btnCustomer);
        
        ///////////////////////////////
        //Label Info
        infoLb = new JLabel("Thông tin tài khoản", JLabel.CENTER);
        infoLb.setForeground(new Color(245, 245, 245));
        infoLb.setFont(new Font("sansserif", 1, 24));
        infoLb.setBounds(0, 664, 300, 40);
        menuAdmin.add(infoLb);
        //Button Show info
        btnInfo = new Button();
        btnInfo.setBackground(new Color(255, 255, 255));
        btnInfo.setFont(new Font("sansserif", 1, 15));
        btnInfo.setContentAreaFilled(false);
        btnInfo.setBorder(null);
        btnInfo.setText("Tài khoản cá nhân");
        btnInfo.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/info.png")));
        btnInfo.setBounds(10, 712, 280, 40);
        menuAdmin.add(btnInfo);
        //Button Logout
        btnLogout = new Button();
        btnLogout.setFont(new Font("sansserif", 1, 15));
        btnLogout.setText("Đăng xuất");
        btnLogout.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/logout.png")));
        btnLogout.setBounds(10, 760, 130, 40);
        menuAdmin.add(btnLogout);
        //Button Exit
        btnExit = new Button();
        btnExit.setFont(new Font("sansserif", 1, 15));
        btnExit.setText("Thoát");
        btnExit.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/exit.png")));
        btnExit.setBounds(160, 760, 130, 40);
        menuAdmin.add(btnExit);
        

    }

}
