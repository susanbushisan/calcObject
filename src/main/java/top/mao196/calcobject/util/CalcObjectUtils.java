package top.mao196.calcobject.util;

import cn.hutool.core.convert.Convert;
import top.mao196.calcobject.obj.*;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author susanbushisan
 * @date 2024/3/10
 **/
public class CalcObjectUtils {


    public static CalcObject toCalcObject(Object obj) {
        if (obj == null) {
            return CalcNull.instance();
        }
        if (obj instanceof CalcObject) {
            return (CalcObject) obj;
        }
        if (obj instanceof Integer) {
            return CalcInteger.valueOf((Integer) obj);
        }
        if (obj instanceof Long) {
            return CalcLong.valueOf((Long) obj);
        }
        if (obj instanceof Float) {
            return CalcDouble.valueOf((Float) obj);
        }
        if (obj instanceof BigDecimal) {
            return CalcBigDecimal.valueOf(((BigDecimal) obj));
        }
        if (obj instanceof Double) {
            return CalcDouble.valueOf((Double) obj);
        }
        if (obj instanceof String) {
            return CalcString.valueOf((String) obj);
        }
        if (obj instanceof Boolean) {
            return CalcBoolean.valueOf((Boolean) obj);
        }
        if (obj instanceof Short) {
            return CalcInteger.valueOf((Short) obj);
        }
        if (obj instanceof Byte) {
            return CalcInteger.valueOf((Byte) obj);
        }
        if (obj instanceof BigInteger) {
            return CalcBigInteger.valueOf((BigInteger) obj);
        }
        if (obj instanceof LocalDate) {
            return CalcDate.valueOf((LocalDate) obj);
        }
        if (obj instanceof LocalDateTime) {
            return CalcDateTime.valueOf((LocalDateTime) obj);
        }
        if (obj instanceof CharSequence) {
            return CalcString.valueOf(obj.toString());
        }
        return CalcNull.instance();
    }

    /**
     * 将对象转为计算对象，除了转换为自身对应的计算对象还可能转换为CalcNull
     *
     * @param obj 被转换的对象
     * @param calcType 希望转换为的类型
     * @return 转换后的对象
     */
    public static CalcObject parse(Object obj, CalcType calcType) {
        if (obj == null) {
            return CalcNull.instance();
        }
        if (obj instanceof CalcObject){
            obj = ((CalcObject) obj).getValue();
        }
        switch (calcType) {
            case DOUBLE -> {
                return parseDouble(obj);
            }
            case BIG_DECIMAL -> {
                return parseBigDecimal(obj);
            }
            case BIG_INTEGER -> {
                return parseBigInteger(obj);
            }
            case LONG -> {
                return parseLong(obj);
            }
            case INTEGER -> {
                return parseInteger(obj);
            }
            case STRING -> {
                return parseString(obj);
            }
            case BOOLEAN -> {
                return parseBoolean(obj);
            }
            case DATE_TIME -> {
                return parseDateTime(obj);
            }
            case DATE -> {
                return parseDate(obj);
            }
            case DURATION -> {
                return parseDuration(obj);
            }
        }
        return CalcNull.instance();
    }

    public static CalcObject parseDuration(Object obj) {
        if (obj instanceof Duration) {
            return CalcDuration.valueOf((Duration) obj);
        } else {
            return CalcNull.instance();
        }

    }

    public static CalcObject parseDate(Object obj) {
        if (obj instanceof LocalDate) {
            return CalcDate.valueOf(((LocalDate) obj));
        } else {
            return CalcNull.instance();
        }
    }

    public static CalcObject parseDateTime(Object obj) {
        if (obj instanceof LocalDateTime) {
            return CalcDateTime.valueOf((LocalDateTime) obj);
        } else {
            return CalcNull.instance();
        }
    }

    public static CalcObject parseBoolean(Object obj) {
        if (obj instanceof Boolean) {
            return CalcBoolean.valueOf((Boolean) obj);
        } else {
            Boolean value = Convert.toBool(obj);
            if (value == null) {
                return CalcNull.instance();
            }
            return CalcBoolean.valueOf(value);
        }
    }

    public static CalcObject parseString(Object obj) {
        if (obj instanceof CharSequence || obj instanceof Number) {
            return CalcString.valueOf(obj.toString());
        } else {
            return CalcNull.instance();
        }
    }

    public static CalcObject parseInteger(Object obj) {
        int i;
        if (obj instanceof Integer) {
            i = (int) obj;
        } else {
            BigDecimal value = Convert.toBigDecimal(obj);
            if (value == null) {
                return CalcNull.instance();
            }
            i = value.intValue();
        }
        return CalcInteger.valueOf(i);
    }

    public static CalcObject parseBigInteger(Object obj) {
        BigInteger result = null;
        if (obj instanceof BigInteger) {
            result = (BigInteger) obj;
        } else {
            BigDecimal value = Convert.toBigDecimal(obj);
            if (value == null) {
                return CalcNull.instance();
            }
            result = value.toBigInteger();
        }
        return CalcBigInteger.valueOf(result);
    }

    public static CalcObject parseLong(Object obj) {
        long l;
        if (obj instanceof Long) {
            l = (long) obj;
        } else if (obj instanceof Double d) {
            if (d.isInfinite() || d.isNaN()) {
                return CalcNull.instance();
            }
            l = d.longValue();
        } else {
            BigDecimal value = Convert.toBigDecimal(obj);
            if (value == null) {
                return CalcNull.instance();
            }
            l = value.longValue();
        }
        return CalcLong.valueOf(l);
    }

    public static CalcObject parseBigDecimal(Object obj) {
        BigDecimal result = null;
        if (obj instanceof BigDecimal) {
            result = (BigDecimal) obj;
        } else {
            BigDecimal value = Convert.toBigDecimal(obj);
            if (value == null) {
                return CalcNull.instance();
            }
            result = value;
        }
        return CalcBigDecimal.valueOf(result);
    }

    public static CalcObject parseDouble(Object obj) {
        double d;
        if (obj instanceof Number numberObj) {
            d = numberObj.doubleValue();
        } else {
            Double value = Convert.toDouble(obj);
            if (value == null) {
                return CalcNull.instance();
            }
            d = value;
        }
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            return CalcNull.instance();
        }
        return CalcDouble.valueOf(d);
    }

    public static Object unwrap(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof CalcObject) {
            return ((CalcObject) obj).getValue();
        }
        return obj;
    }
}
