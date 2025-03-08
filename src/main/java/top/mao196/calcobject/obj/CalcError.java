package top.mao196.calcobject.obj;


import java.time.LocalDateTime;

/**
 * @author susanbushisan
 **/
public class CalcError extends CalcObject {

    private final String value;


    public CalcError(String error) {
        this.value = error;
    }

    public static CalcError valueOf(String value) {
        return new CalcError(value);
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public CalcType getCalcType() {
        return CalcType.ERROR;
    }
}
