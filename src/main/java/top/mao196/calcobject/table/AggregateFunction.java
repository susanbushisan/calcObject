package top.mao196.calcobject.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.mao196.calcobject.obj.CalcObject;

import java.util.List;
import java.util.function.Function;

public interface AggregateFunction {

    String getName();

    Function<List<Row>, CalcObject> getFunction();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class SimpleAggregateFunction implements AggregateFunction {

        private String name;

        private Function<List<Row>, CalcObject> function;
    }
}