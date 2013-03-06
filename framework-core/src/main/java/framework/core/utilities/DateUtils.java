package framework.core.utilities;

import java.util.Date;

public interface DateUtils {

    Date convertToDate(Long unixtime);

    Long convertToUnixTime(Date date);

    Date getCurrentTime();

    Long getCurrentUnixTime();

}