package org.icepear.echarts.origin.coord;

/**
 * https://github.com/apache/echarts/blob/790687df55a5dbe286e52cf182c0983938efd367/src/coord/axisCommonTypes.ts#L131
 */
public interface CategoryAxisTickOption extends AxisTickOption {
    void setAlignWithLabel(Boolean alignWithLabel);
    
    void setInterval(Number interval);
}
