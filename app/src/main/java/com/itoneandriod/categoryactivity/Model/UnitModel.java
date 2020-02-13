package com.itoneandriod.categoryactivity.Model;

public class UnitModel {
    public String Name;
    public int Id;
    public int ColorId;
    public int Disable;

    public UnitModel(String name, int id, int colorId, int disable) {
        Name = name;
        Id = id;
        ColorId = colorId;
        Disable = disable;
    }

    public UnitModel() {
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
