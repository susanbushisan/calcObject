package top.mao196.calcobject.table;

import cn.hutool.core.lang.Tuple;
import lombok.*;
import top.mao196.calcobject.obj.CalcNull;
import top.mao196.calcobject.obj.CalcObject;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Builder
public class SimpleTableTransform implements TableTransform {

    private List<String> groupKeys;

    private List<AggregateFunction> measureAggregateFiledList;

    private String tableName;


    @Override
    public Table apply(Table table) {
        Table result = new Table(true,groupKeys);
        result.setTableName(tableName);
        Map<Tuple, List<Row>> group = table.getRowList().stream().collect(Collectors.groupingBy(x -> {
            Tuple key = x.getKey();
            Object[] keys = new Object[groupKeys.size()];
            for (int i = 0; i < groupKeys.size(); i++) {
                keys[i] = key.get(table.getKeyNames().indexOf(groupKeys.get(i)));
            }
            return new Tuple(keys);
        }));
        group.forEach((key, rows) -> {
            Row row = new Row(key);
            for (AggregateFunction filed : measureAggregateFiledList) {
                row.put(filed.getName(), filed.getFunction().apply(rows));
            }
            result.addRow(row);
        });

        return result;
    }
}
