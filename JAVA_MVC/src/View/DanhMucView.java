
package View;

import Swing.MyTextField;
import Swing.Table;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
public class DanhMucView extends JPanel {

    private SimpleDateFormat simpleDate = new SimpleDateFormat("dd - MM - YYYY");
    private JLabel dateLb = new JLabel(simpleDate.format(new Date()) + "");
    private JLabel lblTitle = new JLabel("QUẢN LÍ DANH MỤC");
    private JLabel numberLb = new JLabel();
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
    Font TitleFont = new Font("Arial", Font.BOLD, 20);

    public DanhMucView() {
        GUI();
        add();
    }

    public void GUI() {
        setLayout(null);
        setBounds(0, 100, 800, 600);
        setBackground(Color.WHITE);
        setVisible(true);
        
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
        
        txtResult.setBounds(20, 145, 305, 50);
        txtResult.setFont(buttonFont);
        txtResult.setHint("Tìm kiếm...");
        txtResult.setBackground(Color.WHITE);
        txtResult.setBorder(border);
        this.add(txtResult);

        lblTenDM.setBounds(20, 225, 111, 25);
        lblTenDM.setFont(buttonFont);
        this.add(lblTenDM);

        txtTenDM.setBounds(170, 225, 155, 25);
        txtTenDM.setFont(buttonFont);
        this.add(txtTenDM);

        lblMota.setBounds(20, 280, 85, 25);
        lblMota.setFont(buttonFont);
        this.add(lblMota);

        txtMota.setBounds(105, 280, 220, 145);
        txtMota.setBorder(border);
        txtMota.setLineWrap(true);
        txtMota.setFont(buttonFont);
        this.add(txtMota);

        btnThem.setBounds(10, 470, 90, 30);
        btnThem.setFont(buttonFont);
        this.add(btnThem);

        btnSua.setBounds(135, 470, 70, 30);
        btnSua.setFont(buttonFont);
        this.add(btnSua);

        btnXoa.setBounds(235, 470, 70, 30);
        btnXoa.setFont(buttonFont);
        this.add(btnXoa);

        btnClear.setBounds(20, 515, 105, 30);
        btnClear.setFont(buttonFont);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTenDM.setText("");
                txtMota.setText("");
            }
        });
        this.add(btnClear);

        btnExcel.setBounds(160, 515, 120, 30);
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
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(240);
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setResizable(false);
        }
        JTableHeader header = tblDanhmuc.getTableHeader();
        header.setReorderingAllowed(false);
        sp.setViewportView(tblDanhmuc);
        sp.setBounds(355, 100, 427, 460);
        sp.getViewport().setBackground(Color.WHITE);
        add(sp);
    }

    public JLabel getNumberLb() {
        return numberLb;
    }

    public void setNumberLb(JLabel numberLb) {
        this.numberLb = numberLb;
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
