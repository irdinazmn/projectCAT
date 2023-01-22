package org.payroll.EmployeeSalary;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import org.payroll.Main;
import org.payroll.Manager.DashboardFrame;
import org.payroll.TableToPDF;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/*Function: Display and download The employee salary table that are in the database or user can choose
            a specific month and year of salary data to display in the employee salary table*/
public class EmployeeSalaryFrame extends JFrame{

    //Declare the element of the frame
    private JSplitPane EmployeeSalaryPanel;
    private JPanel EmployeeAttendancePanel;
    private JTable JTblReport;
    private JScrollPane JSPnReport;
    private JLabel JLblTitle;
    private JLabel JTFldMonth;
    private JLabel JTFldYear;
    private JPanel JPnlYear;
    private JButton JBtnGet;
    private JLabel JLblYear;
    private JLabel JLblMonth;
    private JPanel JPnlMonth;
    private JButton JBtnBack;
    private JButton JBtnDownload;

    //To display the choice of month and year in the frame
    JYearChooser yearChooser = new JYearChooser();
    JMonthChooser monthChooser = new JMonthChooser();

    public EmployeeSalaryFrame() {
        //Set the size, layout and properties of the frame
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(EmployeeSalaryPanel);
        setMinimumSize(new Dimension(750, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

        //Assigning the yearChooser and monthChooser to a panel to display the choice
        // of month and year in the frame for user to choose
        JPnlYear.add(yearChooser);
        JPnlMonth.add(monthChooser);

        JLblMonth.setVisible(false);
        JLblYear.setVisible(false);

        //declaring Object[][] variable to store the data from the database
        Object[][] data = Main.dbManager.getAllMonthlySalary();
        //Set the JTable based on the data from the database
        String col[] = {"Employee ID","Employee Full Name","Month","Year","Total Salary","Total Hours Worked"};
        JTblReport.setModel(new DefaultTableModel(data,col));

        JBtnGet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To retrieve the date that user choose to see
                Integer yr = yearChooser.getYear();
                JLblYear.setText(yr.toString());
                Integer mnth = monthChooser.getMonth();
                mnth = mnth + 1;
                JLblMonth.setText(mnth.toString());
                JLblMonth.setVisible(true);
                JLblYear.setVisible(true);

                //Assigning the date to the database
                Object[][] Ndata = Main.dbManager.getSalaryByMonthAndYear(mnth, yr);
                //Set the JTable based on the data from the chosen date in the database
                JTblReport.setModel(new DefaultTableModel(Ndata,col));
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
                if (JLblMonth.isVisible() && JLblYear.isVisible()){
                    Integer yr = yearChooser.getYear();
                    JLblYear.setText(yr.toString());
                    Integer mnth = monthChooser.getMonth();
                    mnth = mnth + 1;
                    Object[][] Ndata = Main.dbManager.getSalaryByMonthAndYear(mnth, yr);
                    new TableToPDF().SalaryPDF(Ndata);
                    JOptionPane.showMessageDialog(
                            null,
                            "Download Successful",
                            "Download Successful",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
                else {
                    new TableToPDF().SalaryPDF(data);
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
