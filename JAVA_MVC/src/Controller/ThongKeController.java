
package Controller;

import DbConnection.DataConnection;
import EasyXLS.Charts.ExcelChart;
import EasyXLS.Constants.Chart;
import EasyXLS.Constants.DataType;
import EasyXLS.ExcelDocument;
import EasyXLS.ExcelTable;
import EasyXLS.ExcelWorksheet;
import Model.ModelBill;
import Swing.Table;
import View.ThongKeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
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
    private Table tblTke = new Table();
    
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
                    getData();
                } catch (SQLException ex) {
                    Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        tblTke.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Tháng", "Doanh thu"
                }
        ));
        getData();
        
        dtView.getBtnExcel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToExcel(tblTke);
            }
        });
    }
    
    public int getDtNgay(int nam) throws SQLException{
        int result = 0;
        String query = "SELECT SUM(tong_tien) FROM tbl_donhang WHERE YEAR(ngaylap) = ?";
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
        String sql = "SELECT MONTH(ngaylap) as thang, SUM(tong_tien) as money FROM tbl_donhang WHERE YEAR(ngaylap) = ? GROUP BY thang";
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
    
    private void getData() throws SQLException {
        tblTke.removeAllRow();
        list = getListMonth(Integer.parseInt(cboNam.getSelectedItem() + ""));
        for (ModelBill data : list) {
            tblTke.addRow(new Object[]{data.getDate(), data.getMoney()});
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
                workbook.easy_getSheetAt(0).setSheetName("Doanh thu");
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
                        xlsTable.easy_getCell(row + 1, col).setDataType(DataType.NUMERIC);
                    }
                }
                ExcelChart xlsChart = new ExcelChart("A15", 600, 300);
                xlsChart.easy_setChartType(Chart.CHART_TYPE_LINE);
                xlsChart.easy_addSeries("=Doanh thu!$B$1", "=Doanh thu!$B$2:$B$13");
                xlsChart.easy_setCategoryXAxisLabels("=Doanh thu!$A$2:$A$13");
                
                ((ExcelWorksheet)workbook.easy_getSheet("Doanh thu")).easy_addChart(xlsChart);
                
                workbook.easy_WriteXLSXFile(fileSave.toString());
                workbook.Dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "File saved successfully!");
        }
    }
}
