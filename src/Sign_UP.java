import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Sign_UP extends JDialog {
    List<account> listofa = list.getInstance();
    JLabel name, job, phonum, password, datec, dateb;
    JFormattedTextField namet, jobt, phonumt, datect, datebt;
    JTextField passwordt;
    JButton SignupB;
    public Sign_UP(){} // for testing only

    public Sign_UP(JFrame parentF) {
        // we sit it to true so that the user has to finish signup or close it to user other windows.
        super(parentF,"Sign Up",true);
        this.setBounds(100, 100, 600, 507);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        name = new JLabel("Full Name");
        name.setBounds(20, 38, 89, 29);
        this.getContentPane().add(name);

        namet = new JFormattedTextField();
        namet.setBounds(20, 77, 139, 19);
        this.getContentPane().add(namet);

        job = new JLabel("Profession");
        job.setBounds(20, 124, 103, 29);
        this.getContentPane().add(job);

        jobt = new JFormattedTextField();
        jobt.setBounds(20, 163, 139, 19);
        this.getContentPane().add(jobt);

        phonum = new JLabel("Phone Number");
        phonum.setBounds(20, 219, 89, 29);
        this.getContentPane().add(phonum);

        phonumt = new JFormattedTextField();
        phonumt.setBounds(20, 258, 139, 19);
        this.getContentPane().add(phonumt);

        dateb = new JLabel("Date of birth : Format(dd/MM/yyyy)");
        dateb.setBounds(291, 42, 240, 21);
        this.getContentPane().add(dateb);

        datebt = new JFormattedTextField();
        datebt.setBounds(291, 77, 180, 19);
        this.getContentPane().add(datebt);

        datec = new JLabel("Date of Account creation : Format(dd/MM/yyyy)");
        datec.setBounds(291, 129, 280, 19);
        this.getContentPane().add(datec);

        datect = new JFormattedTextField();
        datect.setBounds(291, 163, 147, 19);
        this.getContentPane().add(datect);

        password = new JLabel("Password");
        password.setBounds(291, 227, 114, 13);
        this.getContentPane().add(password);

        passwordt = new JTextField();
        passwordt.setBounds(291, 258, 147, 19);
        this.getContentPane().add(passwordt);

        SignupB = new JButton("Create Account");
        SignupB.setBounds(108, 362, 262, 58);
        this.getContentPane().add(SignupB);
        SignupB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(validateInputs())
                {
                    String date1 = datect.getText();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Define the format

                    Date date_1 = null;
                    Date date_2 = null;
                    try {
                         date_1 = dateFormat.parse(date1);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    String date2 = datebt.getText();

                    try {
                        date_2 = dateFormat.parse(date2);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    account a1 = new account(namet.getText(),jobt.getText(),date_1,date_2,passwordt.getText(),phonumt.getText());
                    listofa.add(a1);
                    JOptionPane.showMessageDialog(Sign_UP.this, "Your account number is: " + a1.getAccnum(), "Account Created", JOptionPane.INFORMATION_MESSAGE);
                    Sign_UP.this.dispose();
                }
            }
        });

        // if the user closes the window it returns to the main frame.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentF.setVisible(true);
            }
        });
    }
    // check if user input is only alpha or a space.
    public boolean isAlphaOrSpace(String txt) {
        boolean lastWasSpace = false; // Tracks if the last character was a space

        for (int i = 0; i < txt.length(); i++) {
            char c = txt.charAt(i); // checks what letter it is at that time

            if (Character.isLetter(c)) {
                lastWasSpace = false; // Reset the flag if the current character is a letter
            } else if (c == ' ') {
                if (lastWasSpace || i == 0 || i == txt.length() - 1) {
                    // Check for consecutive spaces or space at the beginning/end of the string
                    return false;
                }
                lastWasSpace = true;
            } else {
                return false; // Not a letter and not a valid space
            }
        }
        return true;
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
    // this function makes sure that the date of birth is not bigger than the current date.
    public boolean isSmaller_valid(String dateString) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        dateFormatter.setLenient(false);

        try {
            Date inputDate = dateFormatter.parse(dateString);

            // Get today's date without time for an accurate comparison
            Calendar currentCal = Calendar.getInstance();
            currentCal.set(Calendar.HOUR_OF_DAY, 0);
            currentCal.set(Calendar.MINUTE, 0);
            currentCal.set(Calendar.SECOND, 0);
            currentCal.set(Calendar.MILLISECOND, 0);
            Date currentDate = currentCal.getTime();

            if (inputDate.after(currentDate)) {
                JOptionPane.showMessageDialog(this, "The date cannot be in the future", "Input Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            return true;
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    // this function compares the date of creation to current date and makes sure it is not before the current date.
    public boolean isbigger_valid(String dateString) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        dateFormatter.setLenient(false);

        try {
            Date inputDate = dateFormatter.parse(dateString);

            // Get today's date without time for an accurate comparison
            Calendar currentCal = Calendar.getInstance();
            currentCal.set(Calendar.HOUR_OF_DAY, 0);
            currentCal.set(Calendar.MINUTE, 0);
            currentCal.set(Calendar.SECOND, 0);
            currentCal.set(Calendar.MILLISECOND, 0);
            Date currentDate = currentCal.getTime();

            if (inputDate.before(currentDate)) {
                JOptionPane.showMessageDialog(this, "Date of account creation cannot be in the past", "Input Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            return true;
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format for date of creation", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    private boolean validateInputs() {
        // check if name or job are correct
        if (!isAlphaOrSpace(namet.getText()) || !isAlphaOrSpace(jobt.getText()) || namet.getText().isEmpty() || jobt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and Profession must only contain letters and spaces or there are consecutive spaces or the space is not between characters or the input are empty ", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // check that the phone number is only numbers
        if (!isNumeric(phonumt.getText()) || phonumt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone number must only contain numbers or the input is empty", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // check that the dates are vaild
        if (!isSmaller_valid(datebt.getText())){
            return false;
        }
        if(!isbigger_valid(datect.getText()))
        {
            return false;
        }
        if(passwordt.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Password can not be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // All validations passed
        return true;
    }

}
