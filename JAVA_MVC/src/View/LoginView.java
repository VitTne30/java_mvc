package View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public class LoginView extends JFrame{
    public LoginView(){
        super("JAVA_MVC");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 500);
        
        addComponent();
        
    }

    private void addComponent() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        //userName
        JLabel usernameLb = new JLabel("Tên đăng nhập: ");
        JTextField usernameField = new JTextField();
        
    }
    
    
}
