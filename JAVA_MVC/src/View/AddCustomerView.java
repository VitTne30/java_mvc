package View;

import Swing.Button;
import Swing.MyTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AddCustomerView extends JFrame {

    private JLabel title, name, email, sdt;
    private MyTextField jtfName, jtfEmail, jtfSdt;
    private Button add, cancel;

    public AddCustomerView() {
        setSize(400, 400);
        setBounds(0, 0, 400, 400);
        setTitle("Thêm khách hàng");
        setLayout(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        Font chuTo = new Font("Times New Roman", Font.ITALIC, 32);
        Font chuNho = new Font("Arial", Font.CENTER_BASELINE, 15);
        //title
        title = new JLabel("Thêm khách hàng");
        title.setFont(chuTo);
        title.setBounds(65, 0, 400, 50);
        add(title);
        
        JLabel line = new JLabel();
        line.setBackground(Color.BLACK);
        line.setBounds(0, 55, 500, 2);
        line.setOpaque(true);
        this.add(line);

        name = new JLabel("Họ và tên:");
        name.setFont(chuNho);
        name.setBounds(10, 75, 100, 40);
        add(name);

        jtfName = new MyTextField();
        jtfName.setFont(chuNho);
        jtfName.setBounds(150, 75, 220, 40);
        add(jtfName);

        email = new JLabel("Email:");
        email.setFont(chuNho);
        email.setBounds(10, 140, 100, 40);
        add(email);

        jtfEmail = new MyTextField();
        jtfEmail.setFont(chuNho);
        jtfEmail.setBounds(150, 140, 220, 40);
        add(jtfEmail);

        sdt = new JLabel("SDT:");
        sdt.setFont(chuNho);
        sdt.setBounds(10, 205, 100, 40);
        add(sdt);

        jtfSdt = new MyTextField();
        jtfSdt.setFont(chuNho);
        jtfSdt.setBounds(150, 205, 220, 40);
        add(jtfSdt);

        add = new Button();
        add.setText("Thêm khách hàng");
        add.setFont(chuNho);
        add.setBounds(20, 280, 200, 40);
        add(add);

        cancel = new Button();
        cancel.setText("Đóng");
        cancel.setFont(chuNho);
        cancel.setBounds(260, 280, 100, 40);
        add(cancel);
        repaint();
    }

    public MyTextField getJtfName() {
        return jtfName;
    }

    public MyTextField getJtfEmail() {
        return jtfEmail;
    }

    public MyTextField getJtfSdt() {
        return jtfSdt;
    }

    public Button getAdd() {
        return add;
    }

    public Button getCancel() {
        return cancel;
    }

}
