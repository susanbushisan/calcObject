package top.mao196.calcobject.table.function;

import lombok.Getter;
import top.mao196.calcobject.table.AggregateFunction;

@Getter
public abstract class SelfAggregateFunction implements AggregateFunction {

    private final String name;

    private final String calcMeasureName;

    protected SelfAggregateFunction(String name) {
        this.name = name;
        this.calcMeasureName = name;
    }

    protected SelfAggregateFunction(String name, String calcMeasureName) {
        this.name = name;
        this.calcMeasureName = calcMeasureName;
    }
}
