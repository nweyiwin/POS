package com.itoneandriod.categoryactivity.Model;


public class SaleDetModel {

    public int
            Id,
            Sr,
            ItemId,
            Qty,
            SalePrice,
            OrderId;

    public SaleDetModel(int id, int sr, int itemId, int qty, int salePrice, int orderId) {
        Id = id;
        Sr = sr;
        ItemId = itemId;
        Qty = qty;
        SalePrice = salePrice;
        OrderId = orderId;
    }

    public SaleDetModel() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getSr() {
        return Sr;
    }

    public void setSr(int sr) {
        Sr = sr;
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public int getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(int salePrice) {
        SalePrice = salePrice;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }
}
