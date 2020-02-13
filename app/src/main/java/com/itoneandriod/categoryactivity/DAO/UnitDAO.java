package com.itoneandriod.categoryactivity.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itoneandriod.categoryactivity.Model.CategoryModel;
import com.itoneandriod.categoryactivity.Model.UnitModel;

import java.util.ArrayList;

public class UnitDAO {
    public static String
            TBName="Unit",
            ColId="Id",
            ColName="Name",
            ColColorId="ColorId",
            ColDisable="Disable";

    Context context;
    DBHelper dbHelper;
    SQLiteDatabase db;
    public UnitDAO(Context context) {
        this.context=context;
        dbHelper=new DBHelper(context);
        db=dbHelper.getReadableDatabase();

    }
    public ArrayList<UnitModel> getModels()
    {
        ArrayList<UnitModel> unitModels=new ArrayList<UnitModel>();
        Cursor cursor=db.rawQuery("select * from "+TBName,null);
        while (cursor.moveToNext())
        {
            UnitModel temp=new UnitModel();
            temp.Id=cursor.getInt(cursor.getColumnIndex(ColId));
            temp.Name=cursor.getString(cursor.getColumnIndex(ColName));
            temp.ColorId=cursor.getInt(cursor.getColumnIndex(ColColorId));
            temp.Disable=cursor.getInt(cursor.getColumnIndex(ColDisable));

            unitModels.add(temp);
        }
        return unitModels;
    }
    public long insertModel(UnitModel model)
    {
        db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ColName,model.Name);
        values.put(ColColorId,model.ColorId);
        values.put(ColDisable,model.Disable);
        long id=db.insert(TBName,null,values);
        return id;
    }

}
