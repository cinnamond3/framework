package framework.core.utilities;

import java.util.Date;

/**
 * Utility for converting {@link Date} to and from its unixtime equivalent.
 * 
 * @author frederick
 */
public interface DateUtils {

    /**
     * Converts an input unixtime to date.
     * 
     * @param unixtime
     *            the unixtime.
     * @return the converted date.
     */
    Date convertToDate(Long unixtime);

    /**
     * Converts an input date to unixtime.
     * 
     * @param date
     *            the date.
     * @return the equivalent unixtime.
     */
    Long convertToUnixTime(Date date);

    /**
     * Returns the current datetime.
     * 
     * @return the current datetime.
     */
    Date getCurrentTime();

    /**
     * Returns the current unixtime.
     * 
     * @return the current unixtime.
     */
    Long getCurrentUnixTime();
}
