package org.payroll.ProfileManager;

import org.payroll.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Function: Form for user to fill in to change new username for manager user account
public class ChangeUsernameFrame extends JFrame{

    //Declare the element of the frame
    private JPanel ChangeUsernamePanel;
    private JButton JBtnCancel;
    private JButton JBtnChange;
    private JLabel JLblTitle;
    private JPasswordField JPFldPassword;
    private JTextField JTFldNewUsername;
    private JTextField JTFldConfirmUsername;
    private JLabel JLblPassword;
    private JLabel JLblNewUsername;
    private JLabel JLblConfirmUsername;

    public ChangeUsernameFrame() {

        //Set the size, layout and properties of the frame
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(ChangeUsernamePanel);
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
                String Pass = (JPFldPassword.getPassword()).toString();
                String N_UName = (JTFldNewUsername.getText());
                String C_UName = (JTFldConfirmUsername.getText());

                //Verifying the user input
                if (N_UName == C_UName) {
                    if (Main.dbManager.verifyLoginId(Pass)) {
                        Main.dbManager.changeUsername(N_UName, Pass);
                        JOptionPane.showMessageDialog(
                                null,
                                "Username Changed",
                                "Username Changed",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Username Change Failed\nYou enter wrong password",
                                "Username Change Failed",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        JPFldPassword.setText("");
                        JTFldNewUsername.setText("");
                        JTFldConfirmUsername.setText("");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Username Change Failed\nCannot confirm the new username",
                            "Cannot confirm the new username",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    JPFldPassword.setText("");
                    JTFldNewUsername.setText("");
                    JTFldConfirmUsername.setText("");
                }
            }
        });
    }
}
