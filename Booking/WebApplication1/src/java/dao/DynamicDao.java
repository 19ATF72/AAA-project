package dao;

import model.UserModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AppointmentModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rob
 */
public class DynamicDao{
        
    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
  
    public void tryConnect(){
        
        DynamicDao bookingDao = new DynamicDao();
        Connection conn = null;
        try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/SmartCare","root","root");
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        connect(conn);
    }
    
    public void connect(Connection con){
       connection = con;
    }
    
    private ArrayList rsToList() throws SQLException {
        ArrayList aList = new ArrayList();

        int cols = rs.getMetaData().getColumnCount();
        while (rs.next()) { 
          String[] s = new String[cols];
          for (int i = 1; i <= cols; i++) {
            s[i-1] = rs.getString(i);
          } 
          aList.add(s);
        } // while    
        return aList;
    } //rsToList
 
//    private String makeTable(ArrayList list) {
//        StringBuilder b = new StringBuilder();
//        String[] row;
//        b.append("<table border=\"3\">");
//        for (Object s : list) {
//          b.append("<tr>");
//          row = (String[]) s;
//            for (String row1 : row) {
//                b.append("<td>");
//                b.append(row1);
//                b.append("</td>");
//            }
//          b.append("</tr>\n");
//        } // for
//        b.append("</table>");
//        return b.toString();
//    }//makeHtmlTable
//    
    private void select(String query){
        //Statement statement = null;
        
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            //statement.close();
        }
        catch(SQLException e) {
            System.out.println("way way"+e);
            //results = e.toString();
        }
    }
    
    public AppointmentModel retrieveAppointment(String query)  {
        select(query);
        try {
            
            if (rs==null)
                System.out.println("rs is null");
            else
            {
                AppointmentModel appointmentModel = new AppointmentModel();
                appointmentModel.setAddress(rs.getString(1)); //Gets Column 1
            }
                
                
        
        } catch (SQLException ex) {
            Logger.getLogger(DynamicDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean exists(String sqlQuery) {
        boolean bool = false;
        try  {
            select(sqlQuery);
            if(rs.next()) {
                System.out.println("TRUE");         
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DynamicDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    public void insert(String sqlQuery, String[] str){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sqlQuery ,PreparedStatement.RETURN_GENERATED_KEYS);
            
            for (int i = 0; i < str.length; i++)
            {
                ps.setString(i + 1, str[i]);
            }
           
            ps.executeUpdate();
            ps.close();
            
            System.out.println("TODO LOGGING");
        
        } catch (SQLException ex) {
            Logger.getLogger(DynamicDao.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
//    public void registerAppointment(UserModel bookingModel){
//        System.out.println("reeee");
//        tryConnect();
//        try {
//            PreparedStatement ps = connection.prepareStatement("SELECT appointment_time FROM available_appointments WHERE booked=FALSE");
//            //ps.setInt(4, 1);INSERT INTO appointments (TIME, DOCTOR, PATIENT, ID) VALUES (?,?,?,?)
//            ps.executeUpdate();
//        
//            ps.close();
//            System.out.println("rows added.");
//        } catch (SQLException ex) {
//            Logger.getLogger(DynamicDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         
//    }
    

    
//    public AppointmentModel fetchAvailableAppointments(){
//        
//        AppointmentModel appointmentModel = new AppointmentModel(); //TEMP FOR TESTING
//        
//        try {
//            ArrayList<String> availableAppointmentTimes = new ArrayList<String>();
//            
//            Statement statement = connection.createStatement();
//            ResultSet result = statement.executeQuery("SELECT appointment_time FROM available_appointments WHERE booked=false");
//          
//            while(result.next()){
//                availableAppointmentTimes.add(result.getString("APPOINTMENT_TIME"));
//            }
//            
//            appointmentModel.setAvailableAppointments(availableAppointmentTimes);
//            
//         
//        } catch (SQLException ex) {
//            Logger.getLogger(DynamicDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    ///////////////////////////
   
    public void update(String sqlQuery, String[] str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sqlQuery ,PreparedStatement.RETURN_GENERATED_KEYS);
            
            for (int i = 0; i < str.length; i++)
            {
                ps.setString(i + 1, str[i]);
            }
           
            ps.executeUpdate();
            ps.close();
            
          
            System.out.println("TODO LOGGING");
        
        } catch (SQLException ex) {
            Logger.getLogger(DynamicDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(String sqlQuery, String[] rowToDelete){ 
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlQuery + rowToDelete);
        }
        catch(SQLException e) {
            System.out.println("way way"+e);
            //results = e.toString();
        }
    }
    public void closeAll(){
        try {
            statement.close(); 		
            //connection.close();                                         
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) throws SQLException {
        //String str = "select * from users";
        //String insert = "INSERT INTO `Users` (`username`, `password`) VALUES ('meaydin', 'eaydin')";
        //tring update = "UPDATE `Users` SET `password`='eaydin' WHERE `username`='meaydin' ";
        //String db = "MyDB";
        
        DynamicDao bookingDao = new DynamicDao();
        Connection conn = null;
        try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
//Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/SmartCare","root","root");
//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db.trim(), "root", "");
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        bookingDao.connect(conn);
        
        //System.out.println(bookingDao.retrieve());
 
    }            


   
}
