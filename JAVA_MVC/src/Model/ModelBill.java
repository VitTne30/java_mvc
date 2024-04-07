package Model;

import java.util.Date;
import java.util.Objects;
/**
 *
 * @author ADMIN
 */
public class ModelBill {
    private int idBill;
    private int idCus;
    private String date;
    private int money;

    public ModelBill(int idBill, int idCus, String date, int money) {
        this.idBill = idBill;
        this.idCus = idCus;
        this.date = date;
        this.money = money;
    }

    public ModelBill(String date, int money) {
        this.date = date;
        this.money = money;
    }
    

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdCus() {
        return idCus;
    }

    public void setIdCus(int idCus) {
        this.idCus = idCus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelBill that = (ModelBill) o;
        return idBill == that.idBill &&
                Objects.equals(idCus, that.idCus) &&
                Objects.equals(date, that.date) &&
                Objects.equals(money, that.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBill, idCus, date, money);
    }
    
}
