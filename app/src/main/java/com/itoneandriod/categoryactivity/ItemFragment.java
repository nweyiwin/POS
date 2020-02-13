package com.itoneandriod.categoryactivity;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.itoneandriod.categoryactivity.DAO.BrandDAO;
import com.itoneandriod.categoryactivity.DAO.CategoryDAO;
import com.itoneandriod.categoryactivity.DAO.ColorDAO;
import com.itoneandriod.categoryactivity.DAO.ItemDAO;
import com.itoneandriod.categoryactivity.DAO.UnitDAO;
import com.itoneandriod.categoryactivity.Model.BrandModel;
import com.itoneandriod.categoryactivity.Model.CategoryModel;
import com.itoneandriod.categoryactivity.Model.ItemModel;
import com.itoneandriod.categoryactivity.Model.UnitModel;
import com.itoneandriod.categoryactivity.Utill.ColorAdapter;
import com.itoneandriod.categoryactivity.Utill.ItemAdapter;
import com.itoneandriod.categoryactivity.Utill.RecyclerAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment implements View.OnClickListener {


    ItemDAO itemDAO;
    CategoryDAO categoryDAO;
    BrandDAO brandDAO;
    UnitDAO unitDAO;
    RecyclerView rcView;
    EditText edtname,edtsaleprice,edtoriginalprice;
    RadioButton rbtusecolor,rbtuseimage;
    Spinner spcategory,spbrand,spunit;
    Button btnsave,btncancel;
    View myView;

    ArrayList<CategoryModel> categoryModels=new ArrayList<CategoryModel>();
    ArrayList<BrandModel> brandModels=new ArrayList<BrandModel>();
    ArrayList<UnitModel> unitModels=new ArrayList<UnitModel>();

    public static  int selectcolor=1;

    public ItemFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         myView= inflater.inflate(R.layout.fragment_item, container, false);

         initUI();
         loadData();
         loadspinnetdata();
         btnsave.setOnClickListener(this);
         rbtusecolor.setOnClickListener(this);
         rbtuseimage.setOnClickListener(this);
         clearData();

        return myView;
    }

    public void loadData()
    {
        ItemAdapter adapter=new ItemAdapter(itemDAO.getModels(),getContext());
        rcView.setAdapter(adapter);
        LinearLayoutManager lm=new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        rcView.setLayoutManager(lm);
    }
    public void clearData()
    {
        edtname.setText("");
    }

    public void setFragment(Fragment f)
    {
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.mainframe,f);
        ft.commit();
    }

    public void initUI()
    {
        rcView=myView.findViewById(R.id.rcview);
        edtname =myView.findViewById(R.id.item_name);
        edtsaleprice=myView.findViewById(R.id.sprice);
        edtoriginalprice=myView.findViewById(R.id.oprice);
        btnsave=myView.findViewById(R.id.save);
        btncancel=myView.findViewById(R.id.cancel);
        spcategory=myView.findViewById(R.id.categoryspinner);
        spbrand=myView.findViewById(R.id.brandspinner);
        spunit=myView.findViewById(R.id.unitspinner);
        rbtusecolor=myView.findViewById(R.id.rbtusecolor);
        rbtuseimage=myView.findViewById(R.id.rbtiusemage);

        itemDAO = new ItemDAO(getContext());
        categoryDAO=new CategoryDAO(getContext());
        brandDAO=new BrandDAO(getContext());
        unitDAO=new UnitDAO(getContext());
        rbtusecolor.setChecked(true);
        setFragment(new ColorFragment());
    }
    public void loadspinnetdata()
    {
        categoryModels=categoryDAO.getModels();
        ArrayList<String> categorynames=new ArrayList<String>();
        for(CategoryModel c: categoryModels)
        {
            categorynames.add(c.Name);
        }
        ArrayAdapter<String> adaptercategory=new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,categorynames);
        spcategory.setAdapter(adaptercategory);


         brandModels=brandDAO.getModels();
        ArrayList<String> brandnames=new ArrayList<String>();
        for(BrandModel b: brandModels)
        {
            brandnames.add(b.Name);
        }
        ArrayAdapter<String> adapterbrand=new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,brandnames);
        spbrand.setAdapter(adapterbrand);

        unitModels=unitDAO.getModels();
        ArrayList<String> unitnames=new ArrayList<String>();
        for (UnitModel u: unitModels)
        {
            unitnames.add(u.Name);
        }
        ArrayAdapter<String> adapterunit=new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,unitnames);
        spunit.setAdapter(adapterunit);

    }

    @Override
    public void onClick(View v) {
        Button btnselected=(Button)v;
        if(btnselected.getId()==R.id.save) {
            if (!TextUtils.isEmpty(edtname.getText().toString().trim())) {
                String name = edtname.getText().toString().trim();
                String oprice = edtoriginalprice.getText().toString().trim();
                String sprice = edtsaleprice.getText().toString().trim();

                ItemModel model = new ItemModel();
                model.Name = name;
                model.CategoryId = categoryModels.get(spcategory.getSelectedItemPosition()).Id;
                model.BrandId = brandModels.get(spbrand.getSelectedItemPosition()).Id;
                model.UnitId = unitModels.get(spunit.getSelectedItemPosition()).Id;
                model.OPrice = Integer.parseInt(oprice);
                model.SPrice = Integer.parseInt(sprice);
                model.ColorId = selectcolor;
                model.PicturePath = "";
                model.Disable = 0;

                itemDAO.insertModel(model);
                loadData();
                clearData();

                Toast.makeText(getContext(), "Save OK", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Please Fill", Toast.LENGTH_LONG).show();
            }
        }
        if(btnselected.getId()==R.id.rbtusecolor)
        {
            setFragment(new ColorFragment());
            rbtusecolor.setChecked(true);
        }
        if(btnselected.getId()==R.id.rbtiusemage)
        {
            setFragment(new ImageFragment());
            rbtuseimage.setChecked(true);
        }

    }
}
