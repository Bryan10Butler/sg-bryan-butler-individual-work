package com.sg.datetimecodealong;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.GregorianCalendar;

public class App {

    public static void main(String[] args) {

        //create instance ld from object LocalDate
        //.now() gives us the current date
        //2018-02-12
        LocalDate ld = LocalDate.now();
        System.out.println(ld);

        //reset ld
        //.parse able to parse a date of your choosing
        ld = LocalDate.parse("2015-01-01");
        System.out.println(ld);

        //.parse a date of our choosing and set the format as well
        ld = LocalDate.parse("02/07/2010", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(ld);

        //convert ld into a String as isoDate
        //print isoDate
        String isoDate = ld.toString();
        System.out.println(isoDate);

        //different way to look at this
        ld = LocalDate.parse(isoDate);
        System.out.println(ld);

        //pass int MM/DD/yyyy and store as formatted
        //print formatted
        String formatted = ld.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(formatted);

        //what does the += do
        formatted = ld.format(DateTimeFormatter.ofPattern("MM=dd=yyyy+=+=+="));
        System.out.println(formatted);

        formatted = ld.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);

        ///////////next video////////////

        //go back 6 days from today
        LocalDate past = ld.minusDays(6);
        formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);

        //back 3 months
        past = ld.minusMonths(3);
        formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);

        //object period
        Period diff = ld.until(past);
        System.out.println(diff);

        System.out.println(diff.getMonths());
        diff = past.until(ld);
        System.out.println(diff.getMonths());

        //////New Video///////

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        formatted = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        System.out.println(formatted);

        //different formatting
        Date legacyDate = new Date();
        System.out.println(legacyDate);

        //lets convert greg calendar into new date API
        GregorianCalendar legacyCalendar = new GregorianCalendar();
        System.out.println(legacyCalendar);

        //legacy date to local date
        ZonedDateTime zdt = ZonedDateTime.ofInstant(legacyDate.toInstant(), ZoneId.systemDefault());
        ld = zdt.toLocalDate();
        System.out.println(ld);

        zdt = legacyCalendar.toZonedDateTime();
        ld = zdt.toLocalDate();
        System.out.println(ld);







    }
}
