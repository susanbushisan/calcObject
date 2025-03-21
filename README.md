
# calcObject - 简单计算对象库


`calcObject` 是一个用于简化Java中数值计算的轻量级库。它帮助开发者处理常见的计算逻辑，减少重复代码，并提高代码的可维护性和可读性。

## 快速开始

### 导入依赖

#### Maven
```xml
<dependency>
    <groupId>top.mao196</groupId>
    <artifactId>calc-object</artifactId>
    <version>0.2.0</version>
</dependency>
```


#### Gradle
```groovy
implementation 'top.mao196:calc-object:0.2.0'
```


### 使用示例

将两个 `Double` 类型相加：

**原始写法**
```java
public Double add(Double a, Double b) {
    if (a == null && b == null) {
        return null;
    } else if (a == null) {
        return b;
    } else if (b == null) {
        return a;
    } else {
        return a + b;
    }
}
```


**使用 `calcObject`**
```java
import top.mao196.calcobject.obj.CalcDouble;
import top.mao196.calcobject.util.CalcObjectUtils;

public Double add(Double a, Double b) {
    return CalcObjectUtils.unwrap(
        CalcObjectUtils.toCalcObject(a).add(CalcObjectUtils.toCalcObject(b)),
        Double.class
    );
}
```


## 计算规则

具体的计算规则可以参考 [DefaultCalcStrategy.java](src/main/java/top/mao196/calcobject/strategy/DefaultCalcStrategy.java)。

你可以通过实现 [CalcStrategy.java](src/main/java/top/mao196/calcobject/strategy/CalcStrategy.java) 来定制具体的计算逻辑，并使用 `CalcObject.setCalcStrategy(calcStrategy)` 设置全局规则。也可以在计算过程中传入计算规则 `CalcObject add(final CalcObject other, CalcStrategy calcStrategy)`。

## 表格操作

Table 是一个二维表的数据结构，用于对表格数据进行操作。其中用于聚合的度量的值是`CalcObject`类型。处理一些小数据量的数据

可以参考([Table.java](src/main/java/top/mao196/calcobject/table/Table.java))

## 更多功能与特性

- 支持多种数据类型的计算。
- 提供灵活的计算策略配置。
- 内置默认计算规则，开箱即用。

## 贡献与反馈

欢迎提交问题和Pull Request！如果你有任何建议或遇到问题，请访问 [Issues](https://github.com/susanbushisan/calcObject/issues) 页面。

