package business.Timer;

/**时间相关工具类
 * Created by zhaopy on 2015/11/18.
 */
public class TimeSpan {

    private long milliseconds;
    public long getMilliseconds() {
        return milliseconds;
    }

    public TimeSpan(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public int getMillisecondPart() {
        return (int)(milliseconds % 1000);
    }

    public int getSecondPart() {
        return getSecond(milliseconds);
    }

    public int getMinutePart() {
        return getMinute(milliseconds);
    }

    public int getHourPart() {
        return getHour(milliseconds);
    }

    @Override
    public String toString() {
        return getClockHmsString(milliseconds);
    }


    public static String getClockHmsString(long milliseconds) {
        return String.format("%d:%02d:%02d", getHour(milliseconds), getMinute(milliseconds), getSecond(milliseconds));
    }

    public static String getCounterHmsString(long milliseconds) {
        return String.format("%02d:%02d:%02d", getHour(milliseconds), getMinute(milliseconds), getSecond(milliseconds));
    }

    private static int getHour(long milliseconds) {
        return (int)(Math.floor((float)milliseconds / (1000 * 60 * 60)));
    }

    private static int getMinute(long milliseconds) {
        return (int)(Math.floor((float)milliseconds / (1000 * 60))) % 60;
    }

    private static int getSecond(long milliseconds) {
        return (int)(Math.floor((float)milliseconds / 1000)) % 60;
    }


    public static TimeSpan fromMilliseconds(float time) {
        return new TimeSpan((long)time);
    }

    public static TimeSpan fromSeconds(float time) {
        return new TimeSpan((long)(time * 1000));
    }

    public static TimeSpan fromMinutes(float time) {
        return new TimeSpan((long)(time * 1000 * 60));
    }

    public static TimeSpan fromHours(float time) {
        return new TimeSpan((long)(time * 1000 * 60 * 60));
    }

    public static TimeSpan fromDays(float time) {
        return new TimeSpan((long)(time * 1000 * 60 * 60 * 24));
    }
}
