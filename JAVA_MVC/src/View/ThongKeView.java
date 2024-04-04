/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Swing.Button;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.chart.ChartPanel;


/**
 *
 * @author haqan
 */
public class ThongKeView extends JPanel{
    private SimpleDateFormat simpleDate = new SimpleDateFormat("dd - MM - YYYY");
    private JLabel dateLb = new JLabel(simpleDate.format(new Date()) + "");
    private JLabel lblTitle = new JLabel("THỐNG KÊ DOANH THU");
    private JLabel lblDt = new JLabel();
    private JTextField txtDt = new JTextField();
    private Button btnExcel = new Button();
    private JComboBox cboNam = new JComboBox();
    private ChartPanel chartPanel = new ChartPanel(null);
    
    Font buttonFont = new Font("Arial", Font.BOLD, 16);
    Font TitleFont = new Font("Arial", Font.BOLD, 20);
    
    public ThongKeView() {
        GUI();
        add();
        this.setVisible(true);
    }
    
    private void GUI() {
        setBounds(0, 0, 800, 800);
        setLayout(null);
        setBackground(new Color(240, 240, 240));
    }
    
    private void add(){
    
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        chartPanel.setBounds(20, 80, 700, 500);
        add(chartPanel);
        
        cboNam.setBounds(600, 600, 120, 50);
        cboNam.setFont(buttonFont);
        add(cboNam);
        
        lblTitle.setBounds(10, 0, 320, 40);
        lblTitle.setFont(TitleFont);
        lblTitle.setForeground(Color.decode("#0066FF"));
        this.add(lblTitle);
        
        dateLb.setBounds(650, 0, 300, 40);
        dateLb.setBackground(Color.red);
        dateLb.setFont(TitleFont);
        this.add(dateLb);
        
        lblDt.setBounds(50, 700, 150, 50);
        lblDt.setFont(buttonFont);
        lblDt.setText("Doanh thu: ");
        this.add(lblDt);
        
        txtDt.setBounds(180, 700, 300, 50);
        txtDt.setFont(buttonFont);
        txtDt.setEditable(false);
        this.add(txtDt);
        
        btnExcel.setBounds(600, 700, 120, 50);
        btnExcel.setFont(buttonFont);
        btnExcel.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/excel.png")));
        btnExcel.setText("Excel");
        this.add(btnExcel);

    }

    public JLabel getLblDt() {
        return lblDt;
    }

    public void setLblDt(JLabel lblDt) {
        this.lblDt = lblDt;
    }

    public JTextField getTxtDt() {
        return txtDt;
    }

    public void setTxtDt(JTextField txtDt) {
        this.txtDt = txtDt;
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public void setChartPanel(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }

    public JComboBox getCboNam() {
        return cboNam;
    }

    public void setCboNam(JComboBox cboNam) {
        this.cboNam = cboNam;
    }

    public Button getBtnExcel() {
        return btnExcel;
    }

    public void setBtnExcel(Button btnExcel) {
        this.btnExcel = btnExcel;
    }
    
    
}
