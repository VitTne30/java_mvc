package Controller;

import Controller.AddCustomerController;
import Controller.BillController;
import Controller.MainController;
import DbConnection.DataConnection;
import Model.ModelBook;
import Model.ModelCustomer;
import Swing.Table;
import View.AddCustomerView;
import View.BillView;
import View.OrderView;
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

    public OrderController(OrderView ov) throws SQLException {
        this.OView = ov;
        db = DataConnection.getInstance();
        conn = (Connection) db.getConnection();
        table = OView.getTableOrder();
        getAllCustomer();
        getAllProduct();
        OView.getJtfIdOrder().setEditable(false);
        id_order = getId();
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

                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                String totalPrice = OView.getJtfTotalPrice().getText();

                String sql = "UPDATE `tbl_donhang` SET `ngaylap`='" + date + "',"
                        + "`tong_tien`='" + totalPrice + "' WHERE id_donhang = '" + id_order + "';";
                PreparedStatement stmt;
                try {
                    stmt = conn.prepareStatement(sql);

                    int affectefRow = stmt.executeUpdate();
                    if (affectefRow > 0) {
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
    }

    private void getAllProduct() throws SQLException {
        arrBook = new ArrayList<>();
        String sql = "SELECT `id_sanpham`, `ten_sanpham`, `gia_tien` FROM `tbl_sanpham`";
        ResultSet result;
        PreparedStatement stmt = conn.prepareStatement(sql);
        result = stmt.executeQuery();
        while (result.next()) {
            ModelBook temp = new ModelBook(result.getString("ten_sanpham"),
                    result.getInt("id_sanpham"), result.getInt("gia_tien"));
            arrBook.add(temp);
        }
        result.close();
        stmt.close();
    }

    private void getAllCustomer() throws SQLException {
        arrCus = new ArrayList<>();
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
        return -1;
    }

}
