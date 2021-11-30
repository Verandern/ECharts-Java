package org.icepear.echarts.origin.coord;

/**
 * https://github.com/apache/echarts/blob/790687df55a5dbe286e52cf182c0983938efd367/src/coord/axisCommonTypes.ts#L172
 */
public interface AxisTickOption {
    AxisTickOption setShow(Boolean show);

    AxisTickOption setInside(Boolean inside);

    AxisTickOption setLength(Number length);

    AxisTickOption setLineStyle(Object lineStyle);
}
