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
import Model.ModelUser;
import Swing.Table;
import View.NhanVienView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class NhanVienController {
    
    private NhanVienView nvView;
    
    private DataConnection databaseConnection;
    private Connection con;
    private Table tblNhanvien;
    private ArrayList<ModelUser> listNV = new ArrayList<>();
    private String[] arr = {"Quản lý", "Nhân viên"};
    
    public NhanVienController(NhanVienView newNV) throws SQLException {
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();
        this.nvView = newNV;
        tblNhanvien = nvView.getTblTaiKhoan();
        
        getData();
        for(int i=0; i<arr.length; i++){
            nvView.getCboChucVu().addItem(arr[i]);
        }
        
        nvView.getTblTaiKhoan().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                SelectData();
            }
        });
        
        nvView.getTxtResult().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblNhanvien.removeAllRow();
                String search = nvView.getTxtResult().getText();
                for (ModelUser data : listNV) {
                    if(data.getName().toLowerCase().contains(search.toLowerCase())){
                        tblNhanvien.addRow(new Object[]{data.getUserId(), data.getName(), data.getAddress(), data.getPhone(), data.getEmail(), data.getUserName(), data.getPassword(), data.getRole()});
                    }
                }
            }
        });
        
        nvView.getBtnThem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addNhanvien();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        nvView.getBtnSua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateNhanvien();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        nvView.getBtnXoa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteNhanvien();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        nvView.getBtnClear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clear();
            }
        });
        
        nvView.getBtnExcel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToExcel(tblNhanvien);
            }
        });
    }
    
    public ArrayList<ModelUser> getListNV() throws SQLException {
        ArrayList<ModelUser> list = new ArrayList();
        String sql = "SELECT * FROM tbl_taikhoan";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int id = r.getInt("id");
            String hoten = r.getString("hoten");
            String diachi = r.getString("diachi");
            String sdt = r.getString("sdt");
            String email = r.getString("email");
            String tentk = r.getString("tentaikhoan");
            String matkhau = r.getString("matkhau");
            String chucvu = r.getString("vaitro");
            ModelUser data = new ModelUser(id, tentk, matkhau, chucvu, hoten, diachi, sdt, email);
            list.add(data);
        }
        p.close();
        r.close();
        databaseConnection.releaseConnection(con);
        return list;
    }
    
    public void Clear(){
        nvView.getTxtHoten().setText("");
        nvView.getTxtDiachi().setText("");
        nvView.getTxtSDT().setText("");
        nvView.getTxtEmail().setText("");
        nvView.getTxtTenTK().setText("");
        nvView.getTxtMK().setText("");
    }
    
    private void getData() throws SQLException{
        tblNhanvien.removeAllRow();
        listNV = getListNV();
        for (ModelUser data : listNV) {
            tblNhanvien.addRow(new Object[]{data.getUserId(), data.getName(), data.getAddress(), data.getPhone(), data.getEmail(), data.getUserName(), data.getPassword(), data.getRole()});
        }
        nvView.getNumberLb().setText("Tổng số nhân viên: "+ listNV.size());
    }
    
    public void SelectData() {
        int selectedRow = nvView.getTblTaiKhoan().getSelectedRow();

        if (selectedRow >= 0) {
            int i = tblNhanvien.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) nvView.getTblTaiKhoan().getModel();
            nvView.getTxtHoten().setText(model.getValueAt(selectedRow, 1).toString());
            nvView.getTxtDiachi().setText(model.getValueAt(selectedRow, 2).toString());
            nvView.getTxtSDT().setText(model.getValueAt(selectedRow, 3).toString());
            nvView.getTxtEmail().setText(model.getValueAt(selectedRow, 4).toString());
            nvView.getTxtTenTK().setText(model.getValueAt(selectedRow, 5).toString());
            nvView.getTxtMK().setText(model.getValueAt(selectedRow, 6).toString());
            nvView.getCboChucVu().setSelectedItem(model.getValueAt(selectedRow, 7).toString());
        }
    }
    
    private boolean isDuplicate(String tentaikhoan) throws SQLException{
        String query = "SELECT * FROM tbl_taikhoan WHERE tentaikhoan =?";
        PreparedStatement p = con.prepareStatement(query);
        p.setString(1, tentaikhoan);
        ResultSet r = p.executeQuery();
        
        return r.next();
    }
    
    public void addNhanvien() throws SQLException{
        String hoten = nvView.getTxtHoten().getText();
        String diachi = nvView.getTxtDiachi().getText();
        String sdt = nvView.getTxtSDT().getText();
        String email = nvView.getTxtEmail().getText();
        String tentk = nvView.getTxtTenTK().getText();
        String matkhau = nvView.getTxtMK().getText();
        String vaitro = nvView.getCboChucVu().getSelectedItem().toString();
        
        if(isDuplicate(tentk) == true){
            JOptionPane.showMessageDialog(null, "Tên tài khoản mới không được trùng");
        }
        else if(hoten.equals("") || diachi.equals("") || sdt.equals("") || email.equals("") || tentk.equals("") || matkhau.equals("")){
            JOptionPane.showMessageDialog(null, "Các trường dữ liệu không được để trống");
        }
        else{
            ModelUser nv = new ModelUser(tentk, matkhau, vaitro, hoten, diachi, sdt, email);
            
            String query = "INSERT INTO tbl_taikhoan (hoten, diachi, sdt, email, tentaikhoan, matkhau, vaitro) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1, hoten);
            p.setString(2, diachi);
            p.setString(3, sdt);
            p.setString(4, email);
            p.setString(5, tentk);
            p.setString(6, matkhau);
            p.setString(7, vaitro);
            
            p.executeUpdate();
        }
        getData();
    }
    
    public void updateNhanvien() throws SQLException{
        String hoten = nvView.getTxtHoten().getText();
        String diachi = nvView.getTxtDiachi().getText();
        String sdt = nvView.getTxtSDT().getText();
        String email = nvView.getTxtEmail().getText();
        String tentk = nvView.getTxtTenTK().getText();
        String matkhau = nvView.getTxtMK().getText();
        String vaitro = nvView.getCboChucVu().getSelectedItem().toString();
        
        int selectedRow = nvView.getTblTaiKhoan().getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) nvView.getTblTaiKhoan().getModel();
        String id = model.getValueAt(selectedRow, 0).toString();
        String check = model.getValueAt(selectedRow, 1).toString();
        
        if(isDuplicate(hoten) == true && check != hoten){
            JOptionPane.showMessageDialog(null, "Tên nhân viên mới không được trùng");
        }
        else if(hoten.equals("") || diachi.equals("") || sdt.equals("") || email.equals("") || tentk.equals("") || matkhau.equals("")){
            JOptionPane.showMessageDialog(null, "Các trường dữ liệu không được để trống");
        }
        else{
            ModelUser nv = new ModelUser(tentk, matkhau, vaitro, hoten, diachi, sdt, email);
            
            String query = "UPDATE tbl_taikhoan SET hoten =? , diachi =? , sdt =? , email =? , tentaikhoan =? , matkhau =? , vaitro =? WHERE id =?";
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1, hoten);
            p.setString(2, diachi);
            p.setString(3, sdt);
            p.setString(4, email);
            p.setString(5, tentk);
            p.setString(6, matkhau);
            p.setString(7, vaitro);
            p.setString(8, id);
            
            p.executeUpdate();
            getData();
        }
    }
    
    public void deleteNhanvien() throws SQLException{
        int selectedRow = nvView.getTblTaiKhoan().getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) nvView.getTblTaiKhoan().getModel();
        int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
        String name = String.valueOf(tblNhanvien.getModel().getValueAt(tblNhanvien.getSelectedRow(), 1));
        int result = JOptionPane.showConfirmDialog(nvView, "Xác nhận xóa tài khoản: " + name + "?",
                "Thông báo", JOptionPane.YES_NO_OPTION);
        
        String query = "DELETE FROM tbl_taikhoan WHERE id = ?";
        PreparedStatement p = con.prepareStatement(query);
        p.setInt(1, id);
        p.executeUpdate();
        
        getData();
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
            JOptionPane.showMessageDialog(null, "Xuất EXCEL thành công!");
        }
    }
}
