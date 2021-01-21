/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContext;
import model.Dao.DynamicDao;
import model.Helper.StoredProcedures;

/**
 * Web application lifecycle listener.
 *
 * @author me-aydin
 */
@WebListener()
public class listener implements ServletContextListener {

    private Connection conn = null;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        String db = sc.getInitParameter("dbname");
        //todo has to be a type table on the data base
        sc.setAttribute("docSalary", 11.0);
        sc.setAttribute("nurseSalary", 6.0);
        sc.setAttribute("clientCharge", 20.0);
        
        try {
                //Class.forName("com.mysql.jdbc.Driver");
                Class.forName("org.apache.derby.jdbc.ClientDriver");

                //conn = DriverManager.getConnection("jdbc:derby://localhost:1527/SmartCare_4","root","OqpWJsbw0X9164b38noF");
                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/SmartCare_9","root","root");
                //conn = DriverManager.getConnection("jdbc:derby://localhost:1527/SmartCare","root","root");
                
        }
        catch(ClassNotFoundException | SQLException e){
            sc.setAttribute("error", e);
        }
        DynamicDao dynamicDao = new DynamicDao();
        StoredProcedures storedProcedures = new StoredProcedures();
        dynamicDao.connect((Connection)sc.getAttribute("connection"));
        try {
                
        sc.setAttribute("docSalary", dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getDoctorBaseSalary)));
        sc.setAttribute("nurseSalary", dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getNurseBaseSalary)));
        sc.setAttribute("clientCharge", dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getPatientCost)));
        } catch (Exception e) {
        }
        sc.setAttribute("connection", conn);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try { conn.close(); } catch(SQLException e) {}
    }
}
