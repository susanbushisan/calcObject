package top.mao196.calcobject.obj;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author susanbushisan
 * @date 2024/3/20
 **/
public class CalcDate extends CalcObject {

    private final LocalDate value;

    public CalcDate(LocalDate value) {
        this.value = value;
    }

    public CalcDate(String value) {
        this.value = LocalDate.parse(value);
    }

    public CalcDate(int year, int month, int day) {
        this.value = LocalDate.of(year, month, day);
    }

    public static CalcObject valueOf(LocalDate obj) {
        return new CalcDate(obj);
    }

    public Date dateValue() {
        // 将LocalDate转换位Date并返回
        return Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public LocalDate localDateValue() {
        return value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public CalcType getCalcType() {
        return CalcType.DATE;
    }
}
