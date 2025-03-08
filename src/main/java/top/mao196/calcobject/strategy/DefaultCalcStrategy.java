package top.mao196.calcobject.strategy;

import cn.hutool.core.collection.CollUtil;
import top.mao196.calcobject.ex.CompareNotSupportedException;
import top.mao196.calcobject.ex.ExpressionRuntimeException;
import top.mao196.calcobject.obj.*;


import java.math.RoundingMode;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

/**
 * @author susanbushisan
 **/
public class DefaultCalcStrategy implements CalcStrategy {

    protected EnumSet<CalcType> numberType = EnumSet.of(CalcType.BIG_DECIMAL, CalcType.BIG_INTEGER, CalcType.DOUBLE, CalcType.INTEGER, CalcType.LONG);

    protected List<CalcType> numberTypeOrder = CollUtil.newArrayList(
            CalcType.INTEGER,
            CalcType.LONG,
            CalcType.BIG_INTEGER,
            CalcType.BIG_DECIMAL,
            CalcType.DOUBLE);

    protected RoundingMode roundingMode = RoundingMode.HALF_EVEN;

    @Override
    public CalcObject add(CalcObject a, CalcObject b) {
        boolean isNumberA = isNumber(a);
        boolean isNumberB = isNumber(b);
        if (isNull(a) && isNull(b)) {
            // null + null = null
            return CalcNull.instance();
        } else if (isNull(a)) {
            return b;
        } else if (isNull(b)) {
            return a;
        } else if (isNumberA && isNumberB) {
            // 判断类型转换成什么进行计算
            CalcNumber a1 = (CalcNumber) a;
            CalcNumber b1 = (CalcNumber) b;
            CalcType calcType = getNumberCalcType(a1, (CalcNumber) b);
            if (calcType == CalcType.INTEGER) {
                return CalcInteger.valueOf(a1.intValue() + b1.intValue());
            } else if (calcType == CalcType.LONG) {
                return CalcLong.valueOf(a1.longValue() + b1.longValue());
            } else if (calcType == CalcType.BIG_INTEGER) {
                return CalcBigInteger.valueOf(a1.bigInteger().add(b1.bigInteger()));
            } else if (calcType == CalcType.BIG_DECIMAL) {
                return CalcBigDecimal.valueOf(a1.bigDecimal().add(b1.bigDecimal()));
            } else if (calcType == CalcType.DOUBLE) {
                return CalcDouble.valueOf(a1.doubleValue() + b1.doubleValue());
            }
        }
        throw new ExpressionRuntimeException("Could not sub " + a + " with " + b);
    }

    @Override
    public CalcObject sub(CalcObject a, CalcObject b) {
        boolean isNumberA = isNumber(a);
        boolean isNumberB = isNumber(b);
        if (isNull(a) && isNull(b)) {
            // null * 任何数字的结果都是null
            return CalcNull.instance();
        } else if (isNull(a) && isNumberB) {
            return neg(b);
        } else if (isNumberA && isNull(b)) {
            return a;
        } else if (isNumberA && isNumberB) {
            // 判断类型转换成什么进行计算
            CalcNumber a1 = (CalcNumber) a;
            CalcNumber b1 = (CalcNumber) b;
            CalcType calcType = getNumberCalcType(a1, (CalcNumber) b);
            if (calcType == CalcType.INTEGER) {
                return CalcInteger.valueOf(a1.intValue() - b1.intValue());
            } else if (calcType == CalcType.LONG) {
                return CalcLong.valueOf(a1.longValue() - b1.longValue());
            } else if (calcType == CalcType.BIG_INTEGER) {
                return CalcBigInteger.valueOf(a1.bigInteger().subtract(b1.bigInteger()));
            } else if (calcType == CalcType.BIG_DECIMAL) {
                return CalcBigDecimal.valueOf(a1.bigDecimal().subtract(b1.bigDecimal()));
            } else if (calcType == CalcType.DOUBLE) {
                return CalcDouble.valueOf(a1.doubleValue() - b1.doubleValue());
            }
        }
        throw new ExpressionRuntimeException("Could not sub " + a + " with " + b);
    }

    @Override
    public CalcObject multi(CalcObject a, CalcObject b) {
        boolean isNumberA = isNumber(a);
        boolean isNumberB = isNumber(b);
        if (isNull(a) || isNull(b)) {
            // null * 任何数字的结果都是null
            return CalcNull.instance();
        } else if (isNumberA && isNumberB) {
            // 判断类型转换成什么进行计算
            CalcNumber a1 = (CalcNumber) a;
            CalcNumber b1 = (CalcNumber) b;
            CalcType calcType = getNumberCalcType(a1, (CalcNumber) b);
            if (calcType == CalcType.INTEGER) {
                return CalcInteger.valueOf(a1.intValue() * b1.intValue());
            } else if (calcType == CalcType.LONG) {
                return CalcLong.valueOf(a1.longValue() * b1.longValue());
            } else if (calcType == CalcType.BIG_INTEGER) {
                return CalcBigInteger.valueOf(a1.bigInteger().multiply(b1.bigInteger()));
            } else if (calcType == CalcType.BIG_DECIMAL) {
                return CalcBigDecimal.valueOf(a1.bigDecimal().multiply(b1.bigDecimal()));
            } else if (calcType == CalcType.DOUBLE) {
                return CalcDouble.valueOf(a1.doubleValue() * b1.doubleValue());
            }
        }

        throw new ExpressionRuntimeException("Could not multi " + a + " with " + b);
    }

    @Override
    public CalcObject div(CalcObject a, CalcObject b) {
        boolean isNumberA = isNumber(a);
        boolean isNumberB = isNumber(b);
        if (isNull(b) || (isNumberB && ((CalcNumber) b).isZero())) {
            // 任何数字除以0或者null都是null
            return CalcNull.instance();
        } else if (isNull(a) && isNumberB) {
            // null / 任何数字的结果都是null
            return a;
        } else if (isNumberA && isNumberB) {
            // 判断类型转换成什么进行计算
            CalcNumber a1 = (CalcNumber) a;
            CalcNumber b1 = (CalcNumber) b;
            if (b1.isZero()) {
                throw new ExpressionRuntimeException("Could not div " + a + " with " + b);
            }
            CalcType calcType = getNumberCalcType(a1, (CalcNumber) b);
            if (calcType == CalcType.INTEGER) {
                return CalcInteger.valueOf(a1.intValue() / b1.intValue());
            } else if (calcType == CalcType.LONG) {
                return CalcLong.valueOf(a1.longValue() / b1.longValue());
            } else if (calcType == CalcType.BIG_INTEGER) {
                return CalcBigInteger.valueOf(a1.bigInteger().divide(b1.bigInteger()));
            } else if (calcType == CalcType.BIG_DECIMAL) {
                return CalcBigDecimal.valueOf(a1.bigDecimal().divide(b1.bigDecimal(), roundingMode));
            } else if (calcType == CalcType.DOUBLE) {
                return CalcDouble.valueOf(a1.doubleValue() / b1.doubleValue());
            }
        }

        throw new ExpressionRuntimeException("Could not div " + a + " with " + b);
    }

    @Override
    public CalcObject mod(CalcObject a, CalcObject b) {
        boolean isNumberA = isNumber(a);
        boolean isNumberB = isNumber(b);
        if (isNull(a) && isNumberB) {
            // null % 任何数字的结果都是null
            return a;
        } else if (isNumberA && isNumberB) {
            // 判断类型转换成什么进行计算
            CalcNumber a1 = (CalcNumber) a;
            CalcNumber b1 = (CalcNumber) b;
            if (b1.isZero()) {
                throw new ExpressionRuntimeException("Could not div " + a + " with " + b);
            }
            CalcType calcType = getNumberCalcType(a1, (CalcNumber) b);
            if (calcType == CalcType.INTEGER) {
                return CalcInteger.valueOf(a1.intValue() % b1.intValue());
            } else if (calcType == CalcType.LONG) {
                return CalcLong.valueOf(a1.longValue() % b1.longValue());
            } else if (calcType == CalcType.BIG_INTEGER) {
                return CalcBigInteger.valueOf(a1.bigInteger().mod(b1.bigInteger()));
            } else if (calcType == CalcType.BIG_DECIMAL) {
                return CalcBigDecimal.valueOf(a1.bigDecimal().remainder(b1.bigDecimal()));
            } else if (calcType == CalcType.DOUBLE) {
                return CalcDouble.valueOf(a1.doubleValue() % b1.doubleValue());
            }
        }

        throw new ExpressionRuntimeException("Could not mod " + a + " with " + b);
    }

    /**
     * 获取两个对象的应该进行计算的类型
     * @param a 计算对象a
     * @param b 计算对象b
     * @return 计算类型
     */
    public CalcType getNumberCalcType(CalcNumber a, CalcNumber b) {
        CalcType aCalcType = a.getCalcType();
        CalcType bCalcType = b.getCalcType();
        return numberTypeOrder.get(Math.max(numberTypeOrder.indexOf(aCalcType), numberTypeOrder.indexOf(bCalcType)));
    }

    @Override
    public CalcObject neg(CalcObject a) {
        if (isNull(a)) {
            return a;
        }
        if (isNumber(a)) {
            switch (a.getCalcType()) {
                case DOUBLE:
                    return CalcDouble.valueOf(-((CalcDouble) a).doubleValue());
            }
            switch (a.getCalcType()) {
                case DOUBLE:
                    return CalcDouble.valueOf(-((CalcDouble) a).doubleValue());
                case BIG_DECIMAL:
                    return CalcBigDecimal.valueOf(((CalcBigDecimal) a).bigDecimal().negate());
                case INTEGER:
                    return CalcInteger.valueOf(-((CalcInteger) a).intValue());
                case LONG:
                    return CalcLong.valueOf(-((CalcLong) a).longValue());
                case BIG_INTEGER:
                    return CalcBigInteger.valueOf(((CalcBigInteger) a).bigInteger().negate());
                default:
                    throw new ExpressionRuntimeException("Could not neg " + a);
            }
        }
        if (a.getCalcType() == CalcType.BOOLEAN) {
            return CalcBoolean.valueOf(!((CalcBoolean) a).booleanValue());
        }
        // 无法进行取反操作
        throw new ExpressionRuntimeException("Could not neg " + a);
    }

    /**
     * 比较两个对象，如果无法比较则抛出一场
     * 针对Null和Error有特殊的比较规则
     * 在比较的情况下Null和0一样进行比较
     * Error在类型相同的情况下返回0，其他情况下compare(Error,any)=1 compare(any,Error)=-1
     *
     */
    @Override
    public int compare(CalcObject a, CalcObject b) {
        if (a == b) {
            return 0;
        }
        CalcType aCalcType = a.getCalcType();
        CalcType bCalcType = b.getCalcType();
        if (isError(a)) {
            return 1;
        } else if (isError(b)) {
            return -1;
        }
        // 进行数值比较
        boolean isNumberCompareA = isNumber(a) || isNull(a);
        boolean isNumberCompareB = isNumber(b) || isNull(b);
        if (isNumberCompareA && isNumberCompareB) {
            if (aCalcType == CalcType.NULL) {
                a = CalcInteger.valueOf(0);
            }
            if (bCalcType == CalcType.NULL) {
                b = CalcInteger.valueOf(0);
            }
            Number numberA = ((CalcNumber) a).getNumber();
            Number numberB = ((CalcNumber) b).getNumber();
            // 转换为最大的数据类型进行比较
            return Double.compare(numberA.doubleValue(), numberB.doubleValue());
        }
        // 其他类型之间进行比较
        if (aCalcType == bCalcType) {
            if (aCalcType == CalcType.BOOLEAN) {
                return Boolean.compare(((CalcBoolean) a).booleanValue(), ((CalcBoolean) b).booleanValue());
            } else if (aCalcType == CalcType.STRING) {
                return ((CalcString) a).getStringValue().compareTo(((CalcString) b).getStringValue());
            } else if (aCalcType == CalcType.DATE_TIME) {
                return ((CalcDateTime) a).localDateTimeValue().compareTo(((CalcDateTime) b).localDateTimeValue());
            } else if (aCalcType == CalcType.DURATION) {
                return ((CalcDuration) a).durationValue().compareTo(((CalcDuration) b).durationValue());
            } else if (aCalcType == CalcType.DATE) {
                return ((CalcDate) a).localDateValue().compareTo(((CalcDate) b).localDateValue());
            }
        }
        throw new CompareNotSupportedException("Could not compare " + a + " with " + b);
    }

    /**
     * 如果两个对象不可比较，则返回false
     *
     */
    @Override
    public boolean eq(CalcObject a, CalcObject b) {
        if (a == b) {
            return true;
        }
        CalcType aCalcType = a.getCalcType();
        CalcType bCalcType = b.getCalcType();
        if (aCalcType == bCalcType) {
            return Objects.equals(a.getValue(), b.getValue());
        }
        return false;
    }

    protected boolean isNumber(CalcObject obj) {
        return numberType.contains(obj.getCalcType());
    }

    protected boolean isNull(CalcObject obj) {
        return obj.getCalcType() == CalcType.NULL;
    }

    protected boolean isError(CalcObject obj) {
        return obj.getCalcType() == CalcType.ERROR;
    }
}
