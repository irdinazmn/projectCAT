package org.payroll.ProfileManager;

import org.payroll.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Function: Form for user to fill in to change new password for manager user account
public class ChangePasswordFrame extends JFrame{

    //Declare the element of the frame
    private JPanel ChangePasswordPanel;
    private JPasswordField JPFldPassword;
    private JPasswordField JPFldNewPassword;
    private JPasswordField JPFldConfirmPassword;
    private JButton JBtnCancel;
    private JButton JBtnChange;
    private JLabel JLblTitle;
    private JLabel JLblPassword;
    private JLabel JLblNewPassword;
    private JLabel JLblConfirmPassword;

    public ChangePasswordFrame() {

        //Set the size, layout and properties of the frame
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(ChangePasswordPanel);
        setMinimumSize(new Dimension(550, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        //Cancel button to call the previous function
        JBtnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //Change action listener to update the data fill in by user into the database
        JBtnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String O_Pass = (JPFldPassword.getPassword()).toString();
                String N_Pass = (JPFldNewPassword.getPassword()).toString();
                String C_Pass = (JPFldConfirmPassword.getPassword()).toString();

                //Verifying the user input
                if (N_Pass == C_Pass) {
                    if (Main.dbManager.verifyLoginId(O_Pass)) {
                        Main.dbManager.changePassword(O_Pass, N_Pass);
                        JOptionPane.showMessageDialog(
                                null,
                                "Password Changed",
                                "Password Changed",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Password Change Failed\nYou enter wrong password",
                                "Password Change Failed",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        JPFldPassword.setText("");
                        JPFldNewPassword.setText("");
                        JPFldConfirmPassword.setText("");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Password Change Failed\nCannot confirm the new password",
                            "Cannot confirm the new password",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    JPFldPassword.setText("");
                    JPFldNewPassword.setText("");
                    JPFldConfirmPassword.setText("");
                }
            }
        });
    }
}
