package com.itoneandriod.categoryactivity.Model;

public class SaleHeadModel {

    public int Id,
                TotalAmount,
                UserId,
                CustomerId,
                Sr;
    public String Date,OrderNo;

    public SaleHeadModel(int id, int totalAmount, int userId, int customerId, int sr, String date, String orderNo) {
        Id = id;
        TotalAmount = totalAmount;
        UserId = userId;
        CustomerId = customerId;
        Sr = sr;
        Date = date;
        OrderNo = orderNo;
    }

    public SaleHeadModel() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        TotalAmount = totalAmount;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public int getSr() {
        return Sr;
    }

    public void setSr(int sr) {
        Sr = sr;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }
}
