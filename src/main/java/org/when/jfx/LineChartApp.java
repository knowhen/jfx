package org.when.jfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * @author: when
 * @create: 2021-09-09  20:24
 **/
public class LineChartApp extends BaseApp {

    @Override
    public Parent createContent() {
        NumberAxis xAxis = new NumberAxis("Values for X-Axis", 0, 3, 1);
        NumberAxis yAxis = new NumberAxis("Values for Y-Axis", 0, 3, 1);
        ObservableList<XYChart.Series<Double, Double>> lineChartData =
                FXCollections.observableArrayList(
                        new LineChart.Series<>("Series 1",
                                FXCollections.observableArrayList(
                                        new XYChart.Data<>(0.0, 1.0),
                                        new XYChart.Data<>(1.2, 1.4),
                                        new XYChart.Data<>(2.2, 1.9),
                                        new XYChart.Data<>(2.7, 2.3),
                                        new XYChart.Data<>(2.9, 0.5))),
                        new LineChart.Series<>("Series 2",
                                FXCollections.observableArrayList(
                                        new XYChart.Data<>(0.0, 1.6),
                                        new XYChart.Data<>(0.8, 0.4),
                                        new XYChart.Data<>(1.4, 2.9),
                                        new XYChart.Data<>(2.1, 1.3),
                                        new XYChart.Data<>(2.6, 0.9)))
                );
        return new LineChart(xAxis, yAxis, lineChartData);
    }

    public static void main(String[] args) {
        launch();
    }
}
