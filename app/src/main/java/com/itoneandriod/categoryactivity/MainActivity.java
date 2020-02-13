package com.itoneandriod.categoryactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.itoneandriod.categoryactivity.DAO.ColorDAO;
import com.itoneandriod.categoryactivity.Model.ColorModel;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment(new CategoryFragment());
            setFragment(new BrandFragment());
            setFragment(new UnitFragment());
            setFragment(new ItemFragment());
        ColorDAO colorDAO=new ColorDAO(getApplicationContext());
        if (colorDAO.getModels().size() <= 0)
        {
            Random r=new Random();
            for(int i=0; i<11; i++)
            {
                ColorModel temp=new ColorModel();
                temp.ColorRed=r.nextInt(256);
                temp.ColorGreen=r.nextInt(256);
                temp.ColorBlue=r.nextInt(256);
                colorDAO.saveModel(temp);
            }
        }
    }
    public void setFragment(Fragment f)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.mainframe,f);
        ft.commit();
    }

}
