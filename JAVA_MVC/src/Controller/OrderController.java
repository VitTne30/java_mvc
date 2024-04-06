package Controller;

import DbConnection.DataConnection;
import Model.ModelBook;
import Model.ModelCustomer;
import Swing.Table;
import View.AddCustomerView;
import View.BillView;
import View.OrderView;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.Paper;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.ImageIcon;


public class OrderController {

    private final DataConnection db;
    private final Connection conn;
    private final Table table;
    private final OrderView OView;
    private ArrayList<ModelBook> arrBook;
    private ArrayList<ModelCustomer> arrCus;
    private int id_order;
    private int index_cus;
    private int index_book;
    private int stt = 1;
    private boolean check_product = false;
    private boolean check_cus = false;
    private SimpleDateFormat simpleDate;
    private boolean checkClickProduct = false;
    private boolean checkClickBook = false;
    private ArrayList<ModelBook> detailOrder;
    private double bHeight = 0.0;

    public OrderController(OrderView ov) throws SQLException {
        this.OView = ov;
        db = DataConnection.getInstance();
        conn = (Connection) db.getConnection();
        table = OView.getTableOrder();
        OView.getJtfIdOrder().setEditable(false);
        id_order = getId();
        detailOrder = new ArrayList<>();
        arrCus = new ArrayList<>();
        arrBook = new ArrayList<>();
        getAllCustomer();
        getAllProduct();
        OView.getJtfIdOrder().setText(id_order + "");

        OView.getJtfCustomer().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                checkClickProduct = true;
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!check_cus && checkClickProduct) {
                    OView.getNoticeCus().setText("Không tìm thấy khách hàng!");
                    OView.getAddNewCustomer().setText("Thêm mới");
                    OView.getJtfCustomer().setText("");
                }
            }
        });

        OView.getJtfNumber().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String text = OView.getJtfNumber().getText();
                if (!Character.isDigit(c)) {
                    e.consume();
                    return;
                }
                if (text.length() >= 10) {
                    e.consume();
                }
            }
        });
        OView.getJtfCustomer().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                OView.getAddNewCustomer().setText("Thêm");
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                String key_word = OView.getJtfCustomer().getText();
                if (checkCustoomer(key_word)) {
                    OView.getJtfCustomer().setText(arrCus.get(index_cus).getName());
                    check_cus = true;
                    OView.getNoticeCus().setText("");
                } else {
                    check_cus = false;
                }
                if (key_word.equals("")) {
                    OView.getAddNewCustomer().setText("Thêm mới");
                }
            }
        });

        OView.getAddNewCustomer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String button = OView.getAddNewCustomer().getText();
                if (button.equals("Thêm")) {
                    try {
                        updateCus(index_cus);
                    } catch (SQLException ex) {
                        Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (button.equals("Thêm mới")) {
                    try {
                        AddCustomerView one = new AddCustomerView();
                        AddCustomerController two = new AddCustomerController(one, OView, id_order);
                        //bug

                    } catch (SQLException ex) {
                        Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        OView.getJtfProduct().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                checkClickBook = true;
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (check_product == false && checkClickBook) {
                    OView.getNoticeProduct().setText("Không tìm thấy sản phẩm!");
                    OView.getJtfProduct().setText("");
                    OView.getJtfProduct().grabFocus();
                }
            }
        });
        OView.getJtfProduct().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String keyword = OView.getJtfProduct().getText();
                if (checkProduct(keyword)) {
                    OView.getJtfProduct().setText(arrBook.get(index_book).getName());
                    check_product = true;
                    OView.getNoticeProduct().setText("");

                } else {
                    check_product = false;
                }

            }
        });

        OView.getJtfNumber().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (!OView.getJtfProduct().getText().equals("")) {
                    int temp_price = Integer.parseInt(OView.getJtfNumber().getText())
                            * arrBook.get(index_book).getPrice();
                    OView.getJtfPrice().setText(temp_price + "");
                }

                if (!OView.getJtfNumber().getText().equals("")) {
                    int soLuong = Integer.parseInt(OView.getJtfNumber().getText());
                    int soLuongDangCo = arrBook.get(index_book).getNumber();
                    if (soLuong > soLuongDangCo) {
                        JOptionPane.showMessageDialog(OView,
                                "Số lượng sách đang có là " + soLuongDangCo + ". Vui lòng nhập số bé hơn hoặc bằng! ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        OView.getJtfNumber().setText("");
                    }
                }
            }
        });

        OView.getBtnCancelOrder().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "DELETE FROM `tbl_donhang` WHERE id_donhang = '" + id_order + "'";
                try {
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    int affectedRow = stmt.executeUpdate();
                    if (affectedRow > 0) {

                        String s = "DELETE FROM `tbl_chitietdonhang` WHERE id_donhang = '" + id_order + "';";
                        stmt = conn.prepareStatement(s);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(OView, "Hủy đơn hàng thành công!",
                                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        db.releaseConnection(conn);
                        OView.getTwo().getChangePanel().removeAll();
                        BillView billView = new BillView();
                        try {
                            BillController billSer = new BillController(billView);
                        } catch (SQLException ex) {
                            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        OView.getTwo().getChangePanel().add(billView);
                        OView.getTwo().getChangePanel().revalidate();
                        OView.getTwo().getChangePanel().repaint();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        OView.getBthAddBook().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int number = Integer.parseInt(OView.getJtfNumber().getText());
                int price = Integer.parseInt(OView.getJtfPrice().getText());
                String sql = "INSERT INTO `tbl_chitietdonhang`(`id_donhang`, `id_sanpham`, `soluong`, `tong_tien`) "
                        + "VALUES ('" + id_order + "','" + arrBook.get(index_book).getId() + "',"
                        + "'" + number + "','" + price + "')";
                ModelBook temp = new ModelBook(arrBook.get(index_book).getName(), arrBook.get(index_book).getId(), number, price);
                detailOrder.add(temp);
                PreparedStatement stmt;
                try {
                    stmt = conn.prepareStatement(sql);
                    int affectedRow = stmt.executeUpdate();
                    if (affectedRow > 0) {
                        stmt.close();
                        table.addRow(new Object[]{
                            stt, arrBook.get(index_book).getName(), number, price
                        });
                        int tong = Integer.parseInt(OView.getJtfTotalPrice().getText());
                        tong = tong + Integer.parseInt(OView.getJtfPrice().getText());
                        OView.getJtfTotalPrice().setText(tong + "");
                        OView.getJtfProduct().setText("");
                        OView.getJtfPrice().setText("");
                        OView.getJtfNumber().setText("");
                        getAllProduct();
                        db.releaseConnection(conn);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                }
                stt++;
            }
        });

        OView.getBtnAddOrder().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(OView, "Vui lòng thêm 1 sản phẩm vào đơn hàng!",
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    long millis = System.currentTimeMillis();
                    java.sql.Date date = new java.sql.Date(millis);
                    String totalPrice = OView.getJtfTotalPrice().getText();
                    if (OView.getJcPrintPDF().isSelected()) {
                        print();
                    }
                    String sql = "UPDATE `tbl_donhang` SET `ngaylap`='" + date + "',"
                            + "`tong_tien`='" + totalPrice + "' WHERE id_donhang = '" + id_order + "';";

                    PreparedStatement stmt;
                    try {
                        stmt = conn.prepareStatement(sql);
                        updateSoLuong();
                        int affectefRow = stmt.executeUpdate();
                        if (affectefRow > 0) {
                            db.releaseConnection(conn);
                            OView.getTwo().getChangePanel().removeAll();
                            BillView billView = new BillView();
                            try {
                                BillController billSer = new BillController(billView);
                            } catch (SQLException ex) {
                                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            OView.getTwo().getChangePanel().add(billView);
                            OView.getTwo().getChangePanel().revalidate();
                            OView.getTwo().getChangePanel().repaint();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });
        Thread clockThread = new Thread(() -> {
            while (true) {
                Date currentTime = new Date();
                simpleDate = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
                String time = simpleDate.format(currentTime);
                //
                OView.getDateTime().setText(time);
                //
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        clockThread.start();

    }

    private void updateSoLuong() throws SQLException {

        PreparedStatement stmt = null;
        int soluong = 0;
        for (int i = 0; i < detailOrder.size(); i++) {
            soluong = getSoLuong(detailOrder.get(i).getId()) - detailOrder.get(i).getNumber();
            String sql = "UPDATE `tbl_sanpham` SET `so_luong`='" + soluong + "'"
                    + " WHERE `id_sanpham`='" + detailOrder.get(i).getId() + "';";
            stmt = conn.prepareStatement(sql);
            stmt.execute();
        }
        stmt.close();
        db.releaseConnection(conn);
    }

    private int getSoLuong(int id) {
        for (int i = 0; i < arrBook.size(); i++) {
            if (id == arrBook.get(i).getId()) {
                return arrBook.get(i).getNumber();
            }
        }
        return -1;
    }

    private boolean checkProduct(String book) {
        for (int i = 0; i < arrBook.size(); i++) {
            if (book.equalsIgnoreCase(arrBook.get(i).getName())) {
                index_book = i;
                return true;
            }
        }
        return false;
    }

    private boolean checkCustoomer(String customer) {
        for (int i = 0; i < arrCus.size(); i++) {
            if (customer.equalsIgnoreCase(arrCus.get(i).getName())) {
                index_cus = i;
                return true;
            }
        }
        return false;
    }

    private boolean isNumber(String s) {
        String reg = "^\\d+$";
        boolean kt = s.matches(reg);
        return kt;
    }

    private void updateCus(int index) throws SQLException {
        String sql = "UPDATE `tbl_donhang` SET `id_cus`='" + arrCus.get(index).getId() + "' WHERE id_donhang = '" + id_order + "';";
        PreparedStatement stmt = conn.prepareStatement(sql);
        int affectedRow = stmt.executeUpdate();
        if (affectedRow > 0) {
            JOptionPane.showMessageDialog(OView, "Cập nhập đơn hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(OView, "Cập nhập đơn hàng không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        stmt.close();
        db.releaseConnection(conn);
    }

    private void getAllProduct() throws SQLException {
        arrBook.clear();
        String sql = "SELECT `id_sanpham`, `ten_sanpham`, `gia_tien`,`so_luong` FROM `tbl_sanpham`";
        ResultSet result;
        PreparedStatement stmt = conn.prepareStatement(sql);
        result = stmt.executeQuery();
        while (result.next()) {
            ModelBook temp = new ModelBook(result.getString("ten_sanpham"),
                    result.getInt("id_sanpham"), result.getInt("so_luong"), result.getInt("gia_tien"));
            arrBook.add(temp);
        }
        for (int i = 0; i < arrBook.size(); i++) {
            if (arrBook.get(i).getNumber() == 0) {
                arrBook.remove(i);
            }
        }
        result.close();
        stmt.close();
        db.releaseConnection(conn);
    }

    private void getAllCustomer() throws SQLException {
        arrCus.clear();
        String sql = "SELECT id_cus, hoten FROM tbl_khachhang;";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            ModelCustomer temp = new ModelCustomer(result.getInt("id_cus"),
                    result.getString("hoten"));
            arrCus.add(temp);
        }
        result.close();
        stmt.close();
        db.releaseConnection(conn);
    }

    private int getId() throws SQLException {
        int id = 1;
        ArrayList<Integer> arr = new ArrayList<>();
        String sql = "SELECT id_donhang FROM tbl_donhang";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            arr.add(result.getInt("id_donhang"));

        }
        if (!arr.isEmpty()) {
            id = arr.get(arr.size() - 1) + 1;
        }
        sql = "INSERT INTO `tbl_donhang`(`id_donhang`)"
                + " VALUES ('" + id + "')";
        stmt = conn.prepareStatement(sql);
        int affectedRow = stmt.executeUpdate();
        if (affectedRow > 0) {
            return id;
        }
        stmt.close();
        result.close();
        db.releaseConnection(conn);
        return -1;
    }

    private void print() {
        bHeight = Double.valueOf(detailOrder.size());
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(), pageFormat(pj));
        try {
            pj.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

    private PageFormat pageFormat(PrinterJob pj) {
        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double bodyHeight = bHeight;

        double witdh = cm_to_pp(12);
        double height = cm_to_pp(bodyHeight + 21);
        paper.setSize(witdh, height);
        paper.setImageableArea(0, 10, witdh, height - cm_to_pp(1));

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    private static double cm_to_pp(double cm) {
        return toPPI(cm * 0.393600787);
    }

    private static double toPPI(double inch) {
        return inch * 72d;
    }

    public class BillPrintable implements Printable {

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            int r = detailOrder.size();
            ImageIcon icon = new ImageIcon(getClass().getResource("/Icon/bookstore.png"));
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {
                Graphics2D g2d = (Graphics2D) graphics;
                double witdh = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

                try {
                    int y = 30;
                    int yShift = 20;
                    int headerRectHeight = 30;
                    int dot = 40;
                    int dot2 = 18;
                    g2d.setFont(new Font("Arial", Font.BOLD, 14));
                    g2d.drawImage(icon.getImage(), 160, 20, 60, 60, OView);
                    y += yShift + 50;
                    g2d.drawString("-----------------------------------------------------------", dot, y);
                    y += yShift;
                    g2d.drawString("                         Book Store                        ", dot, y);
                    y += yShift;
                    g2d.drawString("  No." + id_order + "                                             ", dot, y);
                    y += yShift;
                    g2d.drawString("  Address 02 Ho Chi Minh                                   ", dot, y);
                    y += yShift;
                    g2d.drawString("  Address 01 Ha Noi                                                  ", dot, y);
                    y += yShift;
                    g2d.drawString("-----------------------------------------------------------", dot, y);
                    y += headerRectHeight;

                    g2d.drawString("  Item                                              Price  ", dot, y);
                    y += yShift;
                    g2d.drawString("-----------------------------------------------------------", dot, y);
                    y += headerRectHeight;
                    for (int i = 0; i < detailOrder.size(); i++) {
                        g2d.drawString("  " + detailOrder.get(i).getName() + "                                  ", dot, y);
                        y += yShift;
                        g2d.drawString("  Quantity: " + detailOrder.get(i).getNumber() + "                           " + detailOrder.get(i).getPrice() + " VND", dot, y);
                        y += yShift;
                    }

                    g2d.drawString("-----------------------------------------------------------", dot, y);
                    y += yShift;
                    g2d.drawString("  Total price:                          " + OView.getJtfTotalPrice().getText() + " VND", dot, y);
                    y += yShift;
                    g2d.drawString("-----------------------------------------------------------", dot, y);
                    y += yShift;
                    g2d.drawString("  Discount:                                           0.0 %", dot, y);
                    y += yShift;
                    g2d.drawString("-----------------------------------------------------------", dot, y);
                    y += yShift;
                    g2d.drawString("  Price:                                    " + OView.getJtfTotalPrice().getText() + " VND", dot, y);
                    y += yShift;

                    g2d.drawString("-----------------------------------------------------------", dot, y);
                    y += yShift;
                    g2d.drawString("            Thank you come agian!!                         ", dot, y);
                    y += yShift;
                    g2d.drawString("-----------------------------------------------------------", dot, y);
                    y += yShift;
                    g2d.drawString("            Have a good day!!                              ", dot, y);
                    y += yShift;
                    g2d.drawString("-----------------------------------------------------------", dot, y);
                    y += yShift;
                    g2d.drawString("            Contact: 012345789                             ", dot, y);
                    y += yShift;

                    result = PAGE_EXISTS;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return result;
        }

    }
}
