package top.mao196.calcobject.table;

import cn.hutool.core.lang.Tuple;
import junit.framework.TestCase;
import top.mao196.calcobject.obj.*;
import top.mao196.calcobject.table.function.*;
import top.mao196.calcobject.util.CalcObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class TableTest extends TestCase {

    protected Table table;

    public void setUp() {
        table = new Table(true, Arrays.asList("大区", "门店", "品类", "季度", "时间"));
        table.setTableName("测试table数据");
        table.setMeasureKeys(Arrays.asList("销量", "销售额", "单价"));
        Object[][] data = {
                {"华北", "500913", "月饼", "Q1", "2025-03-11", 100, 1000, 10},
                {"华北", "500913", "粽子", "Q1", "2025-03-11", 200, 3000, 15},
                {"华北", "500913", "咖啡", "Q1", "2025-03-11", 200, 3000, 15},
        };
        for (Object[] row : data) {
            Row r = new Row(new Tuple(row[0], row[1], row[2], row[3], row[4]));
            r.put("销量", CalcObjectUtils.parse(row[5], CalcType.INTEGER));
            r.put("销售额", CalcObjectUtils.parse(row[6], CalcType.INTEGER));
            r.put("单价", CalcObjectUtils.parse(row[7], CalcType.INTEGER));
            table.addRow(r);
        }
    }

    public void testGroup() {
        Function<List<Row>, CalcObject> function1 = list -> list.stream().map(x -> x.get("销量")).reduce(CalcNull.instance(), CalcObject::add);
        Function<List<Row>, CalcObject> function2 = list -> list.stream().map(x -> x.get("销售额")).reduce(CalcNull.instance(), CalcObject::add);
        Function<List<Row>, CalcObject> function3 = list -> {
            CalcObject qty = list.stream().map(x -> x.get("销量")).reduce(CalcNull.instance(), CalcObject::add);
            CalcObject sales = list.stream().map(x -> x.get("销售额")).reduce(CalcNull.instance(), CalcObject::add);
            return sales.add(CalcDouble.valueOf(0)).div(qty);
        };
        Table result = table.transform(SimpleTableTransform.builder()
                .tableName("group转换")
                .groupKeys(Arrays.asList("大区", "门店"))
                .measureAggregateFiledList(Arrays.asList(
                        new AggregateFunction.SimpleAggregateFunction("销量", function1),
                        new AggregateFunction.SimpleAggregateFunction("销售额", function2),
                        new AggregateFunction.SimpleAggregateFunction("单价", function3))
                )
                .build()
        );
        System.out.println(result.summary());
    }

    public void testGroup2() {
        Function<List<Row>, CalcObject> function3 = list -> new SumAggregateFunction("销售额").getFunction().apply(list)
                .add(CalcDouble.valueOf(0))
                .div(new SumAggregateFunction("销量").getFunction().apply(list));
        Table result = table.transform(SimpleTableTransform.builder()
                .tableName("group转换")
                .groupKeys(Arrays.asList("大区", "门店"))
                .measureAggregateFiledList(Arrays.asList(
                        new SumAggregateFunction("销量"),
                        new SumAggregateFunction("销售额"),
                        new AggregateFunction.SimpleAggregateFunction("单价", function3))
                )
                .build()
        );
        System.out.println(result.summary());
    }

    public void testGroup3() {
        Table result = table.transform(SimpleTableTransform.builder()
                .tableName("group转换")
                .groupKeys(Arrays.asList("大区", "门店"))
                .measureAggregateFiledList(Arrays.asList(
                        new SumAggregateFunction("sum销量", "销量"),
                        new AvgAggregateFunction("AVG销量", "销量"),
                        new CountAggregateFunction("COUNT销量", "销量"),
                        new MaxAggregateFunction("MAX销量", "销量"),
                        new MinAggregateFunction("MIN销量", "销量"))
                )
                .build()
        );
        System.out.println(result.summary());
    }


}