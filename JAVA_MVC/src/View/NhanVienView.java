/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Swing.MyTextField;
import Swing.Table;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author admin
 */
public class NhanVienView extends JPanel {

    private SimpleDateFormat simpleDate = new SimpleDateFormat("dd - MM - YYYY");
    private JLabel dateLb = new JLabel(simpleDate.format(new Date()) + "");
    private JLabel lblTitle = new JLabel("QUẢN LÍ TÀI KHOẢN");
    private JLabel numberLb = new JLabel();
    private JLabel lblHoten = new JLabel("Họ và tên: ");
    private JLabel lblDiachi = new JLabel("Địa chỉ: ");
    private JLabel lblSDT = new JLabel("SĐT: ");
    private JLabel lblEmail = new JLabel("E-mail: ");
    private JLabel lblTenTK = new JLabel("Tài khoản: ");
    private JLabel lblChucVu = new JLabel("Chức vụ: ");
    private JLabel lblMatKhau = new JLabel("Mật khẩu: ");
    private JTextField txtHoten = new JTextField();
    private JTextField txtDiachi = new JTextField();
    private JTextField txtSDT = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtTenTK = new JTextField();
    private JTextField txtMK = new JTextField();
    private JComboBox cboChucVu = new JComboBox();
    private MyTextField txtResult = new MyTextField();
    private JButton btnThem = new JButton("Thêm");
    private JButton btnSua = new JButton("Sửa");
    private JButton btnXoa = new JButton("Xoá");
    private JButton btnClear = new JButton("Xoá trắng");
    private JButton btnExcel = new JButton("Xuất EXCEL");
    
    Table tblTaiKhoan;
    DefaultTableModel model;
    
    Font buttonFont = new Font("Arial", Font.BOLD, 14);
    Font TitleFont = new Font("Arial", Font.BOLD, 20);

    public NhanVienView() {

        GUI();
        add();
    }

    public void GUI() {
        setLayout(null);
        setBounds(0, 0, 800, 800);
//        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setVisible(true);
        
    }

    public void add() {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        lblTitle.setBounds(10, 10, 320, 40);
        lblTitle.setFont(TitleFont);
        lblTitle.setForeground(Color.RED);
        this.add(lblTitle);
        
        numberLb.setFont(TitleFont);
        numberLb.setBounds(10, 60, 285, 40);
        this.add(numberLb);
        
        dateLb.setBounds(650, 10, 300, 40);
        dateLb.setBackground(Color.red);
        dateLb.setFont(TitleFont);
        this.add(dateLb);
        
        txtResult.setBounds(310, 60, 250, 40);
        txtResult.setFont(buttonFont);
        txtResult.setHint("Tìm kiếm...");
        txtResult.setBackground(Color.WHITE);
        txtResult.setBorder(border);
        this.add(txtResult);
        
        lblHoten.setBounds(10,600,80,25);
        lblHoten.setFont(buttonFont);
        this.add(lblHoten);
        
        txtHoten.setBounds(100,600,200,25);
        txtHoten.setFont(buttonFont);
        this.add(txtHoten);
        
        lblDiachi.setBounds(10,640,80,25);
        lblDiachi.setFont(buttonFont);
        this.add(lblDiachi);
        
        txtDiachi.setBounds(100,640,200,25);
        txtDiachi.setFont(buttonFont);
        this.add(txtDiachi);
        
        lblSDT.setBounds(10,680,80,25);
        lblSDT.setFont(buttonFont);
        this.add(lblSDT);
        
        txtSDT.setBounds(100,680,200,25);
        txtSDT.setFont(buttonFont);
        this.add(txtSDT);
        
        lblEmail.setBounds(335,600,80,25);
        lblEmail.setFont(buttonFont);
        this.add(lblEmail);
        
        txtEmail.setBounds(430,600,190,25);
        txtEmail.setFont(buttonFont);
        this.add(txtEmail);
        
        lblTenTK.setBounds(335,640,80,25);
        lblTenTK.setFont(buttonFont);
        this.add(lblTenTK);
        
        txtTenTK.setBounds(430,640,190,25);
        txtTenTK.setFont(buttonFont);
        this.add(txtTenTK);
        
        lblMatKhau.setBounds(335,680,80,25);
        lblMatKhau.setFont(buttonFont);
        this.add(lblMatKhau);
        
        txtMK.setBounds(430,680,190,25);
        txtMK.setFont(buttonFont);
        this.add(txtMK);
        
        lblChucVu.setBounds(10,720,80,25);
        lblChucVu.setFont(buttonFont);
        this.add(lblChucVu);
        
        cboChucVu.setBounds(100,720,200,25);
        cboChucVu.setFont(buttonFont);
        this.add(cboChucVu);
        
        btnThem.setBounds(700,595,80,30);
        btnThem.setFont(buttonFont);
        this.add(btnThem);
        
        btnSua.setBounds(700,635,80,30);
        btnSua.setFont(buttonFont);
        this.add(btnSua);
        
        btnXoa.setBounds(700,675,80,30);
        btnXoa.setFont(buttonFont);
        this.add(btnXoa);
        
        btnClear.setBounds(675,715,105,30);
        btnClear.setFont(buttonFont);
        this.add(btnClear);
        
        btnExcel.setBounds(535,715,120,30);
        btnExcel.setFont(buttonFont);
        this.add(btnExcel);
        
        tblTaiKhoan = new Table();
        JScrollPane sp = new JScrollPane();
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tblTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Họ và tên", "Địa chỉ", "SĐT", "Email", "Tên tài khoản", "Mật khẩu", "Chức vụ"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TableColumnModel columnModel = tblTaiKhoan.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(60);
        columnModel.getColumn(1).setPreferredWidth(120);
        columnModel.getColumn(2).setPreferredWidth(110);
        columnModel.getColumn(3).setPreferredWidth(90);
        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(5).setPreferredWidth(120);
        columnModel.getColumn(6).setPreferredWidth(90);
        columnModel.getColumn(7).setPreferredWidth(110);
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setResizable(false);
        }
        JTableHeader header = tblTaiKhoan.getTableHeader();
        header.setReorderingAllowed(false);
        sp.setViewportView(tblTaiKhoan);
        sp.setBounds(10, 120, 770, 460);
        sp.getViewport().setBackground(Color.WHITE);
        add(sp);
    }

    public JLabel getDateLb() {
        return dateLb;
    }

    public void setDateLb(JLabel dateLb) {
        this.dateLb = dateLb;
    }

    public JLabel getNumberLb() {
        return numberLb;
    }

    public void setNumberLb(JLabel numberLb) {
        this.numberLb = numberLb;
    }

    public JTextField getTxtHoten() {
        return txtHoten;
    }

    public void setTxtHoten(JTextField txtHoten) {
        this.txtHoten = txtHoten;
    }

    public JTextField getTxtDiachi() {
        return txtDiachi;
    }

    public void setTxtDiachi(JTextField txtDiachi) {
        this.txtDiachi = txtDiachi;
    }

    public JTextField getTxtSDT() {
        return txtSDT;
    }

    public void setTxtSDT(JTextField txtSDT) {
        this.txtSDT = txtSDT;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(JTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public JTextField getTxtTenTK() {
        return txtTenTK;
    }

    public void setTxtTenTK(JTextField txtTenTK) {
        this.txtTenTK = txtTenTK;
    }

    public JComboBox getCboChucVu() {
        return cboChucVu;
    }

    public void setTxtChucVu(JComboBox cboChucVu) {
        this.cboChucVu = cboChucVu;
    }

    public JTextField getTxtMK() {
        return txtMK;
    }

    public void setTxtMK(JTextField txtMK) {
        this.txtMK = txtMK;
    }

    public MyTextField getTxtResult() {
        return txtResult;
    }

    public void setTxtResult(MyTextField txtResult) {
        this.txtResult = txtResult;
    }

    public JButton getBtnThem() {
        return btnThem;
    }

    public void setBtnThem(JButton btnThem) {
        this.btnThem = btnThem;
    }

    public JButton getBtnSua() {
        return btnSua;
    }

    public void setBtnSua(JButton btnSua) {
        this.btnSua = btnSua;
    }

    public JButton getBtnXoa() {
        return btnXoa;
    }

    public void setBtnXoa(JButton btnXoa) {
        this.btnXoa = btnXoa;
    }

    public JButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(JButton btnClear) {
        this.btnClear = btnClear;
    }

    public JButton getBtnExcel() {
        return btnExcel;
    }

    public void setBtnExcel(JButton btnExcel) {
        this.btnExcel = btnExcel;
    }

    public Table getTblTaiKhoan() {
        return tblTaiKhoan;
    }

    public void setTblTaiKhoan(Table tblTaiKhoan) {
        this.tblTaiKhoan = tblTaiKhoan;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

//    public static void main(String[] args) throws SQLException {
//        NhanVienView nv = new NhanVienView();
//        NhanVienController controller = new NhanVienController(nv);
//    }
    
    
}
