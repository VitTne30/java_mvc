package View;

import Swing.Button;
import Swing.MyTextField;
import Swing.Table;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class OrderView extends JPanel {

    private JLabel title, idOrder, customer, product, number, lbDateTime, dateTime, totalPrice, noticeCus, noticeProduct, printPDf;
    private Button btnAddBook, btnAddOrder, btnCancelOrder, addNewCustomer;
    private Table tableOrder;
    private JScrollPane scroll;
    private MyTextField jtfIdOrder, jtfCustomer, jtfNumber, jtfPrice, jtfTotalPrice, jtfProduct;
    private MainView two;
    private JCheckBox jcPrintPDF;

    public OrderView(MainView one) {
        this.two = one;
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

        noticeCus = new JLabel("");
        noticeCus.setFont(new Font("Time news roman", Font.CENTER_BASELINE, 14));
        noticeCus.setBounds(430, 108, 200, 20);
        noticeCus.setForeground(Color.red);
        add(noticeCus);

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

        noticeProduct = new JLabel("");
        noticeProduct.setFont(new Font("Time news roman", Font.CENTER_BASELINE, 14));
        noticeProduct.setBounds(130, 175, 200, 20);
        noticeProduct.setForeground(Color.red);
        add(noticeProduct);

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
        dateTime.setBounds(130, 215, 250, 40);
        add(dateTime);

        btnAddBook = new Button();
        btnAddBook.setText("Thêm vào đơn");
        btnAddBook.setFont(chuNho);
        btnAddBook.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/add_book.png")));
        btnAddBook.setBounds(450, 215, 250, 40);
        add(btnAddBook);

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
        jtfTotalPrice.setText(0 + "");
        jtfTotalPrice.setBounds(670, 675, 110, 30);
        add(jtfTotalPrice);

        printPDf = new JLabel("Xuất PDF:");
        printPDf.setFont(chuNho);
        printPDf.setBounds(10, 740, 120, 40);
        add(printPDf);

        jcPrintPDF = new JCheckBox();
        jcPrintPDF.setBounds(140, 740, 50, 40);
        add(jcPrintPDF);

        btnAddOrder = new Button();
        btnAddOrder.setText("Tạo đơn hàng");
        btnAddOrder.setFont(chuNho);
        btnAddOrder.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/add_order.png")));
        btnAddOrder.setBounds(240, 740, 250, 40);
        add(btnAddOrder);

        btnCancelOrder = new Button();
        btnCancelOrder.setText("Hủy đơn hàng");
        btnCancelOrder.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/delete_bill.png")));
        btnCancelOrder.setFont(chuNho);
        btnCancelOrder.setBounds(520, 740, 250, 40);
        add(btnCancelOrder);

        setVisible(true);
        this.repaint();
    }

    public MainView getTwo() {
        return two;
    }

    public void setTwo(MainView two) {
        this.two = two;
    }

    public JCheckBox getJcPrintPDF() {
        return jcPrintPDF;
    }

    public JLabel getDateTime() {
        return dateTime;
    }

    public MyTextField getJtfTotalPrice() {
        return jtfTotalPrice;
    }

    public JLabel getNoticeProduct() {
        return noticeProduct;
    }

    public Button getBthAddBook() {
        return btnAddBook;
    }

    public Button getBtnAddOrder() {
        return btnAddOrder;
    }

    public Button getBtnCancelOrder() {
        return btnCancelOrder;
    }

    public Button getAddNewCustomer() {
        return addNewCustomer;
    }

    public Table getTableOrder() {
        return tableOrder;
    }

    public JScrollPane getScroll() {
        return scroll;
    }

    public MyTextField getJtfIdOrder() {
        return jtfIdOrder;
    }

    public MyTextField getJtfCustomer() {
        return jtfCustomer;
    }

    public MyTextField getJtfProduct() {
        return jtfProduct;
    }

    public JLabel getNoticeCus() {
        return noticeCus;
    }

    public MyTextField getJtfNumber() {
        return jtfNumber;
    }

    public MyTextField getJtfPrice() {
        return jtfPrice;
    }
}
