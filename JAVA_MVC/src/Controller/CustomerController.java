package Controller;

import DbConnection.DataConnection;
import EasyXLS.Constants.DataType;
import EasyXLS.ExcelDocument;
import EasyXLS.ExcelTable;
import EasyXLS.ExcelWorksheet;
import Model.ModelCustomer;
import Swing.Table;
import View.CustomerView;
import View.InsertModifyView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class CustomerController {

    private DataConnection databaseConnection;
    private Connection con;
    private CustomerView cusView;
    private Table tableNv;
    private ArrayList<ModelCustomer> listCus = new ArrayList<>();

    public CustomerController(CustomerView newMS) throws SQLException {
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();
        this.cusView = newMS;
        tableNv = cusView.getTblCus();
        //GetSTaffData
        getData();
        ////
        cusView.getNumberLb().setText("Tổng số khách hàng đăng ký: " + listCus.size());
        ////Search
        cusView.getSearchField().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableNv.removeAllRow();
                String search = cusView.getSearchField().getText();
                for (ModelCustomer data : listCus) {
                    if (data.getName().toLowerCase().contains(search.toLowerCase())) {
                        tableNv.addRow(new Object[]{data.getId(), data.getName(),
                            data.getPhone(), data.getEmail()});
                    }
                }
            }
        });
        ////Insert
        cusView.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    insertComponent();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        ////Modify
        cusView.getBtnModify().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (tableNv.getSelectedRow() >= 0) {
                        int cusId = tableNv.getFirstCol_RowSelected(tableNv.getSelectedRow());
                        modifyComponent(cusId);
                    } else {
                        JOptionPane.showMessageDialog(tableNv, "Vui lòng chọn khách hàng!",
                                "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        ////Delete
        cusView.getBtnRemove().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableNv.getSelectedRow() >= 0) {
                    int cusId = tableNv.getFirstCol_RowSelected(tableNv.getSelectedRow());
                    String name = String.valueOf(tableNv.getModel().getValueAt(tableNv.getSelectedRow(), 1));
                    int result = JOptionPane.showConfirmDialog(cusView, "Bạn có chắc chắn muốn xóa khách hàng: " + name + " ?",
                            "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            String sql = "DELETE FROM tbl_khachhang WHERE id_cus = ?";
                            PreparedStatement p = con.prepareStatement(sql);
                            p.setInt(1, cusId);
                            p.execute();
                            p.close();
                            System.out.println("Xóa nhân viên thành công");
                            getData();
                            databaseConnection.releaseConnection(con);
                        } catch (SQLException ex) {
                            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(tableNv, "Vui lòng chọn khách hàng!",
                            "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        ////Excel
        cusView.getBtnExcel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToExcel(tableNv);
            }
        });
        //Button Import 
        cusView.getBtnImport().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    importExcel(tableNv);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    private ArrayList<ModelCustomer> getListCus() throws SQLException {
        ArrayList<ModelCustomer> list = new ArrayList();
        String sql = "SELECT id_cus, hoten, sdt, email FROM tbl_khachhang;";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int id = r.getInt(1);
            String name = r.getString(2);
            String phone = r.getString(3);
            String email = r.getString(4);
            ModelCustomer data = new ModelCustomer(id, name, phone, email);
            list.add(data);
        }
        p.close();
        r.close();
        databaseConnection.releaseConnection(con);
        return list;
    }

    private void getData() throws SQLException {
        tableNv.removeAllRow();
        listCus = getListCus();
        for (ModelCustomer data : listCus) {
            tableNv.addRow(new Object[]{data.getId(), data.getName(),
                data.getPhone(), data.getEmail()});
        }

    }

    private void insertComponent() throws SQLException {
        InsertModifyView insert = new InsertModifyView();
        InsertModifyController idmSer = new InsertModifyController(insert, this.cusView);
        cusView.removeAll();
        cusView.add(insert);
        cusView.revalidate();
        cusView.repaint();
        databaseConnection.releaseConnection(con);
    }

    private void modifyComponent(int id) throws SQLException {
        InsertModifyView modify = new InsertModifyView();
        InsertModifyController idmSer = new InsertModifyController(modify, this.cusView, id);
        cusView.removeAll();
        cusView.add(modify);
        cusView.revalidate();
        cusView.repaint();
        databaseConnection.releaseConnection(con);
    }

    ////////////////Excel
    private void exportToExcel(Table table) {
        JFileChooser choose = new JFileChooser();
        choose.setDialogTitle("Lưu Excel");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
        choose.setFileFilter(filter);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(choose);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    /////////////Import
    private void importExcel(Table table) throws FileNotFoundException, SQLException {
        int id = 0;
        String name = "";
        String phone = "";
        String email = "";
        ModelCustomer cus;
        ArrayList<ModelCustomer> newList = new ArrayList<>();
        JFileChooser choose = new JFileChooser();
        choose.setDialogTitle("Lưu Excel");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
        choose.setFileFilter(filter);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(choose);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int select = choose.showSaveDialog(null);
        String path = null;
        if (select == JFileChooser.APPROVE_OPTION) {
            File fileSave = choose.getSelectedFile();
            path = fileSave.getAbsolutePath();
        }
        ExcelDocument workbook = new ExcelDocument();

        workbook.easy_LoadXLSFile(path);
        FileInputStream file = new FileInputStream(path);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(choose);
        } catch (Exception e) {
            e.printStackTrace();
        }
        EasyXLS.Util.Objects.Internal.ResultSet rs = (EasyXLS.Util.Objects.Internal.ResultSet) workbook.easy_ReadXLSXSheet_AsResultSet(file, "Sheet1");
        /////
        int columnCount = rs.getMetaData().getColumnCount();
        int row = 0;
        while (rs.next()) {
            for (int column = 1; column <= columnCount; column++) {
                if (row > 0) {
                    switch (column) {
                        case 1 -> id = Integer.parseInt(rs.getString(column));
                        case 2 -> name = rs.getString(column);
                        case 3 -> phone = rs.getString(column);
                        case 4 -> email = rs.getString(column);
                        default -> throw new AssertionError();
                    }
                }

            }
            if (id > 0 || !name.isEmpty()
                    || !phone.isEmpty() || !email.isEmpty()) {
                cus = new ModelCustomer(id, name, phone, email);
                newList.add(cus);
            }

            row++;
        }
        /////
        String sql = "TRUNCATE tbl_khachhang";
        PreparedStatement pr = con.prepareStatement(sql);
        pr.close();
        for (ModelCustomer data : newList) {
            String sql_ND = "INSERT INTO tbl_khachhang (id_cus,hoten, sdt,email) VALUES (?,?,?,?)";
            PreparedStatement p = con.prepareStatement(sql_ND);
            p.setInt(1, data.getId());
            p.setString(2, data.getName());
            p.setString(3, data.getPhone());
            p.setString(4, data.getEmail());
            p.execute();
            p.close();
        }
        getData();

    }

}
