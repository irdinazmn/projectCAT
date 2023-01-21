
package org.payroll;
import java.io.FileOutputStream;
import javax.swing.*;
import javax.swing.table.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class TableToPDF {

    public void EmployeePDF ( Object[][] Newdata ) // Convert Jtable Employee into PDF file
    {

        try {
            Object[][] data = Newdata;
            String col[] = {"Employee ID", "First Name", "Last Name", "Email Address", "Position"};

            DefaultTableModel model = new DefaultTableModel(data, col);
            JTable table = new JTable(model); //create Jtable using the input Object [][] data ;

            int count = table.getRowCount();// get total table column
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("C:\\Downloads\\Employee_List.PDF")); // specify path to download PDF
            document.open();
            float[] widths = {2, 3, 3, 5, 3}; // set the table column size porportion
            PdfPTable tab = new PdfPTable(widths);
            tab.setWidthPercentage(110); // set the table width
            tab.setSpacingBefore(10f); // add spacing before table
            tab.addCell("Employee ID");
            tab.addCell("First Name");
            tab.addCell("Last Name");
            tab.addCell("Email Address");
            tab.addCell("Position");

            for (int i = 0; i < count; i++) {

                Object obj1 = table.getValueAt(i, 0); //get the object value from Jtable
                Object obj2 = table.getValueAt(i, 1);
                Object obj3 = table.getValueAt(i, 2);
                Object obj4 = table.getValueAt(i, 3);
                Object obj5 = table.getValueAt(i, 4);

                String value1 = obj1.toString(); // convert the object value to string
                String value2 = obj2.toString();
                String value3 = obj3.toString();
                String value4 = obj4.toString();
                String value5 = obj5.toString();

                tab.addCell(value1); // add the string to cell in PDF file
                tab.addCell(value2);
                tab.addCell(value3);
                tab.addCell(value4);
                tab.addCell(value5);

            }
            document.add(tab); // add the table into pdf document
            document.close(); // close PDF document

        } catch(Exception e ){

        };

    }






}
