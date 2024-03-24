/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class NhanVienView extends JFrame {

    private JLabel lblHoten = new JLabel("Họ và tên: ");
    private JLabel lblNgaysinh = new JLabel("Ngày sinh: ");
    private JLabel lblDiachi = new JLabel("Địa chỉ: ");
    private JLabel lblGioitinh = new JLabel("Giới tính: ");
    private JRadioButton rdoNam = new JRadioButton("Nam");
    private JRadioButton rdoNu = new JRadioButton("Nữ");
    private JLabel lblSDT = new JLabel("SĐT: ");
    private JLabel lblEmail = new JLabel("E-mail: ");
    private JLabel lblTenTK = new JLabel("Tài khoản: ");
    private JLabel lblMK = new JLabel("Mật khẩu: ");
    private JTextField txtHoten = new JTextField();
    private JTextField txtNgaysinh = new JTextField();
    private JTextField txtDichi = new JTextField();
    private JTextField txtSDT = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtResult = new JTextField();
    private JTextField txtTenTK = new JTextField();
    private JTextField txtMK = new JTextField();
    private JButton btnThem = new JButton("Thêm");
    private JButton btnSua = new JButton("Sửa");
    private JButton btnXoa = new JButton("Xoá");
    JTable tblNhanvien;
    DefaultTableModel model;
    
    Font buttonFont = new Font("Arial", Font.BOLD, 14);

    public NhanVienView() {

        GUI();
        add();
    }

    public void GUI() {
        this.setTitle("nhanvien");
        this.setSize(800, 500);
        this.getContentPane().setLayout(null);
//        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    public void add() {
        
        txtResult.setBounds(40,15,240,40);
        txtResult.setFont(buttonFont);
        this.add(txtResult);
        
        lblHoten.setBounds(40,80,80,23);
        lblHoten.setFont(buttonFont);
        this.add(lblHoten);
        
        txtHoten.setBounds(140,80,140,23);
        txtHoten.setFont(buttonFont);
        this.add(txtHoten);
        
        lblNgaysinh.setBounds(40,115,80,23);
        lblNgaysinh.setFont(buttonFont);
        this.add(lblNgaysinh);
        
        txtNgaysinh.setBounds(140,115,140,23);
        txtNgaysinh.setFont(buttonFont);
        this.add(txtNgaysinh);
        
        lblDiachi.setBounds(40,150,80,23);
        lblDiachi.setFont(buttonFont);
        this.add(lblDiachi);
        
        txtDichi.setBounds(140,150,140,23);
        txtDichi.setFont(buttonFont);
        this.add(txtDichi);
        
        lblGioitinh.setBounds(40,185,70,23);
        lblGioitinh.setFont(buttonFont);
        this.add(lblGioitinh);
        
        rdoNam.setBounds(140,185,60,23);
        rdoNu.setBounds(220,185,60,23);
        this.add(rdoNam);
        this.add(rdoNu);
        
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(rdoNu);
        btnGroup.add(rdoNam);
        rdoNam.setSelected(true);
        
        lblSDT.setBounds(40,220,80,23);
        lblSDT.setFont(buttonFont);
        this.add(lblSDT);
        
        txtSDT.setBounds(140,220,140,23);
        txtSDT.setFont(buttonFont);
        this.add(txtSDT);
        
        lblEmail.setBounds(40,255,80,23);
        lblEmail.setFont(buttonFont);
        this.add(lblEmail);
        
        txtEmail.setBounds(140,255,140,23);
        txtEmail.setFont(buttonFont);
        this.add(txtEmail);
        
        lblTenTK.setBounds(40,290,80,23);
        lblTenTK.setFont(buttonFont);
        this.add(lblTenTK);
        
        txtTenTK.setBounds(140,290,140,23);
        txtTenTK.setFont(buttonFont);
        this.add(txtTenTK);
        
        lblMK.setBounds(40,325,80,23);
        lblMK.setFont(buttonFont);
        this.add(lblMK);
        
        txtMK.setBounds(140,325,140,23);
        txtMK.setFont(buttonFont);
        this.add(txtMK);
        
        btnThem.setBounds(40,380,80,30);
        btnThem.setFont(buttonFont);
        this.add(btnThem);
        
        btnSua.setBounds(130,380,70,30);
        btnSua.setFont(buttonFont);
        this.add(btnSua);
        
        btnXoa.setBounds(210,380,70,30);
        btnXoa.setFont(buttonFont);
        this.add(btnXoa);
        
        model = new DefaultTableModel();
        model.addColumn("Họ và tên");
        model.addColumn("Ngày sinh");
        model.addColumn("Địa chỉ");
        model.addColumn("Giới tính");
        model.addColumn("SĐT");
        model.addColumn("Email");
        model.addColumn("Tên TK");
        model.addColumn("Mật khẩu");
        tblNhanvien = new JTable(model);
        JScrollPane sp = new JScrollPane(tblNhanvien);
        sp.setBounds(315, 0, 470, 500);

        this.add(sp);
    }

    public static void main(String[] args) {
        new NhanVienView();
    }
    
    
}
