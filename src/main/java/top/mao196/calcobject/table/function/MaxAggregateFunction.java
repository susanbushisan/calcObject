package top.mao196.calcobject.table.function;

import top.mao196.calcobject.obj.CalcNull;
import top.mao196.calcobject.obj.CalcObject;
import top.mao196.calcobject.table.Row;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class MaxAggregateFunction extends SelfAggregateFunction {

    public MaxAggregateFunction(String name, String calcMeasureName) {
        super(name, calcMeasureName);
    }
    public MaxAggregateFunction(String name) {
        super(name);
    }

    @Override
    public Function<List<Row>, CalcObject> getFunction() {
        return rows -> rows.stream().map(x->x.get(getCalcMeasureName())).min((o1,o2)-> o2.compare(o1)).orElse(CalcNull.instance());
    }
}
