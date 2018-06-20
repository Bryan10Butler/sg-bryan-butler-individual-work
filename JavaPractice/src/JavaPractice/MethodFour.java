package JavaPractice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.UnsupportedTemporalTypeException;

public class MethodFour {
    public static void main(String[] args) {

        System.out.println("Running example with LocalDate class.");
        LocalDate now = LocalDate.now();
        try
        {
            System.out.println("ISO_DATE: " + now.format(DateTimeFormatter.ISO_DATE));
        } catch (UnsupportedTemporalTypeException e)
        {
            System.out.println("ISO_DATE is not supported: " + e.getMessage());
        }
        try
        {
            System.out.println("BASIC_ISO_DATE: " + now.format(DateTimeFormatter.BASIC_ISO_DATE));
        } catch (UnsupportedTemporalTypeException e)
        {
            System.out.println("BASIC_ISO_DATE is not supported: " + e.getMessage());
        }
        try
        {
            System.out.println("ISO_DATE_TIME: " + now.format(DateTimeFormatter.ISO_DATE_TIME));
        } catch (UnsupportedTemporalTypeException e)
        {
            System.out.println("ISO_DATE_TIME is not supported: " + e.getMessage());
        }
        try
        {
            System.out.println("ISO_INSTANT: " + now.format(DateTimeFormatter.ISO_INSTANT));
        } catch (UnsupportedTemporalTypeException e)
        {
            System.out.println("ISO_INSTANT is not supported: " + e.getMessage());
        }
        try
        {
            System.out.println("ISO_LOCAL_DATE: " + now.format(DateTimeFormatter.ISO_LOCAL_DATE));
        } catch (UnsupportedTemporalTypeException e)
        {
            System.out.println("ISO_LOCAL_DATE is not supported: " + e.getMessage());
        }
        try
        {
            System.out.println("ISO_LOCAL_DATE_TIME: " + now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        } catch (UnsupportedTemporalTypeException e)
        {
            System.out.println("ISO_LOCAL_DATE_TIME is not supported: " + e.getMessage());
        }
        try
        {
            System.out.println("ISO_LOCAL_TIME: " + now.format(DateTimeFormatter.ISO_LOCAL_TIME));
        } catch (UnsupportedTemporalTypeException e)
        {
            System.out.println("ISO_LOCAL_TIME is not supported: " + e.getMessage());
        }
        try
        {
            System.out.println("ISO_OFFSET_DATE: " + now.format(DateTimeFormatter.ISO_OFFSET_DATE));
        } catch (UnsupportedTemporalTypeException e)
        {
            System.out.println("ISO_OFFSET_DATE is not supported: " + e.getMessage());
        }
        try
        {
            System.out.println("ISO_OFFSET_DATE_TIME: " + now.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        } catch (UnsupportedTemporalTypeException e)
        {
            System.out.println("ISO_OFFSET_DATE_TIME is not supported: " + e.getMessage());
        }
        try
        {
            System.out.println("ISO_OFFSET_TIME: " + now.format(DateTimeFormatter.ISO_OFFSET_TIME));
        } catch (UnsupportedTemporalTypeException e)
        {
            System.out.println("ISO_OFFSET_TIME is not supported: " + e.getMessage());
        }
        try
        {
            System.out.println("ISO_ORDINAL_DATE: " + now.format(DateTimeFormatter.ISO_ORDINAL_DATE));
        } catch (UnsupportedTemporalTypeException e)
        {
            System.out.println("ISO_ORDINAL_DATE is not supported: " + e.getMessage());
        }
        try
        {
            System.out.println("ISO_TIME: " + now.format(DateTimeFormatter.ISO_TIME));
        } catch (UnsupportedTemporalTypeException e)
        {
            System.out.println("ISO_TIME is not supported: " + e.getMessage());
        }
        try
        {
            System.out.println("ISO_WEEK_DATE: " + now.format(DateTimeFormatter.ISO_WEEK_DATE));
        } catch (UnsupportedTemporalTypeException e)
        {
            System.out.println("ISO_WEEK_DATE is not supported: " + e.getMessage());
        }
        try
        {
            System.out.println("ISO_ZONED_DATE_TIME: " + now.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        } catch (UnsupportedTemporalTypeException e)
        {
            System.out.println("ISO_ZONED_DATE_TIME is not supported: " + e.getMessage());
        }
        try
        {
            System.out.println("RFC_1123_DATE_TIME: " + now.format(DateTimeFormatter.RFC_1123_DATE_TIME));
        } catch (UnsupportedTemporalTypeException e)
        {
            System.out.println("RFC_1123_DATE_TIME is not supported: " + e.getMessage());
        }

    }

}
