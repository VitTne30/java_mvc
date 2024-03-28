package Model;

/**
 *
 * @author ADMIN
 */
public class ModelDetail {
    private int idDetail;
    private int idBill;
    private int idPro;
    private int num;
    private int money;

    public ModelDetail(int idDetail, int idBill, int idPro, int num, int money) {
        this.idDetail = idDetail;
        this.idBill = idBill;
        this.idPro = idPro;
        this.num = num;
        this.money = money;
    }

    public int getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    
    
    
    
    
}
