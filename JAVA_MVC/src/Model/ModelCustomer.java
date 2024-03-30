package Model;

public class ModelCustomer {
    private int id;
    private String Name;
    private String phone;
    private String email;

    public ModelCustomer(int id, String Name, String phone, String email) {
        this.id = id;
        this.Name = Name;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ModelCustomer(int id, String Name) {
        this.id = id;
        this.Name = Name;
    }
    
    
}
