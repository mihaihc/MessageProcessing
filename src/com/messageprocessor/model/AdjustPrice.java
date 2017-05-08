package com.messageprocessor.model;

public class AdjustPrice {

    private double adjustedPrice = 0;

    private Product product = null;

    public AdjustPrice(Product product) {
        this.product = product;
    }

    public double getAdjustedPrice() {
        if (product.getAdjustmentOperator().compareToIgnoreCase("add") == 0) {
            addPrice();
        } else if (product.getAdjustmentOperator().compareToIgnoreCase("subtract") == 0) {
            subtractPrice();
        } else if (product.getAdjustmentOperator().compareToIgnoreCase("multiply") == 0) {
            multiplyPrice();
        } else {
            System.out.println("Invalid Adjustment Operator");
        }
        return adjustedPrice;
    }

    public void addPrice() {
        this.adjustedPrice = this.product.getTotalPrice() + ( this.product.getTotalQuantity() * this.product.getProductPrice() );
    }

    public void subtractPrice() {
        this.adjustedPrice = this.product.getTotalPrice() - (this.product.getTotalQuantity() * this.product.getProductPrice());
    }

    public void multiplyPrice() {
        this.adjustedPrice = this.product.getTotalPrice() +
                (this.product.getTotalPrice() * this.product.getProductPrice()) +
                (this.product.getTotalQuantity() * this.product.getProductPrice());
    }

    public String adjustmentReport(){
        String adjustmentReport = String.format(
                "Performed %s %.2fp to %d %s and price adjusted from %.2fp to %.2fp",
                this.product.getAdjustmentOperator(),
                this.product.getProductPrice(),
                this.product.getTotalQuantity(),
                this.product.getProductType(),
                this.product.getTotalPrice(),
                this.adjustedPrice
        );
        return adjustmentReport;
    }
}
