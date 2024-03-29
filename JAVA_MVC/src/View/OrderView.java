package View;

import Swing.Button;
import Swing.MyTextField;
import Swing.Table;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class OrderView extends JPanel {

    private JLabel title, idOrder, customer, product, number, lbDateTime, dateTime, totalPrice;
    private Button bthAddBook, btnAddOrder, btnCancelOrder, addNewCustomer;
    private Table tableOrder;
    private JScrollPane scroll;
    private MyTextField jtfIdOrder, jtfCustomer, jtfProduct, jtfNumber, jtfPrice, jtfTotalPrice;

    public OrderView() {
        setLayout(null);
        setBounds(0, 0, 800, 800);
        Font chuTo = new Font("Times New Roman", Font.ITALIC, 36);
        Font chuNho = new Font("Arial", Font.CENTER_BASELINE, 20);
        //title
        title = new JLabel("Tạo đơn hàng");
        title.setFont(chuTo);
        title.setBounds(300, 0, 800, 50);
        add(title);

        JLabel line = new JLabel();
        line.setBackground(Color.BLACK);
        line.setBounds(0, 55, 800, 2);
        line.setOpaque(true);
        this.add(line);

        idOrder = new JLabel("Mã đơn:");
        idOrder.setFont(chuNho);
        idOrder.setBounds(10, 65, 100, 40);
        add(idOrder);

        jtfIdOrder = new MyTextField();
        jtfIdOrder.setFont(chuNho);
        jtfIdOrder.setBounds(130, 65, 140, 40);
        add(jtfIdOrder);

        customer = new JLabel("Khách hàng:");
        customer.setFont(chuNho);
        customer.setBounds(300, 65, 120, 40);
        add(customer);

        jtfCustomer = new MyTextField();
        jtfCustomer.setFont(chuNho);
        jtfCustomer.setBounds(430, 65, 200, 40);
        add(jtfCustomer);

        addNewCustomer = new Button();
        addNewCustomer.setText("Thêm mới");
        addNewCustomer.setFont(chuNho);
        addNewCustomer.setBounds(650, 65, 120, 40);
        add(addNewCustomer);

        product = new JLabel("Sản phẩm:");
        product.setFont(chuNho);
        product.setBounds(10, 140, 150, 40);
        add(product);

        jtfProduct = new MyTextField();
        jtfProduct.setFont(chuNho);
        jtfProduct.setBounds(130, 140, 200, 40);
        add(jtfProduct);

        number = new JLabel("Số lượng:");
        number.setFont(chuNho);
        number.setBounds(400, 140, 100, 40);
        add(number);

        jtfNumber = new MyTextField();
        jtfNumber.setFont(chuNho);
        jtfNumber.setBounds(510, 140, 120, 40);
        add(jtfNumber);
        
        jtfPrice = new MyTextField();
        jtfPrice.setFont(chuNho);
        jtfPrice.setHint("VND");
        jtfPrice.setBounds(650, 140, 120, 40);
        add(jtfPrice);
        
        lbDateTime = new JLabel("Thời gian: ");
        lbDateTime.setFont(chuNho);
        lbDateTime.setBounds(10, 215, 120, 40);
        add(lbDateTime);

        dateTime = new JLabel("...");
        dateTime.setFont(chuNho);
        dateTime.setBounds(130, 215, 100, 40);
        add(dateTime);

        bthAddBook = new Button();
        bthAddBook.setText("Thêm vào đơn");
        bthAddBook.setFont(chuNho);
        bthAddBook.setBounds(450, 215, 200, 40);
        add(bthAddBook);
        
        tableOrder = new Table();
        scroll = new JScrollPane();
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tableOrder.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "STT", "Tên sản phẩm", "Số lượng", "Tổng tiền"
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
        tableOrder.setFont(chuNho);
        TableColumnModel columnModel = tableOrder.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(450);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(150);
        
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setResizable(false);
        }
        JTableHeader header = tableOrder.getTableHeader();
        header.setReorderingAllowed(false);
        header.setFont(chuNho);
        scroll.setViewportView(tableOrder);
        scroll.setBounds(0, 270, 800, 400);
        scroll.getViewport().setBackground(Color.WHITE);
        this.add(scroll);
        
        totalPrice = new JLabel("Tổng tiền:");
        totalPrice.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        totalPrice.setBounds(590, 675, 80, 30);
        add(totalPrice);
        
        jtfTotalPrice = new MyTextField();
        jtfTotalPrice.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        jtfTotalPrice.setText(0+"");
        jtfTotalPrice.setBounds(670, 675, 110, 30);
        add(jtfTotalPrice);
        
        btnAddOrder = new Button();
        btnAddOrder.setText("Tạo đơn hàng");
        btnAddOrder.setFont(chuNho);
        btnAddOrder.setBounds(160, 710, 160, 40);
        add(btnAddOrder);
        
        btnCancelOrder = new Button();
        btnCancelOrder.setText("Hủy đơn hàng");
        btnCancelOrder.setFont(chuNho);
        btnCancelOrder.setBounds(480, 710, 160, 40);
        add(btnCancelOrder);
        
        setVisible(true);
        this.repaint();
    }

    public static void main(String Arg[]) {
        OrderView one = new OrderView();
        JFrame jf = new JFrame();
        jf.setSize(800, 800);
        jf.setLayout(null);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(one);
        jf.setVisible(true);
    }

}
