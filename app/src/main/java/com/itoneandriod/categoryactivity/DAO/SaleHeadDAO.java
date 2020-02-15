package com.itoneandriod.categoryactivity.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Sampler;

import com.itoneandriod.categoryactivity.Model.SaleDetModel;
import com.itoneandriod.categoryactivity.Model.SaleHeadModel;

import java.sql.Array;
import java.util.ArrayList;

public class SaleHeadDAO {

    public String
            ColId = "Id",
            ColDate = "Date",
            ColOrderNo = "OrderNo",
            ColTotalAmount = "TotalAmount",
            ColUserId = "UserId",
            ColCustomerId = "CustomerId",
            ColSr = "Sr",
            TBName = "SaleHead";

    DBHelper dbHelper;
    android.database.sqlite.SQLiteDatabase db;

    public SaleHeadDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getReadableDatabase();

    }
    public void saveModel(SaleHeadModel model)
    {
        ContentValues values=new ContentValues();
        values.put(ColDate,model.Date);
        values.put(ColOrderNo,model.OrderNo);
        values.put(ColTotalAmount,model.TotalAmount);
        values.put(ColCustomerId,model.CustomerId);
        values.put(ColUserId,model.UserId);
        values.put(ColSr,model.Sr);
        db.insert(TBName,null,values);
    }
    public  int findDailyOrder (String Date)
    {
        db=dbHelper.getReadableDatabase();
        String sql = "select ColOrderNo from " + TBName + " where " + ColDate + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{Date});
        int order=0;
        while (cursor.moveToNext())
        {
            order=cursor.getInt(getDailyCount(ColOrderNo));

        }
        return order;
    }
    public int getDailyCount(String date) {
        db = dbHelper.getReadableDatabase();
        String sql = "select count(*) from " + TBName + " where " + ColDate + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{date});
        int count = 0;
        while (cursor.moveToNext()) {
            count = cursor.getInt(0);
        }
        return count;
    }
    public  int findDailyTotal (String date)
    {
        db=dbHelper.getReadableDatabase();
        String sql = "select sum(ColTotalAmount) from " + TBName + " where " + ColDate + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{date});
        int total=0;
        while (cursor.moveToNext())
        {
            total=cursor.getInt(getDailyCount(ColTotalAmount));

        }
        return total;
    }
    public ArrayList<SaleHeadModel> findOrdersByRange(String fromDate, String toDate)
    {
        db=dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TBName + " where " + ColDate + "=?",new String[]{fromDate,toDate});

        ArrayList<SaleHeadModel> saleHeadModels=new ArrayList<>();
        while (cursor.moveToNext())
        {
            SaleHeadModel saleHeadModel=new SaleHeadModel();
            saleHeadModel.Id=cursor.getInt(cursor.getColumnIndex(ColId));
            saleHeadModel.CustomerId=cursor.getInt(cursor.getColumnIndex(ColCustomerId));
            saleHeadModel.Date=cursor.getString(cursor.getColumnIndex(ColDate));
            saleHeadModel.TotalAmount=cursor.getInt(cursor.getColumnIndex(ColTotalAmount));
            saleHeadModel.OrderNo=cursor.getString(cursor.getColumnIndex(ColOrderNo));
            saleHeadModel.Sr=cursor.getInt(cursor.getColumnIndex(ColSr));
            saleHeadModel.UserId=cursor.getInt(cursor.getColumnIndex(ColUserId));
            saleHeadModels.add(saleHeadModel);
        }
        return saleHeadModels;
    }

}