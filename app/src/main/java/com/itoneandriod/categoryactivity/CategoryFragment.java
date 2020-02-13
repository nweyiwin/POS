package com.itoneandriod.categoryactivity;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itoneandriod.categoryactivity.DAO.CategoryDAO;
import com.itoneandriod.categoryactivity.DAO.ColorDAO;
import com.itoneandriod.categoryactivity.Model.CategoryModel;
import com.itoneandriod.categoryactivity.Utill.ColorAdapter;
import com.itoneandriod.categoryactivity.Utill.RecyclerAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {


    RecyclerView rcView,colorRcView;
    CategoryDAO categoryDAO;

    EditText edtname;
    public static TextView txtcolor;
    public static  int colorId=1;
    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView= inflater.inflate(R.layout.fragment_category, container, false);
        rcView=myView.findViewById(R.id.rcview);
        colorRcView=myView.findViewById(R.id.colordata);
        categoryDAO=new CategoryDAO(getContext());
        edtname=myView.findViewById(R.id.category_name);
        txtcolor=myView.findViewById(R.id.selectcolor);
        Button btnsave=myView.findViewById(R.id.save);
        final EditText edtname=myView.findViewById(R.id.category_name);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (!TextUtils.isEmpty(edtname.getText().toString().trim()))
               {
                   String name=edtname.getText().toString().trim();
                   CategoryModel model=new CategoryModel();
                   model.Name=name;
                   model.ColorId=colorId;
                   model.Disable=0;
                   categoryDAO.insertModel(model);
                   loadData();
                   clearData();
                   Toast.makeText(getContext(),"Save OK",Toast.LENGTH_LONG).show();
               }

               else {
                   Toast.makeText(getContext(),"Please Fill",Toast.LENGTH_LONG).show();
               }

            }
        });
        loadData();
        loadcoloritem();
        return myView;
    }

    public void loadData()
    {
        RecyclerAdapter adapter=new RecyclerAdapter(categoryDAO.getModels().toArray(),getResources().getString(R.string.category_frag),getContext());
        rcView.setAdapter(adapter);
        LinearLayoutManager lm=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcView.setLayoutManager(lm);
    }
    public void clearData()
    {
        edtname.setText("");
    }

    public void loadcoloritem()
    {
        ColorDAO dao=new ColorDAO(getContext());
        ColorAdapter adapter=new ColorAdapter(dao.getModels(),getResources().getString(R.string.category_frag),getContext());
        colorRcView.setAdapter(adapter);
        GridLayoutManager gm=new GridLayoutManager(getContext(),3);
        colorRcView.setLayoutManager(gm);
    }

}
