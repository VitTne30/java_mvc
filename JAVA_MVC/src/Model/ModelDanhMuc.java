/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author admin
 */
public class ModelDanhMuc {
    private int id;
    private String tendanhmuc;
    private String mota;
    
    public ModelDanhMuc(){
        
    }

    public ModelDanhMuc(int id, String tendanhmuc, String mota) {
        this.id = id;
        this.tendanhmuc = tendanhmuc;
        this.mota = mota;
    }
    
    public ModelDanhMuc(String tendanhmuc, String mota) {
        this.tendanhmuc = tendanhmuc;
        this.mota = mota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
    
}
