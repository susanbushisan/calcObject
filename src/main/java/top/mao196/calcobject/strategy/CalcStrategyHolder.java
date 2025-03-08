package top.mao196.calcobject.strategy;


/**
 * @author susanbushisan
 **/
public class CalcStrategyHolder {

    private final static ThreadLocal<CalcStrategy> CALC_STRATEGY_THREAD_LOCAL = new ThreadLocal<>();

    public static CalcStrategy getCalcStrategyThreadLocal() {
        return CALC_STRATEGY_THREAD_LOCAL.get();
    }

    public static void setCalcStrategyThreadLocal(CalcStrategy calcStrategy) {
        CALC_STRATEGY_THREAD_LOCAL.set(calcStrategy);
    }

    public static void removeCalcStrategy() {
        CALC_STRATEGY_THREAD_LOCAL.remove();
    }

}
