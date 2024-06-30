import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class LoginA extends JPanel {
    List<account> listofa = list.getInstance();
    JLabel username;
    JTextField usernamet;
    JLabel pass;
    JTextArea passt;

    JButton log;

    public LoginA() {
        setLayout(null);
        username = new JLabel("User Name: ");
        username.setBounds(31, 27, 112, 42);
        add(username);

        usernamet = new JTextField();
        usernamet.setBounds(27, 78, 399, 19);
        add(usernamet);

        pass = new JLabel("Password");
        pass.setBounds(31, 118, 112, 24);
        add(pass);

        passt = new JTextArea();
        passt.setBounds(31, 152, 395, 22);
        add(passt);

        log = new JButton("Login In");
        log.setBounds(32, 282, 161, 58);
        add(log);
        log.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernamet.getText();
                String password = passt.getText();
                if (username.equals("admin") && password.equals("admin")) {
                    JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(LoginA.this);
                    AdminUi adminUid = new AdminUi(mainFrame);
                    adminUid.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect username or password");
                }
            }
        });
    }

}
