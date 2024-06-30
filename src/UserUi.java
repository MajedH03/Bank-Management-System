import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class UserUi extends JDialog {
    JButton dep, with, check, trans;
    List<account> listofa = list.getInstance();
    int accnum;

    public UserUi(JFrame parentF, int accnum) {
        super(parentF, "Options", true);
        this.accnum = accnum;
        this.setBounds(100, 100, 502, 450);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);
        dep = new JButton("Deposit");
        dep.setBounds(32, 48, 172, 89);
        this.getContentPane().add(dep);
        dep.addActionListener(e -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(UserUi.this); // so that we can use nested dialogs
            depD depositDialog = new depD(parentFrame, accnum);
            depositDialog.setVisible(true);
        });

        with = new JButton("Withdraw");
        with.setBounds(303, 48, 164, 86);
        this.getContentPane().add(with);
        with.addActionListener(e -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(UserUi.this); // so that we can use nested dialogs
            with withDialog = new with(parentFrame, accnum);
            withDialog.setVisible(true);
        });


        check = new JButton("Check Balance");
        check.setBounds(32, 249, 172, 97);
        this.getContentPane().add(check);
        check.addActionListener(e -> {
            account a1 =  findAccountByNumber(accnum);
            double number = a1.getBalance();
            String text = "Your balance is : " + Double.toString(number);
            JOptionPane.showMessageDialog(this, text);
        });



        trans = new JButton("Transaction");
        trans.setBounds(303, 249, 164, 97);
        this.getContentPane().add(trans);
        trans.addActionListener(e -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(UserUi.this); // so that we can use nested dialogs
            trans transDialog = new trans(parentFrame, accnum);// this is for class
            transDialog.setVisible(true);
        });


        // if the user closes the window it returns to the main frame.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentF.setVisible(true);
            }
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
}


