package View;

import Swing.Button;
import Swing.ImageLogo;
import Swing.MyTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author ADMIN
 */
public class InsertModifyView extends JPanel {
    private JLabel titleLb,lb1,lb2,lb3,lb4;
    private JLabel line,line2;
    private MyTextField txtId,txtName,txtPhone,txtEmail;
    private Button btnConfirm, btnCancel;
    private ImageLogo imgCus;
    
    public InsertModifyView(){
        setLayout(null);
        setBounds(0, 125, 800, 550);
        //
        addInsertGUI();
        setBackground(Color.WHITE);
        setVisible(true);
    }
    
    private void addInsertGUI(){
        //Title
        ImageIcon icon = new ImageIcon(getClass().getResource("/Icon/customer.png"));
        titleLb = new JLabel("Thêm khách hàng", icon, JLabel.LEFT);
        titleLb.setFont(new Font("sansserif", 1, 30));
        titleLb.setBounds(10, 20, 420, 50);
        add(titleLb);
        //line
        line = new JLabel();
        line.setBackground(Color.BLACK);
        line.setOpaque(true);
        line.setBounds(20, 115, 760, 2);
        add(line);
        //ImageCustomer
        imgCus = new ImageLogo();
        imgCus.setBounds(20, 175, 240, 240);
        imgCus.setNeedSize(240, 240);
        imgCus.setIcon(new ImageIcon(getClass().getResource("/Icon/question.png")));
        add(imgCus);
        //label Id
        lb1 = new JLabel("Id:");
        lb1.setFont(new Font("sansserif", 1, 18));
        lb1.setBounds(300, 187, 130, 40);
        add(lb1);
        //label Name
        lb2 = new JLabel("Họ và tên:");
        lb2.setFont(new Font("sansserif", 1, 18));
        lb2.setBounds(300, 249, 130, 40);
        add(lb2);
        //label Phone
        lb3 = new JLabel("Số điện thoại:");
        lb3.setFont(new Font("sansserif", 1, 18));
        lb3.setBounds(300, 311, 130, 40);
        add(lb3);
        //Label Gender
        lb4 = new JLabel("Email:");
        lb4.setFont(new Font("sansserif", 1, 18));
        lb4.setBounds(300, 373, 130, 40);
        add(lb4);
        ///////////////////
        //txtId
        txtId = new MyTextField();
        txtId.setEditable(false);
        txtId.setFont(new Font("sansserif", Font.BOLD, 18));
        txtId.setForeground(new java.awt.Color(89, 89, 89));
        txtId.setBounds(440, 187, 315, 40);
        add(txtId);
        //txtName
        txtName = new MyTextField();
        txtName.setFont(new Font("sansserif", 1, 18));
        txtName.setBounds(440, 249, 315, 40);
        add(txtName);
        //txtPhone
        txtPhone = new MyTextField();
        txtPhone.setFont(new Font("sansserif", 1, 18));
        txtPhone.setBounds(440, 311, 315, 40);
        add(txtPhone);
        //txtEmail
        txtEmail = new MyTextField();
        txtEmail.setFont(new Font("sansserif", 1, 18));
        txtEmail.setBounds(440, 373, 315, 40);
        add(txtEmail);
        //
        line2 = new JLabel();
        line2.setBackground(Color.BLACK);
        line2.setOpaque(true);
        line2.setBounds(20, 450, 760, 2);
        add(line2);
        //ButtonConfirm
        btnConfirm = new Button();
        btnConfirm.setFont(new Font("sansserif", 1, 15));
        btnConfirm.setText("Xác nhận");
        btnConfirm.setForeground(Color.GREEN);
        btnConfirm.setBounds(495, 475, 130, 40);
        btnConfirm.setBackground(new java.awt.Color(240, 240, 240));
        add(btnConfirm);
        //ButtonCancel
        btnCancel = new Button();
        btnCancel.setFont(new Font("sansserif", 1, 15));
        btnCancel.setText("Hủy");
        btnCancel.setForeground(Color.red);
        btnCancel.setBackground(new java.awt.Color(240, 240, 240));
        btnCancel.setBounds(175, 475, 130, 40);
        add(btnCancel);
    }

    public JLabel getTitleLb() {
        return titleLb;
    }

    public void setTitleLb(JLabel titleLb) {
        this.titleLb = titleLb;
    }

    public MyTextField getTxtId() {
        return txtId;
    }

    public void setTxtId(MyTextField txtId) {
        this.txtId = txtId;
    }

    public MyTextField getTxtName() {
        return txtName;
    }

    public void setTxtName(MyTextField txtName) {
        this.txtName = txtName;
    }

    public MyTextField getTxtPhone() {
        return txtPhone;
    }

    public void setTxtPhone(MyTextField txtPhone) {
        this.txtPhone = txtPhone;
    }

    public MyTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(MyTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public Button getBtnConfirm() {
        return btnConfirm;
    }

    public void setBtnConfirm(Button btnConfirm) {
        this.btnConfirm = btnConfirm;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(Button btnCancel) {
        this.btnCancel = btnCancel;
    }
    
    
}
