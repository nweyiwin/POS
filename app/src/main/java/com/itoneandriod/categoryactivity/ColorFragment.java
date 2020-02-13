package com.itoneandriod.categoryactivity;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itoneandriod.categoryactivity.DAO.ColorDAO;
import com.itoneandriod.categoryactivity.Utill.ColorAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {
    RecyclerView rcColordata;
   static ColorAdapter adapter;
    static  GridLayoutManager gm;

    public ColorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_color, container, false);
        rcColordata=view.findViewById(R.id.colordata);
        loadcoloritem();
        return view;
    }
    public void loadcoloritem()
    {
        ColorDAO dao=new ColorDAO(getContext());
       adapter=new ColorAdapter(dao.getModels(),getResources().getString(R.string.item_frag),getContext());
        rcColordata.setAdapter(adapter);
        gm=new GridLayoutManager(getContext(),4);
        rcColordata.setLayoutManager(gm);
    }
    public static void removeCheck(int currentpositio)
    {
       for(int i=0; i<adapter.getItemCount();i++)
       {
           if(currentpositio != i)
           {
               View view=gm.findViewByPosition(i);
               view.findViewById(R.id.checkcolor).setVisibility(View.GONE);
           }
       }
    }

}
