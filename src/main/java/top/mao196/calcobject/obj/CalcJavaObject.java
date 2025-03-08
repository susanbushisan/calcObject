package top.mao196.calcobject.obj;

/**
 * @author susanbushisan
 **/
public class CalcJavaObject extends CalcObject {

    private Object delegate;

    private CalcJavaObject(Object delegate) {
        this.delegate = delegate;
    }

    public static CalcJavaObject valueOf(Object delegate) {
        return new CalcJavaObject(delegate);
    }

    @Override
    public Object getValue() {
        return this.delegate;
    }

    @Override
    public CalcType getCalcType() {
        return CalcType.JAVA_OBJECT;
    }
}
