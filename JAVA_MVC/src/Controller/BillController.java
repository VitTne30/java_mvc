package Controller;

import DbConnection.DataConnection;
import EasyXLS.Constants.DataType;
import EasyXLS.ExcelDocument;
import EasyXLS.ExcelTable;
import EasyXLS.ExcelWorksheet;
import Model.ModelBill;
import Model.ModelDetail;
import Swing.Table;
import View.BillView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class BillController {

    private DataConnection databaseConnection;
    private Connection con;
    private BillView billView;
    private Table tblBill, tblDetail;
    private ArrayList<ModelBill> listBill = new ArrayList<>();
    private ArrayList<ModelDetail> listDetail = new ArrayList<>();
    private DefaultTableModel model;
    private SimpleDateFormat simpleDate;

    public BillController(BillView newBill) throws SQLException {
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();
        this.billView = newBill;
        this.tblBill = billView.getTblBill();
        this.tblDetail = billView.getTblDetail();
        this.model = (DefaultTableModel) tblBill.getModel();
        //
        getData();
        billView.getNumberLb().setText("Tổng số đơn hàng: " + listBill.size());
        Thread clockThread = new Thread(() -> {
            while (true) {
                Date currentTime = new Date();
                simpleDate = new SimpleDateFormat("dd - MM - YYYY   HH:mm:ss");
                String time = simpleDate.format(currentTime);
                //
                billView.getDateLb().setText(time);
                //
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        clockThread.start();
        //
        tblBill.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tblBill.getSelectedRow();
                if (selectedRow != -1) {
                    int selectedMaDonHang = (int) tblBill.getValueAt(selectedRow, 0);
                    try {
                        getDetail(selectedMaDonHang);
                    } catch (SQLException ex) {
                        Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        ////Delete
        billView.getBtnRemove().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblBill.getSelectedRow() >= 0) {
                    int cusId = tblBill.getFirstCol_RowSelected(tblBill.getSelectedRow());
                    String name = String.valueOf(tblBill.getModel().getValueAt(tblBill.getSelectedRow(), 0));
                    int result = JOptionPane.showConfirmDialog(billView, "Bạn có chắc chắn muốn xóa đơn hàng số: " + name + " ?",
                            "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            String sql = "DELETE FROM tbl_donhang WHERE id_donhang = ?";
                            PreparedStatement p = con.prepareStatement(sql);
                            p.setInt(1, cusId);
                            p.execute();
                            //
                            String sql2 = "DELETE FROM tbl_chitietdonhang WHERE id_donhang = ?";
                            PreparedStatement p2 = con.prepareStatement(sql);
                            p2.setInt(1, cusId);
                            p2.execute();
                            p.close();
                            p2.close();
                            tblDetail.removeAllRow();
                            getData();
                            JOptionPane.showMessageDialog(tblBill, "Xóa đơn hàng thành công!",
                                    "Thông báo", JOptionPane.OK_OPTION);
                            
                            databaseConnection.releaseConnection(con);
                        } catch (SQLException ex) {
                            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(tblBill, "Vui lòng chọn đơn hàng!",
                            "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        ////
        billView.getBtnExcel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToExcel(tblBill);
            }
        });
        ///
        billView.getBtnSort().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
                tblBill.setRowSorter(sorter);

                // Sort by the second column (Name)
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(1, SortOrder.ASCENDING)));
                sorter.sort();
            }
        });
        ////
        billView.getBtnSortNum().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the TableRowSorter from the table
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
                tblBill.setRowSorter(sorter);

                // Sort by the third column (Age) using a Comparator for integers
                sorter.setComparator(2, new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return Integer.compare(o1, o2);
                    }
                });
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(3, SortOrder.ASCENDING)));
                sorter.sort();
            }
        });

    }

    private ArrayList<ModelBill> getListBill() throws SQLException {
        ArrayList<ModelBill> list = new ArrayList();
        String sql = "SELECT id_donhang, id_cus, "
                + "DATE_FORMAT(ngaylap, '%d-%m-%Y') AS Ngay, tong_tien FROM tbl_donhang;";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int id_bill = r.getInt(1);
            int id_cus = r.getInt(2);
            String date = r.getString(3);
            int money = r.getInt(4);
            ModelBill data = new ModelBill(id_bill, id_cus, date, money);
            list.add(data);
        }
        p.close();
        r.close();
        databaseConnection.releaseConnection(con);
        return list;
    }

    private void getData() throws SQLException {
        tblBill.removeAllRow();
        listBill = getListBill();
        String newCus;
        for (ModelBill data : listBill) {
            newCus = getNameCus(data.getIdCus());
            tblBill.addRow(new Object[]{data.getIdBill(), newCus,
                data.getDate(), data.getMoney()});
        }

    }

    private String getNameCus(int newId) throws SQLException {
        String nameCus = null;
        String sql = "SELECT hoten FROM tbl_khachhang WHERE id_cus = ?";

        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, newId);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            nameCus = r.getString(1);
        }
        p.close();
        r.close();
        databaseConnection.releaseConnection(con);
        return nameCus;
    }

    private String getNamePro(int newId) throws SQLException {
        String namePro = null;
        String sql = "SELECT ten_sanpham FROM tbl_sanpham WHERE id_sanpham = ?";

        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, newId);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            namePro = r.getString(1);
        }
        p.close();
        r.close();
        databaseConnection.releaseConnection(con);
        return namePro;
    }

    private ArrayList<ModelDetail> getListDetail(int idBill) throws SQLException {
        ArrayList<ModelDetail> list = new ArrayList();
        String sql = "SELECT id_chitiet, id_sanpham, "
                + "soluong, tong_tien FROM tbl_chitietdonhang WHERE id_donhang = ?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, idBill);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int id_detail = r.getInt(1);
            int id_pro = r.getInt(2);
            int num = r.getInt(3);
            int money = r.getInt(4);
            ModelDetail data = new ModelDetail(id_detail, idBill, id_pro, num, money);
            list.add(data);
        }
        p.close();
        r.close();
        databaseConnection.releaseConnection(con);
        return list;
    }

    private void getDetail(int idBill) throws SQLException {
        tblDetail.removeAllRow();
        tblDetail.removeAllRow();
        listDetail = getListDetail(idBill);
        String newPro;
        for (ModelDetail data : listDetail) {
            newPro = getNamePro(data.getIdPro());
            tblDetail.addRow(new Object[]{data.getIdDetail(), newPro,
                data.getNum(), data.getMoney()});
        }
    }

    private void exportToExcel(Table table) {
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
                workbook.easy_getSheetAt(0).setSheetName("Sheet1");
                ExcelTable xlsTable = ((ExcelWorksheet) workbook.easy_getSheetAt(0)).easy_getExcelTable();
                //Header
                DefaultTableModel model = (DefaultTableModel) table.getModel();
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

}
