package com.fireinyu.themyth.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.fireinyu.themyth.Defaults;
import com.fireinyu.themyth.exceptions.DateFormatException;



/**
 * Native datetime representation for The Myth/<br><br>
 * Uses LocalDateTime internally.
 * @see MythDateTime
 * @see LocalDateTime
 */
public class MythDateTime implements Comparable<MythDateTime> {

    private final LocalDateTime datetime;

    /**
     * Initialises a MythDateTime at a local datetime instant
     * @param datetime datetime instant of this MythDateTime
     * @see String
     * @see LocalDateTime
     */
    public MythDateTime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    /**
     * Returns the current local date and time.
     *
     * @return the current date and time
     */
    public static MythDateTime now() {
        return new MythDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }
    /**
     * Parse an input String into a MythDateTime<br><br>
     * the String format is length-variable from yyyy to yyyy-MM-dd-HH-mm-ss
     * @param datetime the input String
     * @return the MythDateTime instance corresponding to the input String
     * @see String
     */
    public static MythDateTime parse(String datetime) {
        String[] parts = datetime.split("-");
        if (parts.length == 0 || parts.length > 6) {
            throw new DateFormatException(datetime);
        }
        try {
            return new MythDateTime(LocalDateTime.of(
                    Integer.parseInt(parts[0]),
                    parts.length > 1 ? Integer.parseInt(parts[1]) : 1,
                    parts.length > 2 ? Integer.parseInt(parts[2]) : 1,
                    parts.length > 3 ? Integer.parseInt(parts[3]) : 0,
                    parts.length > 4 ? Integer.parseInt(parts[4]) : 0,
                    parts.length > 5 ? Integer.parseInt(parts[5]) : 0
            ));
        } catch (NumberFormatException e) {
            throw new DateFormatException(datetime);
        }
    }

    /**
     * Serialize this MythDateTime into a String<br><br>
     * the String format is yyyy-MM-dd-HH-mm-ss
     * @return the serialized String representing this MythDateTime
     * @see String
     */
    public String dump() {
        return this.datetime.format(DateTimeFormatter.ofPattern(Defaults.DATE_INPUTFORMAT));
    }

    /**
     * Returns whether this MythDateTime is between given start and end MythDateTimes<br><br>
     * @param start start MythDateTime
     * @param end end MythDateTime
     * @return whether this MythDateTime is between given start and end MythDateTimes
     */
    public boolean isBetween(MythDateTime start, MythDateTime end) {
        return this.isBefore(end) && start.isBefore(this);
    }

    /**
     * Returns whether this MythDateTime is before another MythDateTimes<br><br>
     * @param other other MythDateTime
     * @return whether this MythDateTime is before the other MythDateTime
     */
    public boolean isBefore(MythDateTime other) {
        return this.datetime.isBefore(other.datetime);
    }

    /**
     * Obtain a user-friendly String representation of this MythDateTime.
     * @return user-friendly String representation of this MythDateTime
     */
    @Override
    public String toString() {
        return datetime.format(DateTimeFormatter.ofPattern("LLL dd yyyy hh:mm:ss a"));
    }

    /**
     * Compares this date-time with another date-time.
     *
     * @param o date-time to compare with
     * @return a negative value, zero, or a positive value if this date-time is earlier than,
     *         equal to, or later than the other date-time
     */
    @Override
    public int compareTo(MythDateTime o) {
        return this.datetime.compareTo(o.datetime);
    }
}
