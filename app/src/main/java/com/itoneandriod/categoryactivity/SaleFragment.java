package com.itoneandriod.categoryactivity;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.itoneandriod.categoryactivity.DAO.ItemDAO;
import com.itoneandriod.categoryactivity.DAO.SaleHeadDAO;
import com.itoneandriod.categoryactivity.Utill.ItemAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class SaleFragment extends Fragment {


    public SaleFragment() {
        // Required empty public constructor
    }

    View v;
    Button btnorderNo;
    ItemDAO itemDAO;
    RecyclerView  rcView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       v= inflater.inflate(R.layout.fragment_sale, container, false);
        initUI();
       getNewOrderNo();
       loadData();
        return v;
    }

    public void initUI()
    {
        btnorderNo=v.findViewById(R.id.orderNo);
        rcView=v.findViewById(R.id.itemdata);
    }

    public void getNewOrderNo()
    {
        SaleHeadDAO saleHeadDAO=new SaleHeadDAO(getContext());
        SimpleDateFormat df=new SimpleDateFormat("ddMMyyyy");
        String part1=df.format(new Date());
        String part2=saleHeadDAO.getDailyCount(part1)+1+"";
        btnorderNo.setText(part1+part2);
    }

    public void loadData()
    {
        ItemAdapter adapter=new ItemAdapter(itemDAO.getModels(),getContext());
        rcView.setAdapter(adapter);
        LinearLayoutManager lm=new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        rcView.setLayoutManager(lm);
    }
}
