package top.mao196.calcobject.table;

import cn.hutool.core.lang.Tuple;
import top.mao196.calcobject.obj.CalcNull;
import top.mao196.calcobject.obj.CalcObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class Row {



    private final Tuple key;

    private final Map<String, CalcObject> valueMap = new LinkedHashMap<>();

    public Row(Tuple key){
        this.key = key;
    }

    public Tuple getKey() {
        return key;
    }

    public Map<String, CalcObject> getValueMap() {
        return valueMap;
    }

    public void put(String mkey, CalcObject value){
        valueMap.put(mkey, value);
    }

    public CalcObject get(String mkey){
        return valueMap.getOrDefault(mkey, CalcNull.instance());
    }
}
