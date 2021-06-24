import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

class Utils {

    /**
     *  SimpleDateFormat is a concrete class for formatting and parsing dates in a locale-sensitive manner.
     *  When parsing dates ( parse() ), the SimpleDateFormat typically parses the date from a Java String.
     *  When formatting dates ( format() ), the SimpleDateFormat typically formats a Date object into a String.
     */

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    // parse() : convert a string into date
    static Date convertDate(String input) throws ParseException {
        return formatter.parse(input);
    }

    // format() : convert a Date object into string
    static String formattedDate(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}
