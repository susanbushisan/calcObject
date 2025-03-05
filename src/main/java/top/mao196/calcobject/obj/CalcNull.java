package top.mao196.calcobject.obj;


/**
 * @author susanbushisan
 * @date 2024/3/10
 **/
public class CalcNull extends CalcObject {

    private static final CalcNull INSTANCE = new CalcNull();

    public CalcNull() {
    }

    public static CalcNull instance() {
        return INSTANCE;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public CalcType getCalcType() {
        return CalcType.NULL;
    }
}
