package controller;

import model.IModelV2;
import view.IView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class HandleChartTable extends AbstractCommandsV2 {

    HandleChartTable(Scanner sn) {
        super(sn);
    }

    @Override
    public void execute(IModelV2 model, IView v) throws IOException {
        try {
            LocalDate startDate = LocalDate.parse(getInput(v, "startDate(in YYYY-MM-DD format):"));
            LocalDate endDate = LocalDate.parse(getInput(v, "endDate(in YYYY-MM-DD format):"));
            String portOrStock = getInput(v, "Portfolio Or Stock?: ");
            if (portOrStock.equals("Portfolio")) {
                String portName = getInput(v, "enter Portfolio name: ");
                v.showCustomMessage(model.getChartFromPortfolio(startDate, endDate, portOrStock));
                v.showCustomMessage("\n");
            } else if (portOrStock.equals("Stock")) {
                String ticker = getInput(v, "enter the stock name:");
                v.showCustomMessage(model.getChartFromStock(startDate, endDate, ticker));
                v.showCustomMessage("\n");
            } else {
                v.showCustomMessage("unrecognized type");
            }
        } catch (Exception e) {
            v.showCustomMessage(e.getMessage());
        }
    }
}
