package com.itoneandriod.categoryactivity.Utill;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itoneandriod.categoryactivity.DAO.ColorDAO;
import com.itoneandriod.categoryactivity.Model.BrandModel;
import com.itoneandriod.categoryactivity.Model.CategoryModel;
import com.itoneandriod.categoryactivity.Model.ColorModel;
import com.itoneandriod.categoryactivity.Model.UnitModel;
import com.itoneandriod.categoryactivity.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
    Context context;
    String fragname;
    ArrayList<CategoryModel> categoryModels=new ArrayList<CategoryModel>();
    ArrayList<BrandModel> brandModels=new ArrayList<BrandModel>();
    ArrayList<UnitModel> unitModels=new ArrayList<UnitModel>();

    public RecyclerAdapter(Object[]objects,String fragname,Context context) {
        this.fragname=fragname;
        this.context=context;
       if(fragname.equals(context.getResources().getString(R.string.category_frag)))
       {
           for(Object o : objects)
           {
               this.categoryModels.add((CategoryModel)o);
           }

       }
        if(fragname.equals(context.getResources().getString(R.string.brand_frag)))
        {
            for(Object o : objects)
            {
                this.brandModels.add((BrandModel) o);
            }

        }
        if(fragname.equals(context.getResources().getString(R.string.unit_frag)))
        {
            for(Object o : objects)
            {
                this.unitModels.add((UnitModel) o);
            }

        }
    }
    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView= LayoutInflater.from(parent.getContext()).inflate(R.layout.categoryitem,parent,false);
        return new RecyclerHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        if(fragname.equals(context.getResources().getString(R.string.category_frag)))
        {
            holder.sr.setText(position+1+".");
            holder.name.setText(categoryModels.get(position).Name);
            ColorDAO dao=new ColorDAO(context);
            final ColorModel temp=dao.getModelById(categoryModels.get(position).ColorId);
            final int colorcode= Color.argb(255,temp.ColorRed,temp.ColorGreen,temp.ColorBlue);
            holder.coloritem.setBackgroundColor(colorcode);
        }
        if(fragname.equals(context.getResources().getString(R.string.brand_frag)))
        {
            holder.sr.setText(position+1+".");
            holder.name.setText(brandModels.get(position).Name);
            ColorDAO dao=new ColorDAO(context);
            final ColorModel temp=dao.getModelById(brandModels.get(position).ColorId);
            final int colorcode= Color.argb(255,temp.ColorRed,temp.ColorGreen,temp.ColorBlue);
            holder.coloritem.setBackgroundColor(colorcode);
        }
        if(fragname.equals(context.getResources().getString(R.string.unit_frag)))
        {
            holder.sr.setText(position+1+".");
            holder.name.setText(unitModels.get(position).Name);
            ColorDAO dao=new ColorDAO(context);
            final ColorModel temp=dao.getModelById(unitModels.get(position).ColorId);
            final int colorcode= Color.argb(255,temp.ColorRed,temp.ColorGreen,temp.ColorBlue);
            holder.coloritem.setBackgroundColor(colorcode);
        }
       
    }

    @Override
    public int getItemCount() {
        if(fragname.equals(context.getResources().getString(R.string.category_frag)))
        {
            return categoryModels.size();
        }
        if(fragname.equals(context.getResources().getString(R.string.brand_frag)))
        {
            return brandModels.size();
        }
        if(fragname.equals(context.getResources().getString(R.string.unit_frag)))
        {
            return unitModels.size();
        }
        else {
            return 0;
        }
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder
    {
        TextView sr,name,coloritem;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            sr=itemView.findViewById(R.id.sr);
            name=itemView.findViewById(R.id.txtname);
            coloritem=itemView.findViewById(R.id.coloritem);
        }
    }
}
