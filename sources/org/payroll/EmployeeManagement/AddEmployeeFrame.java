package org.payroll.EmployeeManagement;

import org.payroll.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Function: Form for user to fill in to add a new employee
public class AddEmployeeFrame extends JFrame{

    //Declare the element of the frame
    private JPanel AddEmployeePanel;
    private JLabel JTFldTitle;
    private JTextField JTFldEmpID;
    private JTextField JTFldEmpFirstName;
    private JTextField JTFldEmpEmail;
    private JComboBox JCBoxPosition;
    private JButton JBtnAdd;
    private JButton JBtnCancel;
    private JLabel JLblEmpID;
    private JLabel JLbldEmpFirstName;
    private JLabel JLblEmpEmail;
    private JLabel JLblEmpPosition;
    private JLabel JLblLastName;
    private JTextField JTFldLastName;

    //declaring ArrayList<String> variable to store the data from the database
    ArrayList<String> positions = Main.dbManager.getListOfPositionName();

    public AddEmployeeFrame() {
        //Set the size, layout and properties of the frame
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(AddEmployeePanel);
        setMinimumSize(new Dimension(550, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        //Set the JComboBox based on the data from the databse
        JCBoxPosition.setModel(new DefaultComboBoxModel(positions.toArray(new String[positions.size()])));

        //Cancel button to call the previous function
        JBtnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new EmployeeManagementFrame()).setVisible(true);
                dispose();
            }
        });

        //Add button action listener to add all the detail user put into database
        JBtnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id, Fname, Lname, email, position;
                position = JCBoxPosition.getSelectedItem().toString();
                id = JTFldEmpID.getText();
                email = JTFldEmpEmail.getText();
                Fname = JTFldEmpFirstName.getText();
                Lname = JTFldLastName.getText();

                Main.dbManager.createEmployee(id, Fname, Lname, email, position);

                JOptionPane.showMessageDialog(
                        null,
                        "New Employee Added",
                        "New Employee Added",
                        JOptionPane.INFORMATION_MESSAGE
                );
                (new EmployeeManagementFrame()).setVisible(true);
                dispose();
            }
        });
    }
}
