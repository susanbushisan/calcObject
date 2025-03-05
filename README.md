# calcObject
java中用于简单计算的对象库，用户简化计算的编码

使用示例：
将两个Double类型相加

~~~java
import top.mao196.calcobject.obj.CalcDouble;
import top.mao196.calcobject.util.CalcObjectUtils;

// 原始写法

public Double add(Double a, Double b) {
    if (a == null && b == null) {
        return null;
    } else if (a == null) {
        return b;
    } else if (b == null) {
        return a;
    } else {
        return a + b;
    }
}

// 使用calcObject
public Double add(Double a, Double b) {
    return CalcObjectUtils.unwrap(CalcObjectUtils.toCalcObject(a).add(CalcObjectUtils.toCalcObject(b))
            , Double.class);
}
~~~
### 计算规则

具体的计算规则可以参考[DefaultCalcStrategy.java](src%2Fmain%2Fjava%2Ftop%2Fmao196%2Fcalcobject%2Fstrategy%2FDefaultCalcStrategy.java)

定制具体的计算逻辑可以实现[CalcStrategy.java](src%2Fmain%2Fjava%2Ftop%2Fmao196%2Fcalcobject%2Fstrategy%2FCalcStrategy.java) ，然后使用``CalcObject.setCalcStrategy(calcStrategy)``设置全局规则

也可以在计算的过程中传入计算规则``CalcObject add(final CalcObject other, CalcStrategy calcStrategy)``