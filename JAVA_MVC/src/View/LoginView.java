package View;

import Swing.Button;
import Swing.ImageLogo;
import Swing.MyPasswordField;
import Swing.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginView extends JFrame {

    private JPanel login;
    private char pas;
    private Icon show,hide;
    private ImageLogo logo;
    private JLabel titleName;
    private JLabel storeName;
    private JLabel description, des2;
    private Button btnLogin;
    private JButton btnForget;
    private MyTextField userField;
    private MyPasswordField passField;

    public LoginView() {
        super("JAVA_MVC");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(817, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        //
        show = new ImageIcon(getClass().getResource("/Icon/show.png"));
        //
        login = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                Color startColor = Color.decode("#C06C84");
                Color endColor = Color.decode("#6C5B7B");
                GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, 0, panelHeight, endColor);
                g2.setPaint(gradientPaint);
                g2.fillRect(0, 0, panelWidth, panelHeight);
            }
        };
        login.setBounds(0, 0, 800, 500);
        login.setVisible(true);
        //
        addLoginGui();
        add(login);
    }

    private void addLoginGui() {
        login.setLayout(null);
        JLabel loginLabel = new JLabel("ĐĂNG NHẬP", JLabel.CENTER);
        loginLabel.setBounds(435, 95, 250, 70);
        loginLabel.setForeground(new Color(245, 245, 245));
        loginLabel.setFont(new Font("sansserif", 1, 32));
        login.add(loginLabel);
        //userName
        userField = new MyTextField();
        userField.setBounds(410, 185, 300, 40);
        userField.setBackground(Color.white);
        userField.setHint("Tên đăng nhập ...");
        userField.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/user.png")));
        login.add(userField);
        //pass
        passField = new MyPasswordField();
        passField.setBounds(410, 235, 300, 40);
        passField.setBackground(Color.white);
        passField.setHint("Mật khẩu ...");
        passField.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/pass.png")));
        passField.setSuffixIcon(show);
        pas = passField.getEchoChar();
        hide = new ImageIcon(getClass().getResource("/Icon/hide.png"));
        show = new ImageIcon(getClass().getResource("/Icon/show.png"));
        passField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (passField.getSuffixIcon().equals(hide)) {
                    passField.setSuffixIcon(show);
                    passField.setEchoChar((char) 0);
                } else {
                    passField.setSuffixIcon(hide);
                    passField.setEchoChar(pas);
                }
            }
        });
        //
        login.add(passField);
        //ForgetPass
        btnForget = new JButton("Quên mật khẩu của bạn?");
        btnForget.setBounds(460, 300, 200, 15);
        btnForget.setForeground(new Color(245, 245, 245));
        btnForget.setFont(new Font("sansserif", 1, 15));
        btnForget.setContentAreaFilled(false);
        btnForget.setBorder(null);
        btnForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(btnForget);
        //LoginButton
        btnLogin = new Button();
        btnLogin.setText("Đăng nhập");
        btnLogin.setBounds(490, 335, 135, 35);
        login.add(btnLogin);
        //LoginLogo
        logo = new ImageLogo();
        logo.setBounds(65, 27, 195, 190);
        logo.setNeedSize(195, 190);
        logo.setIcon(new ImageIcon(getClass().getResource("/Icon/toRegister.png")));
        login.add(logo);
        //LoginTitle
        titleName = new JLabel("Chào mừng bạn đến với cửa hàng", JLabel.CENTER);
        titleName.setFont(new Font("sansserif", 1, 12));
        titleName.setForeground(new Color(245, 245, 245));
        titleName.setBounds(59, 220, 207, 34);
        login.add(titleName);
        //LoginStoreName
        storeName = new JLabel("Something store", JLabel.CENTER);
        storeName.setFont(new Font("sansserif", 1, 24));
        storeName.setForeground(new Color(245, 245, 245));
        storeName.setBounds(62, 250, 200, 30);
        login.add(storeName);
        ////Description
        description = new JLabel("Bắt đầu làm việc", JLabel.CENTER);
        description.setForeground(new Color(245, 245, 245));
        description.setFont(new Font("sansserif", 1, 20));
        description.setBounds(72, 280, 180, 40);
        login.add(description);
        //
        des2 = new JLabel("Cống hiến hết mình", JLabel.CENTER);
        des2.setForeground(new Color(245, 245, 245));
        des2.setFont(new Font("sansserif", 1, 18));
        des2.setBounds(72, 320, 180, 40);
        login.add(des2);

    }

    public JPanel getLogin() {
        return login;
    }


    public ImageLogo getLogo() {
        return logo;
    }

    public JLabel getDes2() {
        return des2;
    }

    public Button getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnForget() {
        return btnForget;
    }

    public JLabel getTitleName() {
        return titleName;
    }

    public JLabel getStoreName() {
        return storeName;
    }

    public JLabel getDescription() {
        return description;
    }

    public MyTextField getUserField() {
        return userField;
    }

    public MyPasswordField getPassField() {
        return passField;
    }

    public void setTitleName(JLabel titleName) {
        this.titleName = titleName;
    }

    public void setStoreName(JLabel storeName) {
        this.storeName = storeName;
    }

    public void setDescription(JLabel description) {
        this.description = description;
    }

}
