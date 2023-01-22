package org.payroll.EmployeeManagement;

import org.payroll.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Function: Form for user to fill in to add a new employee
public class UpdateEmployeeFrame extends JFrame{

    //Declare the element of the frame
    private JPanel UpdateEmployeePanel;
    private JLabel JLblTitle;
    private JComboBox JCBoxEmpId;
    private JTextField JTFldEmpFirstName;
    private JTextField JTFldEmpEmail;
    private JButton JBtnCancel;
    private JButton JBtnUpadate;
    private JLabel JLblEmpID;
    private JLabel JLblEmpFirstName;
    private JLabel JLblEmail;
    private JLabel JLblPosition;
    private JComboBox JCBoxEmpPosition;
    private JLabel JTFldEmpLastName;
    private JTextField JTFldLastName;

    //declaring ArrayList<String> variable to store the data from the database
    ArrayList<String> position = Main.dbManager.getListOfPositionName();
    ArrayList<String> id_emp = Main.dbManager.getEmployeeID();

    public UpdateEmployeeFrame() {
        //Set the size, layout and properties of the frame
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(UpdateEmployeePanel);
        setMinimumSize(new Dimension(550, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        //Set the JComboBox based on the data from the databse
        JCBoxEmpPosition.setModel(new DefaultComboBoxModel(position.toArray(new String[position.size()])));
        JCBoxEmpId.setModel(new DefaultComboBoxModel(id_emp.toArray(new String[id_emp.size()])));

        //Cancel button to call the previous function
        JBtnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                (new EmployeeManagementFrame()).setVisible(true);
                dispose();
            }
        });

        //Update button action listener to update all the detail user put into database
        JBtnUpadate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id, Fname, Lname, email, position;
                position = JCBoxEmpPosition.getSelectedItem().toString();
                id = JCBoxEmpId.getSelectedItem().toString();
                email = JTFldEmpEmail.getText();
                Fname = JTFldEmpFirstName.getText();
                Lname = JTFldLastName.getName();

                Main.dbManager.updateEmployee(id, Fname, Lname,email, position);

                JOptionPane.showMessageDialog(
                        null,
                        "Employee Updated",
                        "Employee Updated",
                        JOptionPane.INFORMATION_MESSAGE
                );

                (new EmployeeManagementFrame()).setVisible(true);
                dispose();
            }
        });
    }
}
