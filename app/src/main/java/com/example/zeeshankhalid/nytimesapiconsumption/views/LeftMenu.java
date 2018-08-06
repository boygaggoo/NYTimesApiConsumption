package com.example.zeeshankhalid.nytimesapiconsumption.views;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zeeshankhalid.nytimesapiconsumption.Constants;
import com.example.zeeshankhalid.nytimesapiconsumption.R;
import com.example.zeeshankhalid.nytimesapiconsumption.controllers.interfaces.LeftMenuItemClickListener;


/**
 * Created by khalidz on 10/19/2017.
 */

public class LeftMenu extends RelativeLayout implements View.OnClickListener {
    private LeftMenuItemClickListener leftMenuItemClickListener;
    LinearLayout previouslySelectedLayout;
    LinearLayout llLabel1, llLabel2;
    ImageView ivProfileImage;
    TextView tvEmail, tvAppVersion;

    public LeftMenu(Context context) {
        super(context);
        initViews(context);
    }

    public LeftMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public LeftMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }


    private void initViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        try {
            inflater.inflate(R.layout.layout_left_menu, this);
        } catch (Exception e) {
            Log.e("view-error", e.toString());
        }
        bindViews();

    }



    private void bindViews() {
        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvAppVersion = (TextView) findViewById(R.id.tvAppVersion);

        try {
            PackageInfo packageInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            tvAppVersion.setText("v" + packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        llLabel1 = (LinearLayout) findViewById(R.id.llLabel1);
        llLabel2 = (LinearLayout) findViewById(R.id.llLabel2);



        llLabel1.setOnClickListener(this);
        llLabel2.setOnClickListener(this);

        previouslySelectedLayout = llLabel1;

    }

    public void selectLabel1() {
        previouslySelectedLayout.findViewById(R.id.llActive).setVisibility(GONE);
        previouslySelectedLayout.findViewById(R.id.llInactive).setVisibility(VISIBLE);
        previouslySelectedLayout = llLabel1;
        previouslySelectedLayout.findViewById(R.id.llActive).setVisibility(VISIBLE);
        previouslySelectedLayout.findViewById(R.id.llInactive).setVisibility(GONE);
    }

    public void selectLabel2() {
        previouslySelectedLayout.findViewById(R.id.llActive).setVisibility(GONE);
        previouslySelectedLayout.findViewById(R.id.llInactive).setVisibility(VISIBLE);
        previouslySelectedLayout = llLabel2;
        previouslySelectedLayout.findViewById(R.id.llActive).setVisibility(VISIBLE);
        previouslySelectedLayout.findViewById(R.id.llInactive).setVisibility(GONE);
    }

    public void setOnLeftMenuItemClickListener(LeftMenuItemClickListener leftMenuItemClickListener) {
        this.leftMenuItemClickListener = leftMenuItemClickListener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llLabel1:
                if (previouslySelectedLayout.equals(llLabel1)) {
                    leftMenuItemClickListener.onLeftMenuItemClicked(Constants.DO_NOTHING);
                } else {
                    leftMenuItemClickListener.onLeftMenuItemClicked(R.id.llLabel1);
                }
                break;

            case R.id.llLabel2:
                if (previouslySelectedLayout.equals(llLabel2)) {
                    leftMenuItemClickListener.onLeftMenuItemClicked(Constants.DO_NOTHING);
                } else {
                    leftMenuItemClickListener.onLeftMenuItemClicked(R.id.llLabel2);
                }
                break;

        }
    }
}
