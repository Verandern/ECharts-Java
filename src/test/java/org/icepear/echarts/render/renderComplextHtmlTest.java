package org.icepear.echarts.render;

import org.icepear.echarts.component.Option;
import org.junit.Before;

import java.io.IOException;
import java.util.HashMap;

import org.icepear.echarts.component.CategoryAxis;
import org.icepear.echarts.component.Legend;
import org.icepear.echarts.component.LineSeries;
import org.icepear.echarts.component.MarkLine;
import org.icepear.echarts.component.MarkLine1DDataItem;
import org.icepear.echarts.component.MarkLine2DDataItem;
import org.icepear.echarts.component.MarkLine2DDataItemDim;
import org.icepear.echarts.component.MarkPoint;
import org.icepear.echarts.component.MarkPointDataItem;
import org.icepear.echarts.component.SeriesLineLabel;
import org.icepear.echarts.component.Title;
import org.icepear.echarts.component.Toolbox;
import org.icepear.echarts.component.ToolboxDataViewFeature;
import org.icepear.echarts.component.ToolboxDataZoomFeature;
import org.icepear.echarts.component.ToolboxMagicTypeFeature;
import org.icepear.echarts.component.ToolboxRestoreFeature;
import org.icepear.echarts.component.ToolboxSaveAsImageFeature;
import org.icepear.echarts.component.Tooltip;
import org.icepear.echarts.component.ValueAxis;
import org.icepear.echarts.component.ValueAxisLabel;
import org.icepear.echarts.origin.component.marker.MarkLineDataItemOption;
import org.icepear.echarts.origin.component.toolbox.ToolboxFeatureOption;
import org.icepear.echarts.origin.util.SeriesOption;
import org.junit.Test;

public class renderComplextHtmlTest {

    private Option option;

    @Before
    public void constructOption() {
        Title title = new Title().setText("Temperature Change in the Coming Week");

        Tooltip tooltip = new Tooltip().setTrigger("axis");

        Legend legend = new Legend();

        Toolbox toolbox = new Toolbox()
                .setShow(true)
                .setFeature(new HashMap<String, ToolboxFeatureOption>() {
                    {
                        put("dataZoom", new ToolboxDataZoomFeature().setYAxisIndex("none"));
                        put("dataView", new ToolboxDataViewFeature().setReadOnly(false));
                        put("magicType", new ToolboxMagicTypeFeature()
                                .setType(new String[] { "line", "bar" }));
                        put("restore", new ToolboxRestoreFeature());
                        put("saveAsImage", new ToolboxSaveAsImageFeature());
                    }
                });

        CategoryAxis xAxis = new CategoryAxis()
                .setType("category")
                .setBoundaryGap(false)
                .setData(new String[] { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" });

        ValueAxis yAxis = new ValueAxis()
                .setType("value")
                .setAxisLabel(new ValueAxisLabel().setFormatter("{value} °C"));

        LineSeries series1 = new LineSeries()
                .setName("Highest")
                .setType("line")
                .setData(new Number[] { 10, 11, 13, 11, 12, 12, 9 })
                .setMarkPoint(new MarkPoint()
                        .setData(new MarkPointDataItem[] {
                                new MarkPointDataItem()
                                        .setType("max")
                                        .setName("Max"),
                                new MarkPointDataItem()
                                        .setType("min")
                                        .setName("Min") }))
                .setMarkLine(new MarkLine()
                        .setData(new MarkLine1DDataItem[] {
                                new MarkLine1DDataItem()
                                        .setType("average")
                                        .setName("Avg") }));

        LineSeries series2 = new LineSeries()
                .setName("Lowest")
                .setType("line")
                .setData(new Number[] { 1, -2, 2, 5, 3, 2, 0 })
                .setMarkPoint(new MarkPoint()
                        .setData(new MarkPointDataItem[] {
                                new MarkPointDataItem()
                                        .setName("周最低")
                                        .setValue(-2)
                                        .setXAxis(1)
                                        .setYAxis(-1.5) }))
                .setMarkLine(new MarkLine()
                        .setData(new MarkLineDataItemOption[] {
                                new MarkLine1DDataItem()
                                        .setType("average")
                                        .setName("Avg"),
                                new MarkLine2DDataItem()
                                        .setStartPoint(new MarkLine2DDataItemDim()
                                                .setSymbol("none")
                                                .setX("90%")
                                                .setYAxis("max"))
                                        .setEndPoint(new MarkLine2DDataItemDim()
                                                .setSymbol("circle")
                                                .setLabel(new SeriesLineLabel()
                                                        .setPosition("start")
                                                        .setFormatter("Max"))
                                                .setType("max")
                                                .setName("最高点")) }));

        Option option = new Option()
                .setTitle(title)
                .setTooltip(tooltip)
                .setLegend(legend)
                .setToolbox(toolbox)
                .setXAxis(xAxis)
                .setYAxis(yAxis)
                .setSeries(new SeriesOption[] { series1, series2 });
        this.option = option;
    }

    @Test
    public void renderComplextHtmlTest() throws IOException {
        Engine engine = new Engine();
        engine.render("test.html", this.option);
    }
}
