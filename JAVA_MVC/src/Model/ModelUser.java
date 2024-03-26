package Model;

/**
 *
 * @author ADMIN
 */
public class ModelUser {
    private int userId;
    private String userName;
    private String password;
    private String role;
    private String name;
    private String address;
    private String phone;
    private String email;

    public ModelUser() {
    }

    public ModelUser(int userId, String userName, String password, String role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
    public ModelUser(int id, String name, String address, String phone, String email) {
        this.userId = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
    

    public ModelUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public ModelUser(int id) {
        this.userId =id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
