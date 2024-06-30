import javax.swing.*;
import java.awt.*;
import java.util.List;
public class with extends JDialog {
    List<account> listofa = list.getInstance();
    JButton dep;
    JLabel am;
    JTextField amt;

    public with(JFrame parent, int accnum) {
        super(parent, "withdraw", true);
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

        dep = new JButton("Withdraw");
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
            account acc = findAccountByNumber(accnum);
            if(acc.getBalance() < amount)
            {
                JOptionPane.showMessageDialog(this, "amount can not exceed balance");
                return;
            }
            acc.changeinBalance(-amount); // Method in account class to add amount to balance
            JOptionPane.showMessageDialog(this, "withdraw successful!");
            with.this.dispose();
        });
    }
    private account findAccountByNumber(int accNum) {
        for(account a : listofa)
        {
            if(a.getAccnum() == accNum)
            {
                return a;
            }
        }
        return null; // will never reach here
    }
    private boolean isNumeric(String txt) {
        for (char c : txt.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}