package util;

import com.fireinyu.themyth.Defaults;
import com.fireinyu.themyth.exceptions.ArgumentFormatException;
import com.fireinyu.themyth.exceptions.DateFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MythDateTime {
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

    private final LocalDateTime datetime;

    public MythDateTime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String dump() {
        return this.datetime.format(DateTimeFormatter.ofPattern(Defaults.DATE_INPUTFORMAT));
    }

    public boolean isBetween(MythDateTime start, MythDateTime end) {
        return this.isBefore(end) && start.isBefore(this);
    }

    public boolean isBefore(MythDateTime other) {
        return this.datetime.isBefore(other.datetime);
    }

    @Override
    public String toString() {
        return datetime.format(DateTimeFormatter.ofPattern("LLL dd yyyy hh:mm:ss a"));
    }
}
