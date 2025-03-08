package top.mao196.calcobject.obj;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author susanbushisan
 **/
public class CalcDouble extends CalcNumber {

    private final double value;

    public CalcDouble(double value) {
        this.value = value;
    }

    public static CalcDouble valueOf(final Double d) {
        return new CalcDouble(d);
    }

    public static CalcDouble valueOf(final double d) {
        return new CalcDouble(d);
    }

    @Override
    public double doubleValue() {
        return this.value;
    }

    @Override
    public long longValue() {
        return (long) this.value;
    }

    @Override
    public BigDecimal bigDecimal() {
        return BigDecimal.valueOf(this.value);
    }

    @Override
    public BigInteger bigInteger() {
        return BigInteger.valueOf(longValue());
    }

    @Override
    public int intValue() {
        return (int) this.value;
    }

    @Override
    public boolean isZero() {
        return value == 0d;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public CalcType getCalcType() {
        return CalcType.DOUBLE;
    }

    @Override
    public Number getNumber() {
        return this.value;
    }
}
