package com.messageprocessor.model;

import com.messageprocessor.log.Log;
import com.messageprocessor.parser.MessageParser;

public class Sale {
    public Log log = null;

    private AdjustPrice adjustPrice = null;

    private Product product = null;

    public Sale()
    {
        log = new Log();
    }

    public void processNotification(String saleNotice) {
        MessageParser messageParser = new MessageParser(saleNotice);

        String productType = messageParser.getProductType();

        if (null != productType && productType.isEmpty()) {
            System.out.println("Product type is empty. Invalid message!");
        } else {
            this.product = log.getProduct(productType);

            this.adjustPrice = new AdjustPrice(product);
            this.product.setProductQuantity(messageParser.getProductQuantity());
            this.product.setTotalQuantity(messageParser.getProductQuantity());
            this.product.setProductPrice(messageParser.getProductPrice());
            this.product.setAdjustmentOperator(messageParser.getOperatorType());

            setProductTotalPrice();

            log.setReportsList(saleNotice);
            log.updateProduct(product);
        }
    }

    private void setProductTotalPrice() {
        double adjustedPrice;
        double productValue;

        if (null != product.getAdjustmentOperator() && !product.getAdjustmentOperator().isEmpty()) {
            adjustedPrice = adjustPrice.getAdjustedPrice();
            log.setAdjustmentReports(adjustPrice.adjustmentReport());
            product.setTotalPrice(adjustedPrice);
        } else {
            productValue = product.calculatePrice(product.getProductQuantity(), product.getProductPrice());
            product.appendTotalPrice(productValue);
        }
    }

}