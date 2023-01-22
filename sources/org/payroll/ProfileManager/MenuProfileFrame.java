package org.payroll.ProfileManager;

import org.payroll.Manager.DashboardFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Function: Display the Employee Management menu frame for user to choose
// to change either username or password
public class MenuProfileFrame extends JFrame{

    //Declare the element of the frame
    private JPanel MenuProfilePanel;
    private JLabel JLblTitle;
    private JButton JBtnChangeUsername;
    private JButton JBtnChangePassword;
    private JButton JBtnBack;

    public MenuProfileFrame() {

        //Set the size, layout and properties of the frame
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(MenuProfilePanel);
        setMinimumSize(new Dimension(500, 400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        //Declaring all the action listener for every button in the frame to call other function
        // displaying a form for user to fill in order to change the employee data
        JBtnChangeUsername.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new ChangeUsernameFrame()).setVisible(true);
            }
        });

        JBtnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new ChangePasswordFrame()).setVisible(true);

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
