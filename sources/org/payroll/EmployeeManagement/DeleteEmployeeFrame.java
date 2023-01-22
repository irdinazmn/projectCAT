package org.payroll.EmployeeManagement;

import org.payroll.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Function: Form for user to fill in to delete a new employee
public class DeleteEmployeeFrame extends JFrame{

    //Declare the element of the frame
    private JPanel DeleteEmployeePanel;
    private JLabel JLblTitle;
    private JTextField JTFldEmpID;
    private JButton JBtnCancel;
    private JButton JBtnDelete;
    private JLabel JLblEmpID;

    public DeleteEmployeeFrame() {
        //Set the size, layout and properties of the frame
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(DeleteEmployeePanel);
        setMinimumSize(new Dimension(450, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        //Cancel button to call the previous function
        JBtnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new EmployeeManagementFrame()).setVisible(true);
                dispose();
            }
        });

        //Delete button action listener to delete the desired employee based on employee id
        JBtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Id = JTFldEmpID.getText();
                if (Main.dbManager.verifyLoginId(Id)) {

                    Main.dbManager.deleteEmployee(Id);

                    JOptionPane.showMessageDialog(
                            null,
                            "Employee Deleted",
                            "Employee Deleted",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
                else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Delete employee Failed\nCannot confirm the employee ID",
                            "Delete employee failed",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
                (new EmployeeManagementFrame()).setVisible(true);
                dispose();
            }
        });
    }
}
