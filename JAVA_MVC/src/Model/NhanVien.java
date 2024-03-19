/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author admin
 */
public class NhanVien {
    private int id;
    private String hoten;
    private String ngaysinh;
    private String daichi;
    private String gioitinh;
    private String sdt;
    private String email;
    
    public NhanVien(){
        
    }

    public NhanVien(int id, String hoten, String ngaysinh, String daichi, String gioitinh, String sdt, String email) {
        this.id = id;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.daichi = daichi;
        this.gioitinh = gioitinh;
        this.sdt = sdt;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDaichi() {
        return daichi;
    }

    public void setDaichi(String daichi) {
        this.daichi = daichi;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
