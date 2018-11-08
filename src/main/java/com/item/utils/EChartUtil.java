package com.item.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.MarkType;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.feature.Feature;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.MarkPoint;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.series.Series;

public class EChartUtil {
    public static final String TYPE_BAR = "bar";
    public static final String TYPE_LINE = "line";

    private String title;      //标题
    private String subTitle;   //副标题
    private List<String> hiddenData;

    public EChartUtil(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getLineOrBarOption(Map<String, List<Object>> data, List<Object> xValue, boolean max, boolean min, boolean average, String type) {
        return getLineOrBarOption(data, xValue, max, min, average, type, null);
    }

    @SuppressWarnings("unchecked")
    public String getLineOrBarOption(Map<String, List<Object>> data, List<Object> xValue, boolean max, boolean min, boolean average, String type, String formatter) {
        List<Object> legendData = new ArrayList<Object>();
        List<List<Object>> yValues = new ArrayList<List<Object>>();
        Iterator<String> iter = data.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            legendData.add(key);
            yValues.add(data.get(key));
        }
        Option option = new GsonOption();
        option.title(title, subTitle).calculable(true).title().x(X.left);
        option.legend().data(legendData).orient(Orient.horizontal).x(X.center);

        Map<String, Boolean> hidden = new HashMap<String, Boolean>();
        if (!CollectionUtils.isEmpty(hiddenData)) {
            for (String name : hiddenData) {
                hidden.put(name, false);
            }
        }
        option.legend().selected(hidden);

        option.tooltip().trigger(Trigger.axis);
        if (formatter != null) {
            option.tooltip().setFormatter(formatter);
        }
        option.toolbox().show(true).feature(Tool.dataView, new com.github.abel533.echarts.feature.MagicType(Magic.line, Magic.bar).show(true), Tool.saveAsImage);

        //option.dataZoom().show(true).y(36);

        ValueAxis valueAxis = new ValueAxis();
        valueAxis.axisLabel().formatter("{value}");
        option.yAxis(valueAxis);

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.boundaryGap(EChartUtil.TYPE_BAR.equals(type));
        categoryAxis.setData(xValue);
        option.xAxis(categoryAxis);

        for (int i = 0; i < legendData.size(); i++) {
            @SuppressWarnings("rawtypes")
            Series series = null;
            if (EChartUtil.TYPE_BAR.equals(type))
                series = new Bar();
            else
                series = new Line();

            series.name(legendData.get(i).toString());
            series.setData(yValues.get(i));

            if (max || min) {
                MarkPoint markPoint = new MarkPoint();
                if (max)
                    markPoint.data((new Data().type(MarkType.max).name("最大值")));
                if (min)
                    markPoint.data((new Data().type(MarkType.min).name("最小值")));
                series.markPoint(markPoint);
            }

            if (average)
                series.markLine().data(new Data().type(MarkType.average).name("平均值")).itemStyle().normal();

            series.itemStyle().normal();
            option.series(series);
        }
        return option.toString();
    }

    @SuppressWarnings("unchecked")
    public String getLineAndBarOption(Map<String, List<Object>> data, List<Object> xValue, boolean max, boolean min, boolean average, String type,
                                      Map<String, List<Object>> data2, List<Object> xValue2, boolean max2, boolean min2, boolean average2, String type2) {
        List<Object> legendData = new ArrayList<Object>();
        List<List<Object>> yValues = new ArrayList<List<Object>>();
        List<Object> legendData2 = new ArrayList<Object>();
        List<List<Object>> yValues2 = new ArrayList<List<Object>>();
        Iterator<String> iter = data.keySet().iterator();
        Iterator<String> iter2 = data2.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            legendData.add(key);
            yValues.add(data.get(key));
        }
        while (iter2.hasNext()) {
            String key = iter2.next();
            legendData2.add(key);
            yValues2.add(data2.get(key));
        }
        Option option = new GsonOption();
        List<Object> legendData3 = new ArrayList<Object>();
        legendData3.addAll(legendData);
        legendData3.addAll(legendData2);
        option.title(title, subTitle).calculable(true).title().x(X.left);
        option.legend().data(legendData3).orient(Orient.horizontal).x(X.center);

        Map<String, Boolean> hidden = new HashMap<String, Boolean>();
        if (!CollectionUtils.isEmpty(hiddenData)) {
            for (String name : hiddenData) {
                hidden.put(name, false);
            }
        }
        option.legend().selected(hidden);

        option.tooltip().trigger(Trigger.axis);
        option.toolbox().show(true).feature(Tool.dataView, new com.github.abel533.echarts.feature.MagicType(Magic.line, Magic.bar).show(true), Tool.saveAsImage);

        //option.dataZoom().show(true).y(36);

        ValueAxis valueAxis = new ValueAxis();
        valueAxis.axisLabel().formatter("{value}");
        ValueAxis valueAxis2 = new ValueAxis();
        valueAxis2.axisLabel().formatter("{value}");
        option.yAxis(valueAxis, valueAxis2);

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setData(xValue);
        option.xAxis(categoryAxis);

        for (int i = 0; i < legendData.size(); i++) {
            @SuppressWarnings("rawtypes")
            Series series = null;
            if (EChartUtil.TYPE_BAR.equals(type))
                series = new Bar();
            else {
                series = new Line();
                series.yAxisIndex(1);
            }

            series.name(legendData.get(i).toString());
            series.setData(yValues.get(i));

            if (max || min) {
                MarkPoint markPoint = new MarkPoint();
                if (max)
                    markPoint.data((new Data().type(MarkType.max).name("最大值")));
                if (min)
                    markPoint.data((new Data().type(MarkType.min).name("最小值")));
                series.markPoint(markPoint);
            }

            if (average)
                series.markLine().data(new Data().type(MarkType.average).name("平均值")).itemStyle().normal();

            series.itemStyle().normal();
            option.series(series);
        }
        for (int i = 0; i < legendData2.size(); i++) {
            @SuppressWarnings("rawtypes")
            Series series = null;
            if (EChartUtil.TYPE_BAR.equals(type2))
                series = new Bar();
            else {
                series = new Line();
                series.yAxisIndex(1);
            }

            series.name(legendData2.get(i).toString());
            series.setData(yValues2.get(i));

            if (max2 || min2) {
                MarkPoint markPoint = new MarkPoint();
                if (max2)
                    markPoint.data((new Data().type(MarkType.max).name("最大值")));
                if (min2)
                    markPoint.data((new Data().type(MarkType.min).name("最小值")));
                series.markPoint(markPoint);
            }

            if (average2)
                series.markLine().data(new Data().type(MarkType.average).name("平均值")).itemStyle().normal();

            series.itemStyle().normal();
            option.series(series);
        }
        return option.toString();
    }

    public String getPieOption(List<Object> legendData, List<Object> values) {
        Option option = new GsonOption();
        option.title(title, subTitle).calculable(true).title().x(X.center);
        option.legend().data(legendData).orient(Orient.vertical).x(X.left);
        option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)");
        option.toolbox().show(true).feature(Tool.saveAsImage, Tool.dataView);

        Pie pie = new Pie();
        pie.name(title).center(600, 200).radius(150);
        for (int i = 0; i < legendData.size(); i++)
            pie.data(new Data().name(legendData.get(i).toString()).value(values.get(i)));
        option.series(pie);

        return option.toString();
    }

    class MagicType extends Feature {
        @SuppressWarnings({"rawtypes", "unchecked"})
        public MagicType(MyMagic... magics) {
            this.show(true);
            Map title = new HashMap<String, String>();
            title.put("line", "折线图切换");
            title.put("bar", "柱形图切换");
            title.put("stack", "堆积");
            title.put("tiled", "平铺");
            title.put("funnel", "漏斗图切换");
            title.put("pie", "饼图切换");
            this.title(title);
            if (magics == null || magics.length == 0) {
                this.type(new Object[]{Magic.bar, Magic.line});
            } else {
                this.type(magics);
            }
        }
    }

    enum MyMagic {
        line, bar, stack, tiled, funnel, pie
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public List<String> getHiddenData() {
        return hiddenData;
    }

    public void setHiddenData(List<String> hiddenData) {
        this.hiddenData = hiddenData;
    }
}
