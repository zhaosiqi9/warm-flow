/*
 *    Copyright 2024-2025, Warm-Flow (290631660@qq.com).
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.dromara.warm.flow.core.chart;

import lombok.Getter;
import lombok.Setter;
import org.dromara.warm.flow.core.enums.ChartStatus;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 流程图绘制
 *
 * @author warm
 */
@Getter
@Setter
public class FlowChartChain {

    /**
     * 宽度
     */
    private int width;

    /**
     * 高度
     */
    private int height;

    /**
     * 倍数
     */
    private int n;

    /**
     * 画笔
     */
    private Graphics2D graphics;

    /**
     * 所有图形
     */
    private final List<FlowChart> flowChartList = new ArrayList<>();

    public void addFlowChart(FlowChart flowChart) {
        flowChartList.add(flowChart);
    }

    public void draw(int width, int height, int offsetW, int offsetH, Graphics2D graphics, int n) {
        this.width = width;
        this.height = height;
        this.n = n;
        this.graphics = graphics;

        // 右上角流程图状态示例
        setExample(width, n);

        // 右下角log文字
        setFlowTitle(width, height, n);

        for (FlowChart flowChart : flowChartList) {
            flowChart.offset(offsetW, offsetH);
        }

        this.graphics.setStroke(new BasicStroke(2.5f));
        for (FlowChart flowChart : flowChartList) {
            flowChart.setN(n).draw(this.graphics);
        }
    }

    private void setFlowTitle(int width, int height, int n) {
        int textX = (width - 400) / n;
        int textY = (height - 100) / n;
        Font font = new Font("微软雅黑", Font.BOLD, 20 * n);
        String title = "Warm-Flow";
        TextChart textChart = new TextChart(textX, textY, title, font)
                .setAlpha(0.80f);
        addFlowChart(textChart);
    }

    private void setExample(int width, int n) {
        int tmp = width - 600;
        for (ChartStatus value : ChartStatus.values()) {
            int nodeX = tmp / n;
            int nodeY = 0;
            int textX = nodeX + 30;
            int textY = nodeY + 15;
            TextChart textChart = new TextChart(textX, textY, value.getValue());
            addFlowChart(new ExampleChart(nodeX, nodeY, value.getColor(), textChart));
            tmp += 140;
        }
    }
}
