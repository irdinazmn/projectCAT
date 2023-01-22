package org.payroll.EmployeeAttendance;

import com.toedter.calendar.JDateChooser;
import org.payroll.Main;
import org.payroll.Manager.DashboardFrame;
import org.payroll.TableToPDF;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/*Function: Display and download The attendance table that are in the database or user can choose
            a specific date of attendance data to display in the attendance table*/
public class EmployeeAttendanceFrame extends JFrame{

    //Declare the element of the frame
    private JSplitPane EmployeeAttendancePanel;
    private JPanel JPnlEmployeeAtenndance;
    private JLabel JLblTitle;
    private JLabel JLblDate;
    private JPanel JPnlDate;
    private JLabel JLblDateTry;
    private JButton JBtnGet;
    private JScrollPane JSPnlEmpAtten;
    private JTable JTblEmpAttend;
    private JButton JBtnBack;
    private JButton JBtnDownload;

    //To display the current date and calendar in the frame
    Calendar cal = Calendar.getInstance();
    JDateChooser dateChooser = new JDateChooser();

    public EmployeeAttendanceFrame() {

        //Set the size, layout and properties of the frame
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(EmployeeAttendancePanel);
        setMinimumSize(new Dimension(750, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

        //declaring Object[][] variable to store the data from the database
        Object[][] attendance = Main.dbManager.getAllAttendance();
        //Set the JTable based on the data from the database
        String col[] ={"ID","Employee ID","Employee Full Name","Date","Clock In Time","Clock Out Time"};
        JTblEmpAttend.setModel(new DefaultTableModel(attendance, col));

        //Setting the format of the date that user choose
        dateChooser.setDateFormatString("yyyy-MM-dd");

        //Assigning the dateChooser to a panel to display the calendar in the frame
        JPnlDate.add(dateChooser);

        JLblDateTry.setVisible(false);

        JBtnGet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To retrieve the date that user choose to see
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dat = dateChooser.getDate();
                String dat1 = sdf.format(dat);
                JLblDateTry.setVisible(true);
                JLblDateTry.setText(dat1);

                //Assigning the date to the database
                Object[][] Attendance_data = Main.dbManager.getAttendance(dat1);
                //Set the JTable based on the data from the chosen date in the database
                JTblEmpAttend.setModel(new DefaultTableModel(Attendance_data, col));
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

        //Calling the function to convert the data into pdf
        JBtnDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*If the user did not choose any specific date, then it
                  will convert all attendance data in database*/
                if (JLblDateTry.isVisible()){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date dat = dateChooser.getDate();
                    String dat1 = sdf.format(dat);
                    Object[][] Attendance_data = Main.dbManager.getAttendance(dat1);
                    new TableToPDF().AttandancePDF(Attendance_data);
                    JOptionPane.showMessageDialog(
                            null,
                            "Download Successful",
                            "Download Successful",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
                else {
                    new TableToPDF().AttandancePDF(attendance);
                    JOptionPane.showMessageDialog(
                            null,
                            "Download Successful",
                            "Download Successful",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });
    }
}
