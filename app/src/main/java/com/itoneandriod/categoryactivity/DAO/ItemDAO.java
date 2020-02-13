package com.itoneandriod.categoryactivity.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itoneandriod.categoryactivity.Model.CategoryModel;
import com.itoneandriod.categoryactivity.Model.ItemModel;
import com.itoneandriod.categoryactivity.Model.UnitModel;

import java.util.ArrayList;

public class ItemDAO {
    public static String
            TBName="Item",
            ColId="Id",
            ColName="Name",
            ColCategoryId="CategoryId",
            ColBrandId="BrandId",
            ColUnitId="UnitId",
            ColOPrice="OPrice",
            ColSPrice="SPrice",
            ColColorId="ColorId",
            ColPicturepath="PicturePath",
            ColDisable="Disable";

    public ItemDAO() {
    }

    Context context;
    DBHelper dbHelper;
    SQLiteDatabase db;
    public ItemDAO(Context context) {
        this.context=context;
        dbHelper=new DBHelper(context);
        db=dbHelper.getReadableDatabase();

    }
    public ArrayList<ItemModel> getModels()
    {
        ArrayList<ItemModel> itemModels=new ArrayList<ItemModel>();
        Cursor cursor=db.rawQuery("select * from "+TBName,null);
        while (cursor.moveToNext())
        {
            ItemModel temp=new ItemModel();
            temp.Id=cursor.getInt(cursor.getColumnIndex(ColId));
            temp.Name=cursor.getString(cursor.getColumnIndex(ColName));
            temp.CategoryId=cursor.getInt(cursor.getColumnIndex(ColCategoryId));
            temp.BrandId=cursor.getInt(cursor.getColumnIndex(ColBrandId));
            temp.UnitId=cursor.getInt(cursor.getColumnIndex(ColUnitId));
            temp.OPrice=cursor.getInt(cursor.getColumnIndex(ColOPrice));
            temp.SPrice=cursor.getInt(cursor.getColumnIndex(ColSPrice));
            temp.PicturePath=cursor.getString(cursor.getColumnIndex(ColPicturepath));
            temp.ColorId=cursor.getInt(cursor.getColumnIndex(ColColorId));
            temp.Disable=cursor.getInt(cursor.getColumnIndex(ColDisable));

            itemModels.add(temp);
        }
        return itemModels;
    }
    public long insertModel(ItemModel model)
    {
        db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ColName,model.Name);
        values.put(ColCategoryId,model.CategoryId);
        values.put(ColBrandId,model.ColorId);
        values.put(ColUnitId,model.UnitId);
        values.put(ColOPrice,model.OPrice);
        values.put(ColSPrice,model.SPrice);
        values.put(ColPicturepath,""); // picturepath
        values.put(ColColorId,model.ColorId);
        values.put(ColDisable,0);
        long id=db.insert(TBName,null,values);
        return id;
    }

}
