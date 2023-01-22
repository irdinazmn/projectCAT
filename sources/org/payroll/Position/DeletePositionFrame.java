package org.payroll.Position;

import org.payroll.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Function: Form for user to fill in to add a new position
public class DeletePositionFrame extends JFrame{

    //Declare the element of the frame
    private JPanel DeletePositionPanel;
    private JButton JBtnCancel;
    private JButton JBtnDelete;
    private JLabel JLblTitle;
    private JLabel JLblNamePost;
    private JComboBox JCBoxPosition;

    //declaring ArrayList<String> variable to store the data from the database
    ArrayList<String> positions = Main.dbManager.getListOfPositionName();

    public DeletePositionFrame() {
        //Set the size, layout and properties of the frame
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(DeletePositionPanel);
        setMinimumSize(new Dimension(550, 500));
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
                (new PositionFrame()).setVisible(true);
                dispose();
            }
        });

        //Delete button action listener to delete the desired employee based on the
        // existing position in the database
        JBtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String position = JCBoxPosition.getSelectedItem().toString();

                Main.dbManager.deletePosition(position);

                JOptionPane.showMessageDialog(
                        null,
                        "Position Deleted",
                        "Position Deleted",
                        JOptionPane.INFORMATION_MESSAGE
                );
                (new PositionFrame()).setVisible(true);
                dispose();
            }
        });
    }
}
