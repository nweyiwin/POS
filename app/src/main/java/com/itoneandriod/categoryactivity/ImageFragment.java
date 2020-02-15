package com.itoneandriod.categoryactivity;


import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {


    private static final int CAMERA_CODE = 124;
    private static final int PETMISSION_GALLARY = 125;
    private static final int PETMISSION_CAMERA = 123;

    public ImageFragment() {
        // Required empty public constructor
    }

    View view;
    Button btntakephoto,btnchoosephoto;
    ImageButton image;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view= inflater.inflate(R.layout.fragment_image, container, false);
       btnchoosephoto=view.findViewById(R.id.choosepicture);
       btntakephoto=view.findViewById(R.id.takephote);
       image=view.findViewById(R.id.itemimage);
       context=getContext();
       btntakephoto.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            checkpremission(btntakephoto.getId());
           }
       });
       btnchoosephoto.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               checkpremission(btnchoosephoto.getId());
           }
       });
        return view;
    }

    public void  checkpremission(int id)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(context.checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED
            && context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED
            && context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)
            {
                if(id==R.id.takephote) {
                    takephote();
                }
                else {
                    choosephoto();
                }
            }
        }
        else {
            if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)
            && shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            && shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                Toast.makeText(getContext(),"Premission Required",Toast.LENGTH_LONG).show();
            }
            if(R.id.takephote==id)
            {
                requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},PETMISSION_CAMERA);
            }
            else {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PETMISSION_GALLARY);
            }
        }
    }

    private void choosephoto()
    {
        Intent gallery=new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery,"Select Photo"),PETMISSION_GALLARY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==PETMISSION_CAMERA)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED && grantResults[2]==PackageManager.PERMISSION_GRANTED)
            {
                takephote();
            }
        }
       if(requestCode==PETMISSION_GALLARY)
       {
           if(grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED && grantResults[2]==PackageManager.PERMISSION_GRANTED)
           {
               choosephoto();
           }
       }
    }
    public static Uri imageuri;
    public void takephote()
    {
        ContentValues values=new ContentValues();
        Date date=new Date();
        SimpleDateFormat formater=new SimpleDateFormat("ddMMyyyyhhmmss");
        values.put(MediaStore.Images.Media.TITLE,formater.format(date));
        values.put(MediaStore.Video.Media.DESCRIPTION,context.getResources().getString(R.string.app_name));
        imageuri=context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageuri);
        startActivityForResult(intent,CAMERA_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CAMERA_CODE)
        {
            if (resultCode == AppCompatActivity.RESULT_OK)
            {
                image.setImageURI(imageuri);
            }
        }
        if(requestCode == PETMISSION_GALLARY)
        {
            if (resultCode == AppCompatActivity.RESULT_OK)
            {
                image.setImageURI(data.getData());
                imageuri=data.getData();
            }
        }
    }
}
