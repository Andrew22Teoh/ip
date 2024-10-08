package rizzler.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser for any datetime objects input by the user.
 */
public class DateTimeParser {
    private static final String OUTPUT_DATE_FORMAT = "dd MMMM, yyyy";

    /**
     * Converts a string in <code>YYYY-MM-DD</code> format to a datetime object.
     * @param inputStr String representing a date in <code>YYYY-MM-DD</code> format.
     * @return <code>LocalDate</code> object representing the same date provided by the user.
     */
    public static LocalDate toDatetime(String inputStr) {
        assert inputStr != null : "inputStr is null";
        return LocalDate.parse(inputStr);
    }

    /**
     * Returns a boolean representing whether the <code>DateTimeParser</code> is able to parse
     * this particular input string.
     * @param inputStr User's input string.
     * @return Whether this input string can be parsed by <code>toDatetime</code>.
     */
    private static boolean canParse(String inputStr) {
        assert inputStr != null : "inputStr is null";
        try {
            toDatetime(inputStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Formats <code>inputDate</code> into a different datetime format if possible.
     * Else, returns <code>inputDate</code> without any formatting operations done.
     * @param inputDate String representing the date, ideally in <code>YYYY-MM-DD</code> format.
     * @return Formatted string for a particular date, or just <code>inputDate</code>.
     */
    public static String toStr(String inputDate) {
        assert inputDate != null : "inputDate is null";
        if (!canParse(inputDate)) {
            return inputDate;
        }
        LocalDate inputLocalDate = toDatetime(inputDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
        return inputLocalDate.format(formatter);
    }
}
