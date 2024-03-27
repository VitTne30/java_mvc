/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Services;

import Controller.DbConnection.DataConnection;
import Model.ModelDanhMuc;
import Swing.Table;
import View.DanhMucView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class DanhMucController {

    private DataConnection databaseConnection;
    private Connection con;
    private DanhMucView dmView;
    private Table tblDanhmuc;
    private ArrayList<ModelDanhMuc> listDM = new ArrayList<>();

    public DanhMucController(DanhMucView newDM) throws SQLException {
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();
        this.dmView = newDM;
        tblDanhmuc = dmView.getTblDanhmuc();
        getData();
        
        dmView.getNumberLb().setText("Tổng số danh mục: "+ listDM.size());

        dmView.getTblDanhmuc().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                SelectData();
            }
        });

        dmView.getTxtResult().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblDanhmuc.removeAllRow();
                String search = dmView.getTxtResult().getText();
                for (ModelDanhMuc data : listDM) {
                    if(data.getTendanhmuc().toLowerCase().contains(search.toLowerCase())){
                        tblDanhmuc.addRow(new Object[]{data.getId(),data.getTendanhmuc(),
            data.getMota()});
                    }
                }
            }
        });
        
        dmView.getBtnThem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addDanhmuc();
                } catch (SQLException ex) {
                    Logger.getLogger(DanhMucController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        dmView.getBtnSua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateDanhmuc();
                } catch (SQLException ex) {
                    Logger.getLogger(DanhMucController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        dmView.getBtnXoa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteDanhmuc();
                } catch (SQLException ex) {
                    Logger.getLogger(DanhMucController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        dmView.getBtnClear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clear();
            }
        });
        
    }

    public ArrayList<ModelDanhMuc> getListDM() throws SQLException {
        ArrayList<ModelDanhMuc> list = new ArrayList();
        String sql = "SELECT * FROM tbl_danhmuc";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int id = r.getInt(1);
            String tendanhmuc = r.getString(2);
            String mota = r.getString(3);
            ModelDanhMuc data = new ModelDanhMuc(id, tendanhmuc, mota);
            list.add(data);
        }
        p.close();
        r.close();
        databaseConnection.releaseConnection(con);
        return list;
    }

    public void SelectData() {
        int selectedRow = dmView.getTblDanhmuc().getSelectedRow();

        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) dmView.getTblDanhmuc().getModel();
            dmView.getTxtTenDM().setText(model.getValueAt(selectedRow, 0).toString());
            dmView.getTxtTenDM().setText(model.getValueAt(selectedRow, 1).toString());
            dmView.getTxtMota().setText(model.getValueAt(selectedRow, 2).toString());
        }
    }
    
    private boolean isDuplicate(String tendanhmuc) throws SQLException{
        String query = "SELECT * FROM tbl_danhmuc WHERE tendanhmuc =?";
        PreparedStatement p = con.prepareStatement(query);
        p.setString(1, tendanhmuc);
        ResultSet r = p.executeQuery();
        
        return r.next();
    }
    
    public void addDanhmuc() throws SQLException{
        String tendanhmuc = dmView.getTxtTenDM().getText();
        String mota = dmView.getTxtMota().getText();
        
        if(isDuplicate(tendanhmuc) == true){
            JOptionPane.showMessageDialog(null, "Tên danh mục mới không được trùng");
        }
        else if(tendanhmuc.equals("") || mota.equals("")){
            JOptionPane.showMessageDialog(null, "Các trường dữ liệu không được để trống");
        }
        else{
            ModelDanhMuc danhmuc = new ModelDanhMuc(tendanhmuc, mota);
            
            String query = "INSERT INTO tbl_danhmuc (tendanhmuc, mota) VALUES (?, ?)";
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1, tendanhmuc);
            p.setString(2, mota);
            
            p.executeUpdate();
        }
        getData();
    }
    
    public void updateDanhmuc() throws SQLException{
        String tendanhmuc = dmView.getTxtTenDM().getText();
        String mota = dmView.getTxtMota().getText();
        
        int selectedRow = dmView.getTblDanhmuc().getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) dmView.getTblDanhmuc().getModel();
        String id = model.getValueAt(selectedRow, 0).toString();
        String check = model.getValueAt(selectedRow, 1).toString();
        
        if(isDuplicate(tendanhmuc) == true && check != tendanhmuc){
            JOptionPane.showMessageDialog(null, "Tên danh mục mới không được trùng");
        }
        else if(tendanhmuc.equals("") || mota.equals("")){
            JOptionPane.showMessageDialog(null, "Các trường dữ liệu không được để trống");
        }
        else{
            ModelDanhMuc danhmuc = new ModelDanhMuc(tendanhmuc, mota);
            
            String query = "UPDATE tbl_danhmuc SET tendanhmuc =? , mota =? WHERE id_danhmuc =?";
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1, tendanhmuc);
            p.setString(2, mota);
            p.setString(3, id);
            
            p.executeUpdate();
            getData();
        }
    }
    
    public void deleteDanhmuc() throws SQLException{
        int selectedRow = dmView.getTblDanhmuc().getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) dmView.getTblDanhmuc().getModel();
        int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
        String name = String.valueOf(tblDanhmuc.getModel().getValueAt(tblDanhmuc.getSelectedRow(), 1));
        int result = JOptionPane.showConfirmDialog(dmView, "Xác nhận xóa danh mục: " + name + "?",
                "Thông báo", JOptionPane.YES_NO_OPTION);
        
        String query = "DELETE FROM tbl_danhmuc WHERE id_danhmuc = ?";
        PreparedStatement p = con.prepareStatement(query);
        p.setInt(1, id);
        p.executeUpdate();
        
        getData();
    }
    
    public void Clear(){
        dmView.getTxtTenDM().setText("");
        dmView.getTxtMota().setText("");
    }
    
    private void getData() throws SQLException{
        tblDanhmuc.removeAllRow();
        listDM = getListDM();
        for (ModelDanhMuc data : listDM) {
            tblDanhmuc.addRow(new Object[]{data.getId(), data.getTendanhmuc(), data.getMota()});
        }
    }
}
