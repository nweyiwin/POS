package com.itoneandriod.categoryactivity.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itoneandriod.categoryactivity.Model.SaleDetModel;
import com.itoneandriod.categoryactivity.Model.SaleHeadModel;

import java.util.ArrayList;

public class SaleDetDAO {
    public static String
            ColId="Id",
            ColSr="Sr",
            ColItemId="ItemId",
            ColQty="Qty",
            ColSalePrice="SalePrice",
            ColOrderId="OrderId",
            TBName="SaleDet";

    DBHelper dbhelper;
    SQLiteDatabase db;

    public SaleDetDAO(Context context) {

        dbhelper=new DBHelper(context);
        db=dbhelper.getReadableDatabase();
    }

    public void saveModel(SaleDetModel model)
    {
        ContentValues values=new ContentValues();
        values.put(ColItemId,model.ItemId);
        values.put(ColSr,model.Sr);
        values.put(ColQty,model.Qty);
        values.put(ColSalePrice,model.SalePrice);
        values.put(ColOrderId,model.OrderId);
        db.insert(TBName,null,values);
    }
    public ArrayList<SaleDetModel> findOrderDetById(int OrderId)
    {
        db=dbhelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TBName+ " where " +ColOrderId+"=?",new String[]{String.valueOf(OrderId)});

        ArrayList<SaleDetModel> saleDetModels=new ArrayList<SaleDetModel>();
        while (cursor.moveToNext())
       {
           SaleDetModel saleDetModel=new SaleDetModel();
           saleDetModel.Id=cursor.getInt(cursor.getColumnIndex(ColId));
           saleDetModel.SalePrice=cursor.getInt(cursor.getColumnIndex(ColSalePrice));
           saleDetModel.ItemId=cursor.getInt(cursor.getColumnIndex(ColItemId));
           saleDetModel.Sr=cursor.getInt(cursor.getColumnIndex(ColSr));
           saleDetModel.Qty=cursor.getInt(cursor.getColumnIndex(ColQty));
           saleDetModel.OrderId=cursor.getInt(cursor.getColumnIndex(ColOrderId));
           saleDetModels.add(saleDetModel);



       }
        return  saleDetModels;


    }

}
