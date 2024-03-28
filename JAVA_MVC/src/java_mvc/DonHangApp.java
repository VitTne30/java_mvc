package java_mvc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DonHangApp extends JFrame {
    private JTable donHangTable;
    private DefaultTableModel model;

    public DonHangApp() {
        setTitle("Quản lý đơn hàng");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo dữ liệu mẫu
        List<DonHang> donHangList = new ArrayList<>();
        donHangList.add(new DonHang("DH001", "2024-03-25", 500000));
        donHangList.add(new DonHang("DH002", "2024-03-26", 750000));

        List<ChiTietDonHang> chiTietDonHangList = new ArrayList<>();
        chiTietDonHangList.add(new ChiTietDonHang("CTDH001", "DH001", "SP001", 2, 200000));
        chiTietDonHangList.add(new ChiTietDonHang("CTDH002", "DH001", "SP002", 3, 150000));
        chiTietDonHangList.add(new ChiTietDonHang("CTDH003", "DH002", "SP003", 1, 750000));

        // Tạo model cho bảng
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Mã đơn hàng");
        model.addColumn("Ngày lập");
        model.addColumn("Tổng tiền");

        // Thêm dữ liệu đơn hàng vào model
        for (DonHang donHang : donHangList) {
            model.addRow(new Object[]{donHang.getMaDonHang(), donHang.getNgayLap(), donHang.getTongTien()});
            // Thêm dữ liệu chi tiết đơn hàng vào model
            for (ChiTietDonHang chiTietDonHang : chiTietDonHangList) {
                if (chiTietDonHang.getMaDonHang().equals(donHang.getMaDonHang())) {
                    model.addRow(new Object[]{"", chiTietDonHang.getMaSanPham(), chiTietDonHang.getSoLuong(), chiTietDonHang.getSoLuong() * chiTietDonHang.getGiaTien()});
                }
            }
        }

        // Tạo bảng từ model
        donHangTable = new JTable(model);
        donHangTable.setRowHeight(30);
        donHangTable.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(donHangTable);
        add(scrollPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DonHangApp::new);
    }
}

class DonHang {
    private String maDonHang;
    private String ngayLap;
    private int tongTien;

    public DonHang(String maDonHang, String ngayLap, int tongTien) {
        this.maDonHang = maDonHang;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public int getTongTien() {
        return tongTien;
    }
}

class ChiTietDonHang {
    private String maChiTiet;
    private String maDonHang;
    private String maSanPham;
    private int soLuong;
    private int giaTien;

    public ChiTietDonHang(String maChiTiet, String maDonHang, String maSanPham, int soLuong, int giaTien) {
        this.maChiTiet = maChiTiet;
        this.maDonHang = maDonHang;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
    }

    public String getMaChiTiet() {
        return maChiTiet;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getGiaTien() {
        return giaTien;
    }
}
