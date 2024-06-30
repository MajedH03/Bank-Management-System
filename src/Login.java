import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class Login extends JPanel {
    List<account> listofa = list.getInstance();
    JLabel accountnum;
    JFormattedTextField accountnumt;
    JLabel pass;
    JTextArea passt;

    JButton log;
    JButton sign;

    public Login() {
        setLayout(null);
        accountnum = new JLabel("Account number");
        accountnum.setBounds(31, 27, 112, 42);
        add(accountnum);

        accountnumt = new JFormattedTextField();
        accountnumt.setBounds(27, 78, 399, 19);
        add(accountnumt);

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
                String accNumText = accountnumt.getText().trim();
                String password = passt.getText().trim();

                if (accNumText.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both account number and password.");
                    return;
                }

                if (!isNumeric(accNumText)) {
                    JOptionPane.showMessageDialog(null, "Account number must be numeric.");
                    return;
                }

                int accNum = Integer.parseInt(accNumText);
                int matchedAccNum = findMatchingAccountNumber(accNum, password,listofa);

                if (matchedAccNum != -1) {
                    JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(Login.this);
                    UserUi userUiDialog = new UserUi(mainFrame, matchedAccNum);
                    userUiDialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid account number or password.");
                }
            }
        });
        sign = new JButton("Sign Up");
        sign.setBounds(248, 282, 184, 58);
        add(sign);
        sign.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(Login.this); // to use a frame.
                Sign_UP signUpDialog = new Sign_UP(mainFrame); // create the dialog object
                signUpDialog.setVisible(true); // to make the dialog appear
            }
        });
    }

    public int findMatchingAccountNumber(int accNum, String password,List<account> s1) {
        for (account acc : s1) {
            if (acc.matches(accNum, password)) {
                return acc.getAccnum(); // Return the matched account number
            }
        }
        return -1; // Return -1 if no match is found
    }

    // check that user input is only numbers
    private boolean isNumeric(String txt) {
        for (char c : txt.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
