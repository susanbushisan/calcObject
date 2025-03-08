package top.mao196.calcobject.obj;

/**
 * @author susanbushisan
 **/
public class CalcString extends CalcObject {

    private final String value;

    public CalcString(String value) {
        this.value = value;
    }

    public String getStringValue() {
        return value;
    }

    public static CalcString valueOf(String value) {
        return new CalcString(value);
    }


    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public CalcType getCalcType() {
        return CalcType.STRING;
    }
}
