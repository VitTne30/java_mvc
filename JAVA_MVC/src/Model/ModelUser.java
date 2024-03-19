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

    public ModelUser() {
    }

    public ModelUser(int userId, String userName, String password, String role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
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
    
    
    
    
    
}
