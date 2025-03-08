package top.mao196.calcobject.obj;

import java.time.Duration;

/**
 * @author susanbushisan
 **/
public class CalcDuration extends CalcObject {

    private final Duration value;

    public CalcDuration(Duration value)
    {
        this.value = value;
    }

    public CalcDuration(String value)
    {
        this.value = Duration.parse(value);
    }

    public static CalcObject valueOf(Duration value) {
        return new CalcDuration(value);
    }

    public Duration durationValue() {
        return value;
    }


    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public CalcType getCalcType() {
        return CalcType.DURATION;
    }
}
