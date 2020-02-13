package com.itoneandriod.categoryactivity.Utill;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itoneandriod.categoryactivity.BrandFragment;
import com.itoneandriod.categoryactivity.CategoryFragment;
import com.itoneandriod.categoryactivity.ColorFragment;
import com.itoneandriod.categoryactivity.ItemFragment;
import com.itoneandriod.categoryactivity.Model.ColorModel;
import com.itoneandriod.categoryactivity.R;
import com.itoneandriod.categoryactivity.UnitFragment;

import java.util.ArrayList;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorHolder> {
    ArrayList<ColorModel> colorModels=new ArrayList<ColorModel>();
    String fragname;
    Context context;
    public ColorAdapter(ArrayList<ColorModel> colorModels, String fragname, Context context)
    {
        this.colorModels = colorModels;
        this.context=context;
        this.fragname=fragname;
    }
    @NonNull
    @Override
    public ColorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.coloritem,parent,false);
        return new ColorHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ColorHolder holder, final int position) {

        final ColorModel temp=colorModels.get(position);
        final int colorcode= Color.argb(255,temp.ColorRed,temp.ColorGreen,temp.ColorBlue);
        holder.coloritem.setBackgroundColor(colorcode);
        holder.coloritem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(fragname.equals(context.getResources().getString(R.string.category_frag)))
               {
                   CategoryFragment.txtcolor.setBackgroundColor(colorcode);
                   CategoryFragment.colorId=temp.ColorId;
               }
                if(fragname.equals(context.getResources().getString(R.string.brand_frag)))
                {
                    BrandFragment.txtcolor.setBackgroundColor(colorcode);
                    BrandFragment.colorId=temp.ColorId;
                }
                if(fragname.equals(context.getResources().getString(R.string.unit_frag)))
                {
                    UnitFragment.txtcolor.setBackgroundColor(colorcode);
                    UnitFragment.colorId=temp.ColorId;
                }
                if(fragname.equals(context.getResources().getString(R.string.item_frag)))
                {
                   ItemFragment.selectcolor=temp.getColorId();
                   holder.checkcolor.setVisibility(View.VISIBLE);
                    ColorFragment.removeCheck(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return colorModels.size();
    }



    public class ColorHolder extends RecyclerView.ViewHolder
    {
        TextView coloritem;
        ImageButton checkcolor;
        public ColorHolder(@NonNull View itemView) {
            super(itemView);
            coloritem=itemView.findViewById(R.id.coloritem);
            checkcolor=itemView.findViewById(R.id.checkcolor);
        }
    }
}
