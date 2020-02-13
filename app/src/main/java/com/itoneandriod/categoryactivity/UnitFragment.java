package com.itoneandriod.categoryactivity;


import android.os.Bundle;

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

import com.itoneandriod.categoryactivity.DAO.ColorDAO;
import com.itoneandriod.categoryactivity.DAO.UnitDAO;
import com.itoneandriod.categoryactivity.Model.UnitModel;
import com.itoneandriod.categoryactivity.Utill.ColorAdapter;
import com.itoneandriod.categoryactivity.Utill.RecyclerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class UnitFragment extends Fragment implements View.OnClickListener {


    UnitDAO unitDAO;
    RecyclerView rcView,colorRcView;
    EditText edtname;
    Button btnsave;
    View myView;
    public static TextView txtcolor;
    public static  int colorId=1;
    public UnitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         myView= inflater.inflate(R.layout.fragment_unit, container, false);
        initUI();
        loadData();
        clearData();
        loadcoloritem();
       btnsave.setOnClickListener(this);
        return myView;
    }
    public void initUI()
    {
        rcView=myView.findViewById(R.id.rcview);
        edtname=myView.findViewById(R.id.unit_name);
        unitDAO=new UnitDAO(getContext());
        txtcolor=myView.findViewById(R.id.coloritem);
        colorRcView=myView.findViewById(R.id.colordata);
        btnsave=myView.findViewById(R.id.save);
    }
    public void loadData()
    {
        RecyclerAdapter adapter=new RecyclerAdapter(unitDAO.getModels().toArray(),getResources().getString(R.string.unit_frag),getContext());
        rcView.setAdapter(adapter);
        LinearLayoutManager lm=new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        rcView.setLayoutManager(lm);
    }
    public void clearData()
    {
        edtname.setText("");
    }
    public void loadcoloritem()
    {
        ColorDAO dao=new ColorDAO(getContext());
        ColorAdapter adapter=new ColorAdapter(dao.getModels(),getResources().getString(R.string.unit_frag),getContext());
        colorRcView.setAdapter(adapter);
        GridLayoutManager gm=new GridLayoutManager(getContext(),3);
        colorRcView.setLayoutManager(gm);
    }

    @Override
    public void onClick(View v) {
        if (!TextUtils.isEmpty(edtname.getText().toString().trim()))
        {
            String name=edtname.getText().toString().trim();
            UnitModel model=new UnitModel();
            model.Name=name;
            model.ColorId=colorId;
            model.Disable=0;
            UnitDAO unitDAO=new UnitDAO(getContext());
            unitDAO.insertModel(model);

            loadData();
            clearData();
            Toast.makeText(getContext(),"Save OK",Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(getContext(),"Please Fill",Toast.LENGTH_LONG).show();
        }
    }
}
