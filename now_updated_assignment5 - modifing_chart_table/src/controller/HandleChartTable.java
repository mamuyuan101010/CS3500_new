package controller;

import model.IModelV2;
import view.IView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class HandleChartTable extends AbstractCommandsV2{

    HandleChartTable(Scanner sn) {
        super(sn);
    }
    @Override
    public void execute(IModelV2 model, IView v) throws IOException {
        try {
            LocalDate startDate = LocalDate.parse(getInput(v, "startDate(in YYYY-MM-DD format):"));
            LocalDate endDate = LocalDate.parse(getInput(v, "endDate(in YYYY-MM-DD format):"));
            String portfolioName = getInput(v, "portfolioName");
            String chart = model.getChart(startDate, endDate, portfolioName);
            v.showCustomMessage(chart);
            v.showCustomMessage("\n");
        } catch (Exception e) {
            v.showCustomMessage(e.getMessage());
        }
    }
}
