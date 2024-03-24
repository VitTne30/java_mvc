/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.Services.DanhMucService;
import Swing.MyTextField;
import Swing.Table;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author admin
 */
public class DanhMucView extends JFrame {

    private JLabel lblTenDM = new JLabel("Tên danh mục: ");
    private JLabel lblMota = new JLabel("Mô tả: ");
    private JTextField txtTenDM = new JTextField();
    private JTextArea txtMota = new JTextArea();
    private MyTextField txtResult = new MyTextField();
    private JButton btnThem = new JButton("Thêm");
    private JButton btnSua = new JButton("Sửa");
    private JButton btnXoa = new JButton("Xoá");
    private JButton btnClear = new JButton("Xoá trắng");
    private JButton btnExcel = new JButton("Xuất EXCEL");
    Table tblDanhmuc;
    DefaultTableModel model;

    Font buttonFont = new Font("Arial", Font.BOLD, 14);

    public DanhMucView() {

        GUI();
        add();
    }

    public void GUI() {
        this.setTitle("danhmuc");
        this.setSize(800, 800);
        this.getContentPane().setLayout(null);
//        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    public void add() {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        txtResult.setBounds(25, 320, 305, 40);
        txtResult.setFont(buttonFont);
        txtResult.setHint("Tìm kiếm...");
        txtResult.setBackground(Color.WHITE);
        txtResult.setBorder(border);
        this.add(txtResult);

        lblTenDM.setBounds(25, 390, 111, 25);
        lblTenDM.setFont(buttonFont);
        this.add(lblTenDM);

        txtTenDM.setBounds(175, 390, 155, 25);
        txtTenDM.setFont(buttonFont);
        this.add(txtTenDM);

        lblMota.setBounds(25, 455, 85, 25);
        lblMota.setFont(buttonFont);
        this.add(lblMota);

        txtMota.setBounds(150, 455, 180, 130);
        txtMota.setBorder(border);
        txtMota.setLineWrap(true);
        txtMota.setFont(buttonFont);
        this.add(txtMota);

        btnThem.setBounds(25, 650, 90, 30);
        btnThem.setFont(buttonFont);
        this.add(btnThem);

        btnSua.setBounds(150, 650, 70, 30);
        btnSua.setFont(buttonFont);
        this.add(btnSua);

        btnXoa.setBounds(252, 650, 70, 30);
        btnXoa.setFont(buttonFont);
        this.add(btnXoa);

        btnClear.setBounds(35, 698, 105, 30);
        btnClear.setFont(buttonFont);
        this.add(btnClear);

        btnExcel.setBounds(175, 698, 120, 30);
        btnExcel.setFont(buttonFont);
        this.add(btnExcel);

        tblDanhmuc = new Table();
        JScrollPane sp = new JScrollPane();
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tblDanhmuc.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Tên danh mục", "Mô tả"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TableColumnModel columnModel = tblDanhmuc.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(65);
        columnModel.getColumn(1).setPreferredWidth(130);
        columnModel.getColumn(2).setPreferredWidth(260);
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setResizable(false);
        }
        JTableHeader header = tblDanhmuc.getTableHeader();
        header.setReorderingAllowed(false);
        sp.setViewportView(tblDanhmuc);
        sp.setBounds(355, 280, 427, 478);
        sp.getViewport().setBackground(Color.WHITE);
        add(sp);
    }

    public static void main(String[] args) throws SQLException {
        DanhMucView view = new DanhMucView();
        new DanhMucService(view);
    }

    public JTextField getTxtTenDM() {
        return txtTenDM;
    }

    public void setTxtTenDM(JTextField txtTenDM) {
        this.txtTenDM = txtTenDM;
    }

    public JTextArea getTxtMota() {
        return txtMota;
    }

    public void setTxtMota(JTextArea txtMota) {
        this.txtMota = txtMota;
    }

    public JTextField getTxtResult() {
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

    public Table getTblDanhmuc() {
        return tblDanhmuc;
    }

    public void setTblDanhmuc(Table tblNhanvien) {
        this.tblDanhmuc = tblNhanvien;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

}
