package com.ulimit.common;

public class MyOrders{


    static long id;

    {
        id += 1;
    }

    long orderID;
    Double amount;
    transient String currency;
    String comment;
    String fileName;
    int line;
    //String result;

    public MyOrders() {
    }

    public MyOrders(long orderID, Double amount, String currency, String comment, String fileName, int line) {

        this.orderID = orderID;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
        this.fileName = fileName;
        this.line = line;
        //this.result = result;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString(){
        return orderID + ":" + fileName;
    }
}