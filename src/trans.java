import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class trans extends JDialog {
    List<account> listofa = list.getInstance();
    JLabel amount,acc;
    JTextField amountt,acct;
    JButton tra;
    public trans(JFrame parent, int accnum)
    {
        super(parent, "Transaction", true);
        setBounds(100, 100, 524, 415);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        acc = new JLabel("Enter account number");
        acc.setBounds(27, 35, 160, 39);
        this.getContentPane().add(acc);

        acct = new JTextField();
        acct.setBounds(27, 96, 290, 19);
        this.getContentPane().add(acct);
        acct.setColumns(10);

        amount = new JLabel("Enter amount");
        amount.setBounds(27, 180, 109, 19);
        this.getContentPane().add(amount);

        amountt = new JTextField();
        amountt.setBounds(27, 225, 290, 19);
        this.getContentPane().add(amountt);
        amountt.setColumns(10);

        tra = new JButton("Transaction");
        tra.setBounds(133, 298, 203, 52);
        this.getContentPane().add(tra);
        tra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performTransaction(accnum);
            }
        });
    }
    private void performTransaction(int accnum) {
        try {
            int targetAccNum = Integer.parseInt(acct.getText());
            double amountToTransfer = Double.parseDouble(amountt.getText());

            account sourceAccount = findAccountByNumber(accnum); // this will always be found
            account targetAccount = findAccountByNumber(targetAccNum); // may be found
            if (accnum != targetAccNum) {
                if (targetAccount != null && sourceAccount.getBalance() >= amountToTransfer) {
                    sourceAccount.changeinBalance(-amountToTransfer);
                    targetAccount.changeinBalance(amountToTransfer);
                    JOptionPane.showMessageDialog(this, "Transaction successful");
                    trans.this.dispose();
                } else {
                    if (targetAccount == null) {
                        JOptionPane.showMessageDialog(this, "Target account not found", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (sourceAccount.getBalance() < amountToTransfer) {
                        JOptionPane.showMessageDialog(this, "Insufficient balance", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Cannot transfer to the same account", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private account findAccountByNumber(int accNum) {
        for (account acc : listofa) {
            if (acc.getAccnum() == accNum) {
                return acc;
            }
        }
        return null; // Return null if account is not found
    }
}

