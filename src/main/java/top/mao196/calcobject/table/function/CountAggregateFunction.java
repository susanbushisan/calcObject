package top.mao196.calcobject.table.function;

import top.mao196.calcobject.obj.CalcLong;
import top.mao196.calcobject.obj.CalcNull;
import top.mao196.calcobject.obj.CalcObject;
import top.mao196.calcobject.table.Row;

import java.util.List;
import java.util.function.Function;

public class CountAggregateFunction extends SelfAggregateFunction {
    public CountAggregateFunction(String name, String calcMeasureName) {
        super(name, calcMeasureName);
    }

    public CountAggregateFunction(String name) {
        super(name);
    }

    @Override
    public Function<List<Row>, CalcObject> getFunction() {
        return rows -> CalcLong.valueOf(rows.stream().map(x->x.get(getCalcMeasureName())).filter(x->!x.eq(CalcNull.instance())).count());
    }
}
