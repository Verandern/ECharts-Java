package org.icepear.echarts.pie;

import static org.junit.Assert.assertEquals;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.icepear.echarts.component.Legend;
import org.icepear.echarts.component.Option;
import org.icepear.echarts.component.PieDataItem;
import org.icepear.echarts.component.PieEmphasis;
import org.icepear.echarts.component.PieItemStyle;
import org.icepear.echarts.component.PieSeries;
import org.icepear.echarts.component.Title;
import org.icepear.echarts.component.Tooltip;
import org.icepear.echarts.serializer.EChartSerializer;
import org.icepear.echarts.type.BoxLength;
import org.icepear.echarts.type.EChartsRadius;
import org.junit.Test;

public class RefererOfAWebsiteTest {
    @Test
    public void testRefererOfAWebsite() {
        Title title = new Title()
                .setText("Referer of a Website")
                .setSubtext("Fake Data")
                .setLeft(new BoxLength("center"));

        Tooltip tooltip = new Tooltip().setTrigger("item");

        Legend legend = new Legend()
                .setOrient("vertical")
                .setLeft(new BoxLength("left"));

        PieSeries series = new PieSeries()
                .setName("Access From")
                .setType("pie")
                .setRadius(new EChartsRadius("50%"))
                .setData(Arrays.asList(
                        new PieDataItem().setValue(1048).setName("Search Engine"),
                        new PieDataItem().setValue(735).setName("Direct"),
                        new PieDataItem().setValue(580).setName("Email"),
                        new PieDataItem().setValue(484).setName("Union Ads"),
                        new PieDataItem().setValue(300).setName("Video Ads")))
                .setEmphasis(new PieEmphasis()
                        .setItemStyle(new PieItemStyle()
                                .setShadowBlur(10)
                                .setShadowOffsetX(0)
                                .setShadowColor("rgba(0, 0, 0, 0.5)")));

        Option option = new Option()
                .setTitle(title)
                .setTooltip(tooltip)
                .setLegend(legend)
                .setSeries(Arrays.asList(series));

        Reader reader = new InputStreamReader(
                this.getClass().getResourceAsStream("/pie/referer-of-a-website.json"));
        JsonElement expected = JsonParser.parseReader(reader);
        JsonElement actual = EChartSerializer.toJsonTree(option);
        assertEquals(expected, actual);

        // System.out.println(EChartSerializer.toJson(option));
    }
}
