/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author rob
 */
public class CalendarService {
    
    SimpleDateFormat sdfHours = new SimpleDateFormat("HH");	
    SimpleDateFormat sdfMins = new SimpleDateFormat("mm");	
    Calendar calendar = new GregorianCalendar(2013,1,28,13,24,56);

    int year       = calendar.get(Calendar.YEAR);
    int month      = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); 
    int dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK);
    int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
    int weekOfMonth= calendar.get(Calendar.WEEK_OF_MONTH);

    int hour       = calendar.get(Calendar.HOUR);        // 12 hour clock
    int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
    int minute     = calendar.get(Calendar.MINUTE);
    int second     = calendar.get(Calendar.SECOND);
    int millisecond= calendar.get(Calendar.MILLISECOND);
       
    Clock clock = Clock.systemDefaultZone();
    
    CalendarService(){
         
        System.out.println(sdf.format(calendar.getTime()));
        
        System.out.println("year \t\t: " + year);
        System.out.println("month \t\t: " + month);
        System.out.println("dayOfMonth \t: " + dayOfMonth);
        System.out.println("dayOfWeek \t: " + dayOfWeek);
        System.out.println("weekOfYear \t: " + weekOfYear);
        System.out.println("weekOfMonth \t: " + weekOfMonth);

        System.out.println("hour \t\t: " + hour);
        System.out.println("hourOfDay \t: " + hourOfDay);
        System.out.println("minute \t\t: " + minute);
        System.out.println("second \t\t: " + second);
        System.out.println("millisecond \t: " + millisecond);
        
    }
    
    public String[] createCurrentDay(){
        if(hourOfDay < 8 || hourOfDay > 18)
            System.out.println("Out of hours");
        else if (dayOfWeek > 5)
            System.out.println("Weekend");
        else
        {
            //Valid Day
            
            int invalidHoursCounter = 0;
            List<String> list = new ArrayList<>();
            
            for (int i = hourOfDay; i < 19; i++) 
            {
                for (int j = 0; j < 6; j++)
                {
                    double availableAppointment = hourOfDay + (i/10);
                    DecimalFormat df = new DecimalFormat("#0.00");
                    list.add(df.format(availableAppointment));
                }
            }
            
            //TODO: 
            //Add mins 
        }
    }
    
   
}
