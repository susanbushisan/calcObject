package top.mao196.calcobject.obj;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author susanbushisan
 **/
public class CalcBigInteger extends CalcNumber {

    private final BigInteger value;

    public CalcBigInteger(BigInteger value) {
        this.value = value;
    }

    public static CalcBigInteger valueOf(BigInteger bigInteger) {
        return new CalcBigInteger(bigInteger);
    }

    @Override
    public double doubleValue() {
        return this.value.doubleValue();
    }

    @Override
    public long longValue() {
        return this.value.longValue();
    }

    @Override
    public BigDecimal bigDecimal() {
        return new BigDecimal(this.value);
    }

    @Override
    public BigInteger bigInteger() {
        return this.value;
    }

    @Override
    public int intValue() {
        return this.value.intValue();
    }

    @Override
    public boolean isZero() {
        return value.equals(BigInteger.ZERO);
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public CalcType getCalcType() {
        return CalcType.BIG_INTEGER;
    }


    @Override
    public Number getNumber() {
        return this.value;
    }
}
