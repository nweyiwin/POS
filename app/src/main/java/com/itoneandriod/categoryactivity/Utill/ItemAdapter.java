package com.itoneandriod.categoryactivity.Utill;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itoneandriod.categoryactivity.DAO.CategoryDAO;
import com.itoneandriod.categoryactivity.DAO.ColorDAO;
import com.itoneandriod.categoryactivity.Model.CategoryModel;
import com.itoneandriod.categoryactivity.Model.ColorModel;
import com.itoneandriod.categoryactivity.Model.ItemModel;
import com.itoneandriod.categoryactivity.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder>{
    ArrayList<ItemModel> itemModels=new ArrayList<ItemModel>();

    Context context;
    public ItemAdapter(ArrayList<ItemModel> itemModels,Context con) {
        this.itemModels = itemModels;
        this.context=con;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout,parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        holder.sr.setText(position+1+".");
        holder.itemname.setText(itemModels.get(position).Name);

        ColorDAO dao=new ColorDAO(context);
        final ColorModel temp=dao.getModelById(itemModels.get(position).ColorId);
        final int colorcode= Color.argb(255,temp.ColorRed,temp.ColorGreen,temp.ColorBlue);
        holder.coloritem.setBackgroundColor(colorcode);


    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder
    {
        TextView sr,itemname,categoryname,coloritem;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            sr=itemView.findViewById(R.id.sr);
            itemname=itemView.findViewById(R.id.itemname);
            categoryname=itemView.findViewById(R.id.categoryname);
            coloritem=itemView.findViewById(R.id.coloritem);

        }
    }
}
