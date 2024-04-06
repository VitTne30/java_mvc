package View;

import Swing.Button;
import Swing.Table;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author admin
 */
public class BillView extends JPanel {
    private JLabel titleLb, numberLb, listLb, dateLb, line;
    private Button  btnRemove, btnExcel,btnSort,btnSortNum;
    private Table tblBill,tblDetail;
    private JScrollPane jScrollBill,jScrollDetail;

    public BillView() {
        setLayout(null);
        setBounds(0, 0, 800, 800);
        addBillGUI();
        setVisible(true);

    }

    public static void main(String[] args) {
        BillView bill = new BillView();
        JFrame jf = new JFrame();
        jf.setSize(800, 800);
        jf.setLayout(null);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.add(bill);
        jf.setVisible(true);
    }
    private void addBillGUI() {
        //Title 
        ImageIcon icon = new ImageIcon(getClass().getResource("/Icon/bill.png"));
        titleLb = new JLabel("Quản lý đơn hàng", icon, JLabel.LEFT);
        titleLb.setFont(new Font("sansserif", 1, 30));
        titleLb.setBounds(0, 0, 320, 50);
        add(titleLb);
        // Number
        numberLb = new JLabel("Số đơn hàng: ", JLabel.LEFT);
        numberLb.setFont(new Font("sansserif", 1, 15));
        numberLb.setBounds(0, 50, 285, 40);
        add(numberLb);
        //Time
        
        
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/Icon/cal.png"));
        dateLb = new JLabel( "", icon2, SwingConstants.RIGHT);
        dateLb.setHorizontalTextPosition(SwingConstants.LEFT);
        dateLb.setBounds(400, 0, 400, 40);
        dateLb.setFont(new Font("sansserif", 1, 15));
        add(dateLb);
        //Black line
        line = new JLabel();
        line.setBackground(Color.BLACK);
        line.setOpaque(true);
        line.setBounds(0, 100, 800, 2);
        add(line);
        //List Label
        listLb = new JLabel("Danh sách đơn hàng: ", JLabel.LEFT);
        listLb.setFont(new Font("sansserif", 1, 22));
        listLb.setBounds(0, 130, 285, 40);
        add(listLb);
        //Button Remove
        btnSortNum = new Button();
        btnSortNum.setFont(new Font("sansserif", 1, 14));
        btnSortNum.setText("Theo số");
        btnSortNum.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/sortNum.png")));
        btnSortNum.setBounds(550, 130, 120, 40);
        add(btnSortNum);
        //Button Remove
        btnSort = new Button();
        btnSort.setFont(new Font("sansserif", 1, 14));
        btnSort.setText("A - Z");
        btnSort.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/sortString.png")));
        btnSort.setBounds(690, 130, 100, 40);
        add(btnSort);
        //Button Remove
        btnRemove = new Button();
        btnRemove.setFont(new Font("sansserif", 1, 14));
        btnRemove.setText("Xóa");
        btnRemove.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/remove.png")));
        btnRemove.setBounds(435, 130, 100, 40);
        add(btnRemove);
        //Button Excel
        btnExcel = new Button();
        btnExcel.setFont(new Font("sansserif", 1, 14));
        btnExcel.setText("Xuất Excel");
        btnExcel.setBackground(Color.WHITE);
        btnExcel.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/excel.png")));
        btnExcel.setBounds(285, 130, 140, 40);
        add(btnExcel);
        // Table Bill
        tblBill = new Table();
        jScrollBill = new JScrollPane();
        jScrollBill.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tblBill.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Mã đơn hàng", "Tên khách hàng", "Ngày lập", "Tổng tiền"
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
        TableColumnModel columnModel = tblBill.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(250);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(200);
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setResizable(false);
        }
        JTableHeader header = tblBill.getTableHeader();
        header.setReorderingAllowed(false);
        jScrollBill.setViewportView(tblBill);
        jScrollBill.setBounds(0, 180, 800, 380);
        jScrollBill.getViewport().setBackground(Color.WHITE);
        add(jScrollBill);
        //Black line
        line = new JLabel();
        line.setBackground(Color.BLACK);
        line.setOpaque(true);
        line.setBounds(0, 575, 800, 2);
        add(line);
        //Table detail
        // Table Bill
        tblDetail = new Table();
        jScrollDetail = new JScrollPane();
        jScrollDetail.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tblDetail.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Mã chi tiết", "Tên sản phẩm", "Số lượng", "Tổng tiền"
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
        TableColumnModel columnModel2 = tblDetail.getColumnModel();
        columnModel2.getColumn(0).setPreferredWidth(150);
        columnModel2.getColumn(1).setPreferredWidth(250);
        columnModel2.getColumn(2).setPreferredWidth(200);
        columnModel2.getColumn(3).setPreferredWidth(200);
        for (int i = 0; i < columnModel2.getColumnCount(); i++) {
            TableColumn column = columnModel2.getColumn(i);
            column.setResizable(false);
        }
        JTableHeader header2 = tblDetail.getTableHeader();
        header2.setReorderingAllowed(false);
        jScrollDetail.setViewportView(tblDetail);
        jScrollDetail.setBounds(0, 600, 800, 200);
        jScrollDetail.getViewport().setBackground(Color.WHITE);
        add(jScrollDetail);
        
    }

    public JLabel getNumberLb() {
        return numberLb;
    }

    public void setNumberLb(JLabel numberLb) {
        this.numberLb = numberLb;
    }

    public Button getBtnRemove() {
        return btnRemove;
    }

    public void setBtnRemove(Button btnRemove) {
        this.btnRemove = btnRemove;
    }

    public Button getBtnExcel() {
        return btnExcel;
    }

    public void setBtnExcel(Button btnExcel) {
        this.btnExcel = btnExcel;
    }

    public Table getTblBill() {
        return tblBill;
    }

    public void setTblBill(Table tblBil) {
        this.tblBill = tblBil;
    }

    public Table getTblDetail() {
        return tblDetail;
    }

    public void setTblDetail(Table tblDetail) {
        this.tblDetail = tblDetail;
    }

    public Button getBtnSort() {
        return btnSort;
    }

    public Button getBtnSortNum() {
        return btnSortNum;
    }

    public void setBtnSortNum(Button btnSortNum) {
        this.btnSortNum = btnSortNum;
    }

    public JLabel getDateLb() {
        return dateLb;
    }

    public void setDateLb(JLabel dateLb) {
        this.dateLb = dateLb;
    }
    
}
