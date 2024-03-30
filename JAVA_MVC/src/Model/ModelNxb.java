package Model;

public class ModelNxb {
    private int maNxb;
    private String tenNxb;
    private String sdt;
    private String diaChi;
    
    public ModelNxb(){
        
    }
    
    public ModelNxb(int maNxb, String tenNxb, String sdt, String diaChi){
        this.maNxb = maNxb;
        this.tenNxb = tenNxb;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }
    
    public ModelNxb(String tenNxb, String sdt, String diaChi){
        this.tenNxb = tenNxb;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public int getMaNxb() {
        return maNxb;
    }

    public void setMaNxb(int maNxb) {
        this.maNxb = maNxb;
    }

    public String getTenNxb() {
        return tenNxb;
    }

    public void setTenNxb(String tenNxb) {
        this.tenNxb = tenNxb;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
