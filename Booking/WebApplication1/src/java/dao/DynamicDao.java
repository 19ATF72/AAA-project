package dao;

import model.UserModel;
import model.OrganisationEntity;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
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

//    public void tryConnect(){
//        
//        DynamicDao bookingDao = new DynamicDao();
//        Connection conn = null;
//        try {
//                Class.forName("org.apache.derby.jdbc.ClientDriver");
//                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/SmartCare_2","root","OqpWJsbw0X9164b38noF");
//        }
//        catch(ClassNotFoundException | SQLException e){
//            System.out.println(e);
//        }
//        connect(conn);
//    }
//    
    protected void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
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
    
    
//    public List<Organisation> listAllOrganisations() throws SQLException{
//        List<Organisation> 
//    }
    
    
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
                case "Date":
                    //prep_statement.setDate(param_index, (Date)param);
                    //prep_statement.setDate(param_index, new java.sql.Date((Date)param.getTime()));
                    prep_statement.setDate(param_index, (java.sql.Date)param);
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
    
 
    
    /* 
     * @name function_name 
     *
     *
     * @brief function which will receive any sql query with any amount of parameters of any type
     *        and turn it into a prepared statement, which will then be executed
     *        in order to retrieve the data requested from the data base or 
     *        insert data into the data base depending on the query.
     *        \todo change to "perform appropriate operation depending on the query type" once thsi function handlles all query types that will be used in this project  
     *
     * @param[out] rs result set containing the data retrieved from the database
     * @param[out] create new entry on the database(Only happens if query is of INSERT type)  
     *
     * @param[in] query String contating the sql query to be turned into a prepared statement
     * @param[in] Params List of parameters of Generic types(any type) which will be set into the prepared statement      
     *
     * @returns autoGeneratedId The autogenerated key(index of the record) of the entry inserted on the database  
     * @Throws SQLException
     */    
    public <T> ArrayList agnostic_query(String query, T ... Params)throws SQLException {
        //Statement statement = null;
            ArrayList result = new ArrayList();
            int autoGeneratedId = 0;
            PreparedStatement prep_statement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            for(int parameter = 0; parameter < Params.length; parameter++ ) {
                String[] param_type_sequence = Params[parameter].getClass().getName().split("\\.");
                String param_type = param_type_sequence[param_type_sequence.length - 1];
                choose_type(param_type, prep_statement, (parameter+1), Params[parameter]);
            }
            // might need to be adapted to return multiple generated keys
            if(query.contains("INSERT") || query.contains("UPDATE")){
                prep_statement.executeUpdate();
                rs = prep_statement.getGeneratedKeys();
                while(rs.next() && rs != null)
                {
                    autoGeneratedId = rs.getInt(1);   
                }
                result.add(autoGeneratedId);
            }else{
                rs = prep_statement.executeQuery();
                result = rsToList();
            }
           
           return result; //statement.close();
    }
    
public Connection getCon(){
    return this.connection;
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

//Run This to populate the time Slots table 
public void addTimeSlots() {
        int EghitOclock = 28800;
        int TenMinutes = 600;
        int FiveOclock = 61200;
        int time = EghitOclock;
        int previousTime = time;
        int index = 0;
        try {
         ArrayList isAppointmentPopulated =agnostic_query("SELECT * FROM appointment_slots");
        
       
        if(isAppointmentPopulated.size() == 0){
        while (true) {
            if(time == FiveOclock)
            {
                break;
            }
            time += TenMinutes;
            
            try {

                agnostic_query("INSERT INTO appointment_slots ( start_time, end_time ) VALUES ( ?,? )", previousTime, time);
                
            } catch (Exception e) {
            }
            index++;
            previousTime = time;
        }
        }
    } catch (Exception e) {
        }
}
   
   
    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) throws SQLException {
      
       
        DynamicDao bookingDao = new DynamicDao();
        Connection conn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/SmartCare","root","root");
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        bookingDao.connect(conn);
        
        //System.out.println(bookingDao.retrieve());
 
    }            


   
}
