package org.payroll.Position;

import org.payroll.Main;
import org.payroll.Manager.DashboardFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Function: Display the Position menu frame for user to choose
// either add, delete the positions data and the table of list of positions that exist in database
public class PositionFrame extends JFrame {

    //Declare the element of the frame
    private JPanel JPnlPositionMenu;
    private JLabel JLblTitle;
    private JButton JBtnDelete;
    private JButton JBtnAdd;
    private JButton JBtnReload;
    private JButton JBtnBack;
    private JSplitPane PositionPanel;
    private JTable JTblListPostion;
    private JScrollPane JSPnlPosition;

    public PositionFrame() {
        //Set the size, layout and properties of the frame
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(PositionPanel);
        setMinimumSize(new Dimension(700, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        //declaring Object[][] variable to store the data from the database
        Object[][] data = Main.dbManager.getPositions();
        //Set the JTable based on the data from the database
        String col[] = {"Position ID","Position","Hourly Rate","Overtime Rate"};
        JTblListPostion.setModel(new DefaultTableModel(data,col));

        //Back action listener to call back the previous frame
        JBtnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                (new DashboardFrame()).setVisible(true);
                dispose();
            }
        });

        //Reload the table to get the position table
        JBtnReload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                (new PositionFrame()).setVisible(true);
                dispose();
            }
        });

        //Declaring all the action listener for every button in the frame to call other function
        // displaying a form for user to fill in order to change the position data
        JBtnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                (new AddPositionFrame()).setVisible(true);
                dispose();
            }
        });

        JBtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                (new DeletePositionFrame()).setVisible(true);
                dispose();
            }
        });
    }

}
