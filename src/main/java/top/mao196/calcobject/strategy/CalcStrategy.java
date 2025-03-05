package top.mao196.calcobject.strategy;

import top.mao196.calcobject.obj.CalcObject;

/**
 * 计算策略，用于处理基础计算逻辑
 * @author susanbushisan
 * @date 2024/5/21
 **/
public interface CalcStrategy {
    CalcObject add(final CalcObject a, final CalcObject b);

    CalcObject sub(final CalcObject a, final CalcObject b);

    CalcObject multi(final CalcObject a, final CalcObject b);

    CalcObject div(final CalcObject a, final CalcObject b);

    CalcObject mod(final CalcObject a, final CalcObject b);

    CalcObject neg(final CalcObject a);

    int compare(final CalcObject a, final CalcObject b);

    boolean eq(final CalcObject a, final CalcObject b);
}
