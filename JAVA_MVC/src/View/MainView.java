package View;

import Controller.Services.CustomerController;
import Controller.Services.MainController;
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

    private JPanel menuAdmin, menuStaff, changePanel;
    private ModelUser loginUser;
    private JLabel infoLb, roleLb, sInfoLb, sRoleLb, line, sLine;
    private Button btnInfo, btnExit, btnLogout, btnStaff,
            btnProduct, btnRevenue, btnAuthor, btnCate, btnOrder, btnCustomer, btnCreate;
    private Button sbtnInfo, sbtnExit, sbtnLogout, sbtnOrder, sbtnCustomer, sbtnCreate;

    private ImageLogo mainLogo, sMainLogo;

    public MainView(ModelUser newUser) throws SQLException {
        super("Cửa hàng sách");
        this.loginUser = newUser;
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
        //
        menuStaff = new JPanel() {
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
        menuStaff.setLayout(null);
        menuStaff.setBounds(0, 0, 300, 850);
        ////
        changePanel = new JPanel();
        changePanel.setLayout(null);
        changePanel.setBounds(310, 0, 800, 800);
        //Pa
        CustomerView cusView = new CustomerView();
        CustomerController cusSer = new CustomerController(cusView);
        changePanel.add(cusView);

        changePanel.setVisible(true);
        add(changePanel);
        //
//        addMainGui();
    }

    public static void main(String[] args) throws SQLException {
        MainView newMain = new MainView();
        MainController mainSer = new MainController(newMain);
        newMain.setVisible(true);
    }

    public MainView() throws SQLException {
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
        menuStaff = new JPanel() {
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
        menuStaff.setLayout(null);
        menuStaff.setBounds(0, 0, 300, 850);
        add(menuStaff);
        //changePanel
        changePanel = new JPanel();
        changePanel.setLayout(null);
        changePanel.setBounds(310, 0, 800, 800);
        ////
        CustomerView cusView = new CustomerView();
        CustomerController cusSer = new CustomerController(cusView);
        changePanel.add(cusView);
        changePanel.setVisible(true);
        add(changePanel);
        //
//        addMainGui();
//        addMenuStaff();
    }

    public void addMainGui() {
        //Label Role
        ImageIcon icon = new ImageIcon(getClass().getResource("/Icon/Admin.png"));
        roleLb = new JLabel("Quản lý cửa hàng", icon, JLabel.CENTER);
        roleLb.setFont(new Font("sansserif", 1, 24));
        roleLb.setForeground(new Color(255, 255, 255));
        roleLb.setBounds(10, 15, 280, 60);
        menuAdmin.add(roleLb);
        //line
        line = new JLabel();
        line.setBackground(Color.WHITE);
        line.setOpaque(true);
        line.setBounds(10, 100, 280, 2);
        menuAdmin.add(line);
        //Button Customer
        btnCustomer = new Button();
        btnCustomer.setFont(new Font("sansserif", 1, 15));
        btnCustomer.setText("Quản lý khách hàng");
        btnCustomer.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/customer.png")));
        btnCustomer.setBounds(10, 126, 280, 40);
        menuAdmin.add(btnCustomer);
        //Button Product
        btnProduct = new Button();
        btnProduct.setFont(new Font("sansserif", 1, 15));
        btnProduct.setText("Quản lý sản phẩm");
        btnProduct.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/product.png")));
        btnProduct.setBounds(10, 180, 280, 40);
        menuAdmin.add(btnProduct);
        //Button Revenue
        btnRevenue = new Button();
        btnRevenue.setFont(new Font("sansserif", 1, 15));
        btnRevenue.setText("Quản lý doanh thu");
        btnRevenue.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/revenue.png")));
        btnRevenue.setBounds(10, 234, 280, 40);
        menuAdmin.add(btnRevenue);
        //Button author
        btnAuthor = new Button();
        btnAuthor.setFont(new Font("sansserif", 1, 15));
        btnAuthor.setText("Quản lý nhà xuất bản");
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
        //Button Staff
        btnStaff = new Button();
        btnStaff.setFont(new Font("sansserif", 1, 15));
        btnStaff.setText("Quản lý nhân viên");
        btnStaff.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/staff.png")));
        btnStaff.setBounds(10, 450, 280, 40);
        menuAdmin.add(btnStaff);
        //Button Create
        btnCreate = new Button();
        btnCreate.setFont(new Font("sansserif", 1, 15));
        btnCreate.setText("Tạo đơn");
//        btnCreate.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/staff.png")));
        btnCreate.setBounds(50, 600, 200, 40);
        menuAdmin.add(btnCreate);

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

    public void addMenuStaff() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/Icon/Admin.png"));
        sRoleLb = new JLabel("Nhân viên cửa hàng", icon, JLabel.CENTER);
        sRoleLb.setFont(new Font("sansserif", 1, 22));
        sRoleLb.setForeground(new Color(255, 255, 255));
        sRoleLb.setBounds(10, 15, 280, 60);
        menuStaff.add(sRoleLb);
        //
        line = new JLabel();
        line.setBackground(Color.WHITE);
        line.setOpaque(true);
        line.setBounds(10, 100, 280, 2);
        menuStaff.add(line);
        //Button Customer
        sbtnCustomer = new Button();
        sbtnCustomer.setFont(new Font("sansserif", 1, 15));
        sbtnCustomer.setText("Quản lý khách hàng");
        sbtnCustomer.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/customer.png")));
        sbtnCustomer.setBounds(10, 126, 280, 40);
        menuStaff.add(sbtnCustomer);
        //Button Order
        sbtnOrder = new Button();
        sbtnOrder.setFont(new Font("sansserif", 1, 15));
        sbtnOrder.setText("Quản lý đơn hàng");
        sbtnOrder.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/order.png")));
        sbtnOrder.setBounds(10, 180, 280, 40);
        menuStaff.add(sbtnOrder);
        //Button Create
        sbtnCreate = new Button();
        sbtnCreate.setFont(new Font("sansserif", 1, 15));
        sbtnCreate.setText("Tạo đơn");
//        btnCreate.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/staff.png")));
        sbtnCreate.setBounds(50, 600, 200, 40);
        menuStaff.add(sbtnCreate);

        ///////////////////////////////
        //Label Info
        sInfoLb = new JLabel("Thông tin tài khoản", JLabel.CENTER);
        sInfoLb.setForeground(new Color(245, 245, 245));
        sInfoLb.setFont(new Font("sansserif", 1, 24));
        sInfoLb.setBounds(0, 664, 300, 40);
        menuStaff.add(sInfoLb);
        //Button Show info
        sbtnInfo = new Button();
        sbtnInfo.setBackground(new Color(255, 255, 255));
        sbtnInfo.setFont(new Font("sansserif", 1, 15));
        sbtnInfo.setContentAreaFilled(false);
        sbtnInfo.setBorder(null);
        sbtnInfo.setText("Tài khoản cá nhân");
        sbtnInfo.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/info.png")));
        sbtnInfo.setBounds(10, 712, 280, 40);
        menuStaff.add(sbtnInfo);
        //Button Logout
        sbtnLogout = new Button();
        sbtnLogout.setFont(new Font("sansserif", 1, 15));
        sbtnLogout.setText("Đăng xuất");
        sbtnLogout.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/logout.png")));
        sbtnLogout.setBounds(10, 760, 130, 40);
        menuStaff.add(sbtnLogout);
        //Button Exit
        sbtnExit = new Button();
        sbtnExit.setFont(new Font("sansserif", 1, 15));
        sbtnExit.setText("Thoát");
        sbtnExit.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/exit.png")));
        sbtnExit.setBounds(160, 760, 130, 40);
        menuStaff.add(sbtnExit);
    }

    public JPanel getMenuAdmin() {
        return menuAdmin;
    }

    public void setMenuAdmin(JPanel menuAdmin) {
        this.menuAdmin = menuAdmin;
    }

    public JPanel getMenuStaff() {
        return menuStaff;
    }

    public void setMenuStaff(JPanel menuNhanVien) {
        this.menuStaff = menuNhanVien;
    }

    public JPanel getChangePanel() {
        return changePanel;
    }

    public void setChangePanel(JPanel changePanel) {
        this.changePanel = changePanel;
    }

    public ModelUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(ModelUser loginUser) {
        this.loginUser = loginUser;
    }

    public Button getBtnInfo() {
        return btnInfo;
    }

    public void setBtnInfo(Button btnInfo) {
        this.btnInfo = btnInfo;
    }

    public Button getBtnExit() {
        return btnExit;
    }

    public void setBtnExit(Button btnExit) {
        this.btnExit = btnExit;
    }

    public Button getBtnLogout() {
        return btnLogout;
    }

    public void setBtnLogout(Button btnLogout) {
        this.btnLogout = btnLogout;
    }

    public Button getBtnStaff() {
        return btnStaff;
    }

    public void setBtnStaff(Button btnStaff) {
        this.btnStaff = btnStaff;
    }

    public Button getBtnProduct() {
        return btnProduct;
    }

    public void setBtnProduct(Button btnProduct) {
        this.btnProduct = btnProduct;
    }

    public Button getBtnRevenue() {
        return btnRevenue;
    }

    public void setBtnRevenue(Button btnRevenue) {
        this.btnRevenue = btnRevenue;
    }

    public Button getBtnAuthor() {
        return btnAuthor;
    }

    public void setBtnAuthor(Button btnAuthor) {
        this.btnAuthor = btnAuthor;
    }

    public Button getBtnCate() {
        return btnCate;
    }

    public void setBtnCate(Button btnCate) {
        this.btnCate = btnCate;
    }

    public Button getBtnOrder() {
        return btnOrder;
    }

    public void setBtnOrder(Button btnOrder) {
        this.btnOrder = btnOrder;
    }

    public Button getBtnCustomer() {
        return btnCustomer;
    }

    public void setBtnCustomer(Button btnCustomer) {
        this.btnCustomer = btnCustomer;
    }

    public ImageLogo getMainLogo() {
        return mainLogo;
    }

    public void setMainLogo(ImageLogo mainLogo) {
        this.mainLogo = mainLogo;
    }

    public Button getBtnCreate() {
        return btnCreate;
    }

    public void setBtnCreate(Button btnCreate) {
        this.btnCreate = btnCreate;
    }

    public Button getSbtnInfo() {
        return sbtnInfo;
    }

    public void setSbtnInfo(Button sbtnInfo) {
        this.sbtnInfo = sbtnInfo;
    }

    public Button getSbtnExit() {
        return sbtnExit;
    }

    public void setSbtnExit(Button sbtnExit) {
        this.sbtnExit = sbtnExit;
    }

    public Button getSbtnLogout() {
        return sbtnLogout;
    }

    public void setSbtnLogout(Button sbtnLogout) {
        this.sbtnLogout = sbtnLogout;
    }

    public Button getSbtnOrder() {
        return sbtnOrder;
    }

    public void setSbtnOrder(Button sbtnOrder) {
        this.sbtnOrder = sbtnOrder;
    }

    public Button getSbtnCustomer() {
        return sbtnCustomer;
    }

    public void setSbtnCustomer(Button sbtnCustomer) {
        this.sbtnCustomer = sbtnCustomer;
    }

    public Button getSbtnCreate() {
        return sbtnCreate;
    }

    public void setSbtnCreate(Button sbtnCreate) {
        this.sbtnCreate = sbtnCreate;
    }

}
