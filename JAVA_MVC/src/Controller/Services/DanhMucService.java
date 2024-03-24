/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Services;

import Controller.DbConnection.DataConnection;
import Model.DanhMuc;
import Swing.Table;
import View.DanhMucView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class DanhMucService {

    private DataConnection databaseConnection;
    private Connection con;
    private DanhMucView dmView;
    private Table tblDanhmuc;
    private ArrayList<DanhMuc> listDM = new ArrayList<>();

    public DanhMucService(DanhMucView newDM) throws SQLException {
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();
        this.dmView = newDM;
        tblDanhmuc = dmView.getTblDanhmuc();
        listDM = getListDM();
        for (DanhMuc data : listDM) {
            tblDanhmuc.addRow(new Object[]{data.getId(), data.getTendanhmuc(),
                data.getMota()});
        }

        dmView.getTxtResult().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblDanhmuc.removeAllRow();
                String search = dmView.getTxtResult().getText();
                for (DanhMuc data : listDM) {
                    if(data.getTendanhmuc().toLowerCase().contains(search.toLowerCase())){
                        tblDanhmuc.addRow(new Object[]{data.getId(),data.getTendanhmuc(),
            data.getMota()});
                    }
                }
            }
        });
    }

    public ArrayList<DanhMuc> getListDM() throws SQLException {
        ArrayList<DanhMuc> list = new ArrayList();
        String sql = "SELECT * FROM tbl_danhmuc";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int id = r.getInt(1);
            String tendanhmuc = r.getString(2);
            String mota = r.getString(3);
            DanhMuc data = new DanhMuc(id, tendanhmuc, mota);
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
            dmView.getTxtTenDM().setText(model.getValueAt(selectedRow, 1).toString());
            dmView.getTxtMota().setText(model.getValueAt(selectedRow, 2).toString());
        }
    }
    
    
}
