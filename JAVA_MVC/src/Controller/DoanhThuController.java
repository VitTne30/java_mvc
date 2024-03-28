
package Controller;

import DbConnection.DataConnection;
import View.DoanhThuView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author haqan
 */
public class DoanhThuController {
    private DataConnection databaseConnection;
    private Connection con;
    private DoanhThuView dtView;
    
    public DoanhThuController(DoanhThuView newMs) throws SQLException{
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();
        this.dtView = newMs;
        
        String dt = String.valueOf(getDtNgay());
        dtView.getTxtDt().setText(dt);
    }
    
    public int getDtNgay() throws SQLException{
        int result = 0;
        String query = "SELECT SUM(B.gia_tien*A.soluong) AS doanhthu FROM tbl_chitietdonhang A, tbl_sanpham B, tbl_donhang C WHERE A.id_sanpham = B.id_sanpham AND A.ma_donhang = C.ma_donhang";
        PreparedStatement p = con.prepareStatement(query);
        ResultSet r = p.executeQuery();
        
        if(r.next()){
            result = r.getInt(1); 
        }
        return result;
    }
}
