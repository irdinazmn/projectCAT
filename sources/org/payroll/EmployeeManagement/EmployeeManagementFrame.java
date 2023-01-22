package org.payroll.EmployeeManagement;

import org.payroll.Manager.DashboardFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Function: Display the Employee Management menu frame for user to choose
// either add, delete or update the employee data
public class EmployeeManagementFrame extends JFrame{

    //Declare the element of the frame
    private JPanel EmpManagementPanel;
    private JLabel JLblTitle;
    private JButton JBtnAddEmp;
    private JButton JBtnDeleteEmp;
    private JButton JBtnUpdateEmployee;
    private JButton JBtnBack;

    public EmployeeManagementFrame() {
        //Set the size, layout and properties of the frame
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(EmpManagementPanel);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        //Declaring all the action listener for every button in the frame to call other function
        // displaying a form for user to fill in order to change the employee data
        JBtnAddEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new AddEmployeeFrame()).setVisible(true);
                dispose();
            }
        });

        JBtnDeleteEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new DeleteEmployeeFrame()).setVisible(true);
                dispose();
            }
        });

        JBtnUpdateEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new UpdateEmployeeFrame()).setVisible(true);
                dispose();
            }
        });

        //Back action listener to call back the previous frame
        JBtnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                (new DashboardFrame()).setVisible(true);
                dispose();
            }
        });
    }
}
