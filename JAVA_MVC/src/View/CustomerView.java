package View;

import Swing.Button;
import Swing.MyTextField;
import Swing.Table;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ADMIN
 */
public class CustomerView extends javax.swing.JPanel {

    private JLabel titleLb, numberLb, listLb, dateLb,line;
    private SimpleDateFormat simpleDate;
    private MyTextField searchField;
    private Button btnAdd, btnRemove, btnModify,btnExcel;
    private Table tableStaff;
    private JScrollPane jScrollPane1;

    public CustomerView() {
        setLayout(null);
        setVisible(true);
        setBounds(0, 0, 800, 800);
        //
        addCusGUI();
    }

    private void addCusGUI() {
        //Title 
        ImageIcon icon = new ImageIcon(getClass().getResource("/Icon/customer.png"));
        titleLb = new JLabel("Quản lý khách hàng", icon, JLabel.LEFT);
        titleLb.setFont(new Font("sansserif", 1, 30));
        titleLb.setBounds(0, 0, 320, 50);
        add(titleLb);
        // Number
        numberLb = new JLabel("Số khách hàng đăng ký: ", JLabel.LEFT);
        numberLb.setFont(new Font("sansserif", 1, 15));
        numberLb.setBounds(0, 50, 285, 40);
        add(numberLb);
        //Time
        simpleDate = new SimpleDateFormat("dd - MM - YYYY");
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/Icon/cal.png"));
        dateLb = new JLabel(simpleDate.format(new Date()) + "", icon2, SwingConstants.RIGHT);
        dateLb.setHorizontalTextPosition(SwingConstants.LEFT);
        dateLb.setBounds(600, 0, 200, 40);
        dateLb.setFont(new Font("sansserif", 1, 15));
        add(dateLb);
        //Black line
        line = new JLabel();
        line.setBackground(Color.BLACK);
        line.setOpaque(true);
        line.setBounds(0, 100, 770, 2);
        add(line);
        //List Label
        listLb = new JLabel("Danh sách khách hàng: ", JLabel.LEFT);
        listLb.setFont(new Font("sansserif", 1, 22));
        listLb.setBounds(0, 130, 285, 40);
        add(listLb);
        //Search 
        searchField = new MyTextField();
        searchField.setFont(new Font("sansserif", 1, 15));
        searchField.setHint("Tìm kiếm khách hàng");
        searchField.setBounds(0, 180, 285, 40);
        searchField.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/search.png")));
        add(searchField);
        //Button Add
        btnAdd = new Button();
        btnAdd.setFont(new Font("sansserif", 1, 14));
        btnAdd.setText("Thêm");
        btnAdd.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/addStaff.png")));
        btnAdd.setBounds(295, 180, 100, 40);
        add(btnAdd);
        //Button Fix
        btnModify = new Button();
        btnModify.setFont(new Font("sansserif", 1, 14));
        btnModify.setText("Sửa");
        btnModify.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/modify.png")));
        btnModify.setBounds(415, 180, 100, 40);
        add(btnModify);
        //Button Remove
        btnRemove = new Button();
        btnRemove.setFont(new Font("sansserif", 1, 14));
        btnRemove.setText("Xóa");
        btnRemove.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/remove.png")));
        btnRemove.setBounds(535, 180, 100, 40);
        add(btnRemove);
        //Button Excel
        btnExcel = new Button();
        btnExcel.setFont(new Font("sansserif", 1, 14));
        btnExcel.setText("Xuất Excel");
        btnExcel.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/remove.png")));
        btnExcel.setBounds(655, 180, 140, 40);
        add(btnExcel);
        //
        tableStaff = new Table();
        jScrollPane1 = new JScrollPane();
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tableStaff.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Email"
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
        TableColumnModel columnModel = tableStaff.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(250);
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setResizable(false);
        }
        JTableHeader header = tableStaff.getTableHeader();
        header.setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableStaff);
        jScrollPane1.setBounds(0, 225, 800, 580);
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        add(jScrollPane1);
    }
    public JLabel getNumberLb() {
        return numberLb;
    }

    public MyTextField getSearchField() {
        return searchField;
    }

    public Button getBtnAdd() {
        return btnAdd;
    }

    public Button getBtnRemove() {
        return btnRemove;
    }

    public Button getBtnModify() {
        return btnModify;
    }

    public Table getTableStaff() {
        return tableStaff;
    }

    public Button getBtnExcel() {
        return btnExcel;
    }

    public void setBtnExcel(Button btnExcel) {
        this.btnExcel = btnExcel;
    }
    

}
