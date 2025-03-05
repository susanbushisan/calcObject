package top.mao196.calcobject.obj;


import top.mao196.calcobject.strategy.CalcStrategy;
import top.mao196.calcobject.strategy.CalcStrategyHolder;
import top.mao196.calcobject.strategy.DefaultCalcStrategy;

/**
 * @author susanbushisan
 * @date 2024/3/10
 **/
public abstract class CalcObject {

    /**
     * 全局的计算逻辑，如果直接操作对象做计算，则会使用全局的计算逻辑。
     */
    private static CalcStrategy globalCalcStrategy = new DefaultCalcStrategy();

    public static void setGlobalCalcStrategy(CalcStrategy globalCalcStrategy) {
        CalcObject.globalCalcStrategy = globalCalcStrategy;
    }

    public static CalcStrategy getGlobalCalcStrategy() {
        return globalCalcStrategy;
    }

    public CalcStrategy getCalcStrategy() {
        // 从一个threadLocal中获取计算策略，如果没有设置则使用全局的
        CalcStrategy calcStrategyThreadLocal = CalcStrategyHolder.getCalcStrategyThreadLocal();
        if (calcStrategyThreadLocal != null){
            return calcStrategyThreadLocal;
        }
        return globalCalcStrategy;
    }

    public abstract Object getValue();

    public abstract CalcType getCalcType();

    public int compare(final CalcObject other) {
        return getCalcStrategy().compare(this, other);
    }

    public boolean eq(final CalcObject other) {
        return getCalcStrategy().eq(this, other);
    }

    public CalcObject add(final CalcObject other) {
        return getCalcStrategy().add(this, other);
    }

    public CalcObject sub(final CalcObject other) {
        return getCalcStrategy().sub(this, other);
    }

    public CalcObject div(final CalcObject other) {
        return getCalcStrategy().div(this, other);
    }

    public CalcObject multi(final CalcObject other) {
        return getCalcStrategy().multi(this, other);
    }

    public CalcObject neg() {
        return getCalcStrategy().neg(this);
    }

    public CalcObject mod(final CalcObject other) {
        return getCalcStrategy().mod(this, other);
    }

    public int compare(final CalcObject other, CalcStrategy calcStrategy) {
        return calcStrategy.compare(this, other);
    }

    public boolean eq(final CalcObject other, CalcStrategy calcStrategy) {
        return calcStrategy.eq(this, other);
    }

    public CalcObject add(final CalcObject other, CalcStrategy calcStrategy) {
        return calcStrategy.add(this, other);
    }

    public CalcObject sub(final CalcObject other, CalcStrategy calcStrategy) {
        return calcStrategy.sub(this, other);
    }

    public CalcObject div(final CalcObject other, CalcStrategy calcStrategy) {
        return calcStrategy.div(this, other);
    }

    public CalcObject multi(final CalcObject other, CalcStrategy calcStrategy) {
        return calcStrategy.multi(this, other);
    }

    public CalcObject neg(CalcStrategy calcStrategy) {
        return calcStrategy.neg(this);
    }

    public CalcObject mod(final CalcObject other, CalcStrategy calcStrategy) {
        return calcStrategy.mod(this, other);
    }

    @Override
    public String toString() {
        Object val = getValue();
        return "<" + getCalcType() + ", " + val + ">";
    }

}
