package View;

import Swing.Button;
import Swing.ButtonOutLine;
import Swing.ImageLogo;
import Swing.MyPasswordField;
import Swing.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public class LoginAndRegisterView extends JFrame {

    private JPanel login;
    private JPanel register;
    private Icon show, hide;
    private char pas;
    private ImageLogo logo,logo2; //Logo nhà hàng
    private JLabel titleName,titleName2;
    private JLabel storeName,storeName2;
    private JLabel description,des2;
    private ButtonOutLine btnToRegis,btnToLogin;
    private Button btnLogin, btnRegis;
    private JButton btnForget;
    private MyTextField userField, userNameRegis, phone, fullName;
    private MyPasswordField passwordRegis, passField;

    public LoginAndRegisterView() {
        super("JAVA_MVC");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(817, 500);
        setLocationRelativeTo(null);

        setResizable(false);
        setLayout(null);
        //
        hide = new ImageIcon(getClass().getResource("/Icon/hide.png"));
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
        register = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                Color startColor = Color.decode("#6C5B7B");
                Color endColor = Color.decode("#C06C84");
                GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, 0, panelHeight, endColor);
                g2.setPaint(gradientPaint);
                g2.fillRect(0, 0, panelWidth, panelHeight);
            }
        };
        register.setBounds(0, 500, 800, 500);
        register.setVisible(true);
        //
        addRegisterGui();
        addLoginGui();
        add(login);
        add(register);
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
        login.add(passField);
        //ForgetPass
        btnForget = new JButton("Quên mật khẩu của bạn ?");
        btnForget.setBounds(460, 300, 200, 15);
        btnForget.setForeground(new Color(245, 245, 245));
        btnForget.setFont(new Font("sansserif", 1, 15));
        btnForget.setContentAreaFilled(false);
        btnForget.setBorder(null);
        btnForget.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnForget.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnForget.setForeground(new Color(245, 245, 245));
            }
        });
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
        titleName.setBounds(59, 231, 207, 34);
        login.add(titleName);
        //LoginStoreName
        storeName = new JLabel("Sơn phế store", JLabel.CENTER);
        storeName.setFont(new Font("sansserif", 1, 18));
        storeName.setForeground(new Color(245, 245, 245));
        storeName.setBounds(100, 265, 125, 30);
        login.add(storeName);

        description = new JLabel("Tạo tài khoản và trải nghiệm", JLabel.CENTER);
        description.setForeground(new Color(245, 245, 245));
        description.setBounds(70, 309, 180, 40);
        login.add(description);
        //LoginMove
        btnToRegis = new ButtonOutLine();
        btnToRegis.setBackground(new Color(255, 255, 255));
        btnToRegis.setForeground(new Color(255, 255, 255));
        btnToRegis.setText("Đăng ký");
        btnToRegis.setBounds(107, 361, 110, 35);
        login.add(btnToRegis);
    }

    private void addRegisterGui() {
        register.setLayout(null);
        //registerLabel
        JLabel regisLabel = new JLabel("Tạo tài khoản", JLabel.CENTER);
        regisLabel.setBounds(435, 60, 250, 70);        
        regisLabel.setForeground(new Color(245, 245, 245));
        regisLabel.setFont(new Font("sansserif", 1, 30));
        register.add(regisLabel);
        //AccountFullName
        fullName = new MyTextField();
        fullName.setBounds(335, 170, 220, 40);
        fullName.setBackground(Color.white);
        fullName.setHint("Họ và tên ...");
        fullName.setPrefixIcon(new ImageIcon(getClass().
                getResource("/Icon/name.png")));
        register.add(fullName);
        //PhoneNumber
        phone = new MyTextField();
        phone.setBounds(565, 170, 220, 40);
        phone.setBackground(Color.white);
        phone.setHint("Số điện thoại ...");
        phone.setPrefixIcon(new ImageIcon(getClass().
                getResource("/Icon/phone.png")));
        //Check maxNumber 
        phone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String text = phone.getText();

                if (!Character.isDigit(c)) {
                    e.consume();
                    return;
                }
                if (text.length() >= 10) {
                    e.consume();
                }
            }
        });
        register.add(phone);
        //userName
        userNameRegis = new MyTextField();
        userNameRegis.setBounds(335, 230, 220, 40);
        userNameRegis.setBackground(Color.white);
        userNameRegis.setHint("Tên đăng nhập ...");
        userNameRegis.setPrefixIcon(new ImageIcon(getClass().
                getResource("/Icon/user.png")));
        register.add(userNameRegis);
        //pass
        passwordRegis = new MyPasswordField();
        passwordRegis.setBounds(565, 230, 220, 40);
        passwordRegis.setBackground(Color.white);
        passwordRegis.setHint("Mật khẩu ...");
        passwordRegis.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/pass.png")));
        passwordRegis.setSuffixIcon(show);
        pas = passwordRegis.getEchoChar();
        passwordRegis.addMouseListener(new MouseAdapter() {
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
        register.add(passwordRegis);
        //ButtobRegis
        btnRegis = new Button();
        btnRegis.setText("Đăng ký");
        btnRegis.setBounds(492, 322, 135, 35);
        register.add(btnRegis);
        //RegisLogo
        logo2 = new ImageLogo();
        logo2.setBounds(65, 27, 195, 190);
        logo2.setIcon(new ImageIcon(getClass().getResource("/Icon/toLogin.jpg")));
        register.add(logo2);
        //RegisTitle
        titleName2 = new JLabel("Trở thành một thành viên xuất sắc", JLabel.CENTER);
        titleName2.setFont(new Font("sansserif", 1, 12));
        titleName2.setForeground(new Color(245, 245, 245));
        titleName2.setBounds(59, 231, 207, 34);
        register.add(titleName2);
        //RegisStoreName
        storeName2 = new JLabel("Sơn phế store", JLabel.CENTER);
        storeName2.setFont(new Font("sansserif", 1, 18));
        storeName2.setForeground(new Color(245, 245, 245));
        storeName2.setBounds(100, 265, 125, 30);
        register.add(storeName2);
        //desRegis
        des2 = new JLabel("Bắt đầu công việc ngay!", JLabel.CENTER);
        des2.setForeground(new Color(245, 245, 245));
        des2.setBounds(70, 309, 180, 40);
        register.add(des2);
        //RegisMove
        btnToLogin = new ButtonOutLine();
        btnToLogin.setBackground(new Color(255, 255, 255));
        btnToLogin.setForeground(new Color(255, 255, 255));
        btnToLogin.setText("Đăng nhập");
        btnToLogin.setBounds(107, 361, 110, 35);
        register.add(btnToLogin);
    }
    public JPanel getLogin() {
        return login;
    }

    public JPanel getRegister() {
        return register;
    }

    public Icon getShow() {
        return show;
    }

    public ImageLogo getLogo() {
        return logo;
    }

    public ImageLogo getLogo2() {
        return logo2;
    }

    public JLabel getTitleName2() {
        return titleName2;
    }

    public JLabel getStoreName2() {
        return storeName2;
    }

    public JLabel getDes2() {
        return des2;
    }

    public ButtonOutLine getBtnToLogin() {
        return btnToLogin;
    }
 
    public ButtonOutLine getBtnToRegis() {
        return btnToRegis;
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

    public Button getBtnRegis() {
        return btnRegis;
    }

    public MyTextField getUserField() {
        return userField;
    }

    public MyTextField getUserNameRegis() {
        return userNameRegis;
    }

    public MyTextField getPhone() {
        return phone;
    }

    public MyTextField getFullName() {
        return fullName;
    }

    public MyPasswordField getPasswordRegis() {
        return passwordRegis;
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

    public void setFullName(MyTextField fullName) {
        this.fullName = fullName;
    }

}
