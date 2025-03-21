package top.mao196.calcobject.table.function;

import top.mao196.calcobject.obj.CalcNull;
import top.mao196.calcobject.obj.CalcObject;
import top.mao196.calcobject.table.Row;

import java.util.List;
import java.util.function.Function;

public class SumAggregateFunction extends SelfAggregateFunction {
    public SumAggregateFunction(String name, String calcMeasureName) {
        super(name, calcMeasureName);
    }

    public SumAggregateFunction(String name) {
        super(name);
    }

    @Override
    public Function<List<Row>, CalcObject> getFunction() {
        return rows -> rows.stream().map(x->x.get(getCalcMeasureName())).reduce(CalcNull.instance(),CalcObject::add);
    }
}
