import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainF extends JFrame {

    public MainF() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setBounds(100, 100, 600, 450);
        this.setTitle("Bank Management System");

        JMenuBar menuBar = new JMenuBar();

        JMenu userMenu = new JMenu("User");
        JMenuItem userItem = new JMenuItem("Open User Panel");
        userItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login loginPanel = new Login();
                setContentPane(loginPanel); // leads to login
                validate(); // to make sure that the size is good.
            }
        });
        userMenu.add(userItem); // added to menubar


        JMenu adminMenu = new JMenu("Admin");
        JMenuItem adminItem = new JMenuItem("Open Admin Panel");
        adminItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginA a1 = new LoginA();
                setContentPane(a1);
                validate();
            }
        });
        adminMenu.add(adminItem);

        menuBar.add(userMenu);
        menuBar.add(adminMenu);

        this.setJMenuBar(menuBar); // add the menubar to frame

        setContentPane(new Login()); // default

        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                list.storelistandcounter(); // store then dispose
                MainF.this.dispose();
            }
        });
    }

    public static void main(String[] args) {
        list.loadlistandcounter();
        new MainF();
    }
}
