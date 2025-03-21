package top.mao196.calcobject.table;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.lang.Tuple;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Data
public class Table {

    private String tableName;

    private List<Row> rowList = new ArrayList<>();

    /**
     * 是否使用用主键。如果是false则rowsMap不会有数据，如果是ture则可以根据主键查询row
     */
    private final boolean primaryKey;

    private Map<Tuple, Row> rowsMap = new HashMap<>();

    private final List<String> keyNames;

    private List<String> measureKeys;

    public Table(boolean primaryKey, List<String> keyNames) {
        this.keyNames = keyNames;
        this.primaryKey = primaryKey;
    }

    public boolean isEmpty() {
        return CollUtil.isEmpty(rowList);
    }

    public void addRow(Row row) {
        rowList.add(row);
        if (primaryKey) {
            rowsMap.put(row.getKey(), row);
        }
    }

    public void addRowList(List<Row> rowList) {
        rowList.forEach(this::addRow);
    }


    public Row getRow(Tuple key) {
        if (primaryKey) {
            return rowsMap.get(key);
        }
        return null;
    }

    public void removeRow(Row row) {
        if (primaryKey) {
            rowsMap.remove(row.getKey());
        }
        rowList.remove(row);
    }

    public boolean removeRowByKey(Tuple key) {
        if (primaryKey) {
            boolean b = rowsMap.containsKey(key);
            if (!b) {
                return false;
            }
            Row row = rowsMap.get(key);
            rowsMap.remove(row.getKey());
            rowList.remove(row);
            return true;
        }
        return false;
    }

    public boolean containRow(Tuple key) {
        if (primaryKey) {
            return rowsMap.containsKey(key);
        }
        return false;
    }

    public void foreachRow(Consumer<Row> consumer) {
        rowList.forEach(consumer);
    }

    /**
     * 获取table的统计信息
     *
     * @return
     */
    public String summary() {
        String summary = "表名:{} \n表维度:{} \n表行数:{}\n表数据抽样:\n{} ";
        String sample = rowList.stream().limit(10)
                .map(x -> x.getKey().toString() + ":" + x.getValueMap().entrySet()
                        .stream()
                        .map(y -> y.getKey() + "=" + y.getValue().getValue())
                        .collect(Collectors.joining(",")))
                .collect(Collectors.joining("\n"));
        return StrUtil.format(summary, tableName, keyNames, rowList.size(), sample);
    }

    public String print(int rowLimit, String... cKeys) {
        return rowList.stream().limit(rowLimit).map(x -> {
            List<String> context = new ArrayList<>();
            for (int i = 0; i < keyNames.size(); i++) {
                context.add(x.getKey().get(i));
            }
            for (String cKey : cKeys) {
                context.add(Objects.toString(x.get(cKey).getValue()));
            }
            return CollUtil.join(context, "\t");
        }).collect(Collectors.joining("\n"));
    }

    public Pair<List<String>, List<Map<String, Object>>> export(String... cKeys) {
        List<String> header = new ArrayList<>(new ArrayList<>(keyNames));
        Collections.addAll(header, selectMetricKeys(cKeys));
        List<Map<String, Object>> data = new ArrayList<>();
        rowList.forEach(x -> {
            Map<String, Object> row = new LinkedHashMap<>();
            for (int i = 0; i < keyNames.size(); i++) {
                row.put(keyNames.get(i), x.getKey().get(i));
            }
            for (String cKey : cKeys) {
                row.put(cKey, x.get(cKey).getValue());
            }
            data.add(row);
        });
        return Pair.of(header, data);
    }

    public Pair<List<String>, List<List<Object>>> export4EasyExcel(String... cKeys) {
        List<String> header = new ArrayList<>(new ArrayList<>(keyNames));
        Collections.addAll(header, selectMetricKeys(cKeys));
        List<List<Object>> data = new ArrayList<>();
        rowList.forEach(x -> {
            List<Object> row = new ArrayList<>();
            for (int i = 0; i < keyNames.size(); i++) {
                row.add(x.getKey().get(i));
            }
            for (String cKey : cKeys) {
                row.add(x.get(cKey).getValue());
            }
            data.add(row);
        });
        return Pair.of(header, data);
    }

    private String[] selectMetricKeys(String[] cKeys) {
        if (cKeys == null || cKeys.length == 0) {
            return measureKeys.toArray(new String[0]);
        }
        for (String cKey : cKeys) {
            if (!measureKeys.contains(cKey)) {
                throw new IllegalArgumentException("cKeys中包含的指标名称不在指标列表中");
            }
        }
        return cKeys;
    }

    public Table transform(TableTransform tableTransform) {
        return tableTransform.apply(this);
    }

}
