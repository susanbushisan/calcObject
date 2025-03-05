package top.mao196.calcobject.obj;


import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author susanbushisan
 * @date 2024/3/10
 **/
public class CalcLong extends CalcNumber {

    private static class LongCache {
        private LongCache() {
        }

        static final CalcLong[] cache = new CalcLong[256];

        static {
            for (long i = 0; i < cache.length; i++) {
                cache[(int) i] = new CalcLong(i - 128);
            }
        }
    }


    private final long value;

    private CalcLong(long value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }


    public static CalcLong valueOf(final long l) {
        final int offset = 128;
        if (l >= -128 && l <= 127) { // will cache
            return CalcLong.LongCache.cache[(int) l + offset];
        }
        return new CalcLong(l);
    }

    public static CalcLong valueOf(final Long l) {
        return valueOf(l.longValue());
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
        return (int) this.value;
    }

    @Override
    public boolean isZero() {
        return value == 0L;
    }

    @Override
    public CalcType getCalcType() {
        return CalcType.LONG;
    }

    @Override
    public Number getNumber() {
        return this.value;
    }
}
