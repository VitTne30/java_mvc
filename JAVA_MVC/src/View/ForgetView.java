package View;

import Swing.Button;
import Swing.MyPasswordField;
import Swing.MyTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author ADMIN
 */
public class ForgetView extends JFrame {

    private JLabel forgetLb, errorLb;
    private MyTextField findAcc;
    private MyPasswordField newPass, rePass;
    private Button btnBack, btnConfirm;
    private Icon show;

    public ForgetView() {
        super("Quên mật khẩu");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        //
        show = new ImageIcon(getClass().getResource("/Icon/show.png"));
        //
        addGUI();
        //
    }

    private void addGUI() {
        //
        forgetLb = new JLabel("Quên mật khẩu", JLabel.CENTER);
//        forgetLb.setForeground(new Color(245, 245, 245));
        forgetLb.setFont(new Font("sansserif", 1, 32));
        forgetLb.setBounds(75, 20, 250, 70);
        add(forgetLb);
        //findUser
        findAcc = new MyTextField();
        findAcc.setBounds(50, 105, 300, 40);
        findAcc.setBackground(Color.white);
        findAcc.setHint("Nhập email tài khoản cần tìm...");
        add(findAcc);
        //errorLb
        errorLb = new JLabel("", JLabel.LEFT);
        errorLb.setFont(new Font("sansserif", 1, 10));
        errorLb.setForeground(Color.red);
        errorLb.setBounds(50, 150, 260, 15);
        add(errorLb);
        //newPass
        newPass = new MyPasswordField();
        newPass.setBackground(Color.white);
        newPass.setBounds(50, 170, 300, 40);
        newPass.setHint("Nhập mật khẩu mới...");
        newPass.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/pass.png")));
        newPass.setSuffixIcon(show);
        add(newPass);
        //RePass
        rePass = new MyPasswordField();
        rePass.setBackground(Color.white);
        rePass.setBounds(50, 215, 300, 40);
        rePass.setHint("Xác nhận mật khẩu...");
        rePass.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/pass.png")));
        rePass.setSuffixIcon(show);
        add(rePass);
        //ButtonBack
        btnBack = new Button();
        btnBack.setText("Hủy");
        btnBack.setBounds(155, 270, 90, 35);
        add(btnBack);
        //ButtonConFirm
        btnConfirm = new Button();
        btnConfirm.setText("Xác nhận");
        btnConfirm.setBounds(260, 270, 90, 35);
        add(btnConfirm);
    }

    public JLabel getForgetLb() {
        return forgetLb;
    }

    public JLabel getErrorLb() {
        return errorLb;
    }

    public MyTextField getFindAcc() {
        return findAcc;
    }

    public MyPasswordField getNewPass() {
        return newPass;
    }

    public MyPasswordField getRePass() {
        return rePass;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Button getBtnConfirm() {
        return btnConfirm;
    }

}
