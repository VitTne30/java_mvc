/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DbConnection.DataConnection;
import EasyXLS.Constants.DataType;
import EasyXLS.ExcelDocument;
import EasyXLS.ExcelTable;
import EasyXLS.ExcelWorksheet;
import Model.ModelNxb;
import Swing.Table;
import View.NxbView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author haqan
 */
public class NxbController {
    private DataConnection databaseConnection;
    private Connection con;
    private NxbView nxbView;
    private Table tblNxb;
    private ArrayList<ModelNxb> listNxb = new ArrayList<>();
    
    public NxbController(NxbView newMs) throws SQLException {
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();
        this.nxbView = newMs;
        tblNxb = nxbView.getTblNxb();
        getData();
        
        nxbView.getNumberLb().setText("Tổng số nhà xuất bản: "+ listNxb.size());

        nxbView.getTblNxb().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                SelectData();
            }
        });

        nxbView.getTxtResult().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblNxb.removeAllRow();
                String search = nxbView.getTxtResult().getText();
                for (ModelNxb data : listNxb) {
                    if(data.getTenNxb().toLowerCase().contains(search.toLowerCase())){
                        tblNxb.addRow(new Object[]{data.getMaNxb(),data.getTenNxb(),data.getSdt(),data.getDiaChi()});
                    }
                }
            }
        });
        
        nxbView.getBtnThem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addNxb();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi!");
                }
            }
        });
        
        nxbView.getBtnSua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateDanhmuc();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi!");
                }
            }
        });
        
        nxbView.getBtnXoa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteNxb();
                } catch (SQLException ex) {
                  JOptionPane.showMessageDialog(null, "Lỗi!");
                }
            }
        });
        
        nxbView.getBtnClear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clear();
            }
        });
        
        nxbView.getBtnExcel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToExcel(tblNxb);
            }
        });
        
    }

    public ArrayList<ModelNxb> getListNxb() throws SQLException {
        ArrayList<ModelNxb> list = new ArrayList();
        String sql = "SELECT * FROM tbl_nxb";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int id = r.getInt(1);
            String tennxb = r.getString(2);
            String sdt = r.getString(3);
            String diachi = r.getString(4);
            ModelNxb data = new ModelNxb(id, tennxb, sdt, diachi);
            list.add(data);
        }
        p.close();
        r.close();
        databaseConnection.releaseConnection(con);
        return list;
    }

    public void SelectData() {
        int selectedRow = nxbView.getTblNxb().getSelectedRow();

        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) nxbView.getTblNxb().getModel();
            nxbView.getTxtTen().setText(model.getValueAt(selectedRow, 0).toString());
            nxbView.getTxtTen().setText(model.getValueAt(selectedRow, 1).toString());
            nxbView.getTxtSdt().setText(model.getValueAt(selectedRow, 2).toString());
            nxbView.getTxtDiachi().setText(model.getValueAt(selectedRow, 3).toString());
        }
    }
    
    private boolean isDuplicate(String tennxb) throws SQLException{
        String query = "SELECT * FROM tbl_nxb WHERE tennxb =?";
        PreparedStatement p = con.prepareStatement(query);
        p.setString(1, tennxb);
        ResultSet r = p.executeQuery();
        
        return r.next();
    }
    
    public void addNxb() throws SQLException{
        String tennxb = nxbView.getTxtTen().getText();
        String sdt = nxbView.getTxtSdt().getText();
        String diachi = nxbView.getTxtDiachi().getText();
        
        if(isDuplicate(tennxb) == true){
            JOptionPane.showMessageDialog(null, "Tên nhà xuất bản đã tồn tại!");
        }
        else{
            String query = "INSERT INTO tbl_nxb (tennxb, sdt, diachi) VALUES (?, ?, ?)";
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1, tennxb);
            p.setString(2, sdt);
            p.setString(3, diachi);
            p.executeUpdate();
        }
        getData();
        Clear();
    }
    
    public void updateDanhmuc() throws SQLException{
        String tennxb = nxbView.getTxtTen().getText();
        String sdt = nxbView.getTxtSdt().getText();
        String diachi = nxbView.getTxtDiachi().getText();
        
        int selectedRow = nxbView.getTblNxb().getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) nxbView.getTblNxb().getModel();
        String id = model.getValueAt(selectedRow, 0).toString();
        
        if(isDuplicate(tennxb) == true){
            JOptionPane.showMessageDialog(null, "Tên nhà xuất bản mới không được trùng!");
        }
        else{
            String query = "UPDATE tbl_nxb SET tennxb =? , sdt =?, diachi =? WHERE id_nxb =?";
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1, tennxb);
            p.setString(2, sdt);
            p.setString(3, diachi);
            p.setString(4, id);
            
            p.executeUpdate();
            getData();
        }
    }
    
    public void deleteNxb() throws SQLException{
        int selectedRow = nxbView.getTblNxb().getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) nxbView.getTblNxb().getModel();
        int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
        
        String query = "DELETE FROM tbl_nxb WHERE id = ?";
        PreparedStatement p = con.prepareStatement(query);
        p.setInt(1, id);
        p.executeUpdate();
        
        getData();
        Clear();
    }
    
    public void Clear(){
        nxbView.getTxtTen().setText("");
        nxbView.getTxtSdt().setText("");
        nxbView.getTxtDiachi().setText("");
    }
    
    private void getData() throws SQLException{
        tblNxb.removeAllRow();
        listNxb = getListNxb();
        for (ModelNxb data : listNxb) {
            tblNxb.addRow(new Object[]{data.getMaNxb(), data.getTenNxb(), data.getSdt(), data.getDiaChi()});
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
                workbook.easy_getSheetAt(0).setSheetName("Danh sách nhà xuất bản");
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
