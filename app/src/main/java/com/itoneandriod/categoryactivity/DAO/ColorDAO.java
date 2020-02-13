package com.itoneandriod.categoryactivity.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.itoneandriod.categoryactivity.Model.CategoryModel;
import com.itoneandriod.categoryactivity.Model.ColorModel;

import java.util.ArrayList;

public class ColorDAO {
    public static String
                    TBName="Color",
                    ColId="Id",
                    ColRed="ColorRed",
                    ColGreen="ColorGreen",
                    ColBlue="ColorBlue";

    public ColorDAO() {
    }

    DBHelper dbHelper;
    SQLiteDatabase db;
    public ColorDAO(Context context) {
        dbHelper=new DBHelper(context);
        db=dbHelper.getReadableDatabase();

    }
    public ColorModel getModelById(int id)
    {
        db=dbHelper.getReadableDatabase();
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(TBName);
        String selectionargs[]=new String[]{String.valueOf(id)};
        String selection=ColId+" = ? ";
        Cursor cursor=qb.query(db,null,selection,selectionargs,null,null,null);
        ColorModel temp=new ColorModel();
        while (cursor.moveToNext())
        {

            temp.ColorId=cursor.getInt(cursor.getColumnIndex(ColId));
            temp.ColorRed=cursor.getInt(cursor.getColumnIndex(ColRed));
            temp.ColorGreen=cursor.getInt(cursor.getColumnIndex(ColGreen));
            temp.ColorBlue=cursor.getInt(cursor.getColumnIndex(ColBlue));

        }
        return temp;
    }
    public long saveModel(ColorModel model)
    {
        db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ColRed,model.ColorRed);
        values.put(ColGreen,model.ColorGreen);
        values.put(ColBlue,model.ColorBlue);
        long id=db.insert(TBName,null,values);
        return id;
    }
    public ArrayList<ColorModel> getModels()
    {
        db=dbHelper.getReadableDatabase();
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(TBName);
        Cursor cursor=qb.query(db,null,null,null,null,null,null);
        ArrayList<ColorModel> colorModels=new ArrayList<ColorModel>();
        while (cursor.moveToNext())
        {
            ColorModel temp=new ColorModel();
            temp.ColorId=cursor.getInt(cursor.getColumnIndex(ColId));
            temp.ColorRed=cursor.getInt(cursor.getColumnIndex(ColRed));
            temp.ColorGreen=cursor.getInt(cursor.getColumnIndex(ColGreen));
            temp.ColorBlue=cursor.getInt(cursor.getColumnIndex(ColBlue));
            colorModels.add(temp);
        }
        return colorModels;
    }
}
