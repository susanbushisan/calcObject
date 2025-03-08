package top.mao196.calcobject.obj;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author susanbushisan
 **/
public class CalcBigDecimal extends CalcNumber {

    private final BigDecimal value;

    public CalcBigDecimal(BigDecimal value) {
        this.value = value;
    }

    public static CalcBigDecimal valueOf(final BigDecimal l) {
        return new CalcBigDecimal(l);
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
        return this.value;
    }

    @Override
    public BigInteger bigInteger() {
        return this.value.toBigInteger();
    }

    @Override
    public int intValue() {
        return this.value.intValue();
    }

    @Override
    public boolean isZero() {
        return value.compareTo(BigDecimal.ZERO) == 0;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public CalcType getCalcType() {
        return CalcType.BIG_DECIMAL;
    }


    @Override
    public Number getNumber() {
        return this.value;
    }
}
