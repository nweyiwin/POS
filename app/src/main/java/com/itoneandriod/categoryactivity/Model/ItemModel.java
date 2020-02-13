package com.itoneandriod.categoryactivity.Model;

public class ItemModel {
    public String Name;
    public int Id;
    public int CategoryId;
    public int BrandId;
    public int UnitId;
    public int OPrice;
    public int SPrice;
    public String PicturePath;
    public int ColorId;
    public int Disable;

    public ItemModel(String name, int id, int categoryId, int brandId, int unitId, int OPrice, int SPrice, String picturePath, int colorId, int disable) {
        Name = name;
        Id = id;
        CategoryId = categoryId;
        BrandId = brandId;
        UnitId = unitId;
        this.OPrice = OPrice;
        this.SPrice = SPrice;
        PicturePath = picturePath;
        ColorId = colorId;
        Disable = disable;
    }

    public ItemModel() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public int getBrandId() {
        return BrandId;
    }

    public void setBrandId(int brandId) {
        BrandId = brandId;
    }

    public int getUnitId() {
        return UnitId;
    }

    public void setUnitId(int unitId) {
        UnitId = unitId;
    }

    public int getOPrice() {
        return OPrice;
    }

    public void setOPrice(int OPrice) {
        this.OPrice = OPrice;
    }

    public int getSPrice() {
        return SPrice;
    }

    public void setSPrice(int SPrice) {
        this.SPrice = SPrice;
    }

    public String getPicturePath() {
        return PicturePath;
    }

    public void setPicturePath(String picturePath) {
        PicturePath = picturePath;
    }

    public int getColorId() {
        return ColorId;
    }

    public void setColorId(int colorId) {
        ColorId = colorId;
    }

    public int getDisable() {
        return Disable;
    }

    public void setDisable(int disable) {
        Disable = disable;
    }
}
