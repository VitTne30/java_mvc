
package Controller;

import DbConnection.DataConnection;
import Model.ModelBill;
import View.ThongKeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author haqan
 */
public class ThongKeController {
    private DataConnection databaseConnection;
    private Connection con;
    private ThongKeView dtView;
    private ChartPanel chartPanel;
    private JComboBox cboNam = new JComboBox();
    private ArrayList<ModelBill> list = new ArrayList<>();
    
    public ThongKeController(ThongKeView newMs) throws SQLException{
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();
        this.dtView = newMs;
        
        chartPanel = dtView.getChartPanel();
        cboNam = dtView.getCboNam();
        
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = year; i >= year - 5; i--)
            cboNam.addItem(i);
        
        chartPanel.setChart(createLineChart());
        String dt = String.valueOf(getDtNgay(Integer.parseInt(cboNam.getSelectedItem() + "")));
        dtView.getTxtDt().setText(dt);
        
        cboNam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    chartPanel.setChart(createLineChart());
                    String dt = String.valueOf(getDtNgay(Integer.parseInt(cboNam.getSelectedItem() + "")));
                    dtView.getTxtDt().setText(dt);
                } catch (SQLException ex) {
                    Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public int getDtNgay(int nam) throws SQLException{
        int result = 0;
        String query = "SELECT SUM(tien) FROM tbl_donhang WHERE YEAR(ngaylap) = ?";
        PreparedStatement p = con.prepareStatement(query);
        p.setInt(1, nam);
        ResultSet r = p.executeQuery();
        if(r.next()){
            result = r.getInt(1); 
        }
        return result;
    }
    
    private DefaultCategoryDataset createDataset() throws SQLException {
        cboNam = dtView.getCboNam();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        list = getListMonth(Integer.parseInt(cboNam.getSelectedItem() + ""));
        for (ModelBill data : list) {
            dataset.addValue(data.getMoney(), "Vnd", data.getDate());
        }
        
        return dataset;
    }
    
    private JFreeChart createLineChart() throws SQLException {
        JFreeChart lineChart = ChartFactory.createLineChart(

                "Biểu đồ doanh thu".toUpperCase(),


                "Tháng", "Vnđ (đ)", createDataset(),


                PlotOrientation.VERTICAL, false, false, false);
        return lineChart;
    }
    
    private ArrayList<ModelBill> getListMonth(int nam) throws SQLException {
        ArrayList<ModelBill> list = new ArrayList();
        String sql = "SELECT MONTH(ngaylap) as thang, SUM(tien) as money FROM tbl_donhang WHERE YEAR(ngaylap) = ? GROUP BY thang";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, nam);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            String date = r.getString(1);
            int money = r.getInt(2);
            ModelBill data = new ModelBill(date, money);
            list.add(data);
        }
        p.close();
        r.close();
        databaseConnection.releaseConnection(con);
        return list;
    }
    
    
}
