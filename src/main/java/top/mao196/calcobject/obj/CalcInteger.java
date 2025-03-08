package top.mao196.calcobject.obj;


import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * @author susanbushisan
 **/
public class CalcInteger extends CalcNumber {

    private final int value;

    public CalcInteger(int value) {
        this.value = value;
    }

    public static CalcInteger valueOf(int i) {
        return new CalcInteger(i);
    }

    @Override
    public double doubleValue() {
        return this.value;
    }

    @Override
    public long longValue() {
        return this.value;
    }

    @Override
    public BigDecimal bigDecimal() {
        return BigDecimal.valueOf(this.value);
    }

    @Override
    public BigInteger bigInteger() {
        return BigInteger.valueOf(this.value);
    }

    @Override
    public int intValue() {
        return this.value;
    }

    @Override
    public boolean isZero() {
        return value == 0;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public CalcType getCalcType() {
        return CalcType.INTEGER;
    }

    @Override
    public Number getNumber() {
        return this.value;
    }
}
