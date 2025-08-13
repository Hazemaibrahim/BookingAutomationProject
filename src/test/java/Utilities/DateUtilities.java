package Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtilities {

    public static String extractDay(String dateStr) {
        try {
            // Try parsing yyyy-MM-dd
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return String.valueOf(date.getDayOfMonth());
        } catch (Exception e1) {
            try {
                // Try parsing dd-MMM-yyyy
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
                return String.valueOf(date.getDayOfMonth());
            } catch (Exception e2) {
                throw new RuntimeException("Invalid date format in Excel: " + dateStr);
            }
        }
    }
}