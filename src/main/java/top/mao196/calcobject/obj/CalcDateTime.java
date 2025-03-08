package top.mao196.calcobject.obj;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author susanbushisan
 **/
public class CalcDateTime extends CalcObject {

    private final LocalDateTime value;

    public CalcDateTime(LocalDateTime value)
    {
        this.value = value;
    }

    public static CalcDateTime valueOf(LocalDateTime value) {
        return new CalcDateTime(value);
    }


    public LocalDateTime localDateTimeValue() {
        return value;
    }

    public Date dateValue() {
        return Date.from(value.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public CalcType getCalcType() {
        return CalcType.DATE_TIME;
    }
}
