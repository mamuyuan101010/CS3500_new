package lineChart;

/**
 * ILineChart interface is an interface that make a LineChart
 */
public interface ILineChart {
    /**
     *
     * @return a String of a ChartTable to show portfolio's value
     * on each date in each row, and list those data line by line,
     *
     */
    String generateChart();
}
