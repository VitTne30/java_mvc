package View;

import Model.ModelUser;
import Swing.Button;
import Swing.MyPasswordField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author ADMIN
 */
public class ChangePassView extends JPanel {

    private JLabel titleLb, lbMessage, line, line1;
    private MyPasswordField txtOld, txtNew, txtConfirm;
    private char pas;
    private Icon show;
    private Button btnConfirm, btnCancel;
    private ModelUser inUseUser;

    public ChangePassView() {
        setLayout(null);
        setBounds(150, 175, 600, 450);
        setBackground(Color.WHITE);
        show = new ImageIcon(getClass().getResource("/Icon/show.png"));
        //
        changeGUI();
        setVisible(true);
    }

    public ChangePassView(ModelUser us) {
        setLayout(null);
        setBounds(100, 150, 600, 500);
        setBackground(Color.WHITE);
        show = new ImageIcon(getClass().getResource("/Icon/show.png"));
        //
        this.inUseUser = us;
        changeGUI();
        setVisible(true);
    }

    private void changeGUI() {
        //LabelTitle
        ImageIcon icon = new ImageIcon(getClass().getResource("/Icon/changePass.png"));
        titleLb = new JLabel("Đổi mật khẩu", icon, JLabel.LEFT);
        titleLb.setFont(new Font("sansserif", 1, 20));
        titleLb.setBounds(25, 20, 225, 70);
        add(titleLb);
        //Black line
        line = new JLabel();
        line.setBackground(Color.BLACK);
        line.setOpaque(true);
        line.setBounds(20, 92, 560, 2);
        add(line);
        //OldPass
        txtOld = new MyPasswordField();
        txtOld.setBounds(127, 146, 345, 40);
        txtOld.setBackground(new Color(240,240,240));
        txtOld.setHint("Nhập mật khẩu hiện tại...");
        txtOld.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/pass.png")));
        txtOld.setSuffixIcon(show);
        add(txtOld);
        //errorLb
        lbMessage = new JLabel("", JLabel.LEFT);
        lbMessage.setFont(new Font("sansserif", 1, 10));
        lbMessage.setForeground(Color.red);
        lbMessage.setBounds(127, 190, 345, 18);
        add(lbMessage);
        //txtNew
        txtNew = new MyPasswordField();
        txtNew.setBackground(new Color(240,240,240));
        txtNew.setBounds(127, 227, 345, 40);
        txtNew.setHint("Nhập mật khẩu mới...");
        txtNew.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/pass.png")));
        txtNew.setSuffixIcon(show);
        
        add(txtNew);
        //RePass
        txtConfirm = new MyPasswordField();
        txtConfirm.setBackground(new Color(240,240,240));
        txtConfirm.setBounds(127, 285, 345, 40);
        txtConfirm.setHint("Xác nhận mật khẩu...");
        txtConfirm.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/pass.png")));
        txtConfirm.setSuffixIcon(show);
 
        add(txtConfirm);
        //Black line
        line1 = new JLabel();
        line1.setBackground(Color.BLACK);
        line1.setOpaque(true);
        line1.setBounds(25, 350, 560, 2);
        add(line1);
        //ButtonConfirm
        btnConfirm  = new Button();
        btnConfirm.setFont(new Font("sansserif", 1, 15));
        btnConfirm.setText("Xác nhận");
        btnConfirm.setForeground(Color.GREEN);
        btnConfirm.setBounds(127, 380, 130, 40);
        btnConfirm.setBackground(new java.awt.Color(240, 240, 240));
        add(btnConfirm);
        //ButtonCancel
        btnCancel = new Button();
        btnCancel.setFont(new Font("sansserif", 1, 15));
        btnCancel.setText("Hủy");
        btnCancel.setForeground(Color.red);
        btnCancel.setBackground(new java.awt.Color(240, 240, 240));
        btnCancel.setBounds(342, 380, 130, 40);
        add(btnCancel);
    }

    public static void main(String[] args) {
        ChangePassView txtNew = new ChangePassView();
        JFrame jf = new JFrame();
        jf.setSize(800, 800);
        jf.setLayout(null);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.add(txtNew);
        jf.setVisible(true);
    }

    public MyPasswordField getTxtOld() {
        return txtOld;
    }

    public void setTxtOld(MyPasswordField txtOld) {
        this.txtOld = txtOld;
    }

    public MyPasswordField getTxtNew() {
        return txtNew;
    }

    public void setTxtNew(MyPasswordField txtNew) {
        this.txtNew = txtNew;
    }

    public MyPasswordField getTxtConfirm() {
        return txtConfirm;
    }

    public void setTxtConfirm(MyPasswordField txtConfirm) {
        this.txtConfirm = txtConfirm;
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

    public ModelUser getInUseUser() {
        return inUseUser;
    }

    public void setInUseUser(ModelUser inUseUser) {
        this.inUseUser = inUseUser;
    }
    

    public JLabel getLbMessage() {
        return lbMessage;
    }

    public void setLbMessage(JLabel lbMessage) {
        this.lbMessage = lbMessage;
    }
    
}
