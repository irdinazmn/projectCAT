package org.payroll.EmployeeDetail;

import org.payroll.Main;
import org.payroll.Manager.DashboardFrame;
import org.payroll.TableToPDF;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Function: Display and download the employee personal detail table list
public class EmployeeDetailFrame extends JFrame{

    //Declare the element of the frame
    private JPanel EmployeeDetailPanel;
    private JButton JBtnReload;
    private JLabel JLblTitle;
    private JTable JTblEmployeeDetail;
    private JSplitPane RootPanel;
    private JButton JBtnDownload;
    private JButton JBtnBack;
    private JScrollPane JSPnlEmpDetail;



    public EmployeeDetailFrame() {
        //Set the size, layout and properties of the frame
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(RootPanel);
        setMinimumSize(new Dimension(750, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        //declaring Object[][] variable to store the data from the database
        Object[][] data = Main.dbManager.getEmployees();
        //Set the JTable based on the data from the chosen date in the databse
        String col[] = {"Employee ID", "First Name", "Last Name", "Email Address", "Position"};
        JTblEmployeeDetail.setModel(new DefaultTableModel(data,col));

        //Reload the table
        JBtnReload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                (new EmployeeDetailFrame()).setVisible(true);
                dispose();
            }
        });

        //Add action listener to call back the previous frame
        JBtnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                (new DashboardFrame()).setVisible(true);
                dispose();
            }
        });

        //Calling the function to convert the data into pdf
        JBtnDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TableToPDF().EmployeePDF(data);
                JOptionPane.showMessageDialog(
                        null,
                        "Download Successful",
                        "Download Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }
}
