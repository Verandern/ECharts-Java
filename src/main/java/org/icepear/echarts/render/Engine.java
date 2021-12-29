package org.icepear.echarts.render;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import org.icepear.echarts.Chart;
import org.icepear.echarts.Option;
import org.icepear.echarts.serializer.EChartsSerializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Engine {
    private Handlebars handlebars;

    public Engine() throws IOException {
        this.handlebars = new Handlebars();
    }

    /**
     * Generate Html file according to the specified path
     * 
     * @param html     a String representing in html format
     * @param path     path to save the html file
     * @param willOpen whether allowing to open the html in browser automatically
     * @throws IOException
     */
    private void writeHtml(String html, String path, Boolean willOpen) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(html);
            writer.close();
            if (willOpen) {
                File file = new File(path);
                Desktop.getDesktop().browse(file.toURI());
            }
        } catch (IOException e) {
            log.info("Write Html failed. Path error.");
        }
    }

    /**
     * Used in simple chart case, render the echarts in html file, in default width,
     * height and willOpen
     * 
     * @param path  path to save the html file
     * @param chart the chart to be rendered
     * @throws IOException
     */
    public void render(String path, Chart<?, ?> chart) {
        String jsonStr = EChartsSerializer.toJson(chart.getOption());
        ChartMeta chartMeta = new ChartMeta(600, 600, jsonStr);
        try {
            Template template = handlebars.compile("index");
            String html = template.apply(chartMeta);
            writeHtml(html, path, false);
        } catch (IOException e) {
            log.info("render: Handlebars cannot find corresponding templates.");
        }
    }

    /**
     * Used in advanced chart case, render the echarts in html file, in default
     * width, height and willOpen
     * 
     * @param path   path to save the html file
     * @param option the option used to init the chart
     * @throws IOException
     */
    public void render(String path, Option option) {
        String jsonStr = EChartsSerializer.toJson(option);
        ChartMeta chartMeta = new ChartMeta(600, 600, jsonStr);
        try {
            Template template = handlebars.compile("index");
            String html = template.apply(chartMeta);
            writeHtml(html, path, false);
        } catch (IOException e) {
            log.info("render: Handlebars cannot find corresponding templates.");
        }
    }

    /**
     * Used in both simple and advanced chart cases, render the echarts in
     * customized width, height, and willOpen
     * 
     * @param path     path to save the html file
     * @param option   the option used to init the chart
     * @param height   the height of the chart
     * @param width    the width of the chart
     * @param willOpen whether allowing to open the html in browser automatically
     * @throws IOException
     */
    public void render(String path, Option option, int height, int width, Boolean willOpen) {
        String jsonStr = EChartsSerializer.toJson(option);
        ChartMeta chartMeta = new ChartMeta(height, width, jsonStr);
        try {
            Template template = handlebars.compile("index");
            String html = template.apply(chartMeta);
            writeHtml(html, path, willOpen);
        } catch (IOException e) {
            log.info("render: Handlebars cannot find corresponding templates.");
        }
    }

    /**
     * Used in the simple case, render the echarts in default width and height,
     * without download button
     * 
     * @param chart the chart to be rendered
     * @return a string in html format
     * @throws IOException
     */
    public String renderHtml(Chart<?, ?> chart) {
        String jsonStr = EChartsSerializer.toJson(chart.getOption());
        ChartMeta chartMeta = new ChartMeta(600, 600, jsonStr);
        Template template = null;
        try {
            template = handlebars.compile("base");
            return template.apply(chartMeta);
        } catch (IOException e) {
            log.info("renderHtml: Handlebars cannot find corresponding templates.");
            return "";
        }
    }

    /**
     * Used in the advanced case, render the echarts in default width and height,
     * without download button
     * 
     * @param option the option to initiate the chart
     * @return the resulted string in html format
     * @throws IOException
     */
    public String renderHtml(Option option) {
        String jsonStr = EChartsSerializer.toJson(option);
        ChartMeta chartMeta = new ChartMeta(600, 600, jsonStr);
        Template template = null;
        try {
            template = handlebars.compile("base");
            return template.apply(chartMeta);
        } catch (IOException e) {
            log.info("renderHtml: Handlebars cannot find corresponding templates.");
            return "";
        }
    }

    /**
     * Used in both the simple and advanced cases, render the echarts in customized
     * width and height, without download button
     * 
     * @param option the option to initiate the chart
     * @param height the height of the chart
     * @param width  the width of the chart
     * @return the resulted string in html format
     * @throws IOException
     */
    public String renderHtml(Option option, int height, int width) throws IOException {
        String jsonStr = EChartsSerializer.toJson(option);
        ChartMeta chartMeta = new ChartMeta(height, width, jsonStr);
        try {
            Template template = handlebars.compile("base");
            return template.apply(chartMeta);    
        } catch (IOException e) {
            log.info("renderHtml: Handlebars cannot find corresponding templates.");
            return "";
        }
    }

}
