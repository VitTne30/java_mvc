/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Swing.Button;
import Swing.MyTextField;
import Swing.Table;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
 * @author haqan
 */
public class NxbView extends JPanel {

    private SimpleDateFormat simpleDate = new SimpleDateFormat("dd - MM - YYYY");
    private JLabel dateLb = new JLabel(simpleDate.format(new Date()) + "");
    private JLabel lblTitle = new JLabel("QUẢN LÍ NHÀ XUẤT BẢN");
    private JLabel numberLb = new JLabel();
    private JLabel lblTen = new JLabel();
    private JLabel lblSdt = new JLabel();
    private JLabel lblDiachi = new JLabel();
    private JTextField txtTen = new JTextField();
    private JTextField txtSdt = new JTextField();
    private JTextField txtDiachi = new JTextField();
    private MyTextField txtResult = new MyTextField();
    private Button btnThem = new Button();
    private Button btnSua = new Button();
    private Button btnXoa = new Button();
    private Button btnClear = new Button();
    private Button btnExcel = new Button();
    Table tblNxb;
    DefaultTableModel model;

    Font buttonFont = new Font("Arial", Font.BOLD, 14);
    Font TitleFont = new Font("Arial", Font.BOLD, 20);

    public NxbView() {
        GUI();
        add();
        this.setVisible(true);
    }

    private void GUI() {
        setBounds(0, 0, 800, 800);
        setLayout(null);
        setBackground(new Color(240, 240, 240));
    }

    private void add() {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        lblTitle.setBounds(10, 0, 320, 40);
        lblTitle.setFont(TitleFont);
        lblTitle.setForeground(Color.decode("#0066FF"));
        this.add(lblTitle);

        numberLb.setFont(TitleFont);
        numberLb.setBounds(50, 420, 285, 40);
        this.add(numberLb);

        dateLb.setBounds(650, 0, 300, 40);
        dateLb.setBackground(Color.red);
        dateLb.setFont(TitleFont);
        this.add(dateLb);

        txtResult.setBounds(500, 430, 250, 50);
        txtResult.setFont(buttonFont);
        txtResult.setHint("Tìm kiếm...");
        txtResult.setBackground(Color.WHITE);
        txtResult.setBorder(border);
        this.add(txtResult);

        lblTen.setBounds(50, 500, 150, 30);
        lblTen.setFont(buttonFont);
        lblTen.setText("Nhà xuất bản: ");
        this.add(lblTen);

        txtTen.setBounds(150, 500, 300, 30);
        txtTen.setFont(buttonFont);
        this.add(txtTen);

        lblSdt.setBounds(50, 550, 150, 30);
        lblSdt.setFont(buttonFont);
        lblSdt.setText("Số điện thoại: ");
        this.add(lblSdt);

        txtSdt.setBounds(150, 550, 300, 30);
        txtSdt.setFont(buttonFont);
        txtSdt.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txtSdt.getText().length() >= 10)
                {
                    e.consume();
                }
            }
        });
        this.add(txtSdt);

        lblDiachi.setBounds(50, 600, 150, 30);
        lblDiachi.setFont(buttonFont);
        lblDiachi.setText("Địa chỉ: ");
        this.add(lblDiachi);

        txtDiachi.setBounds(150, 600, 300, 30);
        txtDiachi.setFont(buttonFont);
        this.add(txtDiachi);

        btnThem.setBounds(40, 680, 120, 50);
        btnThem.setFont(buttonFont);
        btnThem.setText("Thêm");
        btnThem.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/addStaff.png")));
        this.add(btnThem);

        btnSua.setBounds(190, 680, 120, 50);
        btnSua.setFont(buttonFont);
        btnSua.setText("Sửa");
        btnSua.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/modify.png")));
        this.add(btnSua);

        btnXoa.setBounds(340, 680, 120, 50);
        btnXoa.setFont(buttonFont);
        btnXoa.setText("Xóa");
        btnXoa.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/remove.png")));
        this.add(btnXoa);

        btnClear.setBounds(490, 680, 130, 50);
        btnClear.setFont(buttonFont);
        btnClear.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/clear.png")));
        btnClear.setText("Xóa trắng");
        this.add(btnClear);

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTen.setText("");
                txtSdt.setText("");
                txtDiachi.setText("");
            }
        });

        btnExcel.setBounds(640, 680, 120, 50);
        btnExcel.setFont(buttonFont);
        btnExcel.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/excel.png")));
        btnExcel.setText("Excel");
        this.add(btnExcel);

        tblNxb = new Table();
        JScrollPane sp = new JScrollPane();
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tblNxb.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Tên nhà xuất bản", "SĐT", "Địa chỉ"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TableColumnModel columnModel = tblNxb.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(175);
        columnModel.getColumn(2).setPreferredWidth(175);
        columnModel.getColumn(3).setPreferredWidth(300);
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setResizable(false);
        }
        JTableHeader header = tblNxb.getTableHeader();
        header.setReorderingAllowed(false);
        sp.setViewportView(tblNxb);
        sp.setBounds(50, 60, 700, 355);
        sp.getViewport().setBackground(Color.WHITE);
        add(sp);
    }

    public JLabel getNumberLb() {
        return numberLb;
    }

    public void setNumberLb(JLabel numberLb) {
        this.numberLb = numberLb;
    }

    public JTextField getTxtTen() {
        return txtTen;
    }

    public void setTxtTen(JTextField txtTen) {
        this.txtTen = txtTen;
    }

    public JTextField getTxtSdt() {
        return txtSdt;
    }

    public void setTxtSdt(JTextField txtSdt) {
        this.txtSdt = txtSdt;
    }

    public JTextField getTxtDiachi() {
        return txtDiachi;
    }

    public void setTxtDiachi(JTextField txtDiachi) {
        this.txtDiachi = txtDiachi;
    }

    public MyTextField getTxtResult() {
        return txtResult;
    }

    public void setTxtResult(MyTextField txtResult) {
        this.txtResult = txtResult;
    }

    public Button getBtnThem() {
        return btnThem;
    }

    public void setBtnThem(Button btnThem) {
        this.btnThem = btnThem;
    }

    public Button getBtnSua() {
        return btnSua;
    }

    public void setBtnSua(Button btnSua) {
        this.btnSua = btnSua;
    }

    public Button getBtnXoa() {
        return btnXoa;
    }

    public void setBtnXoa(Button btnXoa) {
        this.btnXoa = btnXoa;
    }

    public Button getBtnExcel() {
        return btnExcel;
    }

    public void setBtnExcel(Button btnExcel) {
        this.btnExcel = btnExcel;
    }

    public Table getTblNxb() {
        return tblNxb;
    }

    public void setTblNxb(Table tblNxb) {
        this.tblNxb = tblNxb;
    }

}
