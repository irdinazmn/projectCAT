package org.payroll.Manager;

import org.payroll.EmployeeAttendance.EmployeeAttendanceFrame;
import org.payroll.EmployeeDetail.EmployeeDetailFrame;
import org.payroll.EmployeeManagement.EmployeeManagementFrame;
import org.payroll.EmployeeSalary.EmployeeSalaryFrame;
import org.payroll.Position.PositionFrame;
import org.payroll.ProfileManager.MenuProfileFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Function: Display the Dashboard menu frame for user to choose
// the activity frame that they want to do
public class DashboardFrame extends JFrame{

    //Declare the element of the frame
    private JPanel DashboardPanel;
    private JLabel JLblDashboard;
    private JButton JBtnEmpManage;
    private JButton JBtnEmpDetail;
    private JButton JBtnEmpAttendance;
    private JButton JBtnEmpSalary;
    private JMenuBar JMBDashboard;
    private JButton JBtnProfile;
    private JButton JBtnHelp;
    private JButton JBtnQuit;
    private JButton JBtnPosition;

    public DashboardFrame() {
        //Set the size, layout and properties of the frame
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(DashboardPanel);
        setMinimumSize(new Dimension(900, 680));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        //Declaring all the action listener for every button in the frame to call other function
        JBtnEmpManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                (new EmployeeManagementFrame()).setVisible(true);
            }
        });

        JBtnEmpDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                (new EmployeeDetailFrame()).setVisible(true);
            }
        });

        JBtnEmpSalary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                (new EmployeeSalaryFrame()).setVisible(true);
            }
        });

        JBtnEmpAttendance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                (new EmployeeAttendanceFrame()).setVisible(true);
            }
        });

        JBtnProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new MenuProfileFrame()).setVisible(true);
                dispose();
            }

        });

        JBtnHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Employee Payroll Syatem\nAuthor: Group 51 ",
                        "About",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        JBtnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new ManagerLoginFrame()).setVisible(true);
                dispose();
            }
        });

        JBtnPosition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new PositionFrame()).setVisible(true);
                dispose();
            }
        });
    }
}
