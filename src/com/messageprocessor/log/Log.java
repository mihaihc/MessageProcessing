package com.messageprocessor.log;

import com.messageprocessor.model.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class Log {

    private HashMap<String, Product> products = new HashMap<>();

    private double totalSalesValue = 0;

    private ArrayList<String> reportsList = new ArrayList <String>();

    private ArrayList adjustmentReports = new ArrayList <String>();

    public Product getProduct(String type) {
        return products.getOrDefault(type,new Product(type));
    }

    public void updateProduct(Product product) {
        products.put(product.getProductType(), product);
    }

    public void setReportsList(String reportsList) {

        this.reportsList.add(reportsList);
    }

    public ArrayList getAdjustmentReports() {
        return adjustmentReports;
    }

    public void setAdjustmentReports(String adjustmentReport) {
        this.adjustmentReports.add(adjustmentReport);
    }

    public double getTotalSalesValue() {

        return totalSalesValue;
    }

    public void appendTotalSalesValue(double productTotalPrice) {
        totalSalesValue += productTotalPrice;
    }

    public void setTotalSalesValue(double productTotalPrice) {
        totalSalesValue = productTotalPrice;
    }

    public void report() {

        if((reportsList.size() % 10) == 0 && reportsList.size() !=0) {
            setTotalSalesValue(0.0);
            System.out.println("Application reached 10 messages!");
            System.out.println("-------- Log Report ---------");
            System.out.println("|Product|Quantity|Value|");
            for (String key : products.keySet()) {
                Product product = products.get(key);
                System.out.println("|" + product.getProductType() + "|" + product.getTotalQuantity() + "|" + product.getTotalPrice());
                appendTotalSalesValue(product.getTotalPrice());
            }
            System.out.println("-------------------------------------------");
            System.out.println("Total Sales" + getTotalSalesValue());
            System.out.println("-------------------------------------------");
            System.out.println("End\n\n");
        }

        if((reportsList.size() % 50) == 0 && reportsList.size() !=0) {
            System.out.println("Application reached 50 messages. The application is pausing!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The following are the adjustment records made;\n");
            System.out.println(getAdjustmentReports().toString());
            System.out.println("Resuming the application!");
        }
    }
}
