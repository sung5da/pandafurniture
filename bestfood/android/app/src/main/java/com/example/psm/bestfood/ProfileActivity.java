package com.example.psm.bestfood;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.psm.bestfood.item.MemberInfoItem;
import com.example.psm.bestfood.lib.EtcLib;
import com.example.psm.bestfood.lib.MyLog;
import com.example.psm.bestfood.lib.MyToast;
import com.example.psm.bestfood.lib.StringLib;
import com.example.psm.bestfood.remote.*;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.example.psm.bestfood.remote.RemoteService;
//import com.example.psm.bestfood.remote.ServiceGenerator;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = this.getClass().getSimpleName();
    Context context;
    ImageView profileIconImage;
    ImageView profileIconChangeImage;
    EditText nameEdit;
    EditText sextypeEdit;
    EditText birthEdit;
    EditText phoneEdit;

    MemberInfoItem currentItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        context = this;

        currentItem = ((MyApp)getApplication()).getMemberInfoItem();

        setToolbar();
        setView();
    }

    @Override
    protected void onResume(){
        super.onResume();

        MyLog.d(TAG,RemoteService.MEMBER_ICON_URL + currentItem.memberIconFilename);

        if(StringLib.getInstance().isBlank(currentItem.memberIconFilename)){
            Picasso.with(this).load(R.drawable.ic_person).into(profileIconImage);
        }else{
            Picasso.with(this).load(RemoteService.MEMBER_ICON_URL + currentItem.memberIconFilename).into(profileIconImage);
        }
    }

    private void setToolbar(){
        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.profile_setting);
        }
    }

    private void setView(){
        profileIconImage = (ImageView)findViewById(R.id.profile_icon);
        profileIconImage.setOnClickListener(this);

        nameEdit = (EditText)findViewById(R.id.profile_name);
        nameEdit.setText(currentItem.name);

        sextypeEdit = (EditText)findViewById(R.id.profile_sextype);
        sextypeEdit.setText(currentItem.sextype);
        sextypeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSexTypeDialog();
            }
        });

        birthEdit = (EditText)findViewById(R.id.profile_birth);
        birthEdit.setText(currentItem.birthday);
        birthEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBirthdayDialog();
            }
        });

        String phoneNumber = EtcLib.getInstance().getPhoneNumber(context);

        phoneEdit = (EditText)findViewById(R.id.profile_phone);
        phoneEdit.setText(currentItem.phone);

        TextView phoneStateEdit = (TextView)findViewById(R.id.phone_state);



    }
}
