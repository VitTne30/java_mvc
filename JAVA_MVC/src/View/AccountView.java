package View;

import Model.ModelUser;
import Swing.Button;
import Swing.ImageLogo;
import Swing.MyTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AccountView extends JPanel {

    private JLabel titleLb, titleAccLb, line,line2, lb1, lb2,
            lb4, lb5, lb6,lb7,lb8,lb9;
    private MyTextField idLb, nameLb,
            addreddLb, phoneLb, emailLb,usNameLb,pass,role;
    private ImageLogo accLogo;
    private Button btnChangepass;
    private ModelUser user;

    public AccountView(ModelUser inUseUser) {
        setLayout(null);
        setBounds(0, 0, 800, 800);
        setBackground(Color.WHITE);
        //
        this.user = inUseUser;
        //
        addAccountGUI();
        setVisible(true);
    }
    public AccountView() {
        setLayout(null);
        setBounds(0, 0, 800, 800);
        setBackground(Color.WHITE);
        //
        //
        addAccountGUI();
        setVisible(true);
    }

    private void addAccountGUI() {
        //Label title
        titleLb = new JLabel("Thông tin người dùng:");
        titleLb.setFont(new Font("sansserif", 1, 30));
        titleLb.setBounds(20, 25, 360, 90);
        add(titleLb);
        //Black line
        line = new JLabel();
        line.setBackground(Color.BLACK);
        line.setOpaque(true);
        line.setBounds(20, 115, 750, 2);
        add(line);
        //AccountLogo
        accLogo = new ImageLogo();
        accLogo.setBounds(20, 175, 360, 350);
        accLogo.setNeedSize(360, 350);
        accLogo.setIcon(new ImageIcon(getClass().getResource("/Icon/manager.png")));
        add(accLogo);
        //Label id
        lb1 = new JLabel("Id:");
        lb1.setFont(new Font("sansserif", 1, 18));
        lb1.setBounds(415, 206, 130, 40);
        add(lb1);
        //Label name
        lb2 = new JLabel("Họ và tên:");
        lb2.setFont(new Font("sansserif", 1, 18));
        lb2.setBounds(415, 268, 130, 40);
        add(lb2);
        //Label Address
        lb4 = new JLabel("Địa chỉ:");
        lb4.setFont(new Font("sansserif", 1, 18));
        lb4.setBounds(415, 330, 130, 40);
        add(lb4);
        //Label Phone
        lb5 = new JLabel("Số điện thoại:");
        lb5.setFont(new Font("sansserif", 1, 18));
        lb5.setBounds(415,392, 130, 40);
        add(lb5);
        //Label Email
        lb6 = new JLabel("Email:");
        lb6.setFont(new Font("sansserif", 1, 18));
        lb6.setBounds(415, 454, 130, 40);
        add(lb6);
        ////////////////Data
        //id
        idLb = new MyTextField();
        idLb.setHorizontalAlignment(JTextField.CENTER);
        idLb.setEditable(false);
        idLb.setFont(new Font("sansserif", 1, 18));
        idLb.setBounds(555, 206, 230, 40);
        add(idLb);
        //name
        nameLb = new MyTextField();
        nameLb.setHorizontalAlignment(JTextField.CENTER);
        nameLb.setEditable(false);
        nameLb.setFont(new Font("sansserif", 1, 18));
        nameLb.setBounds(555, 268, 230, 40);
        add(nameLb);
        //address
        addreddLb = new MyTextField();
        addreddLb.setEditable(false);
        addreddLb.setHorizontalAlignment(JTextField.CENTER);
        addreddLb.setFont(new Font("sansserif", 1, 18));
        addreddLb.setBounds(555, 330, 230, 40);
        add(addreddLb);
        //phone
        phoneLb = new MyTextField();
        phoneLb.setEditable(false);
        phoneLb.setHorizontalAlignment(JTextField.CENTER);
        phoneLb.setFont(new Font("sansserif", 1, 18));
        phoneLb.setBounds(555, 392, 230, 40);
        add(phoneLb);
        //Email
        emailLb = new MyTextField();
        emailLb.setEditable(false);
        emailLb.setHorizontalAlignment(JTextField.CENTER);
        emailLb.setFont(new Font("sansserif", 1, 13));
        emailLb.setBounds(555, 454, 230, 40);
        add(emailLb);
        //Black line
        line2 = new JLabel();
        line2.setBackground(Color.BLACK);
        line2.setOpaque(true);
        line2.setBounds(20, 555, 750, 2);
        add(line2);
        ///////////////////////
        //title Account
        titleAccLb = new JLabel("Thông tin tài khoản:");
        titleAccLb.setFont(new Font("sansserif", 1, 30));
        titleAccLb.setBounds(20, 555, 360, 90);
        add(titleAccLb);
        //Username Label
        lb7 = new JLabel("Tên tài khoản:");
        lb7.setFont(new Font("sansserif", 1, 18));
        lb7.setBounds(20, 650, 130, 40);
        add(lb7);
        //Role Label
        lb8 = new JLabel("Vai trò:");
        lb8.setFont(new Font("sansserif", 1, 18));
        lb8.setBounds(20, 700, 130, 40);
        add(lb8);
        //PasswordLabel
        lb9 = new JLabel("Mật khẩu:");
        lb9.setFont(new Font("sansserif", 1, 18));
        lb9.setBounds(430, 650, 130, 40);
        add(lb9);
        ///////////////data
        //Username
        usNameLb = new MyTextField();
        usNameLb.setEditable(false);
        usNameLb.setHorizontalAlignment(JTextField.CENTER);
        usNameLb.setFont(new Font("sansserif", 1, 18));
        usNameLb.setBounds(170, 650, 150, 40);
        add(usNameLb);
        //Role
        role = new MyTextField();
        role.setEditable(false);
        role.setHorizontalAlignment(JTextField.CENTER);
        role.setFont(new Font("sansserif", 1, 18));
        role.setBounds(170, 700, 150, 40);
        add(role);
        //Password
        pass = new MyTextField();
        pass.setEditable(false);
        pass.setHorizontalAlignment(JTextField.CENTER);
        pass.setFont(new Font("sansserif", 1, 18));
        pass.setBounds(570, 650, 150, 40);
        add(pass);
        //Button Changepass
        btnChangepass = new Button();
        btnChangepass.setBackground(new Color(215,221,232));
        btnChangepass.setText("Đổi mật khẩu");
        btnChangepass.setFont(new Font("sansserif",1,30));
        btnChangepass.setBounds(445, 700, 280, 40);
        add(btnChangepass);
    }

    public MyTextField getIdLb() {
        return idLb;
    }

    public void setIdLb(MyTextField idLb) {
        this.idLb = idLb;
    }

    public MyTextField getNameLb() {
        return nameLb;
    }

    public void setNameLb(MyTextField nameLb) {
        this.nameLb = nameLb;
    }

    public MyTextField getAddreddLb() {
        return addreddLb;
    }

    public void setAddreddLb(MyTextField addreddLb) {
        this.addreddLb = addreddLb;
    }

    public MyTextField getPhoneLb() {
        return phoneLb;
    }

    public void setPhoneLb(MyTextField phoneLb) {
        this.phoneLb = phoneLb;
    }

    public MyTextField getEmailLb() {
        return emailLb;
    }

    public void setEmailLb(MyTextField emailLb) {
        this.emailLb = emailLb;
    }

    public MyTextField getUsNameLb() {
        return usNameLb;
    }

    public void setUsNameLb(MyTextField usNameLb) {
        this.usNameLb = usNameLb;
    }

    public MyTextField getPass() {
        return pass;
    }

    public void setPass(MyTextField pass) {
        this.pass = pass;
    }

    public MyTextField getRole() {
        return role;
    }

    public void setRole(MyTextField role) {
        this.role = role;
    }

    public Button getBtnChangepass() {
        return btnChangepass;
    }

    public void setBtnChangepass(Button btnChangepass) {
        this.btnChangepass = btnChangepass;
    }

    public ModelUser getUser() {
        return user;
    }

    public void setUser(ModelUser user) {
        this.user = user;
    }
}
