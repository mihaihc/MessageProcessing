package com.messageprocessor.model;

public class Product {

    private double productPrice = 0;

    private int productQuantity = 0;

    private String adjustmentOperator = null;

    private String productType = null;

    private int totalQuantity = 0;

    private double totalPrice = 0;

    @Override
    public String toString() {
        return "Product{" +
                "productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                ", adjustmentOperator='" + adjustmentOperator + '\'' +
                ", productType='" + productType + '\'' +
                ", totalQuantity=" + totalQuantity +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public Product(String type) {
        this.productType = type;
    }

    public double calculatePrice(int productQuantity, double productPrice){
        return productQuantity * productPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void appendTotalPrice(double productPrice) {

        this.totalPrice += productPrice;
    }

    public void setTotalQuantity(int quantity) {
        this.totalQuantity += quantity;
    }

    public int getTotalQuantity() {
        return this.totalQuantity;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String type) {
        this.productType = type;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getAdjustmentOperator() {
        return adjustmentOperator;
    }

    public void setAdjustmentOperator(String adjustmentOperator) {
        this.adjustmentOperator = adjustmentOperator;
    }
}
