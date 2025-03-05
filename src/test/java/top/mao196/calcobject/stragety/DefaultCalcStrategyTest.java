package top.mao196.calcobject.stragety;

import junit.framework.TestCase;
import top.mao196.calcobject.ex.CompareNotSupportedException;
import top.mao196.calcobject.ex.ExpressionRuntimeException;
import top.mao196.calcobject.obj.*;
import top.mao196.calcobject.strategy.DefaultCalcStrategy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DefaultCalcStrategyTest extends TestCase {

    private DefaultCalcStrategy defaultCalcStrategy;

    @Override
    public void setUp() throws Exception {
        defaultCalcStrategy = new DefaultCalcStrategy();
    }

    public void testAdd() {
        // null+null
        assertEquals(defaultCalcStrategy.add(CalcNull.instance(), CalcNull.instance()), CalcNull.instance());
        // null+number=number
        assertTrue(defaultCalcStrategy.add(CalcNull.instance(), CalcDouble.valueOf(20)).eq(CalcDouble.valueOf(20)));
        assertTrue(defaultCalcStrategy.add(CalcDouble.valueOf(20), CalcNull.instance()).eq(CalcDouble.valueOf(20)));
        // number+number
        assertEquals(defaultCalcStrategy.add(CalcInteger.valueOf(20), CalcDouble.valueOf(20)).compare(CalcDouble.valueOf(40)), 0);
        assertEquals(defaultCalcStrategy.add(CalcInteger.valueOf(20), CalcDouble.valueOf(20)).getCalcType(), CalcType.DOUBLE);
        assertEquals(defaultCalcStrategy.add(CalcBigDecimal.valueOf(BigDecimal.ZERO), CalcBigDecimal.valueOf(BigDecimal.TEN)).compare(CalcBigDecimal.valueOf(BigDecimal.TEN)), 0);
        assertEquals(defaultCalcStrategy.add(CalcBigDecimal.valueOf(BigDecimal.ZERO), CalcBigDecimal.valueOf(BigDecimal.TEN)).getCalcType(), CalcType.BIG_DECIMAL);
        // number+javaObject
        try {
            defaultCalcStrategy.add(CalcInteger.valueOf(20), CalcJavaObject.valueOf(new Object()));
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), ExpressionRuntimeException.class);
        }
    }

    public void testSub() {
        // null-null
        assertEquals(defaultCalcStrategy.sub(CalcNull.instance(), CalcNull.instance()), CalcNull.instance());
        // null-number=-number
        assertTrue(defaultCalcStrategy.sub(CalcNull.instance(), CalcDouble.valueOf(20)).eq(CalcDouble.valueOf(20).neg(defaultCalcStrategy)));
        assertTrue(defaultCalcStrategy.sub(CalcDouble.valueOf(20), CalcNull.instance()).eq(CalcDouble.valueOf(20)));
        // number-number
        assertEquals(defaultCalcStrategy.sub(CalcInteger.valueOf(20), CalcDouble.valueOf(20)).compare(CalcDouble.valueOf(0)), 0);
        assertEquals(defaultCalcStrategy.sub(CalcInteger.valueOf(20), CalcDouble.valueOf(20)).getCalcType(), CalcType.DOUBLE);
        assertEquals(defaultCalcStrategy.sub(CalcBigDecimal.valueOf(BigDecimal.ZERO), CalcBigDecimal.valueOf(BigDecimal.TEN)).compare(CalcBigDecimal.valueOf(BigDecimal.valueOf(-10))), 0);
        assertEquals(defaultCalcStrategy.sub(CalcBigDecimal.valueOf(BigDecimal.ZERO), CalcBigDecimal.valueOf(BigDecimal.TEN)).getCalcType(), CalcType.BIG_DECIMAL);
        // number+javaObject
        try {
            defaultCalcStrategy.sub(CalcInteger.valueOf(20), CalcJavaObject.valueOf(new Object()));
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), ExpressionRuntimeException.class);
        }
    }

    public void testMulti() {
        // null*null
        assertEquals(defaultCalcStrategy.multi(CalcNull.instance(), CalcNull.instance()), CalcNull.instance());
        // null*number=null
        assertTrue(defaultCalcStrategy.multi(CalcNull.instance(), CalcDouble.valueOf(20)).eq(CalcNull.instance()));
        assertTrue(defaultCalcStrategy.multi(CalcDouble.valueOf(20), CalcNull.instance()).eq(CalcNull.instance()));
        // number*number
        assertEquals(defaultCalcStrategy.multi(CalcInteger.valueOf(20), CalcDouble.valueOf(20)).compare(CalcDouble.valueOf(400)), 0);
        assertEquals(defaultCalcStrategy.multi(CalcInteger.valueOf(20), CalcDouble.valueOf(20)).getCalcType(), CalcType.DOUBLE);
        assertEquals(defaultCalcStrategy.multi(CalcBigDecimal.valueOf(BigDecimal.ZERO), CalcBigDecimal.valueOf(BigDecimal.TEN)).compare(CalcBigDecimal.valueOf(BigDecimal.ZERO)), 0);
        assertEquals(defaultCalcStrategy.multi(CalcBigDecimal.valueOf(BigDecimal.ZERO), CalcBigDecimal.valueOf(BigDecimal.TEN)).getCalcType(), CalcType.BIG_DECIMAL);
        // number*javaObject
        try {
            defaultCalcStrategy.multi(CalcInteger.valueOf(20), CalcJavaObject.valueOf(new Object()));
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), ExpressionRuntimeException.class);
        }
    }

    public void testDiv() {
        // null/null
        assertEquals(defaultCalcStrategy.div(CalcNull.instance(), CalcNull.instance()), CalcNull.instance());
        // null/number=null
        assertTrue(defaultCalcStrategy.div(CalcNull.instance(), CalcDouble.valueOf(20)).eq(CalcNull.instance()));
        // number/null=null
        assertTrue(defaultCalcStrategy.div(CalcDouble.valueOf(20), CalcNull.instance()).eq(CalcNull.instance()));
        // number/number
        assertEquals(defaultCalcStrategy.div(CalcInteger.valueOf(20), CalcDouble.valueOf(20)).compare(CalcDouble.valueOf(1)), 0);
        assertEquals(defaultCalcStrategy.div(CalcInteger.valueOf(20), CalcDouble.valueOf(20)).getCalcType(), CalcType.DOUBLE);
        assertEquals(defaultCalcStrategy.div(CalcBigDecimal.valueOf(BigDecimal.ZERO), CalcBigDecimal.valueOf(BigDecimal.TEN)).compare(CalcBigDecimal.valueOf(BigDecimal.ZERO)), 0);
        assertEquals(defaultCalcStrategy.div(CalcBigDecimal.valueOf(BigDecimal.ZERO), CalcBigDecimal.valueOf(BigDecimal.TEN)).getCalcType(), CalcType.BIG_DECIMAL);
        // number/javaObject
        try {
            defaultCalcStrategy.div(CalcInteger.valueOf(20), CalcJavaObject.valueOf(new Object()));
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), ExpressionRuntimeException.class);
        }
    }

    public void testMod() {
        // null%null
        try {
            assertEquals(defaultCalcStrategy.mod(CalcNull.instance(), CalcNull.instance()), CalcNull.instance());
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), ExpressionRuntimeException.class);
        }
        // null%number=null
        assertTrue(defaultCalcStrategy.mod(CalcNull.instance(), CalcDouble.valueOf(20)).eq(CalcNull.instance()));
        // number%null=null
        try {
            assertTrue(defaultCalcStrategy.mod(CalcDouble.valueOf(20), CalcNull.instance()).eq(CalcNull.instance()));
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), ExpressionRuntimeException.class);
        }
        // number%number
        assertEquals(defaultCalcStrategy.mod(CalcInteger.valueOf(20), CalcDouble.valueOf(20)).compare(CalcDouble.valueOf(0)), 0);
        assertEquals(defaultCalcStrategy.mod(CalcInteger.valueOf(20), CalcDouble.valueOf(20)).getCalcType(), CalcType.DOUBLE);
        assertEquals(defaultCalcStrategy.mod(CalcBigDecimal.valueOf(BigDecimal.ZERO), CalcBigDecimal.valueOf(BigDecimal.TEN)).compare(CalcBigDecimal.valueOf(BigDecimal.ZERO)), 0);
        assertEquals(defaultCalcStrategy.mod(CalcBigDecimal.valueOf(BigDecimal.ZERO), CalcBigDecimal.valueOf(BigDecimal.TEN)).getCalcType(), CalcType.BIG_DECIMAL);
        // number%javaObject
        try {
            defaultCalcStrategy.mod(CalcInteger.valueOf(20), CalcJavaObject.valueOf(new Object()));
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), ExpressionRuntimeException.class);
        }
    }

    public void testNeg() {
        // null
        assertEquals(defaultCalcStrategy.neg(CalcNull.instance()), CalcNull.instance());
        // number
        assertEquals(defaultCalcStrategy.neg(CalcBigInteger.valueOf(new BigInteger("965274651763591273642193649216"))).getValue(),
                (CalcBigInteger.valueOf(new BigInteger("-965274651763591273642193649216"))).getValue());
        // boolean
        assertEquals(defaultCalcStrategy.neg(CalcBoolean.valueOf(true)), CalcBoolean.valueOf(false));
        // other
        try {
            defaultCalcStrategy.neg(CalcDate.valueOf(LocalDate.now()));
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), ExpressionRuntimeException.class);
        }
    }

    public void testCompare() {
        // null 与其他类型比较
        assertEquals(defaultCalcStrategy.compare(CalcNull.instance(), CalcNull.instance()), 0);
        assertEquals(defaultCalcStrategy.compare(CalcDouble.valueOf(0), CalcNull.instance()), 0);
        try {
            defaultCalcStrategy.compare(CalcNull.instance(), CalcJavaObject.valueOf(new Object()));
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), CompareNotSupportedException.class);
        }
        // number 与其他类型比较
        assertEquals(defaultCalcStrategy.compare(CalcInteger.valueOf(0), CalcDouble.valueOf(0)), 0);
        assertTrue(defaultCalcStrategy.compare(CalcInteger.valueOf(10), CalcDouble.valueOf(8)) > 0);
        // 可比较类型的比较
        assertEquals(defaultCalcStrategy.compare(CalcDate.valueOf(LocalDate.now()), CalcDate.valueOf(LocalDate.now())), 0);
        try {
            defaultCalcStrategy.compare(CalcDate.valueOf(LocalDate.now()), CalcDateTime.valueOf(LocalDateTime.now()));
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), CompareNotSupportedException.class);
        }
    }

    public void testEq() {
        assertTrue(defaultCalcStrategy.eq(CalcNull.instance(), CalcNull.instance()));
        assertFalse(defaultCalcStrategy.eq(CalcDouble.valueOf(0), CalcNull.instance()));
        assertFalse(defaultCalcStrategy.eq(CalcNull.instance(), CalcJavaObject.valueOf(new Object())));
        // number 与其他类型比较
        assertFalse(defaultCalcStrategy.eq(CalcInteger.valueOf(0), CalcDouble.valueOf(0)));
        assertTrue(defaultCalcStrategy.eq(CalcInteger.valueOf(10), CalcInteger.valueOf(10)) );
        // 可比较类型的比较
        assertTrue(defaultCalcStrategy.eq(CalcDate.valueOf(LocalDate.now()), CalcDate.valueOf(LocalDate.now())));
        assertFalse(defaultCalcStrategy.eq(CalcDate.valueOf(LocalDate.now()), CalcDateTime.valueOf(LocalDateTime.now())));

    }
}