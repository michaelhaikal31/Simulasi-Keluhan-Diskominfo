package com.goblog.androidhive.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.goblog.androidhive.FireBaseClient;
import com.goblog.androidhive.R;

import java.io.IOException;


/**
 * Created by haikal on 4/30/2016.
 */
public class DialogKeluhan extends DialogFragment {
    private EditText nameEditText,urlEditText;
    private Button saveBtn, cameraBtn, galleryBtn;
    private ImageView imageCamera;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PICK_FROM_GALLERY = 2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        /** Inflating layout for the dialog */
        View v = inflater.inflate(R.layout.dialoglayout, null);
        getDialog().setTitle("About App");
        /** Getting the arguments passed to this fragment. Here we expects the selected item's position as argument */
        //Bundle b = getArguments();

        /** Setting the title for the dialog window *//*
        getDialog().setTitle("Time @ " + Country.name[b.getInt("position")] );
*/
        /** Getting the reference to the TextView object of the layout */
        //TextView tv = (TextView) v.findViewById(R.id.tv_current_time);

  /*      *//** Setting the current time to the TextView object of the layout *//*
        tv.setText(getCurTime(b.getInt("position")));
*/
        /** Returns the View object */
        nameEditText= (EditText)v.findViewById(R.id.nameEditText);
        urlEditText= (EditText) v.findViewById(R.id.urlEditText);
        imageCamera = (ImageView)v.findViewById(R.id.image_camera);

        saveBtn= (Button) v.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Movie m=new Movie();
                Firebase fire = new Firebase("https://androidhive-bf05b.firebaseio.com/");
                m.setName(nameEditText.toString());
                m.setUrl(urlEditText.toString());
                fire.child("Movie").push().setValue(m);*/
                FireBaseClient fireBaseClient = new FireBaseClient(getContext(), "https://androidhive-bf05b.firebaseio.com/", null);
                fireBaseClient.saveOnline(nameEditText.getText().toString(),urlEditText.getText().toString());
                nameEditText.setText("");
                urlEditText.setText("");
                Toast.makeText(getContext(),"Data has saved", Toast.LENGTH_SHORT).show();
            }
        });
        cameraBtn = (Button)v.findViewById(R.id.btn_camera);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
// ******** code for crop image
                startActivityForResult(Intent.createChooser(intent,
                        "Complete action using"), REQUEST_IMAGE_CAPTURE);
            }
        });
        galleryBtn = (Button)v.findViewById(R.id.btn_gallery);
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);//
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),PICK_FROM_GALLERY);
            }
        });
        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if(resultCode == Activity.RESULT_OK){
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");

                    imageCamera.setImageBitmap(photo);
                    imageCamera.invalidate();

                }
            }else if (resultCode == Activity.RESULT_CANCELED)
            {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
            }

        }

        if (requestCode == PICK_FROM_GALLERY)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                if (data != null)
                {
                    try
                    {

                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                        imageCamera.setImageBitmap(bitmap);
                        imageCamera.invalidate();

                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
            } else if (resultCode == Activity.RESULT_CANCELED)
            {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
