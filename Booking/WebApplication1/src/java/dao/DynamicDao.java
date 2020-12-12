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
import java.sql.Timestamp;
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
    
    public DynamicDao() {}
  
    public void tryConnect(){
        
        DynamicDao bookingDao = new DynamicDao();
        Connection conn = null;
        try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/SmartCare_2","root","OqpWJsbw0X9164b38noF");
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
    
    private <T> void choose_type(String type, PreparedStatement prep_statement, int param_index, T param){
        try {
            switch(type) {
                case "String":
                    prep_statement.setString(param_index, (String)param);
                  break;
                case "Integer":
                    prep_statement.setInt(param_index, (Integer)param);
                  break;
                case "Double":
                    prep_statement.setDouble(param_index, (Double)param);
                  break;
                case "Boolean":
                    prep_statement.setBoolean(param_index, (Boolean)param);
                  break;
                case "Timestamp":
                    prep_statement.setTimestamp(param_index, (Timestamp)param);
                  break;
                default:
                    int p = 0;
            }
        }
        catch(SQLException e) {
            System.out.println("fail cho0se type"+e);
            //results = e.toString();
        }
    } 
    
    public <T> void agnostic_query(String query, T ... Params){
        //Statement statement = null;
       
        try {
            PreparedStatement prep_statement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            for(int parameter = 0; parameter < Params.length; parameter++ ) {
                String[] param_type_sequence = Params[parameter].getClass().getName().split("\\.");
                String param_type = param_type_sequence[param_type_sequence.length - 1];
                choose_type(param_type, prep_statement, (parameter+1), Params[parameter]);
            }
            if(query.contains("INSERT")){
                prep_statement.executeUpdate();
            }else{
                rs = prep_statement.executeQuery();    
            }
           
            //statement.close();
        }
        catch(SQLException e) {
            System.out.println("fail agnostic query"+e);
            //results = e.toString();
        }
    }
    
    private void string_query(String query, String ... Params){
        //Statement statement = null;
       
        try {
            PreparedStatement prep_statement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            for(int parameter = 0; parameter < Params.length; parameter++ ) {
                  prep_statement.setString((parameter + 1), Params[parameter]);
            }
            rs = prep_statement.executeQuery();
            //statement.close();
        }
        catch(SQLException e) {
            System.out.println("fail string_query"+e);
            //results = e.toString();
        }
    }
    private void select(String query){
        //Statement statement = null;
       
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            //statement.close();
        }
        catch(SQLException e) {
            System.out.println("fail select"+e);
            //results = e.toString();
        }
    }
    
        public <T> ArrayList agnostic_retrieve(String query, T ... Params) throws SQLException {
        String results="";
        agnostic_query(query, Params);
        //        try {
        //            
        //            if (rs==null)
        //                System.out.println("rs is null");
        //            else
        //                results = makeTable(rsToList());
        //        } catch (SQLException ex) {
        //            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        //        }
        return rsToList();//results;
    }
    
    public ArrayList retrieve(String query) throws SQLException {
        String results="";
        select(query);
        //        try {
        //            
        //            if (rs==null)
        //                System.out.println("rs is null");
        //            else
        //                results = makeTable(rsToList());
        //        } catch (SQLException ex) {
        //            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        //        }
        return rsToList();//results;
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
            System.out.println("fail delete"+e);
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
