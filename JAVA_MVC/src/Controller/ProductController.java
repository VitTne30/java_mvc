package Controller;

import DbConnection.DataConnection;
import EasyXLS.Constants.DataType;
import EasyXLS.ExcelDocument;
import EasyXLS.ExcelTable;
import EasyXLS.ExcelWorksheet;
import Model.ModelBook;
import Model.ModelCategory;
import Model.ModelPublisher;
import Swing.Table;
import View.ProductView;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public final class ProductController {

    private final DataConnection db;
    private final Connection conn;
    private final ProductView PView;
    private final Table table;
    private ArrayList<ModelBook> arrBook;
    private ArrayList<ModelPublisher> arrPublisher;
    private ArrayList<ModelCategory> arrCategory;
    private String state = "nothing";

    public ProductController(ProductView one) throws SQLException {
        this.arrBook = new ArrayList<>();
        db = DataConnection.getInstance();
        conn = (Connection) db.getConnection();
        this.PView = one;
        table = this.PView.getTable_product();
        this.arrBook = getListProduct();
        refreshTable();
        getDataForJComboBox();
        ableTextField(false);
        this.PView.getJtf_id().setEditable(false);
        this.PView.getJcb_category().setEditable(false);
        this.PView.getJcb_publisher().setEditable(false);

        this.PView.getAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "add";
                ableTextField(true);
                clearTextField();
            }
        });

        this.PView.getDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(PView, "Hãy chọn trường sản phẩm muốn xóa rồi chọn 'Lưu'!", "Xóa sản phẩm", JOptionPane.INFORMATION_MESSAGE);
                state = "delete";
            }
        });

        this.PView.getSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (state.equals("add")) {
                    try {

                        addAProduct();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (state.equals("change")) {
                    try {
                        changeProduct();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (state.equals("delete")) {
                    try {
                        deleteProduct();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        this.PView.getChange().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "change";
                ableTextField(Boolean.TRUE);
            }
        });

        this.PView.getTable_product().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                if (state.equals("change")) {
                    PView.getJtf_author().setText(arrBook.get(i).getAuthor());
                    PView.getJtf_name().setText(arrBook.get(i).getName());
                    PView.getJtf_number().setText(Integer.toString(arrBook.get(i).getNumber()));
                    PView.getJtf_id().setText(Integer.toString(arrBook.get(i).getId()));
                    PView.getJtf_price().setText(Integer.toString(arrBook.get(i).getPrice()));
                    PView.getJcb_category().setSelectedIndex(idCategoryWithCategory(arrBook.get(i).getCategory()));
                    PView.getJcb_publisher().setSelectedIndex(idPublisherWithPublisher(arrBook.get(i).getPublisher()));
                }
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
            }

        });
        PView.getJtf_search().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (PView.getJtf_search().getText().equals("")) {
                    try {
                        arrBook = getListProduct();
                        refreshTable();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        });

        this.PView.getSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = PView.getJtf_search().getText();
                if (!word.equals("")) {
                    try {
                        arrBook = getListProductWithWords(word);
                        refreshTable();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    showMessengerError("Vui lòng nhập từ khóa để tìm kiếm!");
                }
            }

        });

        this.PView.getPut_excel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(PView, "Bạn có chắc chắn muốn xuất bảng dữ liệu này không?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
                if (response == JOptionPane.OK_OPTION) {
                    exportToExcel(table);
                } else {
                    state = "nothing";
                }
            }

        });
    }

    //xoa san pham
    private void deleteProduct() throws SQLException {
        if (state.equals("delete")) {
            int seletedIndex = table.getSelectedRow();
            int id = arrBook.get(seletedIndex).getId();
            String sql = "DELETE FROM `tbl_sanpham` WHERE id_sanpham = " + id + ";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            int response = JOptionPane.showConfirmDialog(PView, "Bạn có chắc chắn muốn xóa trường này không?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
            if (response == JOptionPane.OK_OPTION) {
                int rowAffect = stmt.executeUpdate();
                if (rowAffect > 0) {
                    arrBook.remove(seletedIndex);
                    refreshTable();
                    JOptionPane.showMessageDialog(PView, "Xóa thành công sản phẩm!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    showMessengerError("Xóa sản phẩm không thành công!");
                }
            } else {
                state = "Nothing";
            }
            stmt.close();
            db.releaseConnection(conn);
        }
    }

    private void refreshTable() {
        table.removeAllRow();
        for (ModelBook temp : arrBook) {
            table.addRow(new Object[]{
                temp.getId(), temp.getName(), temp.getCategory(), temp.getAuthor(),
                temp.getNumber(), temp.getPrice(), temp.getPublisher()
            });
        }
    }

    private void showMessengerError(String error) {
        JOptionPane.showMessageDialog(PView,
                error, "Thông báo",
                JOptionPane.ERROR_MESSAGE);
    }

    private boolean checkTextField() {
        if (this.PView.getJtf_name().getText().equals("")) {
            showMessengerError("Vui lòng nhập lại tên sản phẩm!");
            return false;
        } else if (this.PView.getJtf_author().getText().equals("")) {
            showMessengerError("Vui lòng nhập lại tên tác giả!");
            return false;
        } else if (this.PView.getJtf_number().getText().equals("")) {
            showMessengerError("Vui lòng nhập lại số lượng!");
            return false;
        } else if (this.PView.getJtf_price().getText().equals("")) {
            showMessengerError("Vui lòng nhập lại giá tiền!");
            return false;
        }
        return true;
    }

    // them san pham
    private void addAProduct() throws SQLException {
        if (checkTextField()) {
            String name = PView.getJtf_name().getText();
            int number = Integer.parseInt(PView.getJtf_number().getText());
            int price = Integer.parseInt(PView.getJtf_price().getText());
            String category = PView.getJcb_category().getSelectedItem().toString();
            String publisher = PView.getJcb_publisher().getSelectedItem().toString();
            String author = PView.getJtf_author().getText();
            int id_category = idCategory();
            int id_publisher = idPublisher();
            int id = 0;
            if (arrBook.size() > 1) {
                id = arrBook.get(arrBook.size() - 1).getId() + 1;
            }
            String sql = "INSERT INTO `tbl_sanpham`(`id_sanpham`,`ten_sanpham`, "
                    + "`id_danhmuc`, `id_nxb`, `so_luong`, `gia_tien`,"
                    + " `tac_gia`) VALUES ('" + id + "','" + name + "','" + id_category + "',"
                    + "'" + id_publisher + "','" + number + "','" + price + "','" + author + "')";

            PreparedStatement preStmt = conn.prepareStatement(sql);
            int rowAffected = preStmt.executeUpdate();
            if (rowAffected > 0) {
                ModelBook temp = new ModelBook(name, author, id, category, number, price, publisher);
                arrBook.add(temp);
                table.addRow(new Object[]{
                    temp.getId(), temp.getName(), temp.getCategory(), temp.getAuthor(),
                    temp.getNumber(), temp.getPrice(), temp.getPublisher()
                });

                JOptionPane.showMessageDialog(PView, "Thêm sản phẩm thành công!",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                clearTextField();
            } else {
                showMessengerError("Thêm sản phẩm không thành công!");
            }
            db.releaseConnection(conn);
            preStmt.close();
        }
    }

    // update thong tin san pham
    private void changeProduct() throws SQLException {
        if (checkTextField()) {
            int selectedIndex = table.getSelectedRow();
            String name = PView.getJtf_name().getText();
            int number = Integer.parseInt(PView.getJtf_number().getText());
            int price = Integer.parseInt(PView.getJtf_price().getText());
            String category = PView.getJcb_category().getSelectedItem().toString();
            String publisher = PView.getJcb_publisher().getSelectedItem().toString();
            String author = PView.getJtf_author().getText();
            int id_category = idCategory();
            int id_publisher = idPublisher();
            int id = Integer.parseInt(PView.getJtf_id().getText());
            String sql = "UPDATE `tbl_sanpham` SET "
                    + "`ten_sanpham`='" + name + "',`id_danhmuc`='" + id_category + "',"
                    + "`id_nxb`='" + id_publisher + "',`so_luong`='" + number + "',"
                    + "`gia_tien`='" + price + "',`tac_gia`='" + author + "'"
                    + " WHERE id_sanpham = " + id + ";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            int rowAffect = stmt.executeUpdate();
            if (rowAffect > 0) {
                ModelBook temp = new ModelBook(name, author, id, category, number, price, publisher);
                arrBook.remove(selectedIndex);
                arrBook.add(selectedIndex, temp);
                refreshTable();
                JOptionPane.showMessageDialog(PView, "Thay đổi thông tin sản phẩm thành công!",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                clearTextField();
            } else {
                showMessengerError("Thay đổi thông tin sản phẩm không thành công");
            }
            stmt.close();
            db.releaseConnection(conn);
        }
    }

    // lay du lieu cho bang 
    private ArrayList<ModelBook> getListProduct() throws SQLException {
        ArrayList<ModelBook> list = new ArrayList();
        String sql = "SELECT `id_sanpham`, `ten_sanpham`, `so_luong`, `gia_tien`, `tac_gia`,"
                + " tbl_nxb.tennxb, tbl_danhmuc.tendanhmuc FROM `tbl_sanpham`"
                + " INNER JOIN tbl_nxb ON tbl_sanpham.id_nxb = tbl_nxb.id_nxb"
                + " INNER JOIN tbl_danhmuc ON tbl_sanpham.id_danhmuc = tbl_danhmuc.id_danhmuc;";
        ResultSet result;
        try (PreparedStatement preStmt = conn.prepareStatement(sql)) {
            result = preStmt.executeQuery();
            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                int so_luong = result.getInt(3);
                int gia_tien = result.getInt(4);
                String tac_gia = result.getString(5);
                String nha_xb = result.getString(6);
                String danh_muc = result.getString(7);
                ModelBook data = new ModelBook(name, tac_gia, id,
                        danh_muc, so_luong, gia_tien, nha_xb);
                list.add(data);
            }
        }
        result.close();
        db.releaseConnection(conn);
        return list;
    }

    //searching
    private ArrayList<ModelBook> getListProductWithWords(String word) throws SQLException {
        ArrayList<ModelBook> list = new ArrayList();
        String sql = "SELECT `id_sanpham`, `ten_sanpham`, `so_luong`, `gia_tien`, `tac_gia`,"
                + " tbl_nxb.tennxb, tbl_danhmuc.tendanhmuc FROM `tbl_sanpham`"
                + " INNER JOIN tbl_nxb ON tbl_sanpham.id_nxb = tbl_nxb.id_nxb"
                + " INNER JOIN tbl_danhmuc ON tbl_sanpham.id_danhmuc = tbl_danhmuc.id_danhmuc"
                + " WHERE ten_sanpham LIKE '%" + word + "%' OR tac_gia LIKE '%" + word + "%' "
                + "OR so_luong LIKE '%" + word + "%' OR gia_tien LIKE '%" + word + "%' "
                + "OR tbl_nxb.tennxb LIKE '%" + word + "%' "
                + "OR tbl_danhmuc.tendanhmuc LIKE '%" + word + "%';";
        ResultSet result;
        try (PreparedStatement preStmt = conn.prepareStatement(sql)) {
            result = preStmt.executeQuery();
            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                int so_luong = result.getInt(3);
                int gia_tien = result.getInt(4);
                String tac_gia = result.getString(5);
                String nha_xb = result.getString(6);
                String danh_muc = result.getString(7);
                ModelBook data = new ModelBook(name, tac_gia, id,
                        danh_muc, so_luong, gia_tien, nha_xb);
                list.add(data);
            }
        }
        result.close();
        db.releaseConnection(conn);
        return list;
    }

    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void exportToExcel(JTable t) {
        JFileChooser choose = new JFileChooser();
        choose.setDialogTitle("Lưu Excel");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
        choose.setFileFilter(filter);

        int select = choose.showSaveDialog(null);

        if (select == JFileChooser.APPROVE_OPTION) {
            File fileSave = choose.getSelectedFile();
            String path = fileSave.getAbsolutePath();
            if (!path.endsWith(".xlsx")) {
                path += ".xlsx";
                fileSave = new File(path);
            }
//            
            try {
                ExcelDocument workbook = new ExcelDocument(1);
                workbook.easy_getSheetAt(0).setSheetName("Danh sách Hóa Đơn");
                ExcelTable xlsTable = ((ExcelWorksheet) workbook.easy_getSheetAt(0)).easy_getExcelTable();
                //Header
                DefaultTableModel model = (DefaultTableModel) t.getModel();
                for (int col = 0; col < model.getColumnCount(); col++) {
                    xlsTable.easy_getCell(0, col).setValue(model.getColumnName(col));
                    xlsTable.easy_getCell(0, col).setDataType(DataType.STRING);
                    xlsTable.easy_getCell(0, col).setBold(true);
                }
                //Data
                for (int row = 0; row < model.getRowCount(); row++) {
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        xlsTable.easy_getCell(row + 1, col).setValue(model.getValueAt(row, col) + "");
                        xlsTable.easy_getCell(row + 1, col).setDataType(DataType.STRING);
                    }
                }
                workbook.easy_WriteXLSXFile(fileSave.toString());
                workbook.Dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "File saved successfully!");
        }
    }

    //lay du lieu cho 2 combo box
    private void getDataForJComboBox() throws SQLException {
        arrCategory = new ArrayList<>();
        arrPublisher = new ArrayList<>();
        String sql = "SELECT `id_danhmuc`, `tendanhmuc` FROM `tbl_danhmuc`";
        ResultSet one;
        try (PreparedStatement preStmt = conn.prepareStatement(sql)) {
            one = preStmt.executeQuery();
//            ResultSet one = takeResult("danhmuc");
            while (one.next()) {
                int id = one.getInt(1);
                String name = one.getString(2);
                ModelCategory temp = new ModelCategory(id, name);
                arrCategory.add(temp);
                this.PView.getJcb_category().addItem(name);
            }
            preStmt.close();
        }
        sql = "SELECT `id_nxb`, `tennxb` FROM `tbl_nxb`";

        try (PreparedStatement preStmt = conn.prepareStatement(sql)) {
            one = preStmt.executeQuery();
//            ResultSet one = takeResult("danhmuc");
            while (one.next()) {
                int id = one.getInt(1);
                String name = one.getString(2);
                ModelPublisher temp = new ModelPublisher(id, name);
                arrPublisher.add(temp);
                this.PView.getJcb_publisher().addItem(name);
            }
            preStmt.close();
        }
        db.releaseConnection(conn);        
        one.close();
    }

    private int idCategory() {
        int id = 0;
        for (int i = 0; i < arrCategory.size(); i++) {
            if (this.PView.getJcb_category().getSelectedItem().toString()
                    .equals(arrCategory.get(i).getName_category())) {
                id = arrCategory.get(i).getId_category();
            }
        }
        return id;
    }

    private int idPublisher() {
        int id = 0;
        for (int i = 0; i < arrPublisher.size(); i++) {
            if (this.PView.getJcb_publisher().getSelectedItem().toString()
                    .equals(arrPublisher.get(i).getName_publisher())) {
                id = arrPublisher.get(i).getId_publisher();
            }
        }
        return id;
    }

    private int idCategoryWithCategory(String category) {
        int id = 0;
        for (int i = 0; i < arrCategory.size(); i++) {
            if (arrCategory.get(i).getName_category().equals(category)) {
                id = i;
            }
        }
        return id;
    }

    private int idPublisherWithPublisher(String publisher) {
        int id = 0;
        for (int i = 0; i < arrPublisher.size(); i++) {
            if (arrPublisher.get(i).getName_publisher().equals(publisher)) {
                id = i;
            }
        }
        return id;
    }

    private void ableTextField(Boolean stat) {
        this.PView.getJtf_author().setEditable(stat);
        this.PView.getJtf_name().setEditable(stat);
        this.PView.getJtf_price().setEditable(stat);
        this.PView.getJtf_number().setEditable(stat);
    }

    private void clearTextField() {
        this.PView.getJtf_id().setText("");
        this.PView.getJtf_author().setText("");
        this.PView.getJtf_name().setText("");
        this.PView.getJtf_price().setText("");
        this.PView.getJtf_number().setText("");
    }

}
