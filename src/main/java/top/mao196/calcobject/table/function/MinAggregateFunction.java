package top.mao196.calcobject.table.function;

import top.mao196.calcobject.obj.CalcDouble;
import top.mao196.calcobject.obj.CalcNull;
import top.mao196.calcobject.obj.CalcObject;
import top.mao196.calcobject.table.Row;

import java.util.List;
import java.util.function.Function;

public class MinAggregateFunction extends SelfAggregateFunction {
    public MinAggregateFunction(String name, String calcMeasureName) {
        super(name, calcMeasureName);
    }

    public MinAggregateFunction(String name) {
        super(name);
    }

    @Override
    public Function<List<Row>, CalcObject> getFunction() {
        return rows -> rows.stream().map(x->x.get(getCalcMeasureName())).min(CalcObject::compare).orElse(CalcNull.instance());
    }
}
