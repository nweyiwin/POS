package com.itoneandriod.categoryactivity.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itoneandriod.categoryactivity.Model.BrandModel;
import com.itoneandriod.categoryactivity.Model.CategoryModel;

import java.util.ArrayList;

public class BrandDAO {
    public static String
            TBName="Brand",
            ColId="Id",
            ColName="Name",
            ColColorId="ColorId",
            ColDisable="Disable";

    DBHelper dbHelper;
    SQLiteDatabase db;
    public BrandDAO(Context context) {
        dbHelper=new DBHelper(context);
        db=dbHelper.getReadableDatabase();

    }
    public ArrayList<BrandModel> getModels()
    {
        ArrayList<BrandModel> brandModels=new ArrayList<BrandModel>();
        Cursor cursor=db.rawQuery("select * from "+TBName,null);
        while (cursor.moveToNext())
        {
            BrandModel temp=new BrandModel();
            temp.Id=cursor.getInt(cursor.getColumnIndex(ColId));
            temp.Name=cursor.getString(cursor.getColumnIndex(ColName));
            temp.ColorId=cursor.getInt(cursor.getColumnIndex(ColColorId));
            temp.Disable=cursor.getInt(cursor.getColumnIndex(ColDisable));

            brandModels.add(temp);
        }
        return brandModels;
    }

    public long insertModel(BrandModel model)
    {
        db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ColName,model.Name);
        values.put(ColColorId,model.ColorId);
        values.put(ColDisable,0);//mean active
        long id=db.insert(TBName,null,values);
        return id;
    }

}
