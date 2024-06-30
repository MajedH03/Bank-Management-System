import javax.swing.*;
import java.awt.*;
import java.util.List;
public class depD extends JDialog {
    List<account> listofa = list.getInstance();
    JButton dep;
    JLabel am;
    JTextField amt;
    public depD(){} // public constructor for testing
    public depD(JFrame parent, int accnum) {
        super(parent, "deposit", true);
        setBounds(100, 100, 503, 385);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        am = new JLabel("Enter amount:");
        am.setFont(new Font("Tahoma", Font.PLAIN, 16));
        am.setBounds(36, 45, 142, 32);
        this.getContentPane().add(am);

        amt = new JTextField();
        amt.setBounds(36, 128, 320, 19);
        this.getContentPane().add(amt);
        amt.setColumns(10); // good amount ngl

        dep = new JButton("Deposit");
        dep.setBounds(153, 229, 199, 97);
        this.getContentPane().add(dep);
        dep.addActionListener(e -> {
            String amountText = amt.getText().trim(); // to remove spaces
            if (amountText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an amount.");
                return;
            }

            if (!isNumeric(amountText)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid INT amount.");
                return;
            }

            double amount = Double.parseDouble(amountText);
            account acc = findAccountByNumber(accnum,listofa);
            acc.changeinBalance(amount); // Method in account class to add amount to balance
            JOptionPane.showMessageDialog(this, "Deposit successful!");
            depD.this.dispose();
        });
    }
    public account findAccountByNumber(int accNum,List<account> s1) {
        for(account a : s1)
        {
            if(a.getAccnum() == accNum)
            {
                return a;
            }
        }
        return null; // will never reach here
    }
    public boolean isNumeric(String txt) {
        for (char c : txt.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}