package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TesterMain {

    public static void main(String[] args) {
        AuctionDate date = new AuctionDate(28, 6, 2018);
        
        System.out.print(date.compareToToday());
        
        
//        Calendar cal = Calendar.getInstance();
//        System.out.println(cal.get(Calendar.DAY_OF_MONTH));
        
    }

}
