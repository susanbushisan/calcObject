package top.mao196.calcobject.obj;

import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * 计算类型转换，同类型运算=同类型
 * 从小到大的类型范围为 Integer	Long	BigInteger	BigDecimal	Double
 * 与Null想加得到自身
 *
 * @author susanbushisan
 * @date 2024/3/10
 **/
public abstract class CalcNumber extends CalcObject {


    public abstract double doubleValue();

    public abstract long longValue();

    public abstract BigDecimal bigDecimal();

    public abstract BigInteger bigInteger();

    public abstract int intValue();

    public abstract boolean isZero();

    public abstract Number getNumber();


}
