import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.text.SimpleDateFormat;
public class AdminUi extends JDialog {
    List<account> accounts = list.getInstance();
    JButton delete;
    JTable table;
    public AdminUi(JFrame parentF)
    {
        super(parentF,"Admin UI",true);
        this.setBounds(100, 100, 775, 676);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 741, 487);
        this.getContentPane().add(scrollPane);

        String[] columnNames = {"Account Number", "Name", "Job", "Date of Creation", "Date of Birth", "Balance","Phone number"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane.setViewportView(table);

        populateTable();
        // Add TableModelListener to handle cell edits

        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int col = e.getColumn(); // I will only edit name and
                if (col == 1 || col == 2 || col == 6) {
                    updateAccount(row, col, (String) model.getValueAt(row, col));
                }
            }
        });
        delete = new JButton("Delete");
        delete.setBounds(236, 521, 247, 73);
        this.getContentPane().add(delete);

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteSelectedRow();
            }
        });
    }
    public void populateTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // just to make sure every runs.

        for (account acc : accounts) {
            Object[] rowData = new Object[]{
                    acc.getAccnum(),
                    acc.getName(),
                    acc.getJob(),
                    dateFormat.format(acc.getDateofc()),
                    dateFormat.format(acc.getDateofb()),
                    acc.getBalance(),
                    acc.getPnum()
            };
            model.addRow(rowData);
        }
    }
    private void deleteSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // remove the account through the index
            accounts.remove(selectedRow);

            // Remove from the table model
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void updateAccount(int row, int col, String newValue) {
        account acc = accounts.get(row);
        switch (col) {
            case 1:
                acc.setName(newValue);
                break;
            case 2:
                acc.setJob(newValue);
                break;
            case 6:
                acc.setpnum(newValue);
                break;
        }
    }
}
