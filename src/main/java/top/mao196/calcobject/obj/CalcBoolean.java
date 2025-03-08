package top.mao196.calcobject.obj;



/**
 * @author susanbushisan
 **/
public class CalcBoolean extends CalcObject {

    private final Boolean value;

    public static final CalcBoolean TRUE = new CalcBoolean(Boolean.TRUE);

    public static final CalcBoolean FALSE = new CalcBoolean(Boolean.FALSE);

    private CalcBoolean(Boolean value) {
        this.value = value;
    }

    public boolean booleanValue() {
        return this.value;
    }

    public static CalcBoolean valueOf(final boolean b) {
        return b ? CalcBoolean.TRUE : CalcBoolean.FALSE;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public CalcType getCalcType() {
        return CalcType.BOOLEAN;
    }
}
